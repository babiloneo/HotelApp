package itesloscabos.com.hotelapp.Models.HoldSell;

import android.telecom.Call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by croni on 28/06/2017.
 */

public class Detail {

    @SerializedName("checkIn")
    @Expose
    private String checkIn;
    @SerializedName("checkOut")
    @Expose
    private String checkOut;
    @SerializedName("confirmationNumber")
    @Expose
    private String confirmationNumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reservationDate")
    @Expose
    private String reservationDate;
    @SerializedName("statusDate")
    @Expose
    private String statusDate;



    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }
}
