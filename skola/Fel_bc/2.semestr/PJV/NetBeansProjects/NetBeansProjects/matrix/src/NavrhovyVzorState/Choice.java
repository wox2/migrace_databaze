/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NavrhovyVzorState;

import Core.Matrix;
import GUI.ResultWin;
import javax.swing.JOptionPane;

/**
 *
 * @author já
 */
public class Choice implements AbstractChoice {

    private Command chosenCommand;

    public void setChosenCommand(Command command) {
        chosenCommand = command;
    }

    public void count(Matrix firstMatrix, Matrix secondMatrix, float multiplicator, ResultWin window) {
        try {
            chosenCommand.count(firstMatrix, secondMatrix, multiplicator, window);
        } catch (IndexOutOfBoundsException E) {JOptionPane.showMessageDialog(window, "Nemáte vhodné rozměry matic pro odpovídající operaci");
        }
    }

    public Choice() {
        chosenCommand = new APlusB();
    }
}
