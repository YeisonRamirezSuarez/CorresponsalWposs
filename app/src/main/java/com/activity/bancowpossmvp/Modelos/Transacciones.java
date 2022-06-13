package com.activity.bancowpossmvp.Modelos;

public class Transacciones {

    String id;
    int idTransaccion;
    String nombreTansaccionEnvia;
    String nombreTansaccionRecibe;
    String enviaTransaccion;
    String recibeTransaccion;
    String tarjetaTransaccion;
    String montoTransaccion;
    String tipoTransaccion;
    String numeroCuotasTransaccion;
    String fechaTransaccion;
    String correoTransaccion;

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreTansaccionEnvia() {
        return nombreTansaccionEnvia;
    }

    public void setNombreTansaccionEnvia(String nombreTansaccionEnvia) {
        this.nombreTansaccionEnvia = nombreTansaccionEnvia;
    }

    public String getNombreTansaccionRecibe() {
        return nombreTansaccionRecibe;
    }

    public void setNombreTansaccionRecibe(String nombreTansaccionRecibe) {
        this.nombreTansaccionRecibe = nombreTansaccionRecibe;
    }

    public String getEnviaTransaccion() {
        return enviaTransaccion;
    }

    public void setEnviaTransaccion(String enviaTransaccion) {
        this.enviaTransaccion = enviaTransaccion;
    }

    public String getRecibeTransaccion() {
        return recibeTransaccion;
    }

    public void setRecibeTransaccion(String recibeTransaccion) {
        this.recibeTransaccion = recibeTransaccion;
    }

    public String getTarjetaTransaccion() {
        return tarjetaTransaccion;
    }

    public void setTarjetaTransaccion(String tarjetaTransaccion) {
        this.tarjetaTransaccion = tarjetaTransaccion;
    }

    public String getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(String montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getNumeroCuotasTransaccion() {
        return numeroCuotasTransaccion;
    }

    public void setNumeroCuotasTransaccion(String numeroCuotasTransaccion) {
        this.numeroCuotasTransaccion = numeroCuotasTransaccion;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getCorreoTransaccion() {
        return correoTransaccion;
    }

    public void setCorreoTransaccion(String correoTransaccion) {
        this.correoTransaccion = correoTransaccion;
    }
}
