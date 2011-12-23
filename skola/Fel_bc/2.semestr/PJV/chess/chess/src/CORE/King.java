/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import GUI.GUIGate;
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
public class King extends SimpleFigure implements Serializable {

    private boolean possibleCasting = true;

    public King(String color) {
        super(color);
    }

    @Override
    public String getImage() {
        return getPath() + getColor() + "King.gif";
    }

    @Override
    public List<Point> getMove() {
        try {
            moves = new ArrayList<Point>();
            move(getPosition().x, getPosition().y + 1);
            move(getPosition().x + 1, getPosition().y);
            move(getPosition().x, getPosition().y - 1);
            move(getPosition().x - 1, getPosition().y);
            move(getPosition().x + 1, getPosition().y + 1);
            move(getPosition().x - 1, getPosition().y - 1);
            move(getPosition().x + 1, getPosition().y - 1);
            move(getPosition().x - 1, getPosition().y + 1);
            castling();
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
                    if ((checkMove) || !(new Rules().isCheck(x, y, getColor())) && ((data.get(x, y) == null) || !(data.get(x, y).getColor().equals(getColor())))) {
                        moves.add(new Point(x, y));
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        } catch (DataException ex) {
            System.out.println(ex);
        }
    }

    private void castling() {
        try {
            if (!(checkMove) && (isPosssibleCasting()) && !(new Rules().isCheck(getPosition().x, getPosition().y, getColor()))) {
                if (leftCastling()) {
                    moves.add(new Point(1, 7));
                }
                if (rightCastling()) {
                    moves.add(new Point(6, 7));
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private boolean leftCastling() {
        try {
            Data data = COREGate.getData();
            if ((data.get(0, 7) != null) && (data.get(0, 7) instanceof Rook) && (data.get(0, 7).getColor().equals(getColor())) && (((Rook) data.get(0, 7)).isPosssibleCasting())) {
                for (int i = 1; i < getPosition().x; i++) {
                    if ((data.get(i, 7) != null) || (new Rules().isCheck(i, 7, getColor()))) {
                        return false;
                    }
                }
                return true;
            }
        } catch (DataException ex) {
            System.out.println(ex);
        } catch (FigureException ex) {
            System.out.println(ex);
        }
        return false;
    }

    private boolean rightCastling() {
        try {
            Data data = COREGate.getData();
            if ((data.get(7, 7) != null) && (data.get(7, 7) instanceof Rook) && (data.get(7, 7).getColor().equals(getColor())) && (((Rook) data.get(7, 7)).isPosssibleCasting())) {
                for (int i = 6; i > getPosition().x; i--) {
                    System.out.println(data.get(i, 7));
                    if ((data.get(i, 7) != null) || (new Rules().isCheck(i, 7, getColor()))) {
                        return false;
                    }
                }
                return true;
            }
        } catch (DataException ex) {
            System.out.println(ex);
        } catch (FigureException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean isPosssibleCasting() {
        return possibleCasting;
    }

    public void setPossibleCasting(boolean possibleCasting) {
        this.possibleCasting = possibleCasting;
    }
}
