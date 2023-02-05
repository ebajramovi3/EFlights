package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller with options employees and flights
 * @author Esma
 */
public class AfterLoginController {
    public Button employeesId;
    public Button flightsId;

    /**
     * employees button event handler, opens new window for managing employees data
     * @param actionEvent
     */
    public void employeesButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employeesChanges.fxml"));
        stage.setTitle("Employees");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * flights button event handler, opens new window for managing flights
     * @param actionEvent
     */
    public void flightsButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/flightsChanges.fxml"));
        stage.setTitle("Flights");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

}
