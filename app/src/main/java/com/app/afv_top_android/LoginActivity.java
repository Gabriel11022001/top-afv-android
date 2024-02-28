package com.app.afv_top_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText edtEmail;
    private TextInputEditText edtSenha;
    private TextView txtFeedbackEmail;
    private TextView txtFeedbackSenha;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edt_email);
        edtSenha = findViewById(R.id.edt_senha);
        txtFeedbackEmail = findViewById(R.id.txt_feedback_email);
        txtFeedbackSenha = findViewById(R.id.txt_feedback_senha);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    private boolean validarCamposLogin() {
        boolean ok = true;
        txtFeedbackEmail.setVisibility(View.GONE);
        txtFeedbackSenha.setVisibility(View.GONE);
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        if (email.isEmpty()) {
            ok = false;
            txtFeedbackEmail.setText("Informe o e-mail!");
            txtFeedbackEmail.setVisibility(View.VISIBLE);
        }

        if (senha.isEmpty()) {
            ok = false;
            txtFeedbackSenha.setText("Informe a senha!");
            txtFeedbackSenha.setVisibility(View.VISIBLE);
        }

        return ok;
    }

    private void realizarLogin() {

        if (validarCamposLogin()) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    MainActivity.class
            );
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_login) {
            realizarLogin();
        }

    }
}