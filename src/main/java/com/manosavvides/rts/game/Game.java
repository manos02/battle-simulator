package com.manosavvides.rts.game;

import com.manosavvides.rts.model.Graph;
import com.manosavvides.rts.model.Simulation;
import com.manosavvides.rts.view.GraphView;

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
