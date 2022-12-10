package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * @author Esma Bajramović
 */
public class Destinations {
    private int departureId;
    private String city, country;
    private int arrivalId;

    public Destinations(int departureId, String city, String country, int arrivalId) {
        this.departureId = departureId;
        this.city = city;
        this.country = country;
        this.arrivalId = arrivalId;
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

    public int getArrivalId() {
        return arrivalId;
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

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destinations that = (Destinations) o;
        return departureId == that.departureId && arrivalId == that.arrivalId && city.equals(that.city) && country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureId, city, country, arrivalId);
    }
}
