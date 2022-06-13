package com.activity.bancowpossmvp.MVP.Model;

import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_CONFIRMAR_CLIENTE;

import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;

import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.tools.Metodos;

import java.util.ArrayList;


public class AdminConsultarCliente implements Interface.Model {
    Context context;
    Clientes cliente;
    Interface.Presenter presenter;
    Metodos metodos;
    DbHelper dbHelper;

    public AdminConsultarCliente(Interface.Presenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
    }


    @Override
    public void llevarModelo(Object object) {
        metodos = new Metodos(context);
        cliente = (Clientes) object;


        ArrayList<Clientes> listaClientes = metodos.arraysClientes(cliente.getCedula_cliente());

        if (listaClientes != null) {
            presenter.recibeInfoModelo(listaClientes, SCREEN_CONFIRMAR_CLIENTE);
        } else {
            presenter.showMessage("Cedula no encontrada");
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
    }
    
    @Override
    public void pedirLista() {

    }
}
