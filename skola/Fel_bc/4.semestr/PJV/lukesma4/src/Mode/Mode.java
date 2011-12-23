/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mode;

import Core.Matrix;
import GUI.ResultWin;
import javax.swing.JOptionPane;

/**
 *
 * @author já
 */
public class Mode implements IMode {

    private Command chosenCommand;

    public void setChosenCommand(Command command) {
        chosenCommand = command;
    }

    public void perform(Matrix firstMatrix, Matrix secondMatrix, float multiplicator, ResultWin window) {
        try {
            chosenCommand.perform(firstMatrix, secondMatrix, multiplicator, window);
        } catch (IndexOutOfBoundsException E) {JOptionPane.showMessageDialog(window, "Nemáte vhodné rozměry matic pro odpovídající operaci");
        }
    }

    public Mode() {
        chosenCommand = new APlusB();
    }
}
