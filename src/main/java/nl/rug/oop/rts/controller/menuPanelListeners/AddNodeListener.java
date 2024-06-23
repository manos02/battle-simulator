package nl.rug.oop.rts.controller.menuPanelListeners;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A model when the addNode button is pressed.
 */
public class AddNodeListener implements ActionListener {
    private final Graph graph;

    public AddNodeListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * add a node to the graph.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.addNode();
    }
}
