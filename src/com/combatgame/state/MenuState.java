package com.combatgame.state;

import com.combatgame.controller.GameController;

/**
 * État du menu principal. Attend que le joueur choisisse de commencer ou de quitter.
 */
public class MenuState implements GameState {

    @Override
    public void handle(GameController controller) {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Commencer une nouvelle partie");
        System.out.println("2. Quitter le jeu");
        System.out.print("Votre choix : ");
    }

    /**
     * Traite l'entrée de l'utilisateur dans le menu.
     */
    public void processInput(GameController controller, String input) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Entrée invalide.");
            return;
        }

        switch (input.trim()) {
            case "1":
                // Le joueur veut commencer. On passe à l'état de préparation (StartState).
                controller.setState(new StartState());
                controller.handleState();
                break;
            case "2":
                // Le joueur veut quitter. On passe à un état final qui termine le jeu.
                System.out.println("Merci d'avoir joué ! À bientôt.");
                controller.setState(new ExitState()); // Nous allons créer cet état simple.
                controller.handleState();
                break;
            default:
                System.out.println("Choix invalide. Veuillez taper 1 ou 2.");
                break;
        }
    }
}
