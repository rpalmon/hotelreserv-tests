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

    @FXML
    private void openGuestBook(ActionEvent event) {
        event.consume();
        System.out.println("Opening Guest Book");
        loadScene("guestbook.fxml", "Guest Book");
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            loadLoginScene();
        } catch (IOException e) {
            handleLoadingError(e);
        }
    }

    private void loadScene(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading " + fxmlFile + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

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
