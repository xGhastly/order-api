package br.com.joaoaugusto.order_api.consumidor.controller;

import br.com.joaoaugusto.order_api.consumidor.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.consumidor.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.consumidor.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        pedidoService.criarPedido(pedidoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        final List<PedidoResponseDTO> pedidoResponseDTOList = pedidoService.listarPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTOList);
    }
}