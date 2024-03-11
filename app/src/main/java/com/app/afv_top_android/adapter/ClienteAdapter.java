package com.app.afv_top_android.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.afv_top_android.R;
import com.app.afv_top_android.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private Context contexto;
    private LayoutInflater layoutInflater;
    private Resources resources;
    private List<Cliente> clientes;

    public ClienteAdapter() {
        this.clientes = new ArrayList<>();
    }

    public ClienteAdapter(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.contexto = parent.getContext();
        this.layoutInflater = LayoutInflater.from(this.contexto);
        View view = this.layoutInflater.inflate(R.layout.cliente_adapter, parent, false);
        this.resources = view.getResources();

        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = this.clientes.get(position);
        holder.txtNomeCliente.setText(cliente.getNomeCompleto());
        holder.txtCpfCliente.setText(cliente.getCpf());
        holder.txtStatusCliente.setText(cliente.getStatus() ? "Ativo" : "Inativo");
        holder.txtStatusCliente.setTextColor(cliente.getStatus() ? this.resources.getColor(R.color.verde) : this.resources.getColor(R.color.vermelho));
        holder.iconeStatusCliente.setImageResource(cliente.getStatus() ? R.drawable.ic_ativo : R.drawable.ic_inativo);
    }

    @Override
    public int getItemCount() {

        return this.clientes.size();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {

        public TextView txtNomeCliente;
        public TextView txtCpfCliente;
        public ImageView iconeStatusCliente;
        public TextView txtStatusCliente;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeCliente = itemView.findViewById(R.id.txt_nome_cliente);
            txtCpfCliente = itemView.findViewById(R.id.txt_cpf_cliente);
            iconeStatusCliente = itemView.findViewById(R.id.icone_status_cliente);
            txtStatusCliente = itemView.findViewById(R.id.txt_status_cliente);
        }
    }
}
