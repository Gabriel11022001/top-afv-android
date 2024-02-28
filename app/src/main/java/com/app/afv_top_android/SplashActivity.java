package com.app.afv_top_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private final int TEMPO_DELAY_REDIRECIONAR_USUARIO = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // esconder actionbar
        esconderActionBar();
        // redirecionar usuário para a tela de login após um certo tempo
        redirecionarUsuarioTelaLogin();
    }

    private void esconderActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

    }

    private void redirecionarUsuarioTelaLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentRedirecionarUsuario = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentRedirecionarUsuario);
                finish();
            }
        }, TEMPO_DELAY_REDIRECIONAR_USUARIO);
    }
}