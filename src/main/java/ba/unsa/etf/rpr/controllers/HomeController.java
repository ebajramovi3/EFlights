package ba.unsa.etf.rpr.controllers;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public Hyperlink LogInId;
    public Button ArrivalId;
    public Button DepartureId;
    public Button SearchFlightsId;
    public Button CheckInId;

    public HomeController() {
    }


    public void ArrivalButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Arrivals.fxml"));
            //ArrivalsController controller = new ArrivalsController();
            //loader.setController(controller);
            stage.setTitle("Arrivals");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void DepartureButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Departures.fxml"));
            //DepartureController controller = new DepartureController();
            //loader.setController(controller);
            stage.setTitle("Departures");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void FlightButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SearchFlights.fxml"));
            //SearchFlightsController controller = new SearchFlightsController();
            //loader.setController(controller);
            stage.setTitle("Search");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void CheckinButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CheckIn.fxml"));
            //CheckInController controller = new CheckInController();
            //loader.setController(controller);
            stage.setTitle("Check In");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void LogInAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
            //LoginController controller = new LoginController();
            //loader.setController(controller);
            stage.setTitle("Log In");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
