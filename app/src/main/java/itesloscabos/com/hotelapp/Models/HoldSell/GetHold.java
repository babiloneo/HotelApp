package itesloscabos.com.hotelapp.Models.HoldSell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import itesloscabos.com.hotelapp.Models.CurrencyInfo;
import itesloscabos.com.hotelapp.Models.Provider;


public class GetHold {
    @SerializedName("chainCode")
    @Expose
    private String chainCode;
    @SerializedName("propertyNumber")
    @Expose
    private String propertyNumber;
    @SerializedName("provider")
    @Expose
    private List<Provider> provider = null;
    @SerializedName("filters")
    @Expose
    private List<Object> filters = null;
    @SerializedName("currencyInfo")
    @Expose
    private CurrencyInfo currencyInfo;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("warnings")
    @Expose
    private List<Object> warnings = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("processTime")
    @Expose
    private String processTime;
    @SerializedName("cachedResponse")
    @Expose
    private Boolean cachedResponse;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private List<ResultHold> result = null;

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public List<Provider> getProvider() {
        return provider;
    }

    public void setProvider(List<Provider> provider) {
        this.provider = provider;
    }

    public List<Object> getFilters() {
        return filters;
    }

    public void setFilters(List<Object> filters) {
        this.filters = filters;
    }

    public CurrencyInfo getCurrencyInfo() {
        return currencyInfo;
    }

    public void setCurrencyInfo(CurrencyInfo currencyInfo) {
        this.currencyInfo = currencyInfo;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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

    public List<ResultHold> getResult() {
        return result;
    }

    public void setResult(List<ResultHold> result) {
        this.result = result;
    }

}
