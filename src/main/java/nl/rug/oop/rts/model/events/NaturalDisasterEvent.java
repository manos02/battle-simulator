package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.faction.Army;

import java.util.Random;

/**
 * A model for the NaturalDisaster event.
 */
public class NaturalDisasterEvent extends Event {

    public NaturalDisasterEvent(String type) {
        super(type);
    }

    /**
     * remove some units from the army.
     *
     * @param army to process the event.
     */
    @Override
    public void act(Army army) {
        Random random = new Random();
        int removeUnits = random.nextInt(army.getUnits().size() / 2); // can lose up to half the units.
        for (int i = 0; i < removeUnits; i++) {
            army.removeRandomUnit();
        }
    }
}
