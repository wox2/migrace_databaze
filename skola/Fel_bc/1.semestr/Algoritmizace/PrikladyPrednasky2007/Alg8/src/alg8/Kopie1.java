package alg8;

import java.io.*;

public class Kopie1 {
  public static void main(String[] args) throws IOException {
    FileInputStream in = new FileInputStream("vstup.txt");
    FileOutputStream out = new FileOutputStream("vystup.txt");
    int b = in.read();
    while (b!=-1) {
      out.write(b);
    //  System.out.println(b);
      b = in.read();
    }
    out.close();
    in.close();
  }
}