/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrickascislempi;

import java.awt.geom.Point2D;
import java.util.Scanner;

/**
 *
 * @author já
 * metoda Monte Carlo
 * API Aplication Program interface je na netu
 * doukumentace, java.awt.geom, Point2D.Double
 */
public class Main {

    static double zjisteniHodnotyCislaPi(double pocetExperimentu) {
        // nebo muzem pouzit void jako parametr metody
        int bodyUvnitr=0;
        for (int i = 1; i <= pocetExperimentu; i++) {
            Point2D.Double k=nahodnyBod();

            if (polohaBoduVuciKruzniciACtverci(k)== true) {
                 bodyUvnitr++;}}
            double pi=bodyUvnitr/pocetExperimentu*4.0;
              return pi;
    }
    static boolean polohaBoduVuciKruzniciACtverci(Point2D.Double p) {
        // Point P reference na
        double vzd = vzdalenostBoduOdPocatku(p);
        boolean jeVKruznici=false;
        if (vzd<=1)
            jeVKruznici=true;
        return jeVKruznici;
    }

    static Point2D.Double nahodnyBod() {
        return new Point2D.Double(Math.random(), Math.random());
    }

    static double vzdalenostBoduOdPocatku(Point2D.Double p) {
        return p.distance(0, 0); // instancni metoda, potrebuje pred sebou objekt.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Napiste pocet pokusu, ktere maji slouzit k vypoctu cisla Pi:");
        int pocetPokusu=new Scanner(System.in).nextInt();
        System.out.println("Cislo Pi je priblizne: " + zjisteniHodnotyCislaPi(pocetPokusu));
    }
}
