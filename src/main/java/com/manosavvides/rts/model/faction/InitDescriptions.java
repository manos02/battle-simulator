package com.manosavvides.rts.model.faction;

import java.util.ArrayList;
import java.util.List;

/**
 * A model for the description of the units.
 */
public class InitDescriptions {
    private List<String> menUnits;
    private List<String> elvesUnits;
    private List<String> dwarvesUnits;
    private List<String> mordorUnits;
    private List<String> isengardUnits;

    /**
     * initialise the descriptions of all the categories of factions.
     */
    public InitDescriptions() {
        initDwarvesUnits();
        initElvesUnits();
        initIsengardUnits();
        initMenUnits();
        initMordorUnits();
    }

    /**
     * initialise the description for menUnits.
     */
    public void initMenUnits() {
        menUnits = new ArrayList<>();
        menUnits.add("Gondor Soldier");
        menUnits.add("Tower Guard");
        menUnits.add("Ithilien Ranger");
    }

    /**
     * initialise the description for elvesUnits.
     */
    public void initElvesUnits() {
        elvesUnits = new ArrayList<>();
        elvesUnits.add("Lorien Warrior");
        elvesUnits.add("Mirkwood Archer");
        elvesUnits.add("Rivendell Lancer");
    }

    /**
     * initialise the descriptions for dwarvesUnits.
     */
    public void initDwarvesUnits() {
        dwarvesUnits = new ArrayList<>();
        dwarvesUnits.add("Guardian");
        dwarvesUnits.add("Phalanx");
        dwarvesUnits.add("Axe Thrower");
    }

    /**
     * initialise the descriptions for mordorUnits.
     */
    public void initMordorUnits() {
        mordorUnits = new ArrayList<>();
        mordorUnits.add("Orc Warrior");
        mordorUnits.add("Orc Pikeman");
        mordorUnits.add("Haradrim archer");
    }

    /**
     * initialise the descriptions for isengardUnits.
     */
    public void initIsengardUnits() {
        isengardUnits = new ArrayList<>();
        isengardUnits.add("Uruk-hai");
        isengardUnits.add("Uruk Crossbowman");
        isengardUnits.add("Warg Rider");
    }

    public List<String> getMenUnits() {
        return menUnits;
    }

    public List<String> getElvesUnits() {
        return elvesUnits;
    }

    public List<String> getDwarvesUnits() {
        return dwarvesUnits;
    }

    public List<String> getMordorUnits() {
        return mordorUnits;
    }

    public List<String> getIsengardUnits() {
        return isengardUnits;
    }
}
