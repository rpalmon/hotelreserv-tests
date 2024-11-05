import java.time.LocalDate;

public class Reservation {
    private int resId;
    private int guestId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalCost;
    private Status status;
    private boolean payment;

    public enum Status {
        booked, checked_in, checked_out, canceled
    }
    
    public Reservation(
        int resId, int guestId, int roomId, 
        LocalDate checkInDate, LocalDate checkOutDate, 
        double totalCost, Status status, boolean payment) {
        this.resId = resId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.status = status;
        this.payment = payment;
    }
    
    //getters
    public int getResId() {
        return resId;
    }
    public int getGuestId() {
        return guestId;
    }
    public int getRoomId() {
        return roomId;
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
    public void setResId(int resId) {
        this.resId = resId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
                "reservationId=" + resId +
                ", guestId=" + guestId +
                ", roomId=" + roomId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                '}';
    }
}