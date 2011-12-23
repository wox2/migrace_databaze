package alg8;

import java.io.*;


public class Znaky {
  public static void main(String[] args) throws Exception  {
    FileWriter out = new FileWriter("znaky.txt");
    out.write("èau, nazdar");
    out.close();
    FileReader in = new FileReader("znaky.txt");
    char znaky[] = new char[20];
    int pocet = in.read(znaky);
    for (int i=0; i<pocet; i++)
      System.out.print(znaky[i]);
  }
}