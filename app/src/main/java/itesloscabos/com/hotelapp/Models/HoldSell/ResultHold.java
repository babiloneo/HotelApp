package itesloscabos.com.hotelapp.Models.HoldSell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by croni on 28/06/2017.
 */

public class ResultHold {
    @SerializedName("providerReference")
    @Expose
    private String providerReference;
    @SerializedName("otaResRetrieveRS")
    @Expose
    private String otaResRetrieveRS;
    @SerializedName("detail")
    @Expose
    private Detail detail;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getProviderReference() {
        return providerReference;
    }

    public void setProviderReference(String providerReference) {
        this.providerReference = providerReference;
    }

    public String getOtaResRetrieveRS() {
        return otaResRetrieveRS;
    }

    public void setOtaResRetrieveRS(String otaResRetrieveRS) {
        this.otaResRetrieveRS = otaResRetrieveRS;
    }

}
