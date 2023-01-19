package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateEmployeeController {
    private Employees employees;
    private final EmployeeManager employeeManager = new EmployeeManager();

    public TextField employeeId;
    public TextField lastName;
    public TextField usernameId;
    public PasswordField oldPassword;
    public PasswordField newPassword;
    public TextField firstName;
    public Button okButton;
    public Label newPasswordLabel;
    public Label oldPasswordLabel;
    public Label usernameLabel;

    UpdateEmployeeController(Employees employees){
        this.employees = employees;
    }

    @FXML
    public void initialize(){
        employeeId.setText(String.valueOf(employees.getId()));
        lastName.setText(employees.getLastName());
        firstName.setText(employees.getFirstName());
        usernameId.setText(employees.getUsername());
        oldPassword.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 8)
                oldPasswordLabel.setText("");
            else
                oldPasswordLabel.setText("Incorrect format");
        });
        newPassword.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 8)
                newPasswordLabel.setText("");
            else
                newPasswordLabel.setText("Incorrect format");
        });
        usernameId.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 5)
                usernameLabel.setText("");
            else
                usernameLabel.setText("Incorrect format");
        });
    }

    public void okButtonAction(ActionEvent actionEvent) {
        try {
            employeeManager.checkPassword(employees.getUsername(), oldPassword.getText());
            employees.setFirstName(firstName.getText());
            employees.setLastName(lastName.getText());
            employees.setUsername(usernameId.getText());
            employees.setPassword(newPassword.getText());
            employeeManager.update(employees);
            okButton.getScene().getWindow().hide();
        } catch (FlightsException exception) {
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }

    }
}
