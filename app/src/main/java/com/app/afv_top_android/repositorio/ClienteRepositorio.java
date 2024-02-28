package com.app.afv_top_android.repositorio;

import android.content.ContentValues;
import android.content.Context;

import com.app.afv_top_android.model.Cliente;

import java.util.List;

public class ClienteRepositorio extends Repositorio<Cliente> {

    public ClienteRepositorio(Context contexto) {
        super(contexto);
    }

    @Override
    public int salvar(Cliente entidade) {
        ContentValues contentValuesCadastrarCliente = new ContentValues();
        contentValuesCadastrarCliente.put("nome_completo", entidade.getNomeCompleto());
        contentValuesCadastrarCliente.put("cpf", entidade.getCpf());
        contentValuesCadastrarCliente.put("rg", entidade.getRg());
        contentValuesCadastrarCliente.put("status", entidade.getStatus());
        contentValuesCadastrarCliente.put("data_nascimento", "2001-01-11");
        contentValuesCadastrarCliente.put("sexo", entidade.getSexo());

        return (int) super.conexaoBancoDados.insertOrThrow("tb_clientes", null, contentValuesCadastrarCliente);
    }

    @Override
    public List<Cliente> listarTodos() {

        return null;
    }

    @Override
    public Cliente buscarPeloId(int id) {

        return null;
    }
}
