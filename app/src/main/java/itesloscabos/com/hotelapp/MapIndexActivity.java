package itesloscabos.com.hotelapp;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import itesloscabos.com.hotelapp.HotelAPi.Service;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapIndexActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    private static final String TAG = "PRUEBA";
    String Environment;
    String iata ;
    String refPoint ;
    String checkIn ;
    String checkOut ;
    String rooms ;
    String adults;
    String children;
    String fechas;
    String personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        if (googleServiceAvailable()) {
            setContentView(R.layout.activity_map_index);
            InitMap();
        }

        GeoLocate();

        TextView fechass=(TextView)findViewById(R.id.map_fechas);
        TextView persona=(TextView)findViewById(R.id.map_personas);

        //Envia los titulos a la ventana index

        fechass.setText(AppState.fechas);
        persona.setText(AppState.personas);

        ImageView Regre=(ImageView)findViewById(R.id.RGMapa);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void InitMap() {
        MapFragment fragmenMap = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
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
        //seteando localizacion

    }

    private void goToLocationZoom(double latitud, double longitud,float zoom) {
        LatLng ll =new LatLng(latitud,longitud);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mGoogleMap.moveCamera(update);

    }

    public void GeoLocate() {

        clients.getInstance().getService().ObtenerListaHotel("Bearer " + AppState.accessToken,"TEST",AppState.iata,AppState.destino,AppState.llegada,AppState.salida,AppState.cuarto,AppState.adultos,AppState.ninos).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {

                String buscar =AppState.destino;
                Geocoder gc = new Geocoder(MapIndexActivity.this);
                try {
                    List<Address> list =gc.getFromLocationName(buscar,1);
                    Address address =list.get(0);
                   // String locality = address.getLocality();
                    double lat=address.getLatitude();
                    double log= address.getLongitude();
                    goToLocationZoom(lat,log,6);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                List<hotelResult>lista= response.body().getResult();
                for(int x=0;x<lista.size();x++){
                    hotelResult p=lista.get(x);

                    double latitud =p.getLatitude();
                    double longitud = p.getLongitude();



                    MarkerOptions marker = new MarkerOptions()
                            .title(p.getName())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                            .position(new LatLng(latitud,longitud))
                            .snippet(p.getAddress());
                    mGoogleMap.addMarker(marker);

                }
            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
