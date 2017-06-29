package itesloscabos.com.hotelapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.LoginRespuesta;
import itesloscabos.com.hotelapp.Models.hotelResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import itesloscabos.com.hotelapp.HotelAPi.Service;
public class InicioActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Retrofit retrofit;
    private static final String TAG = "PRUEBA";
    public static int dato=0;
    Button tarifas;
    private Button secion;
    private EditText destino;
    private TextView llegada;
    private TextView salida;
    private TextView cuartos;
    private TextView Adultos;
    private TextView ninos;
    TextView calendario;

    int pssicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tarifas=(Button) findViewById(R.id.ver_tarifas);
        destino=(EditText)findViewById(R.id.txt_destino);
        llegada=(TextView) findViewById(R.id.txt_llegada);
        salida= (TextView) findViewById(R.id.txt_salida);
        cuartos=(TextView)findViewById(R.id.txt_habitacion);
        Adultos=(TextView)findViewById(R.id.txt_adultos);
        ninos=(TextView)findViewById(R.id.txt_ninos);



        add_rem();
        ObtenerDatosLogin();
        mostrarTarifas();
    }

    public void calendario1(View view){
        pssicion=1;
        calendario=(TextView)findViewById(R.id.txt_llegada);
        datePicker(view);
    }

    public void calendario2(View view){
        pssicion=2;
        calendario=(TextView)findViewById(R.id.txt_salida);
        datePicker(view);
    }

    public void datePicker(View view){
        DatePickerFragment fragment = new  DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"Date");
        //Dialog p = fragment.getDialog();

        llegada.setError(null);
        salida.setError(null);
    }

    public void setDate(final Calendar calender){
        SimpleDateFormat dateform = new SimpleDateFormat("dd/MM/yyyy");
        calendario.setText(dateform.format(calender.getTime()));
        //final DateFormat dateforma= DateFormat.getDateInstance(DateFormat.SHORT);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String s = format.format(calender.getTime());

        if(pssicion==1){
            AppState.llegada=s;
        }else if (pssicion==2){
            AppState.salida=s;
        }
        Log.e(TAG,"dataView:"+s);

    }
    public void add_rem(){
        ImageView menos_A=(ImageView)findViewById(R.id.ha_rem);
        ImageView mas_A=(ImageView)findViewById(R.id.ha_add);
        ImageView men_Ad=(ImageView)findViewById(R.id.ad_rem);
        ImageView mas_Ad=(ImageView)findViewById(R.id.ad_add);
        ImageView men_ni=(ImageView)findViewById(R.id.ni_rem);
        ImageView mas_ni=(ImageView)findViewById(R.id.ni_add);

        menos_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int x =Integer.parseInt(((EditText)findViewById(R.id.txt_habitacion)).getText().toString());
                    if(x>0){
                        x--;
                    }
                    ((EditText)findViewById(R.id.txt_habitacion)).setText(String.valueOf(x));

            }
        });
        mas_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuartos.setError(null);
                int x =Integer.parseInt(((EditText)findViewById(R.id.txt_habitacion)).getText().toString());
                if(x>=0 && x<=10){
                    x++;
                }
                ((EditText)findViewById(R.id.txt_habitacion)).setText(String.valueOf(x));
            }

        });

        men_Ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x =Integer.parseInt(((EditText)findViewById(R.id.txt_adultos)).getText().toString());
                if(x>0){
                    x--;
                }
                ((EditText)findViewById(R.id.txt_adultos)).setText(String.valueOf(x));

            }
        });

        mas_Ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x =Integer.parseInt(((EditText)findViewById(R.id.txt_adultos)).getText().toString());
                if(x>=0 && x<=10){
                    x++;
                }
                ((EditText)findViewById(R.id.txt_adultos)).setText(String.valueOf(x));
            }
        });

        men_ni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x =Integer.parseInt(((EditText)findViewById(R.id.txt_ninos)).getText().toString());
                if(x>0){
                    x--;
                }
                ((EditText)findViewById(R.id.txt_ninos)).setText(String.valueOf(x));

            }
        });

        mas_ni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x =Integer.parseInt(((EditText)findViewById(R.id.txt_ninos)).getText().toString());
                if(x>=0 && x<=10){
                    x++;
                }
                ((EditText)findViewById(R.id.txt_ninos)).setText(String.valueOf(x));
            }
        });
    }


    public String iatas(String ciudad){
        String iata="";

        String cmp=ciudad.toLowerCase();

        Log.e(TAG, "iata minuscula: "+cmp);

        if(cmp.equals("mazatlan")){
            iata="MZT";
        }else if(cmp.equals("la habana")){
            iata="HAV";
        }else if(cmp.equals("cancun")){
            iata="CUN";
        }else if(cmp.equals("merida")){
            iata="MID";
        }else if(cmp.equals("veracruz")){
            iata="VER";
        }else if(cmp.equals("la paz")){
            iata="LAP";
        }else if(cmp.equals("toluca")) {
            iata="TLC";
        }
        else
        {
            iata="NOSE";
        }

        return iata;
    }

    public void mostrarTarifas(){

        tarifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();
            }
        });
    }

    public void Globales(){

        AppState.iata=iatas(destino.getText().toString());
        AppState.destino=(destino.getText().toString());
        AppState.cuarto=cuartos.getText().toString();
        AppState.adultos=Adultos.getText().toString();
        AppState.ninos=ninos.getText().toString();
        AppState.fechas=llegada.getText().toString()+"-"+salida.getText().toString();
        AppState.personas=Adultos.getText().toString()+" Adultos "+ninos.getText().toString()+" Niños";

        if(AppState.accessToken=="")
        {
            Toast.makeText(getApplicationContext(), "Inicie su sesión", Toast.LENGTH_SHORT).show();
            ObtenerDatosLogin();
        }else{
            //Globales();
            Intent nextViewIndex = new Intent(InicioActivity.this,IndexActivity.class);
            Bundle datos=new Bundle();
            datos.putString("destino",destino.getText().toString());
            nextViewIndex.putExtras(datos);
            startActivity(nextViewIndex);
        }
    }

    private void  submitForm(){
        dato=0;
        if(!validestino()){
            return;
        }
        if(!valiI()){
            return;
        }
        if(!valiS()){
            return;
        }
        if(!Cuartos()){
            return;
        }
        dato=1;
        Rec(dato);
    }
    private void Rec(int dato){
        if(dato==1){
            //tari();
            Globales();
        }
    }

    private  boolean Cuartos(){
        String cuar=cuartos.getText().toString();
        int C=Integer.parseInt(cuar);
        if(C<=0){
            cuartos.setError("Selecciona el numero de habitaciones");
            return false;
        }
        return true;
    }
    private boolean valiI(){

        String sali=llegada.getText().toString();
        Log.e(TAG, "SALIDA: "+sali);
        if(sali.isEmpty()){
            llegada.setError("Llene este campoI");
            return false;
        }
        return true;
    }
    private boolean valiS(){
        String sali=salida.getText().toString();

        if(sali.isEmpty()){
            salida.setError("Llene este campoI");
            return false;
        }
        return true;
    }
    private boolean validestino(){

        String des=destino.getText().toString();

        if(des.isEmpty()){
            destino.setError("Llene este campo");
            return false;
        }
        return true;
    }
    private void ObtenerDatosLogin()
    {
        //Agregamos la base de la url y formateamos lo que obtenemos para convertirlo
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.univisit.com/cubaapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String type="password";
        String username="b2155b77-97ca-4d53-88c3-a63f662db6e2";
        String password="Htlbm59yvkcE2NMhtqQLuMuW5+u2Eh6JO/pY6o6C+pg=";

        Service service = retrofit.create(Service.class);

        service.obtenerListaLogin(type,username,password).enqueue(new Callback<LoginRespuesta>() {
            @Override
            public void onResponse(Call<LoginRespuesta> call, Response<LoginRespuesta> response) {
                if(response.isSuccessful())
                {
                    String access_token = response.body().getAccessToken();
                    String token_type = response.body().getTokenType();
                    String expires_in = response.body().getExpires();
                    String userName = response.body().getUserName();
                    String issued = response.body().getIssued();
                    String expires = response.body().getExpires();
                    AppState.accessToken = response.body().getAccessToken();


                    Log.i(TAG,"Hoteles"+"access_token: "+access_token+"\n"+"token_type:"+token_type+"/n"+"expires_in:"+expires_in
                            +"\n"+"userName:"+userName+"\n"+"issued:"+issued+"\n"+"expires:"+expires);
                    Toast.makeText(getApplicationContext(), "Secion Iniciada", Toast.LENGTH_SHORT).show();

                }else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<LoginRespuesta> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getMessage());

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year,month,day);
        setDate(cal);
    }

    public static class DatePickerFragment extends DialogFragment{

        public Dialog onCreateDialog(Bundle savedInstanceState){

            final Calendar c =Calendar.getInstance();
            int year =c.get(Calendar.YEAR);
            int month =c.get(Calendar.MONTH);
            int day =c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
            (DatePickerDialog.OnDateSetListener)
                    getActivity(),year,month,day);
        }
    }
}
