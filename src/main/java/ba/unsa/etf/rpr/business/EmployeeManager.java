package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.List;

public class EmployeeManager {
    private void validateUsername(String username) throws FlightsException{
        if(username == null || username.length() <5 || username.length() > 20)
            throw new FlightsException("Username must be between 5 and 20 letters.");
    }

    private void validatePassword(String password) throws FlightsException{
        if(password == null || password.length() < 8 || password.length() > 20)
            throw new FlightsException("Password must be between 8 and 20 letters.");
    }

    private void validateFistName(String fn) throws FlightsException{
        if(fn == null || fn.length() > 45)
            throw new FlightsException("First name can have max 45 letters.");
    }

    private void validateLastName(String ln) throws FlightsException{
        if(ln == null || ln.length() > 45)
            throw new FlightsException("Last name can have max 45 letters.");
    }

    public void delete(int id) throws FlightsException {
        DaoFactory.employeesDao().delete(id);
    }

    public List<Employees> getAll() throws FlightsException{
        return DaoFactory.employeesDao().getAll();
    }

    public Employees add(Employees employee) throws FlightsException{

        try{
            validateFistName(employee.getFirstName());
            validateLastName(employee.getLastName());
            validateUsername(employee.getUsername());
            validatePassword(employee.getPassword());
            return DaoFactory.employeesDao().add(employee);
        }catch (Exception e){
            if(e.getMessage().contains("UQ_NAME")){
                throw new FlightsException(e.getMessage(), e);
            }
            throw e;
        }
    }

    public Employees getByUsername(String username) throws FlightsException{
        return DaoFactory.employeesDao().getByUsername(username);
    }

    public boolean checkPassword(String username, String password) throws FlightsException{
        Employees employee = getByUsername(username);
        return employee.getPassword() == password;
    }
}
