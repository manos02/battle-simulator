package com.manosavvides.rts.model;

/**
 * A model for the unit.
 */
public class Unit {
    private int damage;
    private final int health;
    private final String name;

    /**
     * constructor for the unit.
     *
     * @param damage of the unit.
     * @param health of the unit.
     * @param name of the unit.
     */
    public Unit(int damage, int health, String name) {
        this.damage = damage;
        this.health = health;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }
}
