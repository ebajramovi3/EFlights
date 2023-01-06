package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArrivalManager;
import ba.unsa.etf.rpr.domain.Arrival;
import ba.unsa.etf.rpr.domain.Departure;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArrivalsController {
    private final ArrivalManager arrivalManager = new ArrivalManager();

    public TableView tableId;
    public TableColumn<Flights, String> airlineId;
    public TableColumn<Departure, String> destinationId;
    public TableColumn<Flights, Integer> flightNumberId;
    public TableColumn<Departure, Date> dateId;
    public TextField searchId;

    @FXML
    public void initialize(){
        /*airlineId.setCellValueFactory(new PropertyValueFactory<Flights, String>("airline_name"));
        destinationId.setCellValueFactory(new PropertyValueFactory<Departure, String>(""));
        flightNumberId.setCellValueFactory(new PropertyValueFactory<Flights, Integer>("id"));
        dateId.setCellValueFactory(new PropertyValueFactory<Departure, Date>("date"));

        try{
            List<Arrival> arrivals = arrivalManager.getAll();
            airlineId.set)C
            tableId.refresh();
        } catch (FlightsException exception){

        }*/
    }

}
