package itesloscabos.com.hotelapp.Models;

import java.util.List;

/**
 * Created by croni on 24/05/2017.
 */

public class datos {
    private String name;
    private String address;
    private String city;
    private int category;
    private List<indexImagenes> images;
    private double latitude;
    private double longitude;
    private String state;
    private String country;
    private List<rooms>rooms;

    public List<itesloscabos.com.hotelapp.Models.rooms> getRooms() {
        return rooms;
    }

    public void setRooms(List<itesloscabos.com.hotelapp.Models.rooms> rooms) {
        this.rooms = rooms;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    public List<indexImagenes> getImages() {
        return images;
    }

    public void setImages(List<indexImagenes> images) {
        this.images = images;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
