package com.combatgame.model.decorator;
 // Ou un sous-package comme com.combatgame.model.decorator

import com.combatgame.model.Character;

/**
 * Classe abstraite de base pour tous les décorateurs de personnages.
 * Elle hérite de Character pour pouvoir être utilisée partout où un Character est attendu.
 */
public abstract class CharacterDecorator extends Character {

    protected com.combatgame.model.Character decoratedCharacter;

    public CharacterDecorator(Character decoratedCharacter) {
        // On ne passe pas de stats au constructeur parent car le décorateur
        // va déléguer les appels à l'objet qu'il décore.
        super(decoratedCharacter.getName(), 0, 0, 0,"../images/roi.png");
        this.decoratedCharacter = decoratedCharacter;
    }

    // On délègue tous les appels à l'objet décoré.
    // Les décorateurs concrets surchargeront uniquement les méthodes qu'ils doivent modifier.

    @Override
    public int getHealth() {
        return decoratedCharacter.getHealth();
    }

    @Override
    public void attack(Character target) {
        // SOLUTION : Le décorateur exécute l'attaque lui-même.
        // Il utilise ses propres méthodes qui sont déjà surchargées.

        // 1. On récupère la valeur de l'attaque. Puisque "this" est un décorateur,
        //    cet appel va correctement remonter jusqu'à la méthode getAttack() de AttackDecorator.
        int totalAttack = this.getAttack();

        // 2. On exécute la logique de l'attaque.
        System.out.println(this.getName() + " attaque " + target.getName() + " !");
        target.takeDamage(totalAttack);
    }


    @Override
    public int getMaxHealth() {
        return decoratedCharacter.getMaxHealth();
    }

    @Override
    public int getAttack() {
        return decoratedCharacter.getAttack();
    }

    @Override
    public int getDefense() {
        return decoratedCharacter.getDefense();
    }

    @Override
    public void takeDamage(int damage) {
        decoratedCharacter.takeDamage(damage);
    }

    public void setDecoratedCharacter(Character character) {
        this.decoratedCharacter = character;
    }
}
