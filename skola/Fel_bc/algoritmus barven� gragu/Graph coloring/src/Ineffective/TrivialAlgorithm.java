/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ineffective;
import Core.Checker;
import Core.Graph;
import Core.Node;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author woxie
 */
public class TrivialAlgorithm {

    private Graph graph;
    /**
     * Minimalni reseni
     */
 //   private List<Graph> minimalSolutions;
    /**
     * List neoptimalnich reseni Barveni.
     */
 //   private List<Graph> nonOptimalSolutions;
    /**
     * List Nodu obarvovaneho grafu
     */
    private List<Node> nodes;
    /**
     *  Minimalni chromaticke cislo
     */
    private int minimumChromNumber;
    /**
     * kontrolor spravnych reseni
     */
    private Checker checker;

//    private boolean finished;
//
//    private int testedColorValue;

    public TrivialAlgorithm(Graph originalGraph){
        graph = originalGraph.clone();
        checker = new Checker();
        nodes = graph.getNodes();
   //     minimalSolutions = new ArrayList<Graph>();
   //     nonOptimalSolutions = new ArrayList<Graph>();
        minimumChromNumber = graph.getNodesCount();
      //  finished = false;
    }

    public void setColorToAllNodes(int i){
        for(Node node:nodes){
            node.setColor(1);
        }
    }

    //Pokud je obarvitelny jednou barvou
    public void process(){
        setColorToAllNodes(1);
   
        if(checker.isWellChromed(graph)){
            graph.setMinimalColorAsMaximalUsedColor();
            minimumChromNumber = 1;
//            minimalSolutions.add(graph.clone());
        }
        else{
            while(changeColor()){
                graph.setMinimalColorAsMaximalUsedColor();
                
                if(checker.isWellChromed(graph) && graph.getChromaticNumber() < minimumChromNumber){
//                    {
//                        graph.checkAndSetMinimalChromaticNumber();
//                        minimalSolutions.add(graph.clone());
//
//                        if(graph.getChromaticNumber() < minimumChromNumber){
//                            minimumChromNumber = graph.getChromaticNumber();
//                        }
//
//                    }
                    minimumChromNumber = graph.getChromaticNumber();
                }
            }

//            List<Graph> helper = (List<Graph>)((ArrayList<Graph>)minimalSolutions).clone();
//
//            //Kontrola, ze je v reseni opravdu minimalni chromaticke cislo
//            for(Graph tempSolution:helper){
//                if(!hasMinimalChromaticNumber(tempSolution)){
//                //    nonOptimalSolutions.add(tempSolution);
//                    minimalSolutions.remove(tempSolution);
//                }
//            }

      //      helper = (List<Graph>)((ArrayList<Graph>)nonOptimalSolutions).clone();

        }
    }

//    private boolean hasMinimalChromaticNumber(Graph graph){
//        if(minimumChromNumber == graph.getChromaticNumber()) return true;
//        return false;
//    }

    /**
     * Navysuje barvu Nodu v grafu
     * @param graph
     * @return zdali se operace provedla.
     */
    public boolean changeColor(){
        int color=0;
        //int supremum = graph.getNodesCount();
        for(Node node:nodes){
            color = node.getColor();
            if(color < minimumChromNumber){
                node.setColor(color+1);
                return true;
            } else {
                node.setColor(1);
            }
        }
       return false;
    }

//    public boolean changeColor(){
//        int color=0;
//
//        return false;
//    }


  /*  public void printSolutions(){
        System.out.println("Reseni: ");
        for(Graph temp:minimalSolutions){
            System.out.println("-----------------------------");
            for(Node node:temp.getNodes()){
                System.out.println("Node "+ node.getID()+ " ma barvu " + node.getColor());
            }
        }
    }
*/
    /*
    public List<Graph> getSolutions(){
        List<Graph> solutionsCopy = new ArrayList<Graph>();
        for(Graph tempGraph:minimalSolutions){
            solutionsCopy.add(tempGraph);
        }
        return solutionsCopy;
    }

   /* public List<Graph> getNonOptimalSOlutions(){
        List<Graph> nonOptCopy = new ArrayList<Graph>();
        for(Graph tempGraph:nonOptimalSolutions){
            nonOptCopy.add(tempGraph);
        }
        return nonOptCopy;
    }*/

        public int getMinimalChromaticNumber(){
            return minimumChromNumber;
        }
}
