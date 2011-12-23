/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

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
public class RulesTest {

    public RulesTest() {
        Rules rules = new Rules();
        rules.standartPosition();
        rules.isCheck(0, 0, new PlayerColor().WHITE, 5, 6, 1, 2);
        rules.isKingInCheck(new PlayerColor().WHITE);
        rules.isKingInCheck(new PlayerColor().WHITE, 5, 6, 1, 2);
        rules.isKingInCheckOrCheckmate(new PlayerColor().WHITE);
        rules.isKingInCheckmate(new PlayerColor().WHITE);
        rules.isPossibleAnyMove(new PlayerColor().WHITE);
        rules.isStalemate(new PlayerColor().WHITE);
        rules.setMove(new int[]{0,5});
        rules.isCheck(0, 0, new PlayerColor().WHITE);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testStandartPosition() {
        try {
            Rules rules = new Rules();
            rules.standartPosition();
            COREGate gate = new COREGate();
            gate.startNewGame(new PlayerColor().WHITE);
            rules.standartPosition();
            PlayerData playerData = COREGate.getPlayerData();
            testColorStandartPosition();
            testFirtstLineStandartPosition(7);
            testFirtstLineStandartPosition(0);
            testSecoundLineStandartPosition(6);
            testSecoundLineStandartPosition(1);
            gate.startNewGame(new PlayerColor().BLACK);
            testFirtstLineStandartPosition(7);
            testFirtstLineStandartPosition(0);
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
    }

    private void testColorStandartPosition() {
        try {
            Data data = COREGate.getData();
            PlayerData playerData = COREGate.getPlayerData();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (j < 2) {
                        assertTrue(data.get(i, j).getColor().equals(playerData.getOponentColor()));
                    } else if ((j > 1) && (j < 6)) {
                        assertTrue(data.get(i, j) == null);
                    } else if (j > 6) {
                        assertTrue(data.get(i, j).getColor().equals(playerData.getPlayerColor()));
                    }
                }
            }
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    private void testFirtstLineStandartPosition(int y) {
        try {
            PlayerData playerData = COREGate.getPlayerData();
            assertTrue(COREGate.getData().get(0, y) instanceof Rook);
            assertTrue(COREGate.getData().get(7, y) instanceof Rook);
            assertTrue(COREGate.getData().get(1, y) instanceof Knight);
            assertTrue(COREGate.getData().get(6, y) instanceof Knight);
            assertTrue(COREGate.getData().get(2, y) instanceof Bishop);
            assertTrue(COREGate.getData().get(5, y) instanceof Bishop);
            int king, queen;
            if (playerData.getPlayerColor().equals(new PlayerColor().WHITE)) {
                king = 3;
                queen = 4;
            } else {
                king = 4;
                queen = 3;
            }
            assertTrue(COREGate.getData().get(king, y) instanceof King);
            assertTrue(COREGate.getData().get(queen, y) instanceof Queen);
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    private void testSecoundLineStandartPosition(int y) {
        for (int i = 0; i < 8; i++) {
            try {
                assertTrue(COREGate.getData().get(i, y) instanceof Pawn);
            } catch (DataException ex) {
                System.err.println(ex);
            }
        }
    }

    @Test
    public void testSetMove() {
        try {
            COREGate gate = new COREGate();
            gate.startNewGame(new PlayerColor().WHITE);
            Data data = COREGate.getData();
            Rules rules = new Rules();
            rules.standartPosition();

            testPawnMove();
            testLeftCastlingMove();
            testRightCastlingMove();
            testCheckMove();

//        } catch (PlayerDataException ex) {
//            System.err.println(ex);
        } catch (DataException ex) {
            System.err.println(ex);
        }

    }

    private void testPawnMove() {
        try {
            Rules rules = new Rules();
            assertFalse(rules.setMove(new int[]{0, 6}));
            assertTrue(rules.setMove(new int[]{0, 4}));
            COREGate.getPlayerData().setJustPlaying(true);
            assertFalse(rules.setMove(new int[]{0, 4}));
            assertTrue(rules.setMove(new int[]{0, 3}));
            COREGate.getPlayerData().setJustPlaying(true);
            assertFalse(rules.setMove(new int[]{0, 3}));
            assertFalse(rules.setMove(new int[]{0, 3}));
            assertFalse(rules.setMove(new int[]{0, 3}));
            assertFalse(rules.setMove(new int[]{2, 3}));
            assertFalse(rules.setMove(new int[]{0, 3}));
            assertFalse(rules.setMove(new int[]{0, 0}));
            assertFalse(rules.setMove(new int[]{0, 3}));
            assertTrue(rules.setMove(new int[]{0, 2}));
            COREGate.getPlayerData().setJustPlaying(true);
            assertFalse(rules.setMove(new int[]{0, 2}));
            assertTrue(rules.setMove(new int[]{1, 1}));
            COREGate.getPlayerData().setJustPlaying(true);
            assertFalse(rules.setMove(new int[]{1, 1}));
            assertFalse(rules.setMove(new int[]{2, 0}));
        } catch (PlayerDataException ex) {
            System.err.println(ex);
        }
    }

    private void testLeftCastlingMove() {
        try {
            Rules rules = new Rules();
            COREGate.getData().clear();
            rules.standartPosition();
            try {
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{7, 6}));
                assertTrue(rules.setMove(new int[]{7, 4}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{7, 7}));
                assertTrue(rules.setMove(new int[]{7, 5}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{1, 7}));
                assertTrue(rules.setMove(new int[]{0, 5}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{1, 6}));
                assertTrue(rules.setMove(new int[]{1, 5}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{2, 7}));
                assertTrue(rules.setMove(new int[]{1, 6}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{3, 7}));
                assertTrue(rules.setMove(new int[]{1, 7}));
            } catch (PlayerDataException ex) {
                System.err.println(ex);
            }
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    private void testRightCastlingMove() {
        try {
            Rules rules = new Rules();
            COREGate.getData().clear();

            rules.standartPosition();
            try {
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{6, 7}));
                assertTrue(rules.setMove(new int[]{7, 5}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{6, 6}));
                assertTrue(rules.setMove(new int[]{6, 5}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{5, 7}));
                assertTrue(rules.setMove(new int[]{6, 6}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{5, 6}));
                assertTrue(rules.setMove(new int[]{5, 5}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{4, 7}));
                assertTrue(rules.setMove(new int[]{5, 6}));
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{3, 7}));
                assertTrue(rules.setMove(new int[]{6, 7}));
            } catch (PlayerDataException ex) {
                System.err.println(ex);
            }
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    private void testCheckMove() {
        try {
            Rules rules = new Rules();
            testLeftCastlingMove();
            COREGate.getData().add(new Queen(new PlayerColor().BLACK), new int[]{1, 6});
            try {
                COREGate.getPlayerData().setJustPlaying(true);
                assertFalse(rules.setMove(new int[]{1, 7}));
                assertTrue(rules.setMove(new int[]{1, 6}));
            } catch (PlayerDataException ex) {
                System.err.println(ex);
            }
        } catch (DataException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testIsCheck_7args() {
    }

    @Test
    public void testIsKingInCheck_4args() {
    }

    @Test
    public void testIsKingInCheckOrCheckmate() {
    }

    @Test
    public void testIsKingInCheck_String() {
    }

    @Test
    public void testIsKingInCheckmate() {
    }

    @Test
    public void testIsPossibleAnyMove() {
    }

    @Test
    public void testIsStalemate() {
    }

    @Test
    public void testChangePawnForFigure() {
    }
}