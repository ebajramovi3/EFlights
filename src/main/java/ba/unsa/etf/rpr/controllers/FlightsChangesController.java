package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addFlight.fxml"));
        stage.setTitle("Add flight");
        try {
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void updateButtonAction(ActionEvent actionEvent) {

        try {
            if(tableId.getSelectionModel().isEmpty())
                throw new FlightsException("No rows selected!");
            Flights flights = tableId.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updateFlight.fxml"));
            UpdateFlightController controller = new UpdateFlightController(flights);
            loader.setController(controller);
            stage.setTitle("Update flight");

            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (FlightsException exception) {
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        } catch (IOException e){
        }

    }

    public void deleteButtonAction(ActionEvent actionEvent) {

        try {
            if(tableId.getSelectionModel().isEmpty())
                throw new FlightsException("No rows selected!");
            Flights flight = tableId.getSelectionModel().getSelectedItem();
            flightsManager.delete(flight.getId());
            tableId.refresh();
        } catch (FlightsException e) {
            throw new RuntimeException(e);
        }
        try{
            tableId.setItems(FXCollections.observableList(flightsManager.getAll()));
            tableId.refresh();
        } catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
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
