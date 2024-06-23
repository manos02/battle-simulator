package nl.rug.oop.rts.controller.panelListeners;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A model for moving the nodes.
 */
public class MoveNodeListener extends MouseAdapter {
    private static final int NODEWIDTH = 100;
    private static final int NODEHEIGHT = 100;
    private final Graph graph;

    public MoveNodeListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * Checks which node is moved and change its coordinates and updates its listeners.
     *
     * @param e the event to be processed.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        for (Node node : graph.getNodes()) {
            if (node == graph.getSourceNode()) {
                int halfWidth = NODEWIDTH / 2;
                int halfHeight = NODEHEIGHT / 2;

                node.setX(e.getX() - halfWidth);
                node.setY(e.getY() - halfHeight);
                graph.notifyListeners();
            }
        }
    }
}