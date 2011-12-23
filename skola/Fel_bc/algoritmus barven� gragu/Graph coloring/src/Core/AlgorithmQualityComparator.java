/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Exceptions.NodeNotFoundException;
import Interfaces.AlgorithmQualityComparatorInterface;
import Interfaces.ColoringAlgorithmInterface;
import Interfaces.PriorityQueueComparatorInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author woxie
 */
public class AlgorithmQualityComparator implements AlgorithmQualityComparatorInterface {

    private List<ColoringAlgorithmInterface> coloringAlgorithmList;
    private int minimum;
    // private List<> results;
    private int results;

    public AlgorithmQualityComparator() {
        coloringAlgorithmList = new ArrayList<ColoringAlgorithmInterface>();
        minimum = 0;
    }

    public void addAlgorithmToBeCompared(ColoringAlgorithmInterface coloringAlgorithm) {
        coloringAlgorithmList.add(coloringAlgorithm);
    }

    public ColoringAlgorithmInterface getBestColoringAlgorithm() {
        return null;
    }

    public void startQualifyAlgorithms(Graph graph) {
        MinimalChromaticNumberFinder minimalChromaticNumberFinder = new MinimalChromaticNumberFinder(graph);
        minimum = minimalChromaticNumberFinder.getMinChromNumber();

        for (ColoringAlgorithmInterface coloringAlgorithm : coloringAlgorithmList) {
        }

    }

    private double testColoringAlgorithm(int expectedChromaticNumber, ColoringAlgorithmInterface algorithm, Graph g) {
        int success = 0;
        int overColoring = 0;
        for (int i = 1; i <= g.getNodesCount(); i++) {
            Graph sol = algorithm.getSolution();
            int obtainedChromaticNumber = sol.getChromaticNumber();
            if (obtainedChromaticNumber == expectedChromaticNumber) {
                success++;
            }
            overColoring += obtainedChromaticNumber - expectedChromaticNumber;
        }
        return ((double) success) / g.getNodesCount();
    }

    public ColoringAlgorithmResult testPriorityComparatorMethod(int expectedChromaticNumber, PriorityQueueComparatorInterface comparatorInterface, Graph graph) {
        int success = 0;
        int overColoring = 0;
        int maxDifference = 0;
        int difference = 0;
        int obtainedChromaticNumber = 0;
        int highestColorCount = 0;
        int minDifference = graph.getNodesCount();
        for (int i = 1; i <= graph.getNodesCount(); i++) {
            try {
                //highestColorCount = 0;
                MyAlgorithm algorithm = new MyAlgorithm(graph, graph.getNode(i), comparatorInterface);
                Graph sol = algorithm.getSolution();
                obtainedChromaticNumber = sol.getChromaticNumber();
                difference = obtainedChromaticNumber - expectedChromaticNumber;
                if(difference < minDifference) {
                    minDifference = 0;
                }

                if (difference == 0) {
                    success++;
                } else {
                    overColoring += difference;

                    for(int j = 1 ; j< sol.getNodesCount(); j++){
                    if(sol.getNode(j).getColor() == difference) {
                        highestColorCount++;
                    }
                }
                    if (maxDifference < difference) {
                        maxDifference = difference;
                    }

                }     

            } catch (NodeNotFoundException ex) {
            }
        }
        Node node = null;
        try {
            node = graph.getNode(1);
        } catch (NodeNotFoundException ex) {
            Logger.getLogger(AlgorithmQualityComparator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ColoringAlgorithmResult(new MyAlgorithm(graph, node, comparatorInterface), (double) success / graph.getNodesCount(), overColoring, maxDifference, highestColorCount, minDifference);
    }
}
