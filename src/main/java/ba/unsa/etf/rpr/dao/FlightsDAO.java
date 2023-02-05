package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.time.LocalDate;
import java.util.List;

/**
 * Dao interface for Flights domain bean
 *
 * @author Esma Bajramovic
 */
public interface FlightsDAO extends  DAO<Flights>{
    /**
     * returns all flights based on given date
     *
     * @param dateOfFlight
     * @return list of Flights objects
     * @throws FlightsException
     */
    List<Flights> getByDate(LocalDate dateOfFlight) throws FlightsException;

    /**
     * returns all flights based on current date
     *
     * @return list of Flights objects
     * @throws FlightsException
     */
    List<Flights> getByCurrentDate() throws FlightsException;

    /**
     * returns all flights based on city of arrival
     * @param cityOfArrival
     * @return list of Flights objects
     * @throws FlightsException
     */
    List<Flights> getByArrival(String cityOfArrival) throws FlightsException;

    /**
     * returns all flights based on city of departure
     * @param cityOfDeparture
     * @return list of Flights objects
     * @throws FlightsException
     */
    List<Flights> getByDeparture(String cityOfDeparture) throws FlightsException;

    /**
     * returns all flights based on city of departure, city of arrival and exact date
     * @param flight
     * @return list of Flights objects
     * @throws FlightsException
     */
    List<Flights> searchFlight(Flights flight) throws FlightsException;

    /**
     * returns all flights based on city of departure, city of arrival and dates from date given as param
     *
     * @param cityOfArrival
     * @param cityOfDeparture
     * @return list of Flights objects
     * @throws FlightsException
     */
    List<Flights> searchByArrivalDeparture(String cityOfArrival, String cityOfDeparture) throws FlightsException;

}
