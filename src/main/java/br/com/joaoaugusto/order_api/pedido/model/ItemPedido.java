package br.com.joaoaugusto.order_api.pedido.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemPedido {

    private String nomeProduto;
    private String codigoProduto;

    public ItemPedido() {
    }

    public ItemPedido(String nomeProduto, String codigoProduto) {
        this.nomeProduto = nomeProduto;
        this.codigoProduto = codigoProduto;
    }

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
