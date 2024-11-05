import java.util.Scanner;
public class HotelSystem {
    private GuestData guestData;
    private Scanner sc;

    public HotelSystem(){
        guestData=new GuestData();
        sc= new Scanner(System.in);
    }
    
    public void start(){
        System.out.println("start system.");
        boolean run=true;
        while(run){
            System.out.println("\nChoose option:");
            System.out.println("1 add");
            System.out.println("2 search");
            System.out.println("3 quit");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    addGuest();
                    break;
                case 2:
                    searchGuest();
                    break;
                case 3:
                    run=false;
                    System.out.println("stoppping");
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
        
    }
    private void addGuest(){
        sc.nextLine();
        System.out.println("gname");
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
        int id=sc.nextInt();
        Guest guest= guestData.getGuestID(id);
        if(guest!=null){
            System.out.println("Details:"+guest);
        } else {
            System.out.println("not found.");
        }
    }

    public static void main(String[] args) {
        HotelSystem sys = new HotelSystem();
        sys.start();
    }
}
