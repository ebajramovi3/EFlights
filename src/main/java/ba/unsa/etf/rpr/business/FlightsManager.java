package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import ba.unsa.etf.rpr.dao.DaoFactory;

import java.util.List;

public class FlightsManager {
    public void delete(int id) throws FlightsException {
        try {
            DaoFactory.flightsDao().delete(id);
        } catch (FlightsException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new FlightsException("Cannot delete flight which is related to destination/departure. First delete related destination before deleting flight.");
            }
            throw e;
        }
    }

    public List<Flights> getAll() throws FlightsException{
        return DaoFactory.flightsDao().getAll();
    }

    public Flights add(Flights flight) throws FlightsException{
        try{
            return DaoFactory.flightsDao().add(flight);
        }catch (Exception e){
            if(e.getMessage().contains("UQ_NAME")){
                throw new FlightsException(e.getMessage(), e);
            }
            throw e;
        }
    }

    public List<Flights> searchFlight(Flights flight) throws FlightsException{
        return DaoFactory.flightsDao().searchFlight(flight);
    }

    public List<Flights> searchDepartures(String cityOfDeparture) throws FlightsException{
        return DaoFactory.flightsDao().getByDeparture(cityOfDeparture);
    }

    public List<Flights> searchArrivals(String cityOfArrival) throws FlightsException{
        return DaoFactory.flightsDao().getByArrival(cityOfArrival);
    }
}
