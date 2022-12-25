package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Arrival;

import java.util.Date;
import java.util.List;

public class ArrivalDaoImplementation extends SQLConnection implements ArrivalDao{
    /**
     * @param cityOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByCity(String cityOfDeparture) {
        return null;
    }

    /**
     * @param countryOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByCountry(String countryOfDeparture) {
        return null;
    }

    /**
     * @param dateOfDeparture
     * @return
     */
    @Override
    public List<Arrival> getByDate(Date dateOfDeparture) {
        return null;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Arrival update(Arrival item) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Arrival getById(Integer id) {
        return null;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Arrival add(Arrival item) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {

    }

    /**
     * @return
     */
    @Override
    public List<Arrival> getAll() {
        return null;
    }
}
