package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;

public interface EmployeesDAO extends DAO<Employees>{
    Employees getByUsername(String username) throws FlightsException;
}
