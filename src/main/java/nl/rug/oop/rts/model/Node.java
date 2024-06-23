package nl.rug.oop.rts.model;

import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.faction.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * A model for the node of the graph.
 */
public class Node {
    private final int id;
    private int x, y;
    private String name;
    private final List<Edge> edges;
    private final List<Army> armies;
    private final List<Event> events;

    /**
     * constructor of the node.
     *
     * @param id of the node.
     * @param name of the node.
     * @param edges of the node.
     * @param x coordinate of the node.
     * @param y coordinate of the node.
     */
    public Node(int id, String name, List<Edge> edges, int x, int y) {
        this.id = id;
        this.name = name;
        this.edges = edges;
        this.armies = new ArrayList<>();
        this.events = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addArmy(Army army) {
        armies.add(army);
    }

    public List<Army> getArmies() {
        return armies;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * create a new army and add it to the node.
     *
     * @param selectedFaction of the army.
     * @param unitNames that the army will contain.
     * @param team of the army.
     */
    public void setArmy(String selectedFaction, List<String> unitNames, int team) {
        Army army = new Army(selectedFaction, unitNames, team, this);
        addArmy(army);
    }

    /**
     * if army is provided remove that army, else remove the last one added.
     *
     * @param army to be removed.
     */
    public void removeArmyFromNode(Army army) {
        if (army != null) {
            armies.remove(army);
        } else {
            int indexRemove = armies.size()-1;
            armies.remove(indexRemove);
        }
    }

}
