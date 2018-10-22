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

    @GetMapping("/tickets/user/{id}")
    public List<Ticket> getTicketByUser(@PathVariable String id){
        int idToFind = Integer.parseInt(id);
        return ticketRepository.findByUserid(idToFind);

    }

    @GetMapping("/tickets/lunchroom/{id}")
    public List<Ticket> getTicketsByLunchroom(@PathVariable String id){
        String lunchroomId = id;
        return ticketRepository.findAllByLunchroomId(lunchroomId);
    }
    @GetMapping("/tickets/{id}")
    public Ticket search(@PathVariable String id){
        int ticketId = Integer.parseInt(id);
        return ticketRepository.findOne(ticketId);
    }

    @GetMapping("/nextticket/{id}")
    public Ticket getNextTicket(@PathVariable String id){
        String lunchroomId = id;
        List<Ticket> tickets = ticketRepository
                .findByStatusAndLunchroomId(
                        TicketStatus.WAITING.toString(), lunchroomId);
        if (tickets.size() > 0){
            return tickets.get(0);
        } else{
            return NullTicket.getInstance();
        }
    }

    @PostMapping("/tickets")
    public Ticket create(@RequestBody Map<String, String> body){
        double price = Double.parseDouble(body.get("price"));
        String lunchroomId = body.get("lunchroomId");
        int userId = Integer.parseInt(body.get("userId"));
        Ticket newTicket = new Ticket(TicketStatus.WAITING.toString(), price,
                new Date().toString(), lunchroomId, userId);
        return ticketRepository.save(newTicket);

    }

    @PutMapping("/tickets/{id}")
    public Ticket changeStatus(@PathVariable String id, @RequestBody Map<String, String> body){
        int idTicket = Integer.parseInt(id);
        Ticket ticket = ticketRepository.findOne(idTicket);
        String status = TicketStatus.getStatus(body.get("status"));
        ticket.changeStatus(status);
        return ticketRepository.save(ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public boolean deleteTicket(@PathVariable String id ){
        int ticketId = Integer.parseInt(id);
        ticketRepository.delete(ticketId);
        return true;
    }



}
