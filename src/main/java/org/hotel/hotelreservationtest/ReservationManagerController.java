package org.hotel.hotelreservationtest;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class ReservationManagerController extends BaseController {
    @FXML
    private Button back; // Assuming fx:id of the Button is "back"

    @FXML
    private ChoiceBox<String> reservationStatus; // Assuming fx:id of the ChoiceBox is "reservationStatus"

    //function to return to control panel

    @FXML
    public void initialize() {
        // Populate the ChoiceBox with status options
        reservationStatus.getItems().addAll("booked", "checked_in", "checked_out", "cancelled");
    }

    //function to return to control panel
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        backToControlPanel(event, back);

    }

    
    
}
