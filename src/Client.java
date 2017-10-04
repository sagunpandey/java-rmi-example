import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client {

    private static final String OPERATION_ADD = "add";
    private static final String OPERATION_QUERY = "query";

    private static EventServer eventServer;

    public static void main(String[] args) throws
            RemoteException,
            NotBoundException,
            MalformedURLException {

        int argumentLength = args.length;
        String operation;

        if(argumentLength != 4 && argumentLength !=5) {
            throw new IllegalArgumentException("Invalid number of arguments provided");
        }

        Registry registry = LocateRegistry.getRegistry(args[0]);
        eventServer = (EventServer) registry.lookup("EventServer");

        operation = args[1];

        if(isAddOperation(operation)) {
            if(argumentLength != 5) {
                throw new IllegalArgumentException("Add operation requires five arguments " +
                        "'server add month date event'");
            } else {
                int month = Integer.valueOf(args[2]);
                int date = Integer.valueOf(args[3]);
                String eventDescription = String.valueOf(args[4]);

                add(month, date, eventDescription);
            }
        } else if(isQueryOperation(operation)) {
            if(argumentLength != 4) {
                throw new IllegalArgumentException("Query operation requires five arguments " +
                        "'server query month date'");
            } else {
                int month = Integer.valueOf(args[2]);
                int date = Integer.valueOf(args[3]);

                query(month, date);
            }
        } else {
            throw new IllegalArgumentException("Unknown operation '" + args[1] + "'");
        }
    }

    private static void add(int month, int date, String eventDescription) {
        Event event = new Event(month, date, eventDescription);
        try {
            System.out.println("Add operation invoked");
            int result = eventServer.add(event);
            System.out.println("Add operation response code '" + result + "'");
        } catch (RemoteException e) {
            System.out.println("Add operation failed");
            e.printStackTrace();
        }
    }

    private static void query(int month, int date) {
        try {
            System.out.println("Query operation invoked");
            List<Event> events =  eventServer.query(month, date);

            for(Event event: events) {
                System.out.println(event.getDescription());
            }
        } catch (RemoteException e) {
            System.out.println("Query operation failed");
            e.printStackTrace();
        }
    }

    private static boolean isAddOperation(String operation) {
        return operation.equalsIgnoreCase(OPERATION_ADD);
    }

    private static boolean isQueryOperation(String operation) {
        return operation.equalsIgnoreCase(OPERATION_QUERY);
    }
}
