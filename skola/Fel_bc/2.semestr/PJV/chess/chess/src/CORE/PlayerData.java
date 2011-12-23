/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author sprinkler
 */
public class PlayerData {

    private boolean justPlaying = false;
    private String playerColor;
    private int[] clickedFigure = new int[]{-1, -1};
    private List<Point> movesClickedFigure = null;
    private boolean takeFigure = false;

    public PlayerData(String playerColor) throws PlayerDataException {
        if ((playerColor.equals(new PlayerColor().BLACK)) || (playerColor.equals(new PlayerColor().WHITE))) {
            this.playerColor = playerColor;
        } else {
            throw new PlayerDataException("This is not player color");
        }
        isPlayerPlayingFirst();
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public boolean isJustPlaying() {
        return justPlaying;
    }

    public void setJustPlaying(boolean justPlaying) {
        this.justPlaying = justPlaying;
    }

    public int[] getClickedFigure() {
        return clickedFigure;
    }

    public void setClickedFigure(int[] position, List<Point> movesFigure) throws PlayerDataException {
        if ((position[0] >= 0) && (position[0] < 8) && (position[1] >= 0) && (position[1] < 8)) {
            if (movesFigure != null) {
                for (int i = 0; i < movesFigure.size(); i++) {
                    if ((movesFigure.get(i).x < 0) || (movesFigure.get(i).x > 7) || (movesFigure.get(i).y < 0) || (movesFigure.get(i).y > 7)) {
                        throw new PlayerDataException("This moves is not possible");
                    }
                }
            }
            this.clickedFigure = position;
            this.movesClickedFigure = movesFigure;
        } else {
            throw new PlayerDataException("This figure is not possible");
        }
    }

    public List<Point> getMovesClickedFigure() {
        return movesClickedFigure;
    }

    public void nullClickedFigure() {
        clickedFigure = new int[]{-1, -1};
        if (movesClickedFigure != null) {
            movesClickedFigure.clear();
        }
    }

    private void isPlayerPlayingFirst() {
        if (playerColor.equals(new PlayerColor().WHITE)) {
            justPlaying = true;
        }
    }

    public boolean isTakeFigure() {
        if (takeFigure) {
            takeFigure = false;
            return true;
        }
        return takeFigure;
    }

    public void setTakeFigure(boolean takeFigure) {
        this.takeFigure = takeFigure;
    }

    public String getOponentColor() {
        if (playerColor.equals(new PlayerColor().WHITE)) {
            return new PlayerColor().BLACK;
        } else {
            return new PlayerColor().WHITE;
        }
    }
}
