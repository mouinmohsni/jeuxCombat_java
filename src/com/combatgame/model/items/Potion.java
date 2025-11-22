package com.combatgame.model.items;

import com.combatgame.model.Character;

/**
 * Classe abstraite représentant une potion
 * Les potions soignent le personnage
 */
public abstract class Potion extends Item {
    protected int healAmount;
    
    public Potion(String name, int price, int level, String description, int healAmount) {
        super(name, price, level, description);
        this.healAmount = healAmount;
    }
    

    public void use(Character target) {
        target.heal(healAmount);
        System.out.println(target.getName() + " utilise " + name + " et récupère " + healAmount + " HP");
    }
    
    public int getHealAmount() {
        return healAmount;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [+" + healAmount + " HP]";
    }
}
