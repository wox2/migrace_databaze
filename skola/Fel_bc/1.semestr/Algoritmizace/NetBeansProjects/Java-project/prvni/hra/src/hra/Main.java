/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import java.awt.geom.Point2D;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.print(pi(1000000000));
    }

    static boolean vKruhu(Point2D.Double p) {
        boolean vKruhu = false;
        if (vzdalenostOdPocatku(p) <= 1) {
            vKruhu = true;
        }
        return vKruhu;
    }

    static double pi(int pocetExp) {
        double y = 1;
        for (int i = 1; i <= pocetExp; i++) {
            Point2D.Double p = nahodnyBod();
            if (vKruhu(p)) {
                y++;
            }
        }
        double pi = 4 * y / pocetExp;

        return pi;
    }

    static Point2D.Double nahodnyBod() {
        Point2D.Double p = new Point2D.Double(Math.random(), Math.random());
        return p;
    }

    static double vzdalenostOdPocatku(Point2D.Double p) {
        return p.distance(0, 0);
    }
}
