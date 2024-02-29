package com.app.afv_top_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.afv_top_android.adapter.ClienteAdapter;
import com.app.afv_top_android.model.Cliente;
import com.app.afv_top_android.repositorio.ClienteRepositorio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btnAdicionarCliente;
    private LayoutInflater layoutInflater;
    private List<Cliente> clientes;
    private ClienteAdapter clienteAdapter;
    private RecyclerView recyclerViewClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        btnAdicionarCliente = findViewById(R.id.btn_cadastrar_cliente);
        layoutInflater = getLayoutInflater();
        recyclerViewClientes = findViewById(R.id.recycler_clientes);
        btnAdicionarCliente.setOnClickListener(this);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        clientes = new ArrayList<>();
        clienteAdapter = new ClienteAdapter(layoutInflater);
        recyclerViewClientes.setAdapter(clienteAdapter);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        buscarClientes();
    }

    private void buscarClientes() {

        try {
            ClienteRepositorio clienteRepositorio = new ClienteRepositorio(getApplicationContext());
            clientes = clienteRepositorio.listarTodos();

            if (clientes.size() == 0) {
                findViewById(R.id.txt_mensagem_nao_existem_clientes_cadastrados)
                        .setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.txt_mensagem_nao_existem_clientes_cadastrados)
                        .setVisibility(View.GONE);
            }

            clienteAdapter.setClientes(clientes);
            clienteAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ocorreu um erro ao tentar-se consultar os clientes!", Toast.LENGTH_LONG).show();
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