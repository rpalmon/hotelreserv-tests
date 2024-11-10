package org.hotel.hotelreservationtest;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import java.io.IOException;

//This class controlls login.fxml

public class HelloController {

    // private String userName = "admin";
    // private String password = "admin";

    @FXML
    private TextField username; // Assuming fx:id of the TextField is "username"

    @FXML
    private PasswordField password; // Assuming fx:id of the PasswordField is "password"

    @FXML
    private Button logoutButton; // Assuming fx:id of the Button is "logoutButton"

    @FXML
    private Label welcomeText; // Assuming fx:id of the Label is "welcomeText"

    @FXML
    public void initialize() {
        System.out.println("HelloController initialized");
        // setKeyEventHandlers();
    }

    private String loggedInUser;
    private boolean loggedIn = false;

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

                // set scene title to "Control Panel"
                currentStage.setTitle("Control Panel");
                loggedInUser = username.getText();
                loggedIn = true;

                System.out.println("Welcome " + loggedInUser);
            } catch (IOException e) {
                System.err.println("Error loading controlpanel.fxml: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.out.println("Login Failed");

        }
    }

}