package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.faction.Army;
import nl.rug.oop.rts.model.Unit;

import java.util.Random;

/**
 * A model for the HiddenWeaponry event.
 */
public class HiddenWeaponryEvent extends Event {

    public HiddenWeaponryEvent(String type) {
        super(type);
    }

    /**
     * Increase the damage each unit of the army.
     *
     * @param army to process the event.
     */
    @Override
    public void act(Army army) {
        Random random = new Random();
        for (Unit unit : army.getUnits()) {
            int increaseDamage = random.nextInt(5);
            unit.setDamage(unit.getDamage() + increaseDamage);
        }
    }
}
