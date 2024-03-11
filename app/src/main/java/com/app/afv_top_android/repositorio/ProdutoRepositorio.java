package com.app.afv_top_android.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.app.afv_top_android.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorio extends Repositorio<Produto> {

    public ProdutoRepositorio(Context contexto) {
        super(contexto);
    }

    @Override
    public int salvar(Produto entidade) {

        return 0;
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = super.conexaoBancoDados.rawQuery(
                "SELECT tb_produtos.id AS id_produto, tb_produtos.descricao AS descricao_produto, " +
                        "tb_produtos.status AS status_produto, tb_produtos.unidades_estoque, " +
                        "tb_tabelas_preco.descricao AS descricao_tabela_preco, tb_tabela_preco_produto.preco_produto AS preco, " +
                        "tb_tabela_preco_produto.desconto_produto AS desconto " +
                        "FROM tb_produtos, tb_tabela_preco_produto, tb_tabelas_preco " +
                        "WHERE tb_produtos.id = tb_tabela_preco_produto.produto_id " +
                        "AND tb_tabelas_preco.id = tb_tabela_preco_produto.tabela_preco_id " +
                        "ORDER BY descricao_produto ASC;",
                new String[]{}
        );

        if (cursor != null) {

            while (cursor.moveToNext()) {
                Produto produto = new Produto();
                produto.setId(cursor.getInt(cursor.getColumnIndex("id_produto")));
                produto.setDescricao(cursor.getString(cursor.getColumnIndex("descricao_produto")));
                produto.setStatus(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("status_produto"))));
                produto.setDescricaoTabelaPreco(cursor.getString(cursor.getColumnIndex("descricao_tabela_preco")));
                produto.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
                produto.setDesconto(cursor.getDouble(cursor.getColumnIndex("desconto")));
                produtos.add(produto);
            }

        }

        return produtos;
    }

    @Override
    public Produto buscarPeloId(int id) {

        return null;
    }

    public List<Produto> buscarProdutosPelaDescricao(String descricao) {
        List<Produto> produtos = new ArrayList<>();

        return produtos;
    }

    public List<Produto> buscarProdutosPeloStatus(boolean status) {
        List<Produto> produtos = new ArrayList<>();

        return produtos;
    }
}
