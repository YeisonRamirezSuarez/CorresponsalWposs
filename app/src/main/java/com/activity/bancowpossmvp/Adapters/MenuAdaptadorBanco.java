package com.activity.bancowpossmvp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.bancowpossmvp.Modelos.Menu;
import com.activity.bancowpossmvp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuAdaptadorBanco extends RecyclerView.Adapter<MenuAdaptadorBanco.ViewMyHolder> {
    private Context context;
    ArrayList<Menu> listaMenu;
    MenuCallback callback;

    public MenuAdaptadorBanco(Context context, ArrayList<Menu> listaMenu, MenuCallback callback) {
        this.context = context;
        this.listaMenu = listaMenu;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_administrador, parent, false);
        return new ViewMyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMyHolder holder, int position) {
        final int poc = position;
        Menu menu = listaMenu.get(position);
        holder.nombreMenuAdmin.setText(String.valueOf(menu.getNombreMenu()));
        Glide.with(context)
                .load(String.valueOf(menu.getImgMenu()))
                .centerCrop()
                .error(R.drawable.ic_error)
                .into(holder.imgMenuAdmin);
        holder.mainLayoutMenuAdmin.setOnClickListener(v -> callback.clickListener(menu));

    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    public class ViewMyHolder extends RecyclerView.ViewHolder {
        TextView nombreMenuAdmin;
        ImageView imgMenuAdmin;
        LinearLayout mainLayoutMenuAdmin;

        public ViewMyHolder(@NonNull View itemView) {
            super(itemView);

            nombreMenuAdmin = itemView.findViewById(R.id.nombre_menu_admin_txt);
            imgMenuAdmin = itemView.findViewById(R.id.imagen_menu_admin);
            mainLayoutMenuAdmin = itemView.findViewById(R.id.mainLayoutMenuAdmin);
        }
    }
}
