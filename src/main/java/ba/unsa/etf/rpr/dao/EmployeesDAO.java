package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employees;

public interface EmployeesDAO extends DAO<Employees, Integer>{
    Employees getByUsername(String username);
}
