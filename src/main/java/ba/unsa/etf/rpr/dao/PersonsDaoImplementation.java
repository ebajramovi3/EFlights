package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departure;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PersonsDaoImplementation extends AbstractDao<Persons> implements PersonsDAO{

    public PersonsDaoImplementation() {
        super("Persons");
    }

    /**
     * @param rs
     * @return
     * @throws FlightsException
     */
    @Override
    public Persons row2object(ResultSet rs) throws FlightsException {
        try{
            Persons person = new Persons();

            person.setId(rs.getInt("passportId"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setDateOfBirth(rs.getDate("date_of_birth"));
            person.setCitizenship(rs.getString("citizenship"));
            person.setCheckIn(rs.getBoolean("check_in"));
            person.setFlight(new FlightsDaoImplementation().getById(rs.getInt("flightId")));
            person.setBusinessClass(rs.getBoolean("business_class"));

            return person;
        } catch (Exception exception){
            throw new FlightsException(exception.getMessage(), exception);
        }
    }

    /**
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row(Persons object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("passportId", object.getId());
        item.put("first_name", object.getFirstName());
        item.put("last_name", object.getLastName());
        item.put("date_of_birth", object.getDateOfBirth());
        item.put("citizenship", object.getCitizenship());
        item.put("check_in", object.isCheckIn());
        item.put("Flights", object.getFlight().getId());
        item.put("business_class", object.isBusinessClass());
        return item;
    }
}
