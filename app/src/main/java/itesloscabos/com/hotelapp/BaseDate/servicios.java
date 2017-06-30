package itesloscabos.com.hotelapp.BaseDate;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import itesloscabos.com.hotelapp.Models.Reservaciones.Reservar;

public class servicios {

    private Realm realm;

    public servicios(Realm realm){
        this.realm=realm;
    }

    public void NuevaReservacion(String referencia, String nombre, String direccion, String ciudad, String habitacion, String viajeros, String llegada, String salida, String total, String anombre) {

        realm.beginTransaction();
        Reservar nuevo=realm.createObject(Reservar.class);
        nuevo.setReferencia(referencia);
        nuevo.setNombre(nombre);
        nuevo.setDireccion(direccion);
        nuevo.setCiudad(ciudad);
        nuevo.setHabitacion(habitacion);
        nuevo.setViajeros(viajeros);
        nuevo.setLlegada(llegada);
        nuevo.setSalida(salida);
        nuevo.setTotal(total);
        nuevo.setAnombre(anombre);
        realm.commitTransaction();
    }

    public List<Reservar> obtener(){
        RealmResults<Reservar> r=realm.where(Reservar.class).findAll();
        return r;
    }

}
