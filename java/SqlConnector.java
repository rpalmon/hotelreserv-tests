import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnector {
    
    private static final String URL="jdbc:mysql://localhost:3306/test_db";
    private static final String USER="admin";
    private static final String PASSWORD="password";

    private static Connection connection = null;
    
    public static Connection getConnection() throws SQLException{
        if(connection==null||connection.isClosed()){
            try{
                connection=DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("You are connected.");
            } catch (SQLException e){
                System.err.println("Something has gone wrong"+e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection!=null){
            try {
                connection.close();
                System.out.println("connection is closed");
            } catch (Exception e) {
                System.err.println("something has gone wrong"+ e.getMessage());
            }
        }
    }
}