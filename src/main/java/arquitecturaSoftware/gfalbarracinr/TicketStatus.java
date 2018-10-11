package arquitecturaSoftware.gfalbarracinr;

public enum TicketStatus {
    CALLING(1),
    WAITING(2),
    FINISHED(3),
    ERROR(4);

  TicketStatus(int id){
    this.id = id;
  }

    public static String getStatus(String status) {
        switch (status){
            case "CALLING":
                return TicketStatus.CALLING.toString();
            case "WAITING":
                return TicketStatus.WAITING.toString();
            case "FINISHED":
                return TicketStatus.FINISHED.toString();
            default:
                return TicketStatus.ERROR.toString();
        }

    }

    @Override
    public String toString() {
        return this.name();
    }

    private final int id;
}
