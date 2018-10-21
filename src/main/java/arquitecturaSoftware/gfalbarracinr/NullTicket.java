package arquitecturaSoftware.gfalbarracinr;

import java.util.Date;

public class NullTicket extends Ticket  {

    private NullTicket (){
        super((int) -1, TicketStatus.ERROR.toString(), 0, new Date().toString(), "-1",-1);
    }

    public static NullTicket getInstance(){
        return instance;
    }
    public boolean isNull() {
        return true;
    }

    public static final NullTicket instance = new NullTicket();
}
