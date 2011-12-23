/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matoperace;

/**
 *
 * @author já
 */
public class Increasement extends MatOperace {

    @Override
    int action(int x) {
        return x + 1;
    }

    public Increasement(int[][] increase) {
        this.pole = increase;
    }
}

