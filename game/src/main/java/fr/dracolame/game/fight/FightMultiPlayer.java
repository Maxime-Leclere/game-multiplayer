package fr.dracolame.game.fight;

import fr.dracolame.game.player.Player;

public class FightMultiPlayer extends Fight {
    public FightMultiPlayer(Player player, Player oppositionPlayer) {
        setPlayer(player);
        setOppositionPlayer(oppositionPlayer);
        setPlayer(player);
    }
}
