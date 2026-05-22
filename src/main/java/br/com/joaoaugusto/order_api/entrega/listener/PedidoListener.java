package br.com.joaoaugusto.order_api.entrega.listener;

import br.com.joaoaugusto.order_api.entrega.dto.PedidoEvent;
import br.com.joaoaugusto.order_api.entrega.factory.EntregaFactory;
import br.com.joaoaugusto.order_api.entrega.model.Entrega;
import br.com.joaoaugusto.order_api.entrega.repository.EntregaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoListener.class);

    private final EntregaRepository entregaRepository;

    public PedidoListener(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @RabbitListener(queues = "pedido.criado.queue")
    public void processarPedidoCriado(PedidoEvent pedidoEvent){
        LOGGER.info("Evento de PedidoCriado recebido na fila: {}", pedidoEvent);
        final Entrega entrega = EntregaFactory.entregaFactory(pedidoEvent);
        entregaRepository.save(entrega);
        LOGGER.info("Entrega gerada e salva com sucesso para o Pedido ID: {}", pedidoEvent.getId());
    }
}
