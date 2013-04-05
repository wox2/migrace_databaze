/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Core.Graph;

/**
 *
 * @author woxie
 */
public interface AlgorithmQualityComparatorInterface {

    void addAlgorithmToBeCompared(ColoringAlgorithmInterface coloringAlgorithm);

    ColoringAlgorithmInterface getBestColoringAlgorithm();

    void startQualifyAlgorithms(Graph graph);

}
