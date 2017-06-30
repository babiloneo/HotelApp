package itesloscabos.com.hotelapp.Models.Reservaciones;

import io.realm.RealmObject;

/**
 * Created by croni on 29/06/2017.
 */

public class Reservar extends RealmObject {

    String referencia;
    String nombre;
    String direccion;
    String habitacion;
    String viajeros;
    String llegada;
    String salida;
    String total;
    String Anombre;

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getViajeros() {
        return viajeros;
    }

    public void setViajeros(String viajeros) {
        this.viajeros = viajeros;
    }

    public String getLlegada() {
        return llegada;
    }

    public void setLlegada(String llegada) {
        this.llegada = llegada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAnombre() {
        return Anombre;
    }

    public void setAnombre(String anombre) {
        Anombre = anombre;
    }
}
