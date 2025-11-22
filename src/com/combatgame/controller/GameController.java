// Dans GameController.java

package com.combatgame.controller;

import com.combatgame.factory.EnemyFactory;
import com.combatgame.factory.EnemyFactory.EnemyType;
import com.combatgame.model.Enemy;
import com.combatgame.model.Player;
import com.combatgame.model.Character;
import com.combatgame.model.decorator.ActiveEffect;
import com.combatgame.state.*; // Importe tous les états
import com.combatgame.view.GameView; // Importe la vue
import com.combatgame.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Le contrôleur principal du jeu.
 * Il gère le Pattern State et sert de contexte pour le modèle (Player, Enemy).
 */
public class GameController {

    private Player player;
    private Character  currentEnemy;
    private GameState currentState;
    private Queue<Character> enemyProgression;
    private List<ActiveEffect> playerEffects;
    private  List<ActiveEffect> enemyEffects ;
    private GameView gameView; // Référence à la vue

    public GameController(String playerName) {
        this.player = new Player(playerName);
        this.playerEffects = new ArrayList<>();
        this.enemyEffects= new ArrayList<>();
        // Le jeu commence par l'état du Menu
        this.currentState = new MenuState();
    }

    public void addEffectToPlayer(ActiveEffect effect) {
        this.playerEffects.add(effect);
    }

    public void setGameView(GameView gameView) { // Setter pour la vue
        this.gameView = gameView;
    }

    /**
     * Démarre la machine à états du jeu.
     */
    public void startGame() {
        // Le jeu commence en demandant à l'état actuel (MenuState) de s'exécuter
        handleState();
        System.out.println("sart new game");
    }

    /**
     * Méthode centrale qui exécute la logique de l'état actuel.
     * C'est le moteur du Pattern State.
     */
    public void handleState() {
        this.currentState.handle(this);

        // Mise à jour de la Vue après chaque changement d'état global
        if (gameView != null) {
            if (currentState instanceof MenuState) {
                gameView.showPanel(GameView.MENU_PANEL);
            } else if (currentState instanceof PlayingState) {
                gameView.getCombatPanel().updateView();
                gameView.showPanel(GameView.COMBAT_PANEL);

            } else if (currentState instanceof EndState) {
                // --- LOGIQUE DE FIN CENTRALISÉE ---
                if (getPlayer().isDead()) {
                    // Cas 1 : Le joueur est mort, c'est une défaite.
                    gameView.getGameOverPanel().showFinalMessage("DÉFAITE...");
                    gameView.showPanel(GameView.GAME_OVER_PANEL);

                } else if (enemyProgression.isEmpty()) {
                    // Cas 2 : Le joueur est vivant ET il n'y a plus d'ennemis, c'est une victoire.
                    gameView.getGameOverPanel().showFinalMessage("VICTOIRE !");
                    gameView.showPanel(GameView.GAME_OVER_PANEL);

                } else {
                    // Cas 3 : Le joueur est vivant mais il reste des ennemis.
                    // On lance le combat suivant.
                    startNextCombat();
                }

            }
            // Note: Le CombatPanel (la vue de combat) devra s'abonner aux changements
            // de sous-état (PlayerTurnState/EnemyTurnState) pour mettre à jour l'affichage.

        }
    }




    public void setState(GameState newState) {
        this.currentState = newState;
    }

    /**
     * Reçoit une entrée brute (d'un scanner, d'un bouton Swing, etc.)
     * et la délègue à l'état de jeu actuel pour traitement.
     *
     * @param input L'entrée de l'utilisateur.
     */
    public void processPlayerInput(String input) {


        if (currentState instanceof PlayingState) {
            GameState turnState = ((PlayingState) currentState).getCurrentTurnState();
            if (turnState instanceof PlayerTurnState) {
                ((PlayerTurnState) turnState).processInput(this, input);
            }
        } else if(currentState instanceof MenuState){
            GameState turnState =((MenuState) currentState);
            System.out.println("turnState " +turnState);
            ((MenuState) currentState).processInput(this, input);
        }
    }
    /**
     * Exécute une commande du joueur et fait avancer le jeu au tour suivant.
     * C'est une méthode clé qui centralise la logique post-action.
     *
     * @param command La commande à exécuter (AttackCommand, DefendCommand, etc.).
     */

    public void executePlayerCommand(Command command) {


        // 1. Exécute l'action du joueur.
        command.execute();

        // 2. Vérifie si le joueur a gagné.
        if (getCurrentEnemy().isDead()) {
            setState(new EndState());
            handleState();
            return; // Le combat est fini.
        }

        if (!(this.currentState instanceof PlayingState)) {
            return;
        }

        this.runTurn();


    }

    public boolean startNextCombat() {
        if (enemyProgression == null || enemyProgression.isEmpty()) {
            return false; // Plus d'ennemis
        }
        this.currentEnemy = enemyProgression.poll();

        System.out.println("\nUn nouveau combat commence ! Vous affrontez : " + this.currentEnemy.getName());
        setState(new PlayingState());
        handleState();
        return true;
    }

    /**
     * Gère la séquence complète du tour de l'ennemi.
     */
    private void runTurn() {

        if (!(this.currentState instanceof PlayingState)) {
            return;
        }
        // 1. Prépare et exécute le tour de l'ennemi.
        PlayingState playing = (PlayingState) this.currentState;
        playing.setTurnState(new EnemyTurnState());
        handleState(); // Appelle EnemyTurnState.handle()

        // 2. Vérifie si l'ennemi a gagné.
        if (getPlayer().isDead()) {
            setState(new EndState());
            handleState();
            return; // Stop.
        }

        for (ActiveEffect effect : playerEffects) {
            System.out.println("==========effect"+effect);

            effect.tick();
        }
        // On enlève les effets expirés
        playerEffects.removeIf(ActiveEffect::isExpired);


        // 3. Si le combat n'est pas fini, on prépare le tour du joueur suivant
        // et on attend sa prochaine commande.
        playing.setTurnState(new PlayerTurnState());
        handleState(); // Appelle PlayerTurnState.handle() pour afficher les infos.
    }

    public void setEnemyProgression(Queue<Character> progression) {
        this.enemyProgression = progression;
    }


    /**
     * Prépare et lance un nouveau combat.
     * @param enemyType Le type d'ennemi à créer via la Factory.
     */
    public void startNewCombat(EnemyFactory.EnemyType enemyType) {
        // 1. Crée un nouvel ennemi en utilisant la Factory.
        System.out.println("\nUn nouveau combat commence !");
        this.currentEnemy = EnemyFactory.createEnemy(enemyType);
        System.out.println("Vous affrontez un " + this.currentEnemy.getName() + " !");

        // 2. Met le jeu dans l'état "en combat".
        setState(new PlayingState());

        // 3. Exécute la logique du nouvel état (qui est PlayingState).
        // PlayingState va à son tour appeler PlayerTurnState, qui affichera les infos du tour.
        handleState();
    }



    /**
     * Retourne le personnage du joueur avec tous ses effets actifs appliqués.
     * C'est une version "effective" et temporaire du joueur.
     *
     * @return Un objet Character représentant le joueur décoré.
     */
    public Character getEffectivePlayer() {
        Character effectivePlayer = this.player;
        for (ActiveEffect effect : playerEffects) {
            effectivePlayer = effect.apply(effectivePlayer);
            System.out.println("effectivePlayer oookkkkk " + effectivePlayer.getName());
        }
        return effectivePlayer;
    }


    /**
     * Retourne l'ennemi actuel avec tous ses effets actifs appliqués.
     * @return Un objet Character représentant l'ennemi décoré.
     */
    public Character getEffectiveEnemy() {
        Character effectiveEnemy = this.currentEnemy;
        for (ActiveEffect effect : enemyEffects) {
            effectiveEnemy = effect.apply(effectiveEnemy);
        }
        return effectiveEnemy;
    }
    public Player getPlayer() {
        return player;
    }

    public Character getCurrentEnemy() {
        return currentEnemy;
    }

    public GameState getCurrentState() {
        return currentState;
    }


    public GameView getGameView() {
        return gameView;
    }

    public void reset() {
        // 1. Réinitialiser le joueur (vie, effets, etc.)
        this.player.reset(); // Nous allons créer cette méthode dans Player

        // 2. Vider la liste des effets du contrôleur
        this.playerEffects.clear();
        this.enemyEffects.clear();

        // 3. Recréer la progression des ennemis
       // this.initializeEnemyProgression();

        // 4. Remettre le jeu dans l'état du menu
        setState(new MenuState());
        handleState();
    }
}
