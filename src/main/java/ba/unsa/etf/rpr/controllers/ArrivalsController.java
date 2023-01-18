package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class ArrivalsController {
    private final FlightsManager arrivalManager = new FlightsManager();

    public TableView<Flights> tableId;
    public TableColumn<Flights, String> airlineId;
    public TableColumn<Flights, String> destinationId;
    public TableColumn<Flights, Integer> flightNumberId;
    public TableColumn<Flights, Date> dateId;
    public TextField searchId;

    @FXML
    public void initialize(){
        airlineId.setCellValueFactory(new PropertyValueFactory<Flights, String>("nameOfAirline"));
        destinationId.setCellValueFactory(new PropertyValueFactory<Flights, String>("cityOfDeparture"));
        flightNumberId.setCellValueFactory(new PropertyValueFactory<Flights, Integer>("id"));
        dateId.setCellValueFactory(new PropertyValueFactory<Flights, Date>("date"));

        try{
            tableId.setItems(FXCollections.observableList(arrivalManager.searchArrivals("Sarajevo")));
            tableId.refresh();
        } catch (FlightsException exception){
        }
    }

    public void SearchAction(ActionEvent actionEvent) {
        try{
            tableId.setItems(FXCollections.observableList(arrivalManager.searchArrivalsDepartures("Sarajevo", searchId.getText())));
            tableId.refresh();
        } catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
    }
}
