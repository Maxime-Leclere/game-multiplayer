package fr.dracolame.game.client;

import java.io.Serializable;
import java.util.Random;

public class Request implements Serializable, Data {
    private int num = new Random().nextInt();
    private String command;
    private Client clientDoCommand;

    public Request(String command, Client client){
        setCommand(command);
        setClientDoCommand(client);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Client getClientDoCommand() {
        return clientDoCommand;
    }

    public void setClientDoCommand(Client clientDoCommand) {
        this.clientDoCommand = clientDoCommand;
    }

    public int getNum() {
        return num;
    }
}
