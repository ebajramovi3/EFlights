package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.Date;
import java.util.List;

public interface FlightsDAO extends  DAO<Flights>{
    List<Flights> getByDate(Date dateOfFlight) throws FlightsException;

    List<Flights> getByArrival(String cityOfArrival) throws FlightsException;

    List<Flights> getByDeparture(String cityOfDeparture) throws FlightsException;

    List<Flights> searchFlight(Flights flight) throws FlightsException;
}
