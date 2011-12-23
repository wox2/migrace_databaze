package alg8;

import java.io.*;


public class Dump {
  private static String hexaByte(int b) {
    final String hexa = "0123456789ABCDEF";
    return ""+hexa.charAt((b>>4)&0xf)+hexa.charAt(b&0xf);
  }

  private static String hexaInt(int x) {
    return hexaByte(x>>24) +
           hexaByte((x>>16)&0xff) +
           hexaByte((x>>8)&0xff) +
           hexaByte(x&0xff);
  }

  public static void main(String[] args) throws IOException {
    if (args.length<1) {
      System.out.println("použití: dump <vstupní soubor>");
      return;
    }
    FileInputStream in = new FileInputStream(args[0]);
    byte[] pole = new byte[16];
    String radek;
    int pocet = in.read(pole);
    int adr = 0;
    while (pocet>=0) {
      radek = "";
      for (int i=0; i<pocet; i++)
        radek += hexaByte(pole[i])+" ";
      System.out.println(hexaInt(adr)+" "+radek);
      adr += 16;
      pocet = in.read(pole);
    }
  }
}