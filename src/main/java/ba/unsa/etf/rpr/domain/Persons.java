package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Persons {
    private String passportId, firstName, lastName;
    private Flights destination;
    private String citizenship;
    private Date dateOfBirth;

    public Persons(String passportId, String firstName, String lastName, Flights destination, String citizenship, Date dateOfBirth) {
        this.passportId = passportId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.destination = destination;
        this.citizenship = citizenship;
        this.dateOfBirth = dateOfBirth;
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

    public Flights getDestination() {
        return destination;
    }

    public void setDestination(Flights destination) {
        this.destination = destination;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return passportId.equals(persons.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportId, firstName, lastName, destination, citizenship, dateOfBirth);
    }
}
