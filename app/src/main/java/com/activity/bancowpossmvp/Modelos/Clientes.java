package com.activity.bancowpossmvp.Modelos;

public class Clientes {

    private String id_cliente;
    private String cedula_cliente;
    private String nombre_cliente;
    private String numero_cuenta_cliente;
    private String cvv_cuenta_cliente;
    private String pin_cuenta_cliente;
    private String tipo_cuenta_cliente;
    private String fecha_expiracion_tarjeta_cliente;
    private String saldo_cliente;
    private boolean isPin;

    public String getPin_cuenta_cliente() {
        return pin_cuenta_cliente;
    }

    public void setPin_cuenta_cliente(String pin_cuenta_cliente) {
        this.pin_cuenta_cliente = pin_cuenta_cliente;
    }

    public String getTipo_cuenta_cliente() {
        return tipo_cuenta_cliente;
    }

    public void setTipo_cuenta_cliente(String tipo_cuenta_cliente) {
        this.tipo_cuenta_cliente = tipo_cuenta_cliente;
    }


    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNumero_cuenta_cliente() {
        return numero_cuenta_cliente;
    }

    public void setNumero_cuenta_cliente(String numero_cuenta_cliente) {
        this.numero_cuenta_cliente = numero_cuenta_cliente;
    }

    public String getCvv_cuenta_cliente() {
        return cvv_cuenta_cliente;
    }

    public void setCvv_cuenta_cliente(String cvv_cuenta_cliente) {
        this.cvv_cuenta_cliente = cvv_cuenta_cliente;
    }

    public String getFecha_expiracion_tarjeta_cliente() {
        return fecha_expiracion_tarjeta_cliente;
    }

    public void setFecha_expiracion_tarjeta_cliente(String fecha_expiracion_tarjeta_cliente) {
        this.fecha_expiracion_tarjeta_cliente = fecha_expiracion_tarjeta_cliente;
    }

    public String getSaldo_cliente() {
        return saldo_cliente;
    }

    public void setSaldo_cliente(String saldo_cliente) {
        this.saldo_cliente = saldo_cliente;
    }


    public boolean isPin() {
        return isPin;
    }

    public void setPin(boolean pin) {
        isPin = pin;
    }
}
