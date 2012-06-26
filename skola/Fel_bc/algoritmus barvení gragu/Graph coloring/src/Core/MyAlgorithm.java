/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Interfaces.ColoringAlgorithmInterface;
import Exceptions.AlgorithmFinishedException;
import Exceptions.NodeNotFoundException;
import Exceptions.NodeNotIncideException;
import Exceptions.PriorityQueueEmptyException;
import Interfaces.PriorityQueueComparatorInterface;
import Interfaces.SteppableInterface;
import java.util.List;

/**
 *
 * @author woxie
 */
public class MyAlgorithm implements SteppableInterface, ColoringAlgorithmInterface {

    private Graph graph;
    private PriorityNode actualNode;
    private PriorityQueue queue;
    private int[] restrictedColors;
    private int supremum;
    private List<Edge> edges;
    //   private List<Node> nodes;

    public MyAlgorithm(Graph coloredGraph, Node node, PriorityQueueComparatorInterface comparator) {
        graph = coloredGraph.clone();

        try {
            //graph.setColorOneToAllNodes();
            actualNode = new PriorityNode(graph.getNode(node.getID()));
            setNodeDegreeInNode(actualNode, graph);
        } catch (NodeNotFoundException ex) {
            System.out.println("Node se stejnym id v klonovanym grafu by mel byt, toto by se stat nemelo");
            System.exit(1);
        }

        queue = new PriorityQueue(graph.getNodesCount() - 1);
        queue.setPriorityComparator(comparator);

        for (Node tempNode : graph.getNodes()) {
            if (tempNode.getID() != node.getID()) {
                PriorityNode temp = new PriorityNode(tempNode);
                setNodeDegreeInNode(temp, graph);
                queue.enqueue(temp);
            }
        }
        edges = graph.getEdges();
        supremum = graph.getNodesCount();
    }

    @Override
    public Graph doStep() throws AlgorithmFinishedException {
        try {
            setAvailableColor(actualNode);
            actualNode = (PriorityNode) queue.dequeue();
            return graph;
        } catch (PriorityQueueEmptyException ex) {
            throw new AlgorithmFinishedException();
        }
    }

    @Override
    public void reset() {
        graph.uncolor();
        graph.setMinimalColorAsMaximalUsedColor();
    }

    /**
     * Nastavi spravnou barvu nodu a aktualizuje prioritni frontu
     * @param node
     */
    private void setAvailableColor(PriorityNode node) {
        restrictedColors = new int[supremum + 1];
        Node temp = node.getNode();
        int neighbourId;
        Node neighbour;
        for (Edge edge : edges) {
            if (edge.isIncidentWith(temp.getID())) {
                try {
                    //restrictedColors[temp.getColor()] = 1;
                    neighbourId = edge.getNeighbourId(temp.getID());
                    neighbour = graph.getNode(neighbourId);
                    restrictedColors[neighbour.getColor()] = 1;
                    queue.actualize(neighbourId);
                } catch (NodeNotFoundException ex) {
                    System.out.println(ex);
                    System.exit(1);
                } catch (NodeNotIncideException ex) {
                    System.out.println(ex);
                    System.exit(1);
                }
            }
        }
        int color = 0;

        for (color = 1; color < supremum; color++) {
            if (restrictedColors[color] == 0) {
                break;
            }
        }
        temp.setColor(color);
        graph.setMinimalColorAsMaximalUsedColor();
    }

    public Graph getSolution() {
        Graph temp = null;
        try {
            while (true) {
                temp = doStep();
            }
        } catch (AlgorithmFinishedException ex) {
            return temp;
        }
    }

    private static void setNodeDegreeInNode(PriorityNode node, Graph graph){
        int degree = 0;
        for(Edge edge:graph.getEdges()){
            if(edge.isIncidentWith(node.getId())){
                degree++;
            }
        }
        node.setNodeDegree(degree);
    }

   public void setStartNode(){
        
   }

}
