package itesloscabos.com.hotelapp;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.datos;
import itesloscabos.com.hotelapp.Models.descripcion;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.adapters.viewPagerAdapter;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoHotelActivity extends AppCompatActivity implements OnMapReadyCallback {

    ViewPager viewPager;
    viewPagerAdapter vpa;
    List<indexImagenes>imgs;
    LinearLayout sliderDotaPanel;
    private int dotsCount;
    private ImageView[]dota;
    private static final String TAG = "PRUEBA";

    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (googleServiceAvailable()) {
            setContentView(R.layout.activity_info_hotel);
            InitMap();
        }

        sliderDotaPanel=(LinearLayout)findViewById(R.id.SliderDota);

        List<datos>datosHotel=AppState.result;
        TextView nombre =(TextView)findViewById(R.id.inf_nombre);
        TextView direccion =(TextView)findViewById(R.id.inf_direccion);
        TextView ciudad = (TextView)findViewById(R.id.inf_ciudad);
        RatingBar estrella =(RatingBar)findViewById(R.id.inf_estrellas);
        TextView descripcion=(TextView)findViewById(R.id.inf_descripcion);

        TextView titulo=(TextView)findViewById(R.id.hot_titulo);
        titulo.setText(getIntent().getExtras().getString("titulo"));

        ImageView Regre=(ImageView)findViewById(R.id.RGinfoHabi);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        for (int x =0;x<datosHotel.size();x++){
            datos  p=datosHotel.get(x);
            imgs=p.getImages();
            nombre.setText(p.getName());
            direccion.setText(p.getAddress());
            ciudad.setText(p.getCity()+", "+p.getState());
            estrella.setRating(p.getCategory());

        }


        viewPager=(ViewPager)findViewById(R.id.viewPage);
        vpa= new viewPagerAdapter(getApplicationContext(),imgs);
        viewPager.setAdapter(vpa);

        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new MyTimerClass(),2000,4000);


        dotsCount=vpa.getCount();
        dota=new ImageView[dotsCount];

        for(int x=0;x<dotsCount;x++){
            dota[x] = new ImageView(InfoHotelActivity.this);
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

    }

    private void InitMap() {
        MapFragment fragmenMap = (MapFragment) getFragmentManager().findFragmentById(R.id.mapView);
        fragmenMap.getMapAsync(this);
    }

    public boolean googleServiceAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAbailable = api.isGooglePlayServicesAvailable(this);
        if (isAbailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAbailable)) {
            Dialog dialog = api.getErrorDialog(this, isAbailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "no se conecto", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if(mGoogleMap!=null){
            mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter(){

                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v =getLayoutInflater().inflate(R.layout.info_window,null);
                    ImageView imagen =(ImageView)v.findViewById(R.id.inf_imagen);
                    TextView nombre=(TextView)v.findViewById(R.id.inf_nombre);
                    TextView precio=(TextView)v.findViewById(R.id.inf_precio);
                    TextView direccion=(TextView)v.findViewById(R.id.inf_direccion);

                    LatLng ll = marker.getPosition();
                    nombre.setText(marker.getTitle());
                    precio.setText("120MXN");
                    direccion.setText(marker.getSnippet());

                    return v;
                }
            });
        }
    }

    private void goToLocationZoom(double latitud, double longitud,float zoom) {
        LatLng ll =new LatLng(latitud,longitud);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mGoogleMap.moveCamera(update);

    }

    public void GeoLocate() {

        try {

            String buscar = AppState.destino;
            Log.e(TAG, "buscar"+" "+buscar);

            Geocoder gc = new Geocoder(this);

            List<Address> list  = gc.getFromLocationName(buscar, 1);
            Address address = list.get(0);

            double lat = address.getLatitude();
            double log = address.getLongitude();
            goToLocationZoom(lat, log, 6);
            Log.e(TAG, "primero: "+lat+" "+log);


            List<datos>datosHotel=AppState.result;
                datos p = datosHotel.get(0);

                double latitud = p.getLatitude();
                double longitud = p.getLongitude();

                Log.e(TAG, "segundo: "+latitud+" "+longitud);


                MarkerOptions marker = new MarkerOptions()
                        .title(p.getName())
                        .position(new LatLng(latitud, longitud));
                mGoogleMap.addMarker(marker);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyTimerClass extends TimerTask {

        @Override
        public void run() {

            InfoHotelActivity.this.runOnUiThread(new Runnable() {
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
