package ba.unsa.etf.rpr.conrollers;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public Hyperlink LogInId;
    public Button ArrivalId;
    public Button DepartireId;
    public Button SearchFlightsId;
    public Button CheckInId;

    public HomeController() {
    }


    public void ArrivalButtonAction(ActionEvent actionEvent) {

    }

    public void DepartureButtonAction(ActionEvent actionEvent) {
    }

    public void FlightButtonAction(ActionEvent actionEvent) {
    }

    public void CheckinButtonAction(ActionEvent actionEvent) {
    }

    public void LogInAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
            LoginController controller = new LoginController();
            loader.setController(controller);
            stage.setTitle("Log In");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
