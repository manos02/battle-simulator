package com.manosavvides.rts.model;

import com.manosavvides.rts.model.faction.Army;
import com.manosavvides.rts.model.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A model for the simulation of the game.
 */
public class Simulation {
    private final Graph graph;
    private int state;
    private final Random random;

    /**
     * the initial state is all the armies are at the nodes.
     *
     * @param graph to simulate.
     */
    public Simulation(Graph graph) {
        this.graph = graph;
        this.state = 0;
        this.random = new Random();
    }

    public int getState() {
        return state;
    }

    /**
     * simulation when the nodes are at the node.
     */
    public void nodeToEdgeSimulation() {
        for (Node node : graph.getNodes()) {
            List<Army> armies = new ArrayList<>(node.getArmies());
            for (Army army : armies) {
                List<Edge> edges = node.getEdges();
                if (!edges.isEmpty()) {
                    //choose a random edge from the node
                    Edge randomEdge = edges.get(random.nextInt(edges.size()));
                    //remove the army from the node
                    node.getArmies().remove(army);
                    //place the army to the random edge
                    randomEdge.addArmy(army);
                    // random result for an event to occur to the army
                    boolean fiftyFiftyChance = random.nextBoolean();
                    int numEvents = randomEdge.getEvents().size();
                    // if there is an event to the edge and the random result is yes
                    if (fiftyFiftyChance && numEvents > 0) {
                        //get a random event from that edge
                        Event randomEvent = randomEdge.getEvents().get(random.nextInt(numEvents));
                        // add the army to the event
                        randomEvent.addAffectedArmy(army);
                        // affect the army
                        randomEvent.act(army);
                    }
                    graph.notifyListeners();
                }
            }
        }
        //change the state of the simulation
        state = 1;
    }

    /**
     * A simulation when the nodes are at the edge.
     */
    public void edgeToNodeSimulation() {
        for (Edge edge : graph.getEdges()) {
            List<Army> armies = new ArrayList<>(edge.getArmies());
            for (Army army : armies) {
                //choose the other node from which the army came
                Node targetNode = (army.getNode() == edge.getNode1()) ? edge.getNode2() : edge.getNode1();
                //remove the army from the edge
                edge.getArmies().remove(army);
                // place the army to the node
                targetNode.addArmy(army);
                army.setNode(targetNode);
                // random result for an event to occur to the army
                boolean fiftyFiftyChance = random.nextBoolean();
                int numEvents = targetNode.getEvents().size();
                // if there is an event to the node and the random result is yes
                if (fiftyFiftyChance && numEvents > 0) {
                    // get a random event from that node
                    Event randomEvent = targetNode.getEvents().get(random.nextInt(numEvents));
                    // add the army to the event
                    randomEvent.addAffectedArmy(army);
                    // affect the army
                    randomEvent.act(army);
                }
                graph.notifyListeners();
            }
        }
        // change the state of the simulation.
        state = 0;
    }
}