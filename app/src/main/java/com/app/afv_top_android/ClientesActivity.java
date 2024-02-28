package com.app.afv_top_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClientesActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btnAdicionarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        btnAdicionarCliente = findViewById(R.id.btn_cadastrar_cliente);
        btnAdicionarCliente.setOnClickListener(this);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_cadastrar_cliente) {
            Intent intentRedirecionarTelaCadastroCliente = new Intent(
                    getApplicationContext(),
                    CadastroClienteActivity.class
            );
            startActivity(intentRedirecionarTelaCadastroCliente);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}