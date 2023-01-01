package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;

import java.util.List;

public class PersonsManager {
    public void validateFistName(String fn) throws FlightsException {
        if(fn == null || fn.length() > 45)
            throw new FlightsException("First name can have max 45 letters.");
    }

    public void validateLastName(String ln) throws FlightsException{
        if(ln == null || ln.length() > 45)
            throw new FlightsException("Last name can have max 45 letters.");
    }

    public  void validateCitizenship(String cs) throws FlightsException{
        if(cs == null || cs.length() > 100)
            throw new FlightsException("Citizenship must have less then 100 letters.");
    }

    public Persons add(Persons person) throws FlightsException{

        try{
            validateFistName(person.getFirstName());
            validateLastName(person.getLastName());
            validateCitizenship(person.getCitizenship());
            return DaoFactory.personsDao().add(person);
        }catch (Exception e){
            if(e.getMessage().contains("UQ_NAME")){
                throw new FlightsException(e.getMessage(), e);
            }
            throw e;
        }
    }
}
