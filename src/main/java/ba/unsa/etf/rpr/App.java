package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PersonsManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option arrivals = new Option("a","get-arrivals",false, "Printing arrivals");
    private static final Option departures = new Option("d","get-departures",false, "Printing departures");
    private static final Option findFlights = new Option("ff", "find-flights",false, "Gets flight by given information");
    private static final Option checkIn = new Option("ci", "check-in",false, "Allows check-in");
    private static final Option logIn = new Option("ln", "log-in",false, "Allows login for employees");
    private static final Option addFlight= new Option("af", "add flight",false, "Adds new flight");
    private static final Option addEmployee = new Option("ae", "add employee",false, "Adds new employee");
    private static final Option updateFlight = new Option("uf", "update flight",false, "Updates flight");
    private static final Option updateEmployee = new Option("ue", "update employee",false, "Updates employee data");
    private static final Option deleteFlight = new Option("df", "delete flight",false, "Deletes flight");
    private static final Option deleteEmployee = new Option("de", "delete employee",false, "Deletes employee data");

    /*
     *
     * @param options
     *
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekat-cli-jar-with-dependencies.jar [option] 'something else if needed'");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(arrivals);
        options.addOption(departures);
        options.addOption(findFlights);
        options.addOption(checkIn);
        options.addOption(logIn);
        options.addOption(addFlight);
        options.addOption(addEmployee);
        options.addOption(updateFlight);
        options.addOption(updateEmployee);
        options.addOption(deleteFlight);
        options.addOption(deleteEmployee);
        return options;
    }

    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        FlightsManager flightsManager = new FlightsManager();

        if((cl.hasOption(arrivals.getOpt()) || cl.hasOption(arrivals.getLongOpt())) && cl.hasOption((arrivals.getLongOpt()))){

            List<Flights> flights = new ArrayList<>();

            try {
                flightsManager.searchArrivals("Sarajevo").forEach(f -> System.out.println(f.getFlight()));
            }catch(Exception e) {
                System.out.println("There is no arrivals from this city! Try again.");
                System.exit(1);
            }
        } else if(cl.hasOption(departures.getOpt()) || cl.hasOption(departures.getLongOpt())){

            flightsManager.searchDepartures("Sarajevo").forEach(f -> System.out.println(f.getFlight()));
        } else if(cl.hasOption(findFlights.getOpt()) || cl.hasOption(findFlights.getLongOpt())){

            try {
                String departure = cl.getArgList().get(0);
                String arrival = cl.getArgList().get(1);

                LocalDate dateOfDeparture = LocalDate.parse(cl.getArgList().get(2));

                Flights flight = new Flights(0, null, departure, arrival, dateOfDeparture);
                flightsManager.searchFlight(flight).forEach(f -> System.out.println(f.getFlight()));
            }catch (FlightsException exception){
                System.out.println(exception.getMessage());
            } catch(Exception e) {
                System.out.println("Please enter city of departure, city of arrival and date(in format yyyy-mm-dd)!");
            }

        } else if(cl.hasOption(checkIn.getOpt()) || cl.hasOption(checkIn.getLongOpt())){
            PersonsManager personsManager = new PersonsManager();

            try {
                Persons getFromDB = personsManager.getById(Integer.parseInt(cl.getArgList().get(1)));


                Persons person = new Persons(Integer.parseInt(cl.getArgList().get(1)), cl.getArgList().get(2), cl.getArgList().get(3), cl.getArgList().get(5), LocalDate.parse(cl.getArgList().get(4)), true, flightsManager.getById(Integer.parseInt(cl.getArgList().get(0))));
                if (!getFromDB.getFirstName().equals(person.getFirstName()) || !getFromDB.getLastName().equals(person.getLastName()) || getFromDB.getFlight().getId() != person.getFlight().getId()) {
                    throw new FlightsException("Invalid data!");
                }

                if (getFromDB.isCheckIn())
                    throw new FlightsException("You've already checked in!");
                personsManager.update(person);

                System.out.println("You have successfully checked-in!");
            }catch (FlightsException exception){
                System.out.println(exception.getMessage());
            } catch(Exception e) {
                System.out.println("Please enter flight id, passport id, first name, last name, date of birth(in format yyyy-mm-dd) and citizenship!");
            }
        } else if(cl.hasOption(logIn.getOpt()) || cl.hasOption(logIn.getLongOpt())){
            EmployeeManager employeeManager = new EmployeeManager();
            try {
                String username = cl.getArgList().get(0);
                String password = PasswordField.readPassword("Password: ");
                if(employeeManager.checkPassword(username, password)) {
                    System.out.println("You have successfully logged in!");

                    while (true) {
                        if (cl.hasOption(deleteFlight.getOpt()) || cl.hasOption(deleteFlight.getLongOpt())) {
                            try {
                                flightsManager.delete(Integer.parseInt(cl.getArgList().get(1)));
                                System.out.println("You have successfully deleted flight!");

                            } catch (FlightsException e) {
                                System.out.println(e.getMessage());
                            } catch(Exception e){
                                System.out.println("Please enter id of flight data you want to delete!");
                            } break;
                        } else if (cl.hasOption(deleteEmployee.getOpt()) || cl.hasOption(deleteEmployee.getLongOpt())) {
                            try {
                                flightsManager.delete(Integer.parseInt(cl.getArgList().get(1)));
                                System.out.println("You have successfully deleted employee data!");
                                break;
                            } catch (FlightsException e) {
                                System.out.println(e.getMessage());
                            } catch(Exception e){
                                System.out.println("Please enter id of employee data you want to delete!");
                        }
                            break;
                        } else if (cl.hasOption(updateEmployee.getOpt()) || cl.hasOption(updateEmployee.getLongOpt())) {

                            int id = Integer.parseInt(cl.getArgList().get(1));
                            String firstName = cl.getArgList().get(2), lastName = cl.getArgList().get(3), usernameE = cl.getArgList().get(4);
                            String oldPassword = PasswordField.readPassword("Old password: ");
                            String newPassword = PasswordField.readPassword("New password: ");

                            Employees employees = new Employees(id, firstName, lastName, usernameE, newPassword);
                            try{
                                employeeManager.checkPassword(usernameE, oldPassword);
                                    employeeManager.update(employees);
                                    System.out.println("You have successfully updated employee data!");
                            }catch (FlightsException exception){
                                System.out.println(exception.getMessage());
                            } catch(Exception e) {
                                System.out.println("Please enter employee id, first name, last name and username!");
                            }
                            break;
                        } else if (cl.hasOption(updateFlight.getOpt()) || cl.hasOption(updateFlight.getLongOpt())) {

                            int id = Integer.parseInt(cl.getArgList().get(1));
                            String airline = cl.getArgList().get(2), departure = cl.getArgList().get(3), arrival = cl.getArgList().get(4);

                            LocalDate dateOfDeparture = LocalDate.parse(cl.getArgList().get(5));

                            Flights flights = new Flights(id, airline, departure, arrival, dateOfDeparture);
                            try{
                                flightsManager.update(flights);
                                System.out.println("You have successfully updated flight!");
                            }catch (FlightsException exception){
                                System.out.println(exception.getMessage());
                            } catch(Exception e) {
                                System.out.println("Please enter flight id, airline, city of departure, city of arrival and date(in format yyyy-mm-dd)!");
                            }
                            break;
                        } else if (cl.hasOption(addEmployee.getOpt()) || cl.hasOption(addEmployee.getLongOpt())) {

                            int id = Integer.parseInt(cl.getArgList().get(1));
                            String firstName = cl.getArgList().get(2), lastName = cl.getArgList().get(3), usernameE = cl.getArgList().get(4);
                            String newPassword = PasswordField.readPassword("Password: ");
                            Employees employees = new Employees(id, firstName, lastName, usernameE, newPassword);
                            try{
                                employeeManager.add(employees);
                                System.out.println("You have successfully added employee!");
                            }catch (FlightsException exception){
                                System.out.println(exception.getMessage());
                            } catch(Exception e) {
                                System.out.println("Please enter employee id, first name, last name and username!");
                            }
                            break;
                        } else if(cl.hasOption(addFlight.getOpt()) || cl.hasOption(addFlight.getLongOpt())){
                            int id = Integer.parseInt(cl.getArgList().get(1));
                            String airline = cl.getArgList().get(2), departure = cl.getArgList().get(3), arrival = cl.getArgList().get(4);

                            LocalDate dateOfDeparture = LocalDate.parse(cl.getArgList().get(5));

                            Flights flights = new Flights(id, airline, departure, arrival, dateOfDeparture);
                            try{
                                flightsManager.add(flights);
                                System.out.println("You have successfully added flight!");
                            }catch (FlightsException exception){
                                System.out.println(exception.getMessage());
                            } catch(Exception e) {
                                System.out.println("Please enter flight id, airline, city of departure, city of arrival and date(in format yyyy-mm-dd)!");
                            }
                            break;
                        }
                    }
                }
            }catch (FlightsException exception){
                System.out.println(exception.getMessage());
            } catch(Exception e) {
                System.out.println("Please enter username!");
            }
        } else if(cl.hasOption(deleteFlight.getOpt()) || cl.hasOption(deleteFlight.getLongOpt()) || cl.hasOption(deleteEmployee.getOpt()) || cl.hasOption(deleteEmployee.getLongOpt()) || cl.hasOption(updateEmployee.getOpt()) || cl.hasOption(updateEmployee.getLongOpt()) || cl.hasOption(updateFlight.getOpt()) || cl.hasOption(updateFlight.getLongOpt()) || cl.hasOption(addFlight.getOpt()) || cl.hasOption(addFlight.getLongOpt()) || cl.hasOption(addEmployee.getOpt()) || cl.hasOption(addEmployee.getLongOpt())){
            System.out.println("For this option you need to log in first!");
        }else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
