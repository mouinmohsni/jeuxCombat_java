package com.combatgame.model.items.potions;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.model.items.Potion;

/**
 * Petite potion - Soigne peu mais pas chère
 */
public class SmallPotion extends Potion {
    
    public SmallPotion() {
        super(
            "Petite Potion",
            10,
            1,
            "Une petite potion de soin",
            20
        );
    }

    @Override
    public void use(GameController controller, Character target) {

    }
}
