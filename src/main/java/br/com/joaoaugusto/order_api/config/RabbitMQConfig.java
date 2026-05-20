package br.com.joaoaugusto.order_api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_PEDIDO_CRIADO = "pedido.criado.queue";
    public static final String PEDIDO_EXCHANGE = "pedido.exchange";
    public static final String ROUTING_KEY_PEDIDO_CRIADO = "pedido.criado.routingKey";

    @Bean
    public Queue filaPedidoCriado() {
        return new Queue(FILA_PEDIDO_CRIADO,true);
    }

    @Bean
    public DirectExchange exchangePedido() {
        return new DirectExchange(PEDIDO_EXCHANGE);
    }

    @Bean
    public Binding bindingPedidoCraido(Queue filaPedidoCriado, DirectExchange exchangePedido) {
        return BindingBuilder.bind(filaPedidoCriado).to(exchangePedido).with(ROUTING_KEY_PEDIDO_CRIADO);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

}
