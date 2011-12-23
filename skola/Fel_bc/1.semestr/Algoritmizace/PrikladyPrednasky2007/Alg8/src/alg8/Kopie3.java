package alg8;


import java.io.*;

public class Kopie3 {
  public static void main(String[] args) {
    if (args.length<2) {
      System.out.println("použití: Kopie3 <vstup> <výstup>");
      return;
    }
    try {
      FileInputStream in = new FileInputStream(args[0]);
      FileOutputStream out = new FileOutputStream(args[1]);
      int b = in.read();
      while (b!=-1) {
        out.write(b);
        b = in.read();
      }
      out.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}