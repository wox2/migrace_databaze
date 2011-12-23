package rup.cesky.tvary_0;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


/*******************************************************************************
 * Instance tridy Multipresouvac je jedinacek slouzici 
 * k presunu nekolika grafickych objektu zaroven.
 * Obdobne jako SpravcePlatna prijima prostrednictvim metody <code>pridej</code>
 * do spravy objekty, kterymi pak po platne pohybuje.
 * <p>
 * Tyto objekty musi byt typu IPosuvny. Je-li objekt dokonce typu IMultiposuvny,
 * tak pote, co objekt presune do zadane cilove pozice, zavola jeho metodu
 * <code>presunuto</code>, ktera muze provest libovolnou akci (vetsinou preda
 * znovu objekt multipresouvaci, aby jej presunul do dalsi pozice).
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Multipresouvac
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /** Implicitni hodnota periody = pocet milisekund, ktere uplynou mezi
     *  dvema naslednymi prekreslenimi presouvanych objektu. */    
    private static final int PERIODA_0 = 50;

    /** Tento atribut je tu pouze pro zjednoduseni psani. */
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    /** Jedina existujici instance multipresouvace. */
    private static final Multipresouvac jedinacek = new Multipresouvac();

    

//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Pocet milisekund, ktere uplynou mezi dvema naslednymi prekreslenimi 
     *  presouvanych objektu. */
    private final int perioda = PERIODA_0;

    /** Seznam posouvanych objektu. Je deklarovany jako mapa,
     *  aby v nem bylo mozno rychle testovat pritomnost prvku.
     *  Linkovanou mapou je proto, protoze bude pri kresleni velice casto
     *  porchazen s vysokymi pozadavky na rychlost. */
    private final Map<IPosuvny,Animace> presouvane = 
                                        new LinkedHashMap<IPosuvny,Animace>();

    /** Tabulka dvojic [IPosuvny;Animace] reprezentovana jako mnozina. */
    private final Set<Map.Entry<IPosuvny,Animace>> dvojice = 
                                                        presouvane.entrySet();

    /** Casovac, ktery se postara o opakovane vykreslovani sverenych objektu.*/
    private final Timer timer = new Timer();
    
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    /** Promenna ovladajici beh multipresouvace. */
    private Multipresun multipresun = null;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================

    /***************************************************************************
     * Vrati hodnotu periody =  pocet milisekund, ktere uplynou mezi dvema
     * naslednymi prekreslenimi presouvanych objektu. 
     * 
     * @return Perioda prekreslovani v milisekundach.
     */   
    public int getPerioda()
    {
        return perioda;
    }
    

    /***************************************************************************
     * Vrati frekvenci prekreslovani, tj. pocet prekresleni za sekundu.
     * 
     * @return Frekvence prekreslovani.
     */   
    public int getFrekvence()
    {
        return 1000 / perioda;
    }
    
    
    
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vrati multipresouvac s periodou 50 ms.
     * @return 
     */
    public static Multipresouvac getInstance()
    {
        return jedinacek;
    }


    /***************************************************************************
     * Vytvori novy multipresouvac - vsechna potrebna nastaveni
     * jsou soucasti deklaraci atributu.
     */
    private Multipresouvac()
    {
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
    
    /***************************************************************************
     * Vrati pole se vsemi prave presouvanymi objekty. Vraceny seznam je vsak
     * platny pouze v dobe volani. Vzapeti na to mohou byt nektere objekty
     * dovezeny do svych cilovych pozic a mohou seznam "opustit".
     * 
     * @return Pole prave presouvanych objektu.
     */
    public IPosuvny[] getPresouvane()
    {
        synchronized( presouvane )
        {
            return presouvane.keySet().toArray(new IPosuvny[0]);
        }
    }
    
    
    
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== OSTATNI PREKRYTE METODY RODICOVSKE TRIDY ==================================
//== OSTATNI METODY INSTANCI ===================================================

    /***************************************************************************
     * Presune zadany posuvny objekt do pozadovane cilove pozice 
     * za zadanou dobu.
     *       
     * @param ip        Presouvany objekt
     * @param xn        Vodorovna souradnice cilove pozice           
     * @param yn        Svisla souradnice cilove pozice           
     * @param sekund    Doba, kterou bude presun trvat, v sekundach 
     */    
    public void presun( IPosuvny ip, int xn, int yn, double sekund )
    {
        if( sekund <= 0 ) {
            throw new IllegalArgumentException(
                "Doba presunu musi byt kladna!");
        }
        presun( ip, xn, yn, 0, sekund*1000 );
    }
    

    /***************************************************************************
     * Presune zadany posuvny objekt do pozadovane cilove pozice 
     * za zadanou dobu.
     *       
     * @param ip        Presouvany objekt
     * @param pozice    Pozadovana cilove pozice           
     * @param sekund    Doba, kterou bude presun trvat, v sekundach 
     */    
    public void presun( IPosuvny ip, Pozice pozice, double sekund )
    {
        presun( ip, pozice.x, pozice.y, sekund );
    }
    

    /***************************************************************************
     * Presune zadany posuvny objekt do pozadovane cilove pozice 
     * za zadanou dobu.
     *       
     * @param ip        Presouvany objekt
     * @param pozice    Pozadovana cilove pozice           
     * @param rychlost  Pocet bodu, o ktere se objekt presune za sekundu
     */    
    public void presun( IPosuvny ip, Pozice pozice, int rychlost )
    {
        presun( ip, pozice.x, pozice.y, rychlost, 0 );
    }


    /***************************************************************************
     * Presune zadany posuvny objekt do pozadovane cilove pozice 
     * za zadanou dobu.
     *       
     * @param ip        Presouvany objekt
     * @param xn        Vodorovna souradnice cilove pozice           
     * @param yn        Svisla souradnice cilove pozice           
     * @param rychlost  Pocet bodu, o ktere se objekt presune za sekundu
     */    
    public void presun( IPosuvny ip, int xn, int yn, int rychlost )
    {
        if( rychlost <= 0 ) {
            throw new IllegalArgumentException(
                "Rychlost presunu musi byt kladna!");
        }
        presun( ip, xn, yn, rychlost, 0 );
    }


    /***************************************************************************
     * Zastavi pozadovany objekt, tj. vyjme jej ze seznamu objektu, 
     * s nimiz pohybuje. Pro jeho pristi rozpohybovani je potreba
     * znovu pozadat multipresouvac o jeho presunuti.     
     *       
     * @param ip   Zastavovany objekt         
     *       
     * @return     Informace o tom, byl-li objekt mezi presouvanymi     
     */ 
    public boolean zastav( IPosuvny ip )
    {
        synchronized( presouvane )
        {
            if( presouvane.containsKey(ip) )
            {
                presouvane.remove( ip );
                return true;
            }
            return false;
        }
    }    


   /***************************************************************************
    * Zastavi zastavi presouvace a zrusi vsechny plany presunu. 
    */ 
    public void zastavVse()
    {
        stop();
        synchronized( presouvane )
        {
            presouvane.clear();
        }
    }    


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Presune zadany posuvny objekt do pozadovane cilove pozice 
     * za zadanou dobu nebo zadanou rychlosti.
     *       
     * @param ip            Presouvany objekt
     * @param xn            Vodorovna souradnice cilove pozice           
     * @param yn            Svisla souradnice cilove pozice           
     * @param rychlost      Pocet bodu, o ktere se objekt presune za sekundu
     * @param milisekund    Doba, kterou bude presun trvat, v sekundach 
     */    
    private void presun( IPosuvny ip, int xn, int yn, 
                         int rychlost, double milisekund )
    {
        if( ip == null ) {
            throw new NullPointerException(
                "Presouvany objekt nesmi byt null!");
        }
        Animace a = new Animace( ip, xn, yn, rychlost, milisekund  );
        synchronized(presouvane)
        {
            if( presouvane.get(ip) != null )
            {
                a = null;
                throw new IllegalStateException(
                    "Pridavany objekt jiz je mezi presouvanymi!");
            }
//            SP.pridej( ip );
            presouvane.put( ip, a );
            start();
        }//synchronized(presouvane)
    }
        
    
    /***************************************************************************
     * Ukonci multipresun a tim uvolni procesor.    
     */     
    private synchronized void stop()
    {
        if( multipresun == null ) {
            return;
        }
        multipresun.cancel();
        multipresun = null;
    }
    
    
    /***************************************************************************
     * Spusti novy multipresun. 
     */     
    private synchronized void start()
    {
        if( multipresun == null )
        {
            multipresun = new Multipresun();
            timer.scheduleAtFixedRate( multipresun, perioda, perioda );
            //timer.schedule(multipresun, perioda, perioda);
        }
    }
    

    
//== VNORENE A VNITRNI TRIDY ===================================================

    //==========================================================================
    /***************************************************************************
     * Soukroma vnitrni trida, jejiz instance sdruzuji vsechny potrebne 
     * informace o presouvanem objektu. Funguje pouze jako prepravka.
     */          
    private class Animace
    {
        double   x,  y;     //Aktualni souradnice objektu
        double   dx, dy;    //Prirustek souradnic v jednom kroku
        IPosuvny objekt;    //Animovany objekt
        int      kroku;     //Pocet kroku potrebnych pro presun.


        /***********************************************************************
         * Vytvori animaci na zaklade zadaneho objektu, jeho cilovych 
         * souradnic a doby, behem niz ma techto souradnic dosahnout.
         * nebo rychlosti, s niz se ma k tomuto cili presouvat.
         * Volajici metoda musi zabezpecit, aby doba nebo rychlost byla nulova.
         *           
         * @param ip        Presouvany objekt         
         * @param xn        Vodorovna souradnice cile         
         * @param yn        Svisla souradnice cile
         * @param rychlost  Pocet bodu "zdolanych" za sekundu
         * @param doba      Pocet milisekund         
         */                          
        Animace( IPosuvny ip, int xn, int yn, int rychlost, double doba )
        {
            objekt = ip;
            Pozice p = ip.getPozice();
            x = p.x;
            y = p.y;
            dx = (xn - x);
            dy = (yn - y);
            if( rychlost > 0 )
            {
            	double vzdalenost = Math.hypot( dx, dy );
                kroku = (int)(1000 * vzdalenost / (rychlost * perioda));
            }
            else
            {
            	kroku = (int)(doba / perioda); 
            }
            if( kroku < 1 ) {
                kroku = 1;
            }
            //Posunuti zlepsuje rozlozeni presunu pri malem poctu kroku
            x  += 0.4;
            y  += 0.4;
            dx /= kroku;
            dy /= kroku;
        }
        

    }//private class Animace


    
    //==========================================================================
    /***************************************************************************
     * Instance tridy jsou ulohy ralizujici vlastni multipresun.
     * Multipresun je definvan jako samostatna trida proto, aby jej bylo mozno
     * pri ukonceni vsech zadanych presunu vypnout a pri vzniku novych
     * pozadavku na presun zase zapnout. 
     */    
    private class Multipresun extends TimerTask
    {
        /***********************************************************************
         * Metoda vyzadovana rozhranim Runable implementovanym rodicovskou
         * tridou TimerTask - tuto metodu zavola Timer pokazde, 
         * kdyz se rozhodne spustit dalsi provedeni opakovaneho ukolu 
         * (Timertask) = multipresunu.              
         */     
        public void run()
        {
            //Pri prekreslovani se nesmi menit pocet objektu v seznamu
            synchronized( presouvane )
            {
                Iterator it = dvojice.iterator();
                SP.nekresli(); {
                    while( it.hasNext() )
                    {
                        Animace a = (Animace)(((Map.Entry)it.next()).getValue());
                        a.x += a.dx;
                        a.y += a.dy;
                        a.objekt.setPozice((int)a.x, (int)a.y);
                        if( --a.kroku == 0 )
                        {
                            it.remove();
                            if( a.objekt instanceof IMultiposuvny )
                            {
                                final IMultiposuvny aa =
                                                    (IMultiposuvny) (a.objekt);
                                Thread t = new Thread(a.toString()) {
                                    @Override
                                    public void run() {
                                        aa.presunuto();
                                    }//public void run()
                                };//Thread t = new Thread()
                                t.start();
                            }
                            //Pri vyprazdneni seznamu zrus ulohu
                            if( presouvane.size() <= 0)
                            {
                                stop();
                                break;
                            }
                        }
                    }//while
                } SP.vratKresli();
            }//synchronized( presouvane )
       }//public void run()
        
    }//private class Multipresun extends TimerTask



//== TESTY A METODA MAIN =======================================================
}


