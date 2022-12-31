package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchFlightsController {
    public TextField DepartureId;
    public TextField ArrivalId;
    public DatePicker DepartingDateId;
    public DatePicker ReturningDateId;
    public Button cancelButton;
    public Button SearchButtonId;

    public void CancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) DepartureId.getScene().getWindow();
        stage.close();
    }

    public void SearchButtonAction(ActionEvent actionEvent) {
    }
}
