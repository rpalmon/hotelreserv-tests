package main;
public class Room {
    private int roomID;
    private String roomNum;
    private RoomType roomType;
    private double price;
    private boolean avail; 
    //room availabiltiy

    public enum RoomType {
        SINGLE, DOUBLE, SUITE
    }
    //constructor
    public Room(int roomID, String roomNum, RoomType roomType, double price, boolean avail) {
        this.roomID = roomID;
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.price = price;
        this.avail = avail;
    }

    //getters 
    public int getRoomId() {
        return roomID;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean getAvail(){
        return avail;
    }
    //setters
    public void setRoomId(int roomID) {
        this.roomID = roomID;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public boolean isAvail() {
        return avail;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomID +
                ", roomNumber='" + roomNum + '\'' +
                ", roomType='" + roomType + '\'' +
                ", price=" + price +
                ", availability=" + avail +
                '}';
    }
}
