package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Esma Bajramovic
 */

public class PersonsDaoImplementation extends AbstractDao<Persons> implements PersonsDAO{

    public PersonsDaoImplementation() {
        super("Persons");
    }

    @Override
    public Persons row2object(ResultSet rs) throws FlightsException {
        try{
            Persons person = new Persons();

            person.setId(rs.getInt("id"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            java.sql.Date date = rs.getDate("date_of_birth");
            if(date == null)
                person.setDateOfBirth(null);
            else
                person.setDateOfBirth(date.toLocalDate());
            person.setCitizenship(rs.getString("citizenship"));
            person.setCheckIn(rs.getBoolean("check_in"));
            person.setFlight(new FlightsDaoImplementation().getById(rs.getInt("flightId")));

            return person;
        } catch (Exception exception){
            throw new FlightsException(exception.getMessage(), exception);
        }
    }

    @Override
    public Map<String, Object> object2row(Persons object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("first_name", object.getFirstName());
        item.put("last_name", object.getLastName());
        item.put("date_of_birth", object.getDateOfBirth());
        item.put("citizenship", object.getCitizenship());
        item.put("check_in", object.isCheckIn());
        item.put("flightId", object.getFlight().getId());
        return item;
    }
}
