package alg9;

import java.io.*;


public class Text1 {
  public static void main(String[] args) throws Exception  {
    FileWriter out = new FileWriter("text1.txt");
    out.write("èau, nazdar\nrybo");
    out.close();
    FileReader in = new FileReader("text1.txt");
    char znaky[] = new char[20];
    int pocet = in.read(znaky);
    for (int i=0; i<pocet; i++)
      System.out.print(znaky[i]);
  }
}