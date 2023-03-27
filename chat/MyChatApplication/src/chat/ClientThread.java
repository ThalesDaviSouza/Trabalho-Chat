/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import static chat.chat_client.msg_textt;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hashi
 */
public class ClientThread implements Runnable {

    private InputStream client;
    private Servidor server;
    private Socket clientIp;

    public ClientThread(InputStream client, Servidor server, Socket clientIp) {
        this.client = client;
        this.server = server;
        this.clientIp = clientIp;
    }

    @Override
    public void run() {
        // quando chegar uma msg, distribui pra todos
        Scanner s = new Scanner(this.client);
        while (s.hasNextLine()) {

            server.ShareMessage(s.nextLine(), clientIp);
        }
        s.close();
    }
}
