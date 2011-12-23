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
public class APlusB implements Command{

    public APlusB(){}
    public void perform(Matrix firstMatrix, Matrix secondMatrix,float multiplicator, ResultWin window) {
        window.insertMatrix(firstMatrix.add(secondMatrix));
        window.setVisible(true);
        firstMatrix.add(secondMatrix);
    }
}
