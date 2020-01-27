package fr.dracolame.game.client;

import fr.dracolame.game.fight.FightMultiPlayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class DataReception implements Runnable {
    private Socket socket;
    private Client client;
    private boolean reply = false;

    public DataReception(Socket socket, Client client) {
        setSocket(socket);
        setClient(client);
    }

    public DataReception(Socket socket, Client client, boolean reply) {
        setSocket(socket);
        setClient(client);
        setReply(reply);
    }

    public boolean isReply() {
        return reply;
    }

    public void setReply(boolean reply) {
        this.reply = reply;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!getSocket().isClosed()) {
            new Thread(() -> {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
                    if (!isReply()) {
                        Request data = (Request) objectInputStream.readObject();

                        // stockage des requetes
                        getClient().getWaitingList().add((Request) data);

                        // Traitement des requetes
                        switch (((Request) data).getCommand()) {
                            case "requestFight":
                                System.out.println("le joueur"+ getClient().getPlayer().getName() + "veut faire un combat avec vous.");
                                String responce = Client.insertStr();
                                if(responce.equals("ok")) {
                                    getClient().setFightMultiPlayer(new FightMultiPlayer(getClient().getPlayer(),
                                            ((Request) data).getClientDoCommand().getPlayer()));
                                } else /*TODO faire DataUpload*/;
                                break;
                            case "requestAttack":
                                data.getClientDoCommand().getPlayer().strikeTo(getClient().getPlayer());
                                System.out.println("le joueur " + getClient().getPlayer().getName() + " vous attaque.");
                                break;
                            case "requestFlee":
                                System.out.println("le joueur " + getClient().getPlayer().getName() + " s'est enfuit du combat.");
                                getClient().getFightMultiPlayer().stopFight();
                                getClient().getPlayer().reset();
                                break;
                            case "requestOccupied":
                                System.out.println("le joueur " + getClient().getPlayer().getName() + " est actuellement en plein combat.");
                                break;
                            case "requestRefused":
                                System.out.println("le joueur " + getClient().getPlayer().getName() + " a refuser votre demande de combat.");
                                break;
                        }
                        // TODO envoie le reply
                    } else {
                        // TODO gestion des reply
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

}
