
package com.combatgame.main;

import com.combatgame.controller.GameController;
import com.combatgame.view.GameView;
import javax.swing.*;

/**
 * Classe principale qui lance l'application Swing.
 */
public class Main {

    public static void main(String[] args) {
        // Swing doit être exécuté dans un thread dédié (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            // 1. Créer la fenêtre principale du jeu.
            JFrame frame = new JFrame("Jeu de Combat");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // 2. Créer le contrôleur.
            GameController controller = new GameController("Héros");

            // 3. Créer la vue principale (le JPanel) et la lier au contrôleur.
            GameView mainPanel = new GameView(controller);

            // 4. Lier la vue au contrôleur pour que le contrôleur puisse la mettre à jour.
            controller.setGameView(mainPanel);

            // 5. Ajouter le panneau principal à la fenêtre.
            frame.add(mainPanel);

            // 6. Rendre la fenêtre visible.
            frame.setVisible(true);

            // 7. On lance la logique de jeu en affichant le premier état (le menu).
            controller.handleState();
        });
    }
}


































//package com.combatgame.main;
//
//import com.combatgame.controller.GameController;
//import com.combatgame.factory.EnemyFactory;
//import com.combatgame.state.EndState;
//import com.combatgame.state.ExitState;
//import com.combatgame.state.PlayingState;
//
//import java.util.Scanner;
//
///**
// * Classe principale qui lance le jeu.
// * Pour l'instant, elle simule le jeu en mode console.
// */
//public class Main {
//
//    public static void main(String[] args) {
//
//        System.out.println("Bienvenue dans Combat Game !");
//        GameController controller = new GameController("Héros");
//
//        controller.startGame();
//        Scanner scanner = new Scanner(System.in);
//
//
//        while (!(controller.getCurrentState() instanceof ExitState)) {
//            String input = scanner.nextLine();
//            controller.processPlayerInput(input);
//        }
//
//        System.out.println("Fin de la partie.");
//        scanner.close();
//    }
//}