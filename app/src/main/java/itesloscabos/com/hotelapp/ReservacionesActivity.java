package itesloscabos.com.hotelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import io.realm.Realm;
import itesloscabos.com.hotelapp.BaseDate.servicios;
import itesloscabos.com.hotelapp.Models.Reservaciones.Reservar;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.adapters.IndexAdapter;
import itesloscabos.com.hotelapp.adapters.ReservacionAdapter;

public class ReservacionesActivity extends AppCompatActivity {

    Realm realm;
    private static final String TAG = "PRUEBA";
    List<Reservar> ReservacionResult;
    ListView listaReservaciones;

    ReservacionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservaciones);
        listaReservaciones=(ListView)findViewById(R.id.miReservacion);
        Query();
    }

    public void Query(){
        servicios nuevo=new servicios(Realm.getDefaultInstance());
        List<Reservar> reservars =nuevo.obtener();
        ReservacionResult=reservars;
        for(int x=0;x<reservars.size();x++){
            Reservar p =reservars.get(x);
            Log.e(TAG, "mi DB: "+p.getNombre());
        }

        adapter=new ReservacionAdapter(getApplicationContext(),R.layout.list_reservacion,ReservacionResult);
        listaReservaciones.setAdapter(adapter);
    }

}
