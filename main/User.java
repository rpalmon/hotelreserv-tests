package main;

public class User {
    private int userID;
    private String userName;
    private String userPass;
    private String userEmail;
    private String userPhone;

    //constructor
    public User(int userID, String userName, String userPass, String userEmail, String userPhone){
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    //setters
    public void setUserName(String name){
        this.userName = name;
    }

    public void setUsePass(String pass){
        this.userPass = pass;
    }

    public void setUseEmail(String email){
        this.userEmail = email;
    }

    public void setUsePhone(String phone){
        this.userPhone = phone;
    }

    //getters
    public int getUserID(){
        return this.userID;
    }

    public String getUserName(){
        return this.userName;
    }
    public String getUserPass(){
        return this.userPass;
    }
    public String getUserEmail(){
        return this.userEmail;
    }
    public String getUserPhone(){
        return this.userPhone;
    }

}
