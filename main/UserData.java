package main;

import java.sql.*;

public class UserData {
    public boolean validUser(String username, String pass) {
        String qry= "SELECT * FROM User WHERE userName = ? AND userPass = ?";
        
        try (Connection connect = SqlConnector.getConnection();
        PreparedStatement prep=connect.prepareStatement(qry)) {
             
            prep.setString(1, username);
            prep.setString(2, pass);
            
            ResultSet result = prep.executeQuery();

            return result.next(); // If a result is returned, the credentials are valid.
            
        } catch (SQLException e) {
            System.err.println("An error occurred while validating user: " + e.getMessage());
            return false;
        }
    }
}