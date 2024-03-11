package com.app.afv_top_android.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.app.afv_top_android.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoRepositorio extends Repositorio<Contato> {

    public ContatoRepositorio(Context contexto) {
        super(contexto);
    }

    @Override
    public int salvar(Contato entidade) {
        ContentValues contentValuesCadatroContato = new ContentValues();
        contentValuesCadatroContato.put("cliente_id", entidade.getClienteId());
        contentValuesCadatroContato.put("tipo", entidade.getTipo());
        contentValuesCadatroContato.put("descricao", entidade.getContato());

        return (int) super.conexaoBancoDados.insertOrThrow("tb_contatos", null, contentValuesCadatroContato);
    }

    @Override
    public List<Contato> listarTodos() {

        return null;
    }

    @Override
    public Contato buscarPeloId(int id) {
        return null;
    }

    public List<Contato> listarTodosContatosCliente(int idCliente) {
        Log.i("teste", "Id do cliente na consulta: " + idCliente);
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = super.conexaoBancoDados.rawQuery(
                "SELECT * FROM tb_contatos;",
                new String[]{}
        );

        if (cursor != null) {

            while (cursor.moveToNext()) {
                Contato contato = new Contato();
                contato.setId(cursor.getInt(cursor.getColumnIndex("id")));
                contato.setContato(cursor.getString(cursor.getColumnIndex("descricao")));
                contato.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                contato.setClienteId(cursor.getInt(cursor.getColumnIndex("cliente_id")));
                contatos.add(contato);
            }

        }

        return contatos;
    }

    public void deletarContato(int idContato) {
        super.conexaoBancoDados.delete("tb_contatos", "id = ?", new String[]{ String.valueOf(idContato) });
    }

    public void deletarTodosContatosClientes(int idCliente) {
        super.conexaoBancoDados.delete("tb_contatos", "cliente_id = ?", new String[]{ String.valueOf(idCliente) });
    }
}
