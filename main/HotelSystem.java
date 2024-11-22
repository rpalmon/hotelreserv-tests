package main;
import java.util.Scanner;

import main.Room.RoomType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HotelSystem {
    private GuestData guestData;
    private RoomData roomData;
    private ReservationData resData;
    private UserData userData;
    private Scanner sc;

    public HotelSystem(){
        guestData= new GuestData();
        roomData= new RoomData();
        resData=new ReservationData();
        userData=new UserData();
        sc= new Scanner(System.in);
    }
    
    public void start(){
        System.out.println("Hotel Reservation System is starting.");
        System.out.println("Enter username:");
        String userN=sc.nextLine();
        System.out.println("Enter password:");
        String userP=sc.nextLine();

        if (userData.validUser(userN, userP)) {
    
        boolean run=true;
        while(run){
            System.out.println("\nChoose option:");
            System.out.println("1 add");
            System.out.println("2 search");
            System.out.println("3 update");
            System.out.println("4 delete guest");
            System.out.println("5 quit");
            System.out.println("6. add room");
            System.out.println("7. del room");
            System.out.println("8. update room");
            System.out.println("9. find room details.");
            System.out.println("10. create reservation");
            System.out.println("11. del res");
            System.out.println("12. update res");
            System.out.println("13. find res");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    addGuest();
                    break;
                case 2:
                    searchGuest();
                    break;
                case 3:
                    updateGuest();
                    break;
                case 4:
                    delGuest();
                    break;
                case 5:
                    run=false;
                    System.out.println("stopping");
                    break;
                case 6:
                    addRoom();
                    break;
                case 7:
                    delRoom();
                    break;
                case 8:
                    updateRoom();
                    break;
                case 9:
                    findRoom();
                    break;
                case 10:
                    addRes();;
                    break;
                case 11:
                    delRes();
                    break;
                case 12:
                    updateRes();
                    break;
                case 13:
                    findRes();
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        } 
        } else {
            System.out.println("Invalid Username or Password.");
        }
    }

    private void addGuest(){
        System.out.println("guest\n name");
        String gName=sc.nextLine();
        System.out.println("email:");
        String gEmail=sc.nextLine();
        System.out.println("phone:");
        String gPhone=sc.nextLine();
        System.out.println("address:");
        String gAddress=sc.nextLine();

        Guest guest= new Guest(0, gName, gEmail, gPhone, gAddress);
        guestData.addGuest(guest);
    }

    private void searchGuest(){
        System.out.println("enter ID");
        int id=Integer.parseInt(sc.nextLine());
        Guest guest= guestData.getGuestID(id);
        if(guest!=null){
            System.out.println("Details:"+guest.getName());
        } else {
            System.out.println("not found.");
        }
    }

    private void updateGuest(){
        System.out.println("enter guest id to update.");
        int gID=Integer.parseInt(sc.nextLine());
        System.out.println("guest name");
        String gName=sc.nextLine();
        System.out.println("email:");
        String gEmail=sc.nextLine();
        System.out.println("phone:");
        String gPhone=sc.nextLine();
        System.out.println("address:");
        String gAddress=sc.nextLine();

        Guest guest= new Guest(gID, gName, gEmail, gPhone, gAddress);
        guestData.updateGuest(guest);
    }

    private void delGuest(){
        System.out.println("Enter Guest ID to delete.");
        int guestID=Integer.parseInt(sc.nextLine());
        guestData.delGuest(guestID);

    }

    public void addRoom(){
        System.out.println("room\n number:");
        String rNum=sc.nextLine();
        System.out.println("type:");
        Room.RoomType rType=Room.RoomType.valueOf("SINGLE");
        System.out.println("price:");
        double rPrice=Double.parseDouble(sc.nextLine());
        Boolean rAvail=true;
        //roomNum, roomType, price, avail
        Room room = new Room(0, rNum, rType, rPrice, rAvail);
        roomData.addRoom(room);
    }

    public void delRoom(){
        System.out.println("Enter Room ID to delete.");
        int roomID=Integer.parseInt(sc.nextLine());
        roomData.delRoom(roomID);
    }

    public void updateRoom(){
        System.out.println("room\n ID:");
        int rID=Integer.parseInt(sc.nextLine());
        System.out.println("number:");
        String rNum=sc.nextLine();
        System.out.println("type:");
        Room.RoomType rType=Room.RoomType.valueOf("SINGLE");
        System.out.println("price:");
        double rPrice=Double.parseDouble(sc.nextLine());
        System.out.println("available:");
        String bool = sc.nextLine().toLowerCase();
        if(bool==null||bool=="false"||bool=="no"||bool=="not available"){
            bool="false";
        } else {
            bool="true";
        }
        Boolean rAvail=Boolean.parseBoolean(bool);
        Room room = new Room(rID, rNum, rType, rPrice, rAvail);
        roomData.updateRoom(room);
    }

    public void findRoom(){
        System.out.println("enter room ID");
        int id=Integer.parseInt(sc.nextLine());
        Room room= roomData.getRoomID(id);
        if(room!=null){
            System.out.println("Details:"+room.toString());
        } else {
            System.out.println("Room not found.");
        }
    }

    

    public void addRes() {
        System.out.println("Reservation\n guestID:");
        int resGuestID = Integer.parseInt(sc.nextLine());
        
        System.out.println("roomID:");
        int resRoomID = Integer.parseInt(sc.nextLine());
        
        System.out.println("checkindate (YYYY-MM-DD):");
        LocalDate checkInDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        
        System.out.println("checkoutdate (YYYY-MM-DD):");
        LocalDate checkOutDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        
        System.out.println("Total cost:");
        double totalCost = Double.parseDouble(sc.nextLine());
        
        System.out.println("Reservation status (BOOKED, CHECKEDIN, CHECKEDOUT, CANCELLED):");
        Reservation.Status status = Reservation.Status.valueOf(sc.nextLine().toLowerCase()); 
        
        System.out.println("Payment status (true/false):");
        boolean paymentStatus = Boolean.parseBoolean(sc.nextLine()); 
        
        Reservation res = new Reservation(0,
            resGuestID, 
            resRoomID, 
            checkInDate, 
            checkOutDate, 
            totalCost, 
            status, 
            paymentStatus
        );
        
        resData.addRes(res);
        
        System.out.println("Reservation was successfully created.");
    }


    public void delRes(){
        System.out.println("Enter Rerservation ID to delete.");
        int resID=Integer.parseInt(sc.nextLine());
        resData.delRes(resID);
    }

    public void updateRes() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter Reservation ID: ");
        int resId = scanner.nextInt();
    
        System.out.print("Enter Guest ID: ");
        int guestId = scanner.nextInt();
    
        System.out.print("Enter Room ID: ");
        int roomId = scanner.nextInt();
    
        System.out.print("Enter Check-in Date (yyyy-mm-dd): ");
        LocalDate checkInDate = LocalDate.parse(scanner.next());
    
        System.out.print("Enter Check-out Date (yyyy-mm-dd): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.next());
    
        System.out.print("Enter Total Cost: ");
        double totalCost = scanner.nextDouble();
    
        System.out.print("Enter Status (booked, checked_in, checked_out, cancelled): ");
        String statusStr = scanner.next();
        Reservation.Status status = Reservation.Status.valueOf(statusStr.toLowerCase());
    
        System.out.print("Enter Payment Status (true/false): ");
        boolean payment = scanner.nextBoolean();
    
        // Create a valid Reservation object
        Reservation res = new Reservation(resId, guestId, roomId, checkInDate, checkOutDate, totalCost, status, payment);
    
        // Call the DAO method to update the reservation
        resData.updateRes(res);
        System.out.println("Reservation updated successfully.");
    }

    public void findRes(){
        System.out.println("enter reservation id");
        int resID=Integer.parseInt(sc.nextLine());
        Reservation res = resData.getRes(resID);
        if (res != null) {
            System.out.println("Reservation found: " + res.toString());
        } else {
            System.out.println("Reservation not found.");
        }
        
    }

    public void validUser(){

    }


    public static void main(String[] args) {
        HotelSystem sys = new HotelSystem();
        sys.start();
    }

    
}
