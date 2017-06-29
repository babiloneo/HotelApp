package itesloscabos.com.hotelapp.Models.Rules;


import java.util.List;

public class Rules {
    private Boolean canHold;
    private String transactionId;
    private List<Result> result = null;

    public Boolean getCanHold() {
        return canHold;
    }

    public void setCanHold(Boolean canHold) {
        this.canHold = canHold;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
