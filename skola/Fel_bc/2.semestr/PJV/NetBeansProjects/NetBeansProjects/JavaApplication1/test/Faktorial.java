/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssssss
 */
public class faktorial {
  public static void main (String[] arg)
    {
        System.out.println("Zadej cele cislo: "); //
     int n = MojeIO.inInt();
     int s = 1;
       while (n>1)
       {
           s=s*n;
           n=n-1;
       }
        System.out.println("Jeho faktorial je " + s);

    }
}
