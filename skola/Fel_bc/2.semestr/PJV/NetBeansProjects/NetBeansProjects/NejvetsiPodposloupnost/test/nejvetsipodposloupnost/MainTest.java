/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nejvetsipodposloupnost;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author já
 */
public class MainTest {

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of action method, of class Main.
     */
    @Test
    public void testAction() {
        System.out.println("action");
        ArrayList<Integer> pole = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = Main.action(pole);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nPP method, of class Main.
     */
    @Test
    public void testNPP() {
        System.out.println("nPP");
        ArrayList<Integer> pole = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = Main.nPP(pole);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class Main.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        ArrayList list = null;
        Main.print(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}