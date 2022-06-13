package com.activity.bancowpossmvp.MVP.Interfaces;

import com.activity.bancowpossmvp.Modelos.Banco;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.Modelos.Transacciones;

import java.util.ArrayList;

public interface Interface {

    interface View{

        void verPagoTarjeta();
        void verTransferencia();
        void verDeposito(String typo, Object object);
        void verRetiro();
        void verConsultaSaldo();
        void verCrearCliente(String typo);
        void verRegistrarCorresponsal();
        void verConsultarCliente(Object object);
        void verConsultarCorresponsal(Object object);
        void verListadoClientes();
        void verListadoCorresponsales();
        void verListadoTransacciones();
        void respuestaValidarView(boolean validar);
        void verPreLogin();
        void verLogin();
        void verPIN(Object object, String typo);
        void verPINnuevamente();
        void verConfirmarCliente();
        void verConfirmarCorresponsal();
        void verConsultadeSaldo(Object object);
        void verTarjetaCliente(Object object);
        void verMenuPrincipal();
        void verMenuAdministrador();
        void verCambioContraseña();
        void verActualizarCliente(Object object);
        void verActualizarCorresponsal(Object object);

        void showMessage(String msje);
        void showScreen(String screen, Object object, String typo);
        void showAlertDialog(String message, String type, String typo, Object object);


        void llegaEstado(String estado, String correo);

        void procesoFinal(Object object);

        void verConsultaCorresponsal(Object object);

        void arrayListCorresponsal(ArrayList <Corresponsal> listaCorresponsal);
        void arrayListAdmin(ArrayList <Banco> listaAdministrador);


        void listadoClientesArray(ArrayList<Clientes> listaClientes);
        void listadoCorresponsalArray(ArrayList<Corresponsal> listaCorresponsal);
        void listadoTransaccionArray(ArrayList<Transacciones> listaTransacciones);

        void verConfirmarProcesoTarjeta(Object object);
        void verConfirmarProcesoDeposito(Object object);
        void verConfirmarProcesoRetiro(Object object);





    }

    interface Presenter{


        void mandarModelo(Object object , String type);
        void recibeInfoModelo(Object object, String screen);

        void consultarSaldo();


        void llevarMenuAdmin(Object clase, String menu);

        void llevarMenuCorresponsal(Object clase, Object clase2, String menu, String typo);
        void traerMenuAdmin(Object clase, String menu);

        //void llevarCorresponsalAdmin(Corresponsal corresponsal, String menu);


        void llevarClase(Corresponsal corresponsal, String tipoClase);
        void respuestaValidarPresenter(boolean validar);
        void showScreen(String screen, Object object);
        void showMessage(String msje);

        void modificarEstado(String estado, String correo);
        void traeEstado(String estado, String correo);

        void corresponsalPedirArrayList();
        void adminPedirArrayList();

        void corresponsalArrayList(ArrayList<Corresponsal> listaCorresponsales);
        void corresponsalRecibirArrayList(ArrayList <Corresponsal> listaCorresponsal);
        void adminRecibirArrayList(ArrayList <Banco> listaAdministrador);





        void listadoClientesArrayList(ArrayList<Clientes> listadoClientes);
        void listadoCorresponsalArrayList(ArrayList<Corresponsal> listadoCorresponsal);
        void listadoTransaccionesArrayList(ArrayList<Transacciones> listadoTransaccion);


        void pedirListaClientes();
        void pedirListaCorresponsal();
        void pedirListaTransaccion();

        void showAlertDialog(String message, String type, String typo, Object object);
    }

    interface Model{



        void llevarModelo(Object object);

        void verAdministrador();

        void recibeEstado(String estado, String correo);

        void arrayListCorresponsal();
        void arrayListAdmin();

        void llevarClaseModelo(Corresponsal corresponsal);

        void llevarMenuModelo(Object clase, Object clase2, String menu);

        void pedirLista();

    }
}
