package br.com.joaoaugusto.order_api.pedido.controller;

import br.com.joaoaugusto.order_api.pedido.exception.PedidoNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderApiExceptionHandlerTest {

    private final OrderApiExceptionHandler exceptionHandler = new OrderApiExceptionHandler();

    @Test
    @DisplayName("Deve tratar PedidoNotFoundException")
    void deveTratarPedidoNotFoundException() {
        final PedidoNotFoundException ex = new PedidoNotFoundException(1L);

        final ResponseEntity<Map<String, Object>> response = exceptionHandler.handlePedidoNotFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().get("error")).isEqualTo("Recurso não encontrado");
        assertThat(response.getBody().get("detalhe")).isEqualTo("Pedido não encontrado para o ID: 1");
        assertThat(response.getBody().get("timestamp")).isNotNull();
    }
}
