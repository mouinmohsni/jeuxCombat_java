package com.combatgame.command;


import com.combatgame.model.Player;

/**
 * Commande pour l'action de défense.
 * Encapsule l'appel à la méthode defend() du joueur.
 */
public class DefendCommand implements Command {

    private final Player player;

    public DefendCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        System.out.println(player.getName() + " se met en position de défense !");
        player.defend();
    }
}
