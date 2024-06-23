package nl.rug.oop.rts.model;

import nl.rug.oop.rts.controller.Listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A model for the graph of the game.
 */
public class Graph {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private int x, y, nodeId, edgeId;
    private Node sourceNode;
    private Edge selectedEdge;
    private Boolean selected;
    private final Collection<Listener> listeners;

    /**
     * constructor of the graph.
     */
    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.sourceNode = null;
        this.x = 250;
        this.y = 150;
        this.nodeId = 1;
        this.edgeId = 1;
        this.selected = false;
    }

    public Edge getSelectedEdge() {
        return selectedEdge;
    }

    public void setSelectedEdge(Edge selectedEdge) {
        this.selectedEdge = selectedEdge;
        notifyListeners();
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public void setSourceNode(Node sourceNode) {
        this.sourceNode = sourceNode;
        notifyListeners();
    }

    public void setName(String name, Node node) {
        node.setName(name);
        notifyListeners();
    }

    /**
     * creates a new node and then adds it to the graph.
     */
    public void addNode() {
        Node newNode = new Node(nodeId, "node" + nodeId, new ArrayList<>(), x, y);
        nodes.add(newNode);
        notifyListeners();
        x += 50;
        y += 50;
        nodeId++;
    }

    /**
     * removes the node and also the edges of the node from the graph.
     *
     * @param node to be removed.
     */
    public void removeNode(Node node) {
        for (Edge edge : node.getEdges()) {
            edges.remove(edge);
        }
        nodes.remove(node);
        notifyListeners();
    }

    /**
     * add an edge between 2 nodes.
     *
     * @param node selected to add the edge to.
     */
    public void addEdge(Node node) {
        Edge newEdge = new Edge(edgeId, "edge" + edgeId, sourceNode, node);
        edgeId++;
        newEdge.setNode1(sourceNode);
        newEdge.setNode2(node);
        sourceNode.addEdge(newEdge);
        node.addEdge(newEdge);
        edges.add(newEdge);
        notifyListeners();
    }

    /**
     * remove an edge from the graph.
     *
     * @param edge to be removed.
     */
    public void removeEdge(Edge edge) {
        edge.getNode1().getEdges().remove(edge); // remove the edge from the source node
        edge.getNode2().getEdges().remove(edge); // remove the edge from the destination node
        edges.remove(edge);
        notifyListeners();
    }

    /**
     * add a listener to the listeners.
     *
     * @param listener to be added.
     */
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * notify all the listeners when an update happens.
     */
    public void notifyListeners() {
        for (Listener listener : listeners) {
            listener.updated();
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
