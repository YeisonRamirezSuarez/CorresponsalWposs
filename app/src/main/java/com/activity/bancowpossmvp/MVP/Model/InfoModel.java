package com.activity.bancowpossmvp.MVP.Model;

import static com.activity.bancowpossmvp.tools.Constantes.ACTUALIZAR_CONTRASEÑA_CORRESPONSAL;
import static com.activity.bancowpossmvp.tools.Constantes.CONFIRMACION_ACTUALIZACION_CLIENTE;
import static com.activity.bancowpossmvp.tools.Constantes.CONFIRMACION_ACTUALIZACION_CORRESPONSAL;
import static com.activity.bancowpossmvp.tools.Constantes.CONFIRMAR_INFORMACION_PROCESO_TARJETA_PIN;
import static com.activity.bancowpossmvp.tools.Constantes.CONSULTA_SALDO;
import static com.activity.bancowpossmvp.tools.Constantes.DEPOSITO;
import static com.activity.bancowpossmvp.tools.Constantes.MODIFICAR_CONTRASEÑA_CORRESPONSAL;
import static com.activity.bancowpossmvp.tools.Constantes.PAGO_TARJETA;
import static com.activity.bancowpossmvp.tools.Constantes.PROCESO_CONSULTAR_SALDO;
import static com.activity.bancowpossmvp.tools.Constantes.PROCESO_FINALIZADO_CORRECTAMENTE;
import static com.activity.bancowpossmvp.tools.Constantes.PROCESO_FINAL_CONSULTA_SALDO;
import static com.activity.bancowpossmvp.tools.Constantes.PROCESO_FINAL_DEPOSITO;
import static com.activity.bancowpossmvp.tools.Constantes.PROCESO_FINAL_RETIRO;
import static com.activity.bancowpossmvp.tools.Constantes.PROCESO_FINAL_TARJETA;
import static com.activity.bancowpossmvp.tools.Constantes.PROCESO_FINAL_TRANSACCION;
import static com.activity.bancowpossmvp.tools.Constantes.RETIRO;
import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_ACTUALIZAR_CLIENTE;
import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_ACTUALIZAR_CORERSPONSAL;
import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_PIN;
import static com.activity.bancowpossmvp.tools.Constantes.SCREEN_VALIDACION;
import static com.activity.bancowpossmvp.tools.Constantes.TRANSFERENCIA;
import static com.activity.bancowpossmvp.tools.Constantes.VALIDACION_PIN_ACTUALIZACION;
import static com.activity.bancowpossmvp.tools.Constantes.VALIDACION_PIN_CONTRASEÑA;

import android.content.Context;

import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.Modelos.SharedPreference;
import com.activity.bancowpossmvp.Modelos.Transacciones;
import com.activity.bancowpossmvp.tools.Metodos;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class InfoModel implements Interface.Model {

    Context context;
    Corresponsal corresponsal;
    Interface.Presenter presenter;
    Metodos metodos;
    Clientes clientes;
    Transacciones transacciones;
    DbHelper dbHelper;
    int id = 1;

    public InfoModel(Interface.Presenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void llevarModelo(Object object) {
        clientes = (Clientes) object;
        metodos = new Metodos(context);

        Clientes clientes1;
        String cedulaConsultar = clientes.getCedula_cliente();

        clientes1 = metodos.infoCedulaConsultaCliente(cedulaConsultar);

        if (clientes1 == null) {
            this.presenter.showScreen(SCREEN_PIN, clientes);
            //this.presenter.llevarMenuAdmin(clientes, SCREEN_PIN);
        } else {
            this.presenter.showAlertDialog("Numero de cedula ya esta registrado", SCREEN_VALIDACION, "", "");
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
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);
        corresponsal = new Corresponsal();

        ArrayList<Corresponsal> listaCorresponsal = metodos.almacenarDatosEnArraysCorresponsal();
        presenter.corresponsalRecibirArrayList(listaCorresponsal);

    }

    @Override
    public void arrayListAdmin() {

    }

    @Override
    public void llevarClaseModelo(Corresponsal corresponsal) {

    }


    private void idProceso(Object object)
    {
        SharedPreference sharedPreference = new SharedPreference(context);
        ArrayList<Corresponsal> listaProceso = (ArrayList<Corresponsal>) object;
        String correo = sharedPreference.getUsuarioActivo();
        boolean correoSer = false;
        ArrayList<Transacciones> lista = metodos.validacionUltimaTransaccion();
        int idAnterior = transacciones.getIdTransaccion();
        ArrayList<Transacciones> listaCor = metodos.listadoTransacciones();




        if (lista .size() == 0)
        {
            transacciones.setIdTransaccion(id);
            return;
        }
        else
        { String correoC = lista.get(0).getCorreoTransaccion();
            if (correo.equals(correoC)){
                int idPRod = lista.get(0).getIdTransaccion();
                transacciones.setIdTransaccion(idPRod + 1);
                return;
            }
            else
            {

                for (int i = 0; i < listaCor.size(); i++)
                {
                    if (listaCor.get(i).getCorreoTransaccion().equals(correo))
                    {
                        correoSer = true;
                    }
                }


            }

        }
        if (correoSer)
        {
            String correo1 = sharedPreference.getUsuarioActivo();
            ArrayList<Transacciones> listaProceso1 = metodos.validacionUltimaTransaccionCorreo(correo1);
            int idAnteriorProceso = listaProceso1.get(0).getIdTransaccion();
            transacciones.setIdTransaccion(idAnteriorProceso + 1);
        }
        else
        {
            transacciones.setIdTransaccion(id);
        }


    }

    @Override
    public void llevarMenuModelo(Object clase, Object clase2, String menu) {
        switch (menu) {
            case PAGO_TARJETA:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;

                String tarjeta = clientes.getNumero_cuenta_cliente();
                String cvv = clientes.getCvv_cuenta_cliente();
                String nombreTarjeta = clientes.getNombre_cliente();
                String fechaExp = clientes.getFecha_expiracion_tarjeta_cliente();
                String saldo = transacciones.getMontoTransaccion();


                clientes = metodos.infoClientesConfirmacionTarjeta(tarjeta, cvv, nombreTarjeta, fechaExp);

                if (Integer.parseInt(saldo) < 10000) {
                    presenter.showAlertDialog("No se permite, reaalizar pago con targeta inferiores a 10.000 pesos", SCREEN_VALIDACION, "", "");
                    return;
                }
                else if (clientes != null) {
                    presenter.llevarMenuCorresponsal(clientes, "", CONFIRMAR_INFORMACION_PROCESO_TARJETA_PIN, PAGO_TARJETA);
                } else {
                    presenter.showAlertDialog("Proceso invalido, verifica tus credenciales", SCREEN_VALIDACION, "", "");
                }
                break;

            case PROCESO_FINAL_TARJETA:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;

                ArrayList<Corresponsal> listaCo = metodos.almacenarDatosEnArraysCorresponsal();

                transacciones.getMontoTransaccion();
                transacciones.getNumeroCuotasTransaccion();
                transacciones.setNombreTansaccionEnvia(clientes.getNombre_cliente());
                transacciones.setTarjetaTransaccion(clientes.getNumero_cuenta_cliente());
                transacciones.setTipoTransaccion("PAGO_TARJETA");
                transacciones.setEnviaTransaccion(clientes.getCedula_cliente());
                transacciones.setRecibeTransaccion("NO_APLICA");
                DateFormat df = new SimpleDateFormat("h:mm a, dd/MM/yy"); //definir formato para fecha
                String fecha_operacion = df.format(Calendar.getInstance().getTime());
                transacciones.setFechaTransaccion(fecha_operacion);
                boolean proceso;
                idProceso(listaCo);
                proceso = metodos.insertarTransaccion(transacciones);


                if (proceso) {
                    String correoCorresponsal = listaCo.get(0).getCorreoCorresponsal();
                    String montoTransaccion = transacciones.getMontoTransaccion();
                    String saldoCorresponsal = listaCo.get(0).getSaldoCorresponsal();
                    int procesoCorresponsal = Integer.parseInt(montoTransaccion) + Integer.parseInt(saldoCorresponsal);
                    metodos.actualizarSaldoCorresponsal(procesoCorresponsal, correoCorresponsal);
                    //int proceso = saldoCliente + montoTransaccion;


                    String cuotas = transacciones.getNumeroCuotasTransaccion();
                    String saldoCliente = clientes.getSaldo_cliente();
                    String cedula = clientes.getCedula_cliente();
                    int restaValor = Integer.parseInt(montoTransaccion) / Integer.parseInt(cuotas);
                    int procesoCliente = Integer.parseInt(saldoCliente) - restaValor;
                    metodos.actualizarSaldoCliente(procesoCliente, cedula);


                    presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, "");
                } else {
                    presenter.showAlertDialog("Proceso invalido, verifica tus credenciales", SCREEN_VALIDACION, "", "");

                }
                break;

            case DEPOSITO:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;
                String cedulaRecibe = transacciones.getRecibeTransaccion();
                int saldoDeposito = Integer.parseInt(transacciones.getMontoTransaccion());
                ArrayList<Corresponsal> listaCor = metodos.almacenarDatosEnArraysCorresponsal();
                ArrayList<Clientes> lista = metodos.arraysClientes(cedulaRecibe);
                idProceso(listaCor);
                if (lista != null) {
                    if (saldoDeposito < 10000) {
                        presenter.showAlertDialog("No se permite, realizar un Deposito inferior a 10.000 pesos", SCREEN_VALIDACION, "", "");
                        return;
                    }
                    transacciones.setNombreTansaccionEnvia(lista.get(0).getNombre_cliente());
                    corresponsal.setSaldoCorresponsal(listaCor.get(0).getSaldoCorresponsal());
                    presenter.llevarMenuCorresponsal(transacciones, corresponsal, PROCESO_FINAL_DEPOSITO, "");
                } else {
                    presenter.showAlertDialog("Usuario ingresado, Cedula " + transacciones.getRecibeTransaccion() + " no esta registrado", SCREEN_VALIDACION, "", "");

                }
                break;
            case PROCESO_FINAL_DEPOSITO:
                clientes = (Clientes) clase2;
                transacciones = (Transacciones) clase;
                String cedula1 = transacciones.getEnviaTransaccion();
                String cedulaRecibe1 = transacciones.getRecibeTransaccion();
                ArrayList<Clientes> lista1 = metodos.arraysClientes(cedulaRecibe1);
                ArrayList<Corresponsal> listaCor1 = metodos.almacenarDatosEnArraysCorresponsal();
                if (lista1 != null) {
                    String correo = listaCor1.get(0).getCorreoCorresponsal();
                    String saldoCo = listaCor1.get(0).getSaldoCorresponsal();
                    int totalCo = Integer.parseInt(saldoCo) + 1000;
                    metodos.actualizarSaldoCorresponsal(totalCo, correo);


                    String clisaldo = lista1.get(0).getSaldo_cliente();
                    String montotran = transacciones.getMontoTransaccion();
                    int total = Integer.parseInt(clisaldo) + Integer.parseInt(montotran);
                    metodos.actualizarSaldoCliente(total, cedulaRecibe1);


                    String correoC = listaCor1.get(0).getCorreoCorresponsal();
                    String saldoCor = listaCor1.get(0).getSaldoCorresponsal();
                    int totalCor = Integer.parseInt(saldoCor) - Integer.parseInt(montotran);
                    metodos.actualizarSaldoCorresponsal(totalCor, correoC);


                    //Proceso transaccion
                    transacciones.setEnviaTransaccion(clientes.getCedula_cliente());
                    transacciones.getRecibeTransaccion();
                    transacciones.getMontoTransaccion();
                    transacciones.getFechaTransaccion();
                    transacciones.setNumeroCuotasTransaccion("No aplica");
                    transacciones.setTarjetaTransaccion("No aplica");
                    transacciones.setTipoTransaccion("DEPOSITO");
                    metodos.insertarTransaccion(transacciones);


                    presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, "");
                } else {
                    presenter.showAlertDialog("Proceso invalido, verifica tus credenciales", SCREEN_VALIDACION, "", "");

                }

                break;

            case RETIRO:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;
                String cedulaRetiro = clientes.getCedula_cliente();
                ArrayList<Clientes> listaRetiro = metodos.arraysClientes(cedulaRetiro);

                if (listaRetiro != null) {
                    int saldo1 = Integer.parseInt(transacciones.getMontoTransaccion());
                    if (saldo1 < 10000) {
                        presenter.showAlertDialog("No se permite, realizar un retiro inferior a 10.000 pesos", SCREEN_VALIDACION, "", "");
                        return;
                    } else if (saldo1 <= 1000000) {
                        clientes.setNombre_cliente(listaRetiro.get(0).getNombre_cliente());
                        clientes.setNumero_cuenta_cliente("No aplica");
                        clientes.setPin_cuenta_cliente(listaRetiro.get(0).getPin_cuenta_cliente());
                        clientes.setSaldo_cliente(listaRetiro.get(0).getSaldo_cliente());
                        transacciones.setNumeroCuotasTransaccion("No aplica");
                        transacciones.setNombreTansaccionEnvia(listaRetiro.get(0).getNombre_cliente());
                        //   presenter.llevarMenuCorresponsal(transacciones, clientes, SCREEN_PIN, RETIRO);

                        presenter.llevarMenuCorresponsal(transacciones, clientes, PROCESO_FINAL_RETIRO, "");

                    } else {
                        presenter.showAlertDialog("Monto a retirar permitido es de 1.000.0000 pesos", SCREEN_VALIDACION, "", "");
                    }

                } else {
                    presenter.showAlertDialog("Usuario ingresado, Cedula " + clientes.getCedula_cliente() + " no esta registrado", SCREEN_VALIDACION, "", "");
                }

                break;
            case PROCESO_FINAL_RETIRO:
                transacciones = (Transacciones) clase;
                clientes = (Clientes) clase2;
                String cedulaRetiro1 = clientes.getCedula_cliente();
                ArrayList<Clientes> listaRetiro1 = metodos.arraysClientes(cedulaRetiro1);
                ArrayList<Corresponsal> listaCorresponsal = metodos.almacenarDatosEnArraysCorresponsal();
                idProceso(listaCorresponsal);




                if (listaRetiro1 != null) {
                    int saldoRetiro = Integer.parseInt(listaRetiro1.get(0).getSaldo_cliente());
                    String saldoProceso = transacciones.getMontoTransaccion();


                    int procesoResta = Integer.parseInt(saldoProceso) + 2000;
                    int resultado = saldoRetiro - procesoResta;
                    metodos.actualizarSaldoCliente(resultado, cedulaRetiro1);

                    String correo = listaCorresponsal.get(0).getCorreoCorresponsal();
                    String saldoCo = listaCorresponsal.get(0).getSaldoCorresponsal();

                    int procesoSuma = Integer.parseInt(saldoProceso) + 2000;
                    int totalCo = Integer.parseInt(saldoCo) + procesoSuma;
                    metodos.actualizarSaldoCorresponsal(totalCo, correo);


                    /*String correoC = listaCorresponsal.get(0).getCorreoCorresponsal();
                    int saldoCor = listaCorresponsal.get(0).getSaldoCorresponsal();
                    int totalCor = saldoCor + saldoProceso;
                    metodos.actualizarSaldoCorresponsal(totalCor, correoC);*/

                    //Proceso transaccion
                    transacciones.setEnviaTransaccion(clientes.getCedula_cliente());
                    transacciones.setRecibeTransaccion(clientes.getCedula_cliente());
                    transacciones.setNombreTansaccionEnvia(clientes.getNombre_cliente());
                    transacciones.setTarjetaTransaccion(clientes.getNumero_cuenta_cliente());
                    transacciones.setTipoTransaccion("RETIRO");
                    DateFormat df1 = new SimpleDateFormat("h:mm a, dd/MM/yy"); //definir formato para fecha
                    String fecha_operacion1 = df1.format(Calendar.getInstance().getTime());
                    transacciones.setFechaTransaccion(fecha_operacion1);
                    metodos.insertarTransaccion(transacciones);


                    presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, "");
                } else {
                    presenter.showAlertDialog("Proceso invalido, verifica tus credenciales", SCREEN_VALIDACION, "", "");


                }

                break;

            case TRANSFERENCIA:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;
                String cedula_cliente_envia = transacciones.getEnviaTransaccion();
                String cedula_cliente_recibe = transacciones.getRecibeTransaccion();

                Clientes clientes1 = new Clientes();
                Clientes clientes2 = new Clientes();

                clientes1 = metodos.infoCedulaConsultaCliente(cedula_cliente_envia);
                clientes2 = metodos.infoCedulaConsultaCliente(cedula_cliente_recibe);
                if (clientes1 == null) {
                    presenter.showAlertDialog("Numero de Cedula ingresado en el campo 1 no esta registrado", SCREEN_VALIDACION, "", "");
                    return;
                } else {
                    if (clientes2 == null) {
                        presenter.showAlertDialog("Numero de Cedula ingresado en el campo 2 no esta registrado", SCREEN_VALIDACION, "", "");
                        return;
                    } else {
                        String saldoTranferencia = transacciones.getMontoTransaccion();
                        if (Integer.parseInt(saldoTranferencia) < 10000) {
                            presenter.showAlertDialog("No se permite, realizar una transferencia\n inferior a 10.000 pesos\n", SCREEN_VALIDACION, "", "");
                            return;
                        } if (Integer.parseInt(saldoTranferencia) > 1000000){
                            presenter.showAlertDialog("Monto minumo permitido en una transferencia\n es de 1.000.0000 pesos\n", SCREEN_VALIDACION, "", "");
                            return;
                        }
                        presenter.llevarMenuCorresponsal(clientes2, clientes1, SCREEN_PIN, TRANSFERENCIA);
                    }
                }
                break;
            case PROCESO_FINAL_TRANSACCION:
                clientes = (Clientes) clase;
                transacciones = (Transacciones) clase2;

                ArrayList<Corresponsal> listaCo1 = metodos.almacenarDatosEnArraysCorresponsal();
                idProceso(listaCo1);


                Clientes clientes11 = new Clientes();
                Clientes clientes22 = new Clientes();

                clientes11 = metodos.infoCedulaConsultaCliente(transacciones.getEnviaTransaccion());
                clientes22 = metodos.infoCedulaConsultaCliente(transacciones.getRecibeTransaccion());


                transacciones.getMontoTransaccion();
                transacciones.getNumeroCuotasTransaccion();
                transacciones.setNombreTansaccionEnvia(clientes.getNombre_cliente());
                transacciones.setTarjetaTransaccion("NO_APLICA");
                transacciones.getTipoTransaccion();
                transacciones.getEnviaTransaccion();
                transacciones.getRecibeTransaccion();
                DateFormat df1 = new SimpleDateFormat("h:mm a, dd/MM/yy"); //definir formato para fecha
                String fecha_operacion1 = df1.format(Calendar.getInstance().getTime());
                transacciones.setFechaTransaccion(fecha_operacion1);
                boolean proceso1;
                proceso1 = metodos.insertarTransaccion(transacciones);


                if (proceso1) {
                    //Descontar al cliente que recibe
                    String cedula = clientes22.getCedula_cliente();
                    int saldoRecibe = Integer.parseInt(clientes22.getSaldo_cliente());
                    int montoProceso = Integer.parseInt(transacciones.getMontoTransaccion());
                    int procesoClienteRecibe = saldoRecibe + montoProceso;
                    metodos.actualizarSaldoCliente(procesoClienteRecibe, cedula);


                    //Sumar al cliente que envia
                    String cedula11 = clientes11.getCedula_cliente();
                    int saldoEnvia = Integer.parseInt(clientes11.getSaldo_cliente());
                    int montoProcesoEnvia = Integer.parseInt(transacciones.getMontoTransaccion());
                    int procesoMas = montoProcesoEnvia + 1000;
                    int procesoClienteEnvia = saldoEnvia - procesoMas;
                    metodos.actualizarSaldoCliente(procesoClienteEnvia, cedula11);


                    String correoCorresponsal = listaCo1.get(0).getCorreoCorresponsal();
                    String saldoCorresponsal = listaCo1.get(0).getSaldoCorresponsal();
                    int procesoCorresponsal = Integer.parseInt(saldoCorresponsal) + 1000;
                    metodos.actualizarSaldoCorresponsal(procesoCorresponsal, correoCorresponsal);
                    //int proceso = saldoCliente + montoTransaccion;


                    presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, "");
                } else {
                    presenter.showAlertDialog("Proceso invalido, verifica tus credenciales", SCREEN_VALIDACION, "", "");

                }
                break;

            case CONSULTA_SALDO:
                clientes = (Clientes) clase;
                String cedulaConsultaSaldo = clientes.getCedula_cliente();
                clientes = metodos.infoCedulaConsultaCliente(cedulaConsultaSaldo);


                if (clientes != null) {
                    int saldoCliente = Integer.parseInt(clientes.getSaldo_cliente());
                    if (saldoCliente >= 1000) {
                        int total = saldoCliente - 1000;
                        metodos.actualizarSaldoCliente(total, cedulaConsultaSaldo);
                        clientes = metodos.infoCedulaConsultaCliente(cedulaConsultaSaldo);


                        presenter.llevarMenuCorresponsal(clientes, "", PROCESO_CONSULTAR_SALDO, "");

                    } else {
                        presenter.showAlertDialog("Saldo Insuficiente, te invitamos a realizar un deposito", SCREEN_VALIDACION, "", "");
                    }

                } else {
                    presenter.showAlertDialog("Numero de cedula a consultar no esta registrada", SCREEN_VALIDACION, "", "");
                }
                break;

            case PROCESO_FINAL_CONSULTA_SALDO:
                clientes = (Clientes) clase;
                ArrayList<Corresponsal> listaCorresponsal11 = metodos.almacenarDatosEnArraysCorresponsal();
                boolean proceso12 = true;

                if (proceso12) {
                    String correoC = listaCorresponsal11.get(0).getCorreoCorresponsal();
                    String saldoCor = listaCorresponsal11.get(0).getSaldoCorresponsal();
                    int totalCor = Integer.parseInt(saldoCor) + 1000;
                    metodos.actualizarSaldoCorresponsal(totalCor, correoC);


                } else {
                }

                presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, "");

                break;

            case ACTUALIZAR_CONTRASEÑA_CORRESPONSAL:
                corresponsal = (Corresponsal) clase;
                ArrayList<Corresponsal> corresponsalArrayList = metodos.almacenarDatosEnArraysCorresponsal();
                boolean validacion = corresponsal.getContrasenaCorresponsal().equals(corresponsalArrayList.get(0).getContrasenaCorresponsal());

                if (validacion) {
                    boolean pasa = false;
                    presenter.llevarMenuCorresponsal(pasa, "", ACTUALIZAR_CONTRASEÑA_CORRESPONSAL, VALIDACION_PIN_CONTRASEÑA);
                } else {
                    presenter.showAlertDialog("Contraseña Actual Incorrecta, verificala", SCREEN_VALIDACION, "", "");

                }


                break;

            case MODIFICAR_CONTRASEÑA_CORRESPONSAL:
                corresponsal = (Corresponsal) clase;
                ArrayList<Corresponsal> corresponsalArray = metodos.almacenarDatosEnArraysCorresponsal();
                String correo = corresponsalArray.get(0).getCorreoCorresponsal();
                String contraseñaNueva = corresponsal.getContrasenaCorresponsal();
                boolean modificar = true;

                if (modificar) {
                    metodos.actualizarContraseñaCorresponsal(contraseñaNueva, correo);
                    presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, "");
                } else {
                    return;
                }

                break;

            case SCREEN_ACTUALIZAR_CORERSPONSAL:
                corresponsal = (Corresponsal) clase;
                metodos = new Metodos(context);

                String nitActualizar = corresponsal.getNitCorresponsal();

                corresponsal = metodos.infoNitConsultaCorresponsal(nitActualizar);

                if (corresponsal != null) {
                    presenter.llevarMenuCorresponsal(corresponsal, "", CONFIRMACION_ACTUALIZACION_CORRESPONSAL, "");
                } else {
                    presenter.showAlertDialog("Número de NIT, no registrado", SCREEN_VALIDACION, "", "");

                }

                break;

            case SCREEN_ACTUALIZAR_CLIENTE:
                clientes1 = (Clientes) clase;
                metodos = new Metodos(context);

                String cedulaActualizar = clientes1.getCedula_cliente();

                clientes = metodos.infoCedulaConsultaCliente(cedulaActualizar);

                if (clientes != null) {
                    presenter.llevarMenuCorresponsal(clientes, clientes1, CONFIRMACION_ACTUALIZACION_CLIENTE, "");
                } else {
                    presenter.showAlertDialog("Número de Cedula, no registrada", SCREEN_VALIDACION, "", "");

                }
                break;

            case VALIDACION_PIN_ACTUALIZACION:
                clientes = (Clientes) clase;

                String cedula = clientes.getCedula_cliente();
                String pin = clientes.getPin_cuenta_cliente();
                clientes = metodos.actualizarPinCliente(pin, cedula);

                presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, SCREEN_ACTUALIZAR_CLIENTE);

                break;

            case VALIDACION_PIN_CONTRASEÑA:

                corresponsal = (Corresponsal) clase;

                String correoCorres = corresponsal.getCorreoCorresponsal();
                String contraseña = corresponsal.getContrasenaCorresponsal();
                metodos.actualizarContraseñaCorresponsal(contraseña, correoCorres);

                presenter.llevarMenuCorresponsal("", "", PROCESO_FINALIZADO_CORRECTAMENTE, SCREEN_ACTUALIZAR_CORERSPONSAL);
                break;

        }
    }

    @Override
    public void pedirLista() {
        dbHelper = new DbHelper(context);
        metodos = new Metodos(context);

        ArrayList<Transacciones> listadoTransacciones = metodos.listadoTransaccionesLogeado();


        presenter.listadoTransaccionesArrayList(listadoTransacciones);

    }


}
