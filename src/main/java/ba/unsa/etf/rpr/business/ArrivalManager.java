package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.List;

public class ArrivalManager {
    public void delete(int id) throws FlightsException {
            DaoFactory.arrivalDao().delete(id);
    }

    public List<Arrival> getAll() throws FlightsException{
        return DaoFactory.arrivalDao().getAll();
    }

    public Arrival add(Arrival arrival) throws FlightsException{
        try{
            return DaoFactory.arrivalDao().add(arrival);
        }catch (Exception e){
            if(e.getMessage().contains("UQ_NAME")){
                throw new FlightsException(e.getMessage(), e);
            }
            throw e;
        }
    }
}
