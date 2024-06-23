package com.manosavvides.rts.model;

import com.manosavvides.rts.model.events.Event;
import com.manosavvides.rts.model.faction.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * A model for the edge of the game.
 */
public class Edge {
    private final int id;
    private final String name;
    private final List<Army> armies;

    private Node node1, node2;
    private final List<Event> events;

    /**
     * constructor for the edge.
     *
     * @param id of the edge.
     * @param name of the edge.
     * @param node1 one endpoint of the edge.
     * @param node2 other endpoint of the edge.
     */
    public Edge(int id, String name, Node node1, Node node2) {
        this.id = id;
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.armies = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addArmy(Army army) {
        armies.add(army);
    }

    public void removeArmyFromEdge(Army army) {
        armies.remove(army);
    }

    public List<Army> getArmies() {
        return armies;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

}
