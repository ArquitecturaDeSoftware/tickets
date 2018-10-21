package arquitecturaSoftware.gfalbarracinr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/tickets")
    public List<Ticket> index(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    @GetMapping("/ticket/user/{id}")
    public List<Ticket> getTicketByuser(@PathVariable String id){
        int idToFind = Integer.parseInt(id);
        return ticketRepository.findByUserid(idToFind);

    }

    @GetMapping("/ticket/lunchroom/{id}")
    public List<Ticket> getTicketsByRestaurant(@PathVariable String id){
        return ticketRepository.findAllByIdrestaurant(id);
    }
    @GetMapping("/ticket/{id}")
    public Ticket search(@PathVariable String id){
        int ticketId = Integer.parseInt(id);
        return ticketRepository.findOne(ticketId);
    }

    @GetMapping("/nextticket/{id}")
    public Ticket getNextTicket(@PathVariable String id){
        List<Ticket> tickets = ticketRepository
                .findByStatusAndIdrestaurant(
                        TicketStatus.WAITING.toString(), id);
        if (tickets.size() > 0){
            return tickets.get(0);
        } else{
            return NullTicket.getInstance();
        }
    }

    @PostMapping("/ticket")
    public Ticket create(@RequestBody Map<String, String> body){
        double price = Double.parseDouble(body.get("price"));
        String lunchroom = body.get("lunchroom");
        int userId = Integer.parseInt(body.get("user"));
        Ticket newTicket = new Ticket(TicketStatus.WAITING.toString(), price,
                new Date(), lunchroom, userId);
        return ticketRepository.save(newTicket);

    }

    @PutMapping("/ticket")
    public Ticket changeStatus(@RequestBody Map<String, String> body){
        int id = Integer.parseInt(body.get("id"));
        Ticket ticket = ticketRepository.findOne(id);
        String status = TicketStatus.getStatus(body.get("status"));
        ticket.changeStatus(status);
        return ticketRepository.save(ticket);
    }

    @DeleteMapping("/ticket/{id}")
    public boolean deleteTicket(@PathVariable String id ){
        int ticketId = Integer.parseInt(id);
        ticketRepository.delete(ticketId);
        return true;
    }



}
