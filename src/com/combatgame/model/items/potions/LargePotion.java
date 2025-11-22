package com.combatgame.model.items.potions;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.model.items.Potion;

/**
 * Grande potion - Soigne beaucoup mais chère
 */
public class LargePotion extends Potion {
    
    public LargePotion() {
        super(
            "Grande Potion",
            50,
            3,
            "Une grande potion de soin puissante",
            100
        );
    }

    @Override
    public void use(GameController controller, Character target) {

    }
}
