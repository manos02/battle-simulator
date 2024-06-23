package nl.rug.oop.rts.controller.menuPanelListeners;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A model for pressing the removeEdge button.
 */
public class RemoveEdgeListener implements ActionListener {
    private final Graph graph;

    public RemoveEdgeListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * checks if an edge is selected and updates the model.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSelectedEdge() != null) {
            graph.removeEdge(graph.getSelectedEdge());
            graph.setSelectedEdge(null);
        }

    }
}
