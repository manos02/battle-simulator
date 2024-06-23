package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.Listener;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.faction.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;

/**
 * displays which armies are affected from which events.
 */
public class PopupPanel extends JPanel implements Listener {
    private final Graph graph;

    /**
     * add it as a listener to the graph.
     *
     * @param graph of the game.
     */
    public PopupPanel(Graph graph) {
        this.graph = graph;
        graph.addListener(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

    }

    /**
     * update when notified.
     */
    @Override
    public void updated() {
        removeAll();
        nodeEvents();
        edgeEvents();
        revalidate();
        repaint();
    }

    /**
     * affected armies from events at nodes.
     */
    public void nodeEvents() {
        for (Node node : graph.getNodes()) {
            for (Event event : node.getEvents()) {
                for (Army army : event.getAffectedArmies()) {
                    String message = event.getType() + " - Army: " + army.getFaction() + " at node: " + node.getId();
                    JLabel messageLabel = new JLabel(message);
                    messageLabel.setForeground(Color.ORANGE);
                    add(messageLabel);
                }
            }
        }
    }

    /**
     * affected armies from events at edges.
     */
    public void edgeEvents() {
        for (Edge edge : graph.getEdges()) {
            for (Event event : edge.getEvents()) {
                for (Army army : event.getAffectedArmies()) {
                    String message = event.getType() + " - Army: " + army.getFaction() + " at edge: " + edge.getId();
                    JLabel messageLabel = new JLabel(message);
                    messageLabel.setForeground(Color.ORANGE);
                    add(messageLabel);
                }
            }
        }
    }
}
