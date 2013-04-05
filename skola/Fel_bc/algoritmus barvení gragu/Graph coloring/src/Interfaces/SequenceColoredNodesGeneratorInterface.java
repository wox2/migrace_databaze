/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Exceptions.AlgorithmFinishedException;

/**
 *
 * @author woxie
 */
public interface SequenceColoredNodesGeneratorInterface {

    int[] getNext() throws AlgorithmFinishedException;

    int[] getSequence();

    boolean hasNext();

}
