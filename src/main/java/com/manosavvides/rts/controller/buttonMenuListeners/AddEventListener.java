package com.manosavvides.rts.controller.buttonMenuListeners;

import com.manosavvides.rts.model.Graph;
import com.manosavvides.rts.model.events.Event;
import com.manosavvides.rts.model.events.HiddenWeaponryEvent;
import com.manosavvides.rts.model.events.NaturalDisasterEvent;
import com.manosavvides.rts.model.events.ReinforcementsEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A model for pressing the addEvent button.
 */
public class AddEventListener implements ActionListener {
    private final Graph graph;

    public AddEventListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * checks if the event is going to be added to a node or an edge.
     *
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSelectedEdge() != null) {
            addEventEdge();
        } else if (graph.getSourceNode() != null) {
            addEventNode();
        }
    }

    /**
     * add the event to the node.
     */
    public void addEventNode() {
        Event event = select();
        if (event != null) {
            graph.getSourceNode().addEvent(event);
        }
    }

    /**
     * add the event to the edge.
     */
    public void addEventEdge() {
        Event event = select();
        if (event != null) {
            graph.getSelectedEdge().addEvent(event);
        }
    }

    /**
     * The user selects an event.
     *
     * @return the selected event from the user.
     */
    public Event select() {
        String[] events = {"reinforcements", "natural disaster", "hidden weaponry"};
        String selectedEvent = (String) JOptionPane.showInputDialog(null, "Select an event:",
                "Add Event", JOptionPane.PLAIN_MESSAGE, null,
                events, events[0]);
        if (selectedEvent == null) {
            return null;  // User pressed "Cancel"
        }
        Event event;
        if (selectedEvent.equals("reinforcements")) {
            event = new ReinforcementsEvent(selectedEvent);
        } else if (selectedEvent.equals("natural disaster")) {
            event = new NaturalDisasterEvent(selectedEvent);
        } else {
            event = new HiddenWeaponryEvent(selectedEvent);
        }
        return event;
    }

}
