package br.com.joaoaugusto.order_api.pedido.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public class PedidoRequestDTO {

    @NotBlank(message = "Código do cliente é obrigatório")
    private String codigoCliente;

    @NotNull(message = "Valor total do pedido é obrigatório")
    @Positive(message = "Valor total do pedido deve ser maior que zero")
    private BigDecimal valorTotal;

    @NotBlank(message = "Endereço de entrega é obrigatório")
    private String enderecoEntrega;

    @NotEmpty(message = "Lista de itens do pedido não pode estar vazia")
    private List<ItemPedidoDTO> itens;

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}
