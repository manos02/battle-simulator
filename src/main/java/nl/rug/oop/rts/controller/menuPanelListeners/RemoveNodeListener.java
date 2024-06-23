package nl.rug.oop.rts.controller.menuPanelListeners;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * A model for pressing the removeNode button.
 */
public class RemoveNodeListener extends MouseAdapter implements ActionListener {
    private final Graph graph;

    public RemoveNodeListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * checks if a node is selected and updates the model.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSourceNode() != null) {
            graph.removeNode(graph.getSourceNode());
            graph.setSourceNode(null);
        }
    }

}
