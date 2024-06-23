package com.manosavvides.rts.view;

import com.manosavvides.rts.controller.Listener;
import com.manosavvides.rts.controller.menuPanelListeners.*;
import com.manosavvides.rts.model.Graph;
import com.manosavvides.rts.model.Simulation;


import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * A model for the ButtonMenu for the graph operations.
 */
public class ButtonMenu extends JPanel implements Listener {

    private final Graph graph;
    private final Simulation simulation;
    private JButton addNodeButton;
    private JButton removeNodeButton;
    private JButton addEdgeButton;
    private JButton removeEdgeButton;

    /**
     * add the buttonMenu as a listener to the graph.
     *
     * @param graph of the game.
     * @param simulation of the game.
     */
    public ButtonMenu(Graph graph, Simulation simulation) {
        this.graph = graph;
        this.simulation = simulation;
        graph.addListener(this);
        setupMenu();
    }

    /**
     * Buttons are created and added to the ButtonMenu.
     */
    private void setupMenu() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.DARK_GRAY);

        addNodeButton = new JButton("Add Location");
        addNodeButton.addActionListener(new AddNodeListener(graph));
        customizeButton(addNodeButton, true);
        add(addNodeButton);

        removeNodeButton = new JButton("Remove Selected Location");
        customizeButton(removeNodeButton, false);
        removeNodeButton.addActionListener(new RemoveNodeListener(graph));
        add(removeNodeButton);

        addEdgeButton = new JButton("Draw Route");
        addEdgeButton.addActionListener(new AddEdgeListener(graph));
        customizeButton(addEdgeButton, false);
        add(addEdgeButton);

        removeEdgeButton = new JButton("Remove Selected Route");
        customizeButton(removeEdgeButton, false);
        removeEdgeButton.addActionListener(new RemoveEdgeListener(graph));
        add(removeEdgeButton);

        JButton simulateTimeStep = new JButton("Simulate Time Step");
        customizeButton(simulateTimeStep, true);
        simulateTimeStep.addActionListener((new SimulationStepListener(simulation)));
        add(simulateTimeStep);

        JButton battle = new JButton("Battle");
        customizeButton(battle, true);
        battle.addActionListener((new BattleListener(graph, simulation)));
        add(battle);

        JButton save = new JButton("Save");
        customizeButton(save, true);
        save.addActionListener(new SaveListener(graph));
        add(save);
    }

    /**
     * customize the buttons according if they can get pressed or not.
     *
     * @param button to be customized.
     * @param selected if the button can be pressed.
     */
    public void customizeButton(JButton button, boolean selected) {
        button.setUI(new BasicButtonUI());
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(selected ? Color.ORANGE : Color.GRAY); // if can be pressed orange else gray
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFont(button.getFont().deriveFont(Font.BOLD));
    }

    /**
     * updates which buttons can be pressed.
     */
    @Override
    public void updated() {
        if (graph.getSelectedEdge() != null) { // if an edge is selected
            customizeButton(removeEdgeButton, true);
            customizeButton(removeNodeButton, false);
            customizeButton(addNodeButton, false);
            customizeButton(addEdgeButton, false);
            return;
        }
        // if a node is selected and there are more than 1 node
        if (graph.getSourceNode() != null && graph.getNodes().size() > 1) {
            customizeButton(removeEdgeButton, false);
            customizeButton(removeNodeButton, true);
            customizeButton(addNodeButton, true);
            customizeButton(addEdgeButton, true);
            // a node is selected and there are less than 1 node
        } else if (graph.getSourceNode() != null && graph.getNodes().size() < 2) {
            customizeButton(removeEdgeButton, false);
            customizeButton(removeNodeButton, true);
            customizeButton(addNodeButton, true);
            customizeButton(addEdgeButton, false);
        } else {
            // nothing is selected
            customizeButton(removeEdgeButton, false);
            customizeButton(removeNodeButton, false);
            customizeButton(addNodeButton, true);
            customizeButton(addEdgeButton, false);
        }
    }
}
