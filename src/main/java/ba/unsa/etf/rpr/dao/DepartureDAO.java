package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departure;

import java.util.Date;
import java.util.List;

public interface DepartureDAO extends DAO<Departure, Integer>{
    List<Departure> getByCity(String cityOfDeparture);

    List<Departure> getByCountry(String countryOfDeparture);

    List<Departure> getByDate(Date dateOfDeparture);

}
