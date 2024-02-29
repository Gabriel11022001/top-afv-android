package com.app.afv_top_android.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.app.afv_top_android.model.Cliente;

import java.util.ArrayList;
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
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = this.conexaoBancoDados.rawQuery(
                "SELECT * FROM tb_clientes;",
                new String[]{}
        );

        if (cursor != null) {

            while (cursor.moveToNext()) {
                Cliente cliente = new Cliente();
                cliente.setId(cursor.getInt(cursor.getColumnIndex("id")));
                cliente.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nome_completo")));
                cliente.setStatus(cursor.getInt(cursor.getColumnIndex("status")) == 1 ? true : false);
                cliente.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                cliente.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                clientes.add(cliente);
            }

        }

        return clientes;
    }

    @Override
    public Cliente buscarPeloId(int id) {
        Cliente cliente = null;
        Cursor cursor = this.conexaoBancoDados.rawQuery("SELECT * FROM tb_clientes WHERE id = ?;", new String[]{ String.valueOf(id) });

        if (cursor != null) {

            if (cursor.moveToNext()) {
                cliente = new Cliente();
                cliente.setId(cursor.getInt(cursor.getColumnIndex("id")));
                cliente.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nome_completo")));
                cliente.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
                cliente.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                cliente.setRg(cursor.getString(cursor.getColumnIndex("rg")));
            }

        }

        return cliente;
    }

    public void deletarCliente(int idCliente) {
        this.conexaoBancoDados.delete("tb_clientes", "id = ?", new String[]{ String.valueOf(idCliente) });
    }
}
