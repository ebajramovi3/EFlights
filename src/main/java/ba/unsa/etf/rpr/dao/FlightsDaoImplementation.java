package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FlightsDaoImplementation extends AbstractDao<Flights> implements FlightsDAO{

    public FlightsDaoImplementation() {
        super("Flights");
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
            flight.setNameOfAirline(rs.getString("airline_name"));
            flight.setMaxNumberOfPassengers(rs.getInt("max_passengers"));
            flight.setMaxNumberOfBusinessClass(rs.getInt("max_business_class"));
            flight.setPriceBusinessClass(rs.getInt("price_business"));
            flight.setPriceEconomyClass(rs.getInt("price_economy"));
            flight.setDestination(new DepartureDaoImplementation().getById(rs.getInt("departureId")));

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


        item.put("id", object.getNameOfAirline());
        item.put("airline_name", object.getDestination().getId());
        item.put("max_passengers", object.getMaxNumberOfPassengers());
        item.put("max_business_class", object.getMaxNumberOfBusinessClass());
        item.put("price_economy", object.getPriceEconomyClass());
        item.put("price_business", object.getPriceBusinessClass());
        item.put("departureId", object.getDestination().getId());

        return item;
    }

    /**
     * @param dateOfFlight
     * @return
     */
    @Override
    public List<Flights> getByDate(Date dateOfFlight) throws FlightsException{
        return executeQuery("SELECT * FROM Departure WHERE date = ?", new Object[]{dateOfFlight});
    }
}
