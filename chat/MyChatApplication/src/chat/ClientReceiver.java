/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import static chat.chat_client.msg_areaa;

import java.awt.TextArea;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JTextArea;

/**
 *
 * @author hashi
 */
public class ClientReceiver implements Runnable {

    private InputStream server;

    public ClientReceiver(InputStream server) {
        this.server = server;
    }

    @Override
    public void run() {
        Scanner receiver = new Scanner(this.server);
        while (receiver.hasNextLine()) {
            msg_areaa.setText(msg_areaa.getText() + "\n" + receiver.nextLine());
        }
        receiver.close();
    }
}
