package itesloscabos.com.hotelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.HotelRespuesta;
import itesloscabos.com.hotelapp.Models.Login;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ObtenerDatosLogin();
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
                    int f=2323;
                    AppState.accessToken = response.body().getAccessToken();



                    Log.i(TAG,"Hoteles"+"access_token: "+access_token+"\n"+"token_type:"+token_type+"/n"+"expires_in:"+expires_in
                            +"\n"+"userName:"+userName+"\n"+"issued:"+issued+"\n"+"expires:"+expires);

                    obtenerDatosIndex();
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

    private void obtenerDatosIndex(){

       // String Authorization = "bearer SqEB0uHh9_1vTNHPIAE8DeJfmrQx4mbY7fqK1VAmDta1SNXQ-hMMEjbwxu3uMhUMKW0cmYH6wkaM3TVqd2b9Wl8G91s-SIQtApTIBtVVrQf0_gtHMTgjn0kZUjpzXaYORCu9ThfNEVj482e_230RRu1OeLzUImnzDC-1JduNSp58Tr8cyRLrzTtGydO7AgNdSSKaXpDsAiZp7umz5d18goHfuEvoIYQIOjmAnfnQDfhKbNmnx_lGj32oeswOIXdc2upoC1icCDyCavVyRjDB0AC2K0fWVqZ4MmlYGO2gMCYdpGskC-U4YZYjTbBdcE3FcvdUJ8UeHcRpeghS8_Q9nOi13hCUgOk_r92cF3ii2dHrTyIOTGH8KdzabK8PkWdza38mkgC7SAFe9yBNqvpvt5s1J3JvGzK-DyGTX7Yj1NEzOTqK2kJM5zrKg96US9fGHrmznsn-iSE2SuXOjcBhiiBWXcIz1xJSi2Xej8Ys2WMpYnuwGR7zzfm9zGIMPdRuqeG9XJusnBE4YAnNfZ5rTw";
        String Environment ="TEST";
        String iata = "VER";
        String refPoint = "Veracruz";
        String checkIn = "20170513";
        String checkOut = "20170520";
        int rooms = 1;
        int adults = 1;
        int children = 0;
        String ages = "1,3";
        Boolean go =true;

        Service service = retrofit.create(Service.class);
        service.ObtenerListaHotel("Bearer " + AppState.accessToken,Environment,iata,refPoint,checkIn,checkOut,rooms,adults,children,go).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                int x = response.body().getCount();
                String y = response.body().getProcessTime();
                Boolean z = response.body().getCachedResponse();
                Boolean a = response.body().getSuccess();

                if(response.isSuccessful()){
                    Log.i(TAG,"Hoteles"+x+" "+y+" "+z+" "+a);
                  //  Hotel result = response.body();
                    ArrayList<hotelResult> indexResult =response.body().getResult();
                    for(int i=0;i<indexResult.size();i++){
                        hotelResult p =indexResult.get(i);
                        Log.i(TAG,"Hoteles"+p.getCountry()+","+p.getCity()+","+p.getName()+"/n");
                    }
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
