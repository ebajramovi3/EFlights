package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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

    public void okButtonAction(ActionEvent actionEvent){
        try{
            if(employeeManager.checkPassword(UsernameId.getText(), PasswordId.getText())) {
                okButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AfterLogin.fxml"));
                Stage stage = new Stage();
                stage.setTitle("");
                try {
                    stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();}
            }catch(FlightsException exception) {
                new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
            }
    }

    @FXML
    public void initialize(){
        UsernameId.textProperty().addListener((abs, oldValue, newValue)-> {
            if (newValue.length() >= 5)
                IncorrectUsername.setText("");
            else {
                IncorrectUsername.setText("Invalid format");
            }
        });
        PasswordId.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 8)
                IncorrectPassword.setText("");
            else
                IncorrectPassword.setText("Invalid format");
        });
    }

}
