package itesloscabos.com.hotelapp.Models.HoldSell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hold {
    @SerializedName("onHold")
    @Expose
    private Boolean onHold;
    @SerializedName("guestList")
    @Expose
    private List<GuestList> guestList = null;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("ignoreCache")
    @Expose
    private Boolean ignoreCache;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("filter")
    @Expose
    private String filter;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("paymentTicket")
    @Expose
    private String paymentTicket;

    public Boolean getOnHold() {
        return onHold;
    }

    public void setOnHold(Boolean onHold) {
        this.onHold = onHold;
    }

    public List<GuestList> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<GuestList> guestList) {
        this.guestList = guestList;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIgnoreCache() {
        return ignoreCache;
    }

    public void setIgnoreCache(Boolean ignoreCache) {
        this.ignoreCache = ignoreCache;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPaymentTicket() {
        return paymentTicket;
    }

    public void setPaymentTicket(String paymentTicket) {
        this.paymentTicket = paymentTicket;
    }

    public Hold(Boolean onHold, List<GuestList> guestList, Contact contact, String language, String currency, Boolean ignoreCache, String transactionId, String filter, String order, String paymentTicket) {
        super();
        this.onHold = onHold;
        this.guestList = guestList;
        this.contact = contact;
        this.language = language;
        this.currency = currency;
        this.ignoreCache = ignoreCache;
        this.transactionId = transactionId;
        this.filter = filter;
        this.order = order;
        this.paymentTicket = paymentTicket;
    }

    public Hold(){
    }
}

