package com.activity.bancowpossmvp.Modelos;

import android.util.Patterns;

public class Varios {

    /*public static String numeroAleatorio(){

        long numeroRandom = (long) (10000000L * Math.random());
        return ""+numeroRandom;
    }*/


    public static String generateCardCorresponsal(Corresponsal corresponsal, int length) {
        int number = (int) (3 + Math.round(Math.random() * 3));
        //String newCedula = corresponsal.getNitCorresponsal().substring(0, 6) + "0000";
        String newCedula = corresponsal.getNitCorresponsal().substring(6, 8) + "0000";

        String starCard = number + newCedula;//11

        starCard = starCard.trim();
        StringBuilder numberAccount = new StringBuilder(length);
        int fill = length - starCard.length();//5
        numberAccount.append(starCard);//11
        while (fill-- > 0) {
            numberAccount.append(Math.round(Math.random() * 9));
        }

        return numberAccount.toString();//12
    }

    public static String generateCardCliente(Clientes clientes, int length) {
        int number = (int) (3 + Math.round(Math.random() * 3));
        //String newCedula = corresponsal.getNitCorresponsal().substring(0, 6) + "0000";
        String newCedula = clientes.getCedula_cliente().substring(6, 8) + "0000";

        String starCard = number + newCedula;//11

        starCard = starCard.trim();
        StringBuilder numberAccount = new StringBuilder(length);
        int fill = length - starCard.length();//5
        numberAccount.append(starCard);//11
        while (fill-- > 0) {
            numberAccount.append(Math.round(Math.random() * 9));
        }

        return numberAccount.toString();//12
    }

    public static boolean validar(String nombre, String cedula, String email, String password) {
        if (nombre.isEmpty()) {
            return false;
        }
        if (cedula.isEmpty()) {
            return false;
        }
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

        } else {
            return false;
        }

       /* if(myDB.validarCorreo(email)){
            //email_input.setError("Este correo Ya existe esta registrado");
            return false;
        }*/

        /* String password = password_input.getText().toString();*/
        if (!validarPassword(password)) {
            return false;
        }
        return true;
    }

    public static boolean validarPassword(String password) {
        if (password.isEmpty()) {
            //password_input.setError("Debe ingresar Password");
            return false;
        }
        if (!password.matches("(.{8,15})")) {
            //password_input.setError("Debe ser de minimo 8 digitos");
            return false;
        }
        if (password.matches("(.*\\s.*)")) {
            //password_input.setError("No debe tener espacios");
            return false;
        }
        if (!password.matches("(.*[A-Z].*)")) {
            //password_input.setError("Debe contener mayusculas");
            return false;
        }
        if (!password.matches("(.*[a-z].*)")) {
            //password_input.setError("Debe contener minusculas");
            return false;
        }
        if (!password.matches("(.*[0-9].*)")) {
            //password_input.setError("Debe contener numeros");
            return false;
        }
        if (!password.matches("(.*[@#$%^&+*=.;:'/?|_,].*)")) {
            //password_input.setError("Debe contener caracteres especiales");
            return false;
        }
        return true;
    }

    public static boolean validarPagoTarjeta(String tarjeta, String cvv, String totalPagar, String nombre, String fecha, int selectedItemPosition) {
        if (tarjeta.isEmpty()) {
            return false;
        }
        if (tarjeta.length() < 16) {
            return false;
        }

        if (cvv.isEmpty()) {
            return false;
        }
        if (cvv.length() < 3) {
            return false;
        }
        if (fecha.isEmpty()) {
            return false;
        }
        if (nombre.isEmpty()) {
            return false;
        }
        if (totalPagar.isEmpty()) {
            return false;
        }

        int totalpagar = Integer.parseInt(totalPagar);
        if (totalpagar < 100 || totalpagar > 90000) {
            return false;
        }
        if (selectedItemPosition == 0) {
            return false;
        }
        return true;
    }

    public static String TipoTarjeta(String tarjeta) {
        String tipoTarejeta = "";
        tarjeta = tarjeta.substring(0,1);
        int inttarjeta = Integer.parseInt(tarjeta);
        switch (inttarjeta) {
            case  3:
                tipoTarejeta ="American Express";
                break;
            case  4:
                tipoTarejeta ="VISA";
                break;
            case  5:
                tipoTarejeta = "Master Card";
                break;
            case  6:
                tipoTarejeta = "UnionPay";
                break;
            default:
                tipoTarejeta = "Tarjeta invalida";
                break;
        }
        return tipoTarejeta;
    }

    public static String imagenTarjeta(String tarjeta) {
        String tipoTarejeta = "";
        int inttarjeta = Integer.parseInt(tarjeta);
        switch (inttarjeta) {
            case  3:
                tipoTarejeta ="American Express";
                break;
            case  4:
                tipoTarejeta ="VISA";
                break;
            case  5:
                tipoTarejeta = "Master Card";
                break;
            case  6:
                tipoTarejeta = "UnionPay";
                break;
            default:
                tipoTarejeta = "Tarjeta invalida";
                break;
        }
        return tipoTarejeta;
    }

    public static String generateCardCvv(Clientes clientes, int length) {
        int number = (int) (3 + Math.round(Math.random() * 3));

        String starCard = String.valueOf(number);
        starCard = starCard.trim();
        StringBuilder numberCvv = new StringBuilder(length);
        int fill = length - starCard.length();//5
        numberCvv.append(starCard);//11
        while (fill-- > 0) {
            numberCvv.append(Math.round(Math.random() * 3));
        }

        return numberCvv.toString();//12

    }

    /*public void String fechaExp() {


        return fechaExp();
    }
*/

}
