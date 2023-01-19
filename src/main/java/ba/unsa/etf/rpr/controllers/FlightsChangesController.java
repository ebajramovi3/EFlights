package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class FlightsChangesController {
    private final FlightsManager flightsManager = new FlightsManager();

    public TableView<Flights> tableId;
    public TableColumn<Flights, String> airlineId;
    public TableColumn<Flights, Integer> flightId;
    public TableColumn<Flights, String> departureId;
    public TableColumn<Flights, String> arrivalId;
    public TableColumn<Flights, Date> dateId;
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;

    public void addButtonAction(ActionEvent actionEvent) {
    }

    public void updateButtonAction(ActionEvent actionEvent) {
    }

    public void deleteButtonAction(ActionEvent actionEvent) {
        Flights flight = tableId.getSelectionModel().getSelectedItem();
        try {
            flightsManager.delete(flight.getId());
            tableId.refresh();
        } catch (FlightsException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize(){
        airlineId.setCellValueFactory(new PropertyValueFactory<Flights, String>("nameOfAirline"));
        departureId.setCellValueFactory(new PropertyValueFactory<Flights, String>("cityOfDeparture"));
        arrivalId.setCellValueFactory(new PropertyValueFactory<Flights, String>("CityOfArrival"));
        flightId.setCellValueFactory(new PropertyValueFactory<Flights, Integer>("id"));
        dateId.setCellValueFactory(new PropertyValueFactory<Flights, Date>("date"));

        try{
            tableId.setItems(FXCollections.observableList(flightsManager.getAll()));
            tableId.refresh();
        } catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
    }
}
