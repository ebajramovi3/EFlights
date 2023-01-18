package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    private final EmployeeManager employeeManager = new EmployeeManager();

    public Button okButton;
    public TextField UsernameId;
    public Label IncorrectUsername;
    public PasswordField PasswordId;
    public Label IncorrectPassword;
    public Button CancelButton;

    public void CancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) UsernameId.getScene().getWindow();
        stage.close();
    }

    public void okButtonAction(ActionEvent actionEvent) {
        boolean incorrectData = false;
            try{
                employeeManager.checkPassword(UsernameId.getText(), PasswordId.getText());
            } catch (FlightsException exception){
                incorrectData = true;
                new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
            }
            if(!incorrectData)
                okButton.getScene().getWindow().hide();
    }

    @FXML
    public void initialize(){
        UsernameId.textProperty().addListener((abs, oldValue, newValue)-> {
            if (newValue.length() >= 5)
                IncorrectUsername.setText("");
            else
                IncorrectUsername.setText("Username must have 5 or more characters.");
        });
        PasswordId.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 8)
                IncorrectPassword.setText("");
            else
                IncorrectPassword.setText("Password must have 8 or more characters.");
        });
    }

}
