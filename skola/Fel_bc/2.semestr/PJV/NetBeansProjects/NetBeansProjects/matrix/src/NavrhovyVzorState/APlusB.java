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
public class APlusB implements Command{

    public APlusB(){}
    public void count(Matrix firstMatrix, Matrix secondMatrix,float multiplicator, ResultWin window) {
        window.insertMatrix(firstMatrix.add(secondMatrix));
        window.setVisible(true);
        firstMatrix.add(secondMatrix);
    }
}
