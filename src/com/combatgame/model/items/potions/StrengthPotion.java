package com.combatgame.model.items.potions;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;
import com.combatgame.model.Player;
import com.combatgame.model.decorator.ActiveEffect;
import com.combatgame.model.decorator.AttackDecorator;
import com.combatgame.model.items.Item;

/**
 * Une potion qui applique un bonus d'attaque temporaire au joueur.
 */
public class StrengthPotion extends Item {

    private static final int ATTACK_BONUS = 500;
    private static final int DURATION = 5; // L'effet dure 5 tours

    /**
     * Constructeur de la Potion de Force.
     * Appelle le constructeur de la classe parente Item avec des valeurs prédéfinies.
     */
    public StrengthPotion() {
        // On respecte le constructeur de la classe Item que vous avez définie.
        super("Potion de Force", 50, 1, "Augmente l'attaque de 15 points pendant 5 tours.");
    }

    /**
     * Applique l'effet de force au joueur en utilisant le GameController.
     * @param controller Le contrôleur de jeu pour gérer l'effet.
     * @param target Le personnage qui utilise la potion.
     */
    @Override
    public void use(GameController controller, Character target) {
        if (target instanceof Player) {
            System.out.println(target.getName() + " boit une " + this.getName() + " ! Son attaque augmente !");

            // Crée le décorateur et l'effet temporaire.
            AttackDecorator attackDecorator = new AttackDecorator(target, ATTACK_BONUS);
            ActiveEffect effect = new ActiveEffect(attackDecorator, DURATION);

            // Demande au contrôleur de suivre cet effet.
            controller.addEffectToPlayer(effect);

        } else {
            System.out.println("Cet objet ne peut être utilisé que par le joueur.");
        }
    }
}
