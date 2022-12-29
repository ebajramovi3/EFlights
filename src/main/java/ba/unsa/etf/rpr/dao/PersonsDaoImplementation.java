package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departure;
import ba.unsa.etf.rpr.domain.Persons;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PersonsDaoImplementation extends SQLConnection implements PersonsDAO{
    /**
     * @param item
     * @return
     */
    @Override
    public Persons update(Persons item) {
        String insert = "UPDATE Persons SET first_name = ?, last_name = ?, citizenship = ?, check_in = ?, flight_id = ?, business_class = ? WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setString(3, item.getCitizenship());
            statement.setBoolean(4, item.isCheckIn());
            statement.setInt(5, item.getFlight().getId());
            statement.setBoolean(6, item.isBusinessClass());
            statement.setString(7, item.getPassportId());

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
    public Persons getById(String id) {
        String query = "SELECT * FROM Departure WHERE id = ?";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Persons person = new Persons();

                person.setPassportId(rs.getString("passportId"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setDateOfBirth(rs.getDate("date_of_birth"));
                person.setCitizenship(rs.getString("citizenship"));
                person.setCheckIn(rs.getBoolean("check_in"));
                person.setFlight(new FlightsDaoImplementation().getById(rs.getInt("flightId")));
                person.setBusinessClass(rs.getBoolean("business_class"));

                rs.close();
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
    public Persons add(Persons item) {
        String add = "INSERT INTO Persons(passportId, first_name, last_name, date_of_birth, citizenship, check_in, flight_id, business_class) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, item.getPassportId());
            statement.setString(2, item.getFirstName());
            statement.setString(3, item.getLastName());
            java.sql.Date sqlDate = new java.sql.Date(item.getDateOfBirth().getYear(), item.getDateOfBirth().getMonth(), item.getDateOfBirth().getDay() + 1);
            statement.setString(4, sqlDate.toString());
            statement.setString(5, item.getCitizenship());
            statement.setBoolean(6, item.isCheckIn());
            statement.setInt(7, item.getFlight().getId());
            statement.setBoolean(8, item.isBusinessClass());

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
    public void delete(String id) {

    }

    /**
     * @return
     */
    @Override
    public List<Persons> getAll() {
        return null;
    }

    /**
     * @param firstName
     * @return
     */
    @Override
    public List<Persons> getByFirstName(String firstName) {
        return null;
    }

    /**
     * @param lastName
     * @return
     */
    @Override
    public List<Persons> getByLastName(String lastName) {
        return null;
    }

    /**
     * @param citizenship
     * @return
     */
    @Override
    public List<Persons> getByCitizenship(String citizenship) {
        return null;
    }
}
