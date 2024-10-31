public class Reservation {
    String name;
    int days;
    int startDate;
    int personID;
    public Reservation(String name, int days, int startDate, int personID){
        this.name=name;
        this.days=days;
        this.startDate=startDate;
        this.personID=personID;
    }

    void setDate(int date){
        this.startDate=date;
    }
    void setName(String name){
        this.name=name;
    }

    void setID(int personID){
        this.personID=personID;
    }

    public String getName() {
        return name;
    }
    public int getDays() {
        return days;
    }
    public int getPersonID() {
        return personID;
    }
    public int getStartDate() {
        return startDate;
    }

    void getInfo(){
        System.out.println(getPersonID());
        System.out.println(getName());
        System.out.println(getStartDate());
        System.out.println(getDays());
    }
}