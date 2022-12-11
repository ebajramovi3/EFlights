package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Destinations;
import java.util.List;

public interface DestinationsDAO<T> extends DAO{

    List<Destinations> searchByCity();

    List<Destinations> searchByCountry();

    List<Destinations> getById();
}
