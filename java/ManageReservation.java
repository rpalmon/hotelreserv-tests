import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class ManageReservation {
    public static void main(String[] args) {
        File f = new File("Reservation-NV.txt");
        
        if(f.exists()){
            try{
                FileWriter writer= new FileWriter("Reservation-NV.txt");
                // display(); all txt file
                // display information on ID 
                //id.change.date
                //id.change.room
                //id.delete.reservation
                writer.close();
                
            } catch(IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        } else{
        try {
            // Create a FileWriter object
            FileWriter writer= new FileWriter("Reservation-NV.txt");
            // Write data to the file
            writer.write("#RID:000001\n");
            writer.write("##Date:");
            writer.write("##Room:");
            writer.write("##PID:");
            // Close the FileWriter
            writer.close();
            
            System.out.println("Data has been written to the file.");
            } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}

}