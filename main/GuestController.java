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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GuestController {
    //Guest table views
    @FXML
    private TableView<Guest> guestTableView;

    @FXML
    private TableColumn<Guest, Integer> idColumn;

    @FXML
    private TableColumn<Guest, String> nameColumn;

    @FXML
    private TableColumn<Guest, String> emailColumn;

    @FXML
    private TableColumn<Guest, String> phoneColumn;

    @FXML
    private TableColumn<Guest, String> addressColumn;

    @FXML
    private Button refresh;

    //buttons and text field for GUEST (top portion)
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
    
    //data acccess object creation
    private GuestData guestData;    
    //dao initiating
    public GuestController() {
        guestData = new GuestData();
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("guestID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        loadAllGuests();
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
    //method to search guest
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

    public void loadAllGuests() {
        try {
            ObservableList<Guest> allGuests = FXCollections.observableArrayList(guestData.getAllGuests());
            guestTableView.setItems(allGuests);

        } catch (Exception e) {
            showAlert("Error", "Failed to load guest data: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void refreshGuest(){
        loadAllGuests();
    }
    //alert system that pops up with a customer string message
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // method to clear the fields in the javafx box
    private void clearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
    }

    //method for the back button to go back to the dashboard view
    @FXML
    public void handleBackToDashboardButton() {
        loadScene("dashboard.fxml", "Dashboard");
    }

    //method to change scenes in java
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