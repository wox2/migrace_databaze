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
public class Pawn extends SimpleFigure implements Serializable {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getImage() {
        return getPath() + getColor() + "Pawn.gif";
    }

    @Override
    public List<Point> getMove() {
        try {
            moves = new ArrayList<Point>();
            move(yMoveFigure(getPosition().y));
            if (isStandartPosition(getPosition().y)) {
                move(yMoveFigure(yMoveFigure(getPosition().y)));
            }
            attack(getPosition().x + 1, yMoveFigure(getPosition().y));
            attack(getPosition().x - 1, yMoveFigure(getPosition().y));
        } catch (FigureException ex) {
            System.out.println(ex);
        }
        return moves;
    }

    private void move(int y) {
        try {
            Data data = COREGate.getData();
            if ((y <= 7) && (y >= 0)) {
                try {
                    if (!(checkMove) && (new Rules().isKingInCheck(getColor(), getPosition().x, getPosition().y, getPosition().x, y))) {
                    } else if (data.get(getPosition().x, y) == null) {
                        moves.add(new Point(getPosition().x, y));
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

    @Override
    public List<Point> getCheckMove() {
        try {
            checkMove = true;
            moves = new ArrayList<Point>();
            attack(getPosition().x - 1, yMoveFigure(getPosition().y));
            attack(getPosition().x + 1, yMoveFigure(getPosition().y));
            checkMove = false;
        } catch (FigureException ex) {
            System.out.println(ex);
        }
        return moves;
    }

    @Override
    public List<Point> getCheckMove(int oldX, int oldY, int newX, int newY) {
        this.oldPosition = new Point(oldX, oldY);
        this.newPosition = new Point(newX, newY);
        checkMoveWithoutFigure = true;
        checkMove = true;
        getCheckMove();
        resetProvisionalValues();
        return moves;
    }

    private void attack(int x, int y) {
        try {
            Data data = COREGate.getData();
            if ((y <= 7) && (y >= 0)) {
                try {
                    if (!(checkMove) && (new Rules().isKingInCheck(getColor(), getPosition().x, getPosition().y, x, y))) {
                    } else if ((checkMoveWithoutFigure) && (getPosition().x == newPosition.x) && (getPosition().y == newPosition.y)) {
                    } else if ((checkMoveWithoutFigure) && (x == newPosition.x) && (y == newPosition.y)) {
                        moves.add(new Point(x, y));
                    } else if ((data.get(x, y) != null) && (!(data.get(x, y).getColor().equals(getColor()))) || (checkMove)) {
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

    private int yMoveFigure(int y) {
        try {
            if (getColor().equals(COREGate.getPlayerData().getPlayerColor())) {
                return y - 1;
            } else {
                return y + 1;
            }
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    private boolean isStandartPosition(int y) {
        try {
            String color = COREGate.getPlayerData().getPlayerColor();
            if (((getColor().equals(color)) && (y == 6)) || (!(getColor().equals(color)) && (y == 1))) {
                return true;
            }
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
        return false;
    }
}
