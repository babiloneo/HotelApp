package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.datos;

public class ConfimacionActivity extends AppCompatActivity {
    String totalx,nombreD,correoD,telefonoD,ciudadD,peticionD;
    float taxRate;
    Button go;
    Realm realm;
    String referenciax;
    TextView nombreH,direccion,ciudad,habitacion,personas,llegada,salida,subtotal,iva,total,Anombre,correo,telefono,Aciudad,peticion;
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

      //  realm = Realm.getDefaultInstance();
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
        String referenciax=getIntent().getExtras().getString("referencia");
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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
