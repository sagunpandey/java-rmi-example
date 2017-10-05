import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created By Sagun Pandey
 */
public interface EventServer extends Remote {

    int add(Event event) throws RemoteException;

    List<Event> query(int month, int date) throws RemoteException;
}
