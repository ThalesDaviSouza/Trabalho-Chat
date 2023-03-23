import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private int port;
    private List<ClientModel> ConnectedClients;

    public static void main(String[] args) {
        try {
            new Servidor(12345).Host();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public Servidor(int door){
        this.port = door;
        this.ConnectedClients = new ArrayList<ClientModel>();
    }

    public void Host() throws IOException{
        ServerSocket server = new ServerSocket(this.port);
        System.out.println("Server started in port: " + port);

        while(true){
            Socket client = server.accept();
            System.out.println("Connected with a new client: " +
                client.getInetAddress().getHostAddress());

            PrintStream ps = new PrintStream(client.getOutputStream());

            this.ConnectedClients.add(new ClientModel(ps, client));

            ClientThread threadClient = new ClientThread(client.getInputStream(), this, client);
            new Thread(threadClient).start();
        }
    }

    public void ShareMessage(String message, Socket author){
        for (ClientModel actualClient : ConnectedClients) {
            if(actualClient.client.getInetAddress().getHostAddress().compareTo(author.getInetAddress().getHostAddress()) != 0 ){
                actualClient.ps.println(message);

            }
        }
    }

}
