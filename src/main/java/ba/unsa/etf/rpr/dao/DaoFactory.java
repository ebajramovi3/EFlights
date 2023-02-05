package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Esma Bajramovic
 */
public class DaoFactory {
    private static final FlightsDAO flight = new FlightsDaoImplementation();
    private static final PersonsDAO person = new PersonsDaoImplementation();
    private static final EmployeesDAO employee = new EmployeesDaoImplementation();

    private DaoFactory() {
    }

    public static FlightsDAO flightsDao(){ return flight;}
    public static PersonsDAO personsDao(){ return person;}
    public static EmployeesDAO employeesDao(){ return employee;}
}
