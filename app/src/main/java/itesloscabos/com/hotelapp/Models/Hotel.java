package itesloscabos.com.hotelapp.Models;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {

    private int count;
    private String processTime;
    private Boolean cachedResponse;
    private Boolean success;
    private List<hotelResult>result;

    public List<hotelResult> getResult() {
        return result;
    }

    public void setResult(List<hotelResult> result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }




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
}
