package alg8;

import java.io.*;
import java.util.*;

public class Kopie2 {
  static FileInputStream vstup() {
   Scanner sc = new Scanner(System.in);
   for (;;) {
     System.out.print("zadejte vstupní soubor: ");
      String jmeno = sc.nextLine();
      if (jmeno.equals("")) System.exit(0);
      try {
        FileInputStream in = new FileInputStream(jmeno);
        return in;
      } catch (FileNotFoundException e) {
        System.out.println("soubor neexistuje");
      }
    }
  }

  static FileOutputStream vystup() {
     Scanner sc = new Scanner(System.in);
     for (;;) {
      System.out.print("zadejte výstupní soubor: ");
      String jmeno = sc.nextLine();
      if (jmeno.equals("")) System.exit(0);
      try {
        FileOutputStream in = new FileOutputStream(jmeno);
        return in;
      } catch (FileNotFoundException e) {
        System.out.println("soubor nelze vytvoøit");
      }
    }
  }

  public static void main(String[] args) {
    FileInputStream in = vstup();
    FileOutputStream out = vystup();
    try {
      int b = in.read();
      while (b!=-1) {
        out.write(b);
        b = in.read();
      }
      in.close();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}