package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

/**
 * MySQL Implementation of DAO
 * @author Esma Bajramovic
 */
public class FlightsDaoImplementation extends AbstractDao<Flights> implements FlightsDAO{

    public FlightsDaoImplementation() {
        super("flight");
    }

    @Override
    public Flights row2object(ResultSet rs) throws FlightsException {
        Flights flight = new Flights();

        try {
            flight.setId(rs.getInt("id"));
            flight.setCityOfDeparture(rs.getString("DepartureDestination"));
            flight.setCityOfArrival(rs.getString("ArrivalDestination"));
            java.sql.Date date = rs.getDate("date");
            if(date == null)
                flight.setDate(null);
            else
                flight.setDate(date.toLocalDate());
            flight.setNameOfAirline(rs.getString("Airline"));

        }catch (Exception exception){
            //throw new FlightsException(exception.getMessage(), exception);
            System.out.println(exception.getCause());
        }
        return flight;
    }

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

    @Override
    public List<Flights> getByDate(LocalDate dateOfFlight) throws FlightsException{
        return executeQuery("SELECT * FROM flight WHERE date = ?", new Object[]{dateOfFlight});
    }

    @Override
    public List<Flights> getByCurrentDate() throws FlightsException{
        return executeQuery("SELECT * FROM flight WHERE date >= CURDATE()", new Object[]{});
    }


    @Override
    public List<Flights> getByArrival(String cityOfArrival) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE ArrivalDestination = ? AND date >= CURDATE()", new Object[]{cityOfArrival});
    }

    @Override
    public List<Flights> getByDeparture(String cityOfDeparture) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE DepartureDestination = ? AND date >= CURDATE()", new Object[]{cityOfDeparture});
    }

    @Override
    public List<Flights> searchFlight(Flights flight) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE ArrivalDestination = ? AND date = ?  AND date >= CURDATE() AND DepartureDestination = ? ", new Object[]{flight.getCityOfArrival(), flight.getDate(), flight.getCityOfDeparture()});
    }

    @Override
    public List<Flights> searchByArrivalDeparture(String cityOfArrival, String cityOfDeparture) throws FlightsException {
        return executeQuery("SELECT * FROM flight WHERE ArrivalDestination = ? AND DepartureDestination = ? AND date >= CURDATE()", new Object[]{ cityOfArrival, cityOfDeparture});
    }

}
