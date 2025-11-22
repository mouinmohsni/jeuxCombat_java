package com.combatgame.state;

import com.combatgame.controller.GameController;
import com.combatgame.command.*;

/**
 * État de jeu en cours.
 * Initialise le combat et gère la boucle de jeu (qui est maintenant une sous-machine à états).
 */
public class PlayingState implements GameState {

    // Sous-état pour gérer le tour actuel (Joueur, Ennemi, etc.)
    private GameState currentTurnState;

    public PlayingState() {
        // Le combat commence par le tour du joueur
        this.currentTurnState = new PlayerTurnState();
    }

    /**
            * Gère la logique de l'état de jeu "en cours".
            * Cette méthode délègue simplement l'appel au sous-état de tour actuel (PlayerTurnState ou EnemyTurnState).
            *
            * @param controller Le contrôleur principal du jeu.
            */
    @Override
    public void handle(GameController controller) {
        // Le PlayingState délègue la gestion du tour au sous-état actuel
        currentTurnState.handle(controller);
    }

    /**
     * Permet de changer le sous-état de tour (passer du joueur à l'ennemi et vice-versa).
     *
     * @param newState Le nouvel état de tour (par exemple, un nouvel EnemyTurnState).
     */
    public void setTurnState(GameState newState) {
        this.currentTurnState = newState;
    }



    /**
     * Traite l'entrée de l'utilisateur (par exemple, 'A', 'D').
     * Cette méthode est appelée par le GameController et ne transmet l'entrée
     * que si le sous-état actuel est bien le tour du joueur.
     *
     * @param controller Le contrôleur principal du jeu.
     * @param input      La chaîne de caractères représentant l'action du joueur.
     */
    public void processInput(GameController controller, String input) {
        // Vérifie si c'est bien au joueur de jouer avant de traiter l'entrée.
        if (currentTurnState instanceof PlayerTurnState) {
            // Délègue le traitement de l'entrée à l'état du tour du joueur.
            ((PlayerTurnState) currentTurnState).processInput(controller, input);
        }
        // Si ce n'est pas le tour du joueur, l'entrée est ignorée (géré dans le contrôleur).
    }

    /**
     * Getter pour obtenir le sous-état de tour actuel.
     * Utile pour le GameController afin de vérifier si c'est le tour du joueur.
     *
     * @return Le GameState du tour actuel.
     */
    public GameState getCurrentTurnState() {
        return currentTurnState;
    }

}
