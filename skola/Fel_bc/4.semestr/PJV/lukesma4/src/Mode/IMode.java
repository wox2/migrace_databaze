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
public interface IMode {
   abstract public void setChosenCommand(Command command);
   abstract public void perform(Matrix firstMatrix,Matrix secondMatrix, float multiplicator, ResultWin window);
}
