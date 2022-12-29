package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departure;
import ba.unsa.etf.rpr.domain.Employees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Employees add(Employees item) {
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
