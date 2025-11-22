package com.combatgame.state;

import com.combatgame.model.Enemy;
import com.combatgame.model.Player;
import com.combatgame.model.ai.AIAction;

/**
 * Interface pour le pattern State/Strategy, définissant le comportement de l'IA d'un ennemi.
 * Chaque implémentation représentera une "stratégie" ou un "état mental" différent.
 */
public interface EnemyAIState {

    /**
     * Exécute l'action de l'ennemi en fonction de la stratégie actuelle.
     * @param self La référence à l'ennemi lui-même, pour qu'il puisse agir (attaquer, défendre).
     * @param target La cible de l'ennemi (le joueur).
     */
    AIAction executeAction(Enemy self, Player target);
}
