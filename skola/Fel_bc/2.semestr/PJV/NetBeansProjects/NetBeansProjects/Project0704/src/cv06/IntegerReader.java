/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cv06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



/**
 *
 * @author já
 */

public class IntegerReader {
 protected BufferedReader br;
 protected File f;


 public IntegerReader(String fileName) throws FileNotFoundException{
 f=new File(fileName);
 FileReader r = new FileReader(f);
 br = new BufferedReader(r);
 }


 public int nextInteger() throws IOException{
 return Integer.valueOf(br.readLine());
 }
}
