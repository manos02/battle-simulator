package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.faction.Army;
import nl.rug.oop.rts.model.Unit;

import java.util.Random;

/**
 * A model for the Reinforcements event.
 */
public class ReinforcementsEvent extends Event {

    public ReinforcementsEvent(String type) {
        super(type);
    }

    /**
     * add extra units to the army.
     *
     * @param army to process the event.
     */
    @Override
    public void act(Army army) {
        Random random = new Random();
        int extraUnits = random.nextInt(11) + 5;
        for (int i = 0; i < extraUnits; i++) {
            String randomUnitName = army.getUnitNames().get(random.nextInt(army.getUnitNames().size()));
            int randomDamage = random.nextInt(21) + 80;
            int randomHealth = random.nextInt(21) + 80;
            Unit unit = new Unit(randomDamage, randomHealth, randomUnitName);
            army.addUnit(unit);
        }
    }
}
