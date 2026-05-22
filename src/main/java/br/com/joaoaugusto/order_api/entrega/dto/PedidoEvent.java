package br.com.joaoaugusto.order_api.entrega.dto;

public class PedidoEvent {

    private Long id;
    private String enderecoEntrega;

    public PedidoEvent(Long id, String enderecoEntrega) {
        this.id = id;
        this.enderecoEntrega = enderecoEntrega;
    }

    public PedidoEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
}
