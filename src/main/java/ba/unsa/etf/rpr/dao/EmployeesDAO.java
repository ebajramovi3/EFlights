package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;

/**
 * Dao interface for Employees domain bean
 *
 * @author Esma Bajramovic
 */
public interface EmployeesDAO extends DAO<Employees>{
    /**
     * Gets entity based on username
     * @param username
     * @return Employees
     * @throws FlightsException
     */
    Employees getByUsername(String username) throws FlightsException;
}
