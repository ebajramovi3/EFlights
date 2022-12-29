package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departure;
import ba.unsa.etf.rpr.domain.Employees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDaoImplementation extends SQLConnection implements EmployeesDAO{
    /**
     * @param item
     * @return
     */
    @Override
    public Employees update(Employees item) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Employees getById(Integer id) {
        String query = "SELECT * FROM Departure";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Employees employee = new Employees();

                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));

                rs.close();
                return employee;
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
    public Employees add(Employees item) {
        String add = "INSERT INTO Employees(id, first_name, last_name, username, password) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, item.getId());
            statement.setString(2, item.getFirstName());
            statement.setString(3, item.getLastName());
            statement.setString(4, item.getUsername());
            statement.setString(5, item.getPassword());

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
        String delete = "DELETE FROM Employees WHERE id = ?";
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
    public List<Employees> getAll() {
        String query = "SELECT * FROM Departure";
        try{
            List<Employees> employees = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Employees employee = new Employees();

                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));

               employees.add(employee);
            }
            rs.close();
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param username
     * @return
     */
    @Override
    public String getByUsername(String username) {
        return null;
    }
}
