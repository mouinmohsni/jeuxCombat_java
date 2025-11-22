package com.combatgame.command;

import com.combatgame.controller.GameController;
import com.combatgame.model.Player;
import com.combatgame.model.items.Item;

/**
 * Commande pour l'action d'utilisation d'un objet.
 */
public class UseItemCommand implements Command {

    private final Player player;
    private final Item item;
    private final GameController controller;

    public UseItemCommand(Player player, Item item, GameController controller) {
        this.player = player;
        this.item = item;
        this.controller = controller;
    }

    @Override
    public void execute() {
        // Appelle la méthode du joueur en lui passant le contrôleur.
        player.useItem(item, controller);
    }
}