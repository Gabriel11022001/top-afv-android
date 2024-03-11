package com.app.afv_top_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.afv_top_android.R;
import com.app.afv_top_android.listener.IOnEditarListener;
import com.app.afv_top_android.listener.IOnRemoverListener;
import com.app.afv_top_android.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private Context contexto;
    private LayoutInflater layoutInflater;
    private List<Contato> contatos;
    private IOnRemoverListener iOnRemoverListener;
    private IOnEditarListener iOnEditarListener;

    public ContatoAdapter(IOnRemoverListener iOnRemoverListener, IOnEditarListener iOnEditarListener) {
        this.contatos = new ArrayList<>();
        this.iOnRemoverListener = iOnRemoverListener;
        this.iOnEditarListener = iOnEditarListener;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.contexto = parent.getContext();
        this.layoutInflater = LayoutInflater.from(this.contexto);
        View view = this.layoutInflater.inflate(R.layout.contato_adapter, parent, false);

        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
        Contato contato = this.contatos.get(position);
        holder.txtContatoDescricao.setText(contato.getContato());
        holder.txtTipoContato.setText(contato.getTipo());
        // mapeando o evento de remover o contato da listagem de contatos do cliente
        holder.btnDeletarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnRemoverListener.onRemover(contato.getId());
            }
        });
        // mapeando o evento de editar o contato do cliente selecionado
        holder.btnEditarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnEditarListener.onEditar(contato.getId());
            }
        });
    }

    @Override
    public int getItemCount() {

        return this.contatos.size();
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public static class ContatoViewHolder extends RecyclerView.ViewHolder {

        public TextView txtContatoDescricao;
        public TextView txtTipoContato;
        public ImageButton btnDeletarContato;
        public ImageButton btnEditarContato;

        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            // mapear elementos de interface no viewholder
            this.txtContatoDescricao = itemView.findViewById(R.id.txt_contato_descricao);
            this.txtTipoContato = itemView.findViewById(R.id.txt_contato_tipo);
            this.btnDeletarContato = itemView.findViewById(R.id.btn_deletar_contato);
            this.btnEditarContato = itemView.findViewById(R.id.btn_editar_contato);
        }
    }
}
