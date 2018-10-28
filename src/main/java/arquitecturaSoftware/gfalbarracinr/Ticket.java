package arquitecturaSoftware.gfalbarracinr;

import javax.persistence.*;

@Entity
public class Ticket {

    public Ticket(){ }

    public Ticket(String status, double price, String date, String lunchroomId, int userid, String name) {
        this.status = status;
        this.price = price;
        this.date = date;
        this.userid = userid;
        this.lunchroomId = lunchroomId;
        this.name = name;

    }

    public Ticket(int id, String status, double price, String date,
                  String lunchroomId, int userId) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.date = date;
        this.lunchroomId = lunchroomId;
        this.userid = userId;
    }

    public long getId() {
        return id;
    }

    public String getStatus(){
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getLunchroomId(){
        return lunchroomId;
    }
    public int getUserid(){
        return userid;
    }
    public String getName(){
        return name;
    }
    public void changeStatus (String newStatus){
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "EnableTicket{" +
                "id=" + id +
                ", name=" + name +
                ", status=" + status +
                ", price=" + price +
                ", date=" + date +
                '}';
    }

    @Id
    @Column(name="idticket", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lunchroomId;
    private int userid;
    private String status;
    private double price;
    private String date;
    private String name;


}
