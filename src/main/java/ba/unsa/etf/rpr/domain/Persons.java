package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Persons implements Idable{
    private int passportId;
    private String firstName, lastName, citizenship;
    private Date dateOfBirth;
    private boolean checkIn, businessClass;
    private Flights flight;

    public Persons(int passportId, String firstName, String lastName, String citizenship, Date dateOfBirth, boolean checkIn, boolean businessClass, Flights flight) {
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
        return passportId == persons.passportId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportId, firstName, lastName, citizenship, dateOfBirth, checkIn, businessClass, flight);
    }

    /**
     * @param id
     */
    @Override
    public void setId(int id) {
        this.passportId = id;
    }

    /**
     * @return
     */
    @Override
    public int getId() {
        return passportId;
    }
}
