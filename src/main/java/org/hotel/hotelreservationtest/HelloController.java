package org.hotel.hotelreservationtest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

import javafx.event.ActionEvent;  
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Button logoutButton; // Assuming fx:id of the Button is "logoutButton"
    

    @FXML
    private void login(ActionEvent event) {
        event.consume();
        System.out.println("Username: " + username.getText() + " Password: " + password.getText());
        boolean nameCorrect = username.getText().equals("admin");
        if (nameCorrect && password.getText().equals("admin")) {
            System.out.println("Login Successful");

            try {
                FXMLLoader controlPanelLoader = new FXMLLoader(HelloApplication.class.getResource("controlpanel.fxml"));
                Parent controlPanelRoot = controlPanelLoader.load();

                Scene controlPanelScene = new Scene(controlPanelRoot);
                Stage currentStage = (Stage) username.getScene().getWindow();
                currentStage.setScene(controlPanelScene);

            } catch (IOException e) {
                System.err.println("Error loading controlpanel.fxml: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.out.println("Login Failed");
        }
    }

    // In your ControlPanelController.java (controller for controlpanel.fxml)

@FXML
private void logout(ActionEvent event) {
    try {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginRoot = loginLoader.load();

        Scene loginScene = new Scene(loginRoot);
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        currentStage.setScene(loginScene);
        

        

    } catch (IOException e) {
        System.err.println("Error loading login.fxml: " + e.getMessage());
        e.printStackTrace();
    }
}
}