/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cv06;

@AfterClass
public static void

tearDownClass() throws Exception {}


@Before
public void

setUp(){}

@After
public void tearDown(){}

@Test
public void test1(){
     File f=new File("ints.txt");
         FileWriter fw = new FileWriter(f);
         BufferedWriter bw = newBufferedWriter (fw);
         bw.write(10);
         bw.newLine();
         bw.write(20);
         bw.close;
}
   try    {
             IntegerReader ir = new IntegerReader("ints.txt");
             assertTrue(ir.nextInteger()==10);
             assertTrue(ir.nextInteger()==20);
          }
            catch (FileNotFoundException ex){fail();}
            catch (IOException ex) {fail();}

/*public class IntegerReaderTest {

} */
