package nl.rug.oop.rts.controller.menuPanelListeners;

import nl.rug.oop.rts.Save;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener that saves the current state of the graph into a file
 * The file is selected by the user through a file chooser dialog
 * that allows only JSON files to be selected.
 */
public class SaveListener implements ActionListener {
    private final Graph graph;

    /**
     * constructor.
     *
     * @param graph the graph whose state will be saved.
     */
    public SaveListener(Graph graph) {
        this.graph = graph;
    }

    /**
     * Executed when the button is pressed.
     * This method opens a file chooser dialog that allows the user to select the file
     * where the graph state will be saved.
     * If the user approves the selection, the state of the graph is saved into the selected file in JSON format.
     * @param e the event object associated with the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON FILES", "json"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            Save save = new Save(graph, fileChooser.getSelectedFile());
            save.saveGame();
        }

    }
}
