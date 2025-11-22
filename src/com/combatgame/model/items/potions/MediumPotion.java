package com.combatgame.model.items.potions;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.model.items.Potion;

/**
 * Potion moyenne - Bon équilibre prix/efficacité
 */
public class MediumPotion extends Potion {
    
    public MediumPotion() {
        super(
            "Potion Moyenne",
            25,
            2,
            "Une potion de soin moyenne",
            50
        );
    }

    @Override
    public void use(GameController controller, Character target) {

    }
}
