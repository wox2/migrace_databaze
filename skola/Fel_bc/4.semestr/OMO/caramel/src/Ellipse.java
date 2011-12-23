
import java.awt.Color;
import java.awt.Graphics;

public class Ellipse implements Repaintable {

    private int x,  y,  width,  height;
    private Window window;

    static Repaintable[] repaintables = new Repaintable[ 1000];
    static int count = 0;

    public Ellipse() {
        x = 50;
        y = 50;
        width = 100;
        height = 50;
        window = Window.getWindow();
        repaint();
        repaintables[count++] = this;
    }

    public void moveRight(int offset) {
        erase();
        x += offset;
        repaint();
        repaintAll();
    }

    public void moveDown(int offset) {
        erase();
        y += offset;
        repaintAll();
    }

    static void repaintAll() {
        for ( int i = 0; i < count; i++)
            repaintables[i].repaint();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        erase();
        this.width = width;
        repaint();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        erase();
        this.height = height;
        repaint();
    }

    public void erase() {
        Graphics g = window.getGraphics();
        g.setColor(Color.WHITE);
        g.fillArc(x, y, width, height, 0, 360);
        window.repaint();
    }

    public void repaint() {
        Graphics g = window.getGraphics();
        g.setColor(Color.GREEN);
        g.fillArc(x, y, width, height, 0, 360);
        window.repaint();
    }
}
