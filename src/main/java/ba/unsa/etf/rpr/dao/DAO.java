package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.List;

public interface DAO<T> {
    /**
     * Fully updates entity in database based on id (primary) match.
     * @param item - bean to be updated. id must be populated
     * @return updated version of bean
     */
    public T update(T item) throws FlightsException;

    /**
     * Gets entity from database base on ID
     * @param id primary key of entity
     * @return Entity from database
     */
    public T getById(int id) throws FlightsException;;

    /**
     * Saves entity into database
     * @param item bean for saving to database
     * @return saved item with id field populated
     */
    public T add(T item) throws FlightsException;;

    /**
     * Deletes entity from database with given id
     * @param id - primary key of entity
     */
    public void delete(int id) throws FlightsException;;

    /**
     * Lists all entities from database
     * @return List of entities from database
     */
    public List<T> getAll() throws FlightsException;;
}

