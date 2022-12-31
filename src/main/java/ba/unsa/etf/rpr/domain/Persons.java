package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Persons {
    private String passportId, firstName, lastName;
    private String citizenship;
    private Date dateOfBirth;
    private boolean checkIn, businessClass;
    private Flights flight;

    public Persons(String passportId, String firstName, String lastName, String citizenship, Date dateOfBirth, boolean checkIn, boolean businessClass, Flights flight) {
        this.passportId = passportId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.citizenship = citizenship;
        this.dateOfBirth = dateOfBirth;
        this.checkIn = checkIn;
        this.businessClass = businessClass;
        this.flight = flight;
    }

    public Persons() {

    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public boolean isBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(boolean businessClass) {
        this.businessClass = businessClass;
    }

    public Flights getFlight() {
        return flight;
    }

    public void setFlight(Flights flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return passportId.equals(persons.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportId, firstName, lastName, destination, citizenship, dateOfBirth, checkIn, businessClass, flight);
    }
}
