package com.combatgame.model.items.weapons;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.model.items.Weapon;

/**
 * Hache - Arme puissante mais chère
 */
public class Axe extends Weapon {
    
    public Axe(int level) {
        super(
            "Hache niveau " + level,
            calculatePrice(level),
            level,
            "Une hache lourde et puissante",
            calculateDamage(level)
        );
    }
    
    private static int calculatePrice(int level) {
        return 75 * level;
    }
    
    private static int calculateDamage(int level) {
        return 15 * level;
    }

    @Override
    public void use(GameController controller, Character target) {

    }
}
