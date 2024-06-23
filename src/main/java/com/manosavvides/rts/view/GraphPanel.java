package com.manosavvides.rts.view;

import com.manosavvides.rts.controller.Listener;
import com.manosavvides.rts.controller.panelListeners.MoveNodeListener;
import com.manosavvides.rts.controller.panelListeners.SelectListener;
import com.manosavvides.rts.model.Edge;
import com.manosavvides.rts.model.Graph;
import com.manosavvides.rts.model.faction.Army;
import com.manosavvides.rts.model.Node;
import com.manosavvides.rts.util.TextureLoader;

import javax.swing.*;
import java.awt.*;

/**
 * A model for the panel of the game.
 */
public class GraphPanel extends JPanel implements Listener {

    private final Graph graph;
    private final TextureLoader textureLoader;

    /**
     * make the panel a listener.
     *
     * @param graph to be displayed on the graph panel.
     */
    public GraphPanel(Graph graph) {
        this.graph = graph;
        textureLoader = TextureLoader.getInstance();
        graph.addListener(this);
        addMouseListener(new SelectListener(graph));
        addMouseListener(new MoveNodeListener(graph));
        addMouseMotionListener(new MoveNodeListener(graph));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = textureLoader.getTexture("mapLotr", getWidth(), getHeight());
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        drawNodes();
        drawEdges(g);
    }

    /**
     * All the nodes are displayed on the panel.
     * Each node is on a separate Label.
     */
    public void drawNodes() {
        if (graph != null) {
            for (Node node : graph.getNodes()) {
                int x = node.getX();
                int y = node.getY();

                Image nodeImage;
                if (graph.getSourceNode() != node) {
                    nodeImage = textureLoader.getTexture("node2", 100, 100);
                } else {
                    nodeImage = textureLoader.getTexture("node3", 100, 100);
                }
                ImageIcon imageIcon = new ImageIcon(nodeImage);

                JLabel nodeLabel = new JLabel(imageIcon);
                nodeLabel.setBounds(x, y, 100, 100);

                nodeLabel.setText(node.getName());
                nodeLabel.setHorizontalTextPosition(JLabel.CENTER);
                nodeLabel.setVerticalTextPosition(JLabel.CENTER);
                nodeLabel.setHorizontalAlignment(JLabel.CENTER);
                nodeLabel.setVerticalAlignment(JLabel.CENTER);

                if (!node.getArmies().isEmpty()) {
                    int iconX = x;
                    for (Army army : node.getArmies()) {
                        Image factionImage = textureLoader.getTexture(army.getFaction(), 30, 30);
                        ImageIcon factionIcon = new ImageIcon(factionImage);
                        JLabel factionLabel = new JLabel(factionIcon);
                        factionLabel.setBounds(iconX + 25, y, 30, 30);
                        factionLabel.setHorizontalAlignment(JLabel.CENTER);
                        factionLabel.setVerticalAlignment(JLabel.CENTER);
                        this.add(factionLabel);
                        iconX += 20;
                    }
                }
                this.add(nodeLabel);
            }
        }
    }

    /**
     * All the edges are drawn to the panel.
     *
     * @param g the Graphics context used for drawing.
     */
    public void drawEdges(Graphics g) {
        if (graph != null) {
            for (Edge edge : graph.getEdges()) {
                Node source = edge.getNode1();
                Node target = edge.getNode2();

                int sourceX = source.getX() + 50;
                int sourceY = source.getY() + 90;
                int targetX = target.getX() + 20;
                int targetY = target.getY() + 30;
                int midX = (sourceX + targetX) / 2;
                int midY = (sourceY + targetY) / 2;
                if (!edge.getArmies().isEmpty()) {
                    for (Army army : edge.getArmies()) {
                        Image factionImage = textureLoader.getTexture(army.getFaction(), 30, 30);
                        ImageIcon factionIcon = new ImageIcon(factionImage);
                        JLabel factionLabel = new JLabel(factionIcon);
                        factionLabel.setBounds(midX, midY, 30, 30);
                        factionLabel.setHorizontalAlignment(JLabel.CENTER);
                        factionLabel.setVerticalAlignment(JLabel.CENTER);
                        this.add(factionLabel);
                        midX += 20;
                        midY += 20;
                    }
                }

                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));
                Color customColor = new Color(245, 164, 66);
                if (edge == graph.getSelectedEdge()) {
                    g2d.setColor(Color.RED);
                } else {
                    g2d.setColor(customColor);
                }
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{5}, 0));
                g2d.drawLine(sourceX, sourceY, targetX, targetY);
            }
        }
    }


    /**
     * when something is updated from the model this method is called.
     */
    @Override
    public void updated() {
        removeAll();
        revalidate();
        repaint();
    }

}
