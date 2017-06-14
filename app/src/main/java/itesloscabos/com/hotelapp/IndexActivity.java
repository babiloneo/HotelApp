package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import itesloscabos.com.hotelapp.HotelAPi.Service;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.LoginRespuesta;
import itesloscabos.com.hotelapp.Models.cuartos;
import itesloscabos.com.hotelapp.Models.datosCuarto;
import itesloscabos.com.hotelapp.Models.disponibilidad;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.Models.resdispo;
import itesloscabos.com.hotelapp.adapters.IndexAdapter;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndexActivity extends AppCompatActivity {
    Retrofit retrofit;
    private static final String TAG = "PRUEBA";

    List<hotelResult> indexResult;
    List<hotelResult>indexAux;
    IndexAdapter adapter;
    ListView listaHoteles;

    Button map;
    String refPoint ;
    String checkIn ;
    String checkOut ;
    String rooms ;
    String adults;
    String children;
    String fechas;
    String personas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        //obtengolos datos de mi ventana inicio


        listaHoteles=(ListView)findViewById(R.id.lista_hoteles);
        //inicio mi lista de hoteles en mi listview
        obtenerDatosIndex();

        TextView fechass=(TextView)findViewById(R.id.txt_fechas);
        TextView persona=(TextView)findViewById(R.id.textView10);

        //Envia los titulos a la ventana index

        fechass.setText(AppState.fechas);
        persona.setText(AppState.personas);

         map=(Button)findViewById(R.id.mapa);

         irMapa();
    }


    private void obtenerDatosIndex(){
        //IATAS: VER,HAV,MID
        /*
        * CUN=cancun
        * MID=Merida
        * VER=Veracruz
        * HAV=La Habana
        * LAP=La Paz
        * MZT=Mazatlan
        * ACA=Acapulco
        * */
        clients.getInstance().getService().ObtenerListaHotel("Bearer " + AppState.accessToken,"TEST",AppState.iata,AppState.destino,AppState.llegada,AppState.salida,AppState.cuarto,AppState.adultos,AppState.ninos).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                int h = response.body().getCount();
                String y = response.body().getProcessTime();
                Boolean z = response.body().getCachedResponse();
                Boolean a = response.body().getSuccess();

                if(response.isSuccessful()){
                    indexResult =response.body().getResult();
                    for(int x=0;x<indexResult.size();x++){
                        final hotelResult p=indexResult.get(x);
                ////comienzo de la segunda peticion
                        clients.getInstance().getService().getDisponibilidad("Bearer " + AppState.accessToken,AppState.iata,p.getPropertyNumber(),AppState.llegada,AppState.salida,AppState.cuarto,AppState.adultos,AppState.ninos,AppState.iata).enqueue(new Callback<disponibilidad>() {
                            @Override
                            public void onResponse(Call<disponibilidad> call, Response<disponibilidad> response) {
                                String processtime;
                                Boolean success;

                                if(response.isSuccessful()){

                                    processtime=response.body().getProcessTime();
                                    success=response.body().getSuccess();
                                    //obtengo mis resultados
                                    List<resdispo>disponibilidad=response.body().getResult();
                                    //obtengo el unico objeto que devuelve
                                    resdispo ss=disponibilidad.get(0);
                                    Log.i(TAG,"disponibilidad "+ss.getAvailable()+"  numero: "+ss.getPropertyNumber());

                                    if(ss.getAvailable()==true){
                                        //
                                        List<cuartos> ListCusttos=ss.getRooms();

                                        for(int i=0;i<ListCusttos.size();i++){

                                            cuartos m =ListCusttos.get(i);
                                            if(i==0){

                                                List<datosCuarto> rooms=m.getRates();
                                                datosCuarto h=rooms.get(0);
                                                p.setTotal(h.getTotal());

                                            }else
                                                {
                                                    List<datosCuarto> rooms=m.getRates();
                                                    datosCuarto h=rooms.get(0);
                                                    p.setTotal2(h.getTotal());
                                                }



                                        }

                                    }else{
                                        p.setTotal(0);
                                        //precio.setText("No Disponible");
                                    }
                                }else
                                {
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
                    AppState.precios=indexResult;
                    indexResult =response.body().getResult();
                    adapter=new IndexAdapter(getApplicationContext(),R.layout.listview_index,indexResult);
                    listaHoteles.setAdapter(adapter);

                }else{
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
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
}
