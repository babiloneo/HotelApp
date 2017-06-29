package itesloscabos.com.hotelapp.HotelAPi;
import java.util.List;

import itesloscabos.com.hotelapp.Models.HoldSell.GetHold;
import itesloscabos.com.hotelapp.Models.HoldSell.GetPayment;
import itesloscabos.com.hotelapp.Models.HoldSell.GetSell;
import itesloscabos.com.hotelapp.Models.HoldSell.Hold;
import itesloscabos.com.hotelapp.Models.HoldSell.Payment;
import itesloscabos.com.hotelapp.Models.HoldSell.ResultHold;
import itesloscabos.com.hotelapp.Models.Hotel;
import itesloscabos.com.hotelapp.Models.LoginRespuesta;
import itesloscabos.com.hotelapp.Models.Rules.Rules;
import itesloscabos.com.hotelapp.Models.descripcion;
import itesloscabos.com.hotelapp.Models.disponibilidad;
import retrofit2.Call;
import retrofit2.http.Body;
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


    @GET("v1/Hotel/Availability")
    Call<disponibilidad> getDisponibilidad(@Header("Authorization") String token,
                                           @Header("X-Environment") String Environment,
                                           @Query("propertyNumber") String propertyNumber,
                                           @Query("checkIn") String checkIn, @Query("checkOut") String checkOut,
                                           @Query("rooms") String rooms, @Query("adults") String adults,
                                           @Query("children") String children,@Query("iata") String iata);

    @GET("v1/Hotel/Rules")
    Call<Rules> getRules(@Header("Authorization") String token,
                         @Header("X-Environment") String Environment,
                         @Query("rateKey") String rateKey,
                         @Query("transactionId") String transactionId);

    @POST("v1/Hotel/Hold")
    Call<GetHold> getHold(@Body Hold hold,
                          @Header("Authorization") String token,
                          @Header("X-Environment") String Environment);

    @POST("v1/Payment/Payment")
    Call<GetPayment> getPayment(@Body Payment payment,
                                @Header("Authorization") String token,
                                @Header("X-Environment") String Environment);
    @POST("v1/Hotel/Sell")
    Call<GetSell> getSell(@Body Hold Hold,
                          @Header("Authorization") String token,
                          @Header("X-Environment") String Environment);
}
