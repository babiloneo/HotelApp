package itesloscabos.com.hotelapp.Models.HoldSell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuestList {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;
    @SerializedName("officePhone")
    @Expose
    private String officePhone;
    @SerializedName("mobilePhone")
    @Expose
    private String mobilePhone;
    @SerializedName("workPhone")
    @Expose
    private String workPhone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("specialRequest")
    @Expose
    private String specialRequest;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zipCode")
    @Expose
    private String zipCode;
    @SerializedName("isChildren")
    @Expose
    private Boolean isChildren;
    @SerializedName("age")
    @Expose
    private Integer age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Boolean getIsChildren() {
        return isChildren;
    }

    public void setIsChildren(Boolean isChildren) {
        this.isChildren = isChildren;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GuestList(String firstName, String lastName, String address, String homePhone, String officePhone, String mobilePhone, String workPhone, String email, String specialRequest, String country, String state, String city, String zipCode, Boolean isChildren, Integer age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homePhone = homePhone;
        this.officePhone = officePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;
        this.specialRequest = specialRequest;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.isChildren = isChildren;
        this.age = age;
    }

    public GuestList() {
    }
}