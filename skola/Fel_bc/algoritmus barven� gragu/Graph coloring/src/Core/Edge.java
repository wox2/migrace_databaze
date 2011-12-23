/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Exceptions.NodeNotIncideException;
import java.io.Serializable;

/**
 * Trida reprezentuje ORIENTOVANOU hranu vedouci do Nodu end.
 * Je tu chyba v tom, ze hrana je pridavana pocatecnimu  Nodu jako atribut, sice dostava pocatecni
 * Node jako parametr, ale uchovava si jen jeho souradnice.
 * @author woxie
 */
public class Edge implements Serializable {

    private String id;
    /**
     * Jeden ze dvou Nodu, s kterymi hrana inciduje.
     */
    private int firstNodeID;
    /**
     * Druhy Node, s kterym hrana inciduje.
     */
    private int secondNodeID;

    /**
     * Konstruktor, vytvori hranu o dane vzdalenosti, nastavi koncovy uzel.
     * @param distance delka hrany
     * @param EndNode Node, kterym je hrana zakoncena
     */
    public Edge(int startNode, int endNode, String idValue) {
        secondNodeID = endNode;
        firstNodeID = startNode;
        id = idValue;
    }

    public Edge(int startNode, int endNode) {
        this(startNode, endNode, startNode + "_" + endNode);
    }

    /**
     * Zjisti, jestli hrana obsahuje Node jako krajni bod.
     * @param node
     * @return
     */
    public boolean isIncidentWith(int nodeId) {
        return (nodeId == firstNodeID || nodeId == secondNodeID);
    }

    /**
     * Vrati souseda Nodu zadaneho jako parametr. Pokud Node neinciduje s hranou, vyhodi vyjimku.
     * @param node
     * @return
     * @throws NodeNotIncideException
     */
    public int getNeighbourId(int nodeId) throws NodeNotIncideException {
        if (!isIncidentWith(nodeId)) {
            throw new NodeNotIncideException(nodeId);
        }
        return (firstNodeID == nodeId ? secondNodeID : firstNodeID);
    }

    /**
     * Vrati id.
     * @return id Edge
     */
    public String getId() {
        return id;
    }

    /**
     * vrati Id jednoho z Nodu.
     * @return
     */
    public int getNodeId() {
        return firstNodeID;
    }

    @Override
    public Edge clone() {
        return new Edge(firstNodeID, secondNodeID, id);
    }

    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        if (((this.firstNodeID == ((Edge) obj).firstNodeID) && (this.secondNodeID == ((Edge) obj).secondNodeID))
                || ((this.firstNodeID == ((Edge) obj).secondNodeID) && (this.secondNodeID == ((Edge) obj).firstNodeID))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.firstNodeID;
        hash = 29 * hash + this.secondNodeID;
        return hash;
    }
}
