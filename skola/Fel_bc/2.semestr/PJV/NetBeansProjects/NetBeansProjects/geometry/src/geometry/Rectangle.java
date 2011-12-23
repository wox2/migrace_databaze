package geometry;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author já
 */
public class Rectangle {

    protected int width;
    protected int height;
    protected int x;
    protected int y;

    public Rectangle() {
        this(10, 10);
    }

    public Rectangle(int width, int length) {
        this(0, 0, width, length);
    }

    public Rectangle(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    public void paint() {
        System.out.println("Tento obdelnik ma rozmery: " + width + " a " + height + " a lezi na pozici " + this.x + ", " + y);

    }
}
