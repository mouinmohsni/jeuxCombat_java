package com.combatgame.model.items.weapons;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.model.items.Weapon;

/**
 * Arc - Arme rapide mais moins puissante
 */
public class Bow extends Weapon {
    
    public Bow(int level) {
        super(
            "Arc niveau " + level,
            calculatePrice(level),
            level,
            "Un arc léger et rapide",
            calculateDamage(level)
        );
    }
    
    private static int calculatePrice(int level) {
        return 40 * level;
    }
    
    private static int calculateDamage(int level) {
        return 8 * level;
    }

    @Override
    public void use(GameController controller, Character target) {

    }
}
