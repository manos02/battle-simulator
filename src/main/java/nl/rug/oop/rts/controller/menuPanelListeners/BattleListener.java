package nl.rug.oop.rts.controller.menuPanelListeners;

import nl.rug.oop.rts.model.Battle;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A model for pressing the battle button.
 */
public class BattleListener implements ActionListener {

    private final Graph graph;
    private final Simulation simulation;

    public BattleListener(Graph graph, Simulation simulation) {
        this.graph = graph;
        this.simulation = simulation;
    }

    /**
     * checks the state of the simulation to check if the armies are at the edge or node.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Battle battle = new Battle(graph);
        if (simulation.getState() == 0) {
            battle.armyNodeBattle();
        } else {
            battle.armyEdgeBattle();
        }
        graph.notifyListeners();
    }
}
