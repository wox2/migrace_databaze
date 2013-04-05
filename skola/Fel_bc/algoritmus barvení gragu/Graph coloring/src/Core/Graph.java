/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Exceptions.NodeNotIncideException;
import java.util.ArrayList;
import java.util.List;
import Exceptions.NodeNotFoundException;
import java.io.Serializable;

/**
 * Trida realizuje model strutktury nazvane graf.
 * @author woxie
 */
public class Graph implements Serializable{

    /**
     * List Nodu, ktere Graf obsahuje.
     */
    private List<Node> nodes;

    /**
     * List neorientovanych hran grafu.
     */
    private List<Edge> edges;

     /**
     * Pocet uzlu v grafu.
     */

    private int numberOfNodes;

    /**
     * Chromaticke cislo grafu - pocet barev, kterymi je obarvitelny.
     */

    private int chromaticNumber;
    /**
     * Obecny konstruktor. Nastavuje pocet uzlu grafu na 0. Chromaticke cislo take nastaveno na 0.
     */


    public Graph() {
        nodes = new ArrayList<Node>();
        numberOfNodes = 0;
        chromaticNumber = 0;
        edges = new ArrayList<Edge>();
    }

    public Graph(int nodesCount){
        this();
        if( nodesCount <=0 ) return;
        for(int i=0 ; i < nodesCount ; i++){
            addNode(new Node(i+1));
        }
    }

    /**
     * Vraci List Nodu grafu.
     * @return
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Metoda pridava uzel do grafu.
     * @param node Node, ktery ma byt pridan do Grafu
     */
    public void addNode(int id) {
        nodes.add(new Node(id));
        numberOfNodes++;
    }

    private void addNode(Node node){
        nodes.add(node.clone());
        numberOfNodes++;
    }

    /**
     * Metoda navraci pocet uzlu v grafu.
     * @return pocet Nodu v grafu
     */
    public int getNodesCount() {
        return numberOfNodes;
    }

    /**
     * Navrati node s zadanym id. V nynejsi verzi metoda vyhazuje vyjimku
     * @param id ID Nodu ktery se ma vratit
     * @return Node s zadanym jmenem
     */
    public Node getNode(int id) throws NodeNotFoundException {
        Node node;

        for (int i = 0; i < numberOfNodes; i++) {
            node = nodes.get(i);
            if (node.getID()==id) {
                return node;
            }
        }
         throw new NodeNotFoundException(id);
    }

    @Override
    public Graph clone() {
        Graph graph = new Graph();
        graph.chromaticNumber = this.chromaticNumber;
    
        for(Edge edge:edges){
            graph.addEdge(edge);
            }

        for(Node node:nodes){
            graph.addNode(node);
        }
       
 return graph;
        }
       
    

    /**
     * Nastavi chromaticke cislo na nejvyssi hodnotu obarveneho Nodu
     */
    public void setMinimalColorAsMaximalUsedColor(){
        int max=0;
        int color;
        for(Node node:nodes){
            color = node.getColor();
            if(max < color){
                max = color;
            }
        }
        chromaticNumber = max;
    }
    
    /**
    * Vrati Chromaticke cislo grafu.
    * @return
    */
    public int getChromaticNumber(){
        return chromaticNumber;
    }

    /**
     * Vrati List hran grafu.
     * @return
     */
    public List<Edge> getEdges(){
        return edges;
    }

    /**
     * Prida Grafu hranu edge
     * @param edge pridana hrana
     */
    public void addEdge(Edge edge){
        edges.add(edge.clone());
    }

    public void addEdge(int idNode1, int idNode2){
        edges.add(new Edge(idNode1, idNode2, idNode1 + "_" + idNode2));
    }

    /**
     * Odbarvi vsechny Nody Graphu
     */
    public void uncolor(){
        for(Node node:nodes){
            node.setColor(0);
        }
    }

    
    public void setColor(Node node, int color){
        node.setColor(color);
    }

    public void setColor(int nodeId, int color){
        for(Node node:nodes){
            if(node.getID() == nodeId) {
                node.setColor(color);
                break;
            }
        }
    }

    /**
     *  Zjisti, jestli ma graph nejake Nody
     * @return
     */
    public boolean hasNoNodes(){
        return nodes.isEmpty();
    }

    public void printGraph(){
        printNodes();
        printEdges();
        System.out.println("Chromaticke cislo: " + chromaticNumber);
        System.out.println("==========================================================================");
    }

    public void printShort(){
        printNodes();
        System.out.println("Chromaticke cislo: " + chromaticNumber);
        System.out.println("==========================================================================");
    }

    public void printNodes(){
        int degree=0;
        for(Node node:nodes){
            System.out.print("Node: " + node.getID() + "  Barva: " + node.getColor());
            degree = 0;
            for(Edge edge:edges){
                if(edge.isIncidentWith(node.getID())){
                    degree++;
                }
            }
            System.out.println("   Stupen: "+ degree);

        }
    }

    public void printEdges(){
        for(Edge edge:edges){
            try {
                System.out.println("Edge: " + edge.getId() + " s krajnimi body s ID: " + edge.getNodeId() + " a " + edge.getNeighbourId(edge.getNodeId()));
            } catch (NodeNotIncideException ex) {

            }
        }
    }

    public void setColorOneToAllNodes(){
        for(Node node:nodes){
            node.setColor(1);
        }
    }

    public boolean containsEdge(Edge edge){
        for(Edge tempEdge:edges){
            if(tempEdge.equals(edge)){
                return true;
            }
                }
            return false;
    }

    public int getEdgeCount(){
        return edges.size();
    }
}
