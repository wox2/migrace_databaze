/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CORE.COREGate;
import controler.Builder;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sprinkler
 */
public class GUIGate {

    static private Window window;
    static private Chessboard chessboard;
    private JLabel waitForPlayer = new JLabel("Èekám na protivníka");
    private Builder builder;

    public GUIGate(Builder builder) {
        this.builder = builder;
    }

    public void createWindow() {
        window = new Window(this);
    }

    public void createChessboard() {
        window.removeCenter();
        System.out.println("chessboard");
        chessboard = new Chessboard(0, 0, this);
        window.addCenter(chessboard);
        repaintChessboard();
        window.repaint();
    }

    public void startNewGame(String playerColor) {
        builder.startNewGame(playerColor);
    }

    public void joinGame(String ipAddress) {
        builder.joinGame(ipAddress);
    }

    public CORE.Data getData() {
        return builder.getData();
    }

    public CORE.PlayerData getPlayerData() {
        return builder.getPlayerData();
    }

    public void repaintChessboard() {
        if (chessboard != null) {
            chessboard.repaintChessSquare();
        }
    }

    public void createWarning(String text) {
        new WarningDialog(text);
    }

    public boolean isPossibleTrySendAgain() {
        return new NetworkDialog().createNetworkDialog();
    }

    public void createFigureforPawn() {
        new FigureforPawnDialog(getPlayerData().getPlayerColor(), this);
    }

    public void setMove(int[] position) {
        builder.setMove(position);
    }

    public void changeFigureForPawn(String figureName) {
        builder.changeFigureForPawn(figureName);
    }

    public void waitForJoinPlayer() {
        waitForPlayer.setSize(200, 20);
        waitForPlayer.setLocation(40, 100);
        window.removeCenter();
        window.addCenter(waitForPlayer);
        window.repaint();
    }

    public void oponentEndGame(){
        new WarningDialog("Protihráè ukonèil hru");
        window.remove(chessboard);
        chessboard = null;
        window.repaint();
    }

    public void endGame(){
        builder.endGame();
    }
}
