package com.manosavvides.rts;

import com.manosavvides.rts.model.Edge;
import com.manosavvides.rts.model.Graph;
import com.manosavvides.rts.model.Node;
import com.manosavvides.rts.model.Unit;
import com.manosavvides.rts.model.events.Event;
import com.manosavvides.rts.model.faction.Army;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Save of the game.
 */
public class Save {
    private final Graph graph;
    private final File selectedFile;
    private int indentation;
    private FileWriter fileWr;
    private static final String COMMA = ",";
    private static final String OPEN_BRACE = "{";
    private static final String CLOSE_BRACE = "}";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String QUOTE = "\"";
    private static final String COLON = ": ";

    /**
     * constructor of the class.
     *
     * @param graph of the game.
     * @param selectedFile to save the game.
     */
    public Save(Graph graph, File selectedFile) {
        this.graph = graph;
        this.selectedFile = selectedFile;
        this.indentation = 0;
    }

    /**
     * This method saves the game state into a file.
     * If the file does not exist, it creates a new one.
     * If the filename doesn't end with .json, it appends .json to the filename.
     * @throws RuntimeException if an IOException occurs during writing to the file.
     */
    public void saveGame() {
        File myFile = selectedFile;
        if (!selectedFile.getName().endsWith(".json")) {
            String pathWithJsonExtension = selectedFile.getAbsolutePath() + ".json";
            myFile = new File(pathWithJsonExtension);
        }

        try {
            fileWr = new FileWriter(myFile);
            saveGraph();
            fileWr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Serializes the Graph object to JSON format and writes it to the previously specified file.
     * The serialized data includes all nodes and edges of the graph.
     *
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveGraph() throws IOException {
        writeToFile(OPEN_BRACE);
        indentation++;
        saveNodes(graph.getNodes());
        saveEdges(graph.getEdges());
        indentation--;
        writeToFile(CLOSE_BRACE);
    }

    /**
     * This method writes the list of nodes to the file.
     * If the list is empty, it writes an empty array.
     * @param nodes of the graph.
     * @throws IOException if an IOException occurs during writing to the file.
     */
    public void saveNodes(List<Node> nodes) throws IOException {
        if (nodes.isEmpty()) {
            writeToFile(QUOTE + "Nodes" + QUOTE + COLON + OPEN_BRACKET + CLOSE_BRACKET + COMMA);
            return;
        }
        writeToFile(QUOTE + "Nodes" + QUOTE + COLON + OPEN_BRACKET);
        indentation++;
        Node lastNode = graph.getNodes().get(graph.getNodes().size() - 1);
        for (Node node : graph.getNodes()) {
            writeToFile(OPEN_BRACE);
            indentation++;
            writeToFile(QUOTE + "Id" + QUOTE + COLON + node.getId() + COMMA);
            writeToFile(QUOTE + "Name" + QUOTE + COLON + QUOTE + node.getName() + QUOTE + COMMA);

            saveArmies(node.getArmies());

            saveEvents(node.getEvents());
            indentation--;
            if (node == lastNode) {
                writeToFile(CLOSE_BRACE);
                indentation--;
                writeToFile(CLOSE_BRACKET + COMMA);
                return;
            }
            writeToFile(CLOSE_BRACE + COMMA);
        }
    }

    /**
     * This method writes the list of armies to the file.
     * If the list is empty, it writes an empty array.
     * @param armies The list of armies to write.
     * @throws IOException if an IOException occurs during writing to the file.
     */
    public void saveArmies(List<Army> armies) throws IOException {
        if (armies.isEmpty()) {
            writeToFile(QUOTE + "Armies" + QUOTE + COLON + OPEN_BRACKET + CLOSE_BRACKET + COMMA);
            return;
        }
        writeToFile(QUOTE + "Armies" + QUOTE + COLON + OPEN_BRACKET);
        indentation++;

        Army lastArmy = armies.get(armies.size() - 1);
        for (Army army : armies) {
            writeToFile(OPEN_BRACE);
            indentation++;
            writeToFile(QUOTE + "Faction" + QUOTE + COLON + QUOTE + army.getFaction() + QUOTE + COMMA);
            writeToFile(QUOTE + "Team" + QUOTE + COLON + army.getTeam() + COMMA);
            saveUnits(army.getUnits());
            indentation--;
            if (army != lastArmy) {
                writeToFile(CLOSE_BRACE + COMMA);
            } else {
                writeToFile(CLOSE_BRACE);
                indentation--;
                writeToFile(CLOSE_BRACKET + COMMA);
            }
        }
    }

    /**
     * This method writes the list of units to the file.
     * If the list is empty, it writes an empty array.
     * @param units The list of units to write.
     * @throws IOException if an IOException occurs during writing to the file.
     */
    public void saveUnits(List<Unit> units) throws IOException {
        if (units.isEmpty()) {
            writeToFile(QUOTE + "units" + QUOTE + COLON + OPEN_BRACKET + CLOSE_BRACKET);
            return;
        }
        writeToFile(QUOTE + "units" + QUOTE + COLON + OPEN_BRACKET);
        indentation++;

        Unit lastUnit = units.get(units.size() - 1);
        for (Unit unit : units) {
            writeToFile(OPEN_BRACE);
            indentation++;
            writeToFile(QUOTE + "Name" + QUOTE + COLON + QUOTE + unit.getName() + QUOTE + COMMA);
            writeToFile(QUOTE + "Strength" + QUOTE + COLON + unit.getDamage() + COMMA);
            writeToFile(QUOTE + "Health" + QUOTE + COLON + unit.getHealth());
            indentation--;

            if (unit != lastUnit) {
                writeToFile(CLOSE_BRACE + COMMA);
            } else {
                writeToFile(CLOSE_BRACE);
                indentation--;
                writeToFile(CLOSE_BRACKET);
            }
        }
    }

    /**
     * This method writes the list of events to the file.
     * If the list is empty, it writes an empty array.
     * For each event, it writes the event's type.
     * @param events The list of events to write.
     * @throws IOException if an IOException occurs during writing to the file.
     */
    public void saveEvents(List<Event> events) throws IOException {
        if (events.isEmpty()) {
            writeToFile(QUOTE + "Events" + QUOTE + COLON + OPEN_BRACKET + CLOSE_BRACKET);
            return;
        }
        writeToFile(QUOTE + "Events" + QUOTE + COLON + OPEN_BRACKET);
        indentation++;

        Event lastEvent = events.get(events.size() - 1);
        for (Event event : events) {
            if (event == lastEvent) {
                writeToFile(QUOTE + event.getType() + QUOTE);
                indentation--;
                writeToFile(CLOSE_BRACKET);
            } else {
                writeToFile(QUOTE + event.getType() + QUOTE + COMMA);
            }
        }
    }

    /**
     * This method writes the list of edges to the file.
     * If the list is empty, it writes an empty array.
     * For each edge, it writes the edge's id, name, ids of connected nodes, and lists of armies and events.
     * @param edges The list of edges to write.
     * @throws IOException if an IOException occurs during writing to the file.
     */
    public void saveEdges(List<Edge> edges) throws IOException {
        if (edges.isEmpty()) {
            writeToFile(QUOTE + "Edges" + QUOTE + COLON + OPEN_BRACKET + CLOSE_BRACKET);
            return;
        }
        Edge lastEdge = edges.get(edges.size() - 1);
        writeToFile(QUOTE + "Edges" + QUOTE + COLON + OPEN_BRACKET);
        indentation++;
        for (Edge edge : edges) {
            writeToFile(OPEN_BRACE);
            indentation++;
            writeToFile(QUOTE + "Id" + QUOTE + COLON + edge.getId() + COMMA);
            writeToFile(QUOTE + "Name" + QUOTE + COLON + QUOTE + edge.getName() + QUOTE + COMMA);
            writeToFile(QUOTE + "Node1" + QUOTE + COLON + edge.getNode1().getId() + COMMA);
            writeToFile(QUOTE + "Node2" + QUOTE + COLON + edge.getNode2().getId() + COMMA);
            saveArmies(edge.getArmies());
            saveEvents(edge.getEvents());
            indentation--;
            if (edge == lastEdge) {
                writeToFile(CLOSE_BRACE);
                indentation--;
                writeToFile(CLOSE_BRACKET);
            } else {
                writeToFile(CLOSE_BRACE + COMMA);
            }
        }
    }

    /**
     * This method writes a string to the file, adding the necessary indentation.
     * @param myString The string to write.
     * @throws IOException if an IOException occurs during writing to the file.
     */
    private void writeToFile(String myString) throws IOException {
        for (int i = 0; i < indentation; i++) {
            fileWr.write("\t");
        }
        fileWr.write(myString + "\n");
    }

}
