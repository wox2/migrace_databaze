/* Soubor Znaky.java
 * program vypíše univerzální jména znakù èeské
 * a slovenské abecedy s diakritickımi
 * znaménky
 */
  
public class Znaky {

  public Znaky() {
  }
  public static void main(String[] args) {
    String zn = "áäèïéìí¾åòóôàøšúùıÁÄÈÏÉÌÍ¼ÅÒÓÔÀØŠÚÙİ";
    Znaky znaky1 = new Znaky();
    System.out.println("ah\u00f3j\nlidi");
    for(int i = 0; i < zn.length(); i++)
    {
        int z = zn.charAt(i);
        String s = new Konvertor(16).konverze(z);
        String ss = "\\u0" + (s.length()==2?"0":"")+s;
        System.out.println(""+zn.charAt(i)+" "+ ss);
    }
  }
}