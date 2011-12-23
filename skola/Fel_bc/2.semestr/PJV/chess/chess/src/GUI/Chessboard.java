/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Label;
import java.awt.Point;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author sprinkler
 */
public class Chessboard extends JPanel {

    private ChessSquare[][] chessSquares = new ChessSquare[8][8];
    private GUIGate gate;

    public Chessboard(int x, int y, GUIGate gate) {
        this.gate = gate;
        setSize(420, 420);
        setLocation(x, y);
        setLayout(null);
        fill();
        markChessboard();
        repaint();
    }

    private void fill() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessSquares[i][j] = new ChessSquare(i, j, gate);
                add(chessSquares[i][j]);
            }
        }
    }

    private void markChessboard(){
        if (gate.getPlayerData().getPlayerColor().equals("white")){
            markNumberWhiteChessboard();
            markCharWhiteChessboard();
        }else{
            markNumberBlackChessboard();
            markCharBlackChessboard();
        }
    }

    private void markNumberWhiteChessboard() {
        for (int i = 0; i < 8; i++) {
            Label mark = new Label(Integer.toString(i));
            mark.setSize(20, 20);
            mark.setLocation(0, ((i * 50) + 35));
            add(mark);
        }
    }

    private void markCharWhiteChessboard(){
        for (int i=0;i<8;i++){
            Label mark = new Label(Character.toString((char) (i + 97)));
            mark.setSize(20, 20);
            mark.setLocation(((i * 50) + 35), 0);
            add(mark);
        }
    }

        private void markNumberBlackChessboard() {
        for (int i = 0, j = 7; i < 8; i++, j--) {
            Label mark = new Label(Integer.toString(j));
            mark.setSize(20, 20);
            mark.setLocation(0, ((i * 50) + 35));
            add(mark);
        }
    }

    private void markCharBlackChessboard(){
        for (int i = 0, j = 7; i < 8; i++, j--){
            Label mark = new Label(Character.toString((char) (j + 97)));
            mark.setSize(20, 20);
            mark.setLocation(((i * 50) + 35), 0);
            add(mark);
        }
    }

    public void repaintChessSquare() {
        List<Point> movesClickedFigure = gate.getPlayerData().getMovesClickedFigure();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessSquares[i][j].setImage();
                chessSquares[i][j].setBackground();
                if ((gate.getPlayerData().getClickedFigure()[0] == i) && (gate.getPlayerData().getClickedFigure()[1] == j)) {
                    chessSquares[i][j].setSelectedSquareBackground();
                }
                if (movesClickedFigure != null) {
                    for (int k = 0; k < movesClickedFigure.size(); k++) {
                        if ((movesClickedFigure.get(k).x == i) && (movesClickedFigure.get(k).y == j)) {
                            chessSquares[i][j].setSelectedSquareBackground();
                            break;
                        }
                    }
                }
            }
        }
    }
}
