package alg8;

import java.io.*;

public class Kopie5 {
  public static void main(String[] args) {
    if (args.length<2) {
      System.out.println("použití: Kopie3 <vstup> <výstup>");
      return;
    }
    byte[] buf = new byte[1000];
    try {
      FileInputStream in = new FileInputStream(args[0]);
      FileOutputStream out = new FileOutputStream(args[1]);
      while (in.available()>0) {
        int pocet = in.read(buf);
        out.write(buf,0,pocet);
      }
      out.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}