import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class EasyComplexGraphicsApp extends JFrame{
	public static void main (String args[] ){
		EasyComplexGraphicsApp app = new EasyComplexGraphicsApp();
		app.paint(app.getGraphics());
	}
	
	EasyComplexGraphicsApp(){
		setSize(800, 600);
		setVisible(true);
		setMinimumSize(new Dimension(800, 600));
		File f = new File("folders.brusher.png");
		setTitle("SUPER COOOL MODERN Simple Complex graphic editor");
        FlowLayout flowLayout = new FlowLayout();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(flowLayout);

		JButton buttonVyber = new JButton("", new ImageIcon("icons/folders-brushes.png"));
		buttonVyber.setBounds(750, 300, 40, 40);
		buttonVyber.setVisible(true);
		add(buttonVyber);
		
	}

	@Override
    public void paint(Graphics gr){
        clear();
        paintComponents(gr);
    }
	
	public void clear() {
        int width = getBounds().width;
        int height = getBounds().height;
        int x = getBounds().x;
        int y = getBounds().y;
        getGraphics().clearRect(x, y + 60, width, height);
    }
}



