package Core;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Exceptions.AlgorithmFinishedException;
import Exceptions.GraphGeneratingException;
import QueueComparators.FirstLowerByFindedPriority;
import QueueComparators.FirstLowerByFindedPriorityThenNodeDegree;
import Exceptions.NodeNotFoundException;
import Interfaces.PriorityQueueComparatorInterface;
import QueueComparators.FirstHigherByFindedPriority;
import QueueComparators.FirstLowerByNodeDegree;
import QueueComparators.FirstLowerByNodeDegreeThenFindedPriority;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author woxie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GraphGenerator generator = new GraphGenerator();
            Graph graph = generator.generateGraph(16, 25);
            graph.printShort();
            graph.printEdges();
            MinimalChromaticNumberFinder minimal = new MinimalChromaticNumberFinder(graph);

            int minimum = minimal.getMinChromNumber();
            minimal.getColoredGraph().printGraph();
            minimal.getColoredGraph().printEdges();


            FirstHigherByFindedPriority comp1 = new FirstHigherByFindedPriority();
            FirstLowerByFindedPriority comp2 = new FirstLowerByFindedPriority();
            FirstLowerByFindedPriorityThenNodeDegree comp3 = new FirstLowerByFindedPriorityThenNodeDegree();
            FirstLowerByNodeDegree comp4  = new FirstLowerByNodeDegree();
            FirstLowerByNodeDegreeThenFindedPriority comp5 = new FirstLowerByNodeDegreeThenFindedPriority();

            AlgorithmQualityComparator qualityComparator = new AlgorithmQualityComparator();

            qualityComparator.testPriorityComparatorMethod(minimum, comp1, graph).printResults();
            qualityComparator.testPriorityComparatorMethod(minimum, comp2, graph).printResults();
            qualityComparator.testPriorityComparatorMethod(minimum, comp3, graph).printResults();
            qualityComparator.testPriorityComparatorMethod(minimum, comp4, graph).printResults();
            qualityComparator.testPriorityComparatorMethod(minimum, comp5, graph).printResults();


        } catch (GraphGeneratingException ex) {
            System.err.println("Generator nemohl nagenerovat graf s zadanymi parametry");
            System.exit(1);
        }

    }

    static double testMyAlgo(int expectedChromaticNumber, PriorityQueueComparatorInterface comparatorMethod, Graph g) {
        int success = 0;
        for (int i = 1; i <= g.getNodesCount(); i++) {
            try {
                MyAlgorithm algo = new MyAlgorithm(g, g.getNode(i), comparatorMethod);
                //  System.out.println("Reseni pro Node: " + i);
                Graph sol = algo.getSolution();
                if (sol.getChromaticNumber() == expectedChromaticNumber) {
                    success++;
                } else {
                    // sol.printShort();
                }

            } catch (NodeNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Neocekavana chyba - Node nenalezen");
                System.exit(1);
            }
        }
        return ((double) success) / g.getNodesCount();
    }

    static void testAlgo(Graph g) {
        MinimalChromaticNumberFinder fast = new MinimalChromaticNumberFinder(g);
        int min = fast.getMinChromNumber();
        System.out.println("Minimalni chromaticke cislo je: " + min);

        double successRate = testMyAlgo(min, new FirstLowerByFindedPriority(), g);
        System.out.println("Uspesnost pro FirstLowerByFindedPriority: " + successRate);

        successRate = testMyAlgo(min, new FirstLowerByFindedPriorityThenNodeDegree(), g);
        System.out.println("Uspesnost pro FirstLowerByFindedPriorityThenNodeDegree: " + successRate);

        successRate = testMyAlgo(min, new FirstLowerByNodeDegree(), g);
        System.out.println("Uspesnost pro FirstLowerByNodeDegree: " + successRate);

        successRate = testMyAlgo(min, new FirstLowerByNodeDegreeThenFindedPriority(), g);
        System.out.println("Uspesnost pro FirstLowerByNodeDegreeThenFindedPriority: " + successRate);

        successRate = testMyAlgo(min, new FirstHigherByFindedPriority(), g);
        System.out.println("Uspesnost pro FirstHigherByFindedPriority: " + successRate);
    }
}
