/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Core.Graph;
import Exceptions.AlgorithmFinishedException;

/**
 *
 * @author woxie
 */
public interface ColoringAlgorithmInterface {

    Graph doStep() throws AlgorithmFinishedException;

    Graph getSolution();

    void reset();

}
