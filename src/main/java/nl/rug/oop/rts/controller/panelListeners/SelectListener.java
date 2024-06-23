package nl.rug.oop.rts.controller.panelListeners;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A model for selecting a node.
 */
public class SelectListener extends MouseAdapter {
    private final Graph graph;

    public SelectListener(Graph graph) {
        this.graph = graph;
    }

    //mousePressed
    @Override
    public void mousePressed(MouseEvent e) {
        super.mouseClicked(e);
        int x = e.getX();
        int y = e.getY();
        checkNodes(x, y);
        checkEdges(x, y);
    }

    /**
     * checks if a node was clicked and if it is a source or destination node.
     * also calls the MenuPanel to update it if a node was selected.
     *
     * @param x is the xCoordinate of the mouse click.
     * @param y is the yCoordinate of the mouse click.
     */
    public void checkNodes(int x, int y) {
        if (graph.getSelectedEdge() != null) {
            return;
        }
        for (Node node : graph.getNodes()) {
            if (x > node.getX() && x <= node.getX() + 100 && y > node.getY() && y <= node.getY() + 100) {
                if (graph.getSourceNode() == null) {
                    graph.setSourceNode(node);
                    return;
                } else if (graph.getSourceNode() != null && graph.getSourceNode() != node && graph.getSelected()) {
                    graph.setSelected(false);
                    graph.addEdge(node);
                    return;
                } else if (graph.getSourceNode() != null && graph.getSourceNode() != node && !graph.getSelected()) {
                    graph.setSourceNode(node);
                    return;
                } else {
                    graph.setSourceNode(null);
                    return;
                }
            }
        }
        if (graph.getSourceNode() != null) {
            graph.setSourceNode(null);
        }

    }

    /**
     * checks if an edge was clicked.
     *
     * @param x is the xCoordinate of the mouse click.
     * @param y is the yCoordinate of the mouse click.
     */
    public void checkEdges(int x, int y) {
        if (graph.getSourceNode() != null) {
            return;
        }
        for (Edge edge : graph.getEdges()) {
            int sourceX, sourceY, targetX, targetY;

            if (edge.getNode1().getX() + 50 > edge.getNode2().getX() + 20) {
                targetX = edge.getNode1().getX() + 50;
                sourceX = edge.getNode2().getX() + 20;
            } else {
                sourceX = edge.getNode1().getX() + 50;
                targetX = edge.getNode2().getX() + 20;
            }
            if (edge.getNode1().getY() + 90 > edge.getNode2().getY() + 30) {
                targetY = edge.getNode1().getY() + 90;
                sourceY = edge.getNode2().getY() + 30;
            } else {
                sourceY = edge.getNode1().getY() + 90;
                targetY = edge.getNode2().getY() + 30;
            }
            double distance = distanceToLine(x, y, sourceX, sourceY, targetX, targetY);
            if (distance < 5) {
                if (graph.getSelectedEdge() == null) {
                    graph.setSelectedEdge(edge);
                } else {
                    graph.setSelectedEdge(null);
                }
            }
        }
        System.out.println();
    }

    /**
     * Calculates the shortest distance from a given point to a line defined by two points.
     * D = |(px - x1) * (y2 - y1) - (py - y1) * (x2 - x1)| / sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2)
     *
     * @param pointX coordinate of the mouse click.
     * @param pointY y coordinate of the mouse click.
     * @param lineStartX coordinate of the starting point of the line.
     * @param lineStartY coordinate of the starting point of the line.
     * @param lineEndX coordinate of the ending point of the line.
     * @param lineEndY coordinate of the ending point of the line.
     * @return The shortest distance from the mouse click to the line.
     */
    public double distanceToLine(int pointX, int pointY, int lineStartX, int lineStartY, int lineEndX, int lineEndY) {
        double lineLength = Math.sqrt(Math.pow(lineEndX - lineStartX, 2) + Math.pow(lineEndY - lineStartY, 2));
        return Math.abs((pointX - lineStartX) * (lineEndY - lineStartY)
                - (pointY - lineStartY) * (lineEndX - lineStartX)) / lineLength;
    }
}
