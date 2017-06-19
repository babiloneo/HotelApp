package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InformacionPagoActivity extends AppCompatActivity {
    Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_pago);

       confirmar = (Button)findViewById(R.id.Complete);
        irFinal();
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
