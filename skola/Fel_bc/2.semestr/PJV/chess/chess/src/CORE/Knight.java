/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sprinkler
 */
public class Knight extends SimpleFigure implements Serializable {

    public Knight(String color) {
        super(color);
    }

    @Override
    public String getImage() {
        return getPath() + getColor() + "Knight.gif";
    }

    @Override
    public List<Point> getMove() {
        try {
            moves = new ArrayList<Point>();
            move(getPosition().x + 1, getPosition().y + 2);
            move(getPosition().x - 1, getPosition().y - 2);
            move(getPosition().x + 1, getPosition().y - 2);
            move(getPosition().x - 1, getPosition().y + 2);
            move(getPosition().x + 2, getPosition().y + 1);
            move(getPosition().x - 2, getPosition().y - 1);
            move(getPosition().x + 2, getPosition().y - 1);
            move(getPosition().x - 2, getPosition().y + 1);
        } catch (FigureException ex) {
            System.out.println(ex);
        }
        return moves;
    }

    private void move(int x, int y) {
        try {
            Data data = COREGate.getData();
            if ((x <= 7) && (x >= 0) && (y <= 7) && (y >= 0)) {
                try {
                    if (!(checkMove) && (new Rules().isKingInCheck(getColor(), getPosition().x, getPosition().y, x, y))) {
                    } else if ((checkMoveWithoutFigure) && (getPosition().x == newPosition.x) && (getPosition().y == newPosition.y)) {
                    } else if ((data.get(x, y) == null) || !(data.get(x, y).getColor().equals(getColor())) || ((checkMove) && (data.get(x, y).getColor().equals(getColor())))) {
                        moves.add(new Point(x, y));
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                } catch (FigureException ex) {
                    System.out.println(ex);
                }
            }
        } catch (DataException ex) {
            System.out.println(ex);
        }
    }
}
