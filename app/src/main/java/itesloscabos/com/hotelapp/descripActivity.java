package itesloscabos.com.hotelapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.List;

import itesloscabos.com.hotelapp.HotelAPi.Service;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.datos;
import itesloscabos.com.hotelapp.Models.descripcion;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class descripActivity extends AppCompatActivity {

    private static final String TAG = "PRUEBA";

    TextView nombre,direccion,ciudad,estrella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrip);
        nombre = (TextView)findViewById(R.id.txv_nombre);
        direccion = (TextView)findViewById(R.id.txv_direccion);
        ciudad = (TextView)findViewById(R.id.txv_ciudad);
        estrella = (TextView)findViewById(R.id.txv_estrellas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String propiedad = getIntent().getExtras().getString("propiedad");

        clients.getInstance().getService().getDescripciones("Bearer " +AppState.accessToken,"TEST",propiedad)
                .enqueue(new Callback<descripcion>() {
                    @Override
                    public void onResponse(Call<descripcion> call, Response<descripcion> response) {

                        int q=response.body().getCount();
                        Log.e(TAG, "prueba: "+q);

                        if(response.isSuccessful()){
                            List<datos> datosHotel=response.body().getResult();

                            for(int x=0;x<datosHotel.size();x++){
                                datos p=datosHotel.get(x);

                                nombre.setText(p.getName());
                                direccion.setText(p.getAddress());
                                ciudad.setText(p.getCity()+", "+p.getState());
                            }

                        }else{
                            Log.e(TAG, "onResponse: "+response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<descripcion> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());

                    }
                });

    }
}
