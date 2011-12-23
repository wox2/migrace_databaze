/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Position;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sprinkler
 */
public class PlayerDataTest {

    public PlayerDataTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetPlayerColor() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().WHITE);
            assertTrue(playerData.getPlayerColor().equals(new PlayerColor().WHITE));
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
        try {
            PlayerData playerData2 = new PlayerData("wadsf");
            fail();
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testIsJustPlaying() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().WHITE);
            assertTrue(playerData.isJustPlaying());
            playerData.setJustPlaying(false);
            assertFalse(playerData.isJustPlaying());
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }

    }

    @Test
    public void testSetJustPlaying() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().BLACK);
            assertFalse(playerData.isJustPlaying());
            playerData.setJustPlaying(true);
            assertTrue(playerData.isJustPlaying());
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }

    }

    @Test
    public void testGetClickedFigure() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().BLACK);
            int[] clickedFigure = playerData.getClickedFigure();
            assertTrue((clickedFigure[0] == -1) && (clickedFigure[1] == -1));
            List<Point> move = new ArrayList<Point>();
            move.add(new Point(0, 1));

            playerData.setClickedFigure(new int[]{5, 6}, move);
            assertTrue((playerData.getClickedFigure()[0] == 5) && (playerData.getClickedFigure()[1] == 6));
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testSetClickedFigure() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().BLACK);
            List<Point> move = new ArrayList<Point>();
            move.add(new Point(0, 1));
            playerData.setClickedFigure(new int[]{0, -6}, move);
            fail();
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().BLACK);
            List<Point> move = new ArrayList<Point>();
            move.add(new Point(0, 1));
            move.add(new Point(-5, 1));
            playerData.setClickedFigure(new int[]{0, 0}, move);
            fail();
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testGetMovesClickedFigure() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().BLACK);
            List<Point> move = new ArrayList<Point>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    move.add(new Point(i, j));
                }
            }
            playerData.setClickedFigure(new int[]{0, 0}, move);
            List<Point> getMove = playerData.getMovesClickedFigure();
            int k = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    assertTrue(((getMove.get(k)).x == i) && ((getMove.get(k)).y == j));
                    k++;
                }
            }
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testNullClickedFigure() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().BLACK);
            List<Point> move = new ArrayList<Point>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    move.add(new Point(i, j));
                }
            }
            playerData.setClickedFigure(new int[]{0, 0}, move);
            playerData.nullClickedFigure();
            int[] clickedFigure = playerData.getClickedFigure();
            List<Point> getMove = playerData.getMovesClickedFigure();
            assertTrue((clickedFigure[0] == -1) && (clickedFigure[1] == -1));
            assertTrue(getMove.size() == 0);
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testIsTakeFigure() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().BLACK);
            assertFalse(playerData.isTakeFigure());
            playerData.setTakeFigure(false);
            assertFalse(playerData.isTakeFigure());
            playerData.setTakeFigure(true);
            assertTrue(playerData.isTakeFigure());
            assertFalse(playerData.isTakeFigure());
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testSetTakeFigure() {
        testIsTakeFigure();
    }

    @Test
    public void testGetOponentColor() {
        try {
            PlayerData playerData = new PlayerData(new PlayerColor().WHITE);
            assertTrue(playerData.getOponentColor().equals(new PlayerColor().BLACK));
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
        try {
            PlayerData playerData3 = new PlayerData(new PlayerColor().BLACK);
            assertTrue(playerData3.getOponentColor().equals(new PlayerColor().WHITE));
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
        try {
            PlayerData playerData2 = new PlayerData("wadsf");
            fail();
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
    }
}