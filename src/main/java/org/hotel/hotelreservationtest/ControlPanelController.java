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
//Control panel is the main page after login for the 3 pages. 

public class ControlPanelController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button logoutButton; // Assuming fx:id of the Button is "logoutButton"

    //This method opens the guest book page
    @FXML
    private void openGuestBook (ActionEvent event) throws IOException {
        event.consume();
        System.out.println("Opening Guest Book");
        loadScene("guestbook.fxml", "Guest Book");


    }

    //This function opens rooomeditor page
    @FXML
    private void openRoomEditor (ActionEvent event) throws IOException {
        event.consume();
        System.out.println("Opening Room Editor");
        loadScene("roomeditor.fxml", "Room Editor");


    }

    //This method opens the reservation page
    @FXML
    private void openReservationManager (ActionEvent event) throws IOException {
        event.consume();
        System.out.println("Opening Reservation Manager");
        loadScene("reservationmanager.fxml", "Reservation Manager");

    }


    //This method opens the room page
    @FXML
    private void logout(ActionEvent event) {
        try {
            loadLoginScene();
        } catch (IOException e) {
            handleLoadingError(e);
        }
    }

    //This method loads the scene
    private void loadScene(String fxmlFile, String title) throws IOException {
        try {
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent loginRoot = loginLoader.load();
            Scene loginScene = new Scene(loginRoot);
            Stage currentStage = (Stage) logoutButton.getScene().getWindow();
            currentStage.setScene(loginScene);

            // set scene title to "Control Panel"
            currentStage.setTitle(title);
            
        } catch (IOException e) {
            System.err.println("Error loading " + fxmlFile + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    //This method loads the login for when you logout
    private void loadLoginScene() throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot);
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        currentStage.setScene(loginScene);

    }

    private void handleLoadingError(IOException e) {
        System.err.println("Error loading scene: " + e.getMessage());
        e.printStackTrace();
    }

    public void setWelcomeText(String text) {
        welcomeText.setText(text);
    }
}
