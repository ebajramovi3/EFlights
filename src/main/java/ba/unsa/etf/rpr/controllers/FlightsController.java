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
import java.time.LocalDate;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class FlightsController {
    private final FlightsManager flightsManager = new FlightsManager();
    public Button ReservationButton;
    private Flights flights;
    public TableView<Flights> tableId;
    public TableColumn<Flights, String> AirlineNameId;
    public TableColumn<Flights, Integer> FlightId;
    public TableColumn<Flights, String> DepartureId;
    public TableColumn<Flights, String> ArrivalId;
    public TableColumn<Flights, Date> DateId;


    public FlightsController(String cityOfArrival, String cityOfDeparture, LocalDate date){
        this.flights = new Flights(0, "", cityOfArrival, cityOfDeparture, date);
    }

    @FXML
    public void initialize(){
        AirlineNameId.setCellValueFactory(new PropertyValueFactory<Flights, String>("nameOfAirline"));
        DepartureId.setCellValueFactory(new PropertyValueFactory<Flights, String>("cityOfDeparture"));
        ArrivalId.setCellValueFactory(new PropertyValueFactory<Flights, String>("CityOfArrival"));
        FlightId.setCellValueFactory(new PropertyValueFactory<Flights, Integer>("id"));
        DateId.setCellValueFactory(new PropertyValueFactory<Flights, Date>("date"));

        try{
            tableId.setItems(FXCollections.observableList(flightsManager.searchFlight(flights)));
            tableId.refresh();
        } catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
    }

    public void ReservationAction(ActionEvent actionEvent) {
        Flights flight = tableId.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reservation.fxml"));
            ReservationController controller = new ReservationController(flight.getId());
            loader.setController(controller);
            stage.setTitle("Reservation");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
