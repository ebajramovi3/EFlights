package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.List;
import java.util.regex.Pattern;

public class EmployeeManager {
    private void validateUsername(String username) throws FlightsException{
        if(username == null || username.length() <5 || username.length() > 20)
            throw new FlightsException("Invalid username!");
    }

    private void validatePassword(String password) throws FlightsException{
        if(password == null || password.length() < 8 || password.length() > 20)
            throw new FlightsException("Invalid password!");
    }

    private void validateFirstName(String fn) throws FlightsException{
        if(fn == null || fn.length() > 45 || fn.length() < 1 || !Pattern.compile("[a-zA-Z]*").matcher(fn).matches())
            throw new FlightsException("Invalid first name!");
    }

    private void validateLastName(String ln) throws FlightsException{
        if(ln == null || ln.length() > 45 || ln.length() < 1 || !Pattern.compile("[a-zA-Z]*").matcher(ln).matches())
            throw new FlightsException("Invalid last name!");
    }

    private void trimData(Employees employees){
        if(employees.getFirstName() != null)
            employees.setFirstName(employees.getFirstName().trim());
        if(employees.getLastName() != null)
            employees.setLastName(employees.getLastName().trim());
        if(employees.getUsername() != null)
            employees.setUsername(employees.getUsername().trim());
        if(employees.getPassword() != null)
            employees.setPassword(employees.getPassword().trim());
    }

    public void delete(int id) throws FlightsException {
        DaoFactory.employeesDao().delete(id);
    }

    public List<Employees> getAll() throws FlightsException{
        return DaoFactory.employeesDao().getAll();
    }

    public Employees add(Employees employee) throws FlightsException{
            trimData(employee);
            validateFirstName(employee.getFirstName());
            validateLastName(employee.getLastName());
            validateUsername(employee.getUsername());
            validatePassword(employee.getPassword());
            return DaoFactory.employeesDao().add(employee);
    }

    public Employees getByUsername(String username) throws FlightsException{
        username = username.trim();
        validateUsername(username);
        return DaoFactory.employeesDao().getByUsername(username);
    }

    public boolean checkPassword(String username, String password) throws FlightsException{
        Employees employee = getByUsername(username);
        if(!employee.getPassword().equals(password))
            throw new FlightsException("Incorrect password!");
        return employee.getPassword().equals(password);
    }

    public Employees update(Employees employee) throws FlightsException {
        trimData(employee);
        validateFirstName(employee.getFirstName());
        validateLastName(employee.getLastName());
        validateUsername(employee.getUsername());
        validatePassword(employee.getPassword());
        return DaoFactory.employeesDao().update(employee);
    }
}
