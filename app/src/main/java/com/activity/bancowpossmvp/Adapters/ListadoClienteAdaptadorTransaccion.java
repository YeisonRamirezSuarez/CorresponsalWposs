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
import com.activity.bancowpossmvp.Modelos.Transacciones;
import com.activity.bancowpossmvp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListadoClienteAdaptadorTransaccion extends RecyclerView.Adapter<ListadoClienteAdaptadorTransaccion.MyViewHolder> {
    //Creamos el proceso para que identifique el la vista de Mi fila
    private Context context;
    private ArrayList<Transacciones> listaTransacciones;
    private ArrayList<Transacciones> listaOriginal;
    Interface.View view;

    public ListadoClienteAdaptadorTransaccion(Context context, ArrayList<Transacciones> listaTransacciones, Interface.View view){
        this.context = context;
        this.listaTransacciones = listaTransacciones;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaTransacciones);

        this.view = view;



        //listaOriginal.addAll(listaLibros);
    }

    //Aqui agregamos la vista
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mi_item_listado_transaccion, parent, false);
        return new MyViewHolder(view);

    }

    //Aqui diferenciamos entre el que guardamos y lo que traeremos en la Vista de mi fila
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fecha_transaccion_txt.setText(String.valueOf(listaTransacciones.get(position).getFechaTransaccion()));
        holder.tipo_transaccion_txt.setText(listaTransacciones.get(position).getTipoTransaccion());
        holder.nombre_transaccion_txt.setText(listaTransacciones.get(position).getNombreTansaccionEnvia());
        holder.monto_transaccion_txt.setText(MainActivity.formatNumber(listaTransacciones.get(position). getMontoTransaccion()));
        holder.cedula_transaccion_txt.setText(listaTransacciones.get(position).getRecibeTransaccion());
        holder.id_transaccion.setText(String.valueOf(listaTransacciones.get(position).getIdTransaccion()));



    }

    public void filtradoTransaccion(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaTransacciones.clear();
            listaTransacciones.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                listaTransacciones.clear();
                List<Transacciones> collecion = listaOriginal.stream()
                        .filter(i -> i.getTipoTransaccion().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());

                listaTransacciones.addAll(collecion);
            } else {
                for (Transacciones transacciones : listaOriginal) {
                    if (transacciones.getTipoTransaccion().contains(txtBuscar)) {
                        listaTransacciones.add(transacciones);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaTransacciones.size();
    }

    //Aqui igualamos los dos valores y los llamamos con ItemView
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fecha_transaccion_txt;
        TextView tipo_transaccion_txt;
        TextView nombre_transaccion_txt;
        TextView monto_transaccion_txt;
        TextView cedula_transaccion_txt;
        TextView id_transaccion;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha_transaccion_txt = itemView.findViewById(R.id.fecha_transaccion_txt);
            tipo_transaccion_txt = itemView.findViewById(R.id.tipo_transaccion_txt);
            nombre_transaccion_txt = itemView.findViewById(R.id.nombre_transaccion_txt);
            monto_transaccion_txt = itemView.findViewById(R.id.monto_transaccion_txt);
            cedula_transaccion_txt = itemView.findViewById(R.id.cedula_transaccion_txt);
            id_transaccion = itemView.findViewById(R.id.id_transaccion);


            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View view2) {
                    Transacciones transacciones = listaTransacciones.get(getAdapterPosition());
                    ArrayList<Transacciones> listaNueva = new ArrayList<>();
                    listaNueva.add(transacciones);
                    view.showScreen(SCREEN_ESTADO_CORERSPONSAL, listaNueva);
                }
            });*/


        }
    }
}
