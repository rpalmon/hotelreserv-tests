package main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.Guest;

public class GuestData {

    public void addGuest(Guest guest) {
        String qry= "INSERT INTO Guest (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setString(1,guest.getName());
            prep.setString(2,guest.getEmail());
            prep.setString(3,guest.getPhone());
            prep.setString(4,guest.getAddress());
            if(prep.executeUpdate()>0){
                System.out.println("Guest has been added");
            } else {
                System.out.println("Fail to add guest.");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }
    
    public Guest getGuestID(int guestID){
        Guest guest=null;
        String qry= "SELECT * FROM Guest WHERE GuestID = ?";
        
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setInt(1, guestID);
            ResultSet result = prep.executeQuery();
            if(result.next()){
                guest = new Guest(
                    result.getInt("guestID"), 
                    result.getString("name"), 
                    result.getString("email"), 
                    result.getString("phone"), 
                    result.getString("address"));
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
        return guest;
    }

    public void updateGuest(Guest guest) {
        String qry= "UPDATE Guest SET name = ?, email = ?, phone = ?, address = ? WHERE guestID = ?";
        try(Connection connection = SqlConnector.getConnection();
        PreparedStatement prep=connection.prepareStatement(qry)){
            prep.setString(1,guest.getName());
            prep.setString(2,guest.getEmail());
            prep.setString(3,guest.getPhone());
            prep.setString(4,guest.getAddress());
            prep.setInt(5, guest.getGuestId());
            if(prep.executeUpdate()>0){
                System.out.println("Guest has been updated");
            } else {
                System.out.println("Fail to update guest.");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public List<Guest> getAllGuests(){
        List<Guest> guests = new ArrayList<>();
        String qry="SELECT * FROM Guest";
        try (Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry);
        ResultSet result = prep.executeQuery()
        ){
            while(result.next()){
                int id=result.getInt("GuestId");
                String name=result.getString("name");
                String email=result.getString("email");
                String phone=result.getString("phone");
                String address=result.getString("address");
                guests.add(new Guest(id, name, email, phone, address));
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong with guest list."+ e.getMessage());        
        }
        return guests;
    }

    public void delGuest(int guestID){
        String qry = "DELETE FROM Guest WHERE GuestID = ?";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setInt(1, guestID);
            if(prep.executeUpdate()>0){
                System.out.println("Guest has been deleted");
            } else {
                System.out.println("Fail to delete guest.");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public List<Guest> guestNameSearch(String gName){
        List<Guest> guests= new ArrayList<>();
        String qry="SELECT * FROM Guest WHERE name LIKE ?";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setString(1, "%"+gName+"%");
            ResultSet result=prep.executeQuery();
            while(result.next()){
                int id=result.getInt("GuestId");
                String name=result.getString("name");
                String email=result.getString("email");
                String phone=result.getString("phone");
                String address=result.getString("address");
                guests.add(new Guest(id, name, email, phone, address));
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
        return guests;
    }

    // crud methods
    //add, update, delete, read, guest list, find guest(id, name)
    //int guestId, String name, String email, String phone, String address

    // try(Connection connect = SqlConnector.getConnection();
    //     PreparedStatement prep=connect.prepareStatement()){
    //     } catch (SQLException e) {
    //         System.err.println("Something has gone wrong."+e.getMessage());
    //     }
}
