package br.com.joaoaugusto.order_api.pedido.controller;

import br.com.joaoaugusto.order_api.pedido.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.pedido.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import br.com.joaoaugusto.order_api.pedido.service.PedidoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        LOGGER.info("Recebida requisição para criar pedido: {}", pedidoRequestDTO);
        pedidoService.criarPedido(pedidoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        LOGGER.info("Recebida requisição para listar pedidos");
        final List<PedidoResponseDTO> pedidoResponseDTOList = pedidoService.listarPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> listarPedidoPorId(@PathVariable Long id) {
        LOGGER.info("Recebida requisição para listar o pedido para o id: {}", id);
        final PedidoResponseDTO pedidoResponseDTO = pedidoService.listarPedidoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTO);
    }
}