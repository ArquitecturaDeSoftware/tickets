package arquitecturaSoftware.gfalbarracinr;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Ticket {

    public Ticket(){ }

    public Ticket(String status, double price, String date, String lunchroomId, int userid) {
        this.status = status;
        this.price = price;
        this.date = date;
        this.userid = userid;
        this.lunchroomId = lunchroomId;
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
    public void changeStatus (String newStatus){
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "EnableTicket{" +
                "id=" + id +
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


}
