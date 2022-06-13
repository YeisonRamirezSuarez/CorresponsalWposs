package com.activity.bancowpossmvp.MVP.Model;

import static com.activity.bancowpossmvp.tools.Constantes.*;


import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.tools.Metodos;

public class LoginModel implements Interface.Model {

    Context context;
    Corresponsal corresponsal;
    Interface.Presenter presenter;
    Metodos metodos;
    DbHelper dbHelper;

    public LoginModel(Interface.Presenter presenter, Context context) {
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
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);


        int var = metodos.login(corresponsal);

        boolean isValidar = false;

        if (var == 1) {
            isValidar = true;
            presenter.showScreen(SCREEN_CORRECTO, "");
            presenter.showScreen(SCREEN_MENU_CORRESPONSAL, "");
            return;
        } else {
            presenter.showScreen(SCREEN_ERROR, "");
        }
        presenter.respuestaValidarPresenter(isValidar);

    }

    @Override
    public void llevarMenuModelo(Object clase, Object clase2, String menu) {

    }

    @Override
    public void pedirLista() {

    }
}
