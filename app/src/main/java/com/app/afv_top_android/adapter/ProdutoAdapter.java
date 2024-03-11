package com.app.afv_top_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.afv_top_android.R;
import com.app.afv_top_android.model.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<Produto> produtos;
    private LayoutInflater layoutInflater;
    private Context contexto;

    public ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.contexto = parent.getContext();
        this.layoutInflater = LayoutInflater.from(this.contexto);
        View view = this.layoutInflater.inflate(R.layout.produto_adapter, parent, false);

        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = this.produtos.get(position);
        holder.txtDescricaoProduto.setText(produto.getDescricao().toUpperCase());
        holder.txtDescricaoTabelaPreco.setText(produto.getDescricaoTabelaPreco().toUpperCase());
        holder.txtPrecoProduto.setText(String.valueOf(produto.getPreco()));
    }

    @Override
    public int getItemCount() {

        return this.produtos.size();
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDescricaoProduto;
        public TextView txtDescricaoTabelaPreco;
        public TextView txtPrecoProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtDescricaoProduto = itemView.findViewById(R.id.txt_descricao_produto);
            this.txtDescricaoTabelaPreco = itemView.findViewById(R.id.txt_descricao_tabela_preco);
            this.txtPrecoProduto = itemView.findViewById(R.id.preco_produto);
        }
    }
}
