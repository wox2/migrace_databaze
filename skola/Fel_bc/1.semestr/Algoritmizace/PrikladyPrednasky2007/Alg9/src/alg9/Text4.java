package alg9;

import java.io.*;

public class Text4 {
  public static void main(String[] args) throws Exception {
  /*  if (args.length==0) {
      System.out.println("použití: Text4 <vstup>");
      return;
    }
    */BufferedReader in = new BufferedReader(new FileReader("text4.txt"));
    String radek = in.readLine();
    while (radek!=null) {
      System.out.println(radek);
      radek = in.readLine();
    }
  }
}