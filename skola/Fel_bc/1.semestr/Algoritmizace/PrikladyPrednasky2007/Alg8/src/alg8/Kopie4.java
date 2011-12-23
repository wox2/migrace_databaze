package alg8;

import java.io.*;

public class Kopie4 {
  public static void main(String[] args) {
    if (args.length<2) {
      System.out.println("použití: Kopie3 <vstup> <výstup>");
      return;
    }
    try {
      FileInputStream in = new FileInputStream(args[0]);
      FileOutputStream out = new FileOutputStream(args[1]);
      while (in.available()>0) {
        out.write(in.read());
      }
      out.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}