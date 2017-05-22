package itesloscabos.com.hotelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import itesloscabos.com.hotelapp.HotelAPi.Service;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.LoginRespuesta;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.adapters.IndexAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndexActivity extends AppCompatActivity {
    Retrofit retrofit;
    private static final String TAG = "PRUEBA";

    List<hotelResult> indexResult;
    IndexAdapter adapter;
    ListView listaHoteles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        listaHoteles=(ListView)findViewById(R.id.lista_hoteles);
        obtenerDatosIndex();
    }

    private void obtenerDatosIndex(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.univisit.com/cubaapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // String Authorization = "bearer SqEB0uHh9_1vTNHPIAE8DeJfmrQx4mbY7fqK1VAmDta1SNXQ-hMMEjbwxu3uMhUMKW0cmYH6wkaM3TVqd2b9Wl8G91s-SIQtApTIBtVVrQf0_gtHMTgjn0kZUjpzXaYORCu9ThfNEVj482e_230RRu1OeLzUImnzDC-1JduNSp58Tr8cyRLrzTtGydO7AgNdSSKaXpDsAiZp7umz5d18goHfuEvoIYQIOjmAnfnQDfhKbNmnx_lGj32oeswOIXdc2upoC1icCDyCavVyRjDB0AC2K0fWVqZ4MmlYGO2gMCYdpGskC-U4YZYjTbBdcE3FcvdUJ8UeHcRpeghS8_Q9nOi13hCUgOk_r92cF3ii2dHrTyIOTGH8KdzabK8PkWdza38mkgC7SAFe9yBNqvpvt5s1J3JvGzK-DyGTX7Yj1NEzOTqK2kJM5zrKg96US9fGHrmznsn-iSE2SuXOjcBhiiBWXcIz1xJSi2Xej8Ys2WMpYnuwGR7zzfm9zGIMPdRuqeG9XJusnBE4YAnNfZ5rTw";
        String Environment ="TEST";
        String iata = "VER";
        String refPoint = getIntent().getStringExtra("destino");
        String checkIn = getIntent().getStringExtra("llegada");
        String checkOut = getIntent().getStringExtra("salida");
        String rooms =getIntent().getStringExtra("cuartos");
        String adults = getIntent().getStringExtra("adultos");
        String children = getIntent().getStringExtra("ninos");
        String ages = "1,3";
        Boolean go =true;
        //String rojo='proenfjki';

        Service service = retrofit.create(Service.class);
        service.ObtenerListaHotel("Bearer " + AppState.accessToken,Environment,iata,refPoint,checkIn,checkOut,rooms,adults,children).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                int x = response.body().getCount();
                String y = response.body().getProcessTime();
                Boolean z = response.body().getCachedResponse();
                Boolean a = response.body().getSuccess();

                if(response.isSuccessful()){
                    Log.i(TAG,"Hoteles"+x+" "+y+" "+z+" "+a);
                    //  Hotel result = response.body();
                    indexResult =response.body().getResult();
                    adapter=new IndexAdapter(getApplicationContext(),R.layout.listview_index,indexResult);
                    listaHoteles.setAdapter(adapter);
/*
                    for(int i=0;i<indexResult.size();i++){
                        hotelResult p =indexResult.get(i);
                        Log.i(TAG,"Hoteles"+p.getCountry()+","+p.getCity()+","+p.getName());
                        ArrayList<indexImagenes> indexImg=p.getImages();

                        for(int j=0;j<indexImg.size();j++){
                            indexImagenes q=indexImg.get(j);
                            Log.i(TAG,"imagenes"+q.getKeyword()+","+q.getDescription()+","+q.getUrl());
                        }
                    }*/
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


}
