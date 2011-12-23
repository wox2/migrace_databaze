/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import GUI.GUIGate;
import java.awt.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sprinkler
 */
public class DataTest {

    public DataTest() {
        Data data = new Data();
        try {
            data.remove(1, 2);
            fail();
        } catch (DataException ex) {
            System.err.println(ex);
        }

        try {
            data.add(new Pawn(new PlayerColor().WHITE), 0, 1);
            data.add(new Pawn(new PlayerColor().WHITE), 2, 3);
            data.add(new Pawn(new PlayerColor().WHITE), 4, 5);
            data.add(new Pawn(new PlayerColor().WHITE), 6, 7);
        } catch (DataException ex) {
            System.err.println(ex);
        }
        Data data2 = new Data(data);

    }

    @BeforeClass
    public static void setUpClass() throws Exception {


    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testAdd_3args() throws Exception {
        Data data = new Data();
        try {
            data.add(new Pawn("black"), -1, 0);
            fail();
            System.out.println(data.get(-1, 0));
        } catch (DataException ex) {
            System.err.println(ex);
        }
        Figure figures[][] = new Pawn[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                figures[i][j] = new Pawn("white");
                data.add(figures[i][j], i, j);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertTrue(data.get(i, j).equals(figures[i][j]));
            }
        }
        try {
            data.add(new Queen("black"), 7, 6);
            fail();
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testAdd_Figure_intArr() throws Exception {
        Data data = new Data();
        try {
            data.add(new Pawn("black"), new int[]{-1, 0});
            fail();
            System.out.println(data.get(new int[]{-1, 0}));
        } catch (DataException ex) {
            System.err.println(ex);
        }
        Figure figures[][] = new Pawn[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                figures[i][j] = new Pawn("white");
                data.add(figures[i][j], new int[]{i, j});
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertTrue(data.get(new int[]{i, j}).equals(figures[i][j]));
            }
        }
        try {
            data.add(new Queen("black"), new int[]{7, 6});
            fail();
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testRemove_intArr() throws Exception {
        Data data = new Data();
        try {
            Figure figures[][] = new Pawn[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    figures[i][j] = new Pawn("white");
                    data.add(figures[i][j], i, j);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    data.remove(i, j);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    assertTrue(data.get(i, j) == null);
                }
            }
        } catch (DataException ex) {
            System.err.println(ex);
        }
        data.add(new Pawn(new PlayerColor().WHITE), 0, 2);
        assertFalse(data.remove(0, 0));
    }

    @Test
    public void testRemove_int_int() throws Exception {
        Data data = new Data();
        try {
            Figure figures[][] = new Pawn[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    figures[i][j] = new Pawn("white");
                    data.add(figures[i][j], i, j);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    data.remove(i, j);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    assertTrue(data.get(new int[]{i, j}) == null);
                }
            }
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testGet_int_int() throws Exception {
        testAdd_3args();
    }

    @Test
    public void testGet_intArr() throws Exception {
        testAdd_Figure_intArr();
    }

    @Test
    public void testClear() {
        Data data = new Data();
        data.clear();
        try {
            data.add(new Pawn("white"), 1, 2);
        } catch (DataException ex) {
            System.err.println(ex);
        }
        data.clear();
        try {
            data.get(1, 2);
            fail();
        } catch (DataException ex) {
            System.err.println(ex);
        }

    }
}