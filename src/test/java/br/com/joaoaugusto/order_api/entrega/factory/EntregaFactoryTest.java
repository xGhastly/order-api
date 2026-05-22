package br.com.joaoaugusto.order_api.entrega.factory;

import br.com.joaoaugusto.order_api.entrega.dto.PedidoEvent;
import br.com.joaoaugusto.order_api.entrega.model.Entrega;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntregaFactoryTest {

    @Test
    @DisplayName("Deve converter PedidoEvent para Entrega")
    void deveConverterPedidoEventParaEntrega() {
        final PedidoEvent event = new PedidoEvent(1L, "Rua A, 123");
        final  Entrega expected = new Entrega();
        expected.setPedidoId(1L);
        expected.setEnderecoEntrega("Rua A, 123");

        final Entrega result = EntregaFactory.entregaFactory(event);

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }
}
