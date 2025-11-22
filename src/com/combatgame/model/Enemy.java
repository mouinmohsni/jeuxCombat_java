package com.combatgame.model;

import com.combatgame.model.ai.AIAction;
import com.combatgame.model.ai.AggressiveState;
import com.combatgame.model.ai.DefensiveState;
import com.combatgame.state.EnemyAIState;

/**
 * Classe représentant un ennemi. Elle peut être étendue par des types spécifiques (Warrior, Mage...).
 * Elle contient la logique de décision (IA) basée sur un pattern State interne.
 */
public abstract class Enemy extends Character {

    private EnemyAIState aiState;
    private String imagePath;

    public Enemy(String name, int maxHealth, int attack, int defense,String imagePath) {
        super(name, maxHealth, attack, defense, imagePath);
        // Par défaut, un ennemi commence avec une stratégie agressive.
        this.aiState = new AggressiveState();
        this.imagePath = imagePath;
    }

    /**
     * Le point d'entrée public pour l'action de l'ennemi.
     * Il délègue la décision à son état d'IA, puis exécute l'action choisie.
     */
    public void performAction(Player playerTarget) {
        // 1. L'ennemi demande à son état d'IA de PRENDRE UNE DÉCISION.
        AIAction chosenAction = this.aiState.executeAction(this, playerTarget);

        // 2. L'ennemi EXÉCUTE LUI-MÊME cette décision.
        switch (chosenAction) {
            case ATTACK:
                this.attack(playerTarget);
                break;
            case DEFEND:
                this.defend();
                break;
        }

        // 3. L'ennemi met à jour son état si nécessaire.
        updateAIState();
    }

    /**
     * Met à jour l'état de l'IA en fonction de la situation.
     * C'est ici que la dynamique du combat se joue.
     */
    private void updateAIState() {
        boolean isLowHealth = (double) this.getHealth() / this.getMaxHealth() < 0.3;

        // Si l'ennemi est bas en vie et n'est pas déjà en mode défensif, il le devient.
        if (isLowHealth && !(aiState instanceof DefensiveState)) {
            System.out.println(this.getName() + " devient prudent !");
            this.setAiState(new DefensiveState());
        }
        // Si l'ennemi a récupéré de la vie et était en mode défensif, il redevient agressif.
        else if (!isLowHealth && (aiState instanceof DefensiveState)) {
            System.out.println(this.getName() + " reprend confiance et redevient agressif !");
            this.setAiState(new AggressiveState());
        }
    }

    // Permet de changer l'état de l'IA de l'extérieur si besoin.
    public void setAiState(EnemyAIState newState) {
        this.aiState = newState;
    }
}