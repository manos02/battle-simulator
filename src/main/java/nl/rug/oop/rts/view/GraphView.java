package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * A class to display all the things on the frame.
 */
public class GraphView {
    private GameFrame gameFrame;
    private final Graph graph;
    private final Simulation simulation;

    /**
     * constructor for the graphView.
     *
     * @param graph of the game.
     * @param simulation of the game.
     */
    public GraphView(Graph graph, Simulation simulation) {
        this.graph = graph;
        this.simulation = simulation;
    }

    /**
     * create a frame and add all the components to it.
     */
    public void createGraphView() {
        gameFrame = new GameFrame();
        createGraphPane();
        createButtonMenu();
        gameFrame.setVisible(true);
    }

    /**
     * Creates the graph panel and menuPanel and added to a split panel.
     */
    public void createGraphPane() {
        MenuPanel menuPanel = new MenuPanel(graph);
        GraphPanel graphPanel = new GraphPanel(graph);
        graphPanel.setLayout(null);

        Dimension preferredSize = new Dimension(300, 600);
        menuPanel.setPreferredSize(preferredSize);

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuPanel, graphPanel);
        gameFrame.getContentPane().add(pane);
    }

    /**
     * ButtonMenu is created for the different operations on the graph.
     */
    public void createButtonMenu() {
        ButtonMenu buttonMenu = new ButtonMenu(graph, simulation);
        gameFrame.add(buttonMenu, BorderLayout.NORTH);
    }

}
