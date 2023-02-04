package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import ba.unsa.etf.rpr.dao.DaoFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static java.time.ZoneId.systemDefault;

public class FlightsManager {
    private void validateCity(String city) throws FlightsException{
        if(city == null || city.length() < 1 || city.length()> 45)
            throw new FlightsException("Invalid city!");
    }

    private void validateAirline(String airlineName) throws FlightsException{
        if(airlineName == null || airlineName.length() < 1 || airlineName.length()> 45)
            throw new FlightsException("Invalid airline!");
    }

    private void validateDate(LocalDate date) throws FlightsException{
        if((date == null) || date.compareTo(Instant.ofEpochMilli(Instant.now().toEpochMilli()).atZone(systemDefault()).toLocalDate()) < 0)
            throw new FlightsException("Invalid date!");
    }

    private void validateFlight(Flights flights) throws FlightsException{
        validateAirline(flights.getNameOfAirline());
        validateCity(flights.getCityOfDeparture());
        validateCity(flights.getCityOfArrival());
        validateDate(flights.getDate());
    }

    private void trimData(Flights flights){
        flights.setCityOfArrival(flights.getCityOfArrival().trim());
        flights.setCityOfDeparture(flights.getCityOfDeparture().trim());
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
        return DaoFactory.flightsDao().searchFlight(flight);
    }

    public List<Flights> searchDepartures(String cityOfDeparture) throws FlightsException{
        return DaoFactory.flightsDao().getByDeparture(cityOfDeparture);
    }

    public List<Flights> searchArrivals(String cityOfArrival) throws FlightsException{
        return DaoFactory.flightsDao().getByArrival(cityOfArrival);
    }

    public List<Flights> searchArrivalsDepartures(String cityOfArrival, String cityOfDeparture) throws FlightsException{
        if((cityOfArrival == null || cityOfArrival.equals("")) && (cityOfDeparture == null || cityOfDeparture.equals("")))
            return DaoFactory.flightsDao().getAll();
        if(cityOfArrival == null || cityOfArrival.equals(""))
            return DaoFactory.flightsDao().getByDeparture(cityOfDeparture);
        if(cityOfDeparture == null || cityOfDeparture.equals(""))
            return DaoFactory.flightsDao().getByArrival(cityOfArrival);
        return DaoFactory.flightsDao().searchByArrivalDeparture(cityOfArrival, cityOfDeparture);
    }

    public Flights getById(int id) throws FlightsException {
        return DaoFactory.flightsDao().getById(id);
    }

    public Flights update(Flights flights) throws FlightsException {
        trimData(flights);
        validateFlight(flights);
        return DaoFactory.flightsDao().update(flights);
    }
}
