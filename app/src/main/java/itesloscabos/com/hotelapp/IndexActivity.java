package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.CurrencyInfo;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.Provider;
import itesloscabos.com.hotelapp.Models.cuartos;
import itesloscabos.com.hotelapp.Models.datosCuarto;
import itesloscabos.com.hotelapp.Models.detallesCuartos;
import itesloscabos.com.hotelapp.Models.disponibilidad;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.resdispo;
import itesloscabos.com.hotelapp.adapters.IndexAdapter;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexActivity extends AppCompatActivity {

    private static final String TAG = "PRUEBA";

    List<hotelResult> indexResult;
    IndexAdapter adapter;
    ListView listaHoteles;

    Button map;

    private List<detallesCuartos> nuevob;
    //detallesCuartos detalles = new detallesCuartos();
    ProgressBar procesoCircular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //obtengolos datos de mi ventana inicio

        procesoCircular=(ProgressBar)findViewById(R.id.ProgressBarCircular);
        progresoProceso();
        listaHoteles=(ListView)findViewById(R.id.lista_hoteles);
        //inicio mi lista de hoteles en mi listview
        obtenerDatosIndex();

        TextView fechass=(TextView)findViewById(R.id.txt_fechas);
        TextView persona=(TextView)findViewById(R.id.textView10);
        TextView titulo=(TextView)findViewById(R.id.ind_titulo);

        ImageView Regre=(ImageView)findViewById(R.id.REIndex);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextViewIndex = new Intent(IndexActivity.this,InicioActivity.class);
                startActivity(nextViewIndex);
            }
        });

        //Envia los titulos a la ventana index

        fechass.setText(AppState.fechas);
        persona.setText(AppState.personas);
        titulo.setText("Hoteles en "+getIntent().getExtras().getString("destino"));
         map=(Button)findViewById(R.id.mapa);

         irMapa();
    }

    private void progresoProceso() {
        new Async_Task().execute();
    }


    private void obtenerDatosIndex(){

        /*
        * CUN=cancun
        * MID=Merida
        * VER=Veracruz
        * HAV=La Habana
        * LAP=La Paz
        * MZT=Mazatlan
        * ACA=Acapulco
        * TLC=Toluca
        * */


        clients.getInstance().getService().ObtenerListaHotel("Bearer " + AppState.accessToken,"TEST",AppState.iata,AppState.destino,AppState.llegada,AppState.salida,AppState.cuarto,AppState.adultos,AppState.ninos).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                final int h = response.body().getCount();
                String y = response.body().getProcessTime();
                Boolean z = response.body().getCachedResponse();
                Boolean a = response.body().getSuccess();
                CurrencyInfo valor=response.body().getCurrencyInfo();
                final float euros=valor.getExchangeRate();

                if(response.isSuccessful())
                {

                    indexResult =response.body().getResult();

                    for(int x=0;x<indexResult.size();x++){

                        final hotelResult p=indexResult.get(x);

                //comienzo de la segunda peticion

                        clients.getInstance().getService().getDisponibilidad("Bearer " + AppState.accessToken,AppState.iata,p.getPropertyNumber(),AppState.llegada,AppState.salida,AppState.cuarto,AppState.adultos,AppState.ninos,AppState.iata).enqueue(new Callback<disponibilidad>() {
                            @Override
                            public void onResponse(Call<disponibilidad> call, Response<disponibilidad> response) {

                                if(response.isSuccessful())
                                {
                                    DecimalFormat df = new DecimalFormat("#.00");
                                    String transactionId="";
                                    float taxRate;
                                    nuevob = new ArrayList<detallesCuartos>(2);
                                    detallesCuartos detalles;

                                    List<Provider>provider=response.body().getProvider();

                                    if(provider!=null){
                                        for(int x=0;x<provider.size();x++){
                                            Provider p =provider.get(x);
                                            transactionId=p.getTransactionId();
                                        }
                                    }
                                    Log.i(TAG, "provider"+transactionId);
                                    //obtengo mis resultados
                                    List<resdispo>disponibilidad=response.body().getResult();
                                    //obtengo el unico objeto que devuelve
                                    if(disponibilidad!=null && disponibilidad.size()>0) {
                                        resdispo ss = disponibilidad.get(0);

                                        Log.i(TAG, "disponibilidad " + ss.getAvailable() + "  numero: " + ss.getPropertyNumber());
                                        int fff = 0;

                                        //compruebo la disponibilidad
                                        if (ss.getAvailable() == true) {

                                            //obtengo los datos de los cuartos
                                            List<cuartos> ListCusttos = ss.getRooms();
                                            taxRate=ss.getTaxRate();
                                            if (ListCusttos.size() > 0) {
                                                for (int i = 0; i < ListCusttos.size(); i++) {

                                                    cuartos m = ListCusttos.get(i);
                                                    //obtengo los rates del cuarto
                                                    List<datosCuarto> rooms = m.getRates();
                                                    //pregunto si el rate es mayor a 0 en caso de que este vacio
                                                    if (rooms.size() > 0) {

                                                        for (int gt = 0; gt < rooms.size(); gt++) {
                                                            detalles = new detallesCuartos();

                                                            //si no esta vacio obtengo los detalles del cuarto
                                                            detalles.setName(m.getName());
                                                            detalles.setTransactionId(transactionId);
                                                            detalles.setTaxRate(taxRate);
                                                            datosCuarto h = rooms.get(gt);
                                                            detalles.setAverage(Float.parseFloat(df.format(euros*h.getAverage())));
                                                            detalles.setTotal(Float.parseFloat(df.format(euros*h.getTotal())));
                                                            detalles.setRateKey(h.getRateKey());
                                                            detalles.setCode(h.getCode());
                                                            nuevob.add(detalles);
                                                        }
                                                    }
                                                }
                                                p.setDetalles(nuevob);
                                            }
                                        }
                                    }
                                }else
                                {
                                    Log.e(TAG, "AAADDDFFFF ");
                                    switch(response.code()){
                                        case 404:
                                            Log.e(TAG, "Server Return Error: Not Faund "+response.errorBody());
                                            p.setTotal(0);
                                            break;
                                        case 500:
                                            Log.e(TAG, "Server Return Error: Server Is Broken: "+response.errorBody());
                                            p.setTotal(0);
                                            break;
                                        default:
                                            Log.e(TAG, "Server Return Error: Unknown Error: "+response.errorBody());
                                            p.setTotal(0);
                                            break;
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<disponibilidad> call, Throwable t) {
                                Log.e(TAG, "onFailure: "+t.getMessage());
                                p.setTotal(0);
                            }
                        });
                        //fin del segundo peticion

                    }

                }else{
                    Log.e(TAG, "onResponse: "+response.errorBody());

                }

                AppState.index=indexResult;
                indexResult =response.body().getResult();
                adapter=new IndexAdapter(getApplicationContext(),R.layout.listview_index,indexResult);
                listaHoteles.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());

            }
        });
    }

    private void irMapa(){

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapita = new Intent(IndexActivity.this,MapIndexActivity.class);
                startActivity(mapita);
            }
        });
    }

    public class Async_Task extends AsyncTask<Void,Integer,Void>{

        int progreso;

        @Override
        protected void onPreExecute() {
            progreso=0;
            procesoCircular.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(progreso<100){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(10);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            procesoCircular.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Toast.makeText(IndexActivity.this,"",Toast.LENGTH_SHORT).show();
            procesoCircular.setVisibility(View.INVISIBLE);
            listaHoteles.setVisibility(View.VISIBLE);
        }
    }
}
