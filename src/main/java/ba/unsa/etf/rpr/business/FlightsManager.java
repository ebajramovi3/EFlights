package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import static java.time.ZoneId.systemDefault;

/**
 * Business Logic Layer for management of Flights
 *
 * @author Esma Bajramovic
 */
public class FlightsManager {
    public void validateCity(String city) throws FlightsException{
        if(city == null || city.trim().length() < 1 || city.trim().length()> 45 || !Pattern.compile("[a-zA-Z]*").matcher(city).matches())
            throw new FlightsException("Invalid city!");
    }

    public void validateAirline(String airlineName) throws FlightsException{
        if(airlineName == null || airlineName.trim().length() < 1 || airlineName.trim().length()> 45 || !Pattern.compile("[a-zA-Z]*").matcher(airlineName).matches())
            throw new FlightsException("Invalid airline!");
    }

    public void validateDate(LocalDate date) throws FlightsException{
        if((date == null) || date.compareTo(Instant.ofEpochMilli(Instant.now().toEpochMilli()).atZone(systemDefault()).toLocalDate()) < 0)
            throw new FlightsException("Invalid date!");
    }

    public void validateFlight(Flights flights) throws FlightsException{
        validateAirline(flights.getNameOfAirline());
        validateCity(flights.getCityOfDeparture());
        validateCity(flights.getCityOfArrival());
        validateDate(flights.getDate());
    }

    /**
     * removes whitespace from both ends of a string
     * @param flights
     */
    public void trimData(Flights flights){
        if(flights.getCityOfArrival() != null)
            flights.setCityOfArrival(flights.getCityOfArrival().trim());
        if(flights.getCityOfDeparture() != null)
            flights.setCityOfDeparture(flights.getCityOfDeparture().trim());
        if(flights.getNameOfAirline() != null)
            flights.setNameOfAirline(flights.getNameOfAirline().trim());
    }
    public void delete(int id) throws FlightsException {
            DaoFactory.flightsDao().delete(id);
    }

    public List<Flights> getAll() throws FlightsException{
        return DaoFactory.flightsDao().getAll();
    }

    public Flights add(Flights flight) throws FlightsException{
        trimData(flight);
        validateFlight(flight);
            return DaoFactory.flightsDao().add(flight);
    }

    public List<Flights> searchFlight(Flights flight) throws FlightsException{
        trimData(flight);
        validateCity(flight.getCityOfDeparture());
        validateCity(flight.getCityOfArrival());
        validateDate(flight.getDate());
        return DaoFactory.flightsDao().searchFlight(flight);
    }

    public List<Flights> searchDepartures(String cityOfDeparture) throws FlightsException{
        cityOfDeparture = cityOfDeparture.trim();
        validateCity(cityOfDeparture);
        return DaoFactory.flightsDao().getByDeparture(cityOfDeparture);
    }

    public List<Flights> searchArrivals(String cityOfArrival) throws FlightsException{
        cityOfArrival= cityOfArrival.trim();
        validateCity(cityOfArrival);
        return DaoFactory.flightsDao().getByArrival(cityOfArrival);
    }

    public List<Flights> searchArrivalsDepartures(String cityOfArrival, String cityOfDeparture) throws FlightsException{
        if((cityOfArrival == null || cityOfArrival.trim().equals("")) && (cityOfDeparture == null || cityOfDeparture.trim().equals("")))
            return DaoFactory.flightsDao().getAll();
        if(cityOfArrival == null || cityOfArrival.trim().equals(""))
            return DaoFactory.flightsDao().getByDeparture(cityOfDeparture.trim());
        if(cityOfDeparture == null || cityOfDeparture.trim().equals(""))
            return DaoFactory.flightsDao().getByArrival(cityOfArrival.trim());
        return DaoFactory.flightsDao().searchByArrivalDeparture(cityOfArrival.trim(), cityOfDeparture.trim());
    }

    public Flights getById(int id) throws FlightsException {
        return DaoFactory.flightsDao().getById(id);
    }

    public Flights update(Flights flights) throws FlightsException {
        trimData(flights);
        validateFlight(flights);
        return DaoFactory.flightsDao().update(flights);
    }

    public List<Flights> getByDate(LocalDate Date) throws FlightsException {
        validateDate(Date);
        return DaoFactory.flightsDao().getByDate(Date);
    }

    public List<Flights> getByCurrentDate() throws FlightsException {
        return DaoFactory.flightsDao().getByCurrentDate();
    }


}

