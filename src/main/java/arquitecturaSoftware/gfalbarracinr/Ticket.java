package arquitecturaSoftware.gfalbarracinr;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Ticket {

    public Ticket(){ }

    public Ticket(String status, double price, Date date, int restaurant, int userid) {
        this.status = status;
        this.price = price;
        this.date = date;
        this.userid = userid;
        this.idrestaurant = restaurant;
    }

    public Ticket(int id, String status, double price, Date date,
                  int restaurant, int userId) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.date = date;
        this.idrestaurant = restaurant;
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

    public Date getDate() {
        return date;
    }

    public int getIdrestaurant(){
        return idrestaurant;
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
                ", date=" + date.toString() +
                '}';
    }

    @Id
    @Column(name="idticket", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int idrestaurant;
    private int userid;
    private String status;
    private double price;
    private Date date;


}
