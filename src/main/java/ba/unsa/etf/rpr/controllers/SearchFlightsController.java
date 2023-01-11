package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Flights;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SearchFlightsController {
    public TextField DepartureId;
    public TextField ArrivalId;
    public DatePicker DepartingDateId;
    public Button cancelButton;
    public Button SearchButtonId;

    public void CancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) DepartureId.getScene().getWindow();
        stage.close();
    }

    public void SearchButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/flights.fxml"));
            FlightsController controller = new FlightsController(ArrivalId.getText(), DepartureId.getText(), DepartingDateId.getValue());
            loader.setController(controller);
            stage.setTitle("Flights");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
