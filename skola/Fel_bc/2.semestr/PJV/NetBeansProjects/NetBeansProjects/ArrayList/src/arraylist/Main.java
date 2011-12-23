/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arraylist;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author já
 */









class Nit implements Runnable{
int a = 0;
static int b = 0;
public void run() {
for (int i = 0; i < 100; i++) {
System.out.printf("i: %3d, a: %3d, b: %3d%n",i,++a,++b);
}
}


}
public class Main {
static void blb(){
    int [][]p=new int[2][3];
int[]pok=new int[5];
p[1]=pok;


}
public static void main(String[] args){
blb();

}
}