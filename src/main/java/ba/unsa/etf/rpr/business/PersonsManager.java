package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import static java.time.ZoneId.systemDefault;

/**
 * Business Logic Layer for management of Persons
 *
 * @author Esma Bajramovic
 */
public class PersonsManager {
    public void validateFirstName(String fn) throws FlightsException {
        if(fn == null || fn.trim().length() > 20 || fn.trim().length() < 1 || !Pattern.compile("[a-zA-Z]*").matcher(fn).matches())
            throw new FlightsException("Invalid first name!");
    }

    public void validateLastName(String ln) throws FlightsException{
        if(ln == null || ln.trim().length() > 20 || ln.trim().length() < 1 || !Pattern.compile("[a-zA-Z]*").matcher(ln).matches())
            throw new FlightsException("Invalid last name!");
    }

    public void validateCitizenship(String cs) throws FlightsException{
        if(cs == null || (cs.trim().length() > 100  || cs.trim().length() < 1) || !Pattern.compile("[a-zA-Z]*").matcher(cs).matches())
            throw new FlightsException("Invalid citizenship!");
    }

    /**
     * checks date of birth
     * @param dateOfBirth
     * @throws FlightsException
     */
    public void validateDateOfBirth(LocalDate dateOfBirth) throws FlightsException{
        if((dateOfBirth == null) || dateOfBirth.compareTo(Instant.ofEpochMilli(Instant.now().toEpochMilli()).atZone(systemDefault()).toLocalDate()) >= 0)
            throw new FlightsException("Invalid date of birth!");
    }

    /**
     * removes whitespace from both ends of a string
     * @param person
     */
    private void trimData(Persons person){
        if(person.getFirstName() != null)
             person.setFirstName(person.getFirstName().trim());
        if(person.getLastName() != null)
             person.setLastName(person.getLastName().trim());
        if(person.getCitizenship() != null)
             person.setCitizenship(person.getCitizenship().trim());
    }

    public Persons add(Persons person) throws FlightsException{
        try{
            trimData(person);
            validateFirstName(person.getFirstName());
            validateLastName(person.getLastName());
            return DaoFactory.personsDao().add(person);
        }catch (Exception e){
            if(e.getMessage().contains("UQ_NAME")){
                throw new FlightsException(e.getMessage(), e);
            }
            throw e;
        }
    }

    public void update(Persons person) throws FlightsException{
            trimData(person);
            validateFirstName(person.getFirstName());
            validateLastName(person.getLastName());
            validateCitizenship(person.getCitizenship());
            validateDateOfBirth(person.getDateOfBirth());
            DaoFactory.personsDao().update(person);

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
