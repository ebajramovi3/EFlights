package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * @author Esma Bajramović
 */
public class Departure implements Idable{
    private int departureId;
    private String city, country;
    private Date dateOfDeparture;
    private Arrival arrival;

    public Departure(int departureId, String city, String country, Date dateOfDeparture, Arrival arrival) {
        this.departureId = departureId;
        this.city = city;
        this.country = country;
        this.dateOfDeparture = new Date(dateOfDeparture.getYear() - 1900, dateOfDeparture.getMonth() - 1, dateOfDeparture.getDay() + 4);
        this.arrival = arrival;
    }

    public Departure() {
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
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

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departure departure = (Departure) o;
        return departureId == departure.departureId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureId, city, country, dateOfDeparture, arrival);
    }

    /**
     * @param id
     */
    @Override
    public void setId(int id) {
        this.departureId = id;
    }

    /**
     * @return
     */
    @Override
    public int getId() {
        return departureId;
    }
}
