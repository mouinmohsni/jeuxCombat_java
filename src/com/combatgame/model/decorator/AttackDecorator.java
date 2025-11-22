package com.combatgame.model.decorator;

import com.combatgame.model.Character;

/**
 * Un décorateur qui augmente la statistique d'attaque d'un personnage.
 */
public class AttackDecorator extends CharacterDecorator {

    private int attackBonus;

    public AttackDecorator(Character decoratedCharacter, int attackBonus) {
        super(decoratedCharacter);
        this.attackBonus = attackBonus;
    }

    /**
     * C'est ici que la "magie" opère.
     * Au lieu de retourner simplement l'attaque du personnage décoré,
     * on y ajoute notre bonus.
     */
    @Override
    public int getAttack() {
        System.out.println(decoratedCharacter.getName() + " attaque augmente ! de " + attackBonus + " !");
        return decoratedCharacter.getAttack() + this.attackBonus;
    }
}