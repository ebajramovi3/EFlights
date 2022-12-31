package ba.unsa.etf.rpr.conrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    public Button okButton;
    public TextField UsernameId;
    public Label IncorrectUsername;
    public TextField PasswordId;
    public Label IncorrectPassword;
    public Button CancelButton;

    public void CancelButtonAction(ActionEvent actionEvent) {
    }

    public void okButtonAction(ActionEvent actionEvent) {
    }

    @FXML
    public void initilize(){
        UsernameId.textProperty().addListener((obs, oldValue, newValue)-> {
            if (newValue.length() >= 5)
                IncorrectUsername.setText("");
            else
                IncorrectUsername.setText("Incorrect username");
        });
        PasswordId.textProperty().addListener((obs, oldValue, newValue)->{
            if(newValue.length() >= 8)
                IncorrectPassword.setText("");
            else
                IncorrectPassword.setText("Incorrect password");
        });
    }

}
