import java.io.InputStream;
import java.util.Scanner;

public class ClientReceiver implements Runnable {
    private InputStream server;

    public ClientReceiver(InputStream server) {
        this.server = server;
    }

    @Override
    public void run(){
        Scanner receiver = new Scanner(this.server);
        while(receiver.hasNextLine())
        {
            System.out.println(receiver.nextLine());
        }
        receiver.close();
    }
}
