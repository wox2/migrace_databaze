/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author sprinkler
 */
public class NetworkDialog extends JOptionPane{

    public boolean createNetworkDialog() {
        String[] dialogOptions = new String[]{"Ano","Ne"};
        int i = showOptionDialog(this,"Nebyl nalezen server! Zkusit znovu?","Chyba",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE, null, dialogOptions, dialogOptions[1]);
        if (i == 0){
            return true;
        }
        return false;
    }
}
