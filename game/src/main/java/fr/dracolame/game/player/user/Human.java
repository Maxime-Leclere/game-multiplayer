package fr.dracolame.game.player.user;

import fr.dracolame.game.player.element.Element;
import fr.dracolame.game.player.Player;


public class Human extends Player {
    public Human(String name, Element element) {
        setName(name);
        setElement(element);
    }
}
