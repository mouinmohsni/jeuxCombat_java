package com.combatgame.command;


/**
 * Interface pour le Design Pattern Command.
 * Chaque action du joueur sera une implémentation de cette interface.
 */
public interface Command {

    /**
     * Exécute l'action encapsulée par la commande.
     */
    void execute();
}
