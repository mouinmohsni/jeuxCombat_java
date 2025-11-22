package com.combatgame.state;



import com.combatgame.command.Command;
import com.combatgame.controller.GameController;
import com.combatgame.factory.EnemyFactory;
import com.combatgame.factory.EnemyFactory.EnemyType;
import java.util.LinkedList;
import java.util.Queue;
import com.combatgame.model.Character;
import com.combatgame.model.enemy.composite.EnemyGroup;

    /**
     * État initial : initialise le combat et passe au tour du joueur.
     */
    public class StartState implements GameState {

        @Override
        public void handle(GameController controller) {
            System.out.println("\n=======================================");
            System.out.println("Début du combat !");
            System.out.println("\nPréparation de la partie...");

            // 1. Créer la file d'attente des ennemis pour cette partie.
            Queue<Character> enemyProgression = new LinkedList<>();
            // Pour l'instant, l'ordre est fixe. On pourra le randomiser plus tard.
            enemyProgression.add(EnemyFactory.createEnemy(EnemyType.ARCHER));
            enemyProgression.add(EnemyFactory.createEnemy(EnemyType.WARRIOR));
            enemyProgression.add(EnemyFactory.createEnemy(EnemyType.MAGE));

            EnemyGroup bossGroup = new EnemyGroup("Le Roi et sa Garde");
            bossGroup.add(EnemyFactory.createEnemy(EnemyType.WARRIOR)); // Le garde
            bossGroup.add(EnemyFactory.createEnemy(EnemyType.MAGE));   // Le Roi Mage

            enemyProgression.add(bossGroup);
            controller.setEnemyProgression(enemyProgression);



            System.out.println("=======================================");
            System.out.println("Partie prête !");

            if (controller.startNextCombat()) {
                // Si un combat a bien été lancé, le contrôleur est déjà passé en PlayingState.
            } else {
                // S'il n'y a aucun ennemi dans la liste, on termine.
                System.out.println("Aucun ennemi à combattre. Fin du jeu.");
                controller.setState(new ExitState());
                controller.handleState();
            }


        }



    }

