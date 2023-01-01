package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departure;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.Date;
import java.util.List;

public interface DepartureDAO extends DAO<Departure>{
    List<Departure> getByCity(String cityOfDeparture) throws FlightsException;

    List<Departure> getByCountry(String countryOfDeparture) throws FlightsException;

    List<Departure> getByDate(Date dateOfDeparture) throws FlightsException;

}
