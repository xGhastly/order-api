package br.com.joaoaugusto.order_api.consumidor.services;

import br.com.joaoaugusto.order_api.consumidor.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.consumidor.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.consumidor.factory.PedidoFactory;
import br.com.joaoaugusto.order_api.consumidor.model.Pedido;
import br.com.joaoaugusto.order_api.consumidor.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        final Pedido pedido = PedidoFactory.pedidoFactory(pedidoRequestDTO);

        return pedidoRepository.save(pedido);
    }

    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoFactory::pedidoResponseDTOFactory)
                .collect(Collectors.toList());
    }
}
