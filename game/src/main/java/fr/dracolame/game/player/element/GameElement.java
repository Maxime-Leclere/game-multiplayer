package fr.dracolame.game.player.element;

import java.util.Random;

public class GameElement {
    private static Element feu    = new Element("Feu"   , null, null);
    private static Element eau    = new Element("Eau"   , null, null);
    private static Element air    = new Element("Air"   , null, null);
    private static Element terre  = new Element("Terre" , null, null);
    private static Element foudre = new Element("foudre", null, null);

    static {
        feu.setFaiblesse(eau);
        feu.setAvantage(air);
        eau.setFaiblesse(foudre);
        eau.setAvantage(feu);
        air.setFaiblesse(feu);
        air.setAvantage(terre);
        terre.setFaiblesse(air);
        terre.setAvantage(foudre);
        foudre.setFaiblesse(terre);
        foudre.setAvantage(eau);
    }

    public static Element getFeu() {
        return feu;
    }

    public static Element getEau() {
        return eau;
    }

    public static Element getAir() {
        return air;
    }

    public static Element getTerre() {
        return terre;
    }

    public static Element getFoudre() {
        return foudre;
    }

    /*
    choisi un element au hazard pour les IA ou si le choix des elements pour les joueurs sont aleatoire
     */
    public static Element getRandomElement() {
        Random random = new Random();
        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                return getFeu();
            case 1:
                return getEau();
            case 2:
                return getAir();
            case 3:
                return getTerre();
            default:
                return getRandomElement();
        }
    }
}
