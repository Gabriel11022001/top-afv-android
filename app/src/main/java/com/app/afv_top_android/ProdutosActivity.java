package com.app.afv_top_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.afv_top_android.adapter.ProdutoAdapter;
import com.app.afv_top_android.model.Produto;
import com.app.afv_top_android.repositorio.ProdutoRepositorio;
import com.app.afv_top_android.utils.SpinnerUtils;

import java.util.ArrayList;
import java.util.List;

public class ProdutosActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewProdutos;
    private TextView txtMensagemSemProdutosCadastrados;
    private Spinner spnFiltroProdutos;
    private EditText edtFiltroProdutos;
    private ImageButton btnFiltrar;
    private List<Produto> produtos;
    private ProdutoAdapter produtoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        // mapeando elementos de interface
        recyclerViewProdutos = findViewById(R.id.recycler_produtos);
        txtMensagemSemProdutosCadastrados = findViewById(R.id.txt_mensagem_nao_existem_produtos_cadastrados);
        edtFiltroProdutos = findViewById(R.id.edt_filtro_produtos);
        btnFiltrar = findViewById(R.id.btn_filtrar);
        spnFiltroProdutos = findViewById(R.id.spn_opcoes_filtro_produtos);
        btnFiltrar.setOnClickListener(this);
        // configurando a RecyclerView
        produtos = new ArrayList<>();
        produtoAdapter = new ProdutoAdapter(this.produtos);
        recyclerViewProdutos.setAdapter(produtoAdapter);
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));
        // configurar o spinner do filtro de produtos
        List<String> opcoesFiltroProdutos = new ArrayList<>();
        opcoesFiltroProdutos.add("Descrição".toUpperCase());
        opcoesFiltroProdutos.add("Status".toUpperCase());
        SpinnerUtils<String> spinnerUtilsFiltroProdutos = new SpinnerUtils<>(
                getApplicationContext()
        );
        spinnerUtilsFiltroProdutos.configurarSpinner(this.spnFiltroProdutos, opcoesFiltroProdutos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        buscarProdutos();
    }

    private void buscarProdutos() {

        try {
            ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(getApplicationContext());
            this.produtos = produtoRepositorio.listarTodos();

            if (this.produtos.size() > 0) {
                txtMensagemSemProdutosCadastrados.setVisibility(View.GONE);
                recyclerViewProdutos.setVisibility(View.VISIBLE);
                // configurar adapter
                this.produtoAdapter.setProdutos(this.produtos);
                this.produtoAdapter.notifyDataSetChanged();
            } else {
                txtMensagemSemProdutosCadastrados.setVisibility(View.VISIBLE);
                recyclerViewProdutos.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            Log.e("erro", "Ocorreu o seguinte erro ao tentar-se consultar os produtos: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Ocorreu um erro ao tentar-se consultar os produtos", Toast.LENGTH_LONG).show();
        }

    }

    private void filtrar() {

        try {
            String textoFiltro = this.edtFiltroProdutos.getText().toString();
            String filtro = (String) this.spnFiltroProdutos.getSelectedItem();

            if (textoFiltro.trim().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Informe o texto para consulta!", Toast.LENGTH_SHORT).show();
            } else {
                ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(getApplicationContext());

                if (filtro.equals("Descrição".toUpperCase())) {
                    // filtrar pela descrição
                    this.produtos = produtoRepositorio.buscarProdutosPelaDescricao(textoFiltro.trim());
                } else {
                    // filtrar pelo status

                    if (textoFiltro.trim().equals("Ativo")
                    || textoFiltro.trim().equals("Inativo")) {
                        this.produtos = produtoRepositorio.buscarProdutosPeloStatus(textoFiltro.equals("Ativo"));
                    } else {
                        Toast.makeText(getApplicationContext(), "Informe Ativo ou Inativo para o filtro!", Toast.LENGTH_SHORT)
                                .show();
                    }

                }

                if (this.produtos.size() > 0) {
                    this.txtMensagemSemProdutosCadastrados.setVisibility(View.GONE);
                    this.recyclerViewProdutos.setVisibility(View.VISIBLE);
                    this.produtoAdapter.setProdutos(this.produtos);
                    this.produtoAdapter.notifyDataSetChanged();
                } else {
                    this.txtMensagemSemProdutosCadastrados.setVisibility(View.VISIBLE);
                    this.recyclerViewProdutos.setVisibility(View.GONE);
                }

            }

        } catch (Exception e) {
            Log.e("erro", "Ocorreu o seguinte erro ao tentar-se filtrar os produtos: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Ocorreu um erro ao tentar-se filtrar os produtos!", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_filtrar) {
            filtrar();
        }

    }
}