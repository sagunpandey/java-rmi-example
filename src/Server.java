import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String args[]) {
        Server INSTANCE = new Server();

        INSTANCE.start();
    }

    private void start() {
        try {
            EventServerImpl eventServer = new EventServerImpl();
            EventServer stub = (EventServer) UnicastRemoteObject.exportObject(eventServer, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("EventServer", stub);

            System.out.println("Server: Ready");
        } catch (Exception e) {
            System.err.println("Server: Exception - " + e.toString());
            e.printStackTrace();
        }
    }
}
