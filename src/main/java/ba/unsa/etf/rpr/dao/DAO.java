package ba.unsa.etf.rpr.dao;

import java.util.List;

public interface DAO<T, S> {
    T update(T item);

    T searchById(S id);

    T add(T item);

    void delete(S id);

    List<T> getAll();
}

