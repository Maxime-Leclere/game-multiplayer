package fr.dracolame.game.fight;

import fr.dracolame.game.player.Player;

public abstract class Fight {
    private Player player;
    private Player oppositionPlayer;
    private Player currentPlayer;
    private boolean running = true;
    private int round = 0;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getOppositionPlayer() {
        return oppositionPlayer;
    }

    public void setOppositionPlayer(Player oppositionPlayer) {
        this.oppositionPlayer = oppositionPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isCurrentPlayer(Player player) {
        return player.equals(getCurrentPlayer());
    }

    public Player getNoCurrentPlayer() {
        if(isCurrentPlayer(getPlayer()))return getOppositionPlayer();
        else return getPlayer();
    }

    public void stopFight() {
        setRunning(false);
        System.out.println("Le combat est terminer!!!!");
    }

    public void inverseCurrentPlayer() {
        if(isCurrentPlayer(getPlayer())) {
            setPlayer(getCurrentPlayer());
            setCurrentPlayer(getOppositionPlayer());
        }
        else {
            setOppositionPlayer(getCurrentPlayer());
            setCurrentPlayer(getPlayer());
        }
    }

    public void checkPointLife(){
        if (getPlayer().getLife() <= 0) {
            System.out.println("Le joueur "+ getPlayer().getName() +" n'a plus de point de vie");
            stopFight();
        }
        else if (getOppositionPlayer().getLife() <= 0) {
            System.out.println("Le joueur "+ getOppositionPlayer().getName() +" n'a plus de point de vie");
            stopFight();
        }
    }

    public void nextRound() {
        setRound(++round);
        inverseCurrentPlayer();
    }

    @Override
    public String toString() {
        return "Tour : " + getRound() + "\n" +
                "Joueur : \n" + getPlayer().toString() + "\n" +
                "Joueur Adverse: \n" + getOppositionPlayer().toString() + "\n";
    }
}
