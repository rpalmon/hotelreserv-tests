package main;

public class Guest {
    private int guestID;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    //constructor
    public Guest(int guestID, String name, String email, String phone, String address) {
        this.guestID = guestID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    //getters
    public int getGuestID() { 
        return guestID; 
    }

    public String getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getAddress(){
        return this.address;
    }

    //setters
    public void setGuestID(int guestID) { 
        this.guestID = guestID; 
    }
    public void setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setAddr(String addr){
        this.address=addr;
    }
}