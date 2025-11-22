package com.combatgame.model;

import com.combatgame.controller.GameController;
import com.combatgame.model.items.Item;
import com.combatgame.model.items.Potion;
import com.combatgame.model.items.Weapon;
import com.combatgame.model.items.potions.StrengthPotion;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character{


     private int gold ;
     private Weapon equippedWeapon ;
     private List<Item> inventory ;
     private String imagePath ;

    public Player(String name) {
        super(name, 100, 20, 5,"../images/player.png");
        this.gold = 100;
        this.inventory = new ArrayList<>();
        this.inventory.add(new StrengthPotion());
        this.imagePath=imagePath;

    }


    /**
     * Acheter un item
     * @return true si l'achat a réussi, false sinon
     */
    public boolean buyItem(Item item) {
        if (gold >= item.getPrice()) {
            gold -= item.getPrice();
            inventory.add(item);
            return true;
        }
        return false;
    }

    /**
     * Vendre un item
     * @return true si la vente a réussi, false sinon
     */
    public boolean sellItem(Item item) {
        if (inventory.contains(item)) {
            int sellPrice = item.getPrice() / 2;
            gold += sellPrice;
            inventory.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Utilise un item de l'inventaire.
     * @param item L'item à utiliser.
     * @param controller Le contrôleur de jeu, nécessaire pour les items avec effets.
     * @return true si l'utilisation a réussi, false sinon.
     */
    public boolean useItem(Item item, GameController controller) {
        if (!inventory.contains(item)) {
            return false; // L'item n'est pas dans l'inventaire.
        }

        // On appelle la méthode use de l'item, en lui passant le contrôleur.
        item.use(controller, this);

        // Si l'item est consommable (comme une potion), on le retire de l'inventaire.
        // On peut ajouter une méthode isConsumable() à la classe Item pour gérer ça proprement.
        if (item instanceof Potion || item instanceof StrengthPotion) {
            inventory.remove(item);
        }

        return true;
    }

    /**
     * Équiper une arme
     * @return true si l'équipement a réussi
     */

    public boolean equipWeapon(Weapon weapon) {
        if (inventory.contains(weapon) || weapon == null) {
            this.equippedWeapon = weapon;
            return true;
        }
        return false;
    }

    public int getGold() {
        return gold;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void reset() {
        this.health = this.maxHealth; // Restaure la vie
        this.isDefending = false; // Annule la défense
        // Vous pourriez aussi réinitialiser l'inventaire si nécessaire
        // this.inventory.clear();
    }
}

