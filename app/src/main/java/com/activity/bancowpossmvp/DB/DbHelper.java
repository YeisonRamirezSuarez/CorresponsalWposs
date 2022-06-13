package com.activity.bancowpossmvp.DB;



import static com.activity.bancowpossmvp.tools.Constantes.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.activity.bancowpossmvp.Modelos.Banco;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.Modelos.Transacciones;
import com.activity.bancowpossmvp.tools.Constantes;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    Constantes cs;
    private Context context;
    private static final String name = "banco.db";
    private static final int version = 4;

    public DbHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryAdministrador =
                "Create table " + TABLE_ADMINISTRADOR + "(" +
                        COLUMN_ID_ADMINISTRADOR + " INTEGER primary key, " +
                        COLUMN_EMAIL_ADMINISTRADOR + " text," +
                        COLUMN_PASSWORD_ADMINISTRADOR + " text);";
        db.execSQL(queryAdministrador);

        String queryCorresponsal =
                "Create table " + TABLE_CORRESPONSAL + "(" +
                        COLUMN_ID_CORRESPONSAL + " INTEGER primary key AUTOINCREMENT, " +
                        COLUMN_NOMBRE_CORRESPONSAL + " text, " +
                        COLUMN_NIT_CORRESPONSAL + " text UNIQUE , " +
                        COLUMN_CORREO_CORRESPONSAL + " text UNIQUE , " +
                        COLUMN_CONTRASENA_CORRESPONSAL + " text, " +
                        COLUMN_SALDO_CORRESPONSAL + " INTEGER, " +
                        COLUMN_CUENTA_CORRESPONSAL + " text, " +
                        COLUMN_ESTADO_CORRESPONSAL + " text);";
        db.execSQL(queryCorresponsal);

        String queryTransaccion =
                "Create table " + TABLE_TRANSACCION + "(" +
                        COLUMN_ID_TRANSACCION + " INTEGER primary key AUTOINCREMENT, " +
                        COLUMN_ID_TRANSACCION_PROCESO + " text, " +
                        COLUMN_NOMBRE_TRANSACCION + " text, " +
                        COLUMN_ENVIA_TRANSACCION + " text, " +
                        COLUMN_RECIBE_TRANSACCION + " text, " +
                        COLUMN_TARJETA_TRANSACCION + " text, " +
                        COLUMN_MONTO_TRANSACCION + " text, " +
                        COLUMN_TIPO_TRANSACCION + " text, " +
                        COLUMN_CUOTAS_TRANSACCION + " text, " +
                        COLUMN_FECHA_PROCESO_TRANSACCION + " text, " +
                        COLUMN_CORREO_ELECTRONICO_TRANSACCION + " text);";
        db.execSQL(queryTransaccion);

        String queryCliente =
                "Create table " + TABLE_CLIENTE + "(" +
                        COLUMN_ID_CLIENTE + " INTEGER primary key AUTOINCREMENT, " +
                        COLUMN_CEDULA_CLIENTE + " text UNIQUE, " +
                        COLUMN_NOMBRE_CLIENTE + " text, " +
                        COLUMN_NUMERO_CUENTA_CLIENTE + " text , " +
                        COLUMN_PIN_CUENTA_CLIENTE + " text, " +
                        COLUMN_TIPO_CUENTA_CLIENTE + " text, " +
                        COLUMN_CVV_CUENTA_CLIENTE + " text, " +
                        COLUMN_FECHA_EXPIRACION_CUENTA_CLIENTE + " text, " +
                        COLUMN_SALDO_CLIENTE + " text);";
        db.execSQL(queryCliente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRADOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CORRESPONSAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACCION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTE);
        onCreate(db);
    }

    /**
     * addAdmin: agrega a la base de datos un administrador
     *
     * @param banco: toma los datos del objeto Administrador donde ya tenemos
     *                       establecido nuestro administrador
     */

    public void addAdmin(Banco banco) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.execSQL("DELETE FROM " + TABLE_ADMINISTRADOR);

        cv.put(COLUMN_ID_ADMINISTRADOR, banco.getId());
        cv.put(COLUMN_EMAIL_ADMINISTRADOR, banco.getEmail());
        cv.put(COLUMN_PASSWORD_ADMINISTRADOR, banco.getPassword());
        db.insert(TABLE_ADMINISTRADOR, null, cv);




    }

    /**
     * addUsuarios > agregar Usuarios a la base de datos
     *
     * @param corresponsal > objeto tomado de la clase Usuarios
     */

    public void addCorresponsal(Corresponsal corresponsal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOMBRE_CORRESPONSAL, corresponsal.getNombreCorresponsal());
        cv.put(COLUMN_NIT_CORRESPONSAL, corresponsal.getNitCorresponsal());
        cv.put(COLUMN_CORREO_CORRESPONSAL, corresponsal.getCorreoCorresponsal());
        cv.put(COLUMN_CONTRASENA_CORRESPONSAL, corresponsal.getContrasenaCorresponsal());
        cv.put(COLUMN_SALDO_CORRESPONSAL, corresponsal.getSaldoCorresponsal());
        cv.put(COLUMN_CUENTA_CORRESPONSAL, corresponsal.getCuentaCorresponsal());
        cv.put(COLUMN_ESTADO_CORRESPONSAL, corresponsal.getEstadoCorresponsal());

        db.insert(TABLE_CORRESPONSAL, null, cv);
    }

    /**
     * validarCorreo > metodo para validar si el correo que se ingresa para usuario nuevo
     * ya existe o no en la base de datos
     *
     * @param email > parametro extraido de registro de usuario
     * @return True > el correo ya existe
     * False > el correo no existe
     */

    public boolean validarCorreoUsuario(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + " = '" + email + "'";
        Corresponsal corresponsal = new Corresponsal();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                corresponsal.setNombreCorresponsal(cursor.getString(1));
                corresponsal.setNitCorresponsal(cursor.getString(2));
                corresponsal.setCorreoCorresponsal(cursor.getString(3));
                corresponsal.setContrasenaCorresponsal(cursor.getString(4));
                corresponsal.setSaldoCorresponsal(cursor.getString(5));
                corresponsal.setCuentaCorresponsal(cursor.getString(6));
                corresponsal.setEstadoCorresponsal(cursor.getString(7));
            }
            return true;
        }
        return false;
    }

    public boolean validarUsuario(Corresponsal corresponsal) {
        String password = corresponsal.getContrasenaCorresponsal();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + " = '" + corresponsal.getCorreoCorresponsal() + "'";
        Corresponsal usuario = new Corresponsal();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                usuario.setNombreCorresponsal(cursor.getString(1));
                usuario.setCuentaCorresponsal(cursor.getString(2));
                usuario.setCorreoCorresponsal(cursor.getString(3));
                usuario.setContrasenaCorresponsal(cursor.getString(4));
                usuario.setSaldoCorresponsal(cursor.getString(5));
                usuario.setCuentaCorresponsal(cursor.getString(6));
                usuario.setEstadoCorresponsal(cursor.getString(7));
            }
            if (usuario.getCorreoCorresponsal() != null) {
                return password.equals(usuario.getContrasenaCorresponsal());
            }
        }
        return false;
    }

    /**
     * validarUsuarioEstado > metodo usado para validar si nuestro usuario esta
     * habilitado o desahabilitado para poder iniciar sersion.
     *
     * @return True > Habilitado
     * false > Deshabilitado
     */

    public boolean validarUsuarioEstado(String correo, String estado) {
//        String estado = corresponsal.getEstadoCorresponsal();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + " = '" + correo + "'";
        Corresponsal corresponsal = new Corresponsal();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                corresponsal.setNombreCorresponsal(cursor.getString(1));
                corresponsal.setNitCorresponsal(cursor.getString(2));
                corresponsal.setCorreoCorresponsal(cursor.getString(3));
                corresponsal.setContrasenaCorresponsal(cursor.getString(4));
                corresponsal.setSaldoCorresponsal(cursor.getString(5));
                corresponsal.setCuentaCorresponsal(cursor.getString(6));
                corresponsal.setEstadoCorresponsal(cursor.getString(7));
            }
            if (corresponsal.getCorreoCorresponsal() != null) {
                return estado.equals(corresponsal.getEstadoCorresponsal());
            }
        }
        return false;
    }

    /**
     * validarAdministrador > Validamos que los datos del administrador digitados sean correctos para
     * poder iniciar sesion
     *
     * @return True > datos correctos
     * False > datos incorrectos
     */

    /*public boolean validarAdministrador(Administrador administrador) {
        String password = administrador.getPassword();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + " WHERE " + COLUMN_EMAIL_ADMINISTRADOR + " = '" + administrador.getEmail() + "'";
        Administrador administrador1 = new Administrador();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                administrador1.setEmail(cursor.getString(1));
                administrador1.setPassword(cursor.getString(2));
            }
            if (administrador1.getEmail() != null) {
                return password.equals(administrador.getPassword());
            }
        }
        return false;
    }*/

    public boolean validarAdministrador(Banco banco) {
        String password = banco.getPassword();
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM " + TABLE_ADMINISTRADOR + " WHERE " + COLUMN_EMAIL_ADMINISTRADOR + " = '" + banco.getEmail() + "'";
        Banco administrar = new Banco();
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                administrar.setEmail(cursor.getString(1));
                administrar.setPassword(cursor.getString(2));
            }
            if (administrar.getEmail() != null) {
                return password.equals(administrar.getPassword());
            }
        }
        return false;
    }

    /**
     * leerUsuarios > recorre la base de datos y nos captura todos los usuarios en ella
     *
     * @return lista > objeto ArrayList con los datos de los usuarios
     */

    public ArrayList<Corresponsal> leerUsuarios() {
        ArrayList<Corresponsal> lista = new ArrayList<>();
        String query =
                "SELECT * FROM " + TABLE_CORRESPONSAL;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
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
                lista.add(corresponsal);
            }
        }
        return lista;
    }

    /**
     *
     */
    public ArrayList<Corresponsal> leerUsuariosEstado(String email) {
        ArrayList<Corresponsal> lista = new ArrayList<>();
        String query =
                "SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + " = '" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
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
                lista.add(corresponsal);
            }
        }
        return lista;
    }

    /**
     * leerUsuariosfiltrado > recorre la base de datos y nos filtra el usuario que se esta queriendo obteber
     *
     * @param email > enviado desde MainActivityAdministrador
     * @return lista > objeto ArrayList con los datos del usuario
     */
    public ArrayList<Corresponsal> leerUsuariosfiltrado(String email) {
        ArrayList<Corresponsal> lista = new ArrayList<>();
        String query =
                "SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + " = '" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
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
                lista.add(corresponsal);
            }
        }
        return lista;
    }

    /**
     * @return
     */
    public Corresponsal leerUsuariosActivo(String email) {
        String query =
                "SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMN_CORREO_CORRESPONSAL + " = '" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            Corresponsal corresponsal = new Corresponsal();
            while (cursor.moveToNext()) {
                corresponsal.setIdCorresponsal(cursor.getString(0));
                corresponsal.setNombreCorresponsal(cursor.getString(1));
                corresponsal.setNitCorresponsal(cursor.getString(2));
                corresponsal.setCorreoCorresponsal(cursor.getString(3));
                corresponsal.setContrasenaCorresponsal(cursor.getString(4));
                corresponsal.setSaldoCorresponsal(cursor.getString(5));
                corresponsal.setCuentaCorresponsal(cursor.getString(6));
                corresponsal.setEstadoCorresponsal(cursor.getString(7));
            }
            return corresponsal;
        }
        return null;
    }

    /**
     * actualizarEstadoUsuario > cambia el estado de un usario para autorizar o desautorizar el inisio de sesion
     * y solo lo puede hacer el administrador
     *
     * @param estado se envia un string con el nuevo estado
     * @param correo el correo de quien queremos cambiar el estado esta tomado de la pantalla EstadoUsuario
     */

    public boolean actualizarEstadoUsuario(String estado, String correo ) {

        boolean correcto = false;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CORRESPONSAL + " SET " + COLUMN_ESTADO_CORRESPONSAL + " = '" + estado + "'" + " WHERE  " + COLUMN_CORREO_CORRESPONSAL + " = '" + correo + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    /*public boolean actualizarEstadoUsuario(String estado, String correo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "UPDATE " + TABLE_CORRESPONSAL + " SET " + COLUMN_ESTADO_CORRESPONSAL + " = '" + estado + "'"
                        + " WHERE  " + COLUMN_EMAIL_ADMINISTRADOR + " = '" + correo + "'";
        db.execSQL(query);
    return true;
    }*/

    /**
     * addTransaccion > metodo usado para guardar las transacciones realizados
     * Transacciones > viene del objeto de su mismo nombre
     */

    public void addTransaccion(Transacciones transacciones) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ENVIA_TRANSACCION, transacciones.getEnviaTransaccion());
        cv.put(COLUMN_RECIBE_TRANSACCION, transacciones.getRecibeTransaccion());
        cv.put(COLUMN_TARJETA_TRANSACCION, transacciones.getTarjetaTransaccion());
        cv.put(COLUMN_MONTO_TRANSACCION, transacciones.getMontoTransaccion());
        cv.put(COLUMN_TIPO_TRANSACCION, transacciones.getTipoTransaccion());
        cv.put(COLUMN_CUOTAS_TRANSACCION, transacciones.getNumeroCuotasTransaccion());
        cv.put(COLUMN_FECHA_PROCESO_TRANSACCION, transacciones.getFechaTransaccion());
        db.insert(TABLE_TRANSACCION, null, cv);
    }

    public void actualizarSaldoUsuarioActivo(String email, int saldoNuevo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "UPDATE " + TABLE_CORRESPONSAL + " SET " + COLUMN_SALDO_CORRESPONSAL + " = '" + saldoNuevo + "'"
                        + " WHERE  " + COLUMN_CORREO_CORRESPONSAL + " = '" + email + "'";
        db.execSQL(query);
    }


    /**
     * leerTransacciones > metodo usado para recorrer los datos de transacciones que se han registrado
     *
     * @return lista > contiene todas las transacciones
     */
    public ArrayList<Transacciones> leerTransacciones(String usuarioActivo) {
        ArrayList<Transacciones> lista = new ArrayList<>();
        String query =
                "SELECT * FROM " + TABLE_TRANSACCION +
                        " WHERE " + COLUMN_FECHA_PROCESO_TRANSACCION + " = '" + usuarioActivo + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Transacciones transacciones = new Transacciones();
                transacciones.setId(cursor.getString(0));
                transacciones.setEnviaTransaccion(cursor.getString(1));
                transacciones.setRecibeTransaccion(cursor.getString(2));
                transacciones.setTarjetaTransaccion(cursor.getString(3));
                transacciones.setMontoTransaccion(cursor.getString(4));
                transacciones.setTipoTransaccion(cursor.getString(5));
                transacciones.setNumeroCuotasTransaccion(cursor.getString(6));
                transacciones.setFechaTransaccion(cursor.getString(7));
                lista.add(transacciones);
            }
        }
        return lista;
    }

    /**
     * leerTransaccionesMovimiento > metodo usado para saber cuales son las transacciones que se hiciero
     *
     * @param tipoTransaccion > se envia desde historial detallado
     * @return lista de Pago tarjeta
     */
    public ArrayList<Transacciones> leerTransaccionesMovimiento(String tipoTransaccion, String usuarioActivo) {
        ArrayList<Transacciones> lista = new ArrayList<>();
        String query =
                "SELECT * FROM " + TABLE_TRANSACCION + " WHERE " + COLUMN_TIPO_TRANSACCION + " = '" + tipoTransaccion + "'" +
                        " AND " + COLUMN_FECHA_PROCESO_TRANSACCION + " = '" + usuarioActivo + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Transacciones transacciones = new Transacciones();
                transacciones.setId(cursor.getString(0));
                transacciones.setEnviaTransaccion(cursor.getString(1));
                transacciones.setRecibeTransaccion(cursor.getString(2));
                transacciones.setTarjetaTransaccion(cursor.getString(3));
                transacciones.setMontoTransaccion(cursor.getString(4));
                transacciones.setTipoTransaccion(cursor.getString(5));
                transacciones.setNumeroCuotasTransaccion(cursor.getString(6));
                transacciones.setFechaTransaccion(cursor.getString(7));
                lista.add(transacciones);
            }
        }
        return lista;
    }




}
