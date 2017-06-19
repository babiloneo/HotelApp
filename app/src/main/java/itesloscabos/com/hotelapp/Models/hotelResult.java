package itesloscabos.com.hotelapp.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by croni on 19/05/2017.
 */

public class hotelResult {

    private String propertyNumber;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String city;
    private String country;
    private String phone;
    private int category;
    private String fax;
    private List<indexImagenes> images;
    private float total;
    private float total2;
    private List<detallesCuartos> detalles;

    public List<detallesCuartos> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<detallesCuartos> detalles) {
        this.detalles = detalles;
    }

    public float getTotal2() {
        return total2;
    }

    public void setTotal2(float total2) {
        this.total2 = total2;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<indexImagenes> getImages() {
        return images;
    }

    public void setImages(List<indexImagenes> images) {
        this.images = images;
    }



    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    private String shortDescription;

}
