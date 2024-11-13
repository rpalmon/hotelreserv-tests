package main;

public class Guest {
    private int guestId;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    //constructor
    public Guest(int guestId, String name, String email, String phone, String address) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    //getters
    public int getGuestId() { 
        return guestId; 
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
    public void setGuestId(int guestId) { 
        this.guestId = guestId; 
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