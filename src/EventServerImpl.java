import java.rmi.RemoteException;
import java.util.List;

public class EventServerImpl implements EventServer {

    private HistoryProvider history;

    public EventServerImpl() {
        history = new HistoryProvider();
    }

    @Override
    public int add(Event event) throws RemoteException {
        try {
            history.addEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    @Override
    public List<Event> query(int month, int date) throws RemoteException {
        try {
            return history.query(month, date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
