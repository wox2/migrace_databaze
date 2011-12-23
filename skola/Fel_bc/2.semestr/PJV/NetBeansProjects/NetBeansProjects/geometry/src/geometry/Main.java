/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

import java.util.Random;
import java.awt.geom.*;
/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rectangle2D.Double o1= new Rectangle2D.Double();
       o1.createIntersection(o1);
      /*  Random rand = new Random();
        int random1 = rand.nextInt(10);
        int random2 = rand.nextInt(10);
        int random3 = rand.nextInt(10);
        int random4 = rand.nextInt(10);
        Square o1 = new Square(random1, random2, random3);
        o1.paint();
        System.out.println(random1 + ", " + random2 + ", " + random3 +
                ", " + random4 + ", ");
         Square2 c1=new Square2(4,5,3);
         Square2 c2=new Square2();
         Square2 c3=new Square2(35);
         Square2 c4=new Square2(2,34);
        c1.paint();
        c2.paint();
        c3.paint();
        c4.paint(); */
    }
}
