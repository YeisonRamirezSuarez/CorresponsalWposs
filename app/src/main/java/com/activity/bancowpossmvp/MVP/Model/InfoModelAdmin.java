package com.activity.bancowpossmvp.MVP.Model;

import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.Modelos.Banco;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.tools.Metodos;


import java.util.ArrayList;

public class InfoModelAdmin implements Interface.Model {

    Context context;
    Banco banco;
    Interface.Presenter presenter;
    Metodos metodos;
    DbHelper dbHelper;

    public InfoModelAdmin(Interface.Presenter presenter, Context context) {
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
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);
        banco = new Banco();

        ArrayList<Banco> listaBanco = metodos.almacenarDatosEnArraysAdministrador();
        presenter.adminRecibirArrayList(listaBanco);

    }

    @Override
    public void llevarClaseModelo(Corresponsal corresponsal) {

    }

    @Override
    public void llevarMenuModelo(Object clase, Object clase2, String menu) {

    }

    @Override
    public void pedirLista() {

    }
}
