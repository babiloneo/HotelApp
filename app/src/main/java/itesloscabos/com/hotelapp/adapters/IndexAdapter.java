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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;

import java.util.List;

import itesloscabos.com.hotelapp.IndexActivity;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.Models.indexImagenes;
import itesloscabos.com.hotelapp.R;
import itesloscabos.com.hotelapp.descripActivity;

/**
 * Created by croni on 21/05/2017.
 */

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final hotelResult item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);

        ImageView imagen =(ImageView)v.findViewById(R.id.imageView3);
        TextView nombre =(TextView)v.findViewById(R.id.nombre);
        TextView direccion =(TextView)v.findViewById(R.id.direccion);
        TextView precio =(TextView)v.findViewById(R.id.precio);
        TextView estrellas=(TextView)v.findViewById(R.id.estrellas);
        ImageButton descripcion=(ImageButton)v.findViewById(R.id.btn_descripcion);

        nombre.setText(item.getName());
        direccion.setText(item.getAddress());
        estrellas.setText(item.getPropertyNumber());
        precio.setText(item.getPhone());
        List<indexImagenes> indexImg=item.getImages();
        indexImagenes q=indexImg.get(1);

        Log.i(TAG,"imagenes"+q.getKeyword()+","+q.getDescription()+","+q.getUrl());

        Glide.with(context)
                .load("http://media-room5.trivago.com/wp-content/uploads/sites/3/2016/11/25114313/hoteles-todo-incluido-canarias-lanzarote-h10-timanfaya-palace-general-id4.jpg")
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
                ViewDescripcion.putExtras(hotel);
                ViewDescripcion.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(ViewDescripcion);
            }
        });

        return v;
    }
}
