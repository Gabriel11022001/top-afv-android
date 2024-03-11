package com.app.afv_top_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout cardClientes;
    private LinearLayout cardProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardClientes = findViewById(R.id.card_redirecionar_tela_clientes);
        cardProdutos = findViewById(R.id.card_redirecionar_tela_produtos);
        cardClientes.setOnClickListener(this);
        cardProdutos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        if (view.getId() == R.id.card_redirecionar_tela_clientes) {
            intent = new Intent(getApplicationContext(), ClientesActivity.class);
        } else if (view.getId() == R.id.card_redirecionar_tela_produtos) {
            intent = new Intent(getApplicationContext(), ProdutosActivity.class);
        }

        startActivity(intent);
    }
}