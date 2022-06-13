package com.activity.bancowpossmvp.MVP.Model;



import static com.activity.bancowpossmvp.tools.Constantes.*;

import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.Modelos.SharedPreference;
import com.activity.bancowpossmvp.tools.Metodos;

import java.util.ArrayList;


public class AdminCrearCliente implements Interface.Model {

    Context context;
    Interface.Presenter presenter;
    DbHelper dbHelper;
    Clientes cliente;
    Metodos metodos;

    public AdminCrearCliente(Interface.Presenter presenter, Context context) {
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
        cliente = (Clientes) clase;

        String saldoCliente = cliente.getSaldo_cliente();

        ArrayList<Corresponsal> listaCor = metodos.almacenarDatosEnArraysCorresponsal();

       if (listaCor.size() != 0){
           String saldoCorre = listaCor.get(0).getSaldoCorresponsal();
           String correoC = listaCor.get(0).getCorreoCorresponsal();
           int proceso = Integer.parseInt(saldoCorre) - Integer.parseInt(saldoCliente);
           int procesofinal = proceso + 10000;
           metodos.actualizarSaldoCorresponsal(procesofinal, correoC);
       }

       long var = metodos.addCliente(cliente);

        boolean isValidar = false;

        if(var <= 0){
            //TODO: mostrar mensaje
            return;
        }

        presenter.traerMenuAdmin(cliente, SCREEN_CONFIRMAR_CLIENTE);
    }

    @Override
    public void pedirLista() {

    }
}
