package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Room;
import main.RoomData;
import main.Room.RoomType;

public class RoomController {

    @FXML
    private TextField roomIDField;

    @FXML
    private TextField roomNumField;

    @FXML
    private ComboBox<Room.RoomType> roomTypeField;

    @FXML
    private TextField priceField;

    @FXML
    private CheckBox availCheckBox;
    // @FXML
    // private TextField availField;

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
    
    private RoomData roomData;

    public RoomController() {
        roomData = new RoomData();
    }

    // Initialize ComboBox with room types
    @FXML
    public void initialize() {
        roomTypeField.getItems().setAll(Room.RoomType.values());
    }
    
    // Method to add a guest
    @FXML
    public void addRoom() {
        // check if values are null returns error message if any are null
        if (roomIDField.getText().isEmpty() || 
            roomNumField.getText().isEmpty() || 
            roomTypeField.getValue() == null || 
            priceField.getText().isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }
        String roomNum = roomNumField.getText();
        Room.RoomType roomType = roomTypeField.getValue();
        double price = Double.parseDouble(priceField.getText());
        boolean avail = availCheckBox.isSelected();

        Room room = new Room(0, roomNum, roomType, price, avail);
        
        try {
            roomData.addRoom(room);
            showAlert("Success", "Room was added.", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            System.err.println("Failed to add room. " + e.getMessage());
            showAlert("Error", "Failed to add room.", Alert.AlertType.ERROR);
        }
    }

    // Method to update a guest
    @FXML
    public void updateRoom() {
        // check if values are null returns error message if any are null
        if (roomIDField.getText().isEmpty() || 
            roomNumField.getText().isEmpty() || 
            roomTypeField.getValue() == null || 
            priceField.getText().isEmpty()) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }
        
        int id = Integer.parseInt(roomIDField.getText());
        String roomNum = roomNumField.getText();
        Room.RoomType roomType = roomTypeField.getValue();
        double price = Double.parseDouble(priceField.getText());
        boolean avail = availCheckBox.isSelected();

        Room room = new Room(id, roomNum, roomType, price, avail);

        try {
            roomData.updateRoom(room);
            showAlert("Success", "Room was updated.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to update room. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Method to delete a guest
    @FXML
    public void deleteRoom() {
        try {
            int id = Integer.parseInt(roomIDField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid guest ID.", Alert.AlertType.ERROR);
                return;
            } else {

            roomData.delRoom(id);
            showAlert("Success", "Guest deleted successfully!", Alert.AlertType.INFORMATION);
            clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid guest ID.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Failed to delete guest. " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void searchRoom() {
        try {
            int id = Integer.parseInt(roomIDField.getText());

            if (id <= 0) {
                showAlert("Error", "Invalid guest ID.", Alert.AlertType.ERROR);
                return;
            }
            
            Room room = roomData.getRoomID(id);
            if (room != null) {
                roomNumField.setText(room.getRoomNum());
                roomTypeField.setValue(room.getRoomType());
                priceField.setText(String.valueOf(room.getPrice()));
                availCheckBox.setSelected(room.isAvail());
            } else {
                showAlert("Info", "Room not found.", Alert.AlertType.INFORMATION);
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Room ID is not valid.", Alert.AlertType.ERROR);
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
        roomIDField.clear();
        roomNumField.clear();
        roomTypeField.setValue(null);
        priceField.clear();
        availCheckBox.setSelected(false);
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