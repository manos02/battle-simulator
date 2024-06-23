package nl.rug.oop.rts.controller.buttonMenuListeners;

import nl.rug.oop.rts.model.faction.InitDescriptions;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A model for pressing the addArmy button.
 */
public class AddArmyListener implements ActionListener {
    private final Graph graph;
    private final InitDescriptions initDescriptions;

    public AddArmyListener(Graph graph) {
        this.graph = graph;
        this.initDescriptions = new InitDescriptions();
    }

    /**
     * The user chooses which army to choose.
     *
     * @param e the event to be processed/
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSourceNode() != null) {
            String[] factions = {"men(1)", "elves(1)", "dwarves(1)", "mordor(2)", "isengard(2)"};
            String selectedFaction = (String) JOptionPane.showInputDialog(null, "Select a faction:",
                    "Add Army", JOptionPane.PLAIN_MESSAGE, null,
                    factions, factions[0]);
            if (selectedFaction == null) {
                return;  // User pressed "Cancel"
            }
            List<String> unitNames;
            int team;
            switch (selectedFaction) {
                case "men(1)" -> {
                    unitNames = initDescriptions.getMenUnits();
                    team = 1;
                }
                case "elves(1)" -> {
                    unitNames = initDescriptions.getElvesUnits();
                    team = 1;
                }
                case "dwarves(1)" -> {
                    unitNames = initDescriptions.getDwarvesUnits();
                    team = 1;
                }
                case "mordor(2)" -> {
                    unitNames = initDescriptions.getMordorUnits();
                    team = 2;
                }
                case "isengard(2)" -> {
                    unitNames = initDescriptions.getIsengardUnits();
                    team = 2;
                }
                default -> {
                    return;
                }
            }
            graph.getSourceNode().setArmy(selectedFaction, unitNames, team);  // set the selected army to the node
            graph.notifyListeners();
        }
    }
}
