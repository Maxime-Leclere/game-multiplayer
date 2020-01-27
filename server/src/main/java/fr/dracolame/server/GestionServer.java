package fr.dracolame.server;

import java.net.UnknownHostException;

public class GestionServer {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.open();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
