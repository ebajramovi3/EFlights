package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Departure;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DepartureDaoImplementation extends AbstractDao<Departure> implements DepartureDAO{

    public DepartureDaoImplementation() {
        super("Departure");
    }

    /**
     * @param rs
     * @return
     * @throws FlightsException
     */
    @Override
    public Departure row2object(ResultSet rs) throws FlightsException {
        try{
                Departure departure = new Departure();

                departure.setId(rs.getInt("id"));
                departure.setCountry(rs.getString("country"));
                departure.setCity("city");
                departure.setDateOfDeparture(rs.getDate("date"));
                departure.setArrival(new ArrivalDaoImplementation().getById(rs.getInt("arrivalId")));

                return departure;
        } catch (SQLException e) {
            throw new FlightsException(e.getMessage(), e);
        }
    }

    /**
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row(Departure object) {
        Map<String, Object> item = new TreeMap<>();

        item.put("id", object.getId());
        item.put("country", object.getCountry());
        item.put("city", object.getCity());
        java.sql.Date sqlDate = new java.sql.Date(object.getDateOfDeparture().getYear(), object.getDateOfDeparture().getMonth(), object.getDateOfDeparture().getDay() + 1);
        item.put("date", sqlDate.toString());
        item.put("arrivalId", object.getArrival().getId());

        return item;
    }

    /**
     * @param cityOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByCity(String cityOfDeparture) throws FlightsException {
        return executeQuery("SELECT * FROM Departure WHERE city = ?", new Object[]{cityOfDeparture});
    }

    /**
     * @param countryOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByCountry(String countryOfDeparture) throws FlightsException {
        return executeQuery("SELECT * FROM Departure WHERE country = ?", new Object[]{countryOfDeparture});
    }

    /**
     * @param dateOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByDate(Date dateOfDeparture) throws FlightsException {
        return executeQuery("SELECT * FROM Departure WHERE date = ?", new Object[]{dateOfDeparture});
    }
}
