package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Business Logic Layer for management of Employees
 *
 * @author Esma Bajramovic
 */

public class EmployeeManager {
    /**
     * Method checks username
     * @param username
     * @throws FlightsException
     */
    public void validateUsername(String username) throws FlightsException{
        if(username == null || username.trim().length() <5 || username.trim().length() > 20)
            throw new FlightsException("Invalid username!");
    }

    /**
     * Method checks password
     * @param password
     * @throws FlightsException
     */
    public void validatePassword(String password) throws FlightsException{
        if(password == null || password.trim().length() < 8 || password.trim().length() > 20)
            throw new FlightsException("Invalid password!");
    }

    /**
     * Method checks first name
     * @param firstName
     * @throws FlightsException
     */
    public void validateFirstName(String firstName) throws FlightsException{
        if(firstName == null || firstName.trim().length() > 20 || firstName.trim().length() < 1 || !Pattern.compile("[a-zA-Z]*").matcher(firstName).matches())
            throw new FlightsException("Invalid first name!");
    }

    /**
     * Method checks last name
     * @param lastName
     * @throws FlightsException
     */
    public void validateLastName(String lastName) throws FlightsException{
        if(lastName == null || lastName.trim().length() > 20 || lastName.trim().length() < 1 || !Pattern.compile("[a-zA-Z]*").matcher(lastName).matches())
            throw new FlightsException("Invalid last name!");
    }

    /**
     * removes whitespace from both ends of a string
     * @param employees
     */
    public void trimData(Employees employees){
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

    /**
     * checks if given password is correct for entered username
     * @param username
     * @param password
     * @throws FlightsException
     */
    public boolean checkPassword(String username, String password) throws FlightsException{
        Employees employee = getByUsername(username);
        if(!employee.getPassword().equals(password))
            throw new FlightsException("Incorrect password!");
        return employee.getPassword().equals(password);
    }

    /**
     * @param employee
     * @return updated object
     * @throws FlightsException
     */

    public Employees update(Employees employee) throws FlightsException {
        trimData(employee);
        validateFirstName(employee.getFirstName());
        validateLastName(employee.getLastName());
        validateUsername(employee.getUsername());
        validatePassword(employee.getPassword());
        return DaoFactory.employeesDao().update(employee);
    }
}
