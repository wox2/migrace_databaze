/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sprinkler
 */
public class Rook extends ComplicateFigure implements Serializable {

    private boolean possibleCasting = true;

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getImage() {
        return getPath() + getColor() + "Rook.gif";
    }

    @Override
    public List<Point> getMove() {
        moves = new ArrayList<Point>();
        moveCross();
        return moves;
    }

    public boolean isPosssibleCasting() {
        return possibleCasting;
    }

    public void setPossibleCasting(boolean possibleCasting){
        this.possibleCasting = possibleCasting;
    }
}
