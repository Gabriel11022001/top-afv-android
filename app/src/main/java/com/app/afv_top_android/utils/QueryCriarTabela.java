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
    public static final String TB_CONTATOS = "CREATE TABLE IF NOT EXISTS tb_contatos(" +
            "id INTEGER NOT NULL PRIMARY KEY," +
            "descricao TEXT NOT NULL," +
            "tipo TEXT NOT NULL," +
            "cliente_id INTEGER NOT NULL," +
            "FOREIGN KEY(cliente_id) REFERENCES tb_clientes(id));";
    public static final String TB_TABELAS_PRECO = "CREATE TABLE IF NOT EXISTS tb_tabelas_preco(" +
            "id INTEGER NOT NULL PRIMARY KEY," +
            "descricao TEXT NOT NULL," +
            "status BOOLEAN NOT NULL);";
    public static final String TB_PRODUTOS = "CREATE TABLE IF NOT EXISTS tb_produtos(" +
            "id INTEGER NOT NULL PRIMARY KEY," +
            "descricao TEXT NOT NULL," +
            "status BOOLEAN NOT NULL," +
            "unidades_estoque INTEGER NOT NULL);";
    public static final String TB_TABELA_PRECO_PRODUTO = "CREATE TABLE IF NOT EXISTS tb_tabela_preco_produto(" +
            "id INTEGER NOT NULL PRIMARY KEY," +
            "preco_produto FLOAT NOT NULL," +
            "desconto_produto FLOAT," +
            "produto_id INTEGER NOT NULL," +
            "tabela_preco_id INTEGER NOT NULL," +
            "FOREIGN KEY(produto_id) REFERENCES tb_produtos(id)," +
            "FOREIGN KEY(tabela_preco_id) REFERENCES tb_tabelas_preco(id));";
}
