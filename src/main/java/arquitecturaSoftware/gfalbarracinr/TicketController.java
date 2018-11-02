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
        List<Ticket> ticketsCalling = ticketRepository
                .findByStatusAndLunchroomId(
                        TicketStatus.CALLING.toString(), lunchroomId);
        List<Ticket> ticketsWaiting = ticketRepository
                .findByStatusAndLunchroomId(
                        TicketStatus.WAITING.toString(), lunchroomId);
        
        if (ticketsCalling.size() > 0){
            return ticketsCalling.get(0);
        } else if (ticketsWaiting.size() > 0){
            return ticketsWaiting.get(0);
        } else{
            return NullTicket.getInstance();
        }
    }

    @GetMapping("/tickets/before/{id}")
    public List<Ticket> getTicketsBeforeMe(@PathVariable String id){
        int ticketId = Integer.parseInt(id);
        Ticket ticket = ticketRepository.findOne(ticketId);
        List<Ticket> ticketsBeforeMe = ticketRepository.findTicketsBeforeMeByStatusAndLunchroomId(
                        ticketId, TicketStatus.WAITING.toString(), ticket.getLunchroomId());
       
        if(ticketsBeforeMe != null && !ticketsBeforeMe.isEmpty())
            return ticketsBeforeMe;
        else
            return ticketRepository.findByStatusAndLunchroomId(TicketStatus.WAITING.toString(), ticket.getLunchroomId());


    }
    @PostMapping("/tickets")
    public Ticket create(@RequestBody Map<String, String> body){
        double price = Double.parseDouble(body.get("price"));
        String lunchroomId = body.get("lunchroomId");
        int userId = Integer.parseInt(body.get("userId"));
        String name = body.get("name");
        Ticket newTicket = new Ticket(TicketStatus.WAITING.toString(), price,
                new Date().toString(), lunchroomId, userId, name);
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
