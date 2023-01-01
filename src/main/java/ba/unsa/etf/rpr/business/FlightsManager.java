package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.exceptions.FlightsException;
import ba.unsa.etf.rpr.dao.DaoFactory;

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


}
