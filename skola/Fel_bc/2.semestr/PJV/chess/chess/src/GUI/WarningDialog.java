/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import javax.swing.JOptionPane;

/**
 *
 * @author sprinkler
 */
public class WarningDialog extends JOptionPane{

    public WarningDialog(String text) {
        showMessageDialog(null, text, "Chyba", JOptionPane.WARNING_MESSAGE);
    }

    
}
