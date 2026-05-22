package br.com.joaoaugusto.order_api.pedido.stub;

import br.com.joaoaugusto.order_api.pedido.model.ItemPedido;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class PedidoStub {

    private Long id;
    private String codigoCliente;
    private BigDecimal valorTotal;
    private String enderecoEntrega;
    private List<ItemPedido> itens;

    public static PedidoStub newBuilder() { return new PedidoStub();}

    public PedidoStub withAllFiels() {
        this.id = 1L;
        this.codigoCliente = "5123";
        this.valorTotal = BigDecimal.valueOf(523.23);
        this.enderecoEntrega = "Av Juazeiro, 212";
        this.itens = new ArrayList<>(List.of(new ItemPedido("TV", "6215")));
        return this;
    }

    public PedidoStub withId(Long id) {
        this.id = id;
        return this;
    }

    public PedidoStub withCodigoCliente(String codigoCliente){
        this.codigoCliente = codigoCliente;
        return this;
    }

    public PedidoStub withValorTotal(BigDecimal valorTotal){
        this.valorTotal = valorTotal;
        return this;
    }

    public PedidoStub withEnderecoEntrega(String enderecoEntrega){
        this.enderecoEntrega = enderecoEntrega;
        return this;
    }

    public PedidoStub withItens(List<ItemPedido> itens){
        this.itens = itens;
        return this;
    }

    public Pedido build() {
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setCodigoCliente(this.codigoCliente);
        pedido.setValorTotal(this.valorTotal);
        pedido.setEnderecoEntrega(this.enderecoEntrega);
        pedido.setItens(this.itens);
        return pedido;
    }
}
