package com.activity.bancowpossmvp.MVP.Model;

import static com.activity.bancowpossmvp.tools.Constantes.*;

import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.tools.Metodos;

import java.util.ArrayList;

public class RegistrarCorresponsalModel implements Interface.Model {

    Context context;
    Corresponsal corresponsal;
    Interface.Presenter presenter;
    Metodos metodos;
    DbHelper dbHelper;

    public RegistrarCorresponsalModel(Interface.Presenter presenter, Context context){
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void llevarModelo(Object object) {
        corresponsal = (Corresponsal) object;
        metodos = new Metodos(context);

        Corresponsal corresponsal1;
        String nitConsultar = corresponsal.getNitCorresponsal();
        String correoConsultar = corresponsal.getCorreoCorresponsal();

        corresponsal1 = metodos.infoNitConsultaCorresponsal(nitConsultar);

        Corresponsal corresponsal2 = metodos.infoNitConsultaCorresponsalCorreo(correoConsultar);

        if (corresponsal1 == null && corresponsal2 == null){
            this.presenter.showScreen(SCREEN_CONFIRMAR_CORERSPONSAL, corresponsal);

        } else if (corresponsal1 != null){
            this.presenter.showAlertDialog("Numero de Nit ya esta registrado", SCREEN_VALIDACION, "", "");
            return;
        } else {
            this.presenter.showAlertDialog("Correo electronico ya esta registrado", SCREEN_VALIDACION, "", "");
            return;
        }
    }

    @Override
    public void verAdministrador() {

    }

    @Override
    public void recibeEstado(String estado, String correo) {
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);

        boolean proceso = dbHelper.actualizarEstadoUsuario(estado, correo);

        boolean isValidatort = false;
        if (proceso = true) {
            presenter.traeEstado(estado, correo);
        }


    }

    @Override
    public void arrayListCorresponsal() {

    }

    @Override
    public void arrayListAdmin() {

    }

    @Override
    public void llevarClaseModelo(Corresponsal corresponsal) {
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);

        long id = metodos.addCorresponsal(corresponsal);

        boolean isValidar = false;

        if(id > 0){
            isValidar = true;
            presenter.showScreen(SCREEN_CORRECTO, "");
            presenter.showScreen(SCREEN_LOGIN, "");
            return;
        }else{
            presenter.showScreen(SCREEN_ERROR, "");
        }
        presenter.respuestaValidarPresenter(isValidar);
    }

    @Override
    public void llevarMenuModelo(Object clase, Object clase2, String menu) {
    }

    @Override
    public void pedirLista() {
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);

        ArrayList<Clientes> listadoClientes = metodos.listadoClientes();

        presenter.listadoClientesArrayList(listadoClientes);



    }
}
