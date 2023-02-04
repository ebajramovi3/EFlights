package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.ResultSet;
import java.util.*;

public class FlightsDaoImplementation extends AbstractDao<Flights> implements FlightsDAO{

    public FlightsDaoImplementation() {
        super("flight");
    }

    /**
     * @param rs
     * @return
     * @throws FlightsException
     */
    @Override
    public Flights row2object(ResultSet rs) throws FlightsException {
        Flights flight = new Flights();

        try {
            flight.setId(rs.getInt("id"));
            flight.setCityOfDeparture(rs.getString("DepartureDestination"));
            flight.setCityOfArrival(rs.getString("ArrivalDestination"));
            flight.setDate(rs.getDate("date").toLocalDate());
            flight.setNameOfAirline(rs.getString("Airline"));

            return flight;
        }catch (Exception exception){
            throw new FlightsException(exception.getMessage(), exception);
        }
    }
    /**
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row(Flights object) {
        Map<String, Object> item = new TreeMap<>();

        item.put("id", object.getId());
        item.put("DepartureDestination", object.getCityOfDeparture());
        item.put("ArrivalDestination", object.getCityOfArrival());
        item.put("date", object.getDate());
        item.put("Airline", object.getNameOfAirline());

        return item;
    }

    /**
     * @param dateOfFlight
     * @return
     */
    @Override
    public List<Flights> getByDate(Date dateOfFlight) throws FlightsException{
        return executeQuery("SELECT * FROM flight WHERE date = ?", new Object[]{dateOfFlight});
    }

    /**
     * @param cityOfArrival
     * @return
     * @throws FlightsException
     */
    @Override
    public List<Flights> getByArrival(String cityOfArrival) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE ArrivalDestination = ?", new Object[]{cityOfArrival});
    }

    /**
     * @param cityOfDeparture
     * @return
     * @throws FlightsException
     */
    @Override
    public List<Flights> getByDeparture(String cityOfDeparture) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE DepartureDestination = ?", new Object[]{cityOfDeparture});
    }

    /**
     * @param flight
     * @return
     * @throws FlightsException
     */
    @Override
    public List<Flights> searchFlight(Flights flight) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE ArrivalDestination = ? AND date = ? AND DepartureDestination = ? ", new Object[]{flight.getCityOfArrival(), flight.getDate(), flight.getCityOfDeparture()});
    }

    /**
     * @param cityOfArrival
     * @param cityOfDeparture
     * @return
     * @throws FlightsException
     */
    @Override
    public List<Flights> searchByArrivalDeparture(String cityOfArrival, String cityOfDeparture) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE ArrivalDestination = ? AND DepartureDestination = ?", new Object[]{ cityOfArrival, cityOfDeparture});
    }

}
