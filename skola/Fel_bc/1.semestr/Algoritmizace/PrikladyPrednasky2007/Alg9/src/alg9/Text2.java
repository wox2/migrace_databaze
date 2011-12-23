package alg9;

import java.io.*;

public class Text2 {
  public static void main(String[] args) throws Exception  {
    FileWriter out = new FileWriter("text2.txt");
    int a = 10, b = 20;
    out.write(String.valueOf(a));
    out.write("+");
    out.write(String.valueOf(b));
    out.write("=");
    out.write(String.valueOf(a+b));
    out.close();
  }
}