package itesloscabos.com.hotelapp.Models;

import java.util.List;

/**
 * Created by croni on 29/05/2017.
 */

public class disponibilidad {

    private Boolean success ;
    private String processTime;
    private List<Provider>provider;
    private List<resdispo> result;

    public List<Provider> getProvider() {
        return provider;
    }

    public void setProvider(List<Provider> provider) {
        provider = provider;
    }

    public List<resdispo> getResult() {
        return result;
    }

    public void setResult(List<resdispo> result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }
}
