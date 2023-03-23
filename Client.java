import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) 
      throws UnknownHostException, IOException{
        Scanner s = new Scanner(System.in);
        System.out.println("Nome: ");
        String user = s.nextLine();
        
        new Client("10.0.0.17", 12345, user).executa(s);
        s.close();
    }

    private String host;
    private int port;
    public Socket socket;
    public String username;

    public Client(String host, int port, String username){
        this.host = host;
        this.port = port;
        this.username = username;
        try {
            this.socket = new Socket(this.host, this.port);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void executa(Scanner keyboard){
        if(socket == null){
            System.out.println("Unable to connect.");
            return;
        }else{
            System.out.println("Client has been connected.");
        }
        System.out.println("Server ip: " + socket.getInetAddress().getHostAddress());
        
        try {
            ClientReceiver receiver = new ClientReceiver(socket.getInputStream());
            new Thread(receiver).start();

            PrintStream output = new PrintStream(socket.getOutputStream());  

            while(keyboard.hasNextLine())
            {
                output.println("["+username+"]: " + keyboard.nextLine());
            }
            output.close();
            socket.close();
            keyboard.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
