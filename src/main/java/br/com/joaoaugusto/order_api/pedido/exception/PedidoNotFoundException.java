package br.com.joaoaugusto.order_api.pedido.exception;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(Long id) {
        super("Pedido não encontrado para o ID: " + id);
    }
}
