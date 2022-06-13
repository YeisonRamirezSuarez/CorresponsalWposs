package com.activity.bancowpossmvp.Adapters;

import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_ESTADO_CORERSPONSAL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.MainActivity;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListadoClienteAdaptadorCorresponsal extends RecyclerView.Adapter<ListadoClienteAdaptadorCorresponsal.MyViewHolder> {
    //Creamos el proceso para que identifique el la vista de Mi fila
    private Context context;
    private ArrayList<Corresponsal> listaCorresponsal;
    private ArrayList<Corresponsal> listaOriginal;
    Interface.View view;

    public ListadoClienteAdaptadorCorresponsal(Context context, ArrayList<Corresponsal> listaCorresponsal, Interface.View view){
        this.context = context;
        this.listaCorresponsal = listaCorresponsal;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaCorresponsal);

        this.view = view;



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

        holder.nombre_cliente_txt_prestado.setText(String.valueOf(listaCorresponsal.get(position).getNombreCorresponsal()));
        holder.cuenta_txt_cliente.setText(listaCorresponsal.get(position).getCuentaCorresponsal());
        holder.cedula_cliente_txt.setText(String.valueOf(listaCorresponsal.get(position).getNitCorresponsal()));
        holder.saldo_cliente_txt.setText(MainActivity.formatNumber(listaCorresponsal.get(position).getSaldoCorresponsal()));

    }

    public void filtradoCorresponsal(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaCorresponsal.clear();
            listaCorresponsal.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                listaCorresponsal.clear();
                List<Corresponsal> collecion = listaOriginal.stream()
                        .filter(i -> i.getNitCorresponsal().replace(".", "").toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaCorresponsal.addAll(collecion);
            } else {
                for (Corresponsal corresponsal : listaOriginal) {
                    if (corresponsal.getNitCorresponsal().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaCorresponsal.add(corresponsal);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaCorresponsal.size();
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View view2) {
                    Corresponsal corresponsal = listaCorresponsal.get(getAdapterPosition());
                    ArrayList<Corresponsal> listaNueva = new ArrayList<>();
                    listaNueva.add(corresponsal);
                    view.showScreen(SCREEN_ESTADO_CORERSPONSAL, listaNueva, "");
                }
            });


        }
    }
}
