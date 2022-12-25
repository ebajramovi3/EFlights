package ba.unsa.etf.rpr.dao;

import java.util.List;

public interface DAO<T, S> {
    public T update(T item);

    public T getById(S id);

    public T add(T item);

    public void delete(S id);

    public List<T> getAll();
}

