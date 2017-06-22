package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.StringTokenizer;
import android.widget.ImageView;

public class InformacionPagoActivity extends AppCompatActivity {
    Button confirmar;

    String total,RuleTransactionId,country,zipCode;
    float taxRate;
    TextView subTotal,iva,totalB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_pago);
        obtenerDatosIntent();
        obtenerIDS();
        setearValores();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView Regre=(ImageView)findViewById(R.id.RGinfoPago);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

       confirmar = (Button)findViewById(R.id.Complete);
        irFinal();

    }

    private void setearValores() {
        subTotal.setText(total);
        if(taxRate==0){
            iva.setText("IVA Incluido");
            totalB.setText(total);
        }else{
            iva.setText(""+iva);
            float aux=Float.parseFloat(total);
            float totalF=aux+taxRate;
            totalB.setText(""+totalF);
        }
    }

    private void obtenerIDS() {
        subTotal=(TextView)findViewById(R.id.pag_subtotal);
        iva=(TextView)findViewById(R.id.pag_iva);
        totalB=(TextView)findViewById(R.id.pag_total);
    }

    private void obtenerDatosIntent() {

        total=getIntent().getExtras().getString("total");
        taxRate=getIntent().getExtras().getFloat("iva");
        RuleTransactionId=getIntent().getExtras().getString("id");
        country=getIntent().getExtras().getString("country");
        zipCode=getIntent().getExtras().getString("zipCode");

    }

    public void irFinal(){
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(InformacionPagoActivity.this,ConfimacionActivity.class);
                startActivity(go);
            }
        });
    }
}
