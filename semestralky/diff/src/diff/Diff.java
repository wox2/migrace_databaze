/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diff;

/**
 *
 * @author woxie
 */
public class Diff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s1 = "XMJYAUZ";
        String s2 = "MZJAWXU";
        //System.out.println(getLTS(s1, s2));
        //System.out.println(  3 >> 1 & 1);
        
//        String arr[] = new String [4];
//        for(String e : arr){
//            System.out.println(e);
//        }
        TrivialDiff diff = new TrivialDiff(s1, s2);
//        System.out.println("Latest substrings for \n" + s1 + "\n" + s2 + ":");
//        for(String substring : diff.getLatestSubstrings()){
//            System.out.println(substring);
//        }
        
        System.out.println("Diff elementy retezcu: \n" + s1 + "\n" + s2 + "\n------------");
        for(DiffElement element : diff.getDiffElements()){
            System.out.println(element);
        }
      
//        System.out.println("Diff retezcu: \n" + s1 + "\n" + s2 + "\n------------");
//        System.out.println(diff.getFullDiff(Boolean.FALSE));
//        
//        System.out.println("Patch retezcu: \n" + s1 + "\n" + s2 + "\n------------");
//        for(DiffElement element : diff.getPatch()){
//            System.out.println(element);
//        }
        
        
    }
    
    public static String getLTS(String s1, String s2){
        if(s1.equals("") || s2.equals("")){
            return "";
        }
        if(s1.charAt(0) == s2.charAt(0)){
            return s1.charAt(0) + getLTS(s1.substring(1), s2.substring(1));
        } 
        String first = getLTS(s1.substring(1), s2);
        String second = getLTS(s1, s2.substring(1));
        
        if(first.length() < second.length() ){
            return second;
        }
        return first;
    } 
}
