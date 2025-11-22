package com.combatgame.model.enemy.composite; // Ou un autre package approprié

import com.combatgame.model.Character;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un groupe d'ennemis qui se comporte comme un seul Character.
 * C'est l'implémentation du Pattern Composite.
 */
public class EnemyGroup extends Character {

    private List<Character> enemies = new ArrayList<>();

    public EnemyGroup(String name) {
        // Le groupe lui-même n'a pas de stats. Il délègue tout à ses membres.
        super(name, 0, 0, 0,"../../image/roi");
    }

    public void add(Character enemy) {
        this.enemies.add(enemy);
    }

    public void remove(Character enemy) {
        this.enemies.remove(enemy);
    }


    /**
     * Quand le groupe est attaqué, un de ses membres subit les dégâts.
     * Stratégie simple : le premier membre vivant de la liste est ciblé.
     */
    @Override
    public void takeDamage(int damage) {
        if (!isDead()) {
            // On cible toujours le premier ennemi de la liste (le "tank").
            enemies.get(0).takeDamage(damage);
            // On vérifie si l'ennemi ciblé est mort et on le retire du groupe si c'est le cas.
            if (enemies.get(0).isDead()) {
                System.out.println(enemies.get(0).getName() + " a été vaincu !");
                enemies.remove(0);
            }
        }
    }

    /**
     * Quand le groupe attaque, chaque membre attaque à son tour.
     */
    @Override
    public void attack(Character target) {
        System.out.println("\nLe groupe '" + this.getName() + "' attaque !");
        for (Character member : enemies) {
            member.attack(target);
        }
    }

    /**
     * Le groupe est considéré comme mort s'il n'a plus de membres.
     */
    @Override
    public boolean isDead() {
        return enemies.isEmpty();
    }

    /**
     * La vie du groupe est la somme des vies de ses membres.
     */
    @Override
    public int getHealth() {
        return enemies.stream().mapToInt(Character::getHealth).sum();
    }

    /**
     * La vie maximale du groupe est la somme des vies maximales de ses membres.
     */
    @Override
    public int getMaxHealth() {
        return enemies.stream().mapToInt(Character::getMaxHealth).sum();
    }

    // n'a pas beaucoup de sens, car chaque membre attaque individuellement.
    // On peut les laisser à 0.
    @Override
    public int getAttack() { return 0; }

    @Override
    public int getDefense() { return 0; }
}
