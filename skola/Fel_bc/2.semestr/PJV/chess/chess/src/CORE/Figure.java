/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sprinkler
 */
public abstract class Figure implements Serializable {

    protected List<Point> moves;
    private Point position = null;
    protected boolean checkMove = false;
    private String color;
    protected Point oldPosition;
    protected Point newPosition;
    protected boolean checkMoveWithoutFigure = false;

    public Figure(String color) {
        if ((color.equals(new PlayerColor().WHITE)) || (color.equals(new PlayerColor().BLACK))) {
            this.color = color;
        }

    }

    public void setPosition(Point position) throws FigureException{
        if ((position.x < 0) ||(position.y < 0)||(position.x > 7)||(position.y > 7)){
            throw new FigureException("This position is not possible");
        }
        this.position = position;
    }

    public Point getPosition() throws FigureException {
        if (position == null){
           throw new FigureException("Position is not set");
        }
        return position;
    }

    public String getColor() {
        return color;
    }

    public abstract String getImage();

    public abstract List<Point> getMove();

    public List<Point> getCheckMove() {
        checkMove = true;
        getMove();
        resetProvisionalValues();
        return moves;
    }

    public List<Point> getCheckMove(int oldX, int oldY, int newX, int newY) {
        this.oldPosition = new Point(oldX, oldY);
        this.newPosition = new Point(newX, newY);
        checkMoveWithoutFigure = true;
        checkMove = true;
        getMove();
        resetProvisionalValues();
        return moves;
    }

    public void resetProvisionalValues() {
        checkMove = false;
        checkMoveWithoutFigure = false;
        oldPosition = null;
        newPosition = null;
    }

    public String getPath() {
        return "image\\";
    }
}
