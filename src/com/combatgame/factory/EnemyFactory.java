package com.combatgame.factory;

import com.combatgame.model.Enemy;
import com.combatgame.model.Enemy;
import com.combatgame.model.enemy.Warrior;
import com.combatgame.model.enemy.Mage;
import com.combatgame.model.enemy.Archer;

public class EnemyFactory {
    public enum EnemyType {
        WARRIOR,
        MAGE,
        ARCHER
    }

    public static Enemy createEnemy(EnemyType type) {
        switch (type) {
            case WARRIOR:
                return new Warrior();
            case MAGE:
                return new Mage();
            case ARCHER:
                return new Archer();
            default:
                throw new IllegalArgumentException("Type d'ennemi inconnu : " + type);
        }
    }
}
