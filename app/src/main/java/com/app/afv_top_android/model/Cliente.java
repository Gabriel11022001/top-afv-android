package com.app.afv_top_android.model;

public class Cliente extends Model {

    private int id;
    private String nomeCompleto;
    private String cpf;
    private String rg;
    private boolean status;
    private String sexo;

    public Cliente() { }

    public Cliente(int id, String nome, String cpf, String rg, boolean status) {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return this.id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto() {

        return this.nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {

        return this.cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRg() {

        return this.rg;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {

        return this.status;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {

        return this.sexo;
    }
}
