package br.com.joaoaugusto.order_api.pedido.producer;

import br.com.joaoaugusto.order_api.config.RabbitMQConfig;
import br.com.joaoaugusto.order_api.pedido.dto.event.PedidoCriadoEvent;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void postarPedido(Pedido pedido) {
        LOGGER.info("Enviando evento de PedidoCriado para o RabbitMQ, Pedido ID: {}", pedido.getId());
        PedidoCriadoEvent pedidoCriadoEvent = new PedidoCriadoEvent(pedido.getId(), pedido.getEnderecoEntrega());
        rabbitTemplate.convertAndSend(RabbitMQConfig.PEDIDO_EXCHANGE, RabbitMQConfig.ROUTING_KEY_PEDIDO_CRIADO, pedidoCriadoEvent);
    }
}
