package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Persons;

import java.util.List;

public interface PersonsDAO<T> extends DAO {
    List<Persons> searchByFirstName(String firstName);

    List<Persons> searchByLastName(String lastName);

    List<Persons> searchByCitizenship(String citizenship);
}
