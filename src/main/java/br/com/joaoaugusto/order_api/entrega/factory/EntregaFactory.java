package br.com.joaoaugusto.order_api.entrega.factory;

import br.com.joaoaugusto.order_api.entrega.dto.PedidoEvent;
import br.com.joaoaugusto.order_api.entrega.model.Entrega;

public class EntregaFactory {

    public static Entrega entregaFactory(PedidoEvent pedidoEvent) {
        Entrega entrega = new Entrega();
        entrega.setPedidoId(pedidoEvent.getId());
        entrega.setEnderecoEntrega(pedidoEvent.getEnderecoEntrega());
        return entrega;
    }
}
