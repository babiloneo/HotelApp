package itesloscabos.com.hotelapp.Models.HoldSell;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone")
    @Expose
    public String phone;


    public Customer() {
    }

    public Customer(String id, String name, String lastname, String email, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

}