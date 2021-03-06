package itesloscabos.com.hotelapp;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import itesloscabos.com.hotelapp.HotelAPi.Service;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.datos;
import itesloscabos.com.hotelapp.Models.descripcion;
import itesloscabos.com.hotelapp.Models.detallesCuartos;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.adapters.viewPageAdapterNumberTwo;
import itesloscabos.com.hotelapp.adapters.viewPagerAdapter;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class descripActivity extends AppCompatActivity {

    private static final String TAG = "PRUEBA";

    TextView nombre,direccion,ciudad;
    RatingBar estrella;
    ImageView img;
    Button info;
    List<datos> datosHotel;
    List<indexImagenes>imgs;

    ViewPager viewPager;
    viewPageAdapterNumberTwo vpa;
    LinearLayout sliderDotaPanel;
    private int dotsCount;
    private ImageView[]dota;

    Double est,eup;

    private String propiedad;

    private TextView estandar;
    private TextView europeo;

    private  String habitacionEstadar,PrecioEstandar,habitacionEuropeo,PrecioEuropeo,transactionId,rateKeyEstandar,rateKeyEuropeo;
    private float taxRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrip);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sliderDotaPanel=(LinearLayout)findViewById(R.id.SliderDota2);

        nombre = (TextView)findViewById(R.id.txv_nombre);
        direccion = (TextView)findViewById(R.id.txv_direccion);
        ciudad = (TextView)findViewById(R.id.txv_ciudad);
        estrella = (RatingBar) findViewById(R.id.des_estrellas);

        TextView fecha=(TextView)findViewById(R.id.desc_fechas);
        TextView personas=(TextView)findViewById(R.id.desc_personas);
        fecha.setText(AppState.fechas);
        personas.setText(AppState.personas);

        propiedad = getIntent().getExtras().getString("propiedad");

        estandar =(TextView)findViewById(R.id.desc_estandar);
        europeo =(TextView)findViewById(R.id.desc_europeo);

        info=(Button)findViewById(R.id.desc_mapa);
        ventanaMapa();
        ImageView Regre=(ImageView)findViewById(R.id.REDetalle);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        descripcion();

        preciosCuartos();

        Habitacion();

        Reservacion();


    }

    private void Habitacion() {

        ImageView habi=(ImageView)findViewById(R.id.desc_habitacion);
        habi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(descripActivity.this,HabitacionActivity.class);
                Bundle ane=new Bundle();
                ane.putString("estandar",estandar.getText().toString());
                ane.putString("europeo",europeo.getText().toString());
                go.putExtras(ane);
                startActivity(go);
            }
        });
    }

    private void preciosCuartos() {

        //obtengo mi datos del index de hoteles
        List<hotelResult> index=AppState.index;

        for(int x=0;x<index.size();x++){

            hotelResult p=index.get(x);
            //busco el hotel que nesesito mostrar su descripcion
            if(p.getPropertyNumber().equals(propiedad)){

                List<detallesCuartos> detalles =p.getDetalles();
                //si lo encontre pregunto si su resultado no esta vacio o nulo
                if(detalles!=null && detalles.size()>0){
                //imprimo en pantalla los precios de las habitaciones
                    for(int y=0;y<detalles.size();y++){
                        detallesCuartos w =detalles.get(y);

                        if(w.getName().equals("Habitación Sencilla") || w.getName().equals("Single Room") || w.getName().equals("Single Standard")){
                            estandar.setText(w.getAverage()+" MXN");
                            habitacionEstadar=w.getName();
                            PrecioEstandar=""+w.getTotal();
                            transactionId=w.getTransactionId();
                            taxRate=w.getTaxRate();
                            rateKeyEstandar=w.getRateKey();
                        }
                        else if(w.getName().equals("Habitación Doble") || w.getName().equals("Double Room")){
                            europeo.setText(w.getAverage()+" MXN");
                            habitacionEuropeo=w.getName();
                            PrecioEuropeo=""+w.getTotal();
                            transactionId=w.getTransactionId();
                            taxRate=w.getTaxRate();
                            rateKeyEuropeo=w.getRateKey();
                        }
                    }
                }
            }
        }
    }

    private void Reservacion() {
        Button estandar2=(Button)findViewById(R.id.estandar);
        Button europeo2=(Button)findViewById(R.id.eutopeo);

        estandar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(estandar.getText().equals("No Disponible")) {
                    Toast.makeText(getApplicationContext(), "No Disponible", Toast.LENGTH_SHORT).show();
                }else{
                    Intent next=new Intent(descripActivity.this,DatosReservacionActivity.class);
                    Bundle datos=new Bundle();
                    datos.putString("habitacion",habitacionEstadar);
                    datos.putString("total",PrecioEstandar);
                    datos.putString("id",transactionId);
                    datos.putFloat("iva",taxRate);
                    datos.putString("key",rateKeyEstandar);
                    next.putExtras(datos);
                    startActivity(next);
                }
            }
        });

        europeo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(europeo.getText().equals("No Disponible")) {
                    Toast.makeText(getApplicationContext(), "No Disponible", Toast.LENGTH_SHORT).show();
                }else{
                    Intent next=new Intent(descripActivity.this,DatosReservacionActivity.class);
                    Bundle datos =new Bundle();
                    datos.putString("habitacion",habitacionEuropeo);
                    datos.putString("total",PrecioEuropeo);
                    datos.putString("id",transactionId);
                    datos.putFloat("iva",taxRate);
                    datos.putString("key",rateKeyEuropeo);
                    next.putExtras(datos);
                    startActivity(next);
                }
            }
        });
    }

    private void ventanaMapa() {
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go =new Intent(descripActivity.this,InfoHotelActivity.class);
                Bundle datos=new Bundle();
                datos.putString("titulo",nombre.getText().toString());
                go.putExtras(datos);
                startActivity(go);
            }
        });
    }

    public void descripcion(){

        clients.getInstance().getService().getDescripciones("Bearer " +AppState.accessToken,AppState.iata,propiedad)
                .enqueue(new Callback<descripcion>() {
                    @Override
                    public void onResponse(Call<descripcion> call, Response<descripcion> response) {

                        int q=response.body().getCount();
                        Log.e(TAG, "prueba: "+q);

                        if(response.isSuccessful()){
                            datosHotel=response.body().getResult();
                             AppState.result=datosHotel;

                            for(int x=0;x<datosHotel.size();x++){
                                datos p=datosHotel.get(x);

                                nombre.setText(p.getName());
                                direccion.setText(p.getAddress());
                                ciudad.setText(p.getCity()+", "+p.getState());
                                estrella.setRating(p.getCategory());
                                imgs=p.getImages();
                            }

                            imgs.remove(0);

                            viewPager=(ViewPager)findViewById(R.id.viewPage2);
                            vpa= new viewPageAdapterNumberTwo(getApplicationContext(),imgs);
                            viewPager.setAdapter(vpa);

                            Timer timer =new Timer();
                            timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);


                            dotsCount=vpa.getCount();
                            dota=new ImageView[dotsCount];

                            for(int x=0;x<dotsCount;x++){
                                dota[x] = new ImageView(descripActivity.this);
                                dota[x].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonative_dot));

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                                params.setMargins(8,0,8,0);
                                sliderDotaPanel.addView(dota[x],params);

                            }

                            dota[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

                            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {

                                    for(int x= 0; x<dotsCount;x++){
                                        dota[x].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonative_dot));
                                    }

                                    dota[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });


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
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            descripActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==2)
                    {
                        viewPager.setCurrentItem(3);
                    }
                    else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}