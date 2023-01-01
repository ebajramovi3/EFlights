package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import ba.unsa.etf.rpr.dao.DaoFactory;

import java.util.List;

public class FlightsManager {
    public void delete(int categoryId) throws FlightsException {
        try {
            DaoFactory.flightsDao().delete(categoryId);
        } catch (FlightsException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new FlightsException("Cannot delete category which is related to quotes. First delete related quotes before deleting category.");
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
}
