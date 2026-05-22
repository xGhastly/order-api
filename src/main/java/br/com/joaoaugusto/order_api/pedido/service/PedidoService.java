package br.com.joaoaugusto.order_api.pedido.services;

import br.com.joaoaugusto.order_api.pedido.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.pedido.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.pedido.factory.PedidoFactory;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import br.com.joaoaugusto.order_api.pedido.producer.PedidoProducer;
import br.com.joaoaugusto.order_api.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final PedidoProducer pedidoProducer;

    public PedidoService(PedidoRepository pedidoRepository, PedidoProducer pedidoProducer) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProducer = pedidoProducer;
    }

    @Transactional
    public Pedido criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        final Pedido pedido = PedidoFactory.pedidoFactory(pedidoRequestDTO);

        final Pedido pedidoSalvo = pedidoRepository.save(pedido);

        pedidoProducer.postarPedido(pedidoSalvo);

        return pedidoSalvo;
    }


    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoFactory::pedidoResponseDTOFactory)
                .collect(Collectors.toList());
    }
}
