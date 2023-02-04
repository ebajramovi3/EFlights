package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PersonsManager;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class CheckInController {
    private final PersonsManager personsManager = new PersonsManager();
    private final FlightsManager flightsManager = new FlightsManager();
    public TextField passportId;
    public TextField flightNumberId;
    public TextField firstNameId;
    public TextField lastNameId;
    public TextField CitizenshipId;
    public DatePicker dateId;
    public Button OkButtonId;

    public void okButtonAction(ActionEvent actionEvent) {
        boolean incorrectData = false;
            try {
                if(passportId.getText().length() == 0)
                    throw new FlightsException("No id specified!");
                if(flightNumberId.getText().length() == 0)
                    throw new FlightsException("No id specified!");
                Persons person = new Persons(Integer.valueOf(passportId.getText()), firstNameId.getText(), lastNameId.getText(), CitizenshipId.getText(), dateId.getValue(), true, flightsManager.getById(Integer.valueOf(flightNumberId.getText())));
                Persons getFromDB = personsManager.getById(person.getId());
                if (!getFromDB.getFirstName().equals(firstNameId.getText()) || !getFromDB.getLastName().equals(lastNameId.getText()) || getFromDB.getFlight().getId() != Integer.valueOf(flightNumberId.getText())) {
                    incorrectData = true;
                    throw new FlightsException("Invalid data!");
                }
                if (getFromDB.isCheckIn())
                    throw new FlightsException("You've already checked in!");
                personsManager.update(person);
            } catch (FlightsException exception) {
                new Alert(Alert.AlertType.NONE, exception.getMessage(), ButtonType.OK).show();
                incorrectData = true;
            }
        if(!incorrectData)
        OkButtonId.getScene().getWindow().hide();
    }


}
