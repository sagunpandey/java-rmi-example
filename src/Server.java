import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created By Sagun Pandey
 */
public class Server {

    public static void main(String args[]) {
        // Provide IP Address of the host running the server
        // System.setProperty("java.rmi.server.hostname", "140.158.130.56");

        Server INSTANCE = new Server();

        INSTANCE.start();
    }

    private void start() {
        try {
            EventServerImpl eventServer = new EventServerImpl();

            // Creating registry
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            // port = 0 : Random available port for RMI service port
            EventServer stub = (EventServer) UnicastRemoteObject.exportObject(eventServer, 0);

            // Rebinding registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("EventServer", stub);

            System.out.println("---- Server: Ready");
        } catch (Exception e) {
            System.err.println("---- Server: Exception - " + e.toString());
            e.printStackTrace();
        }
    }
}
