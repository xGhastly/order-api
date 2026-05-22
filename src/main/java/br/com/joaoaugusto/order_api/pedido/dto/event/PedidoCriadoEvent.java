package br.com.joaoaugusto.order_api.pedido.dto.event;

public class PedidoCriadoEvent {

    private Long id;
    private String enderecoEntrega;

    public PedidoCriadoEvent(Long id, String enderecoEntrega) {
        this.id = id;
        this.enderecoEntrega = enderecoEntrega;
    }

    public PedidoCriadoEvent() {
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
