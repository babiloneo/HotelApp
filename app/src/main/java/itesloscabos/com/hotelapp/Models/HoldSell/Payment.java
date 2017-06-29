package itesloscabos.com.hotelapp.Models.HoldSell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("expire")
    @Expose
    public String expire;
    @SerializedName("cancelationFee")
    @Expose
    public String cancelationFee;
    @SerializedName("returnUrl")
    @Expose
    public String returnUrl;
    @SerializedName("errorUrl")
    @Expose
    public String errorUrl;
    @SerializedName("customer")
    @Expose
    public Customer customer;
    @SerializedName("product")
    @Expose
    public Product product;
    @SerializedName("data")
    @Expose
    public String data;
    @SerializedName("number")
    @Expose
    public String number;
    @SerializedName("expires")
    @Expose
    public String expires;
    @SerializedName("cvcCode")
    @Expose
    public String cvcCode;
    @SerializedName("providerCode")
    @Expose
    public String providerCode;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("transactionId")
    @Expose
    public String transactionId;
    @SerializedName("method")
    @Expose
    public String method;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("language")
    @Expose
    public String language;

    public Payment() {
    }

    public Payment(String expire, String cancelationFee, String returnUrl, String errorUrl, Customer customer, Product product, String data, String number, String expires, String cvcCode, String providerCode, String code, String transactionId, String method, String currency, String language) {
        super();
        this.expire = expire;
        this.cancelationFee = cancelationFee;
        this.returnUrl = returnUrl;
        this.errorUrl = errorUrl;
        this.customer = customer;
        this.product = product;
        this.data = data;
        this.number = number;
        this.expires = expires;
        this.cvcCode = cvcCode;
        this.providerCode = providerCode;
        this.code = code;
        this.transactionId = transactionId;
        this.method = method;
        this.currency = currency;
        this.language = language;
    }

}