/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Core.Matrix;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

/**
 *
 * @author já
 */
public class Win extends JFrame{

    private JMenuBar mb;
    private MField matrixA = new MField("Matice A");
    private MField matrixB = new MField("Matice B");
    private ResultWin result=new ResultWin();
    private static Win window=new Win();

    private Win() {
        super();

        JMenuItem info = new JMenuItem("O programu", KeyEvent.VK_A);
        info.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              JOptionPane.showMessageDialog(getWin(),"Tento program byl vytvořen v roce 2009 jako semestrální práce Martina Lukeše,\n studenta fakulty elektrotechnické, v předmětech PJV a OMO.");
            }
        });

        JMenu about = new JMenu("Napoveda");
        about.add(info);
 
        JMenuItem mi = new JMenuItem("Quit", KeyEvent.VK_Q);
        mi.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               int exit= JOptionPane.showConfirmDialog(getWin(), "konec");
               if(exit==JOptionPane.YES_OPTION){System.exit(0);}
            }
        });
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));

        JMenu menu = new JMenu("Soubor");
        menu.add(mi);
        menu.setMnemonic(KeyEvent.VK_S);

        mb = new JMenuBar();
        mb.add(menu);
        mb.add(about);
        setJMenuBar(mb);

        setTitle("Matrix");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(0, 0);
        setSize(800, 500);
        
        Container cp = getContentPane();
        setLayout(new GridLayout());

        ActionButts operate = new ActionButts(this);
        cp.add(matrixA);
        cp.add(operate);
        cp.add(matrixB);
        operate.setVisible(true);
        matrixB.setVisible(true);
        matrixB.setEditable(false);
        matrixA.setVisible(true);
        matrixA.setEditable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               int exit= JOptionPane.showConfirmDialog(getWin(), "konec");
               if(exit==JOptionPane.YES_OPTION){System.exit(0);}
            }
        });
    }

    public Matrix getMatrixA(){
      return matrixA.getData();
    }


    public Matrix getMatrixB(){
      return matrixB.getData();
    }

    public ResultWin getResults(){
       return result;
    }

    public static Win getWin(){
    return window;
    }
 }
