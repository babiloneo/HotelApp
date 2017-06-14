package itesloscabos.com.hotelapp.Models;

import java.util.List;

/**
 * Created by croni on 29/05/2017.
 */

public class cuartos {
    private String code;
    private String name;
    private List<datosCuarto> rates;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<datosCuarto> getRates() {
        return rates;
    }

    public void setRates(List<datosCuarto> rates) {
        this.rates = rates;
    }
}
