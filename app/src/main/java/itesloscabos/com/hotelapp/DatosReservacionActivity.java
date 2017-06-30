package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Rules.Result;
import itesloscabos.com.hotelapp.Models.Rules.Rules;
import itesloscabos.com.hotelapp.Models.datos;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosReservacionActivity extends AppCompatActivity {

    Double est,eup;
    private static final String TAG = "PRUEBA";
    TextView estandar,europeo;
    private TextView nombre,direccion,ciudad,personas,llegada,salida,habitacion,total;
    String transactionId,totalP,key;
    float taxRate;

    String RuleTransactionId,country,zipCode,state,city;
    Boolean canHold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_reservacion);

        //ImageView este= (ImageView)findViewById(R.id.prueba123);
        obtenerIDS();
        DatosReservacion();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ImageView Regre=(ImageView)findViewById(R.id.RIDatos);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        nexyView();
    }

    private void obtenerIDS() {
        nombre=(TextView)findViewById(R.id.res_nombre);
        direccion=(TextView)findViewById(R.id.res_direccion);
        ciudad=(TextView)findViewById(R.id.res_ciudad);
        personas=(TextView)findViewById(R.id.res_personas);
        llegada=(TextView)findViewById(R.id.res_llegada);
        salida=(TextView)findViewById(R.id.res_salida);
        habitacion=(TextView)findViewById(R.id.res_habitacion);
        total=(TextView)findViewById(R.id.res_total);
    }

    private void DatosReservacion() {
        List<datos> datosHotel = AppState.result;

        if(datosHotel!=null){

            for(int x=0;x<datosHotel.size();x++){
                datos p = datosHotel.get(x);
                nombre.setText(p.getName());
                direccion.setText(p.getAddress());
                ciudad.setText(p.getCity()+", "+p.getState());
            }
        }

        personas.setText(AppState.personas);
        String []fechas=AppState.fechas.split("-");
        llegada.setText(fechas[0]);
        salida.setText(fechas[1]);
        habitacion.setText(getIntent().getExtras().getString("habitacion"));
        total.setText(getIntent().getExtras().getString("total")+" MXN");

        totalP=getIntent().getExtras().getString("total");
        transactionId=getIntent().getExtras().getString("id");
        taxRate=getIntent().getExtras().getFloat("iva");
        key=getIntent().getExtras().getString("key");
    }

    private void nexyView() {
        Rules();

        Button cont=(Button)findViewById(R.id.continuar);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(DatosReservacionActivity.this,InformacionPagoActivity.class);
                Bundle datos= new Bundle();
                datos.putString("id",RuleTransactionId);
                datos.putString("state",state);
                datos.putString("city",city);
                datos.putString("country",country);
                datos.putString("zipCode",zipCode);
                datos.putString("total",totalP);
                datos.putFloat("iva",taxRate);
                datos.putString("habitacion",habitacion.getText().toString());
                go.putExtras(datos);
                startActivity(go);
            }
        });
    }

    public void Rules(){
        Log.e(TAG,"key:"+key+" id:"+transactionId);
        clients.getInstance().getService().getRules("Bearer "+ AppState.accessToken,AppState.Environment,key,transactionId).enqueue(new Callback<Rules>() {
            @Override
            public void onResponse(Call<Rules> call, Response<Rules> response) {

                if(response.isSuccessful()){

                    RuleTransactionId=response.body().getTransactionId();
                    canHold=response.body().getCanHold();
                    List<Result> result=response.body().getResult();
                    if(result!=null){
                        for(int x=0;x<result.size();x++){
                            Result p =result.get(x);
                            city=p.getCity();
                            state=p.getState();
                            country =p.getCountry();
                            zipCode =p.getZipCode();

                        }
                    }

                    Log.e(TAG,"Rules:"+RuleTransactionId+" "+country+" "+zipCode);


                }else{
                    switch(response.code()){
                        case 404:
                            Log.e(TAG, "Server Return Error: Not Faund "+response.errorBody());
                            break;
                        case 500:
                            Log.e(TAG, "Server Return Error: Server Is Broken: "+response.errorBody());
                            break;
                        default:
                            Log.e(TAG, "Server Return Error: Unknown Error: "+response.errorBody());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Rules> call, Throwable t) {

            }
        });
    }

}
