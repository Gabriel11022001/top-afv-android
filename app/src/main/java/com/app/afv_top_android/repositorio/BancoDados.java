package com.app.afv_top_android.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.afv_top_android.utils.QueryCriarTabela;

public class BancoDados extends SQLiteOpenHelper {

    private static final String NOME = "db_top_vendas";
    private static final int VERSAO = 1;

    public BancoDados(Context contexto) {
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // criando a tabela tb_clientes
        Log.i("tb_clientes", "Iniciando a criação da tabela tb_clientes...");
        sqLiteDatabase.execSQL(QueryCriarTabela.TB_CLIENTES);
        Log.i("tb_clientes", "Tabela tb_clientes criada com sucesso!");
        // criando a tabela tb_contatos
        Log.i("tb_contatos", "Iniciando a criação da tabela tb_contatos...");
        sqLiteDatabase.execSQL(QueryCriarTabela.TB_CONTATOS);
        Log.i("tb_contatos", "Tabela tb_contatos criada com sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
