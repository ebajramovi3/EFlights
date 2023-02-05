package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * JavaFX controller for update of flights
 * @author Esma
 */
public class UpdateFlightController {
    private Flights flights;
    private final FlightsManager flightsManager = new FlightsManager();

    public TextField flightId;
    public TextField airlineId;
    public TextField departureId;
    public TextField arrivalId;
    public DatePicker dateId;
    public Button okButton;

    public UpdateFlightController(Flights flights){
        this.flights = flights;
    }

    @FXML
    public void initialize(){
        flightId.setText(String.valueOf(flights.getId()));
        airlineId.setText(flights.getNameOfAirline());
        departureId.setText(flights.getCityOfDeparture());
        arrivalId.setText(flights.getCityOfArrival());
        dateId.setValue(flights.getDate());
    }

    /**
     * ok button event handler, updates flight based on given data
     * @param actionEvent
     */
    public void okButtonAction(ActionEvent actionEvent) {
        try{
            flights.setNameOfAirline(airlineId.getText());
            flights.setCityOfDeparture(departureId.getText());
            flights.setCityOfArrival(arrivalId.getText());
            flights.setDate(dateId.getValue());
            flightsManager.update(flights);
            okButton.getScene().getWindow().hide();
        }catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
    }
}
