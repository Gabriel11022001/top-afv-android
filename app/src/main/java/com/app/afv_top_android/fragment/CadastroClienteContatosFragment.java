package com.app.afv_top_android.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.afv_top_android.R;
import com.app.afv_top_android.adapter.ContatoAdapter;
import com.app.afv_top_android.listener.IOnEditarListener;
import com.app.afv_top_android.listener.IOnRemoverListener;
import com.app.afv_top_android.model.Contato;
import com.app.afv_top_android.repositorio.ContatoRepositorio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CadastroClienteContatosFragment extends Fragment implements View.OnClickListener {

    private LayoutInflater infladorLayout;
    private Context contexto;
    private int clienteId;
    private FloatingActionButton btnAdicionarContato;
    private RecyclerView recyclerViewContatosCliente;
    private ContatoAdapter contatoAdapter;
    private TextView txtMensagemNenhumContatoAdicionado;
    private FloatingActionButton btnProsseguir;
    private FloatingActionButton btnRetornar;
    private List<Contato> contatosCliente;
    private String[] tiposContato;

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
        // definindo os tipos de contato
        tiposContato = new String[]{
                "Telefone",
                "E-mail"
        };
        // configurando o evento de remover o contato
        IOnRemoverListener iOnRemoverListener = new IOnRemoverListener() {
            @Override
            public void onRemover(int id) {
                removerContato(id);
            }
        };
        // configurando o evento de editar o contato
        IOnEditarListener iOnEditarListener = new IOnEditarListener() {
            @Override
            public void onEditar(int id) {
                editarContato(id);
            }
        };
        // configurando o adapter e a recylerview de contatos
        contatoAdapter = new ContatoAdapter(iOnRemoverListener, iOnEditarListener);
        recyclerViewContatosCliente.setAdapter(contatoAdapter);
        recyclerViewContatosCliente.setLayoutManager(new LinearLayoutManager(contexto));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            Bundle bundleDadosVindosTelaAnterior = getArguments();

            if (bundleDadosVindosTelaAnterior != null) {
                clienteId = bundleDadosVindosTelaAnterior.getInt("id_cliente");
                ContatoRepositorio contatoRepositorio = new ContatoRepositorio(contexto);
                this.contatosCliente = contatoRepositorio.listarTodosContatosCliente(clienteId);

                if (contatosCliente.size() > 0) {
                    System.out.println("O cliente possui contatos!");
                    contatoAdapter.setContatos(this.contatosCliente);
                    contatoAdapter.notifyDataSetChanged();
                    recyclerViewContatosCliente.setVisibility(View.VISIBLE);
                    txtMensagemNenhumContatoAdicionado.setVisibility(View.GONE);
                } else {
                    // apresentar mensagem informando que o cliente não possui contatos adicionados
                    System.out.println("O cliente não possui contatos!");
                    recyclerViewContatosCliente.setVisibility(View.GONE);
                    txtMensagemNenhumContatoAdicionado.setVisibility(View.VISIBLE);
                }

            }

        } catch (Exception e) {
            Log.e("erro", "Ocorreu um erro ao tentar-se buscar os contatos do cliente!");
            Toast.makeText(contexto, "Ocorreu um erro ao tentar-se buscar os contatos!", Toast.LENGTH_LONG)
                    .show();
        }

    }

    private void adicionarContato() {
        // criar alerta com campos para cadastro do contato
        AlertDialog.Builder dialogCadastroContato = new AlertDialog.Builder(contexto);
        View view = infladorLayout.inflate(R.layout.dialog_salvar_contato, null, false);
        EditText edtDescricaoContato = view.findViewById(R.id.edt_descricao_contato);
        Spinner spnTiposContato = view.findViewById(R.id.spn_tipos_contato);
        AppCompatButton btnSalvarContato = view.findViewById(R.id.btn_salvar_contato);
        // programando o adapter do spinner de tipos de contato
        ArrayAdapter<String> adapterSpinnerTiposContato = new ArrayAdapter<>(
                contexto,
                android.R.layout.simple_list_item_1,
                this.tiposContato
        );
        spnTiposContato.setAdapter(adapterSpinnerTiposContato);
        // salvar contato quando o usuário clicar no botão salvar
        btnSalvarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarContato(
                        edtDescricaoContato.getText().toString(),
                        String.valueOf(spnTiposContato.getSelectedItem())
                );
            }
        });
        // fechar o dialog de cadastro de contato
        dialogCadastroContato.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogCadastroContato.setView(view);
        dialogCadastroContato.setCancelable(false);
        dialogCadastroContato.show();
    }

    private void prosseguir() {

        if (contatosCliente.size() == 0) {
            Toast.makeText(contexto, "Adicione no mínimo um contato para o cliente!", Toast.LENGTH_LONG)
                    .show();
        } else {
            // prosseguir para a tela de endereços
        }

    }

    private void retornar() {
        Bundle bundleDadosRetornarTelaCadastroDadosBasicos = new Bundle();
        bundleDadosRetornarTelaCadastroDadosBasicos.putInt("id_cliente", clienteId);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_cadastro_cliente, CadastroClienteDadosBasicosFragment.class, bundleDadosRetornarTelaCadastroDadosBasicos)
                .commit();
    }

    private void salvarContato(String contato, String tipo) {

        try {

            if (contato.isEmpty()) {
                Toast.makeText(contexto, "Informe a descrição do contato!", Toast.LENGTH_LONG).show();
            } else {
                Contato contatoCadastrar = new Contato();
                contatoCadastrar.setClienteId(clienteId);
                contatoCadastrar.setContato(contato);
                contatoCadastrar.setTipo(tipo);
                ContatoRepositorio contatoRepositorio = new ContatoRepositorio(contexto);
                int idContatoCadastrado = contatoRepositorio.salvar(contatoCadastrar);
                Log.i("id_contato", "Id do contato cadastrado: " + idContatoCadastrado);
                Toast.makeText(contexto, "Contato salvo com sucesso!", Toast.LENGTH_LONG).show();
                // atualizar a recyclerview com a listagem de contatos
                this.contatosCliente = contatoRepositorio.listarTodosContatosCliente(clienteId);
                this.contatoAdapter.setContatos(contatosCliente);
                this.contatoAdapter.notifyDataSetChanged();
                this.recyclerViewContatosCliente.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            Log.e("erro", "Ocorreu o seguinte erro ao tentar-se salvar o contato: " + e.getMessage());
            Toast.makeText(contexto, "Erro ao tentar-se salvar o contato!", Toast.LENGTH_LONG).show();
        }

    }

    private void removerContato(int idContato) {

        try {
            ContatoRepositorio contatoRepositorio = new ContatoRepositorio(contexto);
            contatoRepositorio.deletarContato(idContato);
            this.contatosCliente = contatoRepositorio.listarTodosContatosCliente(this.clienteId);
            this.contatoAdapter.setContatos(this.contatosCliente);
            this.contatoAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(contexto, "Ocorreu um erro ao tentar-se remover o contato!", Toast.LENGTH_LONG).show();
        }

    }

    private void editarContato(int idContato) {

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_adicionar_contato) {
            adicionarContato();
        } else if (view.getId() == R.id.btn_prosseguir_tela_adicionar_contato) {
            prosseguir();
        } else if (view.getId() == R.id.btn_retornar_tela_adicionar_contato) {
            retornar();
        }

    }
}