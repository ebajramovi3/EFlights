package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Flights {
    private int id;
    private String nameOfAirline;
    private int maxNumberOfPassengers;
    private Destinations destination;
    private Persons pilot;
    private Date dateOfDeparture;

    public Flights(int id, String nameOfAirline, int maxNumberOfPassengers, Destinations destination, Persons pilot, Date dateOfDeparture) {
        this.id = id;
        this.nameOfAirline = nameOfAirline;
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        this.destination = destination;
        this.pilot = pilot;
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

    public Destinations getDestination() {
        return destination;
    }

    public void setDestination(Destinations destination) {
        this.destination = destination;
    }

    public Persons getPilot() {
        return pilot;
    }

    public void setPilot(Persons pilot) {
        this.pilot = pilot;
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
        Flights flights = (Flights) o;
        return id == flights.id && maxNumberOfPassengers == flights.maxNumberOfPassengers && nameOfAirline.equals(flights.nameOfAirline) && destination.equals(flights.destination) && pilot.equals(flights.pilot) && dateOfDeparture.equals(flights.dateOfDeparture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfAirline, maxNumberOfPassengers, destination, pilot, dateOfDeparture);
    }
}
