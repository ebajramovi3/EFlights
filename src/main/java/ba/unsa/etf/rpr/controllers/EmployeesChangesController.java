package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for add, delete and update of employee data
 * @author Esma
 */
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

    /**
     * add button event handler, opens window for adding new employee
     * @param actionEvent
     */
    public void addButtonAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEmployee.fxml"));
        stage.setTitle("Add employee");
        try {
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        } catch (IOException e) {
        }
        stage.setResizable(false);
        stage.show();
    }

    /**
     * update button event handler, opens new window for update of selected employee data
     * @param actionEvent
     */
    public void updateButtonAction(ActionEvent actionEvent) {
        try {
            if(tableId.getSelectionModel().isEmpty())
                throw new FlightsException("No rows selected!");

            Employees employee = tableId.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updateEmployee.fxml"));
            UpdateEmployeeController controller = new UpdateEmployeeController(employee);
            loader.setController(controller);
            stage.setTitle("Update employee");

            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (FlightsException exception) {
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        } catch (IOException e){
        }

    }

    /**
     * delete button event handler, deletes selected employee data
     * @param actionEvent
     */
    public void deleteButtonAction(ActionEvent actionEvent) {
        Employees employee = tableId.getSelectionModel().getSelectedItem();
        try {
            if(tableId.getSelectionModel().isEmpty())
                throw new FlightsException("No rows selected!");
            employeeManager.delete(employee.getId());
            tableId.refresh();
        } catch (FlightsException exception) {
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }
        try{
            tableId.setItems(FXCollections.observableList(employeeManager.getAll()));
            tableId.refresh();
        }catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
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
