package org.hotel.hotelreservationtest;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;

public class GuestBookController {
    @FXML
    private Button back; // Assuming fx:id of the Button is "back"

    //function to return to control panel
    @FXML
    private void backToControlPanel(ActionEvent event) throws IOException {
        event.consume();
        FXMLLoader controlPanelPage = new FXMLLoader(getClass().getResource("controlpanel.fxml"));
        Parent loginRoot = controlPanelPage.load();
        Scene controlPanelScene = new Scene(loginRoot);
        Stage currentStage = (Stage) back.getScene().getWindow();
        currentStage.setScene(controlPanelScene);

    }
    
}
