package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;

import java.util.Date;
import java.util.List;

public interface FlightsDAO<T> extends DAO {
    List<Flights> searchByDate(Date dateOfFlight);

    List<Flights> searchByAirline(String nameOfAirline);
}
