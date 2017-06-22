package itesloscabos.com.hotelapp;

import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.datos;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.Models.rooms;
import itesloscabos.com.hotelapp.adapters.viewPagerAdapter;

public class HabitacionActivity extends AppCompatActivity {

    ViewPager viewPager;
    viewPagerAdapter vpa;
    List<indexImagenes> imgs;
    LinearLayout sliderDotaPanel;
    private int dotsCount;
    private ImageView[]dota;
    private static final String TAG = "PRUEBA";

    TextView estandar,europeo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView Regre=(ImageView)findViewById(R.id.RGhabiEs);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sliderDotaPanel=(LinearLayout)findViewById(R.id.SliderDota3);

        List<datos>datosHotel= AppState.result;

        for(int x=0;x<datosHotel.size();x++){
            datos p=datosHotel.get(x);
            List<rooms>r=p.getRooms();
            rooms s=r.get(0);

            imgs=s.getImages();
        }

        viewPager=(ViewPager)findViewById(R.id.viewPage3);
        vpa= new viewPagerAdapter(getApplicationContext(),imgs);
        viewPager.setAdapter(vpa);

        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new MyTimerClass(),2000,4000);


        dotsCount=vpa.getCount();
        dota=new ImageView[dotsCount];

        for(int x=0;x<dotsCount;x++){
            dota[x] = new ImageView(HabitacionActivity.this);
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

        estandar =(TextView)findViewById(R.id.habi_estandar);
        europeo =(TextView)findViewById(R.id.habi_europeo);
        String est=getIntent().getExtras().getString("estandar");
        String eup=getIntent().getExtras().getString("europeo");
        Log.e(TAG, "cuartos: "+est+" "+eup);

        estandar.setText(est);
        europeo.setText(eup);

    }

    public class MyTimerClass extends TimerTask {

        @Override
        public void run() {

            HabitacionActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==2)
                    {
                        viewPager.setCurrentItem(3);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
