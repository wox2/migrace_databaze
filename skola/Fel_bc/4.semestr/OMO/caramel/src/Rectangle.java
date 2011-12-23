
import java.awt.Color;
import java.awt.Graphics;

public class Rectangle implements Repaintable {

    private int x,  y,  width,  height;
    private Window window;

    public Rectangle() {
        x = 50;
        y = 50;
        width = 100;
        height = 50;
        window = Window.getWindow();
        repaint();
        Ellipse.repaintables[ Ellipse.count++] = this;
    }

    public void moveRight(int offset) {
        erase();
        x += offset;
        repaint();
        Ellipse.repaintAll();
    }

    public void moveDown(int offset) {
        erase();
        y += offset;
        repaint();
        Ellipse.repaintAll();
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
        g.fillRect(x, y, width, height);
        window.repaint();
    }

    public void repaint() {
        Graphics g = window.getGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        window.repaint();
    }
}
