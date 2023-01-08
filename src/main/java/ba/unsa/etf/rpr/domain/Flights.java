package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Flights implements Idable{
    private int id;
    private String nameOfAirline, cityOfDeparture, cityOfArrival;
    private Date date;

    /**
     * @param id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    public Flights(int id, String nameOfAirline, String cityOfDeparture, String cityOfArrival, Date date) {
        this.id = id;
        this.nameOfAirline = nameOfAirline;
        this.cityOfDeparture = cityOfDeparture;
        this.cityOfArrival = cityOfArrival;
        this.date = date;
    }

    public Flights() {
    }

    public String getNameOfAirline() {
        return nameOfAirline;
    }

    public void setNameOfAirline(String nameOfAirline) {
        this.nameOfAirline = nameOfAirline;
    }

    public String getCityOfDeparture() {
        return cityOfDeparture;
    }

    public void setCityOfDeparture(String cityOfDeparture) {
        this.cityOfDeparture = cityOfDeparture;
    }

    public String getCityOfArrival() {
        return cityOfArrival;
    }

    public void setCityOfArrival(String cityOfArrival) {
        this.cityOfArrival = cityOfArrival;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
