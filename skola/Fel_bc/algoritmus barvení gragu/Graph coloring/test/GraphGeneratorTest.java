
import Core.Graph;
import Core.GraphGenerator;
import Exceptions.GraphGeneratingException;
import org.junit.Test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author woxie
 */
public class GraphGeneratorTest {

    @Test
    public void testGenerateWithEdgeParameter(){
        try {
            GraphGenerator generator = new GraphGenerator();
            Graph graph = generator.generateGraph(5, 8);
            System.out.println(graph.getEdgeCount());
            assert(graph.getEdgeCount() == 8);
            

        } catch (GraphGeneratingException ex) {
            assert(false);
        }

    }

}
