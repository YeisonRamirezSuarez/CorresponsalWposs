package com.activity.bancowpossmvp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.activity.bancowpossmvp.MainActivity;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListadoClienteAdaptadorCliente extends RecyclerView.Adapter<ListadoClienteAdaptadorCliente.MyViewHolder> {
    //Creamos el proceso para que identifique el la vista de Mi fila
    private Context context;
    private ArrayList<Clientes> listaClientes;
    private ArrayList<Clientes> listaOriginal;

    public ListadoClienteAdaptadorCliente(Context context, ArrayList<Clientes> listaClientes) {
        this.context = context;
        this.listaClientes = listaClientes;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaClientes);

        //listaOriginal.addAll(listaLibros);
    }

    //Aqui agregamos la vista
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mi_item_listado_cliente, parent, false);
        return new MyViewHolder(view);
    }

    //Aqui diferenciamos entre el que guardamos y lo que traeremos en la Vista de mi fila
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nombre_cliente_txt_prestado.setText(String.valueOf(listaClientes.get(position).getNombre_cliente()));
        holder.cuenta_txt_cliente.setText(listaClientes.get(position).getNumero_cuenta_cliente());
        holder.cedula_cliente_txt.setText(String.valueOf(listaClientes.get(position).getCedula_cliente()));
        holder.saldo_cliente_txt.setText(MainActivity.formatNumber(listaClientes.get(position).getSaldo_cliente()));

    }

    public void filtradoCliente(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaClientes.clear();
            listaClientes.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                listaClientes.clear();
                List<Clientes> collecion = listaOriginal.stream()
                        .filter(i -> i.getCedula_cliente().replace(".", "").toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaClientes.addAll(collecion);
            } else {
                for (Clientes libros : listaOriginal) {
                    if (libros.getCedula_cliente().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaClientes.add(libros);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    //Aqui igualamos los dos valores y los llamamos con ItemView
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre_cliente_txt_prestado;
        TextView cuenta_txt_cliente;
        TextView cedula_cliente_txt;
        TextView saldo_cliente_txt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_cliente_txt_prestado = itemView.findViewById(R.id.nombre_cliente_txt_prestado);
            cuenta_txt_cliente = itemView.findViewById(R.id.cuenta_txt_cliente);
            cedula_cliente_txt = itemView.findViewById(R.id.cedula_cliente_txt);
            saldo_cliente_txt = itemView.findViewById(R.id.saldo_cliente_txt);


            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View view) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, VerMiLibro.class);
                    intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });*/


        }
    }
}
