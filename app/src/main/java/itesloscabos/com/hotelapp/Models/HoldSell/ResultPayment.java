package itesloscabos.com.hotelapp.Models.HoldSell;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultPayment {
    @SerializedName("mode")
    @Expose
    private Integer mode;
    @SerializedName("method")
    @Expose
    private Integer method;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
