package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Guest;
import main.GuestData;

public class GuestController {

    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextArea addressField;

    @FXML
    private Button addButton;

    @FXML 
    private Button delButton;

    @FXML 
    private Button updateButton;

    @FXML 
    private Button searchButton;

    @FXML
    private Button backToDashboardButton;

    private GuestData guestData;

    public GuestController() {
        guestData = new GuestData();
    }

    // Method to add a guest
    @FXML
    public void addGuest() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        Guest guest = new Guest(0, name, email, phone, address);

        try {
            guestData.addGuest(guest);
            showAlert("Success", "Guest added successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            showAlert("Error", "Failed to add guest. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Method to update a guest
    @FXML
    public void updateGuest() {
        if ( idField.getText()==null || 
            nameField.getText().isEmpty() || 
            emailField.getText().isEmpty() || 
            phoneField.getText().isEmpty() || 
            addressField.getText().isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }
        
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        

        Guest guest = new Guest(id, name, email, phone, address);

        try {
            guestData.updateGuest(guest);
            showAlert("Success", "Guest updated successfully!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to update guest. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Method to delete a guest
    @FXML
    public void deleteGuest() {
        try {
            int id = Integer.parseInt(idField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid guest ID.", Alert.AlertType.ERROR);
                return;
            }

            guestData.delGuest(id);
            showAlert("Success", "Guest deleted successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid guest ID.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Failed to delete guest. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void searchGuest() {
        try {
            int id = Integer.parseInt(idField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid guest ID.", Alert.AlertType.ERROR);
                return;
            }

            Guest guest = guestData.getGuestID(id);
            if (guest != null) {
                nameField.setText(guest.getName());
                emailField.setText(guest.getEmail());
                phoneField.setText(guest.getPhone());
                addressField.setText(guest.getAddress());
            } else {
                showAlert("Info", "Guest not found.", Alert.AlertType.INFORMATION);
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid guest ID.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
    }

    @FXML
    public void handleBackToDashboardButton() {
        loadScene("dashboard.fxml", "Dashboard");
    }

    private void loadScene(String fxmlFile, String title) {
        try {
            Stage stage = (Stage) backToDashboardButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (Exception e) {
            showAlert("Error", "Unable to load " + title + " view. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}