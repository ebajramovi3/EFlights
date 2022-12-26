package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.domain.Flights;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightsDaoImplementation extends SQLConnection implements FlightsDAO{


    /**
     * @param item
     * @return
     */
    @Override
    public Flights update(Flights item) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Flights getById(Integer id) {
        return null;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Flights add(Flights item) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        String delete = "DELETE FROM Flights WHERE id = ?";

        try {
            PreparedStatement statement = this.connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public List<Flights> getAll() {String query = "SELECT * FROM Flights";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            List<Flights> flights = new ArrayList<>();
            while(rs.next()){
                Flights flight = new Flights();

                flight.setId(rs.getInt("id"));
                flight.setNameOfAirline(rs.getString("Airline"));
                flight.setMaxNumberOfPassengers(rs.getInt("max"));
                flight.setMaxNumberOfBusinessClass(rs.getInt("max"));
                flight.setPriceBusinessClass(rs.getInt("price"));
                flight.setPriceEconomyClass(rs.getInt("price"));
                flight.setDestination(new DepartureDaoImplementation().getById(rs.getInt("departureId")));
                flight.setDateOfDeparture(rs.getDate("date"));

                flights.add(flight);
            }
            rs.close();
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param dateOfFlight
     * @return
     */
    @Override
    public List<Flights> getByDate(Date dateOfFlight) {
        String query = "SELECT * FROM Flights WHERE date = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setDate(1, (java.sql.Date) dateOfFlight);
            ResultSet rs = statement.executeQuery();

            List<Flights> flights = new ArrayList<>();
            while(rs.next()){
                Flights flight = new Flights();

                flight.setId(rs.getInt("id"));
                flight.setNameOfAirline(rs.getString("Airline"));
                flight.setMaxNumberOfPassengers(rs.getInt("max"));
                flight.setMaxNumberOfBusinessClass(rs.getInt("max"));
                flight.setPriceBusinessClass(rs.getInt("price"));
                flight.setPriceEconomyClass(rs.getInt("price"));
                flight.setDestination(new DepartureDaoImplementation().getById(rs.getInt("departureId")));
                flight.setDateOfDeparture(rs.getDate("date"));

                flights.add(flight);
            }
            rs.close();
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
