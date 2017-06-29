package itesloscabos.com.hotelapp.Models.HoldSell;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSell {

    @SerializedName("cachedResponse")
    @Expose
    private Boolean cachedResponse;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private List<ResultSell> result = null;

    public Boolean getCachedResponse() {
        return cachedResponse;
    }

    public void setCachedResponse(Boolean cachedResponse) {
        this.cachedResponse = cachedResponse;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ResultSell> getResult() {
        return result;
    }

    public void setResult(List<ResultSell> result) {
        this.result = result;
    }

}