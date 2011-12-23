package cislo_slovy;

/**
 * @author Jan Hoskovec
 * @version 9.4.2004
 */
public class HonzaSlovy
{
//-------------------------------------atributy------------------------------------
    //popisy cisel od 0 do 9
    private static String jednotky[] = new String[]{
        "nula","jedna","dva","tri","ctyri","pet","sest","sedm","osm","devet",
        "deset","jedenact","dvanact","trinact","ctrnact","patnact","sestnact",
        "sedmnact","osmnact","devatenact"
            };
    //popisy desitek (20, 30 ...)
    private static String desitky[] = new String[]{
        "dvacet","tricet","ctyricet","padesat","sedesat","sedmdesat",
        "osmdesat","devadesat"
            };
            
    //popisy stovek ( 100, 200, 300 ...)
    private static String stovky[] = new String[]{
        "sto","dve ste", "tri sta","ctyri sta","pet set","sest set",
        "sedm set","osm set","devet set"
            };
//-------------------------------------metody---------------------------------------
    /**
     * Jedina verejna metoda tridy vraci popis zadaneho cisla
     * @param   c   jakekoli cislo od 0 do 99
     * @return  zadane cislo slovy
     */ 
    public static String cislo( int c ){
        String s = "";//sem se bude postupne ukladat popis pro vraceni
        
        //kontrola platnosti parametru
        if( Math.abs( c ) > 999999 ){
            throw new IllegalArgumentException("Absolutni hodnota cisla " + c + " je vetsi " + 
            "nez 999.999 a tak ho tato verze neni schopna urcit.");
            }
         //prvni rozhodovani - pokud je cislo zaporne, bude popis zacinat slove minus,
         //v opacnem pripade zustane zacatek retezce prazdny
        if( c < 0 ){
            s = "minus ";
            c = -c;
        }
        
         //dostane popis cisla a vysledek prida na konec retezce
        if( c < 1000 ){
            s += getKratke( c );
        }else{
            s += getDlouhe( c );
        }
        
        return s;   //vrati urcenou hodnotu
        
    }
    
    
    
//-----------------------------------pomocne metody----------------------------------
    //vraci popis cisla s jednoslovnym nazvem( mensi nez dvacet )
    private static String getJednociferne( int c ){
        //kontrola argumentu
        if( ( c < 0 )||( c > 19 ) ){
            throw new IllegalArgumentException(
                "Chybny argument - tato metoda vraci jen cisla od 0 do 19.");
        }
        return jednotky[ c ];
    }
    
    
    //vraci popis cisla od 20 do 99
    private static String getDvouciferne( int c ){
        //kontrola argumentu
        if( ( c < 0 ) || ( c > 99 ) ){
            throw new IllegalArgumentException(
                "Chybny argument - tato metoda vraci jen cisla od 0 do 99.");
        }
        if( (c % 10) == 0 ){//pokud je cislo delitelne deseti
            return desitky[ ( c / 10 ) - 2 ];//vrat jen desitku
        }else{
            return desitky[ ( c / 10 ) - 2 ] + " " + jednotky[ c % 10 ];
            //vrat desitku a jednotku
        }
    }
    
    
    //vraci popis cisla od 100 do 999
    private static String getTrojciferne( int c ){
        //kontrola argumentu
        if( ( c < 100 ) || ( c > 999 ) ){
            throw new IllegalArgumentException("Chybny argument - tato metoda "+
            "vraci jen cisla od 100 do 999.");
        }
        
        if( (c % 100) == 0 ){//pokud je cislo delitelne stem
            return stovky[ ( c / 100 ) - 1 ];//vrat jen stovku
        }else{
            if( (c % 100)>19 ){
                return stovky[ ( c / 100 ) - 1 ] + " " + getDvouciferne( c % 100  );
            }else{
                return stovky[ ( c / 100 ) - 1 ] + " " +getJednociferne( c % 100 );
            }
        }
    }

    
    //vraci popis cisla od 1000 do 999999
    private static String getDlouhe( int c ){
        //kontrola argumentu
        if( ( c < 1000 ) || ( c > 999999 ) ){
            throw new IllegalArgumentException("Chybny argument - tato metoda "+
            "vraci jen cisla od 1000 do 999 999.");
        }
        
        //pripravi si dva pomocne retezce
        String s1, s2, s3;
        s1 = getKratke( c / 1000 );
        s2 = getKratke( c % 1000 );
        s1 = ( s1 == jednotky[ 1 ] ) ? "" : s1 + " ";//jedna tisic se nezobrazuje
        s2 = ( s2 == jednotky[ 0 ] ) ? "" : " " + s2 ;
        
        //urci, jestli prostredni slovo bude tisic nebo tisice
        if( ( c / 1000 ) > 1 && ( c / 1000 ) < 5 ){
            s3 = "tisice";
        }else{
            s3 = "tisic";
        }
        //vrati vysledek
        return s1 + s3 + s2;
        
    }
    
    
    private static String getKratke( int c ){
        //kontrola argumentu
        if( ( c < 0 ) || ( c > 999 ) ){
            throw new IllegalArgumentException("Chybny argument - tato metoda "+
            "vraci jen cisla od 0 do 999.");
        }
        
        //postupne se zkousi volat ruzne metody, vysledek se vezme od te, od ktere to pujde
        try{
            return getJednociferne( c );//zkus to dostat z jednocifernych
        }catch( IllegalArgumentException exc ){//pokud to nevyjde
            try{
                return getDvouciferne( c );//zkus to cislo vytahnout z dvoucifernych
            }catch( IllegalArgumentException exc2 ){//pokud ani to nepujde
                return getTrojciferne( c );
                //jina moznost uz neni, takze neni potreba hlidat vyjimku
            }
        }
    }

    private HonzaSlovy() {}
}

