package itesloscabos.com.hotelapp.Models.HoldSell;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPayment {

    @SerializedName("transactionId")
    @Expose
    private String transactionId;

    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("processTime")
    @Expose
    private String processTime;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private List<ResultPayment> result = null;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ResultPayment> getResult() {
        return result;
    }

    public void setResult(List<ResultPayment> result) {
        this.result = result;
    }

}