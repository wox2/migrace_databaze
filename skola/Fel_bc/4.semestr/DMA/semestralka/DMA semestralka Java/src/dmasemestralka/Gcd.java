/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dmasemestralka;



/**
 *
 * @author woxie
 */
public class Gcd {

public static int getGcd(int a, int b){
    if (a<b) return getGcd(b, a);
    if (b==0) return a;
    while (a>=b) {a=a-b;}
    return getGcd(a, b);
}

public static int getInversion (int number, int modul){
    if (getGcd(number, modul)!=1) throw new ArithmeticException("Cislo nema inverzi");
    int a = number;
    int b=1;
    while ((a*b)%modul!=1){
        b++;
    }
    return b;
}

public static double getGcd(double a, double b){
    if (a<b) return getGcd(b, a);
    if (b==0) return a;
    while (a>=b) {a=a-b;}
    return getGcd(a, b);
}

public static double getInversion (double number, double modul){
    if (getGcd(number, modul)!=1) throw new ArithmeticException("Cislo nema inverzi");
    double a = number;
    double b=1;
    while ((a*b)%modul!=1){
        b++;
    }
    return b;
}

public static boolean isPrime(int possiblePrime){
    for (int i=0;i<Math.sqrt(possiblePrime);i++){
        if(possiblePrime%i==0) return false;
    }
    return true;
}

}
