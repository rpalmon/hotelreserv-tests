package main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/*
 * resID
guestID     
roomID   
checkInDate 
checkOutDate 
totalCost   
status      
payment 
 */
public class ReservationData {
    public void addRes(Reservation res){
        String qry= "INSERT INTO Reservation (guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setInt(1,res.getGuestId());
            prep.setInt(2,res.getRoomId());
            prep.setDate(3,java.sql.Date.valueOf(res.getCheckInDate()));
            prep.setDate(4,java.sql.Date.valueOf(res.getCheckOutDate()));
            prep.setDouble(5,res.getTotalCost());
            prep.setString(6,res.getStatus().name());
            prep.setBoolean(7,res.getPayment());

            if(prep.executeUpdate()>0){
                System.out.println("Reservation has been created");
            } else {
                System.out.println("Fail to create reservation");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public void delRes(int resID){
        String qry = "DELETE FROM Reservation WHERE resID = ?";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setInt(1, resID);
            if(prep.executeUpdate()>0){
                System.out.println("Reservation has been deleted");
            } else {
                System.out.println("Fail to delete reservation.");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public void updateRes(Reservation res){
        String qry= "UPDATE Reservation SET guestID=?, roomID=?, checkInDate=?, checkOutDate=?, totalCost=?, status=?, payment=? WHERE resID=?";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setInt(1,res.getGuestId());
            prep.setInt(2,res.getRoomId());
            prep.setDate(3,java.sql.Date.valueOf(res.getCheckInDate()));
            prep.setDate(4,java.sql.Date.valueOf(res.getCheckOutDate()));
            prep.setDouble(5,res.getTotalCost());
            prep.setString(6,res.getStatus().name());
            prep.setBoolean(7,res.getPayment());
            prep.setInt(8, res.getResId());

            if(prep.executeUpdate()>0){
                System.out.println("Reservation has been created");
            } else {
                System.out.println("Fail to create reservation");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public Reservation getRes(int resID){
        Reservation reservation=null;
        String qry="SELECT * FROM Reservation WHERE resID = ?";
        try(Connection connect = SqlConnector.getConnection();
            PreparedStatement prep=connect.prepareStatement(qry)){
                prep.setInt(1, resID);
                ResultSet result = prep.executeQuery();
                if(result.next()){
                    reservation = new Reservation(
                        result.getInt("resID"), 
                        result.getInt("guestID"), 
                        result.getInt("roomID"), 
                        result.getDate("checkInDate").toLocalDate(), 
                        result.getDate("checkOutDate").toLocalDate(),
                        result.getDouble("totalCost"), 
                        Reservation.Status.valueOf(result.getString("status")), 
                        result.getBoolean("payment")
                        );
                }
            } catch (SQLException e) {
                System.err.println("Something has gone wrong."+e.getMessage());
        }
        return reservation;
    }

    public List<Reservation> listAllRes() {
        List<Reservation> res = new ArrayList<>();
        String qry = "SELECT * FROM Reservation";
        
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            
            ResultSet result = prep.executeQuery();
            
            while (result.next()) {
                int resID = result.getInt("resID");
                int guestID = result.getInt("guestID");
                int roomID = result.getInt("roomID");
                LocalDate checkInDate = result.getDate("checkInDate").toLocalDate(); // Convert Date to LocalDate
                LocalDate checkOutDate = result.getDate("checkOutDate").toLocalDate(); // Convert Date to LocalDate
                double totalCost = result.getDouble("totalCost");
                Reservation.Status status = Reservation.Status.valueOf(result.getString("status"));
                boolean payment = result.getBoolean("payment");

                // Add the new Reservation object to the list
                res.add(new Reservation(resID, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment));
            }
            
        } catch (SQLException e) {
            System.err.println("Something has gone wrong. " + e.getMessage());
        }
        return res;
    }

}
