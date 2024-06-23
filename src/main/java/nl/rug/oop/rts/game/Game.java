package nl.rug.oop.rts.game;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Simulation;
import nl.rug.oop.rts.view.GraphView;

/**
 * The main model of the game.
 */
public class Game {
    private final Graph graph;

    public Game() {
        graph = new Graph();
    }

    /**
     * create the viewing of the game and the simulation.
     */
    public void startGame() {
        Simulation simulation = new Simulation(graph);
        GraphView graphView = new GraphView(graph, simulation);
        graphView.createGraphView();
    }
}
