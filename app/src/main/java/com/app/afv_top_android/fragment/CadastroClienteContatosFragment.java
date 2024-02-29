package com.app.afv_top_android.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.afv_top_android.R;
import com.app.afv_top_android.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class CadastroClienteContatosFragment extends Fragment implements View.OnClickListener {

    private LayoutInflater infladorLayout;
    private Context contexto;
    private int clienteId;
    private Button btnAdicionarContato;
    private RecyclerView recyclerViewContatosCliente;
    private TextView txtMensagemNenhumContatoAdicionado;
    private AppCompatButton btnProsseguir;
    private AppCompatButton btnRetornar;
    private List<Contato> contatosCliente;

    public CadastroClienteContatosFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_cliente_contatos, container, false);
        infladorLayout = inflater;
        contexto = view.getContext();
        btnAdicionarContato = view.findViewById(R.id.btn_adicionar_contato);
        recyclerViewContatosCliente = view.findViewById(R.id.recyler_contatos);
        txtMensagemNenhumContatoAdicionado = view.findViewById(R.id.txt_msg_nenhum_contato_adicionado);
        btnProsseguir = view.findViewById(R.id.btn_prosseguir_tela_adicionar_contato);
        btnRetornar = view.findViewById(R.id.btn_retornar_tela_adicionar_contato);
        btnAdicionarContato.setOnClickListener(this);
        btnProsseguir.setOnClickListener(this);
        btnRetornar.setOnClickListener(this);
        contatosCliente = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundleDadosVindosTelaAnterior = getArguments();

        if (bundleDadosVindosTelaAnterior != null) {
            clienteId = bundleDadosVindosTelaAnterior.getInt("id_cliente");
        }

    }

    private void adicionarContato() {
        contatosCliente.add(new Contato());
    }

    private void prosseguir() {

        if (contatosCliente.size() == 0) {
            Toast.makeText(contexto, "Adicione no mínimo um contato para o cliente!", Toast.LENGTH_LONG)
                    .show();
        } else {
            salvarContatosAdicionados();
        }

    }

    private void retornar() {

        if (contatosCliente.size() > 0) {
            salvarContatosAdicionados();
        }

        Bundle bundleDadosRetornarTelaCadastroDadosBasicos = new Bundle();
        bundleDadosRetornarTelaCadastroDadosBasicos.putInt("id_cliente", clienteId);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_cadastro_cliente, CadastroClienteDadosBasicosFragment.class, bundleDadosRetornarTelaCadastroDadosBasicos)
                .commit();
    }

    private void salvarContatosAdicionados() {

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_adicionar_contato) {
            // adicionar contato na recyclerview
            adicionarContato();
        } else if (view.getId() == R.id.btn_prosseguir_tela_adicionar_contato) {
            prosseguir();
        } else if (view.getId() == R.id.btn_retornar_tela_adicionar_contato) {
            retornar();
        }

    }
}