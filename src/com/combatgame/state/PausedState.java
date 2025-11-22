package com.combatgame.state;

import com.combatgame.controller.GameController;

/**
 * État de pause.
 * Le jeu est figé, mais peut reprendre.
 */
public class PausedState implements GameState {
    private GameState previousState; // Pour savoir où revenir

    public PausedState(GameState previousState) {
        this.previousState = previousState;
    }

    @Override
    public void handle(GameController controller) {
        System.out.println("--- JEU EN PAUSE ---");
        // Dans Swing, affiche le panneau de pause.
    }

    public GameState getPreviousState() {
        return previousState;
    }
}
