package com.activity.bancowpossmvp.MVP.Presenter;


import static com.activity.bancowpossmvp.tools.Constantes.*;

import android.content.Context;
import android.util.Patterns;

import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.MVP.Model.*;
import com.activity.bancowpossmvp.Modelos.Banco;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.Modelos.Transacciones;

import java.util.ArrayList;


public class Presenter implements Interface.Presenter {
    Interface.View view;
    Interface.Model modelRegistrar;
    Interface.Model modelLogin;
    Interface.Model model3;
    Interface.Model modelInfoAdmin;
    Interface.Model modelAdminCrearCliente;
    Interface.Model modelAdminRegistrarCorresponsal;
    Interface.Model modelAdminConsultarCliente;
    Interface.Model modelAdminConsultarCorresponsal;

    Corresponsal corresponsal;
    Clientes clientes;
    Transacciones transacciones;

    public Presenter(Interface.View view, Context context) {
        this.view = view;
        this.modelRegistrar = new RegistrarCorresponsalModel(this, context);
        this.modelLogin = new LoginModel(this, context);
        this.model3 = new InfoModel(this, context);
        this.modelInfoAdmin = new InfoModelAdmin(this, context);
        this.modelAdminCrearCliente = new AdminCrearCliente(this, context);
        this.modelAdminRegistrarCorresponsal = new AdminRegistrarCorresponsal(this, context);
        this.modelAdminConsultarCliente = new AdminConsultarCliente(this, context);
        this.modelAdminConsultarCorresponsal = new AdminConsultarCorresponsal(this, context);
    }

    @Override
    public void mandarModelo(Object object, String type) {

    }

    @Override
    public void recibeInfoModelo(Object object, String screen) {
        switch (screen) {
            case SCREEN_CONFIRMAR_CLIENTE:
                view.showScreen(SCREEN_TARJETA_CLIENTE, object, "");
                break;
            case CONSULTAR_CORRESPONSAL:
                view.showScreen(SCREEN_ESTADO_CORERSPONSAL, object, "");


        }


    }

    @Override
    public void consultarSaldo() {

    }

    @Override
    public void llevarMenuAdmin(Object clase, String menu) {
        try {
            switch (menu) {
                case CREAR_CLIENTE:
                    clientes = (Clientes) clase;
                    if (!clientes.getNombre_cliente().isEmpty() && !clientes.getCedula_cliente().isEmpty() &&
                            !clientes.getSaldo_cliente().isEmpty()) {
                        int nombre = clientes.getNombre_cliente().length();
                        if (nombre < 2) {
                            view.showAlertDialog("Digite un nombre real", SCREEN_VACIO, "", "");
                            return;
                        }
                        int cedula = clientes.getCedula_cliente().length();
                        if (cedula < 6) {
                            view.showAlertDialog("Numero de cedula invalido, ingrese\n  6 digitos minimo", SCREEN_VACIO, "", "");
                            return;
                        }

                        String saldo = clientes.getSaldo_cliente();
                        if (Integer.parseInt(saldo) < 10000) {
                            view.showAlertDialog("Saldo inicial a ingresar debe ser\n  minimo a $ 10.000", SCREEN_VACIO, "", "");
                            return;
                        }
                        if (Integer.parseInt(saldo) > 1000000) {
                            view.showAlertDialog("Saldo inicial a ingresar no debe ser\n mayor a $ 1´000.000", SCREEN_VACIO, "", "");
                            return;
                        }
                        clientes.setPin(true);
                        this.model3.llevarModelo(clientes);
                       // this.view.showScreen(SCREEN_PIN, clientes, "");
                    } else {
                        view.showAlertDialog("Campo vacios obligatorios", SCREEN_VACIO, "", "");
                    }


                    break;
                case SCREEN_PIN:
                    clientes = (Clientes) clase;
                    if (!clientes.getPin_cuenta_cliente().isEmpty()) {
                        int pin = clientes.getPin_cuenta_cliente().length();
                        if (pin < 4) {
                            view.showAlertDialog("PIN obligatorio, 4 digitos", SCREEN_VACIO, "", "");
                            return;
                        }
                        this.view.showScreen(SCREEN_PIN_NUEVAMENTE, "", "");

                    } else {
                        view.showAlertDialog("Campo vacios obligatorios", SCREEN_VACIO, "", "");
                    }


                    break;
                case SCREEN_PIN_NUEVAMENTE:
                    clientes = (Clientes) clase;

                    if (clientes.getPin_cuenta_cliente().isEmpty()) {
                    } else {
                        this.view.showScreen(SCREEN_CONFIRMAR_CLIENTE, "", "");
                    }
                    break;

                case SCREEN_CONFIRMAR_CLIENTE:
                    clientes = (Clientes) clase;
                    modelAdminCrearCliente.llevarMenuModelo(clientes, transacciones, SCREEN_CONFIRMAR_CLIENTE);

                    break;


                case REGISTRAR_CORRESPONSAL:
                    corresponsal = (Corresponsal) clase;
                    if (!corresponsal.getNitCorresponsal().isEmpty() && !corresponsal.getNombreCorresponsal().isEmpty() && !corresponsal.getCorreoCorresponsal().isEmpty() && !corresponsal.getContrasenaCorresponsal().isEmpty()) {

                        int nombre = corresponsal.getNombreCorresponsal().length();
                        if (nombre < 5) {
                            view.showAlertDialog("Digite un nombre real", SCREEN_VACIO, "", "");
                            return;
                        }
                        int cedula = corresponsal.getNitCorresponsal().length();
                        if (cedula < 6) {
                            view.showAlertDialog("Numero de NIT invalido, ingrese\n  6 digitos minimo", SCREEN_VACIO, "", "");
                            return;
                        }
                        String email = corresponsal.getCorreoCorresponsal();
                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                view.showAlertDialog("Correo electronico, invalido debe contener " + "@wpoos.com", SCREEN_VACIO, "", "");
                            return;
                        }
                        this.modelRegistrar.llevarModelo(corresponsal);
                       // this.view.showScreen(SCREEN_CONFIRMAR_CORERSPONSAL, "", "");
                    } else {
                        view.showAlertDialog("Campo vacios", SCREEN_VACIO, "", "");
                    }

                    break;

                case SCREEN_CONFIRMAR_CORERSPONSAL:
                    corresponsal = (Corresponsal) clase;
                    modelAdminRegistrarCorresponsal.llevarMenuModelo(corresponsal, transacciones, SCREEN_CONFIRMAR_CORERSPONSAL);
                    break;

                case CONSULTAR_CLIENTE:
                    clientes = (Clientes) clase;
                    if (!clientes.getCedula_cliente().isEmpty()) {
                        int cedula1 = clientes.getCedula_cliente().length();
                        if (cedula1 < 6) {
                            view.showAlertDialog("Numero de cedula invalido, ingrese\n  6 digitos minimo", SCREEN_VACIO, "", "");
                            return;
                        }
                        modelAdminConsultarCliente.llevarModelo(clase);
                    } else {
                        view.showAlertDialog("Número de cedula a consultar vacio", SCREEN_VACIO, "", "");
                    }
                    break;


                case CONSULTAR_CORRESPONSAL:
                    corresponsal = (Corresponsal) clase;
                    if (!corresponsal.getNitCorresponsal().isEmpty()) {
                        int nit = corresponsal.getNitCorresponsal().length();
                        if (nit < 6) {
                            view.showAlertDialog("Numero de NIT invalido, ingrese\n  6 digitos minimo", SCREEN_VACIO, "", "");
                            return;
                        }
                        modelAdminConsultarCorresponsal.llevarModelo(clase);
                        ///modelAdminConsultarCorresponsal.llevarMenuModelo(corresponsal, CONSULTAR_CORRESPONSAL);
                    } else {
                        view.showAlertDialog("Número de NIT a consultar vacio", SCREEN_VACIO, "", "");
                    }
                    break;


            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Switch case validation de campos -------- >" + e);
            view.showAlertDialog("Se ha producido un error", SCREEN_VACIO, "", "");
        }

    }

    @Override
    public void llevarMenuCorresponsal(Object clase, Object clase2, String menu, String typo) {
        switch (menu) {
            case PAGO_TARJETA:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;

                if (!clientes.getNombre_cliente().isEmpty() && !clientes.getCvv_cuenta_cliente().isEmpty() && !clientes.getFecha_expiracion_tarjeta_cliente().isEmpty()
                        && !transacciones.getNumeroCuotasTransaccion().isEmpty() && !clientes.getNumero_cuenta_cliente().isEmpty() && !transacciones.getMontoTransaccion().isEmpty()) {
                    model3.llevarMenuModelo(clase, clase2, PAGO_TARJETA);
                    //   this.view.showScreen(CONFIRMAR_INFORMACION_PROCESO_PIN, "");
                } else {
                    view.showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                }
                break;
            case DEPOSITO:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;

                if (!transacciones.getEnviaTransaccion().isEmpty() && !transacciones.getMontoTransaccion().isEmpty()) {


                    model3.llevarMenuModelo(clase, clase2, DEPOSITO);
                    //   this.view.showScreen(CONFIRMAR_INFORMACION_PROCESO_PIN, "");
                } else {
                    view.showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                }

                //this.showScreen(CONFIRMAR_INFORMACION_PROCESO);
                break;

            case SCREEN_PIN:
                clientes = (Clientes) clase2;

                clientes.setPin(false);

                this.view.showScreen(SCREEN_PIN, clientes, typo);

                break;

            case PROCESO_FINAL_TARJETA:
                this.model3.llevarMenuModelo(clase, clase2, PROCESO_FINAL_TARJETA);
                break;
            case PROCESO_FINALIZADO_CORRECTAMENTE:
                if (typo.equals(SCREEN_ACTUALIZAR_CLIENTE)) {
                    this.view.showAlertDialog("Proceso finalizado correctamente", SCREEN_CORRECTO, "", "");
                    this.view.showScreen(SCREEN_MENU_ADMIN, "", typo);
                }
                else if (typo.equals(SCREEN_ACTUALIZAR_CORERSPONSAL)) {
                    this.view.showAlertDialog("Proceso finalizado correctamente", SCREEN_CORRECTO, "", "");
                    this.view.showScreen(SCREEN_MENU_ADMIN, "", typo);
                } else {
                    this.view.showAlertDialog("Proceso finalizado correctamente", SCREEN_CORRECTO, "", "");
                    this.view.showScreen(SCREEN_MENU_CORRESPONSAL, "", typo);
                }


                break;
            case RETIRO:
                this.model3.llevarMenuModelo(clase, clase2, RETIRO);

                break;

            case CONFIRMAR_INFORMACION_PROCESO_TARJETA_PIN:
                clientes = (Clientes) clase;

                clientes.setPin(true);
                this.view.showScreen(CONFIRMAR_INFORMACION_PROCESO_TARJETA_PIN, clientes, typo);
                break;

            case PROCESO_FINAL_DEPOSITO:
                transacciones = (Transacciones) clase;
                corresponsal = (Corresponsal) clase2;

                String saldo = corresponsal.getSaldoCorresponsal();
                String saldTan = transacciones.getMontoTransaccion();


                if (Integer.parseInt(saldo) < Integer.parseInt(saldTan)) {
                    this.view.showScreen(DEPOSITO, clase2, DEPOSITO);
                } else {
                    this.view.showScreen(CONFIRMAR_INFORMACION_PROCESO_DEPOSITO, transacciones, "");
                }
                break;

            case PROCESO_FINAL_RETIRO:
                transacciones = (Transacciones) clase;
                clientes = (Clientes) clase2;
                transacciones.setNombreTansaccionEnvia(clientes.getNombre_cliente());
                transacciones.setTarjetaTransaccion(clientes.getNumero_cuenta_cliente());
                this.view.showAlertDialog("Retiro tiene un costo de 2.000 pesos que se descontarán directamente de su cuenta WPOSS.\n\n  Desea continuar?\n", SCREEN_CONFIRMACION, RETIRO, "");
                //this.view.showScreen(CONFIRMAR_INFORMACION_PROCESO_RETIRO, transacciones);
                break;
            case PROCESO_FINALIZADO_RETIRO:
                transacciones = (Transacciones) clase2;
                clientes = (Clientes) clase;
                this.model3.llevarMenuModelo(transacciones, clientes, PROCESO_FINAL_RETIRO);
                break;
            case PROCESO_FINALIZADO_DEPOSITO:
                transacciones = (Transacciones) clase;
                clientes = (Clientes) clase2;
                this.model3.llevarMenuModelo(transacciones, clientes, PROCESO_FINAL_DEPOSITO);
                break;

            case TRANSFERENCIA:
                this.model3.llevarMenuModelo(clase, clase2, TRANSFERENCIA);
                break;

            case PROCESO_FINAL_TRANSACCION:
                this.model3.llevarMenuModelo(clase, clase2, PROCESO_FINAL_TRANSACCION);
                break;

            case CONSULTA_SALDO:
                this.model3.llevarMenuModelo(clase, clase2, CONSULTA_SALDO);
                break;

            case PROCESO_CONSULTAR_SALDO:
                this.view.showAlertDialog("Consultar saldo tiene un costo de 1.000 pesos que se desocontarán directamente de su cuenta WPOSS.\n\n  Desea continuar?\n", SCREEN_CONFIRMACION, CONSULTA_SALDO, clase);
                break;
            case PROCESO_FINAL_CONSULTA_SALDO:
                this.model3.llevarMenuModelo(clase, clase2, PROCESO_FINAL_CONSULTA_SALDO);
                break;
            case ACTUALIZAR_CONTRASEÑA_CORRESPONSAL:
                if (typo.equals(VALIDACION_PIN_CONTRASEÑA)) {
                    view.showScreen(ACTUALIZAR_CONTRASEÑA_CORRESPONSAL, clase, VALIDACION_PIN_CONTRASEÑA);
                } else {
                    this.model3.llevarMenuModelo(clase, clase2, ACTUALIZAR_CONTRASEÑA_CORRESPONSAL);
                }

                break;
            case MODIFICAR_CONTRASEÑA_CORRESPONSAL:
                this.model3.llevarMenuModelo(clase, clase2, MODIFICAR_CONTRASEÑA_CORRESPONSAL);
                break;

            case SCREEN_ACTUALIZAR_CORERSPONSAL:
                if (typo.equals(SCREEN_ACTUALIZAR_CORERSPONSAL)) {
                    this.model3.llevarMenuModelo(clase, clase2, VALIDACION_PIN_CONTRASEÑA);
                } else {
                    this.model3.llevarMenuModelo(clase, clase2, SCREEN_ACTUALIZAR_CORERSPONSAL);
                }
                break;

            case SCREEN_ACTUALIZAR_CLIENTE:

                if (typo.equals(SCREEN_ACTUALIZAR_CLIENTE)) {
                    this.model3.llevarMenuModelo(clase, clase2, VALIDACION_PIN_ACTUALIZACION);
                } else {
                    this.model3.llevarMenuModelo(clase, clase2, SCREEN_ACTUALIZAR_CLIENTE);
                }
                break;

            case CONFIRMACION_ACTUALIZACION_CLIENTE:
                this.view.showScreen(SCREEN_ACTUALIZAR_CLIENTE, clase, "");
                break;

            case CONFIRMACION_ACTUALIZACION_CORRESPONSAL:
                this.view.showScreen(SCREEN_ACTUALIZAR_CORERSPONSAL, clase, "");
                break;
        }

    }

    @Override
    public void traerMenuAdmin(Object clase, String menu) {

        switch (menu) {
            case SCREEN_CONFIRMAR_CLIENTE:
                view.showScreen(SCREEN_TARJETA_CLIENTE, "", "");
                break;

            case SCREEN_CONFIRMAR_CORERSPONSAL:
                view.showAlertDialog("Registro corresponsal exitoso", SCREEN_CORRECTO, "", "");
                showScreen(SCREEN_MENU_ADMIN, "");
                break;

            default:
                showScreen(SCREEN_ERROR, "");
                break;
        }

    }

    @Override
    public void llevarClase(Corresponsal corresponsal, String tipoClase) {

        switch (tipoClase) {
            case SCREEN_LOGIN:
                if (corresponsal.getCorreoCorresponsal().isEmpty()) {
                    this.view.showMessage("Campos vacios");
                }
                this.modelLogin.llevarClaseModelo(corresponsal);

                break;

            case SCREEN_REGISTRAR:
                if (corresponsal.getNombreCorresponsal().isEmpty() || corresponsal.getNitCorresponsal().isEmpty() || corresponsal.getCorreoCorresponsal().isEmpty() || corresponsal.getContrasenaCorresponsal().isEmpty()) {
                    this.view.showMessage("Campos vacios");

                }
                this.modelRegistrar.llevarClaseModelo(corresponsal);

                break;


        }

    }

    @Override
    public void respuestaValidarPresenter(boolean validar) {

        view.respuestaValidarView(validar);

    }

    @Override
    public void showScreen(String screen, Object object) {
        view.showScreen(screen, object, "");
    }

    @Override
    public void showMessage(String msje) {
        view.showMessage(msje);

    }

    @Override
    public void modificarEstado(String estado, String correo) {
        modelRegistrar.recibeEstado(estado, correo);

    }

    @Override
    public void traeEstado(String estado, String correo) {
        view.llegaEstado(estado, correo);

    }

    @Override
    public void corresponsalPedirArrayList() {
        model3.arrayListCorresponsal();

    }

    @Override
    public void adminPedirArrayList() {
        modelInfoAdmin.arrayListAdmin();
    }

    @Override
    public void corresponsalArrayList(ArrayList<Corresponsal> listaCorresponsales) {
        view.verConsultaCorresponsal(listaCorresponsales);

    }

    @Override
    public void corresponsalRecibirArrayList(ArrayList<Corresponsal> listaCorresponsal) {
        view.arrayListCorresponsal(listaCorresponsal);
    }

    @Override
    public void adminRecibirArrayList(ArrayList<Banco> listaAdministrador) {
        view.arrayListAdmin(listaAdministrador);
    }

    @Override
    public void listadoClientesArrayList(ArrayList<Clientes> listadoClientes) {
        view.listadoClientesArray(listadoClientes);
    }

    @Override
    public void listadoCorresponsalArrayList(ArrayList<Corresponsal> listadoCorresponsal) {
        view.listadoCorresponsalArray(listadoCorresponsal);
    }

    @Override
    public void listadoTransaccionesArrayList(ArrayList<Transacciones> listadoTransaccion) {
        view.listadoTransaccionArray(listadoTransaccion);
    }

    @Override
    public void pedirListaClientes() {
        modelRegistrar.pedirLista();
    }

    @Override
    public void pedirListaCorresponsal() {
        modelAdminConsultarCorresponsal.pedirLista();
    }

    @Override
    public void pedirListaTransaccion() {
        model3.pedirLista();

    }

    @Override
    public void showAlertDialog(String message, String type, String typo, Object object) {
        view.showAlertDialog(message, type, typo, object);
    }


}
