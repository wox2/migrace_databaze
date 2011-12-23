/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package barvicky;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author já
 */
public class Barvicky extends JFrame {
     private String color;
     private JTextField answer=new JTextField("Vyberte aktualni barvu");

    public Barvicky(){
       super();
       setLayout(new GridBagLayout());
       getContentPane().setBackground(Color.BLACK);
       color="black";
       answer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               if(answer.getText().equals("black") & color.equals("black")){ getContentPane().setBackground(Color.BLUE); color="blue";}
               if(answer.getText().equals("blue") & color.equals("blue")){ getContentPane().setBackground(Color.RED);color="red";}
               if(answer.getText().equals("red") & color.equals("red")){ getContentPane().setBackground(Color.GREEN);color="green";}
               if(answer.getText().equals("green") & color.equals("green")){ getContentPane().setBackground(Color.ORANGE);color="orange";}
               if(answer.getText().equals("orange") & color.equals("orange")){ getContentPane().setBackground(Color.magenta);color="pink";}
               if(answer.getText().equals("pink") & color.equals("pink")){ getContentPane().setBackground(Color.YELLOW);color="yellow";}
               if(answer.getText().equals("yellow") & color.equals("yellow")){ getContentPane().setBackground(Color.DARK_GRAY);color="gray";}
               if(answer.getText().equals("gray") & color.equals("gray")){ getContentPane().setBackground(Color.WHITE);color="white";}
               if(answer.getText().equals("white") & color.equals("white")){ getContentPane().setBackground(Color.CYAN);color="cyan";}
               if(answer.getText().equals("cyan") & color.equals("cyan")){ getContentPane().setBackground(Color.black);color="black";}


            }
        });
       add(answer);
       setSize(500, 500);
       setVisible(true);
    }
}
