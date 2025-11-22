package com.combatgame.model.items;

import com.combatgame.controller.GameController;
import com.combatgame.model.Character;

/**
 * Classe abstraite représentant un objet dans le jeu
 * Tous les items (armes, potions, etc.) héritent de cette classe
 */
public abstract class Item {
    protected String name;
    protected int price;
    protected int level;
    protected String description;
    
    public Item(String name, int price, int level, String description) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.description = description;
    }
    
    /**
     * Méthode abstraite pour utiliser l'item
     * Chaque type d'item implémente son propre comportement
     */
    public abstract void use(GameController controller, Character target);


    // Getters
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getLevel() {
        return level;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return name + " (Niveau " + level + ") - " + price + " gold";
    }
}
