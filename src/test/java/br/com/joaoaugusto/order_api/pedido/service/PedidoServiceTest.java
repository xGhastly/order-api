package br.com.joaoaugusto.order_api.pedido.service;

import br.com.joaoaugusto.order_api.pedido.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.pedido.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.pedido.exception.PedidoNotFoundException;
import br.com.joaoaugusto.order_api.pedido.factory.PedidoFactory;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import br.com.joaoaugusto.order_api.pedido.producer.PedidoProducer;
import br.com.joaoaugusto.order_api.pedido.repository.PedidoRepository;
import br.com.joaoaugusto.order_api.pedido.stub.PedidoRequestDTOStub;
import br.com.joaoaugusto.order_api.pedido.stub.PedidoStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {
    private final PedidoRepository pedidoRepository = mock(PedidoRepository.class);
    private final PedidoProducer pedidoProducer = mock(PedidoProducer.class);
    private final PedidoService pedidoService = new PedidoService(pedidoRepository, pedidoProducer);


    @Test
    @DisplayName("Testa a adição de um pedido")
    public void testAdicionarPedido() {
        final Pedido pedidoSalvo = PedidoStub.newBuilder().withAllFiels().build();
        final PedidoRequestDTO pedidoRequestDTO = PedidoRequestDTOStub.newBuilder().withAllFiels().build();

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoSalvo);
        doNothing().when(pedidoProducer).postarPedido(any(Pedido.class));

        pedidoService.criarPedido(pedidoRequestDTO);

        verify(pedidoRepository, times(1)).save(any(Pedido.class));
        verify(pedidoProducer, times(1)).postarPedido(any(Pedido.class));
    }

    @Test
    @DisplayName("Testa a listagem de pedidos")
    public void testListarPedidos() {
        final Pedido pedido = PedidoStub.newBuilder().withAllFiels().build();
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));
        
        final PedidoResponseDTO expected = PedidoFactory.pedidoResponseDTOFactory(pedido);

        final List<PedidoResponseDTO> result = pedidoService.listarPedidos();

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(List.of(expected));
        
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Testa a listagem de pedido por ID")
    public void testListarPedidoPorId() {
        final Pedido pedido = PedidoStub.newBuilder().withAllFiels().build();
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        final PedidoResponseDTO expected = PedidoFactory.pedidoResponseDTOFactory(pedido);

        final PedidoResponseDTO result = pedidoService.listarPedidoPorId(1L);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
                
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Testa a listagem de pedido por ID não encontrado")
    public void testListarPedidoPorIdNotFound() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () -> pedidoService.listarPedidoPorId(1L));
        verify(pedidoRepository, times(1)).findById(1L);
    }
}
