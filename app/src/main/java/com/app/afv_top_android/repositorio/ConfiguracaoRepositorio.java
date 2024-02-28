package com.app.afv_top_android.repositorio;

import android.content.Context;

import com.app.afv_top_android.model.Configuracao;

import java.util.List;

public class ConfiguracaoRepositorio extends Repositorio<Configuracao> {

    public ConfiguracaoRepositorio(Context contexto) {
        super(contexto);
    }

    @Override
    public int salvar(Configuracao entidade) {
        
        return 0;
    }

    @Override
    public List<Configuracao> listarTodos() {

        return null;
    }

    @Override
    public Configuracao buscarPeloId(int id) {

        return null;
    }
}
