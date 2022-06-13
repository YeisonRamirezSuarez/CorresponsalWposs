package com.activity.bancowpossmvp.MVP.Model;

import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_CONFIRMAR_CORERSPONSAL;
import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_PIN;
import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_VALIDACION;

import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.tools.Metodos;

public class AdminRegistrarCorresponsal implements Interface.Model {
    Context context;
    Corresponsal corresponsal;
    Interface.Presenter presenter;
    Metodos metodos;
    DbHelper dbHelper;

    public AdminRegistrarCorresponsal(Interface.Presenter presenter, Context context){
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void llevarModelo(Object object) {
    }

    @Override
    public void verAdministrador() {

    }

    @Override
    public void recibeEstado(String estado, String correo) {

    }

    @Override
    public void arrayListCorresponsal() {

    }

    @Override
    public void arrayListAdmin() {

    }

    @Override
    public void llevarClaseModelo(Corresponsal corresponsal) {
    }

    @Override
    public void llevarMenuModelo(Object clase, Object clase2, String menu) {
        metodos = new Metodos(context);
        dbHelper = new DbHelper(context);
        corresponsal = (Corresponsal) clase;


        long var = metodos.addCorresponsal(corresponsal);

        boolean isValidar = false;

        if(var <= 0){
            //TODO: mostrar mensaje
            return;
        }

        presenter.traerMenuAdmin(corresponsal, SCREEN_CONFIRMAR_CORERSPONSAL);
    }

    @Override
    public void pedirLista() {

    }
}
