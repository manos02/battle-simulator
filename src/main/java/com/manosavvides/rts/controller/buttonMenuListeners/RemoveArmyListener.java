package com.manosavvides.rts.controller.buttonMenuListeners;

import com.manosavvides.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A model for pressing the removeArmy  button.
 */
public class RemoveArmyListener implements ActionListener {
    private final Graph graph;

    public RemoveArmyListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * remove the last army added to the node.
     *
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSourceNode() != null) {
            graph.getSourceNode().removeArmyFromNode(null);
            graph.notifyListeners();
        }
    }
}
