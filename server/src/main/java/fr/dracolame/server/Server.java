package fr.dracolame.server;

import fr.dracolame.game.client.Client;
import fr.dracolame.game.client.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private int port = 2342;
    private InetAddress inetAddress = InetAddress.getLocalHost();
    private ServerSocket serverListen;
    private boolean running;
    private HashMap<Client, Client> listGame = new HashMap<Client, Client>(); // client qui sont connecter et en match
    private ArrayList<Client> listClient = new ArrayList<Client>(); // client qui sont connecter mais pas en match

    public Server() throws UnknownHostException {
        try {
            serverListen = new ServerSocket(port, 100, inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public ServerSocket getServerListen() {
        return serverListen;
    }

    public void setServerListen(ServerSocket serverListen) {
        this.serverListen = serverListen;
    }

    public HashMap<Client, Client> getListGame() {
        return listGame;
    }

    public void setListGame(HashMap<Client, Client> listGame) {
        this.listGame = listGame;
    }

    public ArrayList<Client> getListClient() {
        return listClient;
    }

    public void setListClient(ArrayList<Client> listClient) {
        this.listClient = listClient;
    }

    public void storeGame(Client client1, Client client2) {
        listGame.put(client1, client2);
    }

    public void open() {
        new Thread(() -> {
            while(isRunning()){

                try {
                    //On attend une connexion d'un client
                    Socket client = serverListen.accept();

                    //Une fois reçue, on la traite dans un thread séparé
                    System.out.println("Connexion cliente reçue.");
                    Thread t = new Thread(() -> {
                        try (ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream())) {
                            Request command = (Request) objectInputStream.readObject();
                            switch (command.getCommand()) {
                                default:
                                    break;
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.out.println(toString());
                    }/*TODO*/); // faire une autre class qui implemente runnable qui traite les connexions ici envoi la la command au client destination
                    t.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                serverListen.close();
            } catch (IOException e) {
                e.printStackTrace();
                serverListen = null;
            }
        }).start();
    }

    public void close() {
        setRunning(false);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public String toString() {
        String titleListMatch = "LISTE DES CLIENTS EN MATCH:\n";
        String listMatch = "";
        listGame.forEach((k, v)-> {
            listMatch.concat(""+k.getPlayer().getName() + " vs " + v.getPlayer().getName() +"\n");
        });

        String titleListClient = "\nLISTE DES CLIENTS CONNECTES:\n";
        String listClientStr = "";
        for (Client client: listClient) {
            listClientStr += client.getPlayer().getName();
        }
        return "".concat(titleListMatch + listMatch + titleListClient +
                listClientStr);
    }
}
