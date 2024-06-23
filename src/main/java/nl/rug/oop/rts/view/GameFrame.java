package nl.rug.oop.rts.view;

import javax.swing.*;

/**
 * The main gameFrame of the game.
 */
public class GameFrame extends JFrame {
    /**
     * set the dimensions of the frame and its title.
     */
    public GameFrame() {
        setTitle("Battle game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

