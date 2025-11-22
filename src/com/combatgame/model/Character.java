package com.combatgame.model;

import com.combatgame.model.items.Weapon;

/**
 * Classe abstraite représentant un personnage (joueur ou ennemi)
 */
public abstract class Character {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected int defense;
    protected boolean isDefending;
    protected Weapon equippedWeapon;
    protected String imagePath;


    public Character(String name, int maxHealth, int attack, int defense,String imagePath) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.isDefending = false;
        this.equippedWeapon = null;
        this.imagePath = imagePath;
    }

    /**
     * Le personnage subit des dégâts
     */
    public void takeDamage(int damage) {
        int actualDamage = damage - defense;
        if (isDefending) {
            actualDamage = actualDamage / 2;
        }
        if (actualDamage < 0) {
            actualDamage = 0;
        }
        health -= actualDamage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " subit " + actualDamage + " dégâts (HP: " + health + "/" + maxHealth + ")");
        isDefending = false; // La défense ne dure qu'un tour
    }

    /**
     * Le personnage se soigne
     */
    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    /**
     * Le personnage se met en défense
     */
    public void defend() {
        isDefending = true;
        System.out.println(name + " se met en défense");
    }


    /**
     * Calculer l'attaque totale (attaque de base + bonus d'arme)
     */
    public int getTotalAttack() {
        int totalAttack = attack;
        if (equippedWeapon != null) {
            totalAttack += equippedWeapon.getDamageBonus();
        }
        System.out.println("total attaque " +totalAttack );
        return totalAttack;
    }

    /**
     * Attaque un autre personnage.
     * Calcule les dégâts totaux et les applique à la cible.
     *
     * @param target Le personnage à attaquer.
     */
    public void attack(Character target) {
        // Vérification pour ne pas attaquer une cible déjà morte
        if (target.isDead()) {
            System.out.println("Inutile d'attaquer, " + target.getName() + " est déjà hors de combat.");
            return;
        }

        System.out.println(this.name + " attaque " + target.getName() + " !");

        // 1. Calcule les dégâts en utilisant la méthode que vous avez déjà !
        int damageDealt = this.getTotalAttack();

        // 2. Applique les dégâts à la cible en utilisant une autre de vos méthodes !
        target.takeDamage(damageDealt);
    }

    /**
     * Vérifie si le personnage est mort
     */
    public boolean isDead() {
        return health <= 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isDefending() {
        return isDefending;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
}
