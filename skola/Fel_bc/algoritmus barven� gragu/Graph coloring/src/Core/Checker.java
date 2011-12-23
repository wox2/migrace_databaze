/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Exceptions.NodeNotFoundException;
import Interfaces.CheckerInterface;
import Exceptions.NodeNotIncideException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author woxie
 */
public class Checker implements CheckerInterface {

    public Checker (){
    }

    @Override
    public boolean isWellChromed(Graph graph){
       List <Edge> edges = graph.getEdges();
        Node node;
        Node neighbour;
        int neighbourColor;
        int nodeId;
        int color;
        
        //zkontroluje, jestli Nody incidujici s jednou hranou nemaj stejnou barvu
        for(Edge edge:edges){
            try {
                nodeId = edge.getNodeId();
                node = graph.getNode(nodeId);
                color = node.getColor();
                neighbour = graph.getNode(edge.getNeighbourId(node.getID()));
                neighbourColor = neighbour.getColor();
                if (color == neighbourColor ) {
                    return false;
                }
             
            } catch (NodeNotIncideException ex) {
                return false;
            } catch (NodeNotFoundException ex) {
                return false;
            }
        }

        if(!hasAllColors(graph)){
            return false;
        }
        
        return true;
    }


    //zkontroluje, jestli jsou pouzity vsechny barvy mensi nez max
    public boolean hasAllColors(Graph graph){
         /**
         * Horni hranice barev, nastavena jako pocet Nodu
         */
        final int supremum = graph.getChromaticNumber()+1;
        /**
         * Pouzite barvy
         */
        int [] usedColors = new int [supremum+1];
        /**
         * Maximalni pouzita barva
         */
        for(Node node: graph.getNodes()){
            System.out.println("node.color:"+node.getColor());
            usedColors[node.getColor()]=1;
        }
        
        for( int i = 1 ; i < supremum ; i++ ){
            if(usedColors[i]!=1) return false;
        }

        return true;
    }


    public boolean hasSameColors(Graph graph, Graph comparedGraph){
        for(Node node:graph.getNodes()){
            try {
                if (comparedGraph.getNode(node.getID()).getColor() != node.getColor()) {
                    return false;
                }
            } catch (NodeNotFoundException ex) {
                return false;
            }
        }

        return true;
    }

    public boolean isInCollection(Graph graph, Collection<Graph> solutions){
       for(Graph sol:solutions){
            if(hasSameColors(sol, graph)){
                return true;
            }
        }
        return false;
    }

    public void printGraphs(Collection<Graph> collection){
        for(Graph graph:collection){
                graph.printGraph();
        }
    }
}
