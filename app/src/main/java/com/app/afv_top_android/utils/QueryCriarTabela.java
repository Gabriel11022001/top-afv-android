package com.app.afv_top_android.utils;

public class QueryCriarTabela {

    public static final String TB_CLIENTES = "CREATE TABLE IF NOT EXISTS tb_clientes(" +
            "id INTEGER NOT NULL PRIMARY KEY," +
            "nome_completo TEXT NOT NULL," +
            "cpf TEXT NOT NULL," +
            "rg TEXT NOT NULL," +
            "data_nascimento DATE NOT NULL," +
            "sexo TEXT NOT NULL," +
            "status BOOLEAN NOT NULL);";
    public static final String TB_CONTATOS = "CREATE TABLE IF NOT EXISTS tb_clientes(" +
            "id INTEGER NOT NULL PRIMARY KEY," +
            "descricao TEXT NOT NULL," +
            "tipo TEXT NOT NULL," +
            "cliente_id INTEGER NOT NULL," +
            "FOREIGN KEY(cliente_id) REFERENCES tb_clientes(id));";
}
