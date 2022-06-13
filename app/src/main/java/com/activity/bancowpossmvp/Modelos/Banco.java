package com.activity.bancowpossmvp.Modelos;

public class Banco {

    private String id = "1";
    private String email = "admin@wposs.com";
    private String password = "Admin123*";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
