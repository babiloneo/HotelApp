package itesloscabos.com.hotelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
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
        TextView fechas=(TextView)findViewById(R.id.txt_fechas);
        TextView persona=(TextView)findViewById(R.id.textView10);
        fechas.setText(getIntent().getStringExtra("fechas"));
        persona.setText(getIntent().getStringExtra("personas"));
    }

    private void obtenerDatosIndex(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.univisit.com/cubaapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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


}
