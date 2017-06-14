package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfimacionActivity extends AppCompatActivity {

    Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confimacion);

        go=(Button)findViewById(R.id.Principio);
        goPrincipio();
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
