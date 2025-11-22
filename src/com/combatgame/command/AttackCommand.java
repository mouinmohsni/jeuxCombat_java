package com.combatgame.command;

import com.combatgame.model.Character;


/**
 * Commande pour l'action d'attaquer.
 */
public class AttackCommand implements Command {

    private Character  player;
    private Character  target;

    public AttackCommand(Character  player, Character  target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public void execute() {
        // Le joueur utilise la méthode attack() héritée de Character
        player.attack(target);
    }
}
