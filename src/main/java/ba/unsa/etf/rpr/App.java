package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PersonsManager;
import ba.unsa.etf.rpr.domain.Flights;
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



    /**
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


        if((cl.hasOption(arrivals.getOpt()) || cl.hasOption(arrivals.getLongOpt())) && cl.hasOption((arrivals.getLongOpt()))){
            FlightsManager flightsManager = new FlightsManager();
            List<Flights> flights = new ArrayList<>();

            try {
                flightsManager.searchArrivals("Sarajevo").forEach(f -> System.out.println(f.getFlight()));
            }catch(Exception e) {
                System.out.println("There is no arrivals from this city! Try again.");
                System.exit(1);
            }
        } else if(cl.hasOption(departures.getOpt()) || cl.hasOption(departures.getLongOpt())){
            FlightsManager flightsManager = new FlightsManager();

            flightsManager.searchDepartures("Sarajevo").forEach(f -> System.out.println(f.getFlight()));
        } else if(cl.hasOption(findFlights.getOpt()) || cl.hasOption(findFlights.getLongOpt())){
            FlightsManager flightsManager = new FlightsManager();
            try {
                String departure = cl.getArgList().get(0);
                String arrival = cl.getArgList().get(1);
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. d.");
                LocalDate dateOfDeparture = LocalDate.parse(cl.getArgList().get(2));

                Flights flight = new Flights(0, null, departure, arrival, dateOfDeparture);
                flightsManager.searchFlight(flight).forEach(f -> System.out.println(f.getFlight()));
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }

        } else if(cl.hasOption(checkIn.getOpt()) || cl.hasOption(checkIn.getLongOpt())){
            PersonsManager personsManager = new PersonsManager();



        } else {
            printFormattedOptions(options);
            System.exit(-1);
//                break;
        }
//        }
    }
}
