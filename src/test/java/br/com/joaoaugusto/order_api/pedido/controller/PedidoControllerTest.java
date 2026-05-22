package br.com.joaoaugusto.order_api.pedido.controller;

import br.com.joaoaugusto.order_api.pedido.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.pedido.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.pedido.service.PedidoService;
import br.com.joaoaugusto.order_api.pedido.stub.PedidoRequestDTOStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PedidoControllerTest {

    private final PedidoService pedidoService = mock(PedidoService.class);
    private final PedidoController pedidoController = new PedidoController(pedidoService);

    @Test
    @DisplayName("Deve retornar status CREATED ao criar pedido")
    void deveRetornarStatusCreatedAoCriarPedido() {
        PedidoRequestDTO dto = PedidoRequestDTOStub.newBuilder().withAllFiels().build();
        doNothing().when(pedidoService).criarPedido(any(PedidoRequestDTO.class));

        ResponseEntity<String> response = pedidoController.criarPedido(dto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("Pedido criado com sucesso!");
        verify(pedidoService, times(1)).criarPedido(dto);
    }

    @Test
    @DisplayName("Deve retornar status OK e lista de pedidos")
    void deveRetornarStatusOKEListaDePedidos() {
        final PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setCodigoCliente("123");
        final List<PedidoResponseDTO> expectedList = List.of(dto);
        when(pedidoService.listarPedidos()).thenReturn(expectedList);

        final ResponseEntity<List<PedidoResponseDTO>> response = pedidoController.listarPedidos();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .isEqualTo(expectedList);
        verify(pedidoService, times(1)).listarPedidos();
    }

    @Test
    @DisplayName("Deve retornar status OK e pedido por ID")
    void deveRetornarStatusOKEPedidoPorId() {
        final PedidoResponseDTO expected = new PedidoResponseDTO();
        expected.setCodigoCliente("123");
        when(pedidoService.listarPedidoPorId(1L)).thenReturn(expected);

        final ResponseEntity<PedidoResponseDTO> response = pedidoController.listarPedidoPorId(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .isEqualTo(expected);
        verify(pedidoService, times(1)).listarPedidoPorId(1L);
    }
}
