/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Exceptions.AlgorithmFinishedException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author woxie
 */
public class MinimalChromaticNumberFinder {
    private Graph coloredGraph;
   // private int chosenColoringNumber;
    private Checker checker;
    private List<Node>coloredNodes;
  //  int count;
    private NonIzomorphSequenceColoredNodesGenerator sequenceGenerator;
    int minimum;

    public MinimalChromaticNumberFinder (Graph graph){
        coloredGraph = graph.clone();
      //  chosenColoringNumber=1;
        coloredGraph.setColorOneToAllNodes();
        coloredNodes = coloredGraph.getNodes();
        checker = new Checker();
        sequenceGenerator = new NonIzomorphSequenceColoredNodesGenerator(coloredGraph.getNodesCount());
    //    count = 0;
    }

    public int getMinChromNumber(){
        if(coloredNodes.isEmpty()) return 0;
        process();
        return minimum;
    }

    private void process(){
        try {
        while(true){
                int [] pole = sequenceGenerator.getNext();
                Graph temp = coloredGraph.clone();

                for(int i= 0;i<pole.length;i++){
                    temp.setColor(i, pole[i]);
                }
                if(temp.getChromaticNumber() < minimum && checker.isWellChromed(temp)){
                    coloredGraph = temp;
                    minimum = coloredGraph.getChromaticNumber();
                }
        }
        } catch (AlgorithmFinishedException ex) {
            }
    }

    public void reset(){
        coloredGraph.setColorOneToAllNodes();
    }

    public Graph getColoredGraph(){
        return coloredGraph;
    }

}
