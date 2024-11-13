package org.hotel.hotelreservationtest;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class ReservationManagerController extends BaseController {
    @FXML
    private Button back; // Assuming fx:id of the Button is "back"



    //function to return to control panel
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        backToControlPanel(event, back);

    }

    
    
}
