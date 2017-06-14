package itesloscabos.com.hotelapp.Models;

import java.util.List;

/**
 * Created by croni on 29/05/2017.
 */

public class resdispo {

    private Boolean available;
    private String propertyNumber;

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<cuartos> getRooms() {
        return rooms;
    }

    public void setRooms(List<cuartos> rooms) {
        this.rooms = rooms;
    }

    private List<cuartos> rooms;


}
