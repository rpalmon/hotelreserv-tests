package org.hotel.hotelreservationtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

public class BaseController {
    protected void backToControlPanel(ActionEvent event, Button backButton) throws IOException {
        event.consume();
        FXMLLoader controlPanelPage = new FXMLLoader(getClass().getResource("controlpanel.fxml"));
        Parent loginRoot = controlPanelPage.load();
        Scene controlPanelScene = new Scene(loginRoot);
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.setScene(controlPanelScene);
    }
}
 