package com.combatgame.model.items;

import com.combatgame.model.Character;

/**
 * Classe abstraite représentant une arme
 * Les armes augmentent l'attaque du personnage
 */
public abstract class Weapon extends Item {
    protected int damageBonus;
    
    public Weapon(String name, int price, int level, String description, int damageBonus) {
        super(name, price, level, description);
        this.damageBonus = damageBonus;
    }
    

    public void use(Character target) {
        // Les armes ne s'utilisent pas directement, elles sont équipées
        System.out.println(target.getName() + " équipe " + name);
    }
    
    public int getDamageBonus() {
        return damageBonus;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [+" + damageBonus + " ATK]";
    }
}
