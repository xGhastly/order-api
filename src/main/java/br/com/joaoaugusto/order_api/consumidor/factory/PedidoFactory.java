package br.com.joaoaugusto.order_api.consumidor.factory;

import br.com.joaoaugusto.order_api.consumidor.dto.ItemPedidoDTO;
import br.com.joaoaugusto.order_api.consumidor.dto.PedidoRequestDTO;
import br.com.joaoaugusto.order_api.consumidor.dto.PedidoResponseDTO;
import br.com.joaoaugusto.order_api.consumidor.model.ItemPedido;
import br.com.joaoaugusto.order_api.consumidor.model.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoFactory {

    public static Pedido pedidoFactory(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCodigoCliente(dto.getCodigoCliente());
        pedido.setValorTotal(dto.getValorTotal());
        pedido.setEnderecoEntrega(dto.getEnderecoEntrega());

        if (dto.getItens() != null) {
            List<ItemPedido> itens = dto.getItens().stream()
                    .map(itemDto -> {
                        ItemPedido item = new ItemPedido();
                        item.setNomeProduto(itemDto.getNomeProduto());
                        item.setCodigoProduto(itemDto.getCodigoProduto());
                        return item;
                    }).toList();
            pedido.setItens(new java.util.ArrayList<>(itens));
        }

        return pedido;
    }

    public static PedidoResponseDTO pedidoResponseDTOFactory(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setCodigoCliente(pedido.getCodigoCliente());
        dto.setValorTotal(pedido.getValorTotal());
        dto.setEnderecoEntrega(pedido.getEnderecoEntrega());

        if (pedido.getItens() != null) {
            List<ItemPedidoDTO> itensDto = pedido.getItens().stream()
                    .map(item -> {
                        ItemPedidoDTO itemDto = new ItemPedidoDTO();
                        itemDto.setNomeProduto(item.getNomeProduto());
                        itemDto.setCodigoProduto(item.getCodigoProduto());
                        return itemDto;
                    }).collect(Collectors.toList());
            dto.setItens(itensDto);
        }

        return dto;
    }
}
