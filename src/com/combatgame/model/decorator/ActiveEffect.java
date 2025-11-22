package com.combatgame.model.decorator;

import com.combatgame.model.Character;

/**
 * Représente un effet temporaire appliqué à un personnage.
 */
public class ActiveEffect {
    private final CharacterDecorator decorator;
    private int duration;

    public ActiveEffect(CharacterDecorator decorator, int duration) {
        this.decorator = decorator;
        this.duration = duration;
    }

    public Character apply(Character character) {
        decorator.setDecoratedCharacter(character); // Applique le décorateur sur le personnage actuel
        return decorator;
    }

    public void tick() {
        System.out.println("============= duration : "+duration);
        this.duration--;
    }

    public boolean isExpired() {
        return duration <= 0;
    }
}
