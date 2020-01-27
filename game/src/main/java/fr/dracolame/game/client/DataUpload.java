package fr.dracolame.game.client;

import java.net.Socket;

public class DataUpload implements Runnable {
    private Socket socket;

    public DataUpload(Socket socket) {
        setSocket(socket);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO ne pas oublier demande d'affichage des joueurs connectée
    }
}
