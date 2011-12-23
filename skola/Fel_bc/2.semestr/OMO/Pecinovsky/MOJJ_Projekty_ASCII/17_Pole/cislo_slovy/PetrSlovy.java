package cislo_slovy;

/*******************************************************************************
 * Trida Slovy slouzi k ...
 *
 * @author    Petr Marek
 * @version   0.1,  1.4.2003
 */
public class PetrSlovy
{
//== SOUKROME KONSTANTY ========================================================
//== VEREJNE KONSTANTY =========================================================
//== RETEZCOVE LITERALY ========================================================
//== ATRIBUTY TRIDY ============================================================
    
    private static int len;
    private static String stovky;
    private static String desitky;
    private static String jednotky;
    
    public static String stringCislo2;
    public static String stringCislo;
    public static String stovek;
    public static String desitek;
    public static String jednotek;
    public static String slovy;
    
    public static String[] sta = new String[] {
        "",    "sto", "dve ste",  "tri sta", "ctyri sta",
        "pet set", "sest set",  "sedm set", "osm set", "devet set" };

    public static String[] nactky = new String[] {
        "deset",    "jedenact", "dvanact",  "trinact", "ctrnact",
        "patnact", "sestnact",  "sedmnact", "osmnact", "devatenact" };
        
    public static String[] deset = new String[] {
        "",    "deset", "dvacet",  "tricet", "ctyricet",
        "padesat", "sedesat",  "sedmdesat", "osmdesat", "devadesat" };

    public static String[] jednotka = new String[] {
        "",    "jedna", "dva",  "tri", "ctyri",
        "pet", "sest",  "sedm", "osm", "devet" };
       
    
    
//== ATRIBUTY INSTANCI =========================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Bezparametricky konstruktor ...
     */
        private PetrSlovy()
    {
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

//== NOVE ZAVEDENE METODY INSTANCI =============================================
    public static String rekni(int cislo)
    {
        stringCislo2 = Integer.toString(cislo);
        stringCislo = " ";
        len = stringCislo2.length();
        for(int g=len-1;g>=0;g--)
        {
            stringCislo=stringCislo+(stringCislo2.charAt(g));
        }
        slovy="";
        for(int i=len;i>0;i--)
        {
            if(i==4 || i==7)
            {
                nazvy(stringCislo.charAt(i)-48, 1);
            }
            if(i==3 || i==6)
            {
                nazvy(stringCislo.charAt(i)-48, 3);
            }
           if(i==1)
            {
                nazvy(stringCislo.charAt(i)-48, 4);
            }
            if(i==2 || i==5)
            {
                if(stringCislo.charAt(i)-48 > 1) {
                    desitky(stringCislo.charAt(i) - 48);
                }
                else if(stringCislo.charAt(i)-48==1)
                {
                    nactky(stringCislo.charAt(i-1)-48);
                    i=i-1;
                }
            }
             if(i==4)
            {
                tisice(stringCislo.charAt(i)-48);
            }
            if(i==7)
            {
                miliony(stringCislo.charAt(i)-48);
            }
            if(i==3 || i==6)
            {
                stovky(stringCislo.charAt(i)-48);
            }
        }
/*        if(cislo!=0)
 *       {
 *       jednotek = jednotky(stringCislo);
 *       }   
 *       if(len>1)
*        {
            desitek = desitky(stringCislo);  
 *       }
        if(len==2)
        {
  *          stovek = stovky(stringCislo);
        }
  *     slovy = stovek + " " + desitek + " " + jednotek;
  */      
        return slovy;
        }
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
    private static void nazvy( int cislo, int mod )
    {
        hlavni:switch(cislo)
        {
            case 1:
                switch(mod)
                {
                    case 1:slovy=slovy+" jeden";break hlavni;                  
                    case 2:slovy=slovy+" jedna";break hlavni;                        
                    case 3:slovy=slovy+" jedno";break hlavni;
                    case 4:slovy=slovy+" jedna";break hlavni;
                }
                break;  //Jen aby prekladac nedaval varovani
            case 2:
                switch(mod)
                {
                    case 1:slovy=slovy+" dva";break hlavni;                  
                    case 2:slovy=slovy+" dve";break hlavni;                        
                    case 3:slovy=slovy+" dve";break hlavni;
                    case 4:slovy=slovy+" dva";break hlavni;
                }
                break;  //Jen aby prekladac nedaval varovani
            case 3:slovy=slovy+" tri";break;
            case 4:slovy=slovy+" ctyri";break;
            case 5:slovy=slovy+" pet";break;
            case 6:slovy=slovy+" sest";break;
            case 7:slovy=slovy+" sedm";break;
            case 8:slovy=slovy+" osm";break;
            case 9:slovy=slovy+" devet";break;
        }
    }
    private static String tisice( int cislo )
    {
        switch(cislo)
        {
            case 0:
            case 1:slovy=slovy+" tisic";break;
            case 2:
            case 3:
            case 4:slovy=slovy+" tisice";break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:slovy=slovy+" tisic";break;
        }
        return slovy;
    }
    private static String stovky( int cislo )
    {
        switch(cislo)
        {
            case 1:slovy=slovy+" sto";break;
            case 2:slovy=slovy+" ste";break;
            case 3:
            case 4:slovy=slovy+" sta";break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:slovy=slovy+" set";break;
        }
        return slovy;
    }
    
    private static String desitky( int cislo )
    {
        switch(cislo)
        {
            case 2:slovy=slovy+" dvacet";break;
            case 3:slovy=slovy+" tricet";break;
            case 4:slovy=slovy+" ctyricet";break;
            case 5:slovy=slovy+" padesat";break;
            case 6:slovy=slovy+" sedesat";break;
            case 7:slovy=slovy+" sedmdesat";break;
            case 8:slovy=slovy+" osmdesat";break;
            case 9:slovy=slovy+" devadesat";break;
        }
        return slovy;
    }

    private static String nactky( int cislo )
    {
        switch(cislo)
        {
            case 1:slovy=slovy+" jedenact";break;
            case 2:slovy=slovy+" dvanact";break;
            case 3:slovy=slovy+" trinact";break;
            case 4:slovy=slovy+" ctrnact";break;
            case 5:slovy=slovy+" patnact";break;
            case 6:slovy=slovy+" sestnact";break;
            case 7:slovy=slovy+" sedmnact";break;
            case 8:slovy=slovy+" osmnact";break;
            case 9:slovy=slovy+" devatenact";break;
        }
        return slovy;
    }
    
    private static String miliony( int cislo )
    {
        switch(cislo)
        {
            case 1:slovy=slovy+" milion";break;
            case 2:
            case 3:
            case 4:slovy=slovy+" miliony";break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:slovy=slovy+" milionu";break;
        }
        return slovy;
    }

    private static String desitky(String cislo)
    {
        desitky = deset[stringCislo.charAt(1)-48];
        return desitky;
    }
    
    
    private static String jednotky(String cislo)
    {
        jednotky = jednotka[stringCislo.charAt(2)-48];
        return jednotky;
    }
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

