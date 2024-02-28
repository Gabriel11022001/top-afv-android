package com.app.afv_top_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.app.afv_top_android.fragment.CadastroClienteDadosBasicosFragment;

public class CadastroClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        // inicializando a activity com o fragment de cadastro de dados básicos do cliente
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_cadastro_cliente, new CadastroClienteDadosBasicosFragment())
                .commit();
    }

}