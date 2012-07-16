package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 *
 * @author woxie
 */
public class Main {

    
    /**
     * @param args the command line arguments
     */
        //zadany pocet uzlu
        private static int nodesCount;
        //pozadovany pocet komponent
        private static int desiredComponentCount;
        //trojuhelnikova matice sousednosti
        private static EdgeList chosenEdgesList;
        //seznam hran vzestupne serazeny podle vahy
        private static EdgeList edgeListOrderedByWeight;
        
        //seznam komponent, prvni polozka
        private static int[] componentList;
        //vaze se k komponentListu, vyjadruje pocet ruznych komponentu - repr  v nem
        private static int componentListCount;
        
        //suma vah hran vybranych algoritmem
        private static int sumWeights;
        
        private static int inputEdgeVal1;
        private static int inputEdgeVal2;
        
        private static PriorityQueue<Edge> sortedEdges;
        
        
    public static void main(String[] args) throws IOException {
        try {
            readData();
            processDerBoruvka();
            writeData();
        } catch (IOException ex) {
            System.out.println(-1);
        }
    }
    
    public static void readData() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodesCount = Integer.parseInt(br.readLine());
   
        desiredComponentCount = Integer.parseInt(br.readLine());
        Edge readEdge = readEdge(br);
        sortedEdges = new PriorityQueue<Edge> ();
        
        // vytvoreni serazeneho listu hran
        while(!(readEdge.inputNode == 0 && readEdge.outputNode == 0 && readEdge.weight == 0)){
            sortedEdges.add(readEdge); 
                //nacteni dalsi hrany
            readEdge = readEdge(br);
        }
        //edgeListOrderedByWeight.print();
    }
    
    private static Edge readEdge(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        Edge readEdge = new Edge();
        inputEdgeVal1 = Integer.parseInt(st. nextToken());
        inputEdgeVal2 = Integer.parseInt(st. nextToken());
        if(inputEdgeVal1 < inputEdgeVal2 ){
            readEdge.inputNode = inputEdgeVal1;
            readEdge.outputNode = inputEdgeVal2;
        }else{
            readEdge.inputNode = inputEdgeVal2;
            readEdge.outputNode = inputEdgeVal1;
        }
        readEdge.weight = Integer.parseInt(st. nextToken());
        return readEdge;
    }
    
    public static void processDerBoruvka(){
        //init
        chosenEdgesList = new EdgeList();
        componentList = new int [nodesCount];
        // vytvoreni komponent listu obsahujiciho diskretni body
        for(int i = 0 ; i < nodesCount ; i++){
            componentList[i] = i+1;
        }
        componentListCount = nodesCount;
        System.out.println("sorted");
        // iterujem pres hrany
        for(Edge iterEdge : sortedEdges){
//            System.out.println("des:" + desiredComponentCount + " actualCount" + componentListCount);
            System.out.println("E: " + iterEdge.inputNode + " " + iterEdge.outputNode + " " + iterEdge.weight);
            if(desiredComponentCount >= componentListCount){
                break;
            }
            if(!areIncidentNodesInComponent(iterEdge)){
                chosenEdgesList.addEdge(iterEdge);
                sumWeights += iterEdge.weight;
                //merge components
               merge(iterEdge.inputNode, iterEdge.outputNode);
            }
        }
    }
    
    public static void writeData(){
        if(desiredComponentCount != componentListCount ){
            System.out.println(-1);
        } else{
            StringBuilder builder = new StringBuilder();
            builder.append(sumWeights);
            builder.append("\n");
            
            //vypis reprezentanty
            for(int i = 0 ; i < componentList.length; i++){
                // pokud je i sam sobe reprezentant
                if(componentList[i] == i + 1){
                    builder.append(i);
                    builder.append(" ");
                }
            }
            builder.deleteCharAt(builder.lastIndexOf(" "));
            builder.append("\n"); 
            
            Edge iterEdge = chosenEdgesList.head;
            while(iterEdge != null){       
                    builder.append(iterEdge.inputNode);
                    builder.append(" ");
                    builder.append(iterEdge.outputNode);
                    builder.append(" ");
                    builder.append(iterEdge.weight);
                    builder.append("\n"); 
                    iterEdge = iterEdge.next;
            }
            builder.append("0 0 0");
            System.out.println(builder.toString());
        }
    }
    
    private static boolean areIncidentNodesInComponent(Edge edge){
        return componentList[edge.inputNode - 1] == componentList[edge.outputNode - 1];
    }
    
    private static void merge(int representantRemove, int representantMerge){
        for(int i = 0 ; i < componentList.length ; i++){
            if(componentList[i] == representantRemove){
                componentList[i] = representantMerge;
            }
        }
        componentListCount -=1;
    }
}

class Edge implements Comparable<Edge> {
    /** inputNode ma vyssi velikost**/
    public int inputNode;
    /** outputNode ma nizsi velikost**/
    public int outputNode;
    /** vaha hrany **/
    public int weight;
    public Edge next;
    
    @Override
    public int compareTo(Edge edge){
        if(weight < edge.weight){
            return -1;
        }
        if(weight > edge.weight){
            return 1;
        }
        return 0;
    }
}

class EdgeList{
    
    public Edge head;
    public Edge tail;
    int size;
    
    public EdgeList(){
        head = null;
        tail = null;
        size = 0;
    }
    
    public void addEdge(Edge edge){
        Edge copy = new Edge();
        copy.inputNode = edge.inputNode;
        copy.outputNode = edge.outputNode;
        copy.weight = edge.weight;
        
        if(head == null){
            head = copy;
            tail = copy;
            size = 1;
            return;
        }
        size += 1;
        tail.next = copy;
        tail = copy;
    }
    
//    public void print(){
//        Edge iterEdge = head;
//         while(iterEdge != null){
//             System.out.println("E:" + iterEdge.inputNode + " " + iterEdge.outputNode + " " + iterEdge.weight);
//             iterEdge = iterEdge.next;
//         }
//    }
}