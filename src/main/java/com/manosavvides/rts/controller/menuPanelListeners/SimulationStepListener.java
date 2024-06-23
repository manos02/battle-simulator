package com.manosavvides.rts.controller.menuPanelListeners;

import com.manosavvides.rts.model.Simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A model for the pressing the simulation button.
 */
public class SimulationStepListener implements ActionListener {
    private final Simulation simulation;

    public SimulationStepListener(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * checks the state of the simulation to check if the armies are at the edge or node.
     *
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (simulation.getState() == 0) {
            simulation.nodeToEdgeSimulation();
        } else {
            simulation.edgeToNodeSimulation();
        }

    }
}
