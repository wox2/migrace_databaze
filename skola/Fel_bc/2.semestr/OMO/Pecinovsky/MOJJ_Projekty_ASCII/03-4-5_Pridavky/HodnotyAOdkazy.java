/*******************************************************************************
 * Trida HodnotyAOdkazy obsahuje statickou metodu testik, ktera demonstruje
 * rozdil mezi hodnotovymi a referencnimi (=odkazovymi) 
 * objektovymi datovymi typy.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, kveten 2004
 */
public class HodnotyAOdkazy
{
    private HodnotyAOdkazy() {}
    

    /***************************************************************************
     * Demonstruje rozdil mezi ohodnotovymi a referencnimi 
     * objektovymi datovymi typy.
     */
    public static void testik()
    {
        //Definujeme tri promenne hodnotoveho typu String
        String hodnota_1 = "Prvni";
        String hodnota_2 = "Druhy";
        //Druha a treti promenna bude odkazovat na stejny objekt
        String hodnota_3 = hodnota_2;   
        
        //Definujeme tri promenne referencniho typu Strom
        Strom_5 odkaz_1 = new Strom_5();
        Strom_5 odkaz_2 = new Strom_5();
        //Druha a treti promenna bude odkazovat na stejny objekt
        Strom_5 odkaz_3 = odkaz_2;
        
        System.out.println ( "Stav pred upravou:\n" + 
            hodnota_1 + "\n" + hodnota_2 + "\n" + hodnota_3 + "\n\n" +
            odkaz_1   + "\n" + odkaz_2   + "\n" + odkaz_3 );
            
        //Zmenim hodnotu objektu odkazovaneho druhou promennou
        
        //U hodnotovych typu bude objekt nahrazen jinym
        hodnota_2 = hodnota_2 + "+doplnek";
        
        //U odkazovanych objektu se zmeni pouze stav objektu
        odkaz_2.posunVpravo();
        
        System.out.println ( "\n\nStav po uprave:\n" + 
            hodnota_1 + "\n" + hodnota_2 + "\n" + hodnota_3 + "\n\n" +
            odkaz_1   + "\n" + odkaz_2   + "\n" + odkaz_3 );
    }
    
}

