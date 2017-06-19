package itesloscabos.com.hotelapp.Models;

/**
 * Created by croni on 17/06/2017.
 */

public class Currency {
    public float exchengeRate;
    public String baseCode;

    public float getExchengeRate() {
        return exchengeRate;
    }

    public void setExchengeRate(float exchengeRate) {
        this.exchengeRate = exchengeRate;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
}
