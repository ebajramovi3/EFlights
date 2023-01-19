package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class EmployeesChangesController {
    private final EmployeeManager employeeManager = new EmployeeManager();

    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public TableView<Employees> tableId;
    public TableColumn<Employees, Integer> employeeId;
    public TableColumn<Employees, String> lastNameId;
    public TableColumn<Employees, String> firstNameId;
    public TableColumn<Employees, String> usernameId;

    public void addButtonAction(ActionEvent actionEvent) {
    }

    public void updateButtonAction(ActionEvent actionEvent) {
    }

    public void deleteButtonAction(ActionEvent actionEvent) {
        Employees employee = tableId.getSelectionModel().getSelectedItem();
        try {
            employeeManager.delete(employee.getId());
            tableId.refresh();
        } catch (FlightsException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize(){
        employeeId.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("id"));
        lastNameId.setCellValueFactory(new PropertyValueFactory<Employees, String>("lastName"));
        firstNameId.setCellValueFactory(new PropertyValueFactory<Employees, String>("firstName"));
        usernameId.setCellValueFactory(new PropertyValueFactory<Employees, String>("username"));

        try{
            tableId.setItems(FXCollections.observableList(employeeManager.getAll()));
            tableId.refresh();
        } catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
    }
}
