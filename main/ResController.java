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

    private ReservationData reservationData;

    public ResController() {
        reservationData = new ReservationData();
    }

    @FXML
    public void initialize() {
        statusChoiceBox.getItems().setAll(Reservation.Status.values());
    }
    @FXML
    public void addReservation() {
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

        int guestID = Integer.parseInt(guestIDField.getText());
        int roomID = Integer.parseInt(roomIDField.getText());
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        double totalCost = Double.parseDouble(totalCostField.getText());
        Reservation.Status status = statusChoiceBox.getValue();
        boolean payment = paymentCheckbox.isSelected();

        Reservation reservation = new Reservation(0, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment);

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
        int resID = Integer.parseInt(resIDField.getText());
        int guestID = Integer.parseInt(guestIDField.getText());
        int roomID = Integer.parseInt(roomIDField.getText());
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        double totalCost = Double.parseDouble(totalCostField.getText());
        Reservation.Status status = statusChoiceBox.getValue();
        boolean payment = paymentCheckbox.isSelected();

        Reservation reservation = new Reservation(resID, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment);

        try {
            reservationData.updateRes(reservation);
            showAlert("Success", "Reservation updated successfully!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            System.err.println("Failed to update reservation. " + e.getMessage());
            showAlert("Error", "Failed to update reservation.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void deleteReservation() {
        if (resIDField.getText().isEmpty()) {
            showAlert("Error", "Invalid reservation ID.", Alert.AlertType.ERROR);
            return;
        }
        
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

    @FXML
    public void searchReservation() {
        if (resIDField.getText().isEmpty()) {
            showAlert("Error", "Invalid reservation ID.", Alert.AlertType.ERROR);
            return;
        }
        int resID = Integer.parseInt(resIDField.getText());

        Reservation reservation = reservationData.getRes(resID);

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

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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
