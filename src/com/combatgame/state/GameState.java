package com.combatgame.state;

import com.combatgame.controller.GameController;

/**
 * Interface pour le Design Pattern State.
 * Chaque état concret du jeu (tour du joueur, tour de l'ennemi, etc.)
 * devra implémenter cette interface.
 */
public interface GameState {

    /**
     * La méthode principale que le contrôleur appellera sur l'état actuel.
     * C'est ici que toute la logique de l'état est exécutée.
     *
     * @param controller Une référence au contrôleur principal du jeu,
     *                   pour que l'état puisse interagir avec le modèle et changer l'état du jeu.
     */
    void handle(GameController controller);

}