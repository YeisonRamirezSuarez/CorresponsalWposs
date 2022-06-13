package com.activity.bancowpossmvp.tools;


import static com.activity.bancowpossmvp.tools.Constantes.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.Modelos.Banco;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.Modelos.SharedPreference;
import com.activity.bancowpossmvp.Modelos.Transacciones;

import java.util.ArrayList;

public class Metodos {

    Context context;


    public Metodos(Context context) {
        this.context = context;
    }

    //----REGISTRAR CORRESPONSAL
    public long addCorresponsal(Corresponsal corresponsal) {
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_NOMBRE_CORRESPONSAL, corresponsal.getNombreCorresponsal());
            cv.put(COLUMN_NIT_CORRESPONSAL, corresponsal.getNitCorresponsal());
            cv.put(COLUMN_CORREO_CORRESPONSAL, corresponsal.getCorreoCorresponsal());
            cv.put(COLUMN_CONTRASENA_CORRESPONSAL, corresponsal.getContrasenaCorresponsal());
            cv.put(COLUMN_SALDO_CORRESPONSAL, corresponsal.getSaldoCorresponsal());
            cv.put(COLUMN_CUENTA_CORRESPONSAL, corresponsal.getCuentaCorresponsal());
            cv.put(COLUMN_ESTADO_CORRESPONSAL, corresponsal.getEstadoCorresponsal());

            id = db.insert(TABLE_CORRESPONSAL, null, cv);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    //----REGISTRAR CLIENTE
    public long addCliente(Clientes cliente) {
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_CEDULA_CLIENTE, cliente.getCedula_cliente());
            cv.put(COLUMN_NOMBRE_CLIENTE, cliente.getNombre_cliente());
            cv.put(COLUMN_NUMERO_CUENTA_CLIENTE, cliente.getNumero_cuenta_cliente());
            cv.put(COLUMN_PIN_CUENTA_CLIENTE, cliente.getPin_cuenta_cliente());
            cv.put(COLUMN_TIPO_CUENTA_CLIENTE, cliente.getTipo_cuenta_cliente());
            cv.put(COLUMN_CVV_CUENTA_CLIENTE, cliente.getCvv_cuenta_cliente());
            cv.put(COLUMN_FECHA_EXPIRACION_CUENTA_CLIENTE, cliente.getFecha_expiracion_tarjeta_cliente());
            cv.put(COLUMN_SALDO_CLIENTE, cliente.getSaldo_cliente());

            id = db.insert(TABLE_CLIENTE, null, cv);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    //----LOGIN
    public int login(Corresponsal corresponsal) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int a = 0;

        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_CORRESPONSAL + " '" + COLUMN_CORREO_CORRESPONSAL + "'", null);

        if (cursor != null && cursor.moveToFirst()) {
            Log.d("TAG1", cursor.getString(3));
            Log.d("TAG2", cursor.getString(4));
            Log.d("TAG3", corresponsal.getCorreoCorresponsal());
            Log.d("TAG4", corresponsal.getContrasenaCorresponsal());
            if (cursor.getString(3).equals(corresponsal.getCorreoCorresponsal()) && cursor.getString(4).equals(corresponsal.getContrasenaCorresponsal())) {
                a++;
            }
        }
        return a;
    }

    public ArrayList<Corresponsal> almacenarDatosEnArraysCorresponsal() {

        ArrayList<Corresponsal> listaCorresponsal = new ArrayList<>();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);
        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();

        cursor = sql.rawQuery("SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + " = '" + sharedPreference.getUsuarioActivo() + "' ", null);


        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                Corresponsal corresponsal = new Corresponsal();
                corresponsal.setIdCorresponsal(cursor.getString(0));
                corresponsal.setNombreCorresponsal(cursor.getString(1));
                corresponsal.setNitCorresponsal(cursor.getString(2));
                corresponsal.setCorreoCorresponsal(cursor.getString(3));
                corresponsal.setContrasenaCorresponsal(cursor.getString(4));
                corresponsal.setSaldoCorresponsal(cursor.getString(5));
                corresponsal.setCuentaCorresponsal(cursor.getString(6));
                corresponsal.setEstadoCorresponsal(cursor.getString(7));

                listaCorresponsal.add(corresponsal);
            }

        }
        return listaCorresponsal;

    }

    public ArrayList<Banco> almacenarDatosEnArraysAdministrador() {

        ArrayList<Banco> listaAdmin = new ArrayList<>();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);
        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();

        cursor = sql.rawQuery("SELECT * FROM " + TABLE_ADMINISTRADOR + " WHERE " + COLUMN_EMAIL_ADMINISTRADOR + " = '" + sharedPreference.getAdministradorActivo() + "' ", null);

            while (cursor.moveToNext()) {
                Banco banco = new Banco();
                banco.setId(cursor.getString(0));
                banco.setEmail(cursor.getString(1));
                banco.setPassword(cursor.getString(2));
                listaAdmin.add(banco);

        }
        return listaAdmin;

    }

    //Metodo consulta Cliente
    public ArrayList<Clientes> arraysClientes(String cedula) {

        ArrayList<Clientes> listaCliente = new ArrayList<>();
        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        cursor = sql.rawQuery("SELECT * FROM " + TABLE_CLIENTE + " WHERE " + COLUMN_CEDULA_CLIENTE + " = '" + cedula + "' ", null);


        if (cursor.getCount() == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Clientes cliente = new Clientes();
                cliente.setId_cliente(cursor.getString(0));
                cliente.setNombre_cliente(cursor.getString(2));
                cliente.setNumero_cuenta_cliente(cursor.getString(3));
                cliente.setPin_cuenta_cliente(cursor.getString(4));
                cliente.setTipo_cuenta_cliente(cursor.getString(5));
                cliente.setCvv_cuenta_cliente(cursor.getString(6));
                cliente.setFecha_expiracion_tarjeta_cliente(cursor.getString(7));
                cliente.setSaldo_cliente(cursor.getString(8));

                listaCliente.add(cliente);
            }

        }
        return listaCliente;
    }

    public boolean insertarTransaccion(Transacciones transacciones){
        DbHelper dbHelper = new DbHelper(context);
        SharedPreference sharedPreference = new SharedPreference(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_TRANSACCION_PROCESO, transacciones.getIdTransaccion());
        cv.put(COLUMN_NOMBRE_TRANSACCION, transacciones.getNombreTansaccionEnvia());
        cv.put(COLUMN_ENVIA_TRANSACCION, transacciones.getEnviaTransaccion());
        cv.put(COLUMN_RECIBE_TRANSACCION, transacciones.getRecibeTransaccion());
        cv.put(COLUMN_TARJETA_TRANSACCION, transacciones.getTarjetaTransaccion());
        cv.put(COLUMN_MONTO_TRANSACCION, transacciones.getMontoTransaccion());
        cv.put(COLUMN_TIPO_TRANSACCION, transacciones.getTipoTransaccion());
        cv.put(COLUMN_CUOTAS_TRANSACCION, transacciones.getNumeroCuotasTransaccion());
        cv.put(COLUMN_FECHA_PROCESO_TRANSACCION, transacciones.getFechaTransaccion());
        cv.put(COLUMN_CORREO_ELECTRONICO_TRANSACCION, sharedPreference.getUsuarioActivo());

        db.insert(TABLE_TRANSACCION, null, cv);
        return true;
    }
    //Actualizar Contraseña Corresponsal

    public boolean actualizarContraseñaCorresponsal(String contraseña, String correo) {

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CORRESPONSAL + " SET " + COLUMN_CONTRASENA_CORRESPONSAL + " = '" + contraseña + "'" + " " +
                    "WHERE  " + COLUMN_CORREO_CORRESPONSAL + " = '" + correo + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    //ActualizarCorresponsal
    public boolean actualizarSaldoCorresponsal(int saldo, String correo) {

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CORRESPONSAL + " SET " + COLUMN_SALDO_CORRESPONSAL + " = '" + saldo + "'" + " " +
                    "WHERE  " + COLUMN_CORREO_CORRESPONSAL + " = '" + correo + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    //ActualizarCliente
    public boolean actualizarSaldoCliente(int saldo, String cedula ) {

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CLIENTE + " SET " + COLUMN_SALDO_CLIENTE + " = '" + saldo + "'" + " " +
                    "WHERE  " + COLUMN_CEDULA_CLIENTE + " = '" + cedula + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    //ActualizarClientePIN
    public Clientes actualizarPinCliente(String pin, String cedula ) {

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CLIENTE + " SET " + COLUMN_PIN_CUENTA_CLIENTE + " = '" + pin + "'" + " " +
                    "WHERE  " + COLUMN_CEDULA_CLIENTE + " = '" + cedula + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return null;
    }

    //Metodo consulta Cliente
    public Clientes infoCedulaConsultaCliente(String cedula) {

        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        cursor = sql.rawQuery(" SELECT * FROM " + TABLE_CLIENTE + " WHERE " + COLUMN_CEDULA_CLIENTE + "='" + cedula +"'", null);


        if (cursor.getCount() == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Clientes clientes = new Clientes();
                clientes.setId_cliente(cursor.getString(0));
                clientes.setCedula_cliente(cursor.getString(1));
                clientes.setNombre_cliente(cursor.getString(2));
                clientes.setNumero_cuenta_cliente(cursor.getString(3));
                clientes.setPin_cuenta_cliente(cursor.getString(4));
                clientes.setTipo_cuenta_cliente(cursor.getString(5));
                clientes.setCvv_cuenta_cliente(cursor.getString(6));
                clientes.setFecha_expiracion_tarjeta_cliente(cursor.getString(7));
                clientes.setSaldo_cliente(cursor.getString(8));
                return clientes;
            }

        }
        return null;
    }

    //Metodo consulta Corresponsal
    public Corresponsal infoNitConsultaCorresponsal(String nit) {

        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        cursor = sql.rawQuery(" SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_NIT_CORRESPONSAL + "='" + nit +"'", null);


        if (cursor.getCount() == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Corresponsal corresponsal = new Corresponsal();
                corresponsal.setIdCorresponsal(cursor.getString(0));
                corresponsal.setNitCorresponsal(cursor.getString(1));
                corresponsal.setNombreCorresponsal(cursor.getString(2));
                corresponsal.setCorreoCorresponsal(cursor.getString(3));
                corresponsal.setContrasenaCorresponsal(cursor.getString(4));
                corresponsal.setSaldoCorresponsal(cursor.getString(5));
                corresponsal.setCuentaCorresponsal(cursor.getString(6));
                corresponsal.setEstadoCorresponsal(cursor.getString(7));
                return corresponsal;
            }

        }
        return null;
    }

    //Metodo consulta Corresponsal
    public Corresponsal infoNitConsultaCorresponsalCorreo(String correo) {

        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        cursor = sql.rawQuery(" SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + "='" + correo +"'", null);


        if (cursor.getCount() == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Corresponsal corresponsal = new Corresponsal();
                corresponsal.setIdCorresponsal(cursor.getString(0));
                corresponsal.setNitCorresponsal(cursor.getString(1));
                corresponsal.setNombreCorresponsal(cursor.getString(2));
                corresponsal.setCorreoCorresponsal(cursor.getString(3));
                corresponsal.setContrasenaCorresponsal(cursor.getString(4));
                corresponsal.setSaldoCorresponsal(cursor.getString(5));
                corresponsal.setCuentaCorresponsal(cursor.getString(6));
                corresponsal.setEstadoCorresponsal(cursor.getString(7));
                return corresponsal;
            }

        }
        return null;
    }

    //Metodo consulta Cliente
    public Clientes infoClientesConfirmacion(String tarjeta, String cvv) {

        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        cursor = sql.rawQuery(" SELECT * FROM " + TABLE_CLIENTE + " WHERE " + COLUMN_NUMERO_CUENTA_CLIENTE + "='" + tarjeta +"'" + " AND " + COLUMN_CVV_CUENTA_CLIENTE + "='" + cvv + "'", null);


        if (cursor.getCount() == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Clientes clientes = new Clientes();
                clientes.setId_cliente(cursor.getString(0));
                clientes.setCedula_cliente(cursor.getString(1));
                clientes.setNombre_cliente(cursor.getString(2));
                clientes.setNumero_cuenta_cliente(cursor.getString(3));
                clientes.setPin_cuenta_cliente(cursor.getString(4));
                clientes.setTipo_cuenta_cliente(cursor.getString(5));
                clientes.setCvv_cuenta_cliente(cursor.getString(6));
                clientes.setFecha_expiracion_tarjeta_cliente(cursor.getString(7));
                clientes.setSaldo_cliente(cursor.getString(8));
                return clientes;
            }

        }
        return null;
    }

    public Clientes infoClientesConfirmacionTarjeta(String tarjeta, String cvv, String nombreCliente, String fehcaEx) {

        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        cursor = sql.rawQuery(" SELECT * FROM " + TABLE_CLIENTE + " WHERE " + COLUMN_NUMERO_CUENTA_CLIENTE + "='" + tarjeta +"'" + " AND " + COLUMN_CVV_CUENTA_CLIENTE + "='" + cvv + "'" + " AND " + COLUMN_NOMBRE_CLIENTE + "='" + nombreCliente + "'" + " AND " + COLUMN_FECHA_EXPIRACION_CUENTA_CLIENTE + "='" + fehcaEx + "'", null);


        if (cursor.getCount() == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Clientes clientes = new Clientes();
                clientes.setId_cliente(cursor.getString(0));
                clientes.setCedula_cliente(cursor.getString(1));
                clientes.setNombre_cliente(cursor.getString(2));
                clientes.setNumero_cuenta_cliente(cursor.getString(3));
                clientes.setPin_cuenta_cliente(cursor.getString(4));
                clientes.setTipo_cuenta_cliente(cursor.getString(5));
                clientes.setCvv_cuenta_cliente(cursor.getString(6));
                clientes.setFecha_expiracion_tarjeta_cliente(cursor.getString(7));
                clientes.setSaldo_cliente(cursor.getString(8));
                return clientes;
            }

        }
        return null;
    }

    //Metodo consulta corresponsal
    public ArrayList<Corresponsal> arraysCorresponsal(String nit) {

        ArrayList<Corresponsal> listaCorresponsal = new ArrayList<>();
        Cursor cursor;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        cursor = sql.rawQuery("SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_NIT_CORRESPONSAL + " = '" + nit + "' ", null);


        if (cursor.getCount() == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Corresponsal corresponsal = new Corresponsal();
                corresponsal.setIdCorresponsal(cursor.getString(0));
                corresponsal.setNombreCorresponsal(cursor.getString(1));
                corresponsal.setNitCorresponsal(cursor.getString(2));
                corresponsal.setCorreoCorresponsal(cursor.getString(3));
                corresponsal.setContrasenaCorresponsal(cursor.getString(4));
                corresponsal.setSaldoCorresponsal(cursor.getString(5));
                corresponsal.setCuentaCorresponsal(cursor.getString(6));
                corresponsal.setEstadoCorresponsal(cursor.getString(7));

                listaCorresponsal.add(corresponsal);
            }

        }
        return listaCorresponsal;
    }

    public ArrayList<Clientes> listadoClientes(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);

        ArrayList<Clientes> listaContactos = new ArrayList<>();
        Clientes clientes = null;

        try (Cursor cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTE , null)) {

            if (cursorClientes.moveToFirst()) {
                do {
                    clientes= new Clientes();
                    clientes.setId_cliente(cursorClientes.getString(0));
                    clientes.setCedula_cliente(cursorClientes.getString(1));
                    clientes.setNombre_cliente(cursorClientes.getString(2));
                    clientes.setNumero_cuenta_cliente(cursorClientes.getString(3));
                    clientes.setPin_cuenta_cliente(cursorClientes.getString(4));
                    clientes.setTipo_cuenta_cliente(cursorClientes.getString(5));
                    clientes.setCvv_cuenta_cliente(cursorClientes.getString(6));
                    clientes.setFecha_expiracion_tarjeta_cliente(cursorClientes.getString(7));
                    clientes.setSaldo_cliente(cursorClientes.getString(8));
                    listaContactos.add(clientes);
                } while (cursorClientes.moveToNext());
            }
        }

        return listaContactos;
    }

    public ArrayList<Corresponsal> listadoCorresponsal(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);

        ArrayList<Corresponsal> listaContactos = new ArrayList<>();
        Corresponsal corresponsal = null;

        try (Cursor cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CORRESPONSAL , null)) {

            if (cursorClientes.moveToFirst()) {
                do {
                    corresponsal= new Corresponsal();
                    corresponsal.setIdCorresponsal(cursorClientes.getString(0));
                    corresponsal.setNombreCorresponsal(cursorClientes.getString(1));
                    corresponsal.setNitCorresponsal(cursorClientes.getString(2));
                    corresponsal.setCorreoCorresponsal(cursorClientes.getString(3));
                    corresponsal.setContrasenaCorresponsal(cursorClientes.getString(4));
                    corresponsal.setSaldoCorresponsal(cursorClientes.getString(5));
                    corresponsal.setCuentaCorresponsal(cursorClientes.getString(6));
                    corresponsal.setEstadoCorresponsal(cursorClientes.getString(7));
                    listaContactos.add(corresponsal);
                } while (cursorClientes.moveToNext());
            }
        }

        return listaContactos;
    }
    public ArrayList<Transacciones> validacionUltimaTransaccionCorreo(String correo){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);

        ArrayList<Transacciones> listaContactos = new ArrayList<>();
        Transacciones transacciones = null;
//        SELECT * FROM transaccion WHERE id_transaccion_proceso = (SELECT MAX(id_transaccion_proceso) from transaccion WHERE correo_corresponsal_transaccion = correo)
       // SELECT * FROM " + TABLE_TRANSACCION + " WHERE " + COLUMN_ID_TRANSACCION_PROCESO + " = '" + correo + "' ", null
        try (Cursor cursorClientes = db.rawQuery(" SELECT * FROM " + TABLE_TRANSACCION + " WHERE " + COLUMN_CORREO_ELECTRONICO_TRANSACCION + " = '" + correo + "' ", null)) {

            if (cursorClientes.moveToLast()) {
                    transacciones= new Transacciones();
                    transacciones.setId(cursorClientes.getString(0));
                    transacciones.setIdTransaccion(cursorClientes.getInt(1));
                    transacciones.setNombreTansaccionEnvia(cursorClientes.getString(2));
                    transacciones.setEnviaTransaccion(cursorClientes.getString(3));
                    transacciones.setRecibeTransaccion(cursorClientes.getString(4));
                    transacciones.setTarjetaTransaccion(cursorClientes.getString(5));
                    transacciones.setMontoTransaccion(cursorClientes.getString(6));
                    transacciones.setTipoTransaccion(cursorClientes.getString(7));
                    transacciones.setNumeroCuotasTransaccion(cursorClientes.getString(8));
                    transacciones.setFechaTransaccion(cursorClientes.getString(9));
                    transacciones.setCorreoTransaccion(cursorClientes.getString(10));
                    listaContactos.add(transacciones);
            }
        }


        return listaContactos;
    }

    public ArrayList<Transacciones> validacionUltimaTransaccion(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);

        ArrayList<Transacciones> listaContactos = new ArrayList<>();
        Transacciones transacciones = null;

        try (Cursor cursorClientes = db.rawQuery( "SELECT * FROM transaccion WHERE id_transaccion = ( SELECT MAX(id_transaccion) from transaccion) ", null)) {

            if (cursorClientes.moveToFirst()) {
                do {
                    transacciones= new Transacciones();
                    transacciones.setId(cursorClientes.getString(0));
                    transacciones.setIdTransaccion(cursorClientes.getInt(1));
                    transacciones.setNombreTansaccionEnvia(cursorClientes.getString(2));
                    transacciones.setEnviaTransaccion(cursorClientes.getString(3));
                    transacciones.setRecibeTransaccion(cursorClientes.getString(4));
                    transacciones.setTarjetaTransaccion(cursorClientes.getString(5));
                    transacciones.setMontoTransaccion(cursorClientes.getString(6));
                    transacciones.setTipoTransaccion(cursorClientes.getString(7));
                    transacciones.setNumeroCuotasTransaccion(cursorClientes.getString(8));
                    transacciones.setFechaTransaccion(cursorClientes.getString(9));
                    transacciones.setCorreoTransaccion(cursorClientes.getString(10));
                    listaContactos.add(transacciones);
                } while (cursorClientes.moveToNext());
            }
        }


        return listaContactos;
    }

    public ArrayList<Transacciones> listadoTransaccionesLogeado(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);

        ArrayList<Transacciones> listaContactos = new ArrayList<>();
        Transacciones transacciones = null;

        try (Cursor cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_TRANSACCION + " WHERE " + COLUMN_CORREO_ELECTRONICO_TRANSACCION + " = '" +  sharedPreference.getUsuarioActivo() + "' " + " ORDER BY " + COLUMN_ID_TRANSACCION  + " DESC " , null)) {

            if (cursorClientes.moveToFirst()) {
                do {
                    transacciones= new Transacciones();
                    transacciones.setId(cursorClientes.getString(0));
                    transacciones.setIdTransaccion(cursorClientes.getInt(1));
                    transacciones.setNombreTansaccionEnvia(cursorClientes.getString(2));
                    transacciones.setEnviaTransaccion(cursorClientes.getString(3));
                    transacciones.setRecibeTransaccion(cursorClientes.getString(4));
                    transacciones.setTarjetaTransaccion(cursorClientes.getString(5));
                    transacciones.setMontoTransaccion(cursorClientes.getString(6));
                    transacciones.setTipoTransaccion(cursorClientes.getString(7));
                    transacciones.setNumeroCuotasTransaccion(cursorClientes.getString(8));
                    transacciones.setFechaTransaccion(cursorClientes.getString(9));
                    transacciones.setCorreoTransaccion(cursorClientes.getString(10));
                    listaContactos.add(transacciones);
                } while (cursorClientes.moveToNext());
            }
        }


        return listaContactos;
    }

    public ArrayList<Transacciones> listadoTransacciones(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);

        ArrayList<Transacciones> listaContactos = new ArrayList<>();
        Transacciones transacciones = null;

        try (Cursor cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_TRANSACCION +  " ORDER BY " + COLUMN_ID_TRANSACCION  + " DESC " , null)) {

            if (cursorClientes.moveToFirst()) {
                do {
                    transacciones= new Transacciones();
                    transacciones.setId(cursorClientes.getString(0));
                    transacciones.setIdTransaccion(cursorClientes.getInt(1));
                    transacciones.setNombreTansaccionEnvia(cursorClientes.getString(2));
                    transacciones.setEnviaTransaccion(cursorClientes.getString(3));
                    transacciones.setRecibeTransaccion(cursorClientes.getString(4));
                    transacciones.setTarjetaTransaccion(cursorClientes.getString(5));
                    transacciones.setMontoTransaccion(cursorClientes.getString(6));
                    transacciones.setTipoTransaccion(cursorClientes.getString(7));
                    transacciones.setNumeroCuotasTransaccion(cursorClientes.getString(8));
                    transacciones.setFechaTransaccion(cursorClientes.getString(9));
                    transacciones.setCorreoTransaccion(cursorClientes.getString(10));
                    listaContactos.add(transacciones);
                } while (cursorClientes.moveToNext());
            }
        }


        return listaContactos;
    }


}
