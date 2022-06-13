package com.activity.bancowpossmvp;
import static com.activity.bancowpossmvp.tools.Constantes.*;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.bancowpossmvp.Adapters.ListadoClienteAdaptadorCliente;
import com.activity.bancowpossmvp.Adapters.ListadoClienteAdaptadorCorresponsal;
import com.activity.bancowpossmvp.Adapters.ListadoClienteAdaptadorTransaccion;
import com.activity.bancowpossmvp.Adapters.MenuAdaptadorBanco;
import com.activity.bancowpossmvp.Adapters.MenuAdaptadorCorresponsal;
import com.activity.bancowpossmvp.Adapters.MenuCallback;
import com.activity.bancowpossmvp.DB.DbHelper;
import com.activity.bancowpossmvp.MVP.Interfaces.Interface;
import com.activity.bancowpossmvp.MVP.Presenter.Presenter;
import com.activity.bancowpossmvp.Modelos.Banco;
import com.activity.bancowpossmvp.Modelos.Clientes;
import com.activity.bancowpossmvp.Modelos.Corresponsal;
import com.activity.bancowpossmvp.Modelos.Menu;
import com.activity.bancowpossmvp.Modelos.SharedPreference;
import com.activity.bancowpossmvp.Modelos.Transacciones;
import com.activity.bancowpossmvp.Modelos.Varios;
import com.activity.bancowpossmvp.tools.Metodos;
import com.google.android.material.button.MaterialButton;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements Interface.View, MenuCallback, PopupMenu.OnMenuItemClickListener, SearchView.OnQueryTextListener {

    TextView nombre_banner;
    TextView cuenta_banner;
    TextView saldo_banner;
    TextView nombre_banner_banco;
    TextView rango_banner_banco;
    DbHelper dbHelper;
    Interface.Presenter presenter;


    Metodos metodos;
    Varios varios;

    //CREAR CLIENTE
    TextView tituloCrearCliente;
    EditText nombreCrearCliente;
    EditText cedulaCrearCliente;
    EditText saldoCrearCliente;
    Button confirmarCrearCliente;
    MaterialButton cancelarCrearCliente;
    LinearLayout fondoCrearCliente;
    View barraTitulo;


    //SCREEN_PIN
    EditText pinCliente;
    Button confirmarPin;

    //SCREEN_PIN_NUEVAMENTE
    EditText txt_ingrese_nuevamente_pin;
    Button btn_aceptar_pin;
    MaterialButton btn_cancelar_pin;

    //SCREEN_CONFIRMAR_CLIENTE
    TextView txt_nombre_confirmacion;
    TextView txt_numero_cedular_confirmacion;
    TextView txt_saldo_confirmacion;
    RelativeLayout relativeLayoutCliente;
    RelativeLayout relativeLayoutCliente2;
    TextView signo_dinero;
    Button btn_confirmar_confirmacion_datos_cliente;
    MaterialButton btn_cancelar_confirmacion_datos_cliente;

    //SCREEN_CONFIRMAR_CLIENTE
    TextView txt_nombre_confirmacion_corresponsal;
    TextView txt_numero_nit_confirmacion_corresponsal;
    TextView txt_correo_confirmacion_corresponsal;
    TextView txt_saldo_confirmacion_corresponsal;
    Button btn_confirmar_confirmacion_datos_corresponsal;
    Button btn_cancelar_confirmacion_datos_corresponsal;

    //SCREE_TARJETA
    TextView nombre_tarjeta;
    TextView saldo_tarjeta;
    TextView num_cuenta_tarjeta;
    TextView cvv_tarjeta;
    ImageView tipo_tarjeta;
    TextView fecha_tarjeta;
    MaterialButton btn_aceptar_tarjeta;

    //Re
    RecyclerView listadoClientes;
    ImageView volver;
    RecyclerView listadoCorresponsal;
    RecyclerView listadoTransacciones;


    //Corresponsal Actividad

    //Pago Tarjeta
    EditText ingreseNumeroDeTarjeta;
    EditText codigoCvv;
    EditText mes_vencimiento_pago_tarjeta;
    EditText dia_vencimiento_pago_tarjeta;
    EditText nombreCliente;
    EditText montoPagar;
    Button confirmarPagoTarjeta;
    Button cancelarPagoTarjeta;
    Spinner spinner;

    //Transferencia

    EditText numeroCedulaTransferir;
    EditText numeroCedulaATransferir;
    EditText montoTransferir;
    Button confirmarTranferencia;
    Button cancelarTranferencia;

    //Deposito

    EditText numeroCedulaDepostador;
    EditText numeroCedulaADepositar;
    EditText montoDepositar;
    TextView saldo_insuficiente_corresponsal;
    Button confirmarDeposito;
    Button cancelarDeposito;

    //Retiros

    EditText numeroCedulaRetiro;
    EditText montoRetirar;
    Button confirmarRetiro;
    Button cancelarRetiro;

    //Historial Tansaccional
    ImageView volverMenu;

    //ConsultaSaldo
    EditText numeroCedulaConsultarSaldo;
    Button confirmarConsultarSaldo;
    Button cancelarConsultarSaldo;

    //Confirmacion Tarjeta
    TextView nombreClienteConfirmacion;
    TextView montoConfirmacion;
    TextView numeroCuotas;
    TextView numeroTarjetaConfirmacion;
    TextView tipoTarjeta;
    Button btn_confirmar_confirmacion_datos_cliente_proceso;
    Button btn_cancelar_confirmacion_datos_cliente_proceso;

    //Confirmacion Deposito
    TextView nombreClienteConfirmacionDepositoEnvia;
    TextView nombreClienteConfirmacionDepositoRecibe;
    TextView montoConfirmacionDeposito;
    Button btn_confirmar_confirmacion_datos_cliente_proceso_deposito;
    Button btn_cancelar_confirmacion_datos_cliente_proceso_deposito;

    //Confirmacion Retiro
    TextView nombreClienteConfirmacionRetiro;
    TextView numeroCuentaRetiro;
    TextView montoConfirmacionRetiro;
    Button btn_confirmar_confirmacion_datos_cliente_proceso_retiro;
    Button btn_cancelar_confirmacion_datos_cliente_proceso_retiro;

    //Consulta Saldo
    TextView txt_nombre_confirmacion_saldo;
    TextView txt_numero_cedular_confirmacion_saldo;
    TextView txt_saldo_confirmacion_saldo;
    Button btn_aceptar_consulta_saldo;

    //Cambio de contraseña

    EditText contraseña_actual;
    EditText contraseña_nueva;
    EditText contraseña_confirmar_nueva;
    Button confirmar_actualizacion;
    Button cancelar_actualizacion;

    //Actualizacion Cliente
    EditText actualizar_nombre_cliente;
    EditText actualizar_numero_de_cedula;
    EditText actualizar_pin;
    EditText actualizar_pin_confirmar;
    Button confirmar_actualizacion_cliente;
    Button cancelar_actualizacion_cliente;
    LinearLayout linerBotonesActualizacion, linearbtnActualizar;
    Button btnActualizar;

    //Actualizacion Corresponsal
    EditText actualizar_nombre_corresponsal;
    EditText actualizar_numero_de_nit;
    EditText actualizar_correo_electronico;
    EditText actualizar_contraseña;
    EditText actualizar_contraseña_confirmacion;
    Button confirmar_actualizacion_corresponsal;
    Button cancelar_actualizacion_corresponsal;
    LinearLayout linearbotonesActualizarCorresponsal, linearbtnActualizarCorresponsal;
    Button btn_actualizar_correpsonsal;

    SearchView txtBuscar;

    ListadoClienteAdaptadorCliente listadoClienteAdaptadorCliente;
    ListadoClienteAdaptadorCorresponsal listadoClienteAdaptadorCorresponsal;
    ListadoClienteAdaptadorTransaccion listadoClienteAdaptadorTransaccion;

    boolean isColorCorresponsal = false;
    boolean isLoginPantalla = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(this, this);
        showScreen(SCREEN_PRE_LOGIN, "", "");
        dbHelper = new DbHelper(this);
        Banco banco = new Banco();
        dbHelper.addAdmin(banco);
        metodos = new Metodos(this);
        varios = new Varios();

    }

    public boolean ocultarTeclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return true;
    }



    public void showSuccessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_success_dailog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        isLoginPantalla = false;
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.success_title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.done);

        final AlertDialog alertDialog = builder.create();

        if (alertDialog.getWindow() != null) {
            ocultarTeclado();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) alertDialog.dismiss();
            }
        }, 2000);

    }

    public void showWarningDialogBanco(String message, String typo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_warning_dailog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        isLoginPantalla = false;
        builder.setView(view);
        if (isColorCorresponsal) {
            ((MaterialButton) view.findViewById(R.id.buttonYes)).setText(getResources().getString(R.string.yes));
            ((MaterialButton) view.findViewById(R.id.buttonYes)).setTextColor(getResources().getColor(R.color.white));
            ((MaterialButton) view.findViewById(R.id.buttonYes)).setBackgroundColor(getResources().getColor(R.color.naranja));
            ((MaterialButton) view.findViewById(R.id.buttonYes)).setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
            ((MaterialButton) view.findViewById(R.id.buttonNo)).setText(getResources().getString(R.string.no));
            ((MaterialButton) view.findViewById(R.id.buttonNo)).setTextColor(getResources().getColor(R.color.naranja));
            ((MaterialButton) view.findViewById(R.id.buttonNo)).setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
        }

        if (typo.equals(SCREEN_CANCELAR_BANCO) || typo.equals(SCREEN_CANCELAR_CORRESPONSAL)){
            ((TextView) view.findViewById(R.id.textTitle)).setText("Proceso de salida de la aplicación");
        } else {
            ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.warning_title));
        }
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);
        ((MaterialButton) view.findViewById(R.id.buttonYes)).setText(getResources().getString(R.string.yes));
        ((MaterialButton) view.findViewById(R.id.buttonNo)).setText(getResources().getString(R.string.no));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                if (isColorCorresponsal) {
                    if (typo.equals(SCREEN_CANCELAR_CORRESPONSAL)){
                        ocultarTeclado();
                        showScreen(SCREEN_PRE_LOGIN, "", "");

                    } else {
                        ocultarTeclado();
                        showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
                    }

                } else {
                    if (typo.equals(SCREEN_CANCELAR_BANCO)){
                        ocultarTeclado();
                        showScreen(SCREEN_PRE_LOGIN, "", "");
                    } else {
                        ocultarTeclado();
                        showScreen(SCREEN_MENU_ADMIN, "", "");
                    }
                }

            }
        });

        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            ocultarTeclado();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        ocultarTeclado();
        alertDialog.show();
    }

    public void showWarningDialogCorresponsal(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_warning_dailog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        isLoginPantalla = false;
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.warning_title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);

        ((MaterialButton) view.findViewById(R.id.buttonYes)).setText(getResources().getString(R.string.yes));
        ((MaterialButton) view.findViewById(R.id.buttonYes)).setTextColor(getResources().getColor(R.color.white));
        ((MaterialButton) view.findViewById(R.id.buttonYes)).setBackgroundColor(getResources().getColor(R.color.naranja));
        ((MaterialButton) view.findViewById(R.id.buttonYes)).setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
        ((MaterialButton) view.findViewById(R.id.buttonNo)).setText(getResources().getString(R.string.no));
        ((MaterialButton) view.findViewById(R.id.buttonNo)).setTextColor(getResources().getColor(R.color.naranja));
        ((MaterialButton) view.findViewById(R.id.buttonNo)).setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);


        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                ocultarTeclado();
                showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
            }
        });

        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            ocultarTeclado();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        ocultarTeclado();
        alertDialog.show();
    }

    public void showConfrimacion(String message, String typo, Object object) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_confirmacion_dailog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        isLoginPantalla = false;
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);
        ((Button) view.findViewById(R.id.buttonYes)).setText(getResources().getString(R.string.yes));
        ((Button) view.findViewById(R.id.buttonNo)).setText(getResources().getString(R.string.no));

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (typo.equals(RETIRO)) {
                    ocultarTeclado();
                    showScreen(CONFIRMAR_INFORMACION_PROCESO_RETIRO, transacciones, "");
                }
                if (typo.equals(CONSULTA_SALDO)) {
                    clientes = (Clientes) object;
                    ocultarTeclado();
                    showScreen(PROCESO_CONSULTAR_SALDO, clientes, "");
                }
                if (typo.equals(TRANSFERENCIA)){
                    clientes = (Clientes) object;
                    presenter.llevarMenuCorresponsal(clientes, transacciones, TRANSFERENCIA, "");
                }

            }
        });

        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            ocultarTeclado();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        ocultarTeclado();
        alertDialog.show();
    }

    public void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_error_dailog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        isLoginPantalla = false;
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.error_title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);
        ((Button) view.findViewById(R.id.buttonAction)).setText(getResources().getString(R.string.okay));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.error);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            ocultarTeclado();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        ocultarTeclado();
        alertDialog.show();
    }

    public void showVacioDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_vacio_dailog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        isLoginPantalla = false;
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.vacio_title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

        final AlertDialog alertDialog = builder.create();


        if (alertDialog.getWindow() != null) {
            ocultarTeclado();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        ocultarTeclado();
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) alertDialog.dismiss();
            }
        }, 2000);

    }

    public void showValidacionDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout_vacio_dailog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        isLoginPantalla = false;
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Incorrecto");
        ((TextView) view.findViewById(R.id.textMessage)).setText(message);
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

        final AlertDialog alertDialog = builder.create();


        if (alertDialog.getWindow() != null) {
            ocultarTeclado();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        ocultarTeclado();
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) alertDialog.dismiss();
            }
        }, 2000);

    }

    public static String notFormatThousandAmount(String amount) {
        if (amount.contains(",")) {
            return amount.replaceAll(",", "");
        }
        return amount;
    }

    private static String parseDoubleAmount(Object amount, DecimalFormat decimalFormat) {
        if (amount instanceof String) {
            if (!((String) amount).contains(",") && !((String) amount).contains(".")) {
                amount = Double.parseDouble((String) amount) / 100;
            } else if (((String) amount).contains(",")) {
                amount = Double.parseDouble(notFormatThousandAmount((String) amount));
            } else if (((String) amount).contains(".")) {
                amount = Double.parseDouble((String) amount);
            }
            return decimalFormat.format(amount);
        } else if (amount instanceof Long) {
            amount = Double.parseDouble(String.valueOf((long) amount));
            return decimalFormat.format((Double) amount / 100);
        }
        return decimalFormat.format(amount);
    }

    public static String toStringThousandAmount(Object amount) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return parseDoubleAmount(amount, df);
    }

    public static String toStringAmount(Object amount) {

        DecimalFormat df = new DecimalFormat("###.##");
        return parseDoubleAmount(amount, df);
    }

    @Override
    public void showScreen(String screen, Object object, String typo) {
        switch (screen) {
            case SCREEN_PRE_LOGIN:
                verPreLogin();
                break;

            case SCREEN_LOGIN:
                verLogin();
                break;

            case SCREEN_REGISTRAR:
                verRegistrarCorresponsal();
                break;

            case SCREEN_MENU_CORRESPONSAL:
                verMenuPrincipal();
                break;

            case SCREEN_MENU_ADMIN:
                verMenuAdministrador();
                break;

            /*ADMINISTRADOR*/
            case CREAR_CLIENTE:
                verCrearCliente(typo);
                break;

            case REGISTRAR_CORRESPONSAL:
                verRegistrarCorresponsal();
                break;

            case CONSULTAR_CLIENTE:
                verConsultarCliente(typo);
                break;

            case CONSULTAR_CORRESPONSAL:
                verConsultarCorresponsal(typo);
                break;

            case LISTADO_CLIENTES:
                verListadoClientes();
                break;

            case LISTADO_CORRESPONSALES:
                verListadoCorresponsales();
                break;

            /*CORRESPONSAL*/
            case PAGO_TARJETA:
                verPagoTarjeta();
                break;

            case TRANSFERENCIA:
                verTransferencia();
                break;

            case DEPOSITO:
                verDeposito(typo, object);
                break;

            case RETIRO:
                verRetiro();
                break;

            case HISTORIAL_TRANSACCIONES:
                verListadoTransacciones();
                break;

            case CONSULTA_SALDO:
                verConsultaSaldo();
                break;

            case SCREEN_PIN:
                verPIN(object, typo);
                break;


            case SCREEN_PIN_NUEVAMENTE:
                verPINnuevamente();
                break;

            case SCREEN_TARJETA_CLIENTE:
                verTarjetaCliente(object);
                break;

            case SCREEN_CONFIRMAR_CLIENTE:
                verConfirmarCliente();
                break;

            case SCREEN_ESTADO_CORERSPONSAL:
                verConsultaCorresponsal(object);
                break;

            case SCREEN_CONFIRMAR_CORERSPONSAL:
                verConfirmarCorresponsal();
                break;

            case CONFIRMAR_INFORMACION_PROCESO_DEPOSITO:
                verConfirmarProcesoDeposito(object);

                break;

            case CONFIRMAR_INFORMACION_PROCESO_TARJETA_PIN:
                verConfirmarProcesoTarjeta(object);
                break;

            case CONFIRMAR_INFORMACION_PROCESO_RETIRO:
                verConfirmarProcesoRetiro(object);
                break;

            case PROCESO_CONSULTAR_SALDO:
                verConsultadeSaldo(object);
                break;

            case ACTUALIZAR_CONTRASEÑA_CORRESPONSAL:
                if (typo.equals(VALIDACION_PIN_CONTRASEÑA)) {
                    procesoFinal(object);
                } else {
                    verCambioContraseña();
                }
                break;

            case SCREEN_ACTUALIZAR_CLIENTE:
                verActualizarCliente(object);
                break;

            case SCREEN_ACTUALIZAR_CORERSPONSAL:
                verActualizarCorresponsal(object);
                break;


        }
    }

    @Override
    public void showAlertDialog(String message, String type, String typo, Object object) {
        switch (type) {
            case SCREEN_ERROR:
                showErrorDialog(message);
                break;
            case SCREEN_CORRECTO:
                showSuccessDialog(message);
                //alertDialog(screen);
                break;
            case SCREEN_CANCELAR_BANCO:
                showWarningDialogBanco(message, typo);
                //alertDialog(screen);
                break;
            case SCREEN_VACIO:
                showVacioDialog(message);
                break;
            case SCREEN_VALIDACION:
                showValidacionDialog(message);
                break;
            case SCREEN_CANCELAR_CORRESPONSAL:
                showWarningDialogCorresponsal(message);
                break;
            case SCREEN_CONFIRMACION:
                showConfrimacion(message, typo, object);
                break;

        }
    }


    @Override
    public void llegaEstado(String estado, String correo) {
        showAlertDialog("Cambio de estado exitoso", SCREEN_CORRECTO, "", "");
        showScreen(SCREEN_MENU_ADMIN, "", "");

    }

    @Override
    public void procesoFinal(Object object) {

        boolean validacionProceso = (boolean) object;

        boolean validacion = contraseña_nueva.getText().toString().equals(contraseña_confirmar_nueva.getText().toString());

        if (!validacionProceso) {
            if (validacion) {
                corresponsal.setContrasenaCorresponsal(contraseña_confirmar_nueva.getText().toString());
                presenter.llevarMenuCorresponsal(corresponsal, "", MODIFICAR_CONTRASEÑA_CORRESPONSAL, "");
            } else {
                showAlertDialog("Contraseña Actual Incorrecta, verificala", SCREEN_VALIDACION, "", "");
            }
        } else {
        }

    }


    @Override
    public void verConsultaCorresponsal(Object object) {
        setContentView(R.layout.admin_confirmar_estado_corresponsal);
        isLoginPantalla = false;

        volver = findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreen(SCREEN_MENU_ADMIN, "", "");
            }
        });

        TextView txt_nombre_confirmacion_corresponsal = findViewById(R.id.txt_nombre_confirmacion_corresponsal);
        TextView txt_numero_nit_confirmacion_corresponsal = findViewById(R.id.txt_numero_nit_confirmacion_corresponsal);
        TextView txt_correo_confirmacion_corresponsal = findViewById(R.id.txt_correo_confirmacion_corresponsal);
        TextView txt_saldo_confirmacion_corresponsal = findViewById(R.id.txt_saldo_confirmacion_corresponsal);
        MaterialButton btn_confirmar_estado_datos_corresponsal = findViewById(R.id.btn_confirmar_estado_datos_corresponsal);


        //corresponsal = (Corresponsal) object;
        ArrayList<Corresponsal> listaCorresponsal = (ArrayList<Corresponsal>) object;
        corresponsal = listaCorresponsal.get(0);

        txt_nombre_confirmacion_corresponsal.setText(corresponsal.getNombreCorresponsal());
        txt_numero_nit_confirmacion_corresponsal.setText(corresponsal.getNitCorresponsal());
        txt_correo_confirmacion_corresponsal.setText(corresponsal.getCorreoCorresponsal());
        txt_saldo_confirmacion_corresponsal.setText(formatNumber(corresponsal.getSaldoCorresponsal()));


        //corresponsal = (Corresponsal) object;


        String estado = HABILITADO;
        if (corresponsal.getEstadoCorresponsal().equals(HABILITADO)) {
            btn_confirmar_estado_datos_corresponsal.setText("DESHABILITAR");
            estado = DESHABILITADO;

        }

        String finalEstado = estado;
        btn_confirmar_estado_datos_corresponsal.setOnClickListener(view -> {
            //
            presenter.modificarEstado(finalEstado, corresponsal.getCorreoCorresponsal());
        });
        //corresponsal = new Corresponsal();
    }

    /**
     * POPUP
     */

    public void showPopupCorresponsal(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.mas_usuario);
        popup.show();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.mas_admin);
        popup.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.actualizarCorresponsal:
                showScreen(CONSULTAR_CORRESPONSAL, "", SCREEN_ACTUALIZAR_CORERSPONSAL);
                return true;
            case R.id.actualizarClientes:
                showScreen(CONSULTAR_CLIENTE, "", SCREEN_ACTUALIZAR_CLIENTE);

                return true;
            case R.id.salirApp:
                showAlertDialog("¿Seguro que deseas cerrar sesion?", SCREEN_CANCELAR_BANCO, SCREEN_CANCELAR_BANCO, "");
                return true;
            case R.id.actualizarDatos:
                showScreen(ACTUALIZAR_CONTRASEÑA_CORRESPONSAL, "", "");
                return true;
            case R.id.crearCliente:
                showScreen(CREAR_CLIENTE, "", SCREEN_MENU_CORRESPONSAL);
                return true;

            case R.id.salirLogin:
                showAlertDialog("¿Seguro que deseas cerrar sesion?", SCREEN_CANCELAR_BANCO, SCREEN_CANCELAR_CORRESPONSAL, "");
                return true;
            default:
                return false;
        }
    }

    /**
     * ----
     */

    @Override
    public void arrayListCorresponsal(ArrayList<Corresponsal> listaCorresponsal) {

        nombre_banner = findViewById(R.id.nameUser);
        cuenta_banner = findViewById(R.id.saldoUser);
        saldo_banner = findViewById(R.id.stateUser);


        nombre_banner.setText(listaCorresponsal.get(0).getNombreCorresponsal());
        //cuenta_banner.setText(listaCorresponsal.get(0).getCuentaCorresponsal());
        cuenta_banner.setText(listaCorresponsal.get(0).getCuentaCorresponsal());
        saldo_banner.setText(formatNumber(listaCorresponsal.get(0).getSaldoCorresponsal()));

    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (listadoClienteAdaptadorCorresponsal != null) {
            listadoClienteAdaptadorCorresponsal.filtradoCorresponsal(s);
        } else if (listadoClienteAdaptadorCliente != null){
            listadoClienteAdaptadorCliente.filtradoCliente(s);
        } else {
            listadoClienteAdaptadorTransaccion.filtradoTransaccion(s);
        }
        return false;
    }

    @Override
    public void arrayListAdmin(ArrayList<Banco> listaBanco) {

        nombre_banner_banco = findViewById(R.id.nameUser);
        rango_banner_banco = findViewById(R.id.stateUser);

        String admin = "Yeison Ramirez";
        String adminS = "ADMINISTRADOR";
        nombre_banner_banco = findViewById(R.id.nameUser);
        rango_banner_banco = findViewById(R.id.stateUser);
        nombre_banner_banco.setText(admin);
        rango_banner_banco.setText(adminS);
    }

    @Override
    public void verListadoClientes() {
        setContentView(R.layout.admin_listado_clientes);
        isLoginPantalla = false;
        txtBuscar = findViewById(R.id.txtBuscar);
        txtBuscar.setQueryHint(Html.fromHtml("<font color = #7A7D7D>" + getResources().getString(R.string.buscador_cliente) + "</font>"));
        txtBuscar.setOnQueryTextListener(this);
        listadoClientes = findViewById(R.id.reciclarClientes);
        volver = findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreen(SCREEN_MENU_ADMIN, "", "");
            }
        });
        presenter.pedirListaClientes();

    }

    @Override
    public void listadoClientesArray(ArrayList<Clientes> listaClientes) {
        ArrayList<Clientes> listaArrayContactos;
        LinearLayoutManager linearLayoutManager;
        listadoClienteAdaptadorCliente = new ListadoClienteAdaptadorCliente(this, listaClientes);
        listadoClientes.setAdapter(listadoClienteAdaptadorCliente);
        linearLayoutManager = new LinearLayoutManager(this);
        listadoClientes.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void listadoCorresponsalArray(ArrayList<Corresponsal> listaCorresponsal) {
        ArrayList<Corresponsal> listaArrayContactos;
        LinearLayoutManager linearLayoutManager;

        listadoClienteAdaptadorCorresponsal = new ListadoClienteAdaptadorCorresponsal(this, listaCorresponsal, this);
        listadoCorresponsal.setAdapter(listadoClienteAdaptadorCorresponsal);
        linearLayoutManager = new LinearLayoutManager(this);
        listadoCorresponsal.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void listadoTransaccionArray(ArrayList<Transacciones> listaTransacciones) {
        ArrayList<Transacciones> listaArrayContactos;
        LinearLayoutManager linearLayoutManager;

        listadoClienteAdaptadorTransaccion = new ListadoClienteAdaptadorTransaccion(this, listaTransacciones, this);
        listadoTransacciones.setAdapter(listadoClienteAdaptadorTransaccion);
        linearLayoutManager = new LinearLayoutManager(this);
        listadoTransacciones.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void verConfirmarProcesoTarjeta(Object object) {
        setContentView(R.layout.confirmar_proceso_tarjeta);
        isLoginPantalla = false;
        nombreClienteConfirmacion = findViewById(R.id.nombreClienteConfirmacion);
        montoConfirmacion = findViewById(R.id.montoConfirmacion);
        numeroCuotas = findViewById(R.id.numeroCuotas);
        numeroTarjetaConfirmacion = findViewById(R.id.numeroTarjetaConfirmacion);
        tipoTarjeta = findViewById(R.id.tipoTarjeta);

        clientes = (Clientes) object;


        nombreClienteConfirmacion.setText(clientes.getNombre_cliente().toUpperCase());
        montoConfirmacion.setText(toStringThousandAmount(Integer.parseInt(transacciones.getMontoTransaccion())));
        numeroCuotas.setText(transacciones.getNumeroCuotasTransaccion());
        String newCuenta = clientes.getNumero_cuenta_cliente();
        String newCuenta2 = clientes.getNumero_cuenta_cliente();
        // newCuenta = newCuenta.substring(0, 4) + " " + newCuenta.substring(4, 8) + " " + newCuenta.substring(8, 12) + " " + newCuenta.substring(12, 16);
        newCuenta2 = "**** **** **** " + newCuenta2.substring(14, 19) ;
        numeroTarjetaConfirmacion.setText(newCuenta2);
        tipoTarjeta.setText(clientes.getTipo_cuenta_cliente());


        btn_confirmar_confirmacion_datos_cliente_proceso = findViewById(R.id.btn_confirmar_confirmacion_datos_cliente_proceso);
        btn_confirmar_confirmacion_datos_cliente_proceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientes.isPin()) {
                    presenter.llevarMenuCorresponsal("", clientes, SCREEN_PIN, PAGO_TARJETA);
                } else {
                    showAlertDialog("Correcto", SCREEN_CORRECTO, "", "");
                }
            }
        });
        btn_cancelar_confirmacion_datos_cliente_proceso = findViewById(R.id.btn_cancelar_confirmacion_datos_cliente_proceso);
        btn_cancelar_confirmacion_datos_cliente_proceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });
    }

    @Override
    public void verConfirmarProcesoDeposito(Object object) {
        setContentView(R.layout.confirmar_proceso_deposito);
        isLoginPantalla = false;
        nombreClienteConfirmacionDepositoEnvia = findViewById(R.id.nombreClienteConfirmacionDepositoEnvia);
        nombreClienteConfirmacionDepositoRecibe = findViewById(R.id.nombreClienteConfirmacionDepositoRecibe);
        montoConfirmacionDeposito = findViewById(R.id.montoConfirmacionDeposito);
        btn_confirmar_confirmacion_datos_cliente_proceso_deposito = findViewById(R.id.btn_confirmar_confirmacion_datos_cliente_proceso_deposito);
        btn_cancelar_confirmacion_datos_cliente_proceso_deposito = findViewById(R.id.btn_cancelar_confirmacion_datos_cliente_proceso_deposito);


        //Seteamos
        transacciones = (Transacciones) object;
        nombreClienteConfirmacionDepositoEnvia.setText(transacciones.getNombreTansaccionEnvia().toUpperCase());
        nombreClienteConfirmacionDepositoRecibe.setText(transacciones.getRecibeTransaccion());
        montoConfirmacionDeposito.setText(formatNumber(transacciones.getMontoTransaccion()));


        btn_confirmar_confirmacion_datos_cliente_proceso_deposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.llevarMenuCorresponsal(transacciones, clientes, PROCESO_FINALIZADO_DEPOSITO, "");


            }
        });


        btn_cancelar_confirmacion_datos_cliente_proceso_deposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });


    }

    @Override
    public void verConfirmarProcesoRetiro(Object object) {
        setContentView(R.layout.confirmar_proceso_retiro);
        isLoginPantalla = false;
        nombreClienteConfirmacionRetiro = findViewById(R.id.nombreClienteConfirmacionRetiro);
        numeroCuentaRetiro = findViewById(R.id.numereCuentaRetiro);
        montoConfirmacionRetiro = findViewById(R.id.montoConfirmacionRetiro);
        btn_confirmar_confirmacion_datos_cliente_proceso_retiro = findViewById(R.id.btn_confirmar_confirmacion_datos_cliente_proceso_retiro);
        btn_cancelar_confirmacion_datos_cliente_proceso_retiro = findViewById(R.id.btn_cancelar_confirmacion_datos_cliente_proceso_retiro);


        //Seteamos
        transacciones = (Transacciones) object;
        nombreClienteConfirmacionRetiro.setText(transacciones.getNombreTansaccionEnvia().toUpperCase());
        montoConfirmacionRetiro.setText(toStringThousandAmount(Integer.parseInt(transacciones.getMontoTransaccion())));


        btn_confirmar_confirmacion_datos_cliente_proceso_retiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.llevarMenuCorresponsal(transacciones, clientes, SCREEN_PIN, RETIRO);
                // presenter.llevarMenuCorresponsal(transacciones, clientes, PROCESO_FINALIZADO_RETIRO, "");

            }
        });


        btn_cancelar_confirmacion_datos_cliente_proceso_retiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });


    }


    ArrayList<Menu> listaMenu;
    ArrayList<Menu> listaMenuAdmin;
    MenuAdaptadorCorresponsal menuCorresponsal;
    MenuAdaptadorBanco menuBanco;

    @Override
    public void verMenuPrincipal() {
        setContentView(R.layout.actividad_corresponsal);
        presenter.corresponsalPedirArrayList();
        isLoginPantalla = false;

        RecyclerView recyclerView = findViewById(R.id.reciclerMenuCorresponsal);
        listaMenu = new ArrayList<>();

        listaMenu.add(new Menu(PAGO_TARJETA, PAGO_TARJETA_URL));
        listaMenu.add(new Menu(TRANSFERENCIA, TRANSFERENCIA_URL));
        listaMenu.add(new Menu(DEPOSITO, DEPOSITO_URL));
        listaMenu.add(new Menu(RETIRO, RETIRO_URL));
        listaMenu.add(new Menu(HISTORIAL_TRANSACCIONES, HISTORIAL_TRANSACCIONES_URL));
        listaMenu.add(new Menu(CONSULTA_SALDO, CONSULTA_SALDO_URL));

        menuCorresponsal = new MenuAdaptadorCorresponsal(MainActivity.this, listaMenu, MainActivity.this::clickListener);
        recyclerView.setAdapter(menuCorresponsal);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

    @Override
    public void verMenuAdministrador() {

        setContentView(R.layout.actividad_banco);
        presenter.adminPedirArrayList();
        isLoginPantalla = false;
        RecyclerView recyclerView = findViewById(R.id.reciclerMenuBanco);
        listaMenuAdmin = new ArrayList<>();

        listaMenuAdmin.add(new Menu(CREAR_CLIENTE, CREAR_CLIENTE_URL));
        listaMenuAdmin.add(new Menu(REGISTRAR_CORRESPONSAL, REGISTRAR_CORRESPONSAL_URL));
        listaMenuAdmin.add(new Menu(CONSULTAR_CLIENTE, CONSULTAR_CLIENTE_URL));
        listaMenuAdmin.add(new Menu(CONSULTAR_CORRESPONSAL, CONSULTAR_CORRESPONSAL_URL));
        listaMenuAdmin.add(new Menu(LISTADO_CLIENTES, LISTADO_CLIENTES_URL));
        listaMenuAdmin.add(new Menu(LISTADO_CORRESPONSALES, LISTADO_CORRESPONSALES_URL));

        menuBanco = new MenuAdaptadorBanco(MainActivity.this, listaMenuAdmin, MainActivity.this::clickListener);
        recyclerView.setAdapter(menuBanco);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

    }

    @Override
    public void verCambioContraseña() {
        setContentView(R.layout.actualizar_corresponsal);
        isLoginPantalla = false;
        contraseña_actual = findViewById(R.id.contraseña_actual);
        contraseña_nueva = findViewById(R.id.contraseña_nueva);
        contraseña_confirmar_nueva = findViewById(R.id.contraseña_confirmar_nueva);
        confirmar_actualizacion = findViewById(R.id.confirmar_actualizacion);
        cancelar_actualizacion = findViewById(R.id.cancelar_actualizacion);


        confirmar_actualizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contraseña_actual.getText().toString().isEmpty() && contraseña_nueva.getText().toString().isEmpty() && contraseña_confirmar_nueva.getText().toString().isEmpty()) {
                    showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                } else {
                    corresponsal.setContrasenaCorresponsal(contraseña_actual.getText().toString());
                    presenter.llevarMenuCorresponsal(corresponsal, "", ACTUALIZAR_CONTRASEÑA_CORRESPONSAL, "");
                }


            }
        });

        cancelar_actualizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });


    }

    @Override
    public void verActualizarCliente(Object object) {
        setContentView(R.layout.admin_actualizar_cliente);
        isLoginPantalla = false;
        actualizar_nombre_cliente = findViewById(R.id.actualizar_nombre_cliente);
        actualizar_numero_de_cedula = findViewById(R.id.actualizar_numero_de_cedula);
        actualizar_pin = findViewById(R.id.actualizar_pin);  //gone
        actualizar_pin_confirmar = findViewById(R.id.actualizar_pin_confirmar); //gone
        linerBotonesActualizacion = findViewById(R.id.linerBotonesActualizacion); // gone
        linearbtnActualizar = findViewById(R.id.linearbtnActualizar); //visibity
        btnActualizar = findViewById(R.id.btnActualizar);

        clientes = (Clientes) object;

        actualizar_nombre_cliente.setText(clientes.getNombre_cliente().toUpperCase());
        actualizar_numero_de_cedula.setText(clientes.getCedula_cliente());

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar_pin.setVisibility(View.VISIBLE);
                actualizar_pin_confirmar.setVisibility(View.VISIBLE);
                linerBotonesActualizacion.setVisibility(View.VISIBLE);
                linearbtnActualizar.setVisibility(View.GONE);
            }
        });


        confirmar_actualizacion_cliente = findViewById(R.id.confirmar_actualizacion_cliente);
        confirmar_actualizacion_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = actualizar_pin.getText().toString();
                String pinConfirmacion = actualizar_pin_confirmar.getText().toString();
                int pin1 = actualizar_pin.getText().toString().length();
                int pin2 = actualizar_pin_confirmar.getText().toString().length();

                if (pin1 < 4 && pin2 < 4) {
                    showAlertDialog("El PIN ingresado debe ser de 4 DIGITOS", SCREEN_VACIO, "", "");
                } else {
                    if (pin.equals(pinConfirmacion)) {
                        clientes.setPin_cuenta_cliente(pinConfirmacion);
                        presenter.llevarMenuCorresponsal(clientes, "", SCREEN_ACTUALIZAR_CLIENTE, SCREEN_ACTUALIZAR_CLIENTE);
                    } else {
                        showAlertDialog("PIN no coninciden", SCREEN_VACIO, "", "");
                    }
                }


            }
        });
        cancelar_actualizacion_cliente = findViewById(R.id.cancelar_actualizacion_cliente);
        cancelar_actualizacion_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });


    }

    @Override
    public void verActualizarCorresponsal(Object object) {
        setContentView(R.layout.admin_actualizar_corresponsal);
        isLoginPantalla = false;
        actualizar_nombre_corresponsal = findViewById(R.id.actualizar_nombre_corresponsal);
        actualizar_numero_de_nit = findViewById(R.id.actualizar_numero_de_nit);
        actualizar_correo_electronico = findViewById(R.id.actualizar_correo_electronico);
        actualizar_contraseña = findViewById(R.id.actualizar_contraseña);
        actualizar_contraseña_confirmacion = findViewById(R.id.actualizar_contraseña_confirmacion);

        confirmar_actualizacion_corresponsal = findViewById(R.id.confirmar_actualizacion_corresponsal);
        cancelar_actualizacion_corresponsal = findViewById(R.id.cancelar_actualizacion_corresponsal);
        btn_actualizar_correpsonsal = findViewById(R.id.btn_actualizar_correpsonsal);

        linearbotonesActualizarCorresponsal = findViewById(R.id.linearbotonesActualizarCorresponsal);
        linearbtnActualizarCorresponsal = findViewById(R.id.linearbtnActualizarCorresponsal);

        corresponsal = (Corresponsal) object;

        actualizar_nombre_corresponsal.setText(corresponsal.getNombreCorresponsal().toUpperCase());
        actualizar_numero_de_nit.setText(corresponsal.getNitCorresponsal());
        actualizar_correo_electronico.setText(corresponsal.getCorreoCorresponsal());

        btn_actualizar_correpsonsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizar_contraseña.setVisibility(View.VISIBLE);
                actualizar_contraseña_confirmacion.setVisibility(View.VISIBLE);
                linearbotonesActualizarCorresponsal.setVisibility(View.VISIBLE);
                linearbtnActualizarCorresponsal.setVisibility(View.GONE);

            }
        });
        confirmar_actualizacion_corresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contraseña = actualizar_contraseña.getText().toString();
                String contraseñaConfirmacion = actualizar_contraseña_confirmacion.getText().toString();

                if (contraseña.equals(contraseñaConfirmacion)) {
                    corresponsal.setContrasenaCorresponsal(contraseñaConfirmacion);
                    presenter.llevarMenuCorresponsal(corresponsal, "", SCREEN_ACTUALIZAR_CORERSPONSAL, SCREEN_ACTUALIZAR_CORERSPONSAL);
                } else {
                    showAlertDialog("las Contraseña ingresadas, no coninciden", SCREEN_VACIO, "", "");
                }

            }
        });
        cancelar_actualizacion_corresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });
    }


    @Override
    public void clickListener(Menu menu) {
        switch (menu.getNombreMenu()) {
            case SCREEN_MENU_ADMIN:
                setContentView(R.layout.menu_administrador);
                break;
            case CREAR_CLIENTE:
                verCrearCliente("");
                break;
            case SCREEN_PIN:
                verPIN(menu, "");
                break;
            case SCREEN_PIN_NUEVAMENTE:
                verPINnuevamente();
                break;
            case SCREEN_CONFIRMAR_CLIENTE:
                verConfirmarCliente();
                break;
            case SCREEN_TARJETA_CLIENTE:
                verTarjetaCliente(menu);
                break;
            case CONSULTAR_CLIENTE:
                verConsultarCliente(menu);

                break;
            case REGISTRAR_CORRESPONSAL:
                verRegistrarCorresponsal();

                break;
            case CONSULTAR_CORRESPONSAL:
                verConsultarCorresponsal(menu);

                break;
            case LISTADO_CLIENTES:
                verListadoClientes();

                break;
            case LISTADO_CORRESPONSALES:
                verListadoCorresponsales();

                break;
            case PAGO_TARJETA:
                verPagoTarjeta();

                break;
            case TRANSFERENCIA:
                verTransferencia();

                break;
            case DEPOSITO:
                verDeposito("", "");

                break;
            case RETIRO:
                verRetiro();

                break;
            case HISTORIAL_TRANSACCIONES:
                verListadoTransacciones();

                break;
            case CONSULTA_SALDO:
                verConsultaSaldo();

                break;

            case SCREEN_CONSULTA_CORERSPONSAL:
                verConsultaCorresponsal(menu);
                break;

        }

    }


    public void imagenTarjeta(String tarjeta) {
        View view = LayoutInflater.from(this).inflate(R.layout.tarjeta_crear_cliente, null, false);
        ImageView iv = view.findViewById(R.id.iv_tipo_tarjeta);
        // tarjeta = clientes.getTipo_cuenta_cliente();

        switch (tarjeta) {
            case "American Express":
                tipo_tarjeta.setImageResource(R.drawable.americanexpress);
                break;
            case "VISA":
                tipo_tarjeta.setImageResource(R.drawable.visa);
                break;
            case "Master Card":
                tipo_tarjeta.setImageResource(R.drawable.mastercard);
                break;
            case "UnionPay":
                tipo_tarjeta.setImageResource(R.drawable.unionpay);
                break;
            default:
                break;
        }

    }

    public static String formatNumber(String str) {
        if (!str.trim().isEmpty()) try {
            double value = Double.parseDouble(str);
            DecimalFormat formatter = new DecimalFormat("#,###");
            return formatter.format(value).replace(',', '.');
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String formatText(String str) {
        if (!str.trim().isEmpty()) try {
            double value = Double.parseDouble(str);
            DecimalFormat formatter = new DecimalFormat("####,####,####");
            return formatter.format(value).replace(',', ' ');
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return str;
    }

    //CORRESPONSAL

    Transacciones transacciones = new Transacciones();

    @Override
    public void verPagoTarjeta() {
        setContentView(R.layout.pago_tarjeta);
        isLoginPantalla = false;
        nombreCliente = findViewById(R.id.nombre_cliente_pago_tarjeta);
        ingreseNumeroDeTarjeta = findViewById(R.id.numero_tarjeta_pago_tarjeta);
        ingreseNumeroDeTarjeta.addTextChangedListener(new TextWatcher() {
            char space = ' ';
            String editex = ingreseNumeroDeTarjeta.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove spacing char
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (space == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                // Insert char where needed.
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(space));
                    }
                }
            }
        });
        codigoCvv = findViewById(R.id.cvv_tarjeta_pago_tarjeta);
        mes_vencimiento_pago_tarjeta = findViewById(R.id.mes_vencimiento_pago_tarjeta);
        dia_vencimiento_pago_tarjeta = findViewById(R.id.dia_vencimiento_pago_tarjeta);
        spinner = findViewById(R.id.spinner_cuotas_pago_tarjeta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cuotas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        montoPagar = findViewById(R.id.valor_a_pagar_pago_tarjeta);

        montoPagar.addTextChangedListener(new TextWatcher() {
            String editex = montoPagar.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    montoPagar.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    montoPagar.setText(editex);
                    montoPagar.setSelection(editex.length());
                    montoPagar.addTextChangedListener(this);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        confirmarPagoTarjeta = findViewById(R.id.confirmar_pago_tarjeta);
        cancelarPagoTarjeta = findViewById(R.id.cancelar_pago_tarjeta);

        confirmarPagoTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombreCliente.getText().toString().isEmpty() && montoPagar.getText().toString().isEmpty() && ingreseNumeroDeTarjeta.getText().toString().isEmpty() &&
                        codigoCvv.getText().toString().isEmpty()) {
                    showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                } else {

                    DateFormat df = new SimpleDateFormat("h:mm a, dd/MM/yy"); //definir formato para fecha
                    String fecha_operacion = df.format(Calendar.getInstance().getTime());

                    String fechaexp = mes_vencimiento_pago_tarjeta.getText().toString() + "/" + dia_vencimiento_pago_tarjeta.getText().toString();
                    String cuotas = spinner.getSelectedItem().toString();
                    String guardado = montoPagar.getText().toString().trim();

                    transacciones.setMontoTransaccion(guardado.replace(".", ""));
                    clientes.setNumero_cuenta_cliente(ingreseNumeroDeTarjeta.getText().toString());
                    clientes.setCvv_cuenta_cliente(codigoCvv.getText().toString());
                    clientes.setFecha_expiracion_tarjeta_cliente(fechaexp);
                    transacciones.setNumeroCuotasTransaccion(cuotas);
                    clientes.setNombre_cliente(nombreCliente.getText().toString());
                    transacciones.setFechaTransaccion(fecha_registro);


                    presenter.llevarMenuCorresponsal(clientes, transacciones, PAGO_TARJETA, "");

                }
            }
        });

        cancelarPagoTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });
    }

    @Override
    public void verTransferencia() {
        setContentView(R.layout.transferencia);
        isLoginPantalla = false;
        numeroCedulaTransferir = findViewById(R.id.cedula_a_transferir);
        numeroCedulaTransferir.addTextChangedListener(new TextWatcher() {
            String editex = numeroCedulaTransferir.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    numeroCedulaTransferir.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    numeroCedulaTransferir.setText(editex);
                    numeroCedulaTransferir.setSelection(editex.length());
                    numeroCedulaTransferir.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        numeroCedulaATransferir = findViewById(R.id.cedula_que_transfiere);
        numeroCedulaATransferir.addTextChangedListener(new TextWatcher() {
            String editex = numeroCedulaATransferir.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    numeroCedulaATransferir.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    numeroCedulaATransferir.setText(editex);
                    numeroCedulaATransferir.setSelection(editex.length());
                    numeroCedulaATransferir.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        montoTransferir = findViewById(R.id.monto_a_transferir);
        montoTransferir.addTextChangedListener(new TextWatcher() {
            String editex = montoTransferir.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    montoTransferir.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    montoTransferir.setText(editex);
                    montoTransferir.setSelection(editex.length());
                    montoTransferir.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmarTranferencia = findViewById(R.id.confirmar_transferencia);
        confirmarTranferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeroCedulaTransferir.getText().toString().isEmpty() || numeroCedulaATransferir.getText().toString().isEmpty() ||
                        montoTransferir.getText().toString().isEmpty()) {
                    showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                } else {

                    String num1 = numeroCedulaTransferir.getText().toString();
                    String num2 = numeroCedulaATransferir.getText().toString();

                    String monto = montoTransferir.getText().toString();

                    DateFormat df = new SimpleDateFormat("h:mm a, dd/MM/yy"); //definir formato para fecha
                    String fecha_operacion = df.format(Calendar.getInstance().getTime());

                    transacciones.setEnviaTransaccion(num1);
                    transacciones.setRecibeTransaccion(num2);
                    transacciones.setMontoTransaccion(monto.replace(".", ""));
                    transacciones.setFechaTransaccion(fecha_operacion);
                    transacciones.setNumeroCuotasTransaccion("No aplica");
                    transacciones.setTarjetaTransaccion("No aplica");
                    transacciones.setTipoTransaccion("TRANSFERENCIA");


                    if (num1.equals(num2)) {
                        showAlertDialog("Operación invalida", SCREEN_ERROR, "", "");
                        return;
                    }
                    showAlertDialog("Transferencia tiene un costo de 1.000 pesos que se descontaran directamente de su cuenta WPOSS.\n\n Desea Continuar?\n", SCREEN_CONFIRMACION, TRANSFERENCIA, clientes);
                   // presenter.llevarMenuCorresponsal(clientes, transacciones, TRANSFERENCIA, "");
                }
            }
        });

        cancelarTranferencia = findViewById(R.id.cancelar_transferencia);
        cancelarTranferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });

    }

    @Override
    public void verDeposito(String typo, Object object) {
        setContentView(R.layout.deposito);
        isLoginPantalla = false;
        numeroCedulaDepostador = findViewById(R.id.cedula_a_depositar);
        numeroCedulaDepostador.addTextChangedListener(new TextWatcher() {
            String editex = numeroCedulaDepostador.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    numeroCedulaDepostador.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    numeroCedulaDepostador.setText(editex);
                    numeroCedulaDepostador.setSelection(editex.length());
                    numeroCedulaDepostador.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        numeroCedulaADepositar = findViewById(R.id.cedula_que_deposita);
        numeroCedulaADepositar.addTextChangedListener(new TextWatcher() {
            String editex = numeroCedulaADepositar.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    numeroCedulaADepositar.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    numeroCedulaADepositar.setText(editex);
                    numeroCedulaADepositar.setSelection(editex.length());
                    numeroCedulaADepositar.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        montoDepositar = findViewById(R.id.valor_a_depositar);
        montoDepositar.addTextChangedListener(new TextWatcher() {
            String editex = montoDepositar.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    montoDepositar.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    montoDepositar.setText(editex);
                    montoDepositar.setSelection(editex.length());
                    montoDepositar.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmarDeposito = findViewById(R.id.confirmar_deposito);
        saldo_insuficiente_corresponsal = findViewById(R.id.saldo_insuficiente_corresponsal);

        confirmarDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (numeroCedulaDepostador.getText().toString().isEmpty() || numeroCedulaADepositar.getText().toString().isEmpty() ||
                        montoDepositar.getText().toString().isEmpty()) {
                    showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                } else {


                    String num2 = numeroCedulaDepostador.getText().toString();
                    String num1 = numeroCedulaADepositar.getText().toString();

                    String monto = montoDepositar.getText().toString().replace(".", "");

                    DateFormat df = new SimpleDateFormat("h:mm a, dd/MM/yy"); //definir formato para fecha
                    String fecha_operacion = df.format(Calendar.getInstance().getTime());

                    transacciones.setEnviaTransaccion(num2);
                    transacciones.setRecibeTransaccion(num1);
                    transacciones.setMontoTransaccion(monto);
                    transacciones.setFechaTransaccion(fecha_operacion);
                    transacciones.setNumeroCuotasTransaccion("No aplica");
                    transacciones.setTarjetaTransaccion("No aplica");
                    transacciones.setTipoTransaccion("DEPOSITO");
                    clientes.setCedula_cliente(num2);


                    if (num1.equals(num2)) {
                        showAlertDialog("Operación invalida", SCREEN_ERROR, "", "");
                        return;
                    }
                    presenter.llevarMenuCorresponsal(clientes, transacciones, DEPOSITO, DEPOSITO);
                }

            }
        });
        cancelarDeposito = findViewById(R.id.cancelar_deposito);
        cancelarDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });

        if (typo.equals(DEPOSITO)) {
            corresponsal = (Corresponsal) object;
            saldo_insuficiente_corresponsal.setText("*Saldo insuficiente en el corresponsal cuentas con un monto de $" + formatNumber(corresponsal.getSaldoCorresponsal()));
            return;
        }


    }

    @Override
    public void verRetiro() {
        setContentView(R.layout.retiros);
        isLoginPantalla = false;
        numeroCedulaRetiro = findViewById(R.id.numero_cedula_retiro);
        numeroCedulaRetiro.addTextChangedListener(new TextWatcher() {
            String editex = numeroCedulaRetiro.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    numeroCedulaRetiro.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    numeroCedulaRetiro.setText(editex);
                    numeroCedulaRetiro.setSelection(editex.length());
                    numeroCedulaRetiro.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        montoRetirar = findViewById(R.id.monto_retirar);
        montoRetirar.addTextChangedListener(new TextWatcher() {
            String editex = montoRetirar.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    montoRetirar.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    montoRetirar.setText(editex);
                    montoRetirar.setSelection(editex.length());
                    montoRetirar.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmarRetiro = findViewById(R.id.confirmar_retiro);
        confirmarRetiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeroCedulaRetiro.getText().toString().isEmpty() && montoRetirar.getText().toString().isEmpty()) {
                    showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                } else {
                    clientes.setCedula_cliente(numeroCedulaRetiro.getText().toString());
                    transacciones.setMontoTransaccion(montoRetirar.getText().toString().replace(".", ""));
                    presenter.llevarMenuCorresponsal(clientes, transacciones, RETIRO, "");
                }
            }
        });
        cancelarRetiro = findViewById(R.id.cancelar_retiro);
        cancelarRetiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });
    }


    @Override
    public void verConsultaSaldo() {
        setContentView(R.layout.consulta_saldo);
        isLoginPantalla = false;
        numeroCedulaConsultarSaldo = findViewById(R.id.cedula_a_consultar);
        numeroCedulaConsultarSaldo.addTextChangedListener(new TextWatcher() {
            String editex = numeroCedulaConsultarSaldo.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    numeroCedulaConsultarSaldo.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    numeroCedulaConsultarSaldo.setText(editex);
                    numeroCedulaConsultarSaldo.setSelection(editex.length());
                    numeroCedulaConsultarSaldo.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmarConsultarSaldo = findViewById(R.id.confirmar_consulta);
        confirmarConsultarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!numeroCedulaConsultarSaldo.getText().toString().isEmpty()) {
                    clientes.setCedula_cliente(numeroCedulaConsultarSaldo.getText().toString());
                    presenter.llevarMenuCorresponsal(clientes, "", CONSULTA_SALDO, "");
                } else {
                    showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
                }
            }
        });
        cancelarConsultarSaldo = findViewById(R.id.cancelar_consulta);
        cancelarConsultarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
            }
        });


    }


    //ADMINISTRADOR

    Clientes clientes = new Clientes();


    @Override
    public void verCrearCliente(String typo) {
        setContentView(R.layout.registro_cliente);
        isLoginPantalla = false;
        nombreCrearCliente = findViewById(R.id.txt_nombre_crear_cliente);
        tituloCrearCliente = findViewById(R.id.tituloCrearCliente);
        cedulaCrearCliente = findViewById(R.id.txt_cedula_crear_cliente);
        cedulaCrearCliente.addTextChangedListener(new TextWatcher() {
            String editex = cedulaCrearCliente.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    cedulaCrearCliente.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    cedulaCrearCliente.setText(editex);
                    cedulaCrearCliente.setSelection(editex.length());
                    cedulaCrearCliente.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        saldoCrearCliente = findViewById(R.id.txt_saldo_crear_cliente);
        saldoCrearCliente.addTextChangedListener(new TextWatcher() {
            String editex = saldoCrearCliente.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    saldoCrearCliente.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    saldoCrearCliente.setText(editex);
                    saldoCrearCliente.setSelection(editex.length());
                    saldoCrearCliente.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fondoCrearCliente = findViewById(R.id.fondoCrearCliente);
        cancelarCrearCliente = findViewById(R.id.btn_cancelar_crear_cliente);
        confirmarCrearCliente = findViewById(R.id.btn_confirmar_crear_cliente);
        confirmarCrearCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientes.setNombre_cliente(nombreCrearCliente.getText().toString().toUpperCase());
                clientes.setCedula_cliente(cedulaCrearCliente.getText().toString());
                clientes.setSaldo_cliente((saldoCrearCliente.getText().toString().replace(".","")));
                presenter.llevarMenuAdmin(clientes, CREAR_CLIENTE);


            }
        });

        cancelarCrearCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");

            }
        });

        if (isColorCorresponsal) {
            fondoCrearCliente = findViewById(R.id.fondoCrearCliente);
            fondoCrearCliente.setBackgroundResource(R.drawable.fondosecundario);
            tituloCrearCliente.setTextColor(getResources().getColor(R.color.naranja));
            barraTitulo = findViewById(R.id.barraTitulo);
            barraTitulo.setBackgroundResource(R.color.naranja);
            nombreCrearCliente.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_coolicon_name_naranja, 0, 0, 0);
            cedulaCrearCliente.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cedula_naranja, 0, 0, 0);
            saldoCrearCliente.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_monetizacion_naranja, 0, 0, 0);
            confirmarCrearCliente.setTextColor(getResources().getColor(R.color.white));
            confirmarCrearCliente.setBackgroundColor(getResources().getColor(R.color.naranja));
            cancelarCrearCliente.setTextColor(getResources().getColor(R.color.naranja));
            cancelarCrearCliente.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
        }


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void verPIN(Object object, String typo) {
        setContentView(R.layout.ingresar_pin);
        isLoginPantalla = false;
        pinCliente = findViewById(R.id.txt_ingrese_pin);
        confirmarPin = findViewById(R.id.btn_aceptar_pin);
        MaterialButton cancelarPin = (MaterialButton) findViewById(R.id.btn_cancelar_pin);
        // cancelarPin = findViewById(R.id.btn_cancelar_pin);

        clientes = (Clientes) object;
        confirmarPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!clientes.isPin()) {

                    boolean pin = clientes.getPin_cuenta_cliente().equals(pinCliente.getText().toString());

                    if (pin) {
                        if (typo.equals(PAGO_TARJETA)) {
                            int montoTransaccion = Integer.parseInt(transacciones.getMontoTransaccion());
                            int cuotas = Integer.parseInt(transacciones.getNumeroCuotasTransaccion());
                            int saldoCliente = Integer.parseInt(clientes.getSaldo_cliente());
                            int restaValor = montoTransaccion / cuotas;

                            if (saldoCliente >= restaValor) {
                                presenter.llevarMenuCorresponsal(clientes, transacciones, PROCESO_FINAL_TARJETA, "");
                            } else {
                                showAlertDialog("Saldo insuficiente", SCREEN_VALIDACION, "", "");
                                showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
                            }


                        }
                        if (typo.equals(TRANSFERENCIA)) {
                            String saldoCliente2 = clientes.getSaldo_cliente();
                            String montoTransa = transacciones.getMontoTransaccion();

                            if (Integer.parseInt(saldoCliente2.replace(".", "")) >= (Integer.parseInt(montoTransa.replace(".", "")) + 1000)) {
                                if (Integer.parseInt(saldoCliente2.replace(".","")) > 0) {
                                    presenter.llevarMenuCorresponsal(clientes, transacciones, PROCESO_FINAL_TRANSACCION, "");
                                }
                            } else {
                                showAlertDialog("Saldo insuficiente", SCREEN_VALIDACION, "", "");
                                showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
                            }
                        }

                        if (typo.equals(RETIRO)) {
                            int saldoCliente = Integer.parseInt(clientes.getSaldo_cliente());
                            int montoTran = Integer.parseInt(transacciones.getMontoTransaccion());

                            if (saldoCliente >= (montoTran + 2000)) {
                                presenter.llevarMenuCorresponsal(clientes, transacciones, PROCESO_FINALIZADO_RETIRO, "");
                            } else {
                                showAlertDialog("Saldo insuficiente", SCREEN_VALIDACION, "", "");
                                showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
                            }
                        }

                        if (typo.equals(SCREEN_ACTUALIZAR_CORERSPONSAL)) {
                            presenter.llevarMenuCorresponsal("", "", SCREEN_ACTUALIZAR_CORERSPONSAL, "");
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Pin Incorrecto, Porfavor verifica tus credenciales", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    clientes.setPin_cuenta_cliente(pinCliente.getText().toString());
                    presenter.llevarMenuAdmin(clientes, SCREEN_PIN);
                }


            }
        });

        cancelarPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (clientes.isPin()) {
                    showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
                } else {
                    showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_CORRESPONSAL, "", "");
                }
            }
        });

        if (isColorCorresponsal) {
            fondoCrearCliente = findViewById(R.id.fondoCrearCliente);
            fondoCrearCliente.setBackgroundResource(R.drawable.fondosecundario);
            barraTitulo = findViewById(R.id.barraTitulo);
            barraTitulo.setBackgroundResource(R.color.naranja);
            pinCliente = findViewById(R.id.txt_ingrese_pin);
            pinCliente.setBackgroundResource(R.drawable.fondo_button_naranja_borde_blanco);
            confirmarPin.setTextColor(getResources().getColor(R.color.white));
            confirmarPin.setBackgroundColor(getResources().getColor(R.color.naranja));
            cancelarPin.setTextColor(getResources().getColor(R.color.naranja));
            cancelarPin.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
        }
    }

    @Override
    public void verPINnuevamente() {
        setContentView(R.layout.ingresar_nuevamente_pin);
        isLoginPantalla = false;
        txt_ingrese_nuevamente_pin = findViewById(R.id.txt_ingrese_nuevamente_pin);
        btn_aceptar_pin = findViewById(R.id.btn_aceptar_pin);
        btn_cancelar_pin = findViewById(R.id.btn_cancelar_pin);

        btn_aceptar_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_ingrese_nuevamente_pin.getText().toString().isEmpty()) {
                    showAlertDialog("Campo Vacio", SCREEN_VACIO, "", "");
                } else if (txt_ingrese_nuevamente_pin.getText().toString().equals(clientes.getPin_cuenta_cliente())) {
                    presenter.llevarMenuAdmin(clientes, SCREEN_PIN_NUEVAMENTE);
                    //showAlertDialog("PIN Correcto", SCREEN_CORRECTO, "", "");
                } else {
                    showAlertDialog("PIN no conincide", SCREEN_ERROR, "", "");
                }

            }
        });

        btn_cancelar_pin = findViewById(R.id.btn_cancelar_pin);
        btn_cancelar_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });

        if (isColorCorresponsal) {
            fondoCrearCliente = findViewById(R.id.fondoCrearCliente);
            fondoCrearCliente.setBackgroundResource(R.drawable.fondosecundario);
            barraTitulo = findViewById(R.id.barraTitulo);
            barraTitulo.setBackgroundResource(R.color.naranja);
            txt_ingrese_nuevamente_pin.setBackgroundResource(R.drawable.fondo_button_naranja_borde_blanco);
            btn_aceptar_pin.setTextColor(getResources().getColor(R.color.white));
            btn_aceptar_pin.setBackgroundColor(getResources().getColor(R.color.naranja));
            btn_cancelar_pin.setTextColor(getResources().getColor(R.color.naranja));
            btn_cancelar_pin.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
        }

    }

    DateFormat df = new SimpleDateFormat("MM/yy"); //definir formato para fecha
    String fecha_registro = df.format(Calendar.getInstance().getTime()); //obtener fecha
    String anno = fecha_registro.substring(3, 5);
    int newAnno = Integer.parseInt(anno) + 5;
    String fechaExpiracion = fecha_registro.substring(0, 3) + newAnno;


    @SuppressLint("WrongViewCast")
    @Override
    public void verConfirmarCliente() {
        setContentView(R.layout.admin_confirmar_cliente);
        isLoginPantalla = false;
        txt_nombre_confirmacion = findViewById(R.id.txt_nombre_confirmacion);
        txt_numero_cedular_confirmacion = findViewById(R.id.txt_numero_cedular_confirmacion);
        txt_saldo_confirmacion = findViewById(R.id.txt_saldo_confirmacion);
        relativeLayoutCliente = findViewById(R.id.relativeLayoutCliente);
        relativeLayoutCliente2 = findViewById(R.id.relativeLayoutCliente2);
        txt_nombre_confirmacion.setText(clientes.getNombre_cliente());
        txt_numero_cedular_confirmacion.setText(clientes.getCedula_cliente());
        txt_saldo_confirmacion.setText(formatNumber(clientes.getSaldo_cliente()));

        btn_confirmar_confirmacion_datos_cliente = findViewById(R.id.btn_confirmar_confirmacion_datos_cliente);
        btn_cancelar_confirmacion_datos_cliente = findViewById(R.id.btn_cancelar_confirmacion_datos_cliente);

        String newCuenta = Varios.generateCardCliente(clientes, 16);
        newCuenta = newCuenta.substring(0, 4) + " " + newCuenta.substring(4, 8) + " " +
                newCuenta.substring(8, 12) + " " + newCuenta.substring(12, 16);
        clientes.setNumero_cuenta_cliente(newCuenta);
        String tarjeta = clientes.getNumero_cuenta_cliente();
        clientes.setTipo_cuenta_cliente(Varios.TipoTarjeta(tarjeta));
        clientes.setCvv_cuenta_cliente(Varios.generateCardCvv(clientes, 3));
        clientes.setFecha_expiracion_tarjeta_cliente(fechaExpiracion);


//        txt_numero_cedular_confirmacion.setText(Varios.generateCardCliente(clientes,16));
//        String tarjeta = clientes.getNumero_cuenta_cliente();
//        System.out.println("TARJETTA "+tarjeta);
//        tipo_tarjeta.setText(Varios.TipoTarjeta(tarjeta));

        btn_confirmar_confirmacion_datos_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.llevarMenuAdmin(clientes, SCREEN_CONFIRMAR_CLIENTE);
            }
        });

        btn_cancelar_confirmacion_datos_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });


        if (isColorCorresponsal) {
            fondoCrearCliente = findViewById(R.id.fondoCrearCliente);
            fondoCrearCliente.setBackgroundResource(R.drawable.fondosecundario);
            relativeLayoutCliente.setBackgroundResource(R.drawable.fondo_button_naranja);
            relativeLayoutCliente2.setBackgroundResource(R.drawable.fondo_button_naranja_borde_blanco);
            barraTitulo = findViewById(R.id.barraTitulo);
            barraTitulo.setBackgroundResource(R.color.naranja);
            txt_numero_cedular_confirmacion.setTextColor(getResources().getColor(R.color.naranja));
            txt_saldo_confirmacion.setTextColor(getResources().getColor(R.color.naranja));
            signo_dinero = findViewById(R.id.signo_dinero);
            signo_dinero.setTextColor(getResources().getColor(R.color.naranja));
            btn_confirmar_confirmacion_datos_cliente.setTextColor(getResources().getColor(R.color.white));
            btn_confirmar_confirmacion_datos_cliente.setBackgroundColor(getResources().getColor(R.color.naranja));
            btn_cancelar_confirmacion_datos_cliente.setTextColor(getResources().getColor(R.color.naranja));
            btn_cancelar_confirmacion_datos_cliente.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));
        }

    }

    @Override
    public void verTarjetaCliente(Object object) {
        setContentView(R.layout.tarjeta_crear_cliente);
        isLoginPantalla = false;

        if (!object.equals("")) {
            ArrayList<Clientes> listaCliente = (ArrayList<Clientes>) object;
            clientes = listaCliente.get(0);
        }

        nombre_tarjeta = findViewById(R.id.nombre_tarjeta);
        saldo_tarjeta = findViewById(R.id.saldo_tarjeta);
        num_cuenta_tarjeta = findViewById(R.id.num_cuenta_tarjeta);
        cvv_tarjeta = findViewById(R.id.cvv_tarjeta);
        fecha_tarjeta = findViewById(R.id.fecha_tarjeta);
        tipo_tarjeta = findViewById(R.id.iv_tipo_tarjeta);
        btn_aceptar_tarjeta = findViewById(R.id.btn_aceptar_tarjeta);
        btn_aceptar_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isColorCorresponsal) {
                    showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
                } else {
                    showScreen(SCREEN_MENU_ADMIN, "", "");
                }

            }
        });

        //  tipo_tarjeta = findViewById(R.id.tipo_tarjeta);


        nombre_tarjeta.setText(clientes.getNombre_cliente());
        saldo_tarjeta.setText(formatNumber(clientes.getSaldo_cliente()));


        num_cuenta_tarjeta.setText(clientes.getNumero_cuenta_cliente());

        String tarjeta = clientes.getTipo_cuenta_cliente();
        imagenTarjeta(tarjeta);
        //  tipo_tarjeta.setText(clientes.getTipo_cuenta_cliente());
        cvv_tarjeta.setText(clientes.getCvv_cuenta_cliente());
        fecha_tarjeta.setText(clientes.getFecha_expiracion_tarjeta_cliente());


        if (isColorCorresponsal) {
            fondoCrearCliente = findViewById(R.id.fondoCrearCliente);
            fondoCrearCliente.setBackgroundResource(R.drawable.fondosecundario);
            barraTitulo = findViewById(R.id.barraTitulo);
            barraTitulo.setBackgroundResource(R.color.naranja);
            btn_aceptar_tarjeta.setTextColor(getResources().getColor(R.color.naranja));
            btn_aceptar_tarjeta.setBackgroundColor(getResources().getColor(R.color.white));
            btn_aceptar_tarjeta.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.naranja)));

        }


    }

    @Override
    public void verConsultarCliente(Object object) {
        setContentView(R.layout.admin_consulta_cliente);
        isLoginPantalla = false;
        EditText cedula_a_consultar = findViewById(R.id.cedula_a_consultar);
        cedula_a_consultar.addTextChangedListener(new TextWatcher() {
            String editex = cedula_a_consultar.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    cedula_a_consultar.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    cedula_a_consultar.setText(editex);
                    cedula_a_consultar.setSelection(editex.length());
                    cedula_a_consultar.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        MaterialButton confirmar_consulta_cliente = findViewById(R.id.confirmar_consulta_cliente);
        MaterialButton cancelar_consulta_cliente = findViewById(R.id.cancelar_consulta_cliente);

        confirmar_consulta_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (object.equals(SCREEN_ACTUALIZAR_CLIENTE)) {
                    clientes.setCedula_cliente(cedula_a_consultar.getText().toString());

                    presenter.llevarMenuCorresponsal(clientes, "", SCREEN_ACTUALIZAR_CLIENTE, "");


                } else {
                    clientes.setCedula_cliente(cedula_a_consultar.getText().toString());

                    presenter.llevarMenuAdmin(clientes, CONSULTAR_CLIENTE);
                }
            }


        });
        cancelar_consulta_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });
    }

    @Override
    public void verConsultarCorresponsal(Object object) {
        setContentView(R.layout.admin_consulta_corresponsal);
        isLoginPantalla = false;
        EditText nit_a_consultar = findViewById(R.id.nit_a_consultar);
        nit_a_consultar.addTextChangedListener(new TextWatcher() {
            String editex = nit_a_consultar.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    nit_a_consultar.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    nit_a_consultar.setText(editex);
                    nit_a_consultar.setSelection(editex.length());
                    nit_a_consultar.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        MaterialButton confirmar_consulta_corresponsal = findViewById(R.id.confirmar_consulta_corresponsal);
        MaterialButton cancelar_consulta_corresponsal = findViewById(R.id.cancelar_consulta_corresponsal);

        confirmar_consulta_corresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (object.equals(SCREEN_ACTUALIZAR_CORERSPONSAL)) {
                    corresponsal.setNitCorresponsal(nit_a_consultar.getText().toString());

                    presenter.llevarMenuCorresponsal(corresponsal, "", SCREEN_ACTUALIZAR_CORERSPONSAL, "");


                } else {
                    corresponsal.setNitCorresponsal(nit_a_consultar.getText().toString());
                    presenter.llevarMenuAdmin(corresponsal, CONSULTAR_CORRESPONSAL);
                }
            }


        });
        cancelar_consulta_corresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });
    }

    @Override
    public void verListadoCorresponsales() {
        setContentView(R.layout.admin_listado_corresponsales);
        txtBuscar = findViewById(R.id.txtBuscar);
        txtBuscar.setQueryHint(Html.fromHtml("<font color = #7A7D7D>" + getResources().getString(R.string.buscador_corresponsal) + "</font>"));
        txtBuscar.setOnQueryTextListener(this);
        isLoginPantalla = false;
        listadoCorresponsal = findViewById(R.id.listadoCorresponsal);
        volver = findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreen(SCREEN_MENU_ADMIN, "", "");
            }
        });
        presenter.pedirListaCorresponsal();
    }

    @Override
    public void verListadoTransacciones() {
        setContentView(R.layout.admin_listado_transacciones);
        txtBuscar = findViewById(R.id.txtBuscar);
        txtBuscar.setQueryHint(Html.fromHtml("<font color = #7A7D7D>" + getResources().getString(R.string.buscador_transaccion) + "</font>"));
        txtBuscar.setOnQueryTextListener(this);
        isLoginPantalla = false;
        listadoTransacciones = findViewById(R.id.listadoTransacciones);
        volver = findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
            }
        });
        presenter.pedirListaTransaccion();
    }

    @Override
    public void respuestaValidarView(boolean validar) {

        if (validar == true) {
            showMessage("Corresponsal registrado");
            verMenuPrincipal();
            //verLogin();
        } else {
            showMessage("Error al registrar");
        }
    }

    @Override
    public void verPreLogin() {
        setContentView(R.layout.pre_login);
        isLoginPantalla = true;
        Button btn_inicia_sesion_prelogin = findViewById(R.id.btn_inicia_sesion_prelogin);

        btn_inicia_sesion_prelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreen(SCREEN_LOGIN, "", "");
            }
        });

    }

    @Override
    public void verLogin() {
        setContentView(R.layout.login);
        isLoginPantalla = false;
        Corresponsal corresponsal;
        corresponsal = new Corresponsal();
        EditText correo_login = findViewById(R.id.correo_login);
        EditText contrasena_login = findViewById(R.id.password_login);
        Button btn_inicia_sesion = findViewById(R.id.btn_inicia_sesion);
        ImageView imagenMoneda = findViewById(R.id.imagenMoneda);
        imagenMoneda.animate().rotationY(360).setDuration(6000);
        imagenMoneda.animate().rotation(360).setDuration(8000);
        imagenMoneda.animate().translationZ(120).setDuration(10000);


        btn_inicia_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = correo_login.getText().toString();
                String contraseña = contrasena_login.getText().toString();

                corresponsal.setCorreoCorresponsal(correo);
                corresponsal.setContrasenaCorresponsal(contraseña);
                validacionLogin();

                //showScreen(SCREEN_MENU_PRINCIPAL);
                //presenter.llevarClase(corresponsal, SCREEN_LOGIN);
            }
        });

    }

    Corresponsal corresponsal = new Corresponsal();

    @Override
    public void verRegistrarCorresponsal() {
        setContentView(R.layout.registro_corresponsal);
        isLoginPantalla = false;
        EditText nombre_registrar = findViewById(R.id.nombre_registrar);
        EditText nit_registrar = findViewById(R.id.cedula_registrar);
        nit_registrar.addTextChangedListener(new TextWatcher() {
            String editex = nit_registrar.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editex)){
                    nit_registrar.removeTextChangedListener(this);
                    String replace = charSequence.toString().replaceAll("[Rp. ]", "");
                    if (!replace.isEmpty()){
                        editex = formatNumber(replace);
                    }
                    else {
                        editex = "";
                    }
                    nit_registrar.setText(editex);
                    nit_registrar.setSelection(editex.length());
                    nit_registrar.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        EditText correo_registrar = findViewById(R.id.correo_registrar);
        EditText contrasena_registrar = findViewById(R.id.contrasena_registrar);

        Button btn_registrarse = findViewById(R.id.btn_registrarse);
        Button btn_cancelar = findViewById(R.id.btn_cancelar);


        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saldo = "1000000";
                corresponsal.setSaldoCorresponsal(saldo);

                corresponsal.setNombreCorresponsal(nombre_registrar.getText().toString().toUpperCase());
                corresponsal.setNitCorresponsal(nit_registrar.getText().toString());
                corresponsal.setCorreoCorresponsal(correo_registrar.getText().toString());
                corresponsal.setContrasenaCorresponsal(contrasena_registrar.getText().toString());

                presenter.llevarMenuAdmin(corresponsal, REGISTRAR_CORRESPONSAL);
            }
        });


        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });

    }

    @Override
    public void verConfirmarCorresponsal() {
        setContentView(R.layout.admin_confirmar_corresponsal);
        isLoginPantalla = false;
        txt_nombre_confirmacion_corresponsal = findViewById(R.id.txt_nombre_confirmacion_corresponsal);
        txt_numero_nit_confirmacion_corresponsal = findViewById(R.id.txt_numero_nit_confirmacion_corresponsal);
        txt_saldo_confirmacion_corresponsal = findViewById(R.id.txt_saldo_confirmacion_corresponsal);
        txt_correo_confirmacion_corresponsal = findViewById(R.id.txt_correo_confirmacion_corresponsal);


        txt_nombre_confirmacion_corresponsal.setText(corresponsal.getNombreCorresponsal());
        txt_numero_nit_confirmacion_corresponsal.setText(corresponsal.getNitCorresponsal());
        txt_saldo_confirmacion_corresponsal.setText(formatNumber(corresponsal.getSaldoCorresponsal()));
        txt_correo_confirmacion_corresponsal.setText(corresponsal.getCorreoCorresponsal());

        btn_confirmar_confirmacion_datos_corresponsal = findViewById(R.id.btn_confirmar_confirmacion_datos_corresponsal);
        btn_cancelar_confirmacion_datos_corresponsal = findViewById(R.id.btn_cancelar_confirmacion_datos_corresponsal);


        String newCuenta = Varios.generateCardCorresponsal(corresponsal, 16);
        newCuenta = newCuenta.substring(0, 4) + " " + newCuenta.substring(4, 8) + " " +
                newCuenta.substring(8, 12) + " " + newCuenta.substring(12, 16);
        corresponsal.setCuentaCorresponsal(newCuenta);


        corresponsal.setEstadoCorresponsal(DESHABILITADO);

        btn_confirmar_confirmacion_datos_corresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.llevarMenuAdmin(corresponsal, SCREEN_CONFIRMAR_CORERSPONSAL);
            }
        });

        btn_cancelar_confirmacion_datos_corresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("¿Seguro que deseas cancelar la operacion actual?", SCREEN_CANCELAR_BANCO, "", "");
            }
        });
    }

    @Override
    public void verConsultadeSaldo(Object object) {
        setContentView(R.layout.confirmar_consulta_saldo);
        isLoginPantalla = false;
        txt_nombre_confirmacion_saldo = findViewById(R.id.txt_nombre_confirmacion_saldo);
        txt_numero_cedular_confirmacion_saldo = findViewById(R.id.txt_numero_cedular_confirmacion_saldo);
        txt_saldo_confirmacion_saldo = findViewById(R.id.txt_saldo_confirmacion_saldo);
        btn_aceptar_consulta_saldo = findViewById(R.id.btn_aceptar_consulta_saldo);

        clientes = (Clientes) object;
        txt_nombre_confirmacion_saldo.setText(clientes.getNombre_cliente());
        txt_numero_cedular_confirmacion_saldo.setText(clientes.getCedula_cliente());
        txt_saldo_confirmacion_saldo.setText(formatNumber(clientes.getSaldo_cliente()));


        btn_aceptar_consulta_saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.llevarMenuCorresponsal(clientes, "", PROCESO_FINAL_CONSULTA_SALDO, "");
            }
        });
    }


    @Override
    public void showMessage(String msje) {
        Toast.makeText(this, msje, Toast.LENGTH_SHORT).show();
    }


    public boolean validacionLogin() {
        Corresponsal corresponsal;
        Banco banco;
        banco = new Banco();
        corresponsal = new Corresponsal();


        EditText correo_login = findViewById(R.id.correo_login);
        EditText contrasena_login = findViewById(R.id.password_login);
        Button btn_inicia_sesion = findViewById(R.id.btn_inicia_sesion);

        String correoLogin = correo_login.getText().toString();
        String contraseñaLogin = contrasena_login.getText().toString();

        corresponsal.setCorreoCorresponsal(correoLogin);
        corresponsal.setContrasenaCorresponsal(contraseñaLogin);

        String correoAd = correo_login.getText().toString();

        banco.setEmail(correoAd);


        if (!correoLogin.equals("") && !contraseñaLogin.equals("")) {
            SharedPreference sp = new SharedPreference(MainActivity.this);
            if (dbHelper.validarUsuario(corresponsal)) {
                if (dbHelper.validarUsuarioEstado(correoLogin, HABILITADO)) {
                    //Toast.makeText(MainActivity.this, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show();
                    sp.setUsuarioActivo(correoLogin);
                    isColorCorresponsal = true;
                    showScreen(SCREEN_MENU_CORRESPONSAL, "", "");
                    return true;
                } else {
                    showAlertDialog("Corresponsal deshabilitado\n comuniquese con su banco...", SCREEN_VALIDACION, "", "");

                    //Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            } else if (dbHelper.validarAdministrador(banco)) {
                //Toast.makeText(MainActivity.this, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show();
                sp.setAdministradorActivo(correoAd);
                isColorCorresponsal = false;

                showScreen(SCREEN_MENU_ADMIN, "", "");

            } else {
                showAlertDialog("Verifica tus credenciales", SCREEN_VALIDACION, "", "");
                //Toast.makeText(MainActivity.this, "Verifique los datos", Toast.LENGTH_SHORT).show();

            }
        } else {
            showAlertDialog("Campos vacios", SCREEN_VACIO, "", "");
            //Toast.makeText(MainActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (isLoginPantalla) {
            super.onBackPressed();
        }
    }


}