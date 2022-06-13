package com.activity.bancowpossmvp.MVP.Model;

import static com.activity.bancowpossmvp.tools.Constantes.CONSULTAR_CORRESPONSAL;

import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.tools.Metodos;

import java.util.ArrayList;

public class AdminConsultarCorresponsal implements Interface.Model {
    Context context;
    Corresponsal corresponsal;
    Interface.Presenter presenter;
    Metodos metodos;
    DbHelper dbHelper;

    public AdminConsultarCorresponsal(Interface.Presenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void llevarModelo(Object object) {
        metodos = new Metodos(context);
        corresponsal = (Corresponsal) object;


        ArrayList<Corresponsal> listaCorresponsal = metodos.arraysCorresponsal(corresponsal.getNitCorresponsal());

       /* if (listaClientes.size() > 0) {
            presenter.recibeInfoModelo(listaClientes);
        } else {
            presenter.showMessage("Cedula no encontrada");
        }*/

        if (listaCorresponsal != null) {
            presenter.recibeInfoModelo(listaCorresponsal, CONSULTAR_CORRESPONSAL);
        } else {
            presenter.showMessage("NIT no encontrado");
        }
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


        ArrayList<Corresponsal> listaCorresponsal = metodos.arraysCorresponsal(corresponsal.getNitCorresponsal());
        if (listaCorresponsal.size() > 0) {
            presenter.corresponsalArrayList(listaCorresponsal);
        }



    }

    @Override
    public void pedirLista() {
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);

        ArrayList<Corresponsal> listadoCorresponsal = metodos.listadoCorresponsal();

        presenter.listadoCorresponsalArrayList(listadoCorresponsal);
    }
}
