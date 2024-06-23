package nl.rug.oop.rts.controller.buttonMenuListeners;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A model to save the node name when "Enter" is pressed.
 */
public class EnterKeyListener extends KeyAdapter {
    private final JTextField textField;
    private final Graph graph;

    public EnterKeyListener(JTextField textField, Graph graph) {
        this.textField = textField;
        this.graph = graph;
    }

    /**
     * checks if ENTER is pressed to save the node.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (graph.getSelectedEdge() != null) {
            return;
        }
        Node node = graph.getSourceNode();
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = textField.getText(); //store the string entered.
            graph.setName(text, node); //change the node name.
        }
    }

}
