/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matoperace;

/**
 *
 * @author já
 */
public class Decreasement extends MatOperace {

    @Override
    int action(int x) {
        return x - 1;
    }

    public Decreasement(int[][] decrease) {
        this.pole = decrease;
    }
}