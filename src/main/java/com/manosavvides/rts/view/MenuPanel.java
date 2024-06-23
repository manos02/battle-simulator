package com.manosavvides.rts.view;

import com.manosavvides.rts.model.Edge;
import com.manosavvides.rts.model.Graph;
import com.manosavvides.rts.controller.buttonMenuListeners.AddArmyListener;
import com.manosavvides.rts.controller.buttonMenuListeners.AddEventListener;
import com.manosavvides.rts.controller.buttonMenuListeners.EnterKeyListener;
import com.manosavvides.rts.controller.Listener;
import com.manosavvides.rts.controller.buttonMenuListeners.RemoveArmyListener;
import com.manosavvides.rts.model.Node;

import javax.swing.*;
import java.awt.*;

/**
 * A model for the menu panel.
 */
public class MenuPanel extends JPanel implements Listener {
    private final PopupPanel popupPanel;
    private final Graph graph;
    private JTextField nameField;

    /**
     * initializes all the items on the panel.
     *
     * @param graph of the game.
     */
    public MenuPanel(Graph graph) {
        this.graph = graph;
        this.popupPanel = new PopupPanel(graph);
        graph.addListener(this);
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);  // Set background color to grey
        initTextField();
        initArmiesPanel();
        initEventsPanel();
        updateNodeDetails(null);
        updateEdgeDetails(null);
    }

    /**
     * initialise the text field.
     */
    public void initTextField() {
        nameField = new JTextField();
        add(nameField, BorderLayout.SOUTH);
        nameField.setForeground(Color.ORANGE);  // Set text color to gold
        nameField.setHorizontalAlignment(SwingConstants.CENTER);  // Align text at the center
        nameField.addKeyListener(new EnterKeyListener(nameField, graph));
    }

    /**
     * initialise the armies panel.
     */
    public void initArmiesPanel() {
        JPanel armiesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        armiesPanel.setBackground(Color.DARK_GRAY);
        JLabel armiesLabel = new JLabel("Armies");
        armiesLabel.setForeground(Color.orange);

        JButton plusButton = new JButton("+");
        plusButton.setBackground(Color.DARK_GRAY);
        plusButton.setForeground(Color.orange);
        plusButton.addActionListener(new AddArmyListener(graph));

        JButton minusButton = new JButton("-");
        minusButton.setBackground(Color.DARK_GRAY);
        minusButton.setForeground(Color.orange);
        minusButton.addActionListener(new RemoveArmyListener(graph));

        armiesPanel.add(armiesLabel);
        armiesPanel.add(plusButton);
        armiesPanel.add(minusButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(armiesPanel, BorderLayout.NORTH);
        centerPanel.add(popupPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

    }

    /**
     * initialise the events panel.
     */
    public void initEventsPanel() {
        JPanel eventsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        eventsPanel.setBackground(Color.DARK_GRAY);
        JLabel eventsLabel = new JLabel("Events");
        eventsLabel.setForeground(Color.orange);

        JButton addEventButton = new JButton("+");
        addEventButton.setBackground(Color.DARK_GRAY);
        addEventButton.setForeground(Color.orange);
        addEventButton.addActionListener(new AddEventListener(graph));

        JButton removeEventButton = new JButton("-");
        removeEventButton.setBackground(Color.DARK_GRAY);
        removeEventButton.setForeground(Color.orange);

        eventsPanel.add(eventsLabel);
        eventsPanel.add(addEventButton);
        eventsPanel.add(removeEventButton);

        add(eventsPanel, BorderLayout.BEFORE_FIRST_LINE);
    }

    /**
     * checks if a node is selected and displays its information.
     *
     * @param node selected.
     */
    public void updateNodeDetails(Node node) {
        if (node != null) {
            nameField.setText(node.getName());
        } else {
            nameField.setText("nothing selected");
        }
    }

    /**
     * checks if an edge is selected and displays its information.
     *
     * @param edge selected.
     */
    private void updateEdgeDetails(Edge edge) {
        if (edge != null) {
            nameField.setText(edge.getNode1().getName() + " and " + edge.getNode2().getName());
        } else {
            nameField.setText("nothing selected");
        }
    }

    @Override
    public void updated() {

        if (graph.getSelectedEdge() == null) {
            updateEdgeDetails(null);
        } else {
            updateEdgeDetails(graph.getSelectedEdge());
            return;
        }
        if (graph.getSourceNode() == null) {
            updateNodeDetails(null);
        } else {
            updateNodeDetails(graph.getSourceNode());
        }
    }
}
