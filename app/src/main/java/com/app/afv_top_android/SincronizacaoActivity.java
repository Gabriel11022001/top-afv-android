package com.app.afv_top_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.afv_top_android.repositorio.BancoDados;

public class SincronizacaoActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private CheckBox checkBoxSincConfiguracoes;
    private CheckBox checkBoxSincClientes;
    private CheckBox checkBoxSincCategoriasProdutos;
    private CheckBox checkBoxSincProdutos;
    private CheckBox checkBoxSincFormasPagamento;
    private CheckBox checkBoxSincVendas;
    private ProgressBar loaderSincConfiguracoes;
    private ProgressBar loaderSincClientes;
    private ProgressBar loaderSincCategoriasProdutos;
    private ProgressBar loaderSincProdutos;
    private ProgressBar loaderSincFormasPagamento;
    private ProgressBar loaderSincVendas;
    private AppCompatButton btnSincronizar;
    private AppCompatButton btnProsseguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizacao);
        checkBoxSincConfiguracoes = findViewById(R.id.checkbox_sinc_configuracoes);
        checkBoxSincCategoriasProdutos = findViewById(R.id.checkbox_sinc_categorias);
        checkBoxSincClientes = findViewById(R.id.checkbox_sinc_clientes);
        checkBoxSincProdutos = findViewById(R.id.checkbox_sinc_produtos);
        checkBoxSincFormasPagamento = findViewById(R.id.checkbox_sinc_formas_pagamento);
        checkBoxSincVendas = findViewById(R.id.checkbox_sinc_vendas);
        loaderSincConfiguracoes = findViewById(R.id.loader_sinc_configuracoes);
        loaderSincClientes = findViewById(R.id.loader_sinc_clientes);
        loaderSincCategoriasProdutos = findViewById(R.id.loader_sinc_categorias);
        loaderSincProdutos = findViewById(R.id.loader_sinc_produtos);
        loaderSincFormasPagamento = findViewById(R.id.loader_sinc_formas_pagamento);
        loaderSincVendas = findViewById(R.id.loader_sinc_vendas);
        btnSincronizar = findViewById(R.id.btn_sincronizar);
        btnProsseguir = findViewById(R.id.btn_prosseguir);
        checkBoxSincConfiguracoes.setOnCheckedChangeListener(this);
        checkBoxSincClientes.setOnCheckedChangeListener(this);
        checkBoxSincCategoriasProdutos.setOnCheckedChangeListener(this);
        checkBoxSincFormasPagamento.setOnCheckedChangeListener(this);
        checkBoxSincProdutos.setOnCheckedChangeListener(this);
        checkBoxSincVendas.setOnCheckedChangeListener(this);
        btnSincronizar.setOnClickListener(this);
        btnProsseguir.setOnClickListener(this);
        definirCheckBoxSincObrigatorios();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (compoundButton.getId() == R.id.checkbox_sinc_configuracoes
        || compoundButton.getId() == R.id.checkbox_sinc_produtos
        || compoundButton.getId() == R.id.checkbox_sinc_categorias
        || compoundButton.getId() == R.id.checkbox_sinc_clientes
        || compoundButton.getId() == R.id.checkbox_sinc_formas_pagamento) {
            compoundButton.setChecked(true);
        }

    }

    private void definirCheckBoxSincObrigatorios() {
        checkBoxSincConfiguracoes.setChecked(true);
        checkBoxSincClientes.setChecked(true);
        checkBoxSincCategoriasProdutos.setChecked(true);
        checkBoxSincProdutos.setChecked(true);
        checkBoxSincFormasPagamento.setChecked(true);
    }

    private void esconderTodosCheckBox() {
        checkBoxSincConfiguracoes.setVisibility(View.GONE);
        checkBoxSincClientes.setVisibility(View.GONE);
        checkBoxSincCategoriasProdutos.setVisibility(View.GONE);
        checkBoxSincProdutos.setVisibility(View.GONE);
        checkBoxSincFormasPagamento.setVisibility(View.GONE);
        checkBoxSincVendas.setVisibility(View.GONE);
    }

    private void apresentarTodosCheckBox() {
        checkBoxSincConfiguracoes.setVisibility(View.VISIBLE);
        checkBoxSincClientes.setVisibility(View.VISIBLE);
        checkBoxSincCategoriasProdutos.setVisibility(View.VISIBLE);
        checkBoxSincProdutos.setVisibility(View.VISIBLE);
        checkBoxSincFormasPagamento.setVisibility(View.VISIBLE);
        checkBoxSincVendas.setVisibility(View.VISIBLE);
    }

    private void apresentarTodosLoadersObrigatorios() {
        loaderSincConfiguracoes.setVisibility(View.VISIBLE);
        loaderSincClientes.setVisibility(View.VISIBLE);
        loaderSincCategoriasProdutos.setVisibility(View.VISIBLE);
        loaderSincProdutos.setVisibility(View.VISIBLE);
        loaderSincFormasPagamento.setVisibility(View.VISIBLE);
    }

    private void esconderTodosLoaders() {
        loaderSincConfiguracoes.setVisibility(View.GONE);
        loaderSincClientes.setVisibility(View.GONE);
        loaderSincCategoriasProdutos.setVisibility(View.GONE);
        loaderSincProdutos.setVisibility(View.GONE);
        loaderSincFormasPagamento.setVisibility(View.GONE);
        loaderSincVendas.setVisibility(View.GONE);
    }

    private boolean sincronizarConfiguracoes() {

        return true;
    }

    private boolean sincronizarClientes() {

        return true;
    }

    private boolean sincronizarCategoriasProdutos() {

        return true;
    }

    private boolean sincronizarProdutos() {

        return true;
    }

    private boolean sincronizarFormasPagamento() {

        return true;
    }

    private boolean sincronizarVendas() {

        return true;
    }

    private void criarBancoDados() {

        try {
            Log.i("db", "Criando o banco de dados...");
            BancoDados bancoDados = new BancoDados(getApplicationContext());
            SQLiteDatabase conexaoDb = bancoDados.getWritableDatabase();
            bancoDados.onCreate(conexaoDb);
            Log.i("db", "Banco de dados criado com sucesso!");
        } catch (Exception e) {
            Log.e("erro", "Ocorreu um erro ao tentar-se criar o banco de dados: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Ocorreu um erro ao tentar-se criar o banco de dados!", Toast.LENGTH_LONG)
                    .show();
            finishAffinity();
        }

    }

    private void sincronizar() {
        criarBancoDados();
        btnSincronizar.setVisibility(View.GONE);
        esconderTodosCheckBox();
        apresentarTodosLoadersObrigatorios();

        if (checkBoxSincVendas.isChecked()) {
            loaderSincVendas.setVisibility(View.VISIBLE);
        }

        boolean okSincronizacao = true;
    }

    private void prosseguir() {

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_sincronizar) {
            sincronizar();
        } else if (view.getId() == R.id.btn_prosseguir) {
            prosseguir();
        }

    }
}