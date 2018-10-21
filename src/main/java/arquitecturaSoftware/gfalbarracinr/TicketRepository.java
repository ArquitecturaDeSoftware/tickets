package arquitecturaSoftware.gfalbarracinr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TicketRepository extends
        JpaRepository<Ticket, Integer> {

    List<Ticket> findByStatusAndLunchroomId(String text, String lunchroomId);
    List<Ticket> findByUserid(int userId);
    List<Ticket> findAllByLunchroomId(String lunchroomId);
}
