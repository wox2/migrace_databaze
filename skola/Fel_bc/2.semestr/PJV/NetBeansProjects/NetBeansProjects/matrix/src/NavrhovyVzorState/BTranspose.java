/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NavrhovyVzorState;

import Core.Matrix;
import GUI.ResultWin;

/**
 *
 * @author já
 */
public class BTranspose implements Command{
    public void count(Matrix firstMatrix, Matrix secondMatrix, float multiplicator, ResultWin window) {
        window.insertMatrix(secondMatrix.transpose());
            window.setVisible(true);
    }

}
