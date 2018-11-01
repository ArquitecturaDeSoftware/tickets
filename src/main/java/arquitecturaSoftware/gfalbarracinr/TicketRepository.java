package arquitecturaSoftware.gfalbarracinr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TicketRepository extends
        JpaRepository<Ticket, Integer> {

    List<Ticket> findByStatusAndLunchroomId(String text, String lunchroomId);
    
    List<Ticket> findByUserid(int userId);
    
    List<Ticket> findAllByLunchroomId(String lunchroomId);
    
    @Query("Select t from Ticket t where t.id < ?1 and t.status = ?2 and t.lunchroomId = ?3")
    List<Ticket> findTicketsBeforeMeByStatusAndLunchroomId(int ticketId , String Status ,String lunchroomId);
}
