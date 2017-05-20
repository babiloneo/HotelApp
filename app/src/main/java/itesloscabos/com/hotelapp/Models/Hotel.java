package itesloscabos.com.hotelapp.Models;


import java.util.ArrayList;

public class Hotel {

    private int count;
    private String processTime;
    private Boolean cachedResponse;
    private Boolean success;
    private ArrayList<hotelResult>result;

    public ArrayList<hotelResult> getResult() {
        return result;
    }

    public void setResult(ArrayList<hotelResult> result) {
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
