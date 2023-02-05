package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller that adds new employee
 * @author Esma
 */
public class AddEmployeeController {
    //manager
    private final EmployeeManager employeeManager = new EmployeeManager();

    public TextField employeeId;
    public TextField lastName;
    public TextField usernameId;
    public PasswordField passwordId;
    public TextField firstName;
    public Button okButton;
    public Label passwordLabel;
    public Label usernameLabel;

    @FXML
    public void initialize(){
        passwordId.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 8)
                passwordLabel.setText("");
            else
                passwordLabel.setText("Incorrect format");
        });
        usernameId.textProperty().addListener((abs, oldValue, newValue)->{
            if(newValue.length() >= 5)
                usernameLabel.setText("");
            else
                usernameLabel.setText("Incorrect format");
        });
    }

    /**
     * ok button event handler, adds new employee
     * @param actionEvent
     */
    public void okButtonAction(ActionEvent actionEvent) {
        try{
            if(employeeId.getText().length() == 0)
                throw new FlightsException("No id specified!");
            Employees employees = new Employees();
            employees.setId(Integer.parseInt(employeeId.getText()));
            employees.setFirstName(firstName.getText());
            employees.setLastName(lastName.getText());
            employees.setUsername(usernameId.getText());
            employees.setPassword(passwordId.getText());
            employeeManager.add(employees);
            okButton.getScene().getWindow().hide();
        }catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
    }
}
