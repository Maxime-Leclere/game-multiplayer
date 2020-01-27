package fr.dracolame.game.client;

import fr.dracolame.game.fight.FightMultiPlayer;
import fr.dracolame.game.fight.FightSolo;
import fr.dracolame.game.player.Player;
import fr.dracolame.game.player.user.Human;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Stack;

public class Client implements Serializable {
    private static final String adressServor = "175.124.25.0";
    private int numClient = new Random().nextInt();
    private Player player;
    private FightSolo fight;
    private FightMultiPlayer fightMultiPlayer;
    private Socket socket;
    private Stack<Request> waitingList = new Stack<>();

    public Client() {
        logInOnClient();
        try {
            // connexion au serveur
            socket = new Socket(InetAddress.getLocalHost(), 1452);
            System.out.println("connecter sur le serveur!!");

            // traitement de donnee recu du serveur
            new Thread().start();
        } catch (UnknownHostException e) {
            System.out.println("Le serveur est introuvable ou est déconnecté");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logInOnClient() {
        System.out.println("taper votre nom\n");

        String choix = insertStr();

        player = new Human(choix, null);
    }

    private void sendData(Data data) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            wait(10000);
            sendData(data);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            return;
        }
    }

    private void processCommand() {
        if (!waitingList.empty()) {
            Client clientData = waitingList.get(0).getClientDoCommand();
            String commandCurrent = waitingList.get(0).getCommand();
            switch (commandCurrent) {
                case "strike":
                    // le joueur adverse attaque
                    clientData.getPlayer().strikeTo(player);
                    sendData(new Request("requestAttack:OK", this));
                    break;
                case "flee":
                    // le joueur adverse fuite
                    fight.setRunning(false);
                    break;
                case "requestFight":
                    // le joueur demande un combat
                    if(fight.isRunning()) {
                        sendData(new Request("requestFight:occupied", this));
                        break;
                    }
                    System.out.println(clientData + "demande un combat...");
                    String choix = insertStr();
                    if (choix.equals("yes")) {
                        sendData(new Request("requestFight:OK", this));
                        fightMultiPlayer = new FightMultiPlayer(getPlayer(), clientData.getPlayer());
                        break;
                    } else {
                        sendData(new Request("requestFight:refuse", this));
                        break;
                    }

                case "requestFight:OK":
                    fightMultiPlayer = new FightMultiPlayer(getPlayer(), clientData.getPlayer());
                    break;
                case "requestFight:occupied":
                    System.out.println("Demande refuser : le joueur est actuelement entrain de se battre\n");
                    break;
            }
            sendData(new Reply(waitingList.get(0).getNum(), this));
            waitingList.removeElementAt(0);
        }
    }
    public static String insertStr() {
        String choix = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            choix =  String.valueOf(in.readLine());
        } catch (IOException e) {
            System.out.println("Problème de saisie");
        }
        return choix;
    }

    public static String getAdressServor() {
        return adressServor;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public FightSolo getFight() {
        return fight;
    }

    public void setFight(FightSolo fight) {
        this.fight = fight;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Stack<Request> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Stack<Request> waitingList) {
        this.waitingList = waitingList;
    }

    public FightMultiPlayer getFightMultiPlayer() {
        return fightMultiPlayer;
    }

    public void setFightMultiPlayer(FightMultiPlayer fightMultiPlayer) {
        this.fightMultiPlayer = fightMultiPlayer;
    }

    public int getNumClient() {
        return numClient;
    }
}
