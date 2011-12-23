/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Core.Matrix;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author já
 */
public class MField extends JTextArea{
    private MWin mWin;
    private JLabel title;


    public MField(String title){
       super();
       setName(title);
       mWin=new MWin("Uprava "+ title,0,0,new Matrix(),this);
       addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mWin.setVisible(true);
            }
        });
           fill();
    }

    public void fill(){
       fill(new Matrix());
    }

    public void fill(Matrix matrix){
       setEditable(true);
        String contains=getName()+"\n";
        contains+=matrix.toString();

        this.setText(contains);
        setEditable(false);
    }

    public Matrix getData(){
      return mWin.getMatrix();
    }

}
