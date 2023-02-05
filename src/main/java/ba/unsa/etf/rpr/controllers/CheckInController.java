package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PersonsManager;
import ba.unsa.etf.rpr.domain.Persons;
import ba.unsa.etf.rpr.exceptions.FlightsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Pattern;

/**
 * JavaFX controller for check in
 */
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

    @FXML
    public void initialize(){
        passportId.textProperty().addListener((abs, oldValue, newValue)-> {
            if(newValue.length() > 0){
                try {
                    Persons person = personsManager.getById(Integer.parseInt(newValue.trim()));
                    flightNumberId.setText(String.valueOf(person.getFlight().getId()));
                    firstNameId.setText(person.getFirstName());
                    lastNameId.setText(person.getLastName());
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * ok button event handler
     * @param actionEvent
     */
    public void okButtonAction(ActionEvent actionEvent) {
        boolean incorrectData = false;
            try {
                if(passportId.getText().length() == 0)
                    throw new FlightsException("No id specified!");
                if(flightNumberId.getText().length() == 0)
                    throw new FlightsException("No id specified!");
                if(!Pattern.compile("[0-9]*").matcher(passportId.getText()).matches())
                    throw new FlightsException("Invalid password!");

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
