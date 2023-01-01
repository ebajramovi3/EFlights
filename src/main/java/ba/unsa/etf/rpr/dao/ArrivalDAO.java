package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.exceptions.FlightsException;


import java.util.Date;
import java.util.List;

public interface ArrivalDAO extends DAO<Arrival>{
    List<Arrival> getByCity(String cityOfDeparture) throws FlightsException;

    List<Arrival> getByCountry(String countryOfDeparture) throws FlightsException;

    List<Arrival> getByDate(Date dateOfDeparture) throws FlightsException;
}
