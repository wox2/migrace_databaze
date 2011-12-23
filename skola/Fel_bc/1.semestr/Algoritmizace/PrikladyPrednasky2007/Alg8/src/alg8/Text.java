package alg8;

import java.io.*;

public class Text {
  public static void main(String[] args) throws IOException {
    FileWriter out = new FileWriter("soubor.txt");
    PrintWriter pout = new PrintWriter(out);
    out.write(out.getEncoding()+"\r\n");
    out.write("èau nazdar");
    out.write(System.getProperties().getProperty("line.separator"));
    PrintWriter outp = new PrintWriter(out);
    outp.print(25);
    outp.println();
    outp.println("novy radek");
    System.getProperties().list(outp);
    out.close();
    FileReader in = new FileReader("soubor.txt");
  }
}