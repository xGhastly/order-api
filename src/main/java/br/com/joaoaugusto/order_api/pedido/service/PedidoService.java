package br.com.joaoaugusto.order_api.pedido.service;

import br.com.joaoaugusto.order_api.pedido.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.pedido.exception.PedidoNotFoundException;
import org.slf4j.Logger;
import br.com.joaoaugusto.order_api.pedido.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.pedido.factory.PedidoFactory;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import br.com.joaoaugusto.order_api.pedido.producer.PedidoProducer;
import br.com.joaoaugusto.order_api.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;

    private final PedidoProducer pedidoProducer;

    public PedidoService(PedidoRepository pedidoRepository, PedidoProducer pedidoProducer) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProducer = pedidoProducer;
    }

    @Transactional
    public void criarPedido(PedidoRequestDTO pedidoRequestDTO) {

        LOGGER.info("Criando pedido: {}", pedidoRequestDTO);

        final Pedido pedido = PedidoFactory.pedidoFactory(pedidoRequestDTO);


        final Pedido pedidoSalvo = pedidoRepository.save(pedido);

        LOGGER.info("Pedido salvo com sucesso, ID: {}", pedidoSalvo.getId());

        pedidoProducer.postarPedido(pedidoSalvo);
    }


    public List<PedidoResponseDTO> listarPedidos() {
        LOGGER.info("Listando todos os pedidos");
        return pedidoRepository.findAll().stream()
                .map(PedidoFactory::pedidoResponseDTOFactory)
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO listarPedidoPorId(Long id) {
        return PedidoFactory.pedidoResponseDTOFactory(pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id)));
    }
}
