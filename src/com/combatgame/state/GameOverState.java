package com.combatgame.state;

import com.combatgame.controller.GameController;

/**
 * État de fin de partie.
 * Affiche le résultat final.
 */
public class GameOverState implements GameState {
    @Override
    public void handle(GameController controller) {
        System.out.println("--- GAME OVER ---");
        // Affiche le résultat et propose de revenir au Menu.
    }
}
