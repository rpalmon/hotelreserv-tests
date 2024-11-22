package main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//roomId, String roomNum, String roomType, double price, boolean availability
public class RoomData {
    
    public void addRoom(Room room){
        String qry="INSERT INTO Room (roomNum, roomType, price, avail) VALUES (?, ?, ?, ?)";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setString(1,room.getRoomNum());
            prep.setString(2,room.getRoomType().name());
            prep.setDouble(3,room.getPrice());
            prep.setBoolean(4,room.getAvail());
            if(prep.executeUpdate()>0){
                System.out.println("Room has been added");
            } else {
                System.out.println("Fail to add Room.");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public void delRoom(int roomID){
        String qry="DELETE FROM Room WHERE roomID = ?";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            prep.setInt(1, roomID);
            if(prep.executeUpdate()>0){
                System.out.println("Room has been deleted");
            } else {
                System.out.println("Fail to delete room.");
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public void updateRoom(Room room){
        String qry="UPDATE Room SET roomNum=?, roomType=?, price=?, avail=? WHERE roomID=?";
        try(Connection connect = SqlConnector.getConnection();
            PreparedStatement prep=connect.prepareStatement(qry)){
                prep.setString(1,room.getRoomNum());
                prep.setString(2,room.getRoomType().name());
                prep.setDouble(3,room.getPrice());
                prep.setBoolean(4,room.getAvail());
                prep.setInt(5, room.getRoomId());
                if(prep.executeUpdate()>0){
                    System.out.println("Room has been updated.");
                } else {
                    System.out.println("Fail to update room.");
                }
            } catch (SQLException e) {
                System.err.println("Something has gone wrong."+e.getMessage());
        }
    }

    public Room getRoomID(int roomID){
        Room room=null;
        String qry="SELECT * FROM Room WHERE roomID = ?";
        try(Connection connect = SqlConnector.getConnection();
            PreparedStatement prep=connect.prepareStatement(qry)){
                prep.setInt(1, roomID);
                ResultSet result = prep.executeQuery();
                if(result.next()){
                    room = new Room(
                        result.getInt("roomID"), 
                        result.getString("roomNum"), 
                        Room.RoomType.valueOf(result.getString("roomType")), 
                        result.getDouble("price"), 
                        result.getBoolean("avail"));
                }
            } catch (SQLException e) {
                System.err.println("Something has gone wrong."+e.getMessage());
        }
        return room;
    }

    public List<Room> roomNumSearch(String roomNum){
        List<Room> rooms= new ArrayList<>();
        String qry="SELECT * FROM Room WHERE roomNum LIKE ?";
        try(Connection connect = SqlConnector.getConnection();
            PreparedStatement prep=connect.prepareStatement(qry)){
                prep.setString(1, roomNum);
                ResultSet result = prep.executeQuery();
                while(result.next()){
                    int ID = result.getInt("roomID"); 
                    String num = result.getString("roomNum");
                    Room.RoomType type = Room.RoomType.valueOf(result.getString("roomType").toUpperCase());
                    double price = result.getDouble("price"); 
                    boolean avail = result.getBoolean("avail");
                    rooms.add(new Room(ID,num,type,price,avail));
                }
        } catch (SQLException e) {
                System.err.println("Something has gone wrong."+e.getMessage());
        }
        return rooms;
    }

    public List<Room> listRooms(){
        List<Room> rooms = new ArrayList<>();
        String qry="SELECT * FROM Room";
        try(Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)){
            ResultSet result=prep.executeQuery();
            while(result.next()){
                    int ID = result.getInt("roomID"); 
                    String num = result.getString("roomNum");
                    Room.RoomType type = Room.RoomType.valueOf(result.getString("roomType").toUpperCase());
                    double price = result.getDouble("price"); 
                    boolean avail = result.getBoolean("avail");
                    rooms.add(new Room(ID,num,type,price,avail));
            }
        } catch (SQLException e) {
            System.err.println("Something has gone wrong."+e.getMessage());
        }
        return rooms;
    }

    public List<Room> listAvailRoom(){
        List<Room> rooms = new ArrayList<>();
        String qry="SELECT * FROM Room WHERE avail = TRUE";
        try(Connection connect = SqlConnector.getConnection();
            PreparedStatement prep=connect.prepareStatement(qry)){
                ResultSet result = prep.executeQuery();
                while(result.next()){
                    int ID = result.getInt("roomID"); 
                    String num = result.getString("roomNum");
                    Room.RoomType type = Room.RoomType.valueOf(result.getString("roomType").toUpperCase());
                    double price = result.getDouble("price"); 
                    boolean avail = result.getBoolean("avail");
                    rooms.add(new Room(ID,num,type,price,avail));
                }
        } catch (SQLException e) {
                System.err.println("Something has gone wrong."+e.getMessage());
        }
        return rooms;
    }

    public void updatePrice(int id, double price){
        String qry="UPDATE Room SET price = ? WHERE roomID = ?";
        try(Connection connect = SqlConnector.getConnection();
            PreparedStatement prep=connect.prepareStatement(qry)){
                prep.setDouble(1, price);
                prep.setInt(2,id);
                if(prep.executeUpdate()>0){
                    System.out.println("Price has been updated to "+price+".");
                } else {
                    System.out.println("Failed to change price.");
                }
            } catch (SQLException e) {
                System.err.println("Something has gone wrong."+e.getMessage());
        }
    }


    public List<Room> listAvailableRoomsByType(String roomType) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE avail = TRUE AND roomType = ?";
        try (Connection connect = SqlConnector.getConnection();
            PreparedStatement prep = connect.prepareStatement(sql)){
                prep.setString(1, roomType);
                
                ResultSet result = prep.executeQuery();
                while (result.next()) {
                Room.RoomType type = Room.RoomType.valueOf(result.getString("roomType").toUpperCase());
                rooms.add(new Room(
                    result.getInt("roomID"),
                    result.getString("roomNum"),
                    type,
                    result.getDouble("price"),
                    result.getBoolean("avail")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Somethign went wrong" + e.getMessage());
        }
        return rooms;
    }
    
    //list rooms
    //list avail rooms
    // crud methods
    //add, update, delete, read, guest list, find guest(id, name)
    //int guestId, String name, String email, String phone, String address
    // String qry="";
    // try(Connection connect = SqlConnector.getConnection();
    //     PreparedStatement prep=connect.prepareStatement(qry)){
    //     } catch (SQLException e) {
    //         System.err.println("Something has gone wrong."+e.getMessage());
    // }
}
