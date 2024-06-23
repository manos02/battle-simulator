package com.manosavvides.rts.model.faction;

import com.manosavvides.rts.model.Node;
import com.manosavvides.rts.model.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A model for the army.
 */
public class Army {
    private final List<Unit> units;
    private final int team;
    private final String faction;
    private Node node;
    private final List<String> unitNames;

    /**
     * constructor of the army.
     *
     * @param faction of the army.
     * @param unitNames of the particular army.
     * @param team of the army.
     * @param node that the army is placed to.
     */
    public Army(String faction, List<String> unitNames, int team, Node node) {
        this.faction = faction;
        this.unitNames = unitNames;
        this.units = new ArrayList<>();
        this.team = team;
        this.node = node;
        addUnits();
    }

    /**
     * A method to add 40-50 units to the army with health/damage between 80-100.
     */
    public void addUnits() {
        Random random = new Random();
        int numUnits = random.nextInt(41) + 10; // Generate a random number between 10 and 50

        for (int i = 0; i < numUnits; i++) {
            String randomUnitName = unitNames.get(random.nextInt(unitNames.size()));
            int randomDamage = random.nextInt(21) + 80;
            int randomHealth = random.nextInt(21) + 80;
            Unit unit = new Unit(randomDamage, randomHealth, randomUnitName);
            units.add(unit);
        }
    }

    /**
     * remove a random unit from the army.
     */
    public void removeRandomUnit() {
        Random random = new Random();
        int randomIndex = random.nextInt(units.size());
        units.remove(randomIndex);
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public int getTeam() {
        return team;
    }

    public String getFaction() {
        return faction;
    }

    public List<String> getUnitNames() {
        return unitNames;
    }

}
