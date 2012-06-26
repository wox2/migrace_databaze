/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Interfaces.SequenceColoredNodesGeneratorInterface;
import Exceptions.AlgorithmFinishedException;
import java.util.List;

/**
 *
 * @author woxie
 */
public class NonIzomorphSequenceColoredNodesGenerator implements SequenceColoredNodesGeneratorInterface {

    private int[] array;
    private int position;

    public NonIzomorphSequenceColoredNodesGenerator(int value) {
        array = new int[value];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        position = 0;        
    }

    public int[] getSequence() {
        return array;
    }

    public boolean hasNext() {
        if (array[array.length - 1] == array.length) {
            return false;
        }
        return true;
    }

    public int[] getNext() throws AlgorithmFinishedException {
        if(!hasNext()) throw new AlgorithmFinishedException();
        while (true) {
            if (array[position] < array[position+1] +1)  {
                ++array[position];
                position = 0;
                break;
            } else {
                array[position] = 1;
                position++;
                if(position == array.length-1) throw new AlgorithmFinishedException();
            }
        }

        return array;
    }

}
