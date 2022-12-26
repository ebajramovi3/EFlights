package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;

import java.util.Date;
import java.util.List;

public interface FlightsDAO<T> extends DAO<Flights, Integer> {
    List<Flights> getByDate(Date dateOfFlight);

    List<Flights> getByAirline(String nameOfAirline);
}
