package com.app.afv_top_android.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.afv_top_android.R;
import com.app.afv_top_android.model.Cliente;
import com.app.afv_top_android.repositorio.ClienteRepositorio;
import com.app.afv_top_android.utils.SexoUtils;
import com.app.afv_top_android.utils.SpinnerUtils;

import java.util.List;

public class CadastroClienteDadosBasicosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private int clienteId = 0;
    private Context contexto;
    private LayoutInflater layoutInflater;
    private AppCompatButton btnProsseguir;
    private AppCompatButton btnRetornar;
    private EditText edtNomeCompleto;
    private EditText edtCpf;
    private EditText edtDataNascimento;
    private EditText edtRg;
    private TextView feedbackNomeCompleto;
    private TextView feedbackCpf;
    private TextView feedbackDataNascimento;
    private TextView feedbackRg;
    private Spinner spnSexo;
    private List<String> sexos;

    public CadastroClienteDadosBasicosFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_cliente_dados_basicos, container, false);
        contexto = view.getContext();
        layoutInflater = inflater;
        btnProsseguir = view.findViewById(R.id.btn_prosseguir);
        btnRetornar = view.findViewById(R.id.btn_retornar);
        edtNomeCompleto = view.findViewById(R.id.edt_nome_completo);
        edtCpf = view.findViewById(R.id.edt_cpf);
        spnSexo = view.findViewById(R.id.spn_sexo_cliente);
        edtRg = view.findViewById(R.id.edt_rg);
        edtDataNascimento = view.findViewById(R.id.edt_data_nascimento);
        feedbackNomeCompleto = view.findViewById(R.id.txt_feedback_nome);
        feedbackCpf = view.findViewById(R.id.txt_feedback_cpf);
        feedbackRg = view.findViewById(R.id.txt_feedback_rg);
        feedbackDataNascimento = view.findViewById(R.id.txt_feedback_data_nascimento);
        btnProsseguir.setOnClickListener(this);
        btnRetornar.setOnClickListener(this);
        // obtendo os possíveis sexos
        sexos = SexoUtils.obterSexos();
        // configurando e listando os sexos dos clientes no spinner de sexo
        SpinnerUtils<String> spinnerUtilsSexoCliente = new SpinnerUtils<>(view.getContext());
        spinnerUtilsSexoCliente.configurarSpinner(spnSexo, sexos);
        spnSexo.setOnItemSelectedListener(this);

        return view;
    }

    private boolean validarCamposCadastroClientePrimeiraEtapa() {
        boolean ok = true;
        String nome = edtNomeCompleto.getText().toString();
        String cpf = edtCpf.getText().toString();
        String rg = edtRg.getText().toString();
        String dataNascimentoTexto = edtDataNascimento.getText().toString();
        feedbackNomeCompleto.setVisibility(View.GONE);
        feedbackCpf.setVisibility(View.GONE);
        feedbackDataNascimento.setVisibility(View.GONE);
        feedbackRg.setVisibility(View.GONE);
        feedbackNomeCompleto.setText("");
        feedbackRg.setText("");
        feedbackCpf.setText("");
        feedbackDataNascimento.setText("");

        if (nome.isEmpty()) {
            ok = false;
            feedbackNomeCompleto.setVisibility(View.VISIBLE);
            feedbackNomeCompleto.setText("Informe o nome do cliente");
        }

        if (cpf.isEmpty()) {
            ok = false;
            feedbackCpf.setVisibility(View.VISIBLE);
            feedbackCpf.setText("Informe o cpf do cliente");
        }

        if (rg.isEmpty()) {
            ok = false;
            feedbackRg.setVisibility(View.VISIBLE);
            feedbackRg.setText("Informe o rg do cliente");
        }

        if (dataNascimentoTexto.isEmpty()) {
            ok = false;
            feedbackDataNascimento.setVisibility(View.VISIBLE);
            feedbackDataNascimento.setText("Informe a data de nascimento");
        }

        return ok;
    }

    private int realizarPreCadastroCliente() {
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(contexto);
        Cliente cliente = new Cliente();
        cliente.setNomeCompleto(edtNomeCompleto.getText().toString().trim());
        cliente.setCpf(edtCpf.getText().toString().trim());
        cliente.setRg(edtRg.getText().toString().trim());
        cliente.setStatus(true);
        String sexo = (String) spnSexo.getSelectedItem();
        cliente.setSexo(sexo);

        return clienteRepositorio.salvar(cliente);
    }

    private void prosseguir() {
        View viewAlertaErroCadastrarDadosBasicosCliente = layoutInflater
                .inflate(R.layout.alerta_erro, null);
        TextView txtMensagemAlertaErro = viewAlertaErroCadastrarDadosBasicosCliente.findViewById(R.id.alerta_erro_mensagem);
        AlertDialog.Builder alertaErro = new AlertDialog.Builder(contexto);
        alertaErro.setCancelable(false);
        alertaErro.setTitle("Alerta!");
        alertaErro.setPositiveButton("OK", (dialogInterface, i) -> alertaErro.create().hide());
        alertaErro.setView(viewAlertaErroCadastrarDadosBasicosCliente);

        try {

            if (validarCamposCadastroClientePrimeiraEtapa()) {
                clienteId = realizarPreCadastroCliente();
                Log.i("id_cliente", "Id do cliente cadastrado: " + clienteId);
            }

        } catch (Exception e) {
            Log.e("ERRO", "Ocorreu o seguinte erro: " + e.getMessage());
            txtMensagemAlertaErro.setText("Ocorreu um erro, tente novamente!");
            alertaErro.show();
        }

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_prosseguir) {
            prosseguir();
        } else if (view.getId() == R.id.btn_retornar) {
            View viewAlertaRetornar = layoutInflater.inflate(
                    R.layout.alerta_sair,
                    null
            );
            TextView txtAlertaRetornar = viewAlertaRetornar.findViewById(R.id.txt_mensagem_alerta_sair);
            txtAlertaRetornar.setText("Deseja sair do cadastro de cliente?");
            AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
            alerta.setTitle("Retornar");
            alerta.setCancelable(false);
            alerta.setView(viewAlertaRetornar);
            alerta.setPositiveButton("Sim", (dialogInterface, i) -> {
                Activity activity = getActivity();

                if (activity != null) {
                    activity.finish();
                }

            });
            alerta.setNegativeButton("Não", (dialogInterface, i) -> alerta.create().hide());
            alerta.show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String sexoSelecionado = sexos.get(i);
        System.out.println("Sexo selecionado: " + sexoSelecionado);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}