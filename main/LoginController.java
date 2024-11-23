package main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {
    //user name id and password fiels and text inputs
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label messageLabel;
    
    //create the user data access object (acces user form sql)
    private UserData user;

    //initiates the user dao
    public LoginController(){
        user=new UserData();
    }

    //select text from the user box and checks it with the validuser method from the userDATA class
    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // String username = "admin";
        // String password = "password";
    
        if (user.validUser(username, password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
            loadNewScreen("dashboard.fxml");
        } else {
            messageLabel.setText("Invalid username or password.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }

        
    }
    //if it works it logs in into the dashboard
    private void loadNewScreen(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane root = loader.load();
            Scene newScene = new Scene(root);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Dashboard");
            stage.show();

        } catch (IOException e) {
            System.err.println("Dashboard has gone wrong." +e.getMessage());
        }
    }
}
