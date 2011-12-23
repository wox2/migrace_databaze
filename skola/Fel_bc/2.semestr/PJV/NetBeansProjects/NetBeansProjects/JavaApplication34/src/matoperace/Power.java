/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matoperace;

/**
 *
 * @author já
 */
   public class Power extends MatOperace {

        @Override
        int action(int x) {
            return x * x;
        }

        public Power(int[][] power) {
            this.pole = power;
        }
    }