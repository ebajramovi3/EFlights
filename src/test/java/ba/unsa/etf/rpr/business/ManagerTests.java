package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.EmployeesDaoImplementation;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ManagerTests {
    private EmployeeManager employeeManager;
    private Employees employee;
    private EmployeesDaoImplementation employeeDaoSQLMock;
    private List<Employees> employees;
    private PersonsManager personsManager;
    private Persons person;
    private FlightsManager flightsManager;
    private Flights flight;

    /**
     * Method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        employeeManager = Mockito.mock(EmployeeManager.class);
        employee = new Employees(1, "Kathryn", "Lopez", "klopez", "ksjhdesdsf");

        employeeDaoSQLMock = Mockito.mock(EmployeesDaoImplementation.class);
        employees = new ArrayList<>();
        employees.addAll(Arrays.asList(employee, new Employees(2, "David", "Miller", "dmiller", "kjdhfudhs"), new Employees(3, "Linda", "Warren", "wlinda", "uedzhs2713")));


        flightsManager = Mockito.mock(FlightsManager.class);
        flight = new Flights(2, "Lufthansa", "London", "Sarajevo", LocalDate.parse("2025-10-27"));

        personsManager = Mockito.mock(PersonsManager.class);
        person = new Persons(1, "Kathryn", "Lopez", "USA", LocalDate.parse("2000-12-15"), false, new Flights());
    }


    /**
     * Method tests validateFirstName(String name) for correct passed parameter
     * @throws FlightsException
     */
    @Test
    void validateCorrectFirstName() throws FlightsException{
        String correctName = "Allen";
        try {
            Mockito.doCallRealMethod().when(employeeManager).validateFirstName(correctName);
        } catch (FlightsException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    /**
     * Method tests validateFirstName(String name) for incorrect passed parameters
     * @throws FlightsException
     */
    @Test
    void validateIncorrectFirstName() throws FlightsException{
        String incorrectNameNumbers = "17whdjdoa";
        Mockito.doCallRealMethod().when(employeeManager).validateFirstName(incorrectNameNumbers);
        FlightsException flightsException1 = assertThrows(FlightsException.class, () -> {
            employeeManager.validateFirstName(incorrectNameNumbers);}, "Invalid first name!");
        Assertions.assertEquals("Invalid first name!", flightsException1.getMessage());

        String incorrectNameShort = "    whd";
        Mockito.doCallRealMethod().when(employeeManager).validateFirstName(incorrectNameShort);
        FlightsException flightsException2 = assertThrows(FlightsException.class, () -> {
            employeeManager.validateFirstName(incorrectNameShort);}, "Invalid first name!");
        Assertions.assertEquals("Invalid first name!", flightsException2.getMessage());

    }

    /**
     * Method tests validateFlight(Flights flight) for correct passed parameters
     * @throws FlightsException
     */
    @Test
    void validateUsername(){
        String longUsername = "jihuzgfdfghlkjlhgfjkhlihzfz";
        assertThrows(FlightsException.class, () -> (new EmployeeManager()).validateUsername(longUsername), "Invalid username!" );
    }

    /**
     * Adding employee
     * @throws FlightsException
     */
    @Test
    void addEmployee() throws FlightsException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::employeesDao).thenReturn(employeeDaoSQLMock);
        when(DaoFactory.employeesDao().getAll()).thenReturn(employees);
        when(employeeManager.getAll()).thenReturn(employees);

        Employees employees1 = new Employees(5, "Regina", "Turner", "rturner", "oksjde28ud");
        Mockito.doCallRealMethod().when(employeeManager).add(employees1);
        employeeManager.add(employees1);

        Assertions.assertTrue(true);
        Mockito.verify(employeeManager).add(employees1);
        daoFactoryMockedStatic.close();
    }

    /**
     * Method tests validateDateOfBirth(LocalDate dateOfBirth) for correct passed parameters
     * @throws FlightsException
     */
    @Test
    void validateCorrectDateOfBirth() throws FlightsException{
        try {
            Mockito.doCallRealMethod().when(personsManager).validateDateOfBirth(person.getDateOfBirth());
        } catch (FlightsException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    /**
     * Method tests validateDateOfBirth(LocalDate dateOfBirth) for incorrect passed parameters
     * @throws FlightsException
     */
    @Test
    void validateIncorrectDateOfBirth() throws FlightsException{
        LocalDate incorrectDate = LocalDate.parse("2025-03-04");
        Mockito.doCallRealMethod().when(personsManager).validateDateOfBirth(incorrectDate);
        FlightsException flightsException1 = assertThrows(FlightsException.class, () -> {
            personsManager.validateDateOfBirth(incorrectDate);}, "Invalid date of birth!");
        Assertions.assertEquals("Invalid date of birth!", flightsException1.getMessage());
    }

    /**
     * Method tests checkPassword(String username, Employees employee) for incorrect passed parameter
     * @throws FlightsException
     */
    @Test
    void checkPassword() throws FlightsException{
        Mockito.when(employeeManager.getByUsername(employee.getUsername())).thenReturn(employee);
        Mockito.doCallRealMethod().when(employeeManager).checkPassword(employee.getUsername(), employee.getPassword()+ "jhdsk");

        FlightsException flightsException = assertThrows(FlightsException.class, () -> {
            employeeManager.checkPassword(employee.getUsername(), employee.getPassword() + "jhdsk");}, "Incorrect password!");

        Assertions.assertEquals("Incorrect password!", flightsException.getMessage());
    }

    /**
     * Method tests validateDate(LocalDate date) for incorrect passed parameters
     * @throws FlightsException
     */
    @Test
    void validateIncorrectDate() throws FlightsException{
        LocalDate incorrectDate = LocalDate.parse("2003-06-13");
        Mockito.doCallRealMethod().when(flightsManager).validateDate(incorrectDate);
        FlightsException flightsException1 = assertThrows(FlightsException.class, () -> {
            flightsManager.validateDate(incorrectDate);}, "Invalid date!");
        Assertions.assertEquals("Invalid date!", flightsException1.getMessage());
    }

    /**
     * Method tests validateFlight(Flights flight) for correct passed parameters
     * @throws FlightsException
     */
    @Test
    void validateFlight(){
        try {
            Mockito.doCallRealMethod().when(flightsManager).validateFlight(flight);
        } catch (FlightsException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    /**
     * Method tests trimData(Flights flight)
     * @throws FlightsException
     */
    @Test
    void trimData() {
        flight = new Flights(1, null, "    Sarajevo", "Kopenhagen  ", LocalDate.parse("2024-01-01"));
        assertDoesNotThrow(()->flightsManager.trimData(flight));
    }
}
