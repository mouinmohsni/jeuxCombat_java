package com.combatgame.model.ai;
import com.combatgame.model.Enemy;
import com.combatgame.model.Player;
import java.util.Random;

import com.combatgame.state.EnemyAIState;


public class DefensiveState implements EnemyAIState {
    private Random random = new Random();

    @Override
    public AIAction executeAction(Enemy self, Player target) {
        // 60% de chance de se défendre, 40% de chance d'attaquer
        if (random.nextInt(100) < 60) {
            return AIAction.DEFEND;
        } else {
            return AIAction.ATTACK;
        }
    }
}
