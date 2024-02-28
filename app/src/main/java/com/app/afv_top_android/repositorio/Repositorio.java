package com.app.afv_top_android.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class Repositorio<T> {

    private BancoDados bancoDados;
    protected SQLiteDatabase conexaoBancoDados;

    public Repositorio(Context contexto) {
        this.bancoDados = new BancoDados(contexto);
        this.conexaoBancoDados = this.bancoDados.getWritableDatabase();
    }

    public abstract int salvar(T entidade);

    public abstract List<T> listarTodos();

    public abstract T buscarPeloId(int id);
}
