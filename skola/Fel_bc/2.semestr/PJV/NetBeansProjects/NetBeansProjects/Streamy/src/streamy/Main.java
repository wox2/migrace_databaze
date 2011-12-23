/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package streamy;

import java.io.File;


public class Main {
public void Test() {}
  public static File vytvorNovyPrazdnySoubor(String jmeno) throws java.io.IOException
  {
    File f = new File(jmeno);
    f.mkdir();
    if(f.exists()) f.delete();
    f.createNewFile();
    return f;
  }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { System.out.println(System.getProperty("user.dir")); //zjisti a vypise aktualni adresar

        
    String jmeno = "Data.dta";
    try {
      File f = vytvorNovyPrazdnySoubor(jmeno);
      File f1= new
    }
    catch(java.io.IOException e)
    {
      System.out.print("Nepodarilo se vytvorit soubor " + jmeno);
      System.exit(1);
    }
  }
    

}
