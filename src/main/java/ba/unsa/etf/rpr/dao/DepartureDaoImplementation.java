package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.domain.Departure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepartureDaoImplementation extends SQLConnection implements DepartureDAO{
    public DepartureDaoImplementation() {
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Departure update(Departure item) {
        String insert = "UPDATE Departure SET country = ?, city = ?, date = ?, arrivalId = ? WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, item.getCountry());
            statement.setString(2, item.getCity());
            java.sql.Date sqlDate = new java.sql.Date(item.getDateOfDeparture().getYear(), item.getDateOfDeparture().getMonth(), item.getDateOfDeparture().getDay() + 1);
            statement.setDate(3, sqlDate);
            statement.setInt(4, item.getArrival().getArrivalId());
            statement.setInt(5, item.getDepartureId());

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
    public Departure getById(Integer id) {
        String query = "SELECT * FROM Departure WHERE id = ?";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Departure departure = new Departure();

                departure.setDepartureId(rs.getInt("id"));
                departure.setCountry(rs.getString("country"));
                departure.setCity("city");
                departure.setDateOfDeparture(rs.getDate("date"));
                departure.setArrival(new ArrivalDaoImplementation().getById(rs.getInt("arrivalId")));

                rs.close();
                return departure;
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
    public Departure add(Departure item) {
        String add = "INSERT INTO Departure(id, country, city, date, arrivalId) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, item.getDepartureId());
            statement.setString(2, item.getCountry());
            statement.setString(3, item.getCity());
            java.sql.Date sqlDate = new java.sql.Date(item.getDateOfDeparture().getYear(), item.getDateOfDeparture().getMonth(), item.getDateOfDeparture().getDay() + 1);
            statement.setString(4, sqlDate.toString());
            statement.setInt(5, item.getArrival().getArrivalId());

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
        String delete = "DELETE FROM Departure WHERE id = ?";
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
    public List<Departure> getAll() {
        String query = "SELECT * FROM Departure";
        try{
            List<Departure> departures = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Departure departure = new Departure();

                departure.setDepartureId(rs.getInt("id"));
                departure.setCountry(rs.getString("country"));
                departure.setCity("city");
                departure.setDateOfDeparture(rs.getDate("date"));
                departure.setArrival(new ArrivalDaoImplementation().getById(rs.getInt("arrivalId")));

                departures.add(departure);
            }
            rs.close();
            return departures;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param cityOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByCity(String cityOfDeparture) {
        String query = "SELECT * FROM Departure WHERE city = ?";
        try{
            List<Departure> departures = new ArrayList<>();

            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, cityOfDeparture);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Departure departure = new Departure();

                departure.setDepartureId(rs.getInt("id"));
                departure.setCountry(rs.getString("country"));
                departure.setCity("city");
                departure.setDateOfDeparture(rs.getDate("date"));
                departure.setArrival(new ArrivalDaoImplementation().getById(rs.getInt("arrivalId")));

                departures.add(departure);
            }
            rs.close();
            return departures;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param countryOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByCountry(String countryOfDeparture) {
        String query = "SELECT * FROM Departure WHERE country = ?";
        try{
            List<Departure> departures = new ArrayList<>();

            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, countryOfDeparture);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Departure departure = new Departure();

                departure.setDepartureId(rs.getInt("id"));
                departure.setCountry(rs.getString("country"));
                departure.setCity("city");
                departure.setDateOfDeparture(rs.getDate("date"));
                departure.setArrival(new ArrivalDaoImplementation().getById(rs.getInt("arrivalId")));

                departures.add(departure);
            }
            rs.close();
            return departures;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param dateOfDeparture
     * @return
     */
    @Override
    public List<Departure> getByDate(Date dateOfDeparture) {
        String query = "SELECT * FROM Departure WHERE date = ?";
        try{
            List<Departure> departures = new ArrayList<>();

            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setDate(1, (java.sql.Date) dateOfDeparture);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Departure departure = new Departure();

                departure.setDepartureId(rs.getInt("id"));
                departure.setCountry(rs.getString("country"));
                departure.setCity("city");
                java.sql.Date date = new java.sql.Date(dateOfDeparture.getYear(), dateOfDeparture.getMonth(), dateOfDeparture.getDay() + 1);
                statement.setDate(1, date);
                departure.setArrival(new ArrivalDaoImplementation().getById(rs.getInt("arrivalId")));

                departures.add(departure);
            }
            rs.close();
            return departures;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
