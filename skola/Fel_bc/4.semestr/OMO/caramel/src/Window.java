
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Window {

    private JFrame frame;
    private Image image;
    private Graphics graphics;
    private static Window window;
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    private Window() {
        frame = new JFrame() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, this);
                }
            }
        };
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
        frame.setBounds(50, 50, WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        image = frame.createImage(WIDTH, HEIGHT);
        graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public static Window getWindow() {
        if (window == null) {
            window = new Window();
        }
        return window;
    }

    Graphics getGraphics() {
        return graphics;
    }

    void repaint() {
        frame.repaint();
    }

    public void close() {
        frame.dispose();
    }
}
