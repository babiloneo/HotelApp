package itesloscabos.com.hotelapp.cliente;

import itesloscabos.com.hotelapp.HotelAPi.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by croni on 24/05/2017.
 */

public class clients {
    // Instancia de este singleton
    private static clients instance = null;

    // Cliente de Retrofit
    private Retrofit client;

    // Servicio
    private Service service;

    // Constructor privado
    private clients() {
        // Instanciamos la clase Retrofit que en este caso servirá como nuestro cliente.

        // Hay que enviarle la URL Base del servicio, así como el convertidor
        // En este caso usamos el convertidor GSON para convertir del JSON a los POJOs.
        client = new Retrofit.Builder()
                .baseUrl("http://test.univisit.com/cubaapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creamos la instancia del servicio, que es la que expone los métodos que definimos en la interfaz.
        service = client.create(Service.class);
    }
    // Método para acceder a la instancia de este singleton
    public static clients getInstance() {
        if (instance == null)
            instance = new clients();

        return instance;
    }

    // Me regresa la instancia de este servicio.
    public Service getService() {
        return service;
    }
}
