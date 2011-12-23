package streamy;

import java.io.*;
import java.util.Date;

public class test2 {

    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        File fi = new File("E:\\Java-project\\prvni\\streamy\\src\\streamy\\Main.java");
        System.out.print(new Date());
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fi)));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\Java-project\\test\\muj.txt")));
            String s = null;
            while ((s = br.readLine()) != null) {
                if (s.length() > 0) {
                    bw.write(s);
                    bw.newLine();
                }

            }
            bw.close();
            br.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}