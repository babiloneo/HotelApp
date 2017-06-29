package itesloscabos.com.hotelapp.Models.HoldSell;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("quantity")
    @Expose
    public Integer quantity;

    public Product() {
    }

    public Product(String name, String amount, Integer quantity) {
        super();
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
    }

}