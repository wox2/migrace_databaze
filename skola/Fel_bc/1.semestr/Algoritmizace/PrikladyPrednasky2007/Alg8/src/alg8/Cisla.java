package alg8;


import java.io.*;

public class Cisla {
  public static void main(String[] args) throws Exception {
    DataOutputStream out = new DataOutputStream(new FileOutputStream("temp.bin"));
    for (int i=0; i<100; i++)
      out.writeDouble(2.5);
    out.close();
    DataInputStream in = new DataInputStream(new FileInputStream("temp.bin"));
    double soucet = 0;
    while (in.available()>0)
      soucet += in.readDouble();
    System.out.println(soucet);
  }
}
