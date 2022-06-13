package com.activity.bancowpossmvp.tools;

public class Constantes {

   public static final String TABLE_ADMINISTRADOR = "administrador";
   public static final String COLUMN_ID_ADMINISTRADOR = "id_administrador";
   public static final String COLUMN_EMAIL_ADMINISTRADOR = "email_administrador";
   public static final String COLUMN_PASSWORD_ADMINISTRADOR = "password_administrador";

   public static final String TABLE_CORRESPONSAL = "corresponsal";
   public static final String COLUMN_ID_CORRESPONSAL = "id_corresponsal";
   public static final String COLUMN_NOMBRE_CORRESPONSAL = "nombre_corresponsal";
   public static final String COLUMN_NIT_CORRESPONSAL = "nit_corresponsal";
   public static final String COLUMN_CORREO_CORRESPONSAL = "correo_corresponsal";
   public static final String COLUMN_CONTRASENA_CORRESPONSAL = "contrasena_corresponsal";
   public static final String COLUMN_SALDO_CORRESPONSAL = "saldo_corresponsal";
   public static final String COLUMN_CUENTA_CORRESPONSAL = "cuenta_corresponsal";
   public static final String COLUMN_ESTADO_CORRESPONSAL = "estado_corresponsal";

   public static final String TABLE_TRANSACCION = "transaccion";
   public static final String COLUMN_ID_TRANSACCION = "id_transaccion";
   public static final String COLUMN_ID_TRANSACCION_PROCESO = "id_transaccion_proceso";
   public static final String COLUMN_NOMBRE_TRANSACCION = "nombre_transaccion";
   public static final String COLUMN_ENVIA_TRANSACCION = "envia_transaccion";
   public static final String COLUMN_RECIBE_TRANSACCION = "recibe_transaccion";
   public static final String COLUMN_TARJETA_TRANSACCION = "tarjeta_transaccion";
   public static final String COLUMN_MONTO_TRANSACCION = "saldo_transaccion";
   public static final String COLUMN_TIPO_TRANSACCION = "tipo_transaccion";
   public static final String COLUMN_CUOTAS_TRANSACCION = "cuotas_transaccion";
   public static final String COLUMN_FECHA_PROCESO_TRANSACCION = "fecha_transaccion";
   public static final String COLUMN_CORREO_ELECTRONICO_TRANSACCION = "correo_corresponsal_transaccion";

   public static final String TABLE_CLIENTE = "cliente";
   public static final String COLUMN_ID_CLIENTE = "id_cliente";
   public static final String COLUMN_CEDULA_CLIENTE = "cedula_cliente";
   public static final String COLUMN_NOMBRE_CLIENTE = "nombre_cliente";
   public static final String COLUMN_NUMERO_CUENTA_CLIENTE = "numero_cuenta_cliente";
   public static final String COLUMN_PIN_CUENTA_CLIENTE = "pin_cuenta_cliente";
   public static final String COLUMN_TIPO_CUENTA_CLIENTE = "tipo_cuenta_cliente";
   public static final String COLUMN_CVV_CUENTA_CLIENTE = "cvv_cuenta_cliente";
   public static final String COLUMN_FECHA_EXPIRACION_CUENTA_CLIENTE = "fecha_expiracion_cuenta_cliente";
   public static final String COLUMN_SALDO_CLIENTE = "saldo_cliente";



   public static final String SCREEN_LOGIN = "login";
   public static final String SCREEN_REGISTRAR = "registrar";
   public static final String SCREEN_PRE_LOGIN = "pre-login";
   public static final String SCREEN_ERROR = "error";
   public static final String SCREEN_VACIO = "vacio";
   public static final String SCREEN_VALIDACION = "validacion_proceso";
   public static final String SCREEN_CONFIRMACION = "confirmacion";
   public static final String SCREEN_CORRECTO = "correcto";
   public static final String SCREEN_CANCELAR_BANCO = "cancelar";
   public static final String SCREEN_CANCELAR_CORRESPONSAL = "cancelar_corresponsal";
   public static final String SCREEN_MENU_CORRESPONSAL = "menu";
   public static final String SCREEN_MENU_ADMIN = "menu-admin";
   public static final String SCREEN_PIN = "pin";
   public static final String SCREEN_PIN_CORRESPONSAL = "pin_corresposnal";
   public static final String SCREEN_PIN_NUEVAMENTE = "pin_nuevamente";
   public static final String SCREEN_TARJETA_CLIENTE = "tarjeta";
   public static final String SCREEN_CONFIRMAR_CLIENTE = "confirmar_cliente";
   public static final String SCREEN_CONFIRMAR_CORERSPONSAL = "confirmar_corresponsal";
   public static final String SCREEN_CONSULTA_CORERSPONSAL = "consulta_corresponsal";
   public static final String SCREEN_ESTADO_CORERSPONSAL = "consulta_corresponsal";


   public static final String SCREEN_ACTUALIZAR_CLIENTE = "screen_actualizar_cliente";
   public static final String CONFIRMACION_ACTUALIZACION_CLIENTE = "confirmacion_actualizacion_cliente";
   public static final String VALIDACION_PIN_ACTUALIZACION = "validacion_pin_actualizacion";
   public static final String VALIDACION_PIN_CONTRASEÑA = "validacion_contraseña_actualizacion";
   public static final String SCREEN_ACTUALIZAR_CORERSPONSAL = "screen_actualizar_corresponsal";
   public static final String CONFIRMACION_ACTUALIZACION_CORRESPONSAL = "screen_actualizar_corresponsal_final";

   public static final String CONFIRMAR_INFORMACION_PROCESO_DEPOSITO = "confirmar_proceso_deposito";
   public static final String CONFIRMAR_INFORMACION_PROCESO_RETIRO = "confirmar_proceso_retiro";
   public static final String CONFIRMAR_INFORMACION_PROCESO_TARJETA_PIN = "confirmar_proceso_pin";

   public static final String PROCESO_FINAL_TARJETA = "proceso_tarjeta";
   public static final String PROCESO_FINAL_DEPOSITO = "proceso_deposito";
   public static final String PROCESO_FINAL_RETIRO = "proceso_retiro";
   public static final String PROCESO_FINAL_TRANSACCION = "proceso_transaccion";
   public static final String PROCESO_FINAL_CONSULTA_SALDO = "proceso_consulta_saldo";

   public static final String PROCESO_CONSULTAR_SALDO = "consultar_saldo";

   public static final String PROCESO_FINALIZADO_RETIRO = "finalizacion_retiro";
   public static final String PROCESO_FINALIZADO_DEPOSITO = "finalizacion_deposito";
   public static final String PROCESO_FINALIZADO_CORRECTAMENTE = "finalizacion_correcta";

   public static final String ACTUALIZAR_CONTRASEÑA_CORRESPONSAL = "actualizar_datos";
   public static final String MODIFICAR_CONTRASEÑA_CORRESPONSAL = "actualizar_datos_final";
   //STRING CONSTANTES MENU CORRESPONSAL

   public static final String PAGO_TARJETA = "Pago Tarjeta";
   public static final String PAGO_TARJETA_URL = "https://cdn-icons.flaticon.com/png/512/5688/premium/5688539.png?token=exp=1654795908~hmac=4347f0adf321bab48aa48bd0e5f69ae2";

   public static final String RETIRO = "Retiro";
   public static final String RETIRO_URL = "https://cdn-icons-png.flaticon.com/512/2845/2845718.png";

   public static final String DEPOSITO = "Deposito";
   public static final String DEPOSITO_URL = "https://cdn-icons-png.flaticon.com/512/2650/2650125.png";

   public static final String TRANSFERENCIA = "Tranferencias";
   public static final String TRANSFERENCIA_URL = "https://cdn-icons-png.flaticon.com/512/3502/3502032.png";

   public static final String HISTORIAL_TRANSACCIONES = "Historial Transaccional";
   public static final String HISTORIAL_TRANSACCIONES_URL = "https://cdn-icons-png.flaticon.com/512/1584/1584831.png";

   public static final String CONSULTA_SALDO = "Consulta de saldo";
   public static final String CONSULTA_SALDO_URL = "https://cdn-icons-png.flaticon.com/512/1265/1265907.png";



   //STRING CONSTANTES MENU ADMINISTRADOR

   public static final String CREAR_CLIENTE = "Crear Cliente";
   public static final String CREAR_CLIENTE_URL = "https://cdn-icons-png.flaticon.com/512/2583/2583145.png";

   public static final String REGISTRAR_CORRESPONSAL = "Registrar Corresponsal";
   public static final String REGISTRAR_CORRESPONSAL_URL = "https://cdn-icons-png.flaticon.com/512/2097/2097276.png";

   public static final String CONSULTAR_CLIENTE = "Consultar Cliente";
   public static final String CONSULTAR_CLIENTE_URL = "https://cdn-icons.flaticon.com/png/512/4836/premium/4836998.png?token=exp=1654796277~hmac=ec10ea479b981d022823ed739ba2fc85";

   public static final String CONSULTAR_CORRESPONSAL = "Consultar Corresponsal";
   public static final String CONSULTAR_CORRESPONSAL_URL = "https://huncapital.mx/wp-content/uploads/2021/03/Group-1292.png";

   public static final String LISTADO_CLIENTES = "Listado Clientes";
   //public static final String LISTADO_CLIENTES_URL = "https://www.flaticon.es/premium-icon/icons/svg/2680/2680675.svg";
   public static final String LISTADO_CLIENTES_URL = "https://cdn-icons.flaticon.com/png/512/1046/premium/1046553.png?token=exp=1654795767~hmac=592e363987077d783cf5742f03d4c852";

   public static final String LISTADO_CORRESPONSALES = " Listado Corresponsal";
   public static final String LISTADO_CORRESPONSALES_URL = "https://cdn-icons-png.flaticon.com/512/3437/3437364.png";

   public static final String HABILITADO = "Habilitado";
   public static final String DESHABILITADO = "Deshabilitado";

}
