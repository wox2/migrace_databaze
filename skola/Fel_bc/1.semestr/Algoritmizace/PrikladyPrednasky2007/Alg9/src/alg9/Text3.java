package alg9;

import java.io.*;

public class Text3 {
  public static void main(String[] args) throws Exception  {
    PrintWriter out = new PrintWriter(new FileWriter("text3.txt"));
    int a = 10, b = 20;
    out.print(a);
    out.print("+");
    out.print(b);
    out.print("=");
    out.println(a+b);
    out.println("konec programu");
    out.write("nazdar");
    out.close();
  }
}