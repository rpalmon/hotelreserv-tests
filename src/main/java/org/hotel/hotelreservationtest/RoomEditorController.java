package org.hotel.hotelreservationtest;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RoomEditorController extends BaseController {

    // FXML Mapped Components
    @FXML
    private Button back; // Back button
    @FXML
    private Button saveButton; // Save button
    @FXML
    private Button deleteButton; // Delete button
    @FXML
    private Button updateButton; // Update button
    @FXML
    private TextField roomIDField; // TextField for Room ID
    @FXML
    private TextField roomNumberField; // TextField for Room Number
    @FXML
    private TextField roomTypeField; // TextField for Room Type
    @FXML
    private TextField priceField; // TextField for Price
    @FXML
    private CheckBox availableCheckBox; // CheckBox for Availability

    // Initialize method to set default values or behaviors
    @FXML
    public void initialize() {
        // Example: You can initialize fields or set default values if needed.
        clearFields();
    }

    // Function to handle back button click
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        backToControlPanel(event, back); // Navigate to the control panel
    }

    // Function to handle save button click
    @FXML
    private void handleSaveButton(ActionEvent event) {
        // Retrieve data from input fields
        String roomID = roomIDField.getText();
        String roomNumber = roomNumberField.getText();
        String roomType = roomTypeField.getText();
        String price = priceField.getText();
        boolean isAvailable = availableCheckBox.isSelected();

        // Save logic goes here (e.g., call a service or DAO to save the room details)
        System.out.println("Saving Room: ID=" + roomID + ", Number=" + roomNumber + ", Type=" + roomType + ", Price=" + price + ", Available=" + isAvailable);
        
        // Clear fields after saving
        clearFields();
    }

    // Function to handle delete button click
    @FXML
    private void handleDeleteButton(ActionEvent event) {
        String roomID = roomIDField.getText();

        // Delete logic goes here (e.g., call a service or DAO to delete the room by ID)
        System.out.println("Deleting Room: ID=" + roomID);
        
        // Clear fields after deletion
        clearFields();
    }

    // Function to handle update button click
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        // Retrieve data from input fields
        String roomID = roomIDField.getText();
        String roomNumber = roomNumberField.getText();
        String roomType = roomTypeField.getText();
        String price = priceField.getText();
        boolean isAvailable = availableCheckBox.isSelected();

        // Update logic goes here (e.g., call a service or DAO to update room details)
        System.out.println("Updating Room: ID=" + roomID + ", Number=" + roomNumber + ", Type=" + roomType + ", Price=" + price + ", Available=" + isAvailable);
        
        // Clear fields after updating
        clearFields();
    }

    // Helper function to clear all input fields
    private void clearFields() {
        roomIDField.clear();
        roomNumberField.clear();
        roomTypeField.clear();
        priceField.clear();
        availableCheckBox.setSelected(false);
    }
}
