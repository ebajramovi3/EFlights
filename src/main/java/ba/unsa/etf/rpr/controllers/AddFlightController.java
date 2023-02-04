package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class AddFlightController {
    private final FlightsManager flightsManager = new FlightsManager();

    public TextField flightId;
    public TextField airlineId;
    public TextField departureId;
    public TextField arrivalId;
    public DatePicker dateId;
    public Button okButton;


    public void okButtonAction(ActionEvent actionEvent) {
        Flights flights = new Flights();
        try{
            if(flightId.getText().length() == 0)
                throw new FlightsException("No id specified!");
            flights.setId(Integer.parseInt(flightId.getText()));
            flights.setNameOfAirline(airlineId.getText());
            flights.setCityOfDeparture(departureId.getText());
            flights.setCityOfArrival(arrivalId.getText());
            flights.setDate(dateId.getValue());
            flightsManager.add(flights);
            okButton.getScene().getWindow().hide();
        }catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
    }
}
