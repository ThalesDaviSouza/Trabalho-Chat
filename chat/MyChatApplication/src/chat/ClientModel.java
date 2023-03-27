/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author hashi
 */
public class ClientModel {

    public PrintStream ps;
    public Socket client;

    public ClientModel(PrintStream ps, Socket client) {
        this.ps = ps;
        this.client = client;
    }
}
