package main;
import java.time.LocalDate;

public class Reservation {
    private int resID;
    private int guestID;
    private int roomID;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalCost;
    private Status status;
    private boolean payment;

    public enum Status {
        BOOKED, CHECKEDIN, CHECKEDOUT, CANCELLED
    }
    
    public Reservation(
        int resID, int guestID, int roomID, 
        LocalDate checkInDate, LocalDate checkOutDate, 
        double totalCost, Status status, boolean payment) {
        this.resID = resID;
        this.guestID = guestID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.status = status;
        this.payment = payment;
    }
    
    //getters
    public int getResId() {
        return resID;
    }
    public int getGuestId() {
        return guestID;
    }
    public int getRoomId() {
        return roomID;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public Status getStatus() {
        return status;
    }
    public boolean getPayment(){
        return payment;
    }
    
    //setters
    public void setResId(int resID) {
        this.resID = resID;
    }
    public void setGuestId(int guestID) {
        this.guestID = guestID;
    }
    public void setRoomId(int roomID) {
        this.roomID = roomID;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setPayment(boolean payment) {
        this.payment=payment;
    }

    public boolean isPaid(){
        return payment;
    }
    
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + resID +
                ", guestId=" + guestID +
                ", roomId=" + roomID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                '}';
    }
}