/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matoperace;

/**
 *
 * @author já
 */
abstract public class MatOperace {

    protected int[][] pole;

    abstract int action(int x);
   static int action3(int x){

   return 4;
   }
    protected int[][] solve() {
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[0].length; j++) {
                pole[i][j] = action(pole[i][j]);
            }


        }
        return pole;
    }

    protected void print(){
    for (int i=0;i<pole.length;i++){
            for(int j=0;j<pole[0].length;j++){
        System.out.print(pole[i][j]+" ");
          }
            System.out.println("");
        }
    }
}