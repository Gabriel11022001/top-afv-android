package com.app.afv_top_android.model;

public class Contato extends Model {

    private int id;
    private String contato;
    private String tipo;
    private int clienteId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return this.id;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getContato() {

        return this.contato;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {

        return this.tipo;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getClienteId() {

        return this.clienteId;
    }
}
