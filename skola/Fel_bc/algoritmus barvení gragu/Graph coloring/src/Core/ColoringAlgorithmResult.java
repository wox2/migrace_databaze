/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Interfaces.ColoringAlgorithmInterface;
import Interfaces.ColoringAlgorithmResultInterface;

/**
 *
 * @author woxie
 */
public class ColoringAlgorithmResult implements ColoringAlgorithmResultInterface {
    private final double successRate;
    private final int coloringError;
    private final ColoringAlgorithmInterface coloringAlgorithm;
    private final int maximalniOdchylka;
    private final int highestColorNodeCount;
    private final int minimalDifference;

    public ColoringAlgorithmResult(ColoringAlgorithmInterface algorithm, double algorithmSuccessRate, int error, int maximalniOdchylka, int highestColorCount, int minimalniOdchylka){
        coloringAlgorithm = algorithm;
        successRate = algorithmSuccessRate*100;
        coloringError = error;
        this.maximalniOdchylka = maximalniOdchylka;
        highestColorNodeCount = highestColorCount;
        minimalDifference = minimalniOdchylka;
     }

     public void printResults(){
            System.out.println("SUCCESS:"+successRate+"%"+" nadbarva:"+coloringError + "  maximalni odchylka:"
                    +maximalniOdchylka + "Pocet Nodu s vyssi barvou: "+ highestColorNodeCount
                    +" minimalni odchylka:"+ minimalDifference);
     }
}
