package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Arrival;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.Date;

public class ArrivalsController {

    public TableView tableId;
    public TableColumn airlineId;
    public TableColumn destinationId;
    public TableColumn flightNumberId;
    public TableColumn dateId;
    public TextField searchId;
}
