package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Flights {
    private int id;
    private String nameOfAirline;
    private int maxNumberOfPassengers, maxNumberOfBusinessClass, priceEconomyClass, priceBusinessClass;
    private Departure destination;
    private Date dateOfDeparture;

    public Flights(int id, String nameOfAirline, int maxNumberOfPassengers, int maxNumberOfBusinessClass, int priceEconomyClass, int priceBusinessClass, Departure destination, Date dateOfDeparture) {
        this.id = id;
        this.nameOfAirline = nameOfAirline;
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        this.maxNumberOfBusinessClass = maxNumberOfBusinessClass;
        this.priceEconomyClass = priceEconomyClass;
        this.priceBusinessClass = priceBusinessClass;
        this.destination = destination;
        this.dateOfDeparture = dateOfDeparture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfAirline() {
        return nameOfAirline;
    }

    public void setNameOfAirline(String nameOfAirline) {
        this.nameOfAirline = nameOfAirline;
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    public Departure getDestination() {
        return destination;
    }

    public void setDestination(Departure destination) {
        this.destination = destination;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public Flights() {
    }

    public int getMaxNumberOfBusinessClass() {
        return maxNumberOfBusinessClass;
    }

    public void setMaxNumberOfBusinessClass(int maxNumberOfBusinessClass) {
        this.maxNumberOfBusinessClass = maxNumberOfBusinessClass;
    }

    public int getPriceEconomyClass() {
        return priceEconomyClass;
    }

    public void setPriceEconomyClass(int priceEconomyClass) {
        this.priceEconomyClass = priceEconomyClass;
    }

    public int getPriceBusinessClass() {
        return priceBusinessClass;
    }

    public void setPriceBusinessClass(int priceBusinessClass) {
        this.priceBusinessClass = priceBusinessClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flights = (Flights) o;
        return id == flights.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfAirline, maxNumberOfPassengers, maxNumberOfBusinessClass, priceEconomyClass, priceBusinessClass, destination, dateOfDeparture);
    }
}
