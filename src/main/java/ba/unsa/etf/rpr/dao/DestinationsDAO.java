package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Destinations;
import java.util.List;

public interface DestinationsDAO extends DAO<Destinations, Integer>{
    List<Destinations> searchByCity(String nameOfCity);

    List<Destinations> searchByCountry(String nameOfCountry);
}
