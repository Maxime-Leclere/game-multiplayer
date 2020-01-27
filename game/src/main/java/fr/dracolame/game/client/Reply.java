package fr.dracolame.game.client;

import java.io.Serializable;

public class Reply implements Serializable, Data {
    private int numCommand;
    private Client clientDoReply;

    public Reply(int num, Client clientDoReply) {
        numCommand = num;
        this.clientDoReply = clientDoReply;
    }

    public int getNumCommand() {
        return numCommand;
    }

    public void setNumCommand(int numCommand) {
        this.numCommand = numCommand;
    }

    public Client getClientDoReply() {
        return clientDoReply;
    }

    public void setClientDoReply(Client clientDoReply) {
        this.clientDoReply = clientDoReply;
    }
}
