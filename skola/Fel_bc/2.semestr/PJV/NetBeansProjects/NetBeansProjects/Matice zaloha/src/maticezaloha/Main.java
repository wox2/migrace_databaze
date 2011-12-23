/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticezaloha;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author já
 */
public class Main {

    static boolean znamenko;
    static double[][] A = {{1, 2}, {3, 4}};
    static double[][] B = {{1, 3}, {4, 2}};
    static boolean chyba = false;
    /** Pouzite globalni promenne:
     *   boolean znamenko - nutne pro vypocitani hodnosti determinantu
     *   pole A, B - ukladani si matic k dalsimu vyuziti
     *   boolean chyba - promenna slouzici k rozliseni, zdali maji zadane
     *                   matice spravne parametry

     */


    static int najdiNenulovy(double[][] matice, int iRadku, int iSloupce) {
        int nenul = iRadku;
        int i = iRadku + 1;
        while (i < matice.length && matice[i][iSloupce] == 0) {
            i++;
        }
        if (i < matice.length) {
            nenul = i;
        }
        return nenul;
    }
    /** Metoda najdiNenulovy najde prvni nenulovy radek, ktery nasleduje za
     * zadanym radkem. Parametry metody - matice, v ktere se ma nenulovy radek
     * hledat, dale index radku, od ktereho ma hledat a index sloupce, ve kterem
     * ma hledat (pro pripad, ze jsou nektere sloupce nulove neni index sloupce
     * roven indexu radku). Navratovou hodnotou je index prvniho nenuloveho radku.
     */


    static double[][] prohodRadky(double[][] matice, int cisloRadku, int cisloRadku2) {
        if (znamenko == true) {
            znamenko = false;
        } else {
            znamenko = true;
        }
        for (int i = 0; i < matice[0].length; i++) {
            double odklad = matice[cisloRadku][i];
            matice[cisloRadku][i] = matice[cisloRadku2][i];
            matice[cisloRadku2][i] = odklad;
        }
        return matice;
    }
    /** Metoda prohod radky prohodi dva radky v matici a po prohozeni meni hodnotu
     * znamenka. Vstupni promenne jsou jednak matice, dale pak index prvniho radku
     * a index druheho radku. Navratovou hodnotou je matice s prohozenymi radky
     */

    static double[][] zadaniMatice() {
        int pocetRadku = 0;
        while (pocetRadku <= 0) {
            System.out.println("Zadejte pocet radku matice:");
            pocetRadku = new Scanner(System.in).nextInt();
            if (pocetRadku <= 0) {
                System.out.println("Musite zadat prirozene cislo vetsi nez nula!");
            }
        }

        int pocetSloupcu = 0;
        while (pocetSloupcu <= 0) {
            System.out.println("Zadejte pocet sloupcu matice:");
            pocetSloupcu = new Scanner(System.in).nextInt();
            if (pocetSloupcu <= 0) {
                System.out.println("Musite zadat prirozene cislo vetsi nez nula!");
            }
        }
        double[][] matice = new double[pocetRadku][pocetSloupcu];
        for (int i = 0; i < pocetRadku; i++) {
            System.out.println(i + 1 + ". radek: ");
            for (int j = 0; j < pocetSloupcu; j++) {
                matice[i][j] = new Scanner(System.in).nextDouble();
            }
        }
        return matice;
    }
/**Metoda zadaniMatice slouzi k tomu, aby uzivatel mohl zadat libovolnou matici
 * z klavesnice. Uzivateli neni dovoleno, aby rozmery matice nulove ci zaporne.
 * Navratovou hodnotou je zadana matice.
 */
    
    static double[][] serazeniRadku(double[][] matice) {
        int nulSloup = 0;
        int prohozeni = 0;
        for (int iRadku = 0; iRadku - nulSloup < matice.length; iRadku++) {
            if (iRadku - prohozeni < matice[0].length) {
                int iSloupce = iRadku;
                if (matice[iRadku - nulSloup][iSloupce - prohozeni] == 0) {
                    int prvniNenulovy = najdiNenulovy(matice, iRadku - nulSloup,
                            iSloupce - prohozeni);
                    if (prvniNenulovy != iRadku - nulSloup) {
                        matice = prohodRadky(matice, prvniNenulovy, iRadku - nulSloup);
                        prohozeni++;
                    } else {
                        nulSloup++;
                    }
                }
            } else {
                iRadku = matice.length + nulSloup;
            }
        }
        return matice;
    }
    /** Metoda serazeniRadku slouzi k serazeni radku podle poctu nulovych prvku na
     * zacatku radku od nejmensiho k nejvetsimu. Paremetrem je matice, ktera se ma
     * setridit. Navratovou hodnotou je setridena matice. V teto metode je pouzita
     * metoda najdiNenulovy. promenne nulSloup a prohozeni zajistuji, aby se index 
     * v for cyklu zvysoval u radku ci sloupce, ale ne u obou. 
     */

    static double[][] gaussEli(double[][] matice) {
        int nulSloup = 0;
        boolean akce = true;
        for (int iRadku = 0; iRadku - nulSloup < matice.length; iRadku++) {
            if (iRadku < matice[0].length) {
                if (akce == true && matice[iRadku - nulSloup][iRadku] == 0) {
                    matice = serazeniRadku(matice);
                    akce = false;
                }
                if (matice[iRadku - nulSloup][iRadku] == 0) {
                    nulSloup++;
                } else {
                    for (int iNaslRadku = iRadku - nulSloup + 1;
                            iNaslRadku < matice.length; iNaslRadku++) {
                        if (iRadku < matice[0].length) {
                            double podil = matice[iNaslRadku][iRadku] /
                                    matice[iRadku - nulSloup][iRadku];
                            for (int iSloupce = 0; iSloupce < matice[0].length;
                                    iSloupce++) {
                                matice[iNaslRadku][iSloupce] -=
                                        matice[iRadku - nulSloup][iSloupce] * podil;
                                akce = true;
                            }
                        } else {
                            iNaslRadku = matice.length;
                        }
                    }
                }
            }
        }
        return matice;
    }
    /** Metoda gaussEli zeliminuje matici gausovou eliminaci. Promenna nuloveSloup
     * ridi, aby se v pripade vynulovaneho sloupce eliminovalo opravdu od toho spravneho
     * radku. V metode je pouzita metoda serazeniRadku. Promenna akce napomaha k 
     * preskoceni pouziti metody serazeniRadku, pokud se v predeslem kroku neeliminovalo.
     */ 

    static int hodnost(double[][] matice) {
        int hodnost = 0;
        matice = gaussEli(matice);
        for (int cisloRadku = 0; cisloRadku < matice.length; cisloRadku++) {
            for (int cisloSloupce = 0; cisloSloupce < matice[0].length; cisloSloupce++) {
                if (cisloRadku < matice.length && matice[cisloRadku][cisloSloupce] != 0) {
                    hodnost++;
                    cisloSloupce = 0;
                    cisloRadku++;
                }
            }
        }
        return hodnost;
    }
/** Metoda hodnost slouzi k zjisteni hodnosti matice. Jako parametr dostava matici,
 * u ktere ma hodnost vypocitat. Navratovou hodnotou je samotna hodnost. 
 */
    
    static void vypisMatici(double[][] matice) {
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[i].length; j++) {
                System.out.print(matice[i][j] + " ");
            }
            System.out.println();
        }
    }
/*Metoda vypisMatici vypise matici na obrazovku **/
    
    static double[][] scitaniMatic(double[][] scitanec1, double[][] scitanec2) {
        if (scitanec1.length == scitanec2.length && scitanec1[1].length ==
                scitanec2[1].length) {
            double[][] soucet = new double[scitanec1.length][scitanec1[0].length];
            for (int i = 0; i < scitanec1.length; i++) {
                for (int j = 0; j < scitanec1[i].length; j++) {
                    soucet[i][j] = scitanec1[i][j] + scitanec2[i][j];
                }
            }
            return soucet;
        } else {
            chyba = true;
            double[][] chybovaMatice = {{-1}};
            return chybovaMatice;
        }
    }
    /**Metoda scitani matic zkontroluje, ze matice zadane jako parametry maji
     * stejne rozmery a pokud ano, tak je secte. Pokud ne, tak navrati chybovou matici. 
     */

    static boolean testRektangularnosti(double[][] testovanaMatice) {
        int delkaRadku = testovanaMatice[0].length;
        for (int i = 1; i < testovanaMatice.length; i++) {
            if (delkaRadku != testovanaMatice[i].length) {
                System.out.println("Matice neni rektangularni");
                return false;
            }
        }
        return true;
    }
/** Metoda testRektangularnosti zkontroluje, zdali je matice rektangularni (ma stejny
 * pocet prvku v radcich a stejny pocet prku v sloupcich). V teto verzi programu neni
 * pouzita, vzhledem k tomu, ze se rozmery matic i samotne matice zadavaji z klavesnice,
 * ale pokud se prida cteni ze souboru, tak tato metoda bude pouzitelna. 
 */
    
    static double[][] nasobeniMaticeSkalarem(double[][] matice, double nasobek) {
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[i].length; j++) {
                matice[i][j] *= nasobek;
            }
        }
        return matice;
    }
/** Metoda nasobeniMatice skalarem vynasobi matici libovolnym cislem. Matici i cislo
 * dostava metoda jako parametry. 
 */
    static double[][] rozdilMatic(double[][] mensenec, double[][] mensitel) {
       if (mensenec.length == mensitel.length && mensenec[1].length ==
                mensitel[1].length) {
            double[][] rozdil = new double[mensenec.length][mensenec[0].length];
            for (int i = 0; i < mensenec.length; i++) {
                for (int j = 0; j < mensenec[i].length; j++) {
                    rozdil[i][j] = mensenec[i][j] - mensitel[i][j];
                }
            }
            return rozdil;
        } else {
            chyba = true;
            double[][] chybnaMatice = {{-1}};
            return chybnaMatice;
        }
    }
    /** Metoda rozdilMatic od sebe odecte dve matice, ktere dostane jako parametr a
     * zkontroluje, pokud maji stejne rozmery. 
     */

    static double[][] transponujMatici(double[][] matice) {
        double[][] transponovanaMatice = new double[matice[1].length][matice.length];
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[i].length; j++) {
                transponovanaMatice[j][i] = matice[i][j];
            }
        }
        return transponovanaMatice;
    }
/** Metoda transponujMatici transponuje matici. Jako Parametr metoda dostane matici.
 * Navratova hodnota je transponovana matice.
 */
    
    static double[][] nasobeniMatic(double[][] prvniSoucinitel, double[][] druhySoucinitel) {
        double[][] soucin;
        soucin = new double[prvniSoucinitel.length][druhySoucinitel[1].length];
        double skalarniSoucin=0;
        int i, j, k;
        if (prvniSoucinitel[0].length == druhySoucinitel.length) {
            int radkuPrvniMatice = prvniSoucinitel.length;
            int sloupcuPrvniMatice = prvniSoucinitel[0].length;
            int sloupcuDruheMatice = druhySoucinitel[0].length;
            for (i = 0; i < radkuPrvniMatice; i++) {
                for (k = 0; k < sloupcuDruheMatice; k++) {
                    for (j = 0; j < sloupcuPrvniMatice; j++) {
                        skalarniSoucin = skalarniSoucin + (prvniSoucinitel[i][j] *
                                druhySoucinitel[j][k]);
                    }
                    soucin[i][k] = skalarniSoucin;
                    skalarniSoucin = 0;
                }

            }
        return soucin;
        }
        else {chyba = true;
            double[][] chybovaMatice = {{-1}};
            return chybovaMatice; }
    }
/** Metoda nasobeniMatic zkontroluje, jestli jsou matice vhodnych rozmeru a pokud ano,
 * ta je vynasobi a vrati jejich nasobek. Pokud ne, tak vrati chybovou matici. Jako parametry
 * dostane metoda dve matice.
 */
    
    static double[][] generovaniMatice(int n, int m) {
        if (n > 0 && m > 0) {
            double[][] generovanaMatice = new double[n][m];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    generovanaMatice[i][j] = rand.nextInt(10);
                }
            }
            return generovanaMatice;
        } else {
            System.out.println("Zadane rozmery matice musi byt prirozena cisla !");
            double[][] chybnaMatice = new double[1][1];
            chybnaMatice[0][0] = -1;
            return chybnaMatice;
        }
    }
/** Metoda generovaniMatice vygeneruje matici a vrati ji. Jako parametry dostava 
 * rozmery matice, ktera se ma vygenerovat.
 */
    
    static double hodnotaDeterminantu(double[][] matice) {
        znamenko = true;
        int det = 1;
        if (matice.length == matice[0].length) {
            matice = gaussEli(matice);
            for (int i = 0; i < matice.length; i++) {
                det *= matice[i][i];
            }
            if (znamenko == false) {
                det *= -1;
            }
        } else {
            chyba=true;
            det = -1;
        }
        return det;
    }
    /** Metoda hodnotaDeterminantu zkontroluje, ze je matice stvercova a pokud ano,
     * tak vypocita hodnotu determinantu matice, kterou ziska jako parametr. Pouziva 
     * pri tom metodu gaussEli
     */

    static double[][] volbyVstupu() {
        System.out.println("Zvolte si, s kterou matici chcete pracovat(pomoci cisla a enteru)" +
                "\n1. Matice A \n2. Matice B \n3. Zadat matici z klavesnice a ulozit ji do A" +
                "\n4. Zadat matici z klavesnice a ulozit ji do B" +
                "\n5. Zadat matici a neukladat ji do zadne promenne" +
                "\n6. Vygenerovat matici a ulozit ji do A" +
                "\n7. Vygenerovat matici a ulozit ji do B" +
                "\n8. Vygenerovat matici urcenou jen k teto operaci");
        int volba = new Scanner(System.in).nextInt();
        int radky;
        int sloupce;
        double[][] matice = {{-1}};
        switch (volba) {
            case 1:
                matice = A;
                System.out.println("Matice A");
                break;
            case 2:
                matice = B;
                System.out.println("Matice B");
                break;
            case 3:
                A = zadaniMatice();
                matice = A;
                break;
            case 4:
                B = zadaniMatice();
                matice = B;
                break;

            case 5:
                matice = zadaniMatice();
                break;
            case 6:
                System.out.println("Zadejte pocet radku generovane matice:");
                radky = new Scanner(System.in).nextInt();
                System.out.println("Zadejte pocet sloupcu generovane matice:");
                sloupce = new Scanner(System.in).nextInt();
                A = generovaniMatice(radky, sloupce);
                matice = A;
                break;
            case 7:
                System.out.println("Zadejte pocet radku generovane matice:");
                radky = new Scanner(System.in).nextInt();
                System.out.println("Zadejte pocet sloupcu generovane matice:");
                sloupce = new Scanner(System.in).nextInt();
                B = generovaniMatice(radky, sloupce);
                matice = B;
                break;
            case 8:
                System.out.println("Zadejte pocet radku generovane matice:");
                radky = new Scanner(System.in).nextInt();
                System.out.println("Zadejte pocet sloupcu generovane matice:");
                sloupce = new Scanner(System.in).nextInt();
                matice = generovaniMatice(radky, sloupce);
                break;
            default:
                System.out.println("Musite zadat cislo v rozmezi 1-8");
                matice = volbyVstupu();
                break;
        }
        return matice;
    }
/** Metoda volbyVstupu necha uzivatele vybrat, s jakou matici chce pracovat. S 
 * matici A, B, zadanou matici ci generovanou matici a zda ji chce ulozit do promene
 * A ci B. Metoda pouziva metod generovaniMatice, zadaniMatice. V pripade zadani 
 * cisla nevhodne hodnoty se metoda rekurzivne zavola. 
 */
    
    static void ovladani() {
        int ovladac = 0;
        chyba=false;
        while (true) {
            System.out.println("Program slouzici k pocitani s maticemi, zadejte akci," +
                    " ktera se ma provest pomoci cisla a enteru:");
            System.out.println("1. Scitani matic");
            System.out.println("2. Odcitani matic");
            System.out.println("3. Nasobeni matice matici");
            System.out.println("4. Nasobeni matice skalarem");
            System.out.println("5. Transponovani matice");
            System.out.println("6. Vypocitani hodnosti matice");
            System.out.println("7. Zeliminuj matici gaussovou eliminaci");
            System.out.println("8. Konec");
            System.out.println("9. Vypocitani hodnoty determinantu matice");
            System.out.println("10. O programu");
            System.out.println("11. Zadani matice A");
            System.out.println("12. Zadani matice B");
            System.out.println("13. Vypise matici A");
            System.out.println("14. Vypise matici B");
            ovladac = new Scanner(System.in).nextInt();
            System.out.println("");
            switch (ovladac) {
                case 1:
                     {
                        System.out.println("Zvolte prvni matici, ktera se ma scitat:");
                        double[][] soucet1 = volbyVstupu();
                        System.out.println("Zvolte druhou matici, ktera se ma scitat:");
                        double[][] soucet2 = volbyVstupu();
                        System.out.println("Prvni matice je: ");
                        vypisMatici(soucet1);
                        System.out.println("Druha matice je: ");
                        vypisMatici(soucet2);
                        double[][]vysledek=scitaniMatic(soucet1, soucet2);
                        if (chyba == false) {
                        System.out.println("Soucet obou matic je: ");
                            vypisMatici(vysledek);
                        } else {
                            System.out.println("Chybne zadani - matice nejsou stejneho typu");
                        }

                    }
                    break;
                case 2:
                    System.out.println("Zadejte mensenec");
                    double[][] rozdil1 = volbyVstupu();
                    System.out.println("Zadejte mensitele:");
                    double[][] rozdil2 = volbyVstupu();
                    System.out.println("Prvni matice:");
                    vypisMatici(rozdil1);
                    System.out.println("Druha matice:");
                    vypisMatici(rozdil2);
                    double [][] vysledek2=rozdilMatic(rozdil1, rozdil2);
                    if (chyba == false) {
                    System.out.println("Rozdil prvni matice - druha je: ");
                    vypisMatici(vysledek2);
                    } else {
                        System.out.println("Chybne zadani - matice nejsou stejneho typu");
                    }
                    break;
                case 3:
                    System.out.println("Zadejte prvniho soucinitele:");
                    double[][] soucin1 = volbyVstupu();
                    System.out.println("Zadejte druheho soucinitele:");
                    double[][] soucin2 = volbyVstupu();
                    System.out.println("Prvni soucinitel: ");
                    vypisMatici(soucin1);
                    System.out.println("Druhy soucinitel: ");
                    vypisMatici(soucin2);
                    System.out.println("Soucin matic je: ");
                    double[][]vysledek3=nasobeniMatic(soucin1, soucin2);
                    if (chyba==false) vypisMatici(vysledek3); else
                        System.out.println("Chybne zadani. Pocet sloupcu prvni matice neni" +
                                "roven poctu radku matice druhe");break;
                case 4:
                    System.out.println("Zadejte matici, jez ma byt vynasobena");
                    double[][] nasobena = volbyVstupu();
                    System.out.println("Matice, kterou chcete vynasobit ");
                    vypisMatici(nasobena);
                    System.out.println("Zadejte skalar, kterym chcete vynasobit matici");
                    int nasobek = new Scanner(System.in).nextInt();
                    System.out.println("Vynasobena matice cislem " + nasobek + "je");
                    vypisMatici(nasobeniMaticeSkalarem(nasobena, nasobek));
                    break;
                case 5:
                    double[][] matice = volbyVstupu();
                    System.out.println("Matice, ktera se ma transponovat: ");
                    vypisMatici(matice);
                    System.out.println("Transponovana matic k teto matici je: ");
                    vypisMatici(transponujMatici(matice));
                    break;
                case 6:
                    double[][] hodnaMatice = volbyVstupu();
                    System.out.println("Zadana matice:");
                    vypisMatici(hodnaMatice);
                    System.out.println("Zelimonovana zadana matice: ");
                    vypisMatici(gaussEli(hodnaMatice));
                    System.out.println("Hodnost matice je:" + hodnost(hodnaMatice));
                    break;
                case 7:
                    double[][] matice2 = volbyVstupu();
                    vypisMatici(matice2);
                    System.out.println("Matice vypada po gaussove eliminaci takto: ");
                    vypisMatici(gaussEli(matice2));
                    break;
                case 9:
                    double[][] detMatice = volbyVstupu();
                    vypisMatici(detMatice);
                    System.out.println("Hodnota determinantu matice je:" +
                            hodnotaDeterminantu(detMatice));
                    break;
                case 10:
                    System.out.println("Tento program byl napsan Martinem Lukesem" +
                            " jako zapoctovy program predmetu Algoritmizace na" +
                            "konci roku 2008 a pocatku roku 2009.");
                    break;
                case 11:
                    System.out.println("Zadani hodnot matice A");
                    A = zadaniMatice();
                    vypisMatici(A);
                    break;
                case 12:
                    System.out.println("Zadani hodnot matice B");
                    B = zadaniMatice();
                    break;
                case 13:
                    System.out.println("Matice A:");
                    vypisMatici(A);
                    break;
                case 14:
                    System.out.println("Matice B:");
                    vypisMatici(B);
                    break;
            }
            if (ovladac==8) break;
        }
    }
/**Metoda ovladani slouzi k zkompletovani vsech predeslych metod a k komunikaci 
 * s uzivatelem.
 */
    
    public static void main(String[] args) {
        ovladani();

    }
}


