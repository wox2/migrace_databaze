/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author sprinkler
 */
public abstract class ComplicateFigure extends Figure implements Serializable {

    private boolean isPreviousOpponentsKing = false;

    public ComplicateFigure(String color) {
        super(color);
    }

    private void moveRight() {
        try {
            for (int i = getPosition().y + 1; i < 8; i++) {
                if (!isMoveApplicable(getPosition().x, i)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private void moveLeft() {
        try {
            for (int i = getPosition().y - 1; i >= 0; i--) {
                if (!isMoveApplicable(getPosition().x, i)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private void moveDown() {
        try {
            for (int i = getPosition().x + 1; i < 8; i++) {
                if (!isMoveApplicable(i, getPosition().y)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private void moveUp() {
        try {
            for (int i = getPosition().x - 1; i >= 0; i--) {
                if (!isMoveApplicable(i, getPosition().y)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private void moveUpRight() {
        try {
            for (int i = getPosition().x - 1, j = getPosition().y + 1; i >= 0 && j < 8; i--, j++) {
                if (!isMoveApplicable(i, j)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private void moveUpLeft() {
        try {
            for (int i = getPosition().x - 1, j = getPosition().y - 1; i >= 0 && j >= 0; i--, j--) {
                if (!isMoveApplicable(i, j)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private void moveDownRight() {
        try {
            for (int i = getPosition().x + 1, j = getPosition().y + 1; i < 8 && j < 8; i++, j++) {
                if (!isMoveApplicable(i, j)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    private void moveDownLeft() {
        try {
            for (int i = getPosition().x + 1, j = getPosition().y - 1; i < 8 && j >= 0; i++, j--) {
                if (!isMoveApplicable(i, j)) {
                    break;
                }
            }
        } catch (FigureException ex) {
            System.out.println(ex);
        }
    }

    public void moveCross() {
        moveUp();
        moveDown();
        moveLeft();
        moveRight();
    }

    public void moveInX() {
        moveUpRight();
        moveUpLeft();
        moveDownLeft();
        moveDownRight();
    }

    public boolean isMoveApplicable(int x, int y) {
        try {
            Data data = COREGate.getData();
            if (!(checkMove) && (new Rules().isKingInCheck(getColor(), getPosition().x, getPosition().y, x, y))) {
                if (data.get(x, y) != null) {
                    return false;
                }
                return true;
            } else if ((checkMoveWithoutFigure) && (getPosition().x == newPosition.x) && (getPosition().y == newPosition.y)) {
                return false;
            } else if ((checkMoveWithoutFigure) && (x == newPosition.x) && (y == newPosition.y)) {
                moves.add(new Point(x, y));
                return false;
            } else if ((checkMoveWithoutFigure) && (x == oldPosition.x) && (y == oldPosition.y)) {
                moves.add(new Point(x, y));
                return true;
            } else if (data.get(x, y) == null) {
                moves.add(new Point(x, y));
                if (isPreviousOpponentsKing) {
                    isPreviousOpponentsKing = false;
                    return false;
                }
                return true;

            } else if ((data.get(x, y).getColor().equals(getColor())) && (isPreviousOpponentsKing)) {
                moves.add(new Point(x, y));
                isPreviousOpponentsKing = false;
                return false;
            } else if ((checkMove) && (data.get(x, y) instanceof King) && !(data.get(x, y).getColor().equals(getColor()))) {
                moves.add(new Point(x, y));
                isPreviousOpponentsKing = true;
                return true;
            } else if (!(data.get(x, y).getColor().equals(getColor()))) {
                moves.add(new Point(x, y));
                return false;
            } else if ((checkMove) && (data.get(x, y).getColor().equals(getColor()))) {
                moves.add(new Point(x, y));
                return false;
            } else {
                return false;
            }
        } catch (DataException ex) {
            System.out.println(ex);
            return false;
        } catch (FigureException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
