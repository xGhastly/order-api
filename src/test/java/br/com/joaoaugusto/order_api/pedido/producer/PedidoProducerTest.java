package br.com.joaoaugusto.order_api.pedido.producer;

import br.com.joaoaugusto.order_api.config.RabbitMQConfig;
import br.com.joaoaugusto.order_api.pedido.dto.event.PedidoCriadoEvent;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import br.com.joaoaugusto.order_api.pedido.stub.PedidoStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PedidoProducerTest {

    private final RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
    private final PedidoProducer pedidoProducer = new PedidoProducer(rabbitTemplate);

    @Test
    @DisplayName("Deve postar pedido no RabbitMQ")
    void devePostarPedidoNoRabbitMQ() {
        final Pedido pedido = PedidoStub.newBuilder().withAllFiels().build();
        
        pedidoProducer.postarPedido(pedido);

        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConfig.PEDIDO_EXCHANGE),
                eq(RabbitMQConfig.ROUTING_KEY_PEDIDO_CRIADO),
                argThat((PedidoCriadoEvent event) -> 
                    event.getId().equals(pedido.getId()) && 
                    event.getEnderecoEntrega().equals(pedido.getEnderecoEntrega())
                )
        );
    }
}
