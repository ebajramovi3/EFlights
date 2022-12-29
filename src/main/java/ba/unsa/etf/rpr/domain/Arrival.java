package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Arrival {
    private int arrivalId;
    private String city, country;
    private Date dateOfArrival;

    public Arrival(int arrivalId, String city, String country, Date dateOfArrival) {
        this.arrivalId = arrivalId;
        this.city = city;
        this.country = country;
        this.dateOfArrival = dateOfArrival;
    }

    public Arrival() {
    }


    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
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

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arrival arrival = (Arrival) o;
        return arrivalId == arrival.arrivalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalId, city, country, dateOfArrival);
    }
}
