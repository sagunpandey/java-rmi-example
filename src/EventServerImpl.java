import java.rmi.RemoteException;
import java.util.List;

/**
 * Created By Sagun Pandey
 */
public class EventServerImpl implements EventServer {

    private HistoryProvider history;

    public EventServerImpl() {
        history = new HistoryProvider();
    }

    @Override
    public int add(Event event) throws RemoteException {
        try {
            history.addEvent(event);
            System.out.println("---- Server: New event added: " + event.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public List<Event> query(int month, int date) throws RemoteException {
        try {
            List<Event> events = history.query(month, date);
            System.out.println("---- Server: '" + events + "' events found for month '"
                    + month + "' date '" + date  + "'" );
            return events;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
