package com.combatgame.model.ai;

import com.combatgame.model.Enemy;
import com.combatgame.model.Player;
import com.combatgame.state.EnemyAIState;

import java.util.Random;

public class AggressiveState implements EnemyAIState {

    private Random random = new Random();

    @Override
    public AIAction  executeAction(Enemy self, Player target) {
        // 90% de chance d'attaquer, 10% de se défendre
        if (random.nextInt(100) < 90) {
            return AIAction.ATTACK;
        } else {
            return AIAction.DEFEND;
        }
    }
}
