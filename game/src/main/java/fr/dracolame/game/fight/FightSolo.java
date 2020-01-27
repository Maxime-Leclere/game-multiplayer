package fr.dracolame.game.fight;

import fr.dracolame.game.player.Player;

public class FightSolo extends Fight {

    public FightSolo(Player player, Player playerAdvers) {
        setPlayer(player);
        setCurrentPlayer(player);
        setOppositionPlayer(playerAdvers);
    }

}
