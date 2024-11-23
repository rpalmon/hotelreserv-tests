package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import java.time.LocalDate;

public class ResController {
    //rescontroller id buttons in the fxml files
    @FXML
    private TextField resIDField;

    @FXML
    private TextField guestIDField;

    @FXML
    private TextField roomIDField;

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private TextField totalCostField;

    @FXML
    private ChoiceBox<Reservation.Status> statusChoiceBox;

    @FXML
    private CheckBox paymentCheckbox;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button backToDashboardButton;

    //reservation data access object are created here
    private ReservationData reservationData;
    //res dao is initiated
    public ResController() {
        reservationData = new ReservationData();
    }
    //the choice box dynamically obtain it from resesrvation.class and gets put into the combobox
    @FXML
    public void initialize() {
        statusChoiceBox.getItems().setAll(Reservation.Status.values());
    }

    //add a reservation is called fromt he res dao
    @FXML
    public void addReservation() {
        //error check to make sure all fields are filled in
        if (resIDField.getText().isEmpty() || 
            guestIDField.getText().isEmpty() || 
            roomIDField.getText().isEmpty() || 
            totalCostField.getText().isEmpty() || 
            checkInDatePicker.getValue() == null || 
            checkOutDatePicker.getValue() == null || 
            statusChoiceBox.getValue() == null) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }
        //fields are put passed into variables
        int guestID = Integer.parseInt(guestIDField.getText());
        int roomID = Integer.parseInt(roomIDField.getText());
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        double totalCost = Double.parseDouble(totalCostField.getText());
        Reservation.Status status = statusChoiceBox.getValue();
        boolean payment = paymentCheckbox.isSelected();
        //variables are then used to create a new reservation
        Reservation reservation = new Reservation(0, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment);

        //reservation object is now apssed into the res dao to be submitted into the database
        try {
            reservationData.addRes(reservation);
            showAlert("Success", "Reservation added successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            System.err.println("Failed to add reservation. " + e.getMessage());
            showAlert("Error", "Failed to add reservation.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void updateReservation() {

        //field checks to make sure it is not null
        //if null pop up happenms
        if (resIDField.getText().isEmpty() || 
            guestIDField.getText().isEmpty() || 
            roomIDField.getText().isEmpty() || 
            totalCostField.getText().isEmpty() || 
            checkInDatePicker.getValue() == null || 
            checkOutDatePicker.getValue() == null || 
            statusChoiceBox.getValue() == null) {
            showAlert("Warning", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        //values gets passed into the resrevation.java and an object is created
        int resID = Integer.parseInt(resIDField.getText());
        int guestID = Integer.parseInt(guestIDField.getText());
        int roomID = Integer.parseInt(roomIDField.getText());
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        double totalCost = Double.parseDouble(totalCostField.getText());
        Reservation.Status status = statusChoiceBox.getValue();
        boolean payment = paymentCheckbox.isSelected();

        Reservation reservation = new Reservation(resID, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment);
        //the reservation object is now passed into the res DAO for updates
        try {
            reservationData.updateRes(reservation);
            showAlert("Success", "Reservation updated successfully!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            System.err.println("Failed to update reservation. " + e.getMessage());
            showAlert("Error", "Failed to update reservation.", Alert.AlertType.ERROR);
        }
    }

    //deletes a reservation
    @FXML
    public void deleteReservation() {
        //the resrevation needs an ID to delete so this checks for it
        if (resIDField.getText().isEmpty()) {
            showAlert("Error", "Invalid reservation ID.", Alert.AlertType.ERROR);
            return;
        }
        
        //id is pushed into the resvetaion DAO for it to get queried and deleted fromt he database
        int resID = Integer.parseInt(resIDField.getText());
        try {
            reservationData.delRes(resID);
            showAlert("Success", "Reservation deleted successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        } catch (Exception e) {
            System.err.println("Failed to delete reservation. " + e.getMessage());
            showAlert("Error", "Failed to delete reservation.", Alert.AlertType.ERROR);
        }
    }


    //id is needed to be search for the resrevation specific details
    @FXML
    public void searchReservation() {
        //checks if id is present
        if (resIDField.getText().isEmpty()) {
            showAlert("Error", "Invalid reservation ID.", Alert.AlertType.ERROR);
            return;
        }
        int resID = Integer.parseInt(resIDField.getText());
        //a new reservation is created using the getter in the DAO and is placed into the reservation.class
        Reservation reservation = reservationData.getRes(resID);

        //if it isnt null then it returns the data from that using the resrevation class getters and present it to the view
        if (reservation != null) {
            guestIDField.setText(String.valueOf(reservation.getGuestId()));
            roomIDField.setText(String.valueOf(reservation.getRoomId()));
            checkInDatePicker.setValue(reservation.getCheckInDate());
            checkOutDatePicker.setValue(reservation.getCheckOutDate());
            totalCostField.setText(String.valueOf(reservation.getTotalCost()));
            statusChoiceBox.setValue(reservation.getStatus());
            paymentCheckbox.setText(String.valueOf(reservation.getPayment()));
        } else {
            showAlert("Error", "Reservation not found.", Alert.AlertType.ERROR);
        }
    }
    //alert method to show a pop up box
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //clears the entry box after every method above
    private void clearFields() {
        resIDField.clear();
        guestIDField.clear();
        roomIDField.clear();
        checkInDatePicker.setValue(null);
        checkOutDatePicker.setValue(null);
        totalCostField.clear();
        statusChoiceBox.setValue(null);
        paymentCheckbox.setSelected(false);;
    }
    //provides the back button to dashboard
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
