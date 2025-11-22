package com.combatgame.state;

import com.combatgame.controller.GameController;

/**
 * Un état final qui ne fait rien, utilisé pour arrêter la boucle de jeu.
 */
public class ExitState implements GameState {
    @Override
    public void handle(GameController controller) {
        // Ne fait rien. La boucle de jeu dans Main s'arrêtera.
    }
}
