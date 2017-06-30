package itesloscabos.com.hotelapp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import itesloscabos.com.hotelapp.Models.Reservaciones.Reservar;
import itesloscabos.com.hotelapp.Models.hotelResult;
import itesloscabos.com.hotelapp.R;

/**
 * Created by croni on 30/06/2017.
 */

public class ReservacionAdapter extends ArrayAdapter<Reservar> {
    private List<Reservar> lista;
    private Context context;
    private int resource;
    public ReservacionAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Reservar> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;
        final Reservar item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);

        TextView nombre=(TextView)v.findViewById(R.id.reser_nombre);
        TextView direccion=(TextView)v.findViewById(R.id.reser_direccion);
        TextView ciudad=(TextView)v.findViewById(R.id.reser_ciudad);
        TextView habitacion=(TextView)v.findViewById(R.id.reser_habitacion);
        TextView personas=(TextView)v.findViewById(R.id.reser_personas);
        TextView fechas=(TextView)v.findViewById(R.id.reser_fecha);
        TextView Anombre=(TextView)v.findViewById(R.id.reser_Anombre);
        TextView referencia=(TextView)v.findViewById(R.id.reser_referencia);

        nombre.setText(item.getNombre());
        direccion.setText(item.getDireccion());
        ciudad.setText(item.getCiudad());
        habitacion.setText(item.getHabitacion());
        personas.setText(item.getViajeros());
        fechas.setText(item.getLlegada()+"-"+item.getSalida());
        Anombre.setText(item.getAnombre());
        referencia.setText(item.getReferencia());

        return v;
    }
}
