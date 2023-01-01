package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    public Button okButton;
    public TextField UsernameId;
    public Label IncorrectUsername;
    public TextField PasswordId;
    public Label IncorrectPassword;
    public Button CancelButton;

    public void CancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) UsernameId.getScene().getWindow();
        stage.close();
    }

    public void okButtonAction(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize(){
        UsernameId.textProperty().addListener((abs, oldValue, newValue)-> {
            if (newValue.length() >= 5)
                IncorrectUsername.setText("");
            else
                IncorrectUsername.setText("Incorrect username");
        });
        PasswordId.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 8)
                IncorrectPassword.setText("");
            else
                IncorrectPassword.setText("Incorrect password");
        });
    }

}
