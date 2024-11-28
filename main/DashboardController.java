package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button guestButton;
    @FXML
    private Button resButton;
    @FXML
    private Button roomButton;
    @FXML
    private Button logoutButton;

    // Method to handle "Guest" button click
    @FXML
    private void handlesGuestButton() {
        loadScene("guest.fxml");  // Load guest screen
    }

    // Method to handle "Reservation" button click
    @FXML
    private void handlesResButton() {
        loadScene("reservation.fxml");  // Load reservation screen
    }

    // Method to handle "Rooms" button click
    @FXML
    private void handlesRoomButton() {
        loadScene("room.fxml");  // Load rooms screen
    }

    // Method to load a new screen (FXML) based on the button clicked
    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();  // Load the FXML for the new screen
            Scene newScene = new Scene(root);  // Create a new scene from the FXML
            
            Stage stage = (Stage) guestButton.getScene().getWindow();
            stage.setScene(newScene);             
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle errors (e.g., if the FXML file doesn't exist)
        }
    }

    @FXML
    public void handleLogOutButton() {
        loadScene("login.fxml");
    }
}
