package com.activity.bancowpossmvp.Modelos;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public SharedPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("bd_shared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void setUsuarioActivo(String usuario) {
        editor.putString("usuario", usuario);
        editor.apply();
    }

    public String getUsuarioActivo() {
        return sharedPreferences.getString("usuario", "no encontrado");
    }

    public void setAdministradorActivo(String administrador) {
        editor.putString("administrador", administrador);
        editor.apply();
    }

    public String getAdministradorActivo() {
        return sharedPreferences.getString("administrador", "no encontrado");
    }

    public void setUsuarioEstado(String usuarioEstado) {
        editor.putString("usuarioEstado", usuarioEstado);
        editor.apply();
    }

    public String getUsuarioEstado() {
        return sharedPreferences.getString("usuarioEstado", "no encontrado");
    }
}
