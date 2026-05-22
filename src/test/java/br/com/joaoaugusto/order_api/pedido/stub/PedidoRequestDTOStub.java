package br.com.joaoaugusto.order_api.pedido.stub;

import br.com.joaoaugusto.order_api.pedido.dto.ItemPedidoDTO;
import br.com.joaoaugusto.order_api.pedido.dto.PedidoRequestDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class PedidoRequestDTOStub {

    private String codigoCliente;
    private BigDecimal valorTotal;
    private String enderecoEntrega;
    private List<ItemPedidoDTO> itens;

    public static PedidoRequestDTOStub newBuilder() {
        return new PedidoRequestDTOStub();
    }

    public PedidoRequestDTOStub withAllFiels() {
        this.codigoCliente = "5123";
        this.valorTotal = BigDecimal.valueOf(523.23);
        this.enderecoEntrega = "Av Juazeiro, 212";
        ItemPedidoDTO item = new ItemPedidoDTO();
        item.setNomeProduto("TV");
        item.setCodigoProduto("6215");
        this.itens = new ArrayList<>(List.of(item));
        return this;
    }

    public PedidoRequestDTOStub withCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
        return this;
    }

    public PedidoRequestDTOStub withValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public PedidoRequestDTOStub withEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
        return this;
    }

    public PedidoRequestDTOStub withItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
        return this;
    }

    public PedidoRequestDTO build() {
        PedidoRequestDTO dto = new PedidoRequestDTO();
        dto.setCodigoCliente(this.codigoCliente);
        dto.setValorTotal(this.valorTotal);
        dto.setEnderecoEntrega(this.enderecoEntrega);
        dto.setItens(this.itens);
        return dto;
    }
}
