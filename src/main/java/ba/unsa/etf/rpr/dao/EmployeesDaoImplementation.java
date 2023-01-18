package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class EmployeesDaoImplementation extends AbstractDao<Employees> implements EmployeesDAO{

    public EmployeesDaoImplementation() {
        super("Employees");
    }

    /**
     * @param rs
     * @return
     * @throws FlightsException
     */
    @Override
    public Employees row2object(ResultSet rs) throws FlightsException {
        try {
            Employees employee = new Employees();

            employee.setId(rs.getInt("id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setUsername(rs.getString("username"));
            employee.setPassword(rs.getString("password"));

            return employee;
        } catch (SQLException e) {
            throw new FlightsException(e.getMessage(), e);
        }
    }

    /**
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row(Employees object) {
        Map<String, Object> item = new TreeMap<>();

        item.put("id", object.getId());
        item.put("first_name", object.getFirstName());
        item.put("last_name", object.getLastName());
        item.put("username", object.getUsername());
        item.put("password", object.getPassword());

        return item;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public Employees getByUsername(String username) throws FlightsException{
        return executeQueryUnique("SELECT * FROM Employees WHERE Username = ?", new Object[]{username});
    }
}
