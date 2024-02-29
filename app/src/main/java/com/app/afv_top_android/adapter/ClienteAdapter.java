package com.app.afv_top_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private List<Cliente> clientes;

    public ClienteAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        this.clientes = new ArrayList<>();
    }

    public ClienteAdapter(LayoutInflater layoutInflater, List<Cliente> clientes) {
        this.layoutInflater = layoutInflater;
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.cliente_adapter, parent, false);

        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = this.clientes.get(position);
        holder.txtNomeCliente.setText(cliente.getNomeCompleto());
        holder.txtCpfCliente.setText(cliente.getCpf());
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

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeCliente = itemView.findViewById(R.id.txt_nome_cliente);
            txtCpfCliente = itemView.findViewById(R.id.txt_cpf_cliente);
        }
    }
}
