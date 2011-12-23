package alg8;

import java.io.*;

public class Test {
  public static void main(String[] args) throws IOException {
    byte b = -128;
    System.out.println((b>>>4)&0xf);
    FileOutputStream out = new FileOutputStream("pole.bin");
    byte[] pole = {1,2,3,4,5,6,7,8,9};
    out.write(pole);
    out.close();
    FileInputStream in = new FileInputStream("pole.bin");
    byte[] pole2 = new byte[20];
    System.out.println(in.read(pole2));
  }
}