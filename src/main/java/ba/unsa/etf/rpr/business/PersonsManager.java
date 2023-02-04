package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static java.time.ZoneId.systemDefault;

public class PersonsManager {
    private void validateFirstName(String fn) throws FlightsException {
        if(fn == null || fn.length() > 45 || fn.length() < 1)
            throw new FlightsException("Invalid first name!");
    }

    private void validateLastName(String ln) throws FlightsException{
        if(ln == null || ln.length() > 45 || ln.length() < 1)
            throw new FlightsException("Invalid last name!");
    }

    private void validateCitizenship(String cs) throws FlightsException{
        if(cs != null || cs.length() > 100  || cs.length() < 1)
            throw new FlightsException("Invalid citizenship!");
    }

    private void validateDateOfBirth(LocalDate dateOfBirth) throws FlightsException{
        if((dateOfBirth == null) || dateOfBirth.compareTo(Instant.ofEpochMilli(Instant.now().toEpochMilli()).atZone(systemDefault()).toLocalDate()) >= 0)
            throw new FlightsException("Invalid date of birth!");
    }

    private void validatePerson(Persons person) throws FlightsException{
        validateFirstName(person.getFirstName());
        validateLastName(person.getLastName());
        validateCitizenship(person.getCitizenship());
    }

    private void trimData(Persons person){
        person.setFirstName(person.getFirstName().trim());
        person.setLastName(person.getLastName().trim());
        person.setCitizenship(person.getCitizenship().trim());
    }

    public Persons add(Persons person) throws FlightsException{
        try{
            trimData(person);
            validatePerson(person);
            return DaoFactory.personsDao().add(person);
        }catch (Exception e){
            if(e.getMessage().contains("UQ_NAME")){
                throw new FlightsException(e.getMessage(), e);
            }
            throw e;
        }
    }

    public void update(Persons person) throws FlightsException{
        try{
            trimData(person);
            validatePerson(person);
            DaoFactory.personsDao().update(person);
        } catch (Exception e){
            throw new FlightsException("No such reservation!");
        }
    }

    public Persons getById(int id) throws FlightsException {
        return DaoFactory.personsDao().getById(id);
    }

    public List<Persons> getAll() throws FlightsException{
        return DaoFactory.personsDao().getAll();
    }

    public void delete(int id) throws FlightsException{
        DaoFactory.personsDao().delete(id);
    }
}
