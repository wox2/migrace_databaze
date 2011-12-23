/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Core.Edge;
import Core.Graph;
import Exceptions.GraphGeneratingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author woxie
 */
public class GraphGenerator {
    private Random rand;
    /**
     * 
     * @param fileName
     */
    public GraphGenerator() {
        rand = new Random();
    }

    public Graph generateGraph(int numberNodes){
        Graph graph=new Graph(numberNodes);
        final int supremum = numberNodes * (numberNodes-1) / 2;
        int nrEdges = rand.nextInt(supremum);
        try {
            graph = generateGraph(numberNodes, nrEdges);
        } catch (GraphGeneratingException ex) {
            System.out.println("Toto by mela osetrit metoda generateGraph(int)");
            System.exit(1);
        }
        return graph;
    }

    public Graph generateGraph(int numberNodes, int numberEdges) throws GraphGeneratingException{
        Graph graph = new Graph(numberNodes);
        final int supremum = numberNodes * (numberNodes-1) / 2;

        if(numberEdges > supremum){
            throw new GraphGeneratingException("Zadany pocet Edge: " + numberEdges + " je vyssi nez pocet Edge v uplnem grafu s " + numberNodes + " Nody." );
        }
        Edge edge;
        int id1;
        int id2;


        for(int i = 0; i < numberEdges ; i++){
            
            id1 = rand.nextInt(numberNodes)+1;
            id2 = rand.nextInt(numberNodes)+1;
            if(id1 == id2 || id1 <=0 || id2 <=0) {
                i--;
                continue;
            }
            edge = new Edge(id1, id2 );
            if(graph.containsEdge(edge)){
                i--;
                continue;
            }
            graph.addEdge(edge);
        }
        return graph;
    }

}
