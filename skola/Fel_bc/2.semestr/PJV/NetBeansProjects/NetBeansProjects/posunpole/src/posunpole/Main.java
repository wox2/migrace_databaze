/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package posunpole;

/**
 *
 * @author já
 */
public class Main {

    static int[] posunPole(int[] pole, int posun) {
        int[] posunutePole = new int[pole.length];
        int skutecnyPosun = skutecnyPosun(posun, pole.length);
        int index = 0;
        for (int i = 0; i < pole.length; i++) {
            posunutePole[(index + skutecnyPosun) % pole.length] = pole[index];
            index = (index + skutecnyPosun) % pole.length;
        }
        return posunutePole;
    }

    static int[] posunPole2(int[]pole, int posun){
    int index=0;
    int skutPosun=posun%pole.length;
    int pomoc=0;
    int zapis=pole[0];
    for(int kroku=0;kroku<pole.length;kroku++){
    pomoc=pole[(skutPosun+index)%pole.length];
    pole[(skutPosun+index)%pole.length]=zapis;
    zapis=pomoc;
    index=(skutPosun+index)%pole.length;
    }
    return pole;
    }

    static int skutecnyPosun(int posun, int delkaPole) {
        int skutecnyPosun = posun % delkaPole;
        return skutecnyPosun;
    }

    static void vypisPole(int[] pole) {
        for (int i = 0; i < pole.length; i++) {
            System.out.print(pole[i] + " ");
        }

    }

    public static void main(String[] args) {
        int[] policko = {1, 4, 5};
        int posun2 = 2;
        vypisPole(posunPole(policko, posun2));
        System.out.println("");
        vypisPole(posunPole2(policko, posun2));
    }
}
