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

import io.realm.Realm;
import io.realm.RealmResults;
import itesloscabos.com.hotelapp.BaseDate.servicios;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Reservaciones.Reservar;
import itesloscabos.com.hotelapp.Models.datos;

public class ConfimacionActivity extends AppCompatActivity {
    String totalx,nombreD,correoD,telefonoD,ciudadD,peticionD;
    float taxRate;
    Button go;
    Realm realm;
    String referenciax;
    TextView nombreH,direccion,ciudad,habitacion,personas,llegada,salida,subtotal,iva,total,Anombre,correo,telefono,Aciudad,peticion;
    private static final String TAG = "PRUEBA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confimacion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ImageView Regre=(ImageView)findViewById(R.id.RGReserv);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        go=(Button)findViewById(R.id.Principio);
        goPrincipio();

        realm = Realm.getDefaultInstance();

        obtenerIDS();
        DatosReservacion();
    }

    private void obtenerIDS() {
        nombreH=(TextView)findViewById(R.id.conf_nombre);
        direccion=(TextView)findViewById(R.id.conf_direccion);
        ciudad=(TextView)findViewById(R.id.conf_ciudad);
        habitacion=(TextView)findViewById(R.id.conf_habitacion);
        personas=(TextView)findViewById(R.id.conf_personas);
        llegada=(TextView)findViewById(R.id.conf_llegada);
        salida=(TextView)findViewById(R.id.conf_salida);
        subtotal=(TextView)findViewById(R.id.conf_subTotal);
        iva=(TextView)findViewById(R.id.conf_iva);
        total=(TextView)findViewById(R.id.conf_total);
        Anombre=(TextView)findViewById(R.id.conf_Anombre);
        correo=(TextView)findViewById(R.id.conf_correo);
        Aciudad=(TextView)findViewById(R.id.conf_Aciudad);
        telefono=(TextView)findViewById(R.id.conf_telefono);
        peticion=(TextView)findViewById(R.id.conf_peticion);
    }

    private void DatosReservacion() {
        List<datos> datosHotel = AppState.result;

        if(datosHotel!=null){

            for(int x=0;x<datosHotel.size();x++){
                datos p = datosHotel.get(x);
                nombreH.setText(p.getName());
                direccion.setText(p.getAddress());
                ciudad.setText(p.getCity()+", "+p.getState());
            }
        }

        personas.setText(AppState.personas);
        String []fechas=AppState.fechas.split("-");
        llegada.setText(fechas[0]);
        salida.setText(fechas[1]);
        habitacion.setText(getIntent().getExtras().getString("habitacion"));
        totalx=getIntent().getExtras().getString("total");
        taxRate=getIntent().getExtras().getFloat("iva");
        Anombre.setText(getIntent().getExtras().getString("nombre"));
        correo.setText(getIntent().getExtras().getString("correo"));
        telefono.setText(getIntent().getExtras().getString("telefono"));
        Aciudad.setText(getIntent().getExtras().getString("ciudad"));
        peticion.setText(getIntent().getExtras().getString("peticion"));
        referenciax=getIntent().getExtras().getString("referencia");
        subtotal.setText(totalx);
        if(taxRate==0){
            iva.setText("IVA Incluido");
            total.setText(totalx);
        }else{
            iva.setText(""+iva);
            float aux=Float.parseFloat(totalx);
            float totalF=aux+taxRate;
            total.setText(""+totalF);
        }

        GuardarDB();
    }

    private void GuardarDB() {

        servicios nuevo=new servicios(Realm.getDefaultInstance());
        nuevo.NuevaReservacion(referenciax,nombreH.getText().toString(),direccion.getText().toString(),ciudad.getText().toString(),habitacion.getText().toString(),
                personas.getText().toString(),llegada.getText().toString(),salida.getText().toString(),total.getText().toString(),Anombre.getText().toString());

       /* realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Reservar nuevo = bgRealm.createObject(Reservar.class);
                nuevo.setReferencia(referenciax);
                nuevo.setNombre(nombreH.getText().toString());
                nuevo.setDireccion(direccion.getText().toString());
                nuevo.setCiudad(ciudad.getText().toString());
                nuevo.setHabitacion(habitacion.getText().toString());
                nuevo.setViajeros(personas.getText().toString());
                nuevo.setLlegada(llegada.getText().toString());
                nuevo.setSalida(salida.getText().toString());
                nuevo.setTotal(total.getText().toString());
                nuevo.setAnombre(Anombre.getText().toString());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "Guardado Correctamente!!");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Database: "+error.getMessage());
            }
        });*/

    }


    private void goPrincipio() {

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(ConfimacionActivity.this,InicioActivity.class);
                startActivity(go);
            }
        });
    }
}
