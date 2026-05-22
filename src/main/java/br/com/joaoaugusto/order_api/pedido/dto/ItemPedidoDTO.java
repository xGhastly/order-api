package br.com.joaoaugusto.order_api.pedido.dto;


import jakarta.validation.constraints.NotBlank;

public class ItemPedidoDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    private String nomeProduto;

    @NotBlank(message = "Código do produto é obrigatório")
    private String codigoProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
}
