import java.io.PrintStream;
import java.net.Socket;

public class ClientModel {
    public PrintStream ps;
    public Socket client;

    public ClientModel(PrintStream ps, Socket client) {
        this.ps = ps;
        this.client = client;
    }
}
