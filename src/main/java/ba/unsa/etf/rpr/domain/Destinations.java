package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * @author Esma Bajramović
 */
public class Destinations {
    private int departureId;
    private String city, country;
    private Destinations arrival;

    public Destinations() {
    }

    public Destinations(int departureId, String city, String country, Destinations arrival) {
        this.departureId = departureId;
        this.city = city;
        this.country = country;
        this.arrival = arrival;
    }

    public int getDepartureId() {
        return departureId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }


    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Destinations getArrival() {
        return arrival;
    }

    public void setArrival(Destinations arrival) {
        this.arrival = arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destinations that = (Destinations) o;
        return departureId == that.departureId && city.equals(that.city) && country.equals(that.country) && arrival.equals(that.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureId, city, country, arrival);
    }
}
