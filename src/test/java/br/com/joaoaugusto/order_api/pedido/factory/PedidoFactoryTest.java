package br.com.joaoaugusto.order_api.pedido.factory;

import br.com.joaoaugusto.order_api.pedido.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.pedido.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import br.com.joaoaugusto.order_api.pedido.stub.PedidoRequestDTOStub;
import br.com.joaoaugusto.order_api.pedido.stub.PedidoStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PedidoFactoryTest {

    @Test
    @DisplayName("Deve converter PedidoRequestDTO para Pedido")
    void deveConverterPedidoRequestDTOParaPedido() {
        final PedidoRequestDTO dto = PedidoRequestDTOStub.newBuilder().withAllFiels().build();
        final Pedido expected = PedidoStub.newBuilder().withAllFiels().withId(null).build();

        final Pedido result = PedidoFactory.pedidoFactory(dto);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Deve converter Pedido para PedidoResponseDTO")
    void deveConverterPedidoParaPedidoResponseDTO() {
        final Pedido pedido = PedidoStub.newBuilder().withAllFiels().build();
        final PedidoRequestDTO dtoStub = PedidoRequestDTOStub.newBuilder().withAllFiels().build();

        final PedidoResponseDTO expected = new PedidoResponseDTO();
        expected.setCodigoCliente(dtoStub.getCodigoCliente());
        expected.setValorTotal(dtoStub.getValorTotal());
        expected.setEnderecoEntrega(dtoStub.getEnderecoEntrega());
        expected.setItens(dtoStub.getItens());

        final PedidoResponseDTO result = PedidoFactory.pedidoResponseDTOFactory(pedido);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
