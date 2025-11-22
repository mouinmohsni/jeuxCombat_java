package com.combatgame.model.items.weapons;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.model.items.Weapon;

/**
 * Épée - Arme équilibrée
 */
public class Sword extends Weapon {

    @Override
    public void use(GameController controller, Character target) {

    }

    public Sword(int level) {
        super(
            "Épée niveau " + level,
            calculatePrice(level),
            level,
            "Une épée bien équilibrée",
            calculateDamage(level)
        );
    }
    
    private static int calculatePrice(int level) {
        return 50 * level;
    }
    
    private static int calculateDamage(int level) {
        return 10 * level;
    }
}
