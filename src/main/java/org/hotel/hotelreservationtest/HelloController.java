package org.hotel.hotelreservationtest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;  
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    // private String userName = "admin";
    // private String password = "admin";

     @FXML
    private TextField username; // Assuming fx:id of the TextField is "username"

    @FXML
    private PasswordField password; // Assuming fx:id of the PasswordField is "password"


    @FXML
    private void login(ActionEvent event) {
        event.consume();
        // System.out.println("Hello, JavaFX!");
        // System.out.println(username.getText());

        
        
    }
}