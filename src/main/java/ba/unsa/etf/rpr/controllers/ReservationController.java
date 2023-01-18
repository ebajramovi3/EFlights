package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PersonsManager;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class ReservationController {
    private int id;
    private final PersonsManager personManager = new PersonsManager();
    private final FlightsManager flightsManager = new FlightsManager();
    public ReservationController(int id){
        this.id = id;
    }

    public TextField FirstNameId;
    public TextField LastNameId;
    public TextField PassportId;
    public Button OkButtonId;

    public void OkAction(ActionEvent actionEvent){
        try{
            personManager.add(new Persons(Integer.valueOf(PassportId.getText()), FirstNameId.getText(), LastNameId.getText(), null, null, false, flightsManager.getById(id)));
            
        } catch (FlightsException exception){
            new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
        }

    }
}