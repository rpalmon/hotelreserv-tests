package org.hotel.hotelreservationtest;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;


//This class handles controlpanel.fxml

public class ControlPanelController {

    @FXML
    private Button logoutButton; // Assuming fx:id of the Button is "logoutButton"

    @FXML
    private void openGuestBook(ActionEvent event) {
        event.consume();
        System.out.println("Opening Guest Book");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("guestbook.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Guest Book");
            //replace the current window with the new window
            stage.show();
            
            
                        // Parent guestBookRoot = guestBookLoader.load();
            // Scene guestBookScene = new Scene(guestBookRoot);
            // Stage currentStage = (Stage) username.getScene().getWindow(); // Make sure 'username' is not null
            // currentStage.setScene(guestBookScene);
            // currentStage.setTitle("Guest Book");
        } catch (IOException e) {
            System.err.println("Error loading guestbook.fxml: " + e.getMessage());
            e.printStackTrace(); // Better error visibility
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            // loggedInUser = null;
            loadLoginScene();
            // resetWelcomeText();
        } catch (IOException e) {
            // handleLoadingError(e);
        }
    }

    private void loadLoginScene() throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot);
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        currentStage.setScene(loginScene);
    }
}
