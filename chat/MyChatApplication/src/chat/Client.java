/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import static chat.chat_client.msg_areaa;
import static chat.chat_client.msg_textt;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;


/**
 *
 * @author Pichau
 */
public class Client implements Runnable{
    /*public static void main(String[] args) 
      throws UnknownHostException, IOException{
        Scanner s = new Scanner(System.in);
        System.out.println("Nome: ");
        String user = s.nextLine();
        
        new Client("10.0.0.17", 12345, user).executa(s);
        s.close();
    }
*/
    private String host;
    private int port;
    public Socket socket;
    public String username;
    public boolean envia = false;

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
    
    @Override
    public void run(){
        if(socket == null){
            msg_areaa.setText("Unable to connect.");
            return;
        }else{
            msg_areaa.setText("Client has been connected.");
        }
        msg_areaa.setText( msg_areaa.getText()+"\n Server ip: " + socket.getInetAddress().getHostAddress());
        
        
        
        try {
            ClientReceiver receiver = new ClientReceiver(socket.getInputStream());
            new Thread(receiver).start();

            PrintStream output = new PrintStream(socket.getOutputStream());  

            while(true)
            {
                Thread.sleep(1);
                if(envia == true){
                output.println("["+username+"]: " + msg_textt.getText());
                envia=false;
                }
                
            }
            //output.close();
            //socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
