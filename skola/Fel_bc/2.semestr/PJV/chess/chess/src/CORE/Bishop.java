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
public class Bishop extends ComplicateFigure implements Serializable{

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getImage() {
        return getPath() + getColor() + "Bishop.gif";
    }

    @Override
    public List<Point> getMove() {
        moves = new ArrayList<Point>();
        moveInX();
        return moves;
    }

}
