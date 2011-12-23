package alg8;

import java.io.*;


public class CislaRetezce {
  public static void main(String[] args) throws Exception {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("temp.bin"));
    out.writeInt(1);
    out.writeInt(2);
    out.writeObject("prvni retez");
    out.writeObject("druhy retez");
    out.close();
    ObjectInputStream in = new ObjectInputStream(new FileInputStream("temp.bin"));
    System.out.println(in.readInt()+" "+in.readInt());
    String s1 = (String)in.readObject();
    String s2 = (String)in.readObject();
    System.out.println(s1+" "+s2);
  }
}