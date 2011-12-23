/**
 * Title:        Problém N dam<p>
 * Description:  Problém N dam v Javì<p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
//package damy;
//import java.io.*;
import java.util.*;

public class Resitel {
  int [] X;
  int N;
  int pocRe = 0;

  public Resitel() {
    System.out.print("Zadej pocet dam: ");
    N = MojeIO.inInt();
    if(N <= 0) System.exit(1);
    X = new int[N];
    nuluj();
  }


  void nuluj()
  {
   for(int i = 0; i < N; i++) X[i] = -1;
  }

  boolean Bezpecne(int k, int m)
  {  //{je dana  pozice bezpecna? }
   //k: radek, m: sloupec}
     boolean b = true;
     if(m > 0)
     {
        int l = 0;                // predchozi sloupce
        while (b && (l < m))
        {
          b = (X[l]!=k) &&                      //{na stejnem radku}
              ((X[l]+l) != (m+k)) &&         //{stejn  vedl. diagonala}
              ((X[l]-l) != (k-m));           //{stejna hl. diagonala}
              if(!b) break;
              l++;
         }
    }
    return b;
  }

//{vlastni reseni - rekurzivni backtracking}
  public void vyres(int m)
  {
     if(m == N)
     {
          pocRe++;
     }
     else
     {
      for(int k = 0; k < N; k++)
      {
         if(Bezpecne(k,m))
         {
           X[m] = k;
           vyres(m+1);
         }
      }
      X[m] = -1;
     }
  }
  public void reseni()
  {
    long start = (new Date()).getTime();
    vyres(0);
    long cas = (new Date()).getTime() - start;
    System.out.println("uloha ma " + pocRe + " reseni");
    System.out.println("reseni trvalo " + cas/1000.0 + " s");
  }

  public static void main(String[] args) {
    Resitel res = new Resitel();
    res.reseni();
  }
}