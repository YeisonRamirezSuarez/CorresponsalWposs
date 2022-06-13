package com.activity.bancowpossmvp.Modelos;



public class Menu {
    String nombreMenu;
    String imgMenu;

    public Menu(String nombreMenu, String imgMenu) {
        this.nombreMenu = nombreMenu;
        this.imgMenu = imgMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public String getImgMenu() {
        return imgMenu;
    }

    public void setImgMenu(String imgMenu) {
        this.imgMenu = imgMenu;
    }

}

