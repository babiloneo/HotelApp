package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosReservacionActivity extends AppCompatActivity {

    Double est,eup;
    private static final String TAG = "PRUEBA";
    TextView estandar,europeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_reservacion);

        nexyView();
    }

    private void nexyView() {
        Button cont=(Button)findViewById(R.id.continuar);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(DatosReservacionActivity.this,InformacionPagoActivity.class);
                startActivity(go);
            }
        });
    }

}
