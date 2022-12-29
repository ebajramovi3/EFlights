package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Persons;

import java.util.List;

public interface PersonsDAO extends DAO<Persons, String> {
    List<Persons> getByFirstName(String firstName);

    List<Persons> getByLastName(String lastName);

    List<Persons> getByCitizenship(String citizenship);
}
