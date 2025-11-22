package com.combatgame.state;


import com.combatgame.command.AttackCommand;
import com.combatgame.command.Command;
import com.combatgame.command.DefendCommand;
import com.combatgame.command.UseItemCommand;
import com.combatgame.controller.GameController;
import com.combatgame.model.items.Item;

/**
 * État du tour du joueur : attend une action du joueur.
 */
public class PlayerTurnState implements GameState {

    @Override
    public void handle(GameController controller) {
        // Vérification de fin de combat
//        if (controller.getCurrentEnemy().isDead()) {
//            controller.setState(new EndState());
//            controller.handleState();
//            return;
//        }

        // --- PARTIE MANQUANTE ---
        // Affiche les informations vitales pour le joueur.
        System.out.println("\n--- Tour de " + controller.getPlayer().getName() + " ---");
        System.out.println("HP: " + controller.getPlayer().getHealth() + "/" + controller.getPlayer().getMaxHealth());
        System.out.println("Ennemi HP: " + controller.getCurrentEnemy().getHealth() + "/" + controller.getCurrentEnemy().getMaxHealth());
        System.out.println("Actions : (A) Attaquer, (D) Défendre, (I) Inventaire");
        // --- FIN DE LA PARTIE MANQUANTE ---
    }

    public void processInput(GameController controller, String input) {
        if (input == null || input.trim().isEmpty()) {
            return; // Ignore les entrées vides
        }

        Command command = null;

        switch (input.toUpperCase()) {
            case "A":
                // Crée une commande d'attaque
                command = new AttackCommand(controller.getEffectivePlayer(), controller.getEffectiveEnemy());
                break;
            case "D":
                // Crée une commande de défense
                command = new DefendCommand(controller.getPlayer());
                break;
            case "I":
                // Logique pour utiliser un item
                if (controller.getPlayer().getInventory().isEmpty()) {
                    System.out.println("Votre inventaire est vide !");
                    return; // Ne termine pas le tour
                }
                // Pour l'instant, on utilise le premier item de la liste.
                Item itemToUse = controller.getPlayer().getInventory().get(0);
                command = new UseItemCommand(controller.getPlayer(), itemToUse, controller);
                break;

            default:
                System.out.println("Action invalide.");
                return;
        }

        // Si une commande valide a été créée, on l'exécute via le contrôleur
        controller.executePlayerCommand(command);
    }
}
