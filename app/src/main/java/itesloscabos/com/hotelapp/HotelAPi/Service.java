package itesloscabos.com.hotelapp.HotelAPi;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.LoginRespuesta;
import itesloscabos.com.hotelapp.Models.descripcion;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {
    @FormUrlEncoded
    @POST("login")
    Call<LoginRespuesta>  obtenerListaLogin(@Field("grant_type") String grantType, @Field("username") String username,
                                            @Field("password") String password);



    @GET("v1/Hotel/Index")
    Call<Hotel> ObtenerListaHotel(@Header("Authorization") String token,
                                  @Header("X-Environment") String Environment,
                                  @Query("iata") String iata, @Query("refPoint") String refPoint,
                                  @Query("checkIn") String checkIn, @Query("checkOut") String checkOut,
                                  @Query("rooms") String rooms, @Query("adults") String adults,
                                  @Query("children") String children);
    @GET("v1/Hotel/Description")
    Call<descripcion> getDescripciones(@Header("Authorization") String token,
                                    @Header("X-Environment") String Environment,
                                    @Query("propertyNumber") String propertyNumber);
}