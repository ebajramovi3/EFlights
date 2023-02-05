package ba.unsa.etf.rpr.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller that contains options:
 * Arrivals(shows all arrivals),
 * Departures(shows all departures),
 * Find flights(shows flights based on given data),
 * Check-in(allows check-in of reserved plane ticket),
 * Log in(allows log in of employees)
 * @author Esma
 */
public class HomeController {
    public Hyperlink LogInId;
    public Button ArrivalId;
    public Button DepartureId;
    public Button SearchFlightsId;
    public Button CheckInId;

    public HomeController() {
    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * file->close button event handler
     * @param actionEvent
     */
    public void closeApp(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
    public void openAbout(ActionEvent actionEvent){
        openDialog("About", "/fxml/about.fxml", null);
    }

    /**
     * arrival button event handler
     * @param actionEvent
     */
    public void ArrivalButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Arrivals.fxml"));
            //ArrivalsController controller = new ArrivalsController();
            //loader.setController(controller);
            stage.setTitle("Arrivals");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * departure button event handler
     * @param actionEvent
     */
    public void DepartureButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Departures.fxml"));
            //DepartureController controller = new DepartureController();
            //loader.setController(controller);
            stage.setTitle("Departures");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * flight button event handler
     * @param actionEvent
     */
    public void FlightButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SearchFlights.fxml"));
            //SearchFlightsController controller = new SearchFlightsController();
            //loader.setController(controller);
            stage.setTitle("Search");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * check-in button event handler
     * @param actionEvent
     */
    public void CheckinButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CheckIn.fxml"));
            //CheckInController controller = new CheckInController();
            //loader.setController(controller);
            stage.setTitle("Check In");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * log in button event handler
     * @param actionEvent
     */
    public void LogInAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
            //LoginController controller = new LoginController();
            //loader.setController(controller);
            stage.setTitle("Log In");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
