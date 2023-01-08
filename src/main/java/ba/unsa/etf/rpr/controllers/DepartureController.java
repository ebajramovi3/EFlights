package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class DepartureController {
    FlightsManager departureManager = new FlightsManager();

    public TableView tableId;
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
            tableId.setItems(FXCollections.observableList(departureManager.searchDepartures("Sarajevo")));
            tableId.refresh();
        } catch (FlightsException exception){

        }
    }
}
