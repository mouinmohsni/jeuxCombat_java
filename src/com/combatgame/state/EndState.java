package com.combatgame.state;

import com.combatgame.controller.GameController;
import com.combatgame.view.GameView;

/**
 * État de fin de combat.
 * Analyse la situation (victoire ou défaite) et décide de la suite des événements.
 */
public class EndState implements GameState {

    @Override
    public void handle(GameController controller) {
        // On vérifie si le joueur est mort pour déterminer le message.
        if (controller.getPlayer().isDead()) {
            System.out.println("Le joueur a été vaincu.");
            // On met à jour le message dans le panneau de fin.
            controller.getGameView().getGameOverPanel().setMessage("DÉFAITE...");
        } else {
            System.out.println("L'ennemi a été vaincu !");
            // Note : Ce message ne s'affichera que pour le dernier ennemi.
        }

        // --- MODIFICATION CLÉ ---
        // On force l'affichage du panneau de fin de partie MAINTENANT.
        controller.getGameView().showPanel(GameView.GAME_OVER_PANEL);

        // Maintenant, on décide quoi faire après.
        if (!controller.startNextCombat()) {
            // S'il n'y a plus d'ennemis, la partie est vraiment gagnée !
            System.out.println("\nFÉLICITATIONS ! Vous avez vaincu tous les ennemis !");
            controller.getGameView().getGameOverPanel().setMessage("VICTOIRE !");
            // On passe à un état final qui ne fait rien, en attendant que le joueur clique sur Rejouer/Quitter.
            controller.setState(new ExitState());
        }
        // Si un nouveau combat a commencé, le contrôleur est déjà repassé en PlayingState.
    }

}
