package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;

/**
 * @author Esma Bajramovic
 */
public class Flights implements Idable{
    private int id;
    private String nameOfAirline, cityOfDeparture, cityOfArrival;
    private LocalDate date;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public Flights(int id, String nameOfAirline, String cityOfDeparture, String cityOfArrival, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "nameOfAirline='" + nameOfAirline + '\'' +
                ", cityOfDeparture='" + cityOfDeparture + '\'' +
                ", cityOfArrival='" + cityOfArrival + '\'' +
                ", date=" + date +
                '}';
    }

    public Flights getFlight(){
        return this;
    }
}
