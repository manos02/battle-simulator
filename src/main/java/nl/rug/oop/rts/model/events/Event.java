package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.faction.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * A parent class for all the type of events.
 */
public abstract class Event {
    private final List<Army> affectedArmies;
    private final String type;

    public Event(String type) {
        this.affectedArmies = new ArrayList<>();
        this.type = type;
    }

    public abstract void act(Army army);

    public String getType() {
        return type;
    }

    public void addAffectedArmy(Army army) {
        affectedArmies.add(army);
    }

    public List<Army> getAffectedArmies() {
        return affectedArmies;
    }

}
