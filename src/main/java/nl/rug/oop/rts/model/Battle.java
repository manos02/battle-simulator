package nl.rug.oop.rts.model;

import nl.rug.oop.rts.model.faction.Army;

import java.util.List;
import java.util.ArrayList;

/**
 * This class models a Battle between two armies in a Graph.
 * Each Battle consists of a Graph and two List of Armies representing the two teams.
 */
public class Battle {
    private final Graph graph;
    private final List<Army> team1;
    private final List<Army> team2;

    /**
     * constructor of the battle class.
     *
     * @param graph of the game.
     */
    public Battle(Graph graph) {
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        this.graph = graph;
    }

    public void addArmyTeam1(Army army) {
        team1.add(army);
    }

    public void addArmyTeam2(Army army) {
        team2.add(army);
    }

    /**
     * Calculates the total attack power of a List of Armies.
     *
     * @param army the List of Armies whose attack power is to be calculated.
     * @return the total attack power.
     */
    private int calculateTotalAttackPower(List<Army> army) {
        int totalAttackPower = 0;
        for (Army armies : army) {
            for (Unit unit : armies.getUnits()) {
                totalAttackPower += unit.getDamage();
            }
        }
        return totalAttackPower;
    }

    /**
     * Simulates a battle between two teams and returns the result.
     *
     * @param team1 the first team.
     * @param team2 the second team.
     * @return 0 if both teams are destroyed in the same round, 1 if team1 wins, and 2 if team2 wins.
     */
    private int teamBattle(List<Army> team1, List<Army> team2) {
        int healthTeam1 = calculateTotalHealth(team1);
        int healthTeam2 = calculateTotalHealth(team2);
        int damageTeam1 = calculateTotalAttackPower(team1);
        int damageTeam2 = calculateTotalAttackPower(team2);

        while (healthTeam1 > 0 && healthTeam2 > 0) {
            healthTeam1 -= damageTeam2;
            healthTeam2 -= damageTeam1;
        }

        if (healthTeam1 <= 0 && healthTeam2 <= 0) {
            return 0;
        } else if (healthTeam1 > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Calculates the total health of a List of Armies.
     *
     * @param army the List of Armies whose health is to be calculated.
     * @return the total health.
     */
    private int calculateTotalHealth(List<Army> army) {
        int totalHealth = 0;
        for (Army armies : army) {
            for (Unit unit : armies.getUnits()) {
                totalHealth += unit.getHealth();
            }
        }
        return totalHealth;
    }

    /**
     * Conducts battles for each Node in the Graph.
     * Teams are assembled based on the team property of Armies present at each Node.
     * The losing team is then removed from the Node.
     */
    public void armyNodeBattle() {
        for (Node node : graph.getNodes()) {
            List<Army> armies = new ArrayList<>(node.getArmies());
            if (!armies.isEmpty()) {
                for (Army army : armies) {
                    if (army.getTeam() == 1) {
                        addArmyTeam1(army);
                    } else {
                        addArmyTeam2(army);
                    }
                }
                if (!team1.isEmpty() && !team2.isEmpty()) {
                    if (teamBattle(team1, team2) == 1) {
                        for (Army army : team2) {
                            node.removeArmyFromNode(army);
                        }
                    } else if (teamBattle(team1, team2) == 2) {
                        for (Army army : team1) {
                            node.removeArmyFromNode(army);
                        }
                    } else {
                        for (Army army : team1) {
                            node.removeArmyFromNode(army);
                        }
                        for (Army army : team2) {
                            node.removeArmyFromNode(army);
                        }
                    }
                }
            }
            team1.clear();
            team2.clear();
        }
    }

    /**
     * Conducts battles for each Edge in the Graph.
     * Teams are assembled based on the team property of Armies present at each Edge.
     * The losing team is then removed from the Edge.
     */
    public void armyEdgeBattle() {
        for (Edge edge : graph.getEdges()) {
            List<Army> armies = new ArrayList<>(edge.getArmies());
            if (!armies.isEmpty()) {
                for (Army army : armies) {
                    if (army.getTeam() == 1) {
                        addArmyTeam1(army);
                    } else {
                        addArmyTeam2(army);
                    }
                }
                if (!team1.isEmpty() && !team2.isEmpty()) {
                    if (teamBattle(team1, team2) == 1) {
                        for (Army army : team2) {
                            edge.removeArmyFromEdge(army);
                        }
                    } else if (teamBattle(team1, team2) == 2) {
                        for (Army army : team1) {
                            edge.removeArmyFromEdge(army);
                        }
                    } else {
                        for (Army army : team1) {
                            edge.removeArmyFromEdge(army);
                        }
                        for (Army army : team2) {
                            edge.removeArmyFromEdge(army);
                        }
                    }
                }
            }
            team1.clear();
            team2.clear();
        }
    }

}

