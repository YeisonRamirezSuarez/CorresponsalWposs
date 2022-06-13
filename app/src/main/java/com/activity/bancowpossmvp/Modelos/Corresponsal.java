package com.activity.bancowpossmvp.Modelos;

import java.io.Serializable;

public class Corresponsal implements Serializable {

    private String idCorresponsal;
    private String nombreCorresponsal;
    private String nitCorresponsal;
    private String correoCorresponsal;
    private String contrasenaCorresponsal;
    private String  saldoCorresponsal;
    private String cuentaCorresponsal;
    private String estadoCorresponsal;

    public String getIdCorresponsal() {
        return idCorresponsal;
    }

    public void setIdCorresponsal(String idCorresponsal) {
        this.idCorresponsal = idCorresponsal;
    }

    public String getNombreCorresponsal() {
        return nombreCorresponsal;
    }

    public void setNombreCorresponsal(String nombreCorresponsal) {
        this.nombreCorresponsal = nombreCorresponsal;
    }

    public String getCorreoCorresponsal() {
        return correoCorresponsal;
    }

    public void setCorreoCorresponsal(String correoCorresponsal) {
        this.correoCorresponsal = correoCorresponsal;
    }

    public String getNitCorresponsal() {
        return nitCorresponsal;
    }

    public void setNitCorresponsal(String nitCorresponsal) {
        this.nitCorresponsal = nitCorresponsal;
    }

    public String getContrasenaCorresponsal() {
        return contrasenaCorresponsal;
    }

    public void setContrasenaCorresponsal(String contrasenaCorresponsal) {
        this.contrasenaCorresponsal = contrasenaCorresponsal;
    }

    public String getSaldoCorresponsal() {
        return saldoCorresponsal;
    }

    public void setSaldoCorresponsal(String saldoCorresponsal) {
        this.saldoCorresponsal = saldoCorresponsal;
    }

    public String getCuentaCorresponsal() {
        return cuentaCorresponsal;
    }

    public void setCuentaCorresponsal(String cuentaCorresponsal) {
        this.cuentaCorresponsal = cuentaCorresponsal;
    }

    public String getEstadoCorresponsal() {
        return estadoCorresponsal;
    }

    public void setEstadoCorresponsal(String estadoCorresponsal) {
        this.estadoCorresponsal = estadoCorresponsal;
    }
}
