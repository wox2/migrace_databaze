/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import javax.swing.JButton;

/**
 *
 * @author já
 */
public class MyButton extends JButton{
   private String title;
    public MyButton(String title){
    super(title);
    this.title=title;
 }
    public String getTitle(){
       return title;
    }
}
