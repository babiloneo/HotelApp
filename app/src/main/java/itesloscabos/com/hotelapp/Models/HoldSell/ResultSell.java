package itesloscabos.com.hotelapp.Models.HoldSell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by croni on 29/06/2017.
 */

public class ResultSell {

    @SerializedName("providerReference")
    @Expose
    private String providerReference;
    @SerializedName("detail")
    @Expose
    private Detail detail;

    public String getProviderReference() {
        return providerReference;
    }

    public void setProviderReference(String providerReference) {
        this.providerReference = providerReference;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
