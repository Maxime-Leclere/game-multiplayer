package fr.dracolame.game;

import fr.dracolame.game.fight.Fight;
import fr.dracolame.game.player.user.Human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GestionGame {

    private static String menu(Fight fight) {
        if (fight.getCurrentPlayer().getClass() == Human.class) {
            String choix = "";
            System.out.println("Attaque\nFuite\nTaper le choix de votre action");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try {
                choix =  String.valueOf(in.readLine());
            } catch (IOException e) {
                System.out.println("Problème de saisie");
            }
            return choix;
        }
        return "L'IA choisi une action";
    }

    private static void executeAction(Fight fight, String action) {
        switch (action) {
            case "attaque":
                fight.getCurrentPlayer().strikeTo(fight.getNoCurrentPlayer());
                System.out.println("Le joueur " + fight.getCurrentPlayer().getName() + " attaque " +
                        fight.getNoCurrentPlayer().getName() + " et " + fight.getNoCurrentPlayer().getName() + " subit " +
                        fight.getCurrentPlayer().getDamageFor(fight.getNoCurrentPlayer()) + " de point de vie");
                break;
            case "fuite":
                System.out.println("le Joueur " + fight.getCurrentPlayer().getName() + " a pris la fuite");
                fight.stopFight();
                break;
            default:
                System.out.println(action);
                break;
        }
        fight.checkPointLife();
        if(fight.isRunning()) {
            fight.nextRound();
            System.out.println(fight.toString());

        }
    }

    public static void main(String[] args) {
//        Player user = new Human("Max", GameElement.getFeu());
//        Player userAdver = new IaTimid(GameElement.getAir());
//        Fight fight = new FightSolo(user, userAdver);
//        System.out.println("Le combat commence!!!\n"+fight.toString());
//
//        while (fight.isRunning()) {
//            executeAction(fight, menu(fight));
//        }
//        Client client = new Client();
    }
}
