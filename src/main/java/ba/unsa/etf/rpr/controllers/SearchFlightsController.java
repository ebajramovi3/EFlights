package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller that allows to provide information for flight
 * @author Esma
 */
public class SearchFlightsController {
    public TextField DepartureId;
    public TextField ArrivalId;
    public DatePicker DepartingDateId;
    public Button cancelButton;
    public Button SearchButtonId;

    /**
     * cancel button event handler
     * @param actionEvent
     */
    public void CancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) DepartureId.getScene().getWindow();
        stage.close();
    }

    /**
     * search button event handler, opens window with all flights that contain provided data
     * @param actionEvent
     */
    public void SearchButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/flights.fxml"));
            if(ArrivalId.getText().length() == 0 || DepartureId.getText().length() == 0 || DepartingDateId.getValue() == null)
                throw new FlightsException("Invalid data!");
            FlightsController controller = new FlightsController(ArrivalId.getText(), DepartureId.getText(), DepartingDateId.getValue());
            loader.setController(controller);
            stage.setTitle("Flights");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
            SearchButtonId.getScene().getWindow().hide();
        } catch (Exception exception) {
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }

    }
}
