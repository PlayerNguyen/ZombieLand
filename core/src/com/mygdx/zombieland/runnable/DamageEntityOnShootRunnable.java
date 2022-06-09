package com.mygdx.zombieland.runnable;

import com.mygdx.zombieland.entity.DamageSource;
import com.mygdx.zombieland.entity.Damageable;
import com.mygdx.zombieland.entity.Entity;

import java.util.LinkedList;
import java.util.Set;

public class DamageEntityOnShootRunnable implements Runnable {

    private final DamageSource source;
    private final float amount;
    private final LinkedList<Entity> entities;


    public DamageEntityOnShootRunnable(DamageSource source, float amount, Set<Entity> entities) {
        this.source = source;
        this.amount = amount;
        this.entities = new LinkedList<>(entities);
    }

    @Override
    public void run() {
        if (!entities.isEmpty()) {
            Entity entity = entities.removeFirst();
            ((Damageable) entity).damage(this.source, this.amount);
//            if (entity instanceof Zombie && ((Zombie)entity).getSpeed() == 0) {
//                ((Zombie)entity).setSpeed(((Zombie) entity).getType().getSpeed());
//            }
        }
    }
}
