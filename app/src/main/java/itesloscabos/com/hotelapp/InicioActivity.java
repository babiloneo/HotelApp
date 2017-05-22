package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
public class InicioActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "PRUEBA";
    Button tarifas;
    private Button secion;
    private EditText destino;
    private EditText llegada;
    private EditText salida;
    private TextView cuartos;
    private TextView Adultos;
    private TextView ninos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        secion=(Button)findViewById(R.id.textView);
        tarifas=(Button) findViewById(R.id.ver_tarifas);
        destino=(EditText)findViewById(R.id.txt_destino);
        llegada=(EditText)findViewById(R.id.txt_llegada);
        salida= (EditText)findViewById(R.id.txt_salida);
        cuartos=(TextView)findViewById(R.id.txt_habitacion);
        Adultos=(TextView)findViewById(R.id.txt_adultos);
        ninos=(TextView)findViewById(R.id.txt_ninos);

       // ObtenerDatosLogin();
        iniciar_secion();
        mostrarTarifas();
    }

    public void mostrarTarifas(){


        tarifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppState.accessToken=="")
                {
                    Toast.makeText(getApplicationContext(), "Inicie su secion", Toast.LENGTH_SHORT).show();
                }else{
                    Intent nextViewIndex = new Intent(InicioActivity.this,IndexActivity.class);
                    Bundle datos = new Bundle();
                    datos.putString("destino",destino.getText().toString());
                    datos.putString("llegada",llegada.getText().toString());
                    datos.putString("salida",salida.getText().toString());
                    datos.putString("cuartos",cuartos.getText().toString());
                    datos.putString("adultos",Adultos.getText().toString());
                    datos.putString("ninos",ninos.getText().toString());
                    nextViewIndex.putExtras(datos);
                    startActivity(nextViewIndex);
                }

            }
        });
    }

    public void iniciar_secion(){
        secion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObtenerDatosLogin();

            }
        });
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

}
