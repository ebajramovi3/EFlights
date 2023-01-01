package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.List;

public interface DAO<T> {
    public T update(T item) throws FlightsException;

    public T getById(int id) throws FlightsException;;

    public T add(T item) throws FlightsException;;

    public void delete(int id) throws FlightsException;;

    public List<T> getAll() throws FlightsException;;
}

