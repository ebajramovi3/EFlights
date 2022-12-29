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
        String insert = "UPDATE Flights SET airline_name = ?, departureId = ?, max_passengers = ?, max_business_class = ?, price_economy = ?, price_business = ? WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, item.getNameOfAirline());
            statement.setInt(2, item.getDestination().getDepartureId());
            statement.setInt(3, item.getMaxNumberOfPassengers());
            statement.setInt(4, item.getMaxNumberOfBusinessClass());
            statement.setInt(5, item.getPriceEconomyClass());
            statement.setInt(6, item.getPriceBusinessClass());
            statement.setInt(7, item.getId());

            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Flights getById(Integer id) {
        String query = "SELECT * FROM Flights WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Flights flight = new Flights();

                flight.setId(rs.getInt("id"));
                flight.setNameOfAirline(rs.getString("airline_name"));
                flight.setMaxNumberOfPassengers(rs.getInt("max_passengers"));
                flight.setMaxNumberOfBusinessClass(rs.getInt("max_business_class"));
                flight.setPriceBusinessClass(rs.getInt("price_business"));
                flight.setPriceEconomyClass(rs.getInt("price_economy"));
                flight.setDestination(new DepartureDaoImplementation().getById(rs.getInt("departureId")));

                rs.close();
                return flight;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Flights add(Flights item) {
        String add = "INSERT INTO Flights(id, airline_name, departureId, max_passengers, max_business_class, price_economy, price_business) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, item.getId());
            statement.setString(2, item.getNameOfAirline());
            statement.setInt(3, item.getDestination().getDepartureId());
            statement.setInt(4, item.getMaxNumberOfPassengers());
            statement.setInt(5, item.getMaxNumberOfBusinessClass());
            statement.setInt(6, item.getPriceEconomyClass());
            statement.setInt(7, item.getPriceBusinessClass());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();

            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public List<Flights> getAll() {
        String query = "SELECT * FROM Flights";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            List<Flights> flights = new ArrayList<>();
            while(rs.next()){
                Flights flight = new Flights();

                flight.setId(rs.getInt("id"));
                flight.setNameOfAirline(rs.getString("airline_name"));
                flight.setMaxNumberOfPassengers(rs.getInt("max_passengers"));
                flight.setMaxNumberOfBusinessClass(rs.getInt("max_business_class"));
                flight.setPriceBusinessClass(rs.getInt("price_business"));
                flight.setPriceEconomyClass(rs.getInt("price_economy"));
                flight.setDestination(new DepartureDaoImplementation().getById(rs.getInt("departureId")));

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
                flight.setNameOfAirline(rs.getString("airline_name"));
                flight.setMaxNumberOfPassengers(rs.getInt("max_passengers"));
                flight.setMaxNumberOfBusinessClass(rs.getInt("max_business_class"));
                flight.setPriceBusinessClass(rs.getInt("price_business"));
                flight.setPriceEconomyClass(rs.getInt("price_economy"));
                flight.setDestination(new DepartureDaoImplementation().getById(rs.getInt("departureId")));

                flights.add(flight);
            }
            rs.close();
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
