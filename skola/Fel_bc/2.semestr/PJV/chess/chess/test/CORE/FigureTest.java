/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import GUI.GUIGate;
import controler.Builder;
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
public class FigureTest {

    Figure figure = new Pawn(new PlayerColor().WHITE);

    public FigureTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testSetPosition() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Point point = new Point(i, j);
                    figure.setPosition(point);
                    assertTrue(figure.getPosition().equals(point));
                } catch (FigureException ex) {
                    Logger.getLogger(FigureTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            figure.setPosition(new Point(-2, 8));
            fail();
        } catch (FigureException ex) {
            System.err.println(ex);
        }

    }

    @Test
    public void testGetPosition() {
        try {
            figure.getPosition();
            fail();
        } catch (FigureException ex) {
            System.err.println(ex);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Point point = new Point(i, j);
                    figure.setPosition(point);
                    assertTrue(figure.getPosition().equals(point));
                } catch (FigureException ex) {
                    Logger.getLogger(FigureTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Test
    public void testGetColor() {
        assertTrue(figure.getColor().equals(new PlayerColor().WHITE));
        figure = new Queen(new PlayerColor().BLACK);
        assertTrue(figure.getColor().equals(new PlayerColor().BLACK));
    }

    @Test
    public void testGetImage() {
        figure = new Pawn(new PlayerColor().BLACK);
        assertTrue(figure.getImage().equals(figure.getPath() + figure.getColor() + "Pawn.gif"));
        figure = new Queen(new PlayerColor().BLACK);
        assertTrue(figure.getImage().equals(figure.getPath() + figure.getColor() + "Queen.gif"));
        figure = new King(new PlayerColor().BLACK);
        assertTrue(figure.getImage().equals(figure.getPath() + figure.getColor() + "King.gif"));
        figure = new Bishop(new PlayerColor().BLACK);
        assertTrue(figure.getImage().equals(figure.getPath() + figure.getColor() + "Bishop.gif"));
        figure = new Rook(new PlayerColor().BLACK);
        assertTrue(figure.getImage().equals(figure.getPath() + figure.getColor() + "Rook.gif"));
        figure = new Knight(new PlayerColor().BLACK);
        assertTrue(figure.getImage().equals(figure.getPath() + figure.getColor() + "Knight.gif"));
    }

    @Test
    public void testResetProvisionalValues() {
        figure.resetProvisionalValues();
        assertFalse(figure.checkMove);
        assertFalse(figure.checkMoveWithoutFigure);
        assertTrue(figure.newPosition == null);
        assertTrue(figure.oldPosition == null);
    }

    @Test
    public void testGetPath() {
        assertTrue(figure.getPath().equals("image\\"));
    }
}