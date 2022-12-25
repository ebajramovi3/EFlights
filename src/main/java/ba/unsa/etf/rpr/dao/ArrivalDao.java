package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Arrival;

import java.util.Date;
import java.util.List;

public interface ArrivalDao extends DAO<Arrival, Integer>{
    List<Arrival> getByCity(String cityOfDeparture);

    List<Arrival> getByCountry(String countryOfDeparture);

    List<Arrival> getByDate(Date dateOfDeparture);
}
