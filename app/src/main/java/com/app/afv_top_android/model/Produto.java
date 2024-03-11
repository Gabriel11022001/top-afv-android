package com.app.afv_top_android.model;

public class Produto extends Model {

    private int id;
    private String descricao;
    private boolean status;
    private double preco;
    private String descricaoTabelaPreco;
    private double desconto;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return this.id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {

        return this.descricao;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {

        return this.status;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {

        return this.preco;
    }

    public void setDescricaoTabelaPreco(String descricaoTabelaPreco) {
        this.descricaoTabelaPreco = descricaoTabelaPreco;
    }

    public String getDescricaoTabelaPreco() {

        return this.descricaoTabelaPreco;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getDesconto() {

        return this.desconto;
    }
}
