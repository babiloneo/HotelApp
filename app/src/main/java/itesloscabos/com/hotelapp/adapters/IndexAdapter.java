package itesloscabos.com.hotelapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;

import java.util.List;

import itesloscabos.com.hotelapp.IndexActivity;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.cuartos;
import itesloscabos.com.hotelapp.Models.datosCuarto;
import itesloscabos.com.hotelapp.Models.detallesCuartos;
import itesloscabos.com.hotelapp.Models.disponibilidad;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.Models.resdispo;
import itesloscabos.com.hotelapp.R;
import itesloscabos.com.hotelapp.cliente.clients;
import itesloscabos.com.hotelapp.descripActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IndexAdapter extends ArrayAdapter<hotelResult>{

    private List<hotelResult> lista;
    private Context context;
    private int resource;
    private static final String TAG = "PRUEBA";

    public IndexAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<hotelResult> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final hotelResult item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);

        ImageView imagen =(ImageView)v.findViewById(R.id.imageView3);
        TextView nombre =(TextView)v.findViewById(R.id.nombre);
        TextView direccion =(TextView)v.findViewById(R.id.direccion);
        final TextView precio =(TextView)v.findViewById(R.id.precio);
        RatingBar estrellas=(RatingBar)v.findViewById(R.id.estrellas);
        ImageButton descripcion=(ImageButton)v.findViewById(R.id.btn_descripcion);

        nombre.setText(item.getName());
        direccion.setText(item.getAddress());
        estrellas.setRating(item.getCategory());

        float miprecio=item.getTotal();
        String elprecio;
        List<detallesCuartos> detalles=item.getDetalles();
        String hab1,hab2;
        float prec1,prec2;

        if(detalles!=null){

                float x=0,y=0,z=0;
                for(int g=0;g<detalles.size();g++)
                {

                    detallesCuartos w=detalles.get(g);
                    Log.e(TAG, "x': "+position +": "+w.getTotal());

                    if(w.getName()!="No Disponible"){

                        if(w.getName().equals("Habitación Sencilla") || w.getName().equals("Single Room") || w.getName().equals("Single Standard")){
                            x=w.getTotal();
                        }else if(w.getName().equals("Habitación Doble") || w.getName().equals("Double Room")){
                            z=w.getTotal();
                            //Log.e(TAG, "z': "+position +": "+w.getName()+" "+z);
                        }
                    }else
                    {
                        y=y+1;
                    }

                }
                //x=1000 z=2000 y=3
            //Log.e(TAG, "posicion': "+position +": "+x);
            if(y>x && y>z){
                    precio.setText("No Disponible");
                }else if (x>0 && x>y && x<z && z>0){
                    precio.setText(x+" MXN");
                }else if(x>0 && x>y && x>z && z==0){
                    precio.setText(x+" MXN");
                }else if(z>x && x==0 && z>y){
                    precio.setText(z+" MXN");
                }else if (z<x && x>0 && x>y ){
                    precio.setText(z+" MXN");
            }

        }else{
            precio.setText("No Disponible");
        }

        List<indexImagenes> indexImg=item.getImages();
        indexImagenes q=indexImg.get(1);


        Glide.with(context)
                .load(q.getUrl())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imagen);

        descripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewDescripcion=new Intent(context,descripActivity.class);
                Bundle hotel=new Bundle();
                hotel.putString("propiedad",item.getPropertyNumber());
                hotel.putDouble("estandar",item.getTotal());
                hotel.putDouble("europeo",item.getTotal2());
                ViewDescripcion.putExtras(hotel);
                ViewDescripcion.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(ViewDescripcion);
            }
        });

        return v;
    }
}
