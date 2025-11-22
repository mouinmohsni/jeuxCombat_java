package com.combatgame.state;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character; // Import Character
import com.combatgame.model.Enemy;     // Import Enemy
import com.combatgame.model.enemy.composite.EnemyGroup; // Import EnemyGroup

/**
 * État du tour de l'ennemi : exécute l'IA de l'ennemi.
 */
public class EnemyTurnState implements GameState {

    @Override
    public void handle(GameController controller) {

        Character opponent = controller.getCurrentEnemy();

        System.out.println("\n--- Tour de " + opponent.getName() + " ---");

        // Vérification de fin de combat
//        if (controller.getPlayer().isDead()) {
//            controller.setState(new EndState());
//            controller.handleState();
//            return;
//        }

        if (opponent instanceof Enemy) {
            // Si c'est un ennemi simple, on utilise son IA (performAction).
            ((Enemy) opponent).performAction(controller.getPlayer());

        } else if (opponent instanceof EnemyGroup) {
            // Si c'est un groupe, on lui demande d'attaquer en tant que groupe.
            ((EnemyGroup) opponent).attack(controller.getPlayer());

        } else {
            // Cas par défaut, si l'adversaire n'est ni l'un ni l'autre.
            System.out.println(opponent.getName() + " ne fait rien ce tour-ci.");
        }



        // L'ennemi exécute son action (via son IA interne)
       // controller.getCurrentEnemy().performAction(controller.getPlayer());

    }
}
