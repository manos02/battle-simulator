package com.manosavvides.rts.controller.menuPanelListeners;

import com.manosavvides.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A model to add an edge to the graph.
 */
public class AddEdgeListener implements ActionListener {

    private final Graph graph;

    public AddEdgeListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * Checks if 2 nodes are selected to add the edge.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSourceNode() != null && !graph.getSelected()) {
            graph.setSelected(true);
        }
    }

}
