/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mode;

import Core.Matrix;
import GUI.ResultWin;

/**
 *
 * @author já
 */
public class ATranspose implements Command {
   public void perform(Matrix firstMatrix, Matrix secondMatrix,float multiplicator, ResultWin window) {
        window.insertMatrix(firstMatrix.transpose());
            window.setVisible(true);
    }
}