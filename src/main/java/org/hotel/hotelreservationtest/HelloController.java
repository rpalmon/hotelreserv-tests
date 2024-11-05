package org.hotel.hotelreservationtest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;  

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private void onHelloButtonClick(ActionEvent event) {
        event.consume();
        System.out.println("Hello, JavaFX!");
    }
}