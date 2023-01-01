package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.ResultSet;
import java.util.*;

public class ArrivalDaoImplementation extends AbstractDao<Arrival> implements ArrivalDAO {

    public ArrivalDaoImplementation() {
        super("Arrival");
    }

    /**
     * @param rs
     * @return
     * @throws FlightsException
     */
    @Override
    public Arrival row2object(ResultSet rs) throws FlightsException {
        try {
            Arrival arrival = new Arrival();
            arrival.setId(rs.getInt("id"));
            arrival.setCountry(rs.getString("country"));
            arrival.setCity(rs.getString("city"));
            arrival.setDateOfArrival(rs.getDate("date_of_arrival"));

            return arrival;
        }catch (Exception exception){
            throw new FlightsException(exception.getMessage(), exception);
        }
    }

    /**
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row(Arrival object) {
        Map<String, Object> item = new TreeMap<>();

        item.put("id", object.getId());
        item.put("country", object.getCountry());
        item.put("city", object.getCity());
        java.sql.Date sqlDate = new java.sql.Date(object.getDateOfArrival().getYear(), object.getDateOfArrival().getMonth(), object.getDateOfArrival().getDay() + 1);
        item.put("date", sqlDate.toString());

        return item;
    }

    /**
     * @param cityOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByCity(String cityOfDeparture) throws FlightsException{
        return executeQuery("SELECT * FROM Arrival WHERE city = ?", new Object[]{cityOfDeparture});
    }

    /**
     * @param countryOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByCountry(String countryOfDeparture) throws FlightsException{
        return executeQuery("SELECT * FROM Arrival WHERE country = ?", new Object[]{countryOfDeparture});
    }

    /**
     * @param dateOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByDate(Date dateOfDeparture) throws FlightsException{
        return executeQuery("SELECT * FROM Arrival WHERE date = ?", new Object[]{dateOfDeparture});
    }
}
