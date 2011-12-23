/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matice;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author já
 */
public class Main {

    /*** Pouzite globalni promenne:
     *   boolean znamenko - nutne pro vypocitani hodnosti determinantu
     *   pole A, B - ukladani si matic k dalsimu vyuziti
     *   boolean chyba - promenna slouzici k rozliseni, zdali maji zadane
     *                   matice spravne parametry
     * konstanta rozmer - urcuje hodnotu nejvetsiho poctu sloupcu a/nebo radku
     * konstanta nasobek - urcuje hodnotu nejvetsiho nasobku

     */
    static boolean znamenko;
    static double[][] A = {{1, 2, 3}, {1, 2, 3}};
    static double[][] B = {{1, 3, 0}, {4, 2, 0}, {1, 2, 3}};
    static boolean chyba = false;
    static final int MAX_ROZMER = 10;
    static final int MAX_NASOBEK = 10;

    /** Metoda vstupUzivatele slouzi k kontrole zadani vstupu z klavesnice. Jako parametr
     *  dostava horniMez, kterou uzivatel nesmi preskocit, dale text zadani a text, ktery se
     *  zobrazi, pokud uzivatel zada nevhodny vstup.
     */
    static int vstupUzivatele(int horniMez, String zadani, String hlaska) {
        int volba = 0;
        while (volba == 0) {
            System.out.println(zadani);
            try {
                volba = new Scanner(System.in).nextInt();
                if (volba < 0 || volba > horniMez) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException vstup) {
                System.out.println(hlaska);
                volba = 0;
            }
        }
        return volba;
    }

    /** Metoda najdiNenulovy najde prvni nenulovy radek, ktery nasleduje za
     * zadanym radkem. Vstupem pro metodu je matice, v ktere ma nenulovy radek
     * hledat, dale index radku, od ktereho ma hledat a index sloupce, ve kterem
     * ma hledat (pro pripad, ze jsou nektere sloupce nulove neni index sloupce
     * roven indexu radku). Navratovou hodnotou je index prvniho nenuloveho radku.
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

    /** Metoda prohodRadky prohodi dva radky v matici a po prohozeni meni hodnotu
     * znamenka. Vstupni promenne jsou jednak matice, dale pak index prvniho radku
     * a index druheho radku. Navratovou hodnotou je matice s prohozenymi radky
     */
    static double[][] prohodRadky(double[][] matice, int iRadku, int iRadku2) {
        if (znamenko == true) {
            znamenko = false;
        } else {
            znamenko = true;
        }
        for (int i = 0; i < matice[0].length; i++) {
            double odklad = matice[iRadku][i];
            matice[iRadku][i] = matice[iRadku2][i];
            matice[iRadku2][i] = odklad;
        }
        return matice;
    }

    /**Metoda zadaniMatice slouzi k tomu, aby uzivatel mohl zadat libovolnou matici
     * z klavesnice. Uzivateli neni dovoleno, aby rozmery matice nulove ci zaporne.
     * Navratovou hodnotou je zadana matice.
     */
    static double[][] zadaniMatice() {
        String zadaniRadky = "Zadejte pocet radku";
        String hlaska = "Musite zadat cislo 1-" + MAX_ROZMER;
        int pocetRadku = vstupUzivatele(MAX_ROZMER, zadaniRadky, hlaska);
        String zadaniSloup = "Zadejte pocet sloupcu";
        int pocetSloupcu = vstupUzivatele(MAX_ROZMER, zadaniSloup, hlaska);
        double[][] matice = new double[pocetRadku][pocetSloupcu];
        for (int i = 0; i < pocetRadku; i++) {
            System.out.println(i + 1 + ". radek: ");
            for (int j = 0; j < pocetSloupcu; j++) {
                while (true) {
                    try {
                        matice[i][j] = new Scanner(System.in).nextDouble();
                        break;
                    } catch (InputMismatchException vstup) {
                        System.out.println("Musite zadat cislo");
                    }
                }
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

    /** Metoda odecteniRadku dostava jako parametry matici, index radku, od ktereho ma odcitat ,
     * index radku, ktery ma odcitat, nasobek radku, ktery se ma odecist a nakonec nasobek radku,
     * od ktereho se ma odecitat. Vraci
     * upravenou matici.
     *
     */
    static double[][] odecteniNasobkuRadku(double[][] matice, int iRadku,
            int iOdecitanehoRadku, double nasobek1, double nasobek2) {
        double[][] upravenaMatice = matice;

        for (int iSloup = 0; iSloup < matice[0].length; iSloup++) {
            upravenaMatice[iRadku][iSloup] =nasobek2*upravenaMatice[iRadku][iSloup]- matice[iOdecitanehoRadku][iSloup] * nasobek1;
        }
        return upravenaMatice;
    }

    /** Metoda gaussEli zeliminuje matici gausovou eliminaci. Promenna nuloveSloup
     * ridi, aby se v pripade vynulovaneho sloupce eliminovalo opravdu od toho spravneho
     * radku. V metode je pouzita metoda serazeniRadku. Promenna akce napomaha k
     * preskoceni pouziti metody serazeniRadku, pokud se v predeslem kroku neeliminovalo.
     */
    static double[][] gaussEli(double[][] matice) {
        int nulSloup = 0;
        boolean akce = true;
        double[][] gaussMatice = kopiePole(matice);
        for (int iRadku = 0; iRadku - nulSloup < gaussMatice.length; iRadku++) {
            if (iRadku < gaussMatice[0].length) {
                if (akce == true && gaussMatice[iRadku - nulSloup][iRadku] == 0) {
                    gaussMatice = serazeniRadku(gaussMatice);
                    akce = false;
                }
                if (gaussMatice[iRadku - nulSloup][iRadku] == 0) {
                    nulSloup++;
                } else {
                    for (int iNaslRadku = iRadku - nulSloup + 1;
                            iNaslRadku < gaussMatice.length; iNaslRadku++) {
                        if (iRadku < gaussMatice[0].length) {
                            double nasobek = gaussMatice[iNaslRadku][iRadku];
                            gaussMatice = odecteniNasobkuRadku(gaussMatice, iNaslRadku, iRadku - nulSloup,
                                    nasobek, gaussMatice[iRadku - nulSloup][iRadku]);
                            akce = true;
                        } else {
                            iNaslRadku = gaussMatice.length;
                        }
                    }
                }
            }
        }
        return gaussMatice;
    }

    /** Metoda kopiePole zkopiruje pole a nevytvori jen referenci. Byla napsana, aby
     * se ukladali spravne matice A a B.
     */
    static double[][] kopiePole(double[][] matice) {
        double[][] kopie = new double[matice.length][matice[0].length];
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[0].length; j++) {
                kopie[i][j] = matice[i][j];
            }
        }
        return kopie;
    }

    /** Metoda ulozeni slouzi k ulozeni vysledku predesleho pocitani do mista matice A ci
     * B ci nikam.
     *
     */
    static void ulozeni(double[][] matice) {
        System.out.println();
        int pocetVoleb = 3;
        int volba = vstupUzivatele(pocetVoleb, "Chcete vysledek ulozit? Zadani provedte pomoci cisla + enteru" +
                "\n1.Ano, chci vysledek vlozit na misto matice A\n2.Ano, chci " +
                "vysledek vlozit na misto matice B\n3. Nechci s vysledkem jiz pracovat",
                "Musite zadat cislo v rozmezi 0-" + pocetVoleb);
        switch (volba) {
            case 1:
                A = matice;
                break;
            case 2:
                B = matice;
                break;
        }
    }

    /** Metoda hodnost slouzi k zjisteni hodnosti matice. Jako parametr dostava matici,
     * u ktere ma hodnost vypocitat. Navratovou hodnotou je samotna hodnost.
     */
    static int hodnost(double[][] matice) {
        znamenko = true;
        int hodnost = 0;
        double[][] matice2 = gaussEli(matice);

        for (int cisloRadku = 0; cisloRadku < matice2.length; cisloRadku++) {
            for (int cisloSloupce = 0; cisloSloupce < matice2[0].length; cisloSloupce++) {
                if (cisloRadku < matice2.length && matice2[cisloRadku][cisloSloupce] != 0) {
                    hodnost++;
                    cisloSloupce = 0;
                    cisloRadku++;
                }
            }
        }
        return hodnost;
    }
    /*Metoda vypisMatici vypise matici na obrazovku **/

    static void vypisMatici(double[][] matice) {
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[i].length; j++) {
                System.out.print(matice[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**Metoda scitani matic zkontroluje, ze matice zadane jako parametry maji
     * stejne rozmery a pokud ano, tak je secte. Pokud ne, tak navrati chybovou matici.
     */
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
            double[][] chybnaMatice = {{-1}};
            return chybnaMatice;
        }
    }

    /** Metoda testRektangularnosti zkontroluje, zdali je matice rektangularni (ma stejny
     * pocet prvku v radcich a stejny pocet prku v sloupcich). V teto verzi programu neni
     * pouzita, vzhledem k tomu, ze se rozmery matic i samotne matice zadavaji z klavesnice,
     * ale pokud se prida cteni ze souboru, tak tato metoda bude pouzitelna.
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

    /** Metoda nasobeniMatice skalarem vynasobi matici libovolnym cislem. Matici i cislo
     * dostava metoda jako parametry.
     */
    static double[][] nasobeniMaticeSkalarem(double[][] matice, double nasobek) {
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[i].length; j++) {
                matice[i][j] *= nasobek;
            }
        }
        return matice;
    }

    /** Metoda rozdilMatic od sebe odecte dve matice, ktere dostane jako parametr a
     * zkontroluje, pokud maji stejne rozmery.
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
            double[][] chybovaMatice = {{-1}};
            return chybovaMatice;
        }
    }

    /** Metoda transponujMatici transponuje matici. Jako Parametr metoda dostane matici.
     * Navratova hodnota je transponovana matice.
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

    /** Metoda nasobeniMatic zkontroluje, jestli jsou matice vhodnych rozmeru a pokud ano,
     * ta je vynasobi a vrati jejich nasobek. Pokud ne, tak vrati chybovou matici. Jako parametry
     * dostane metoda dve matice.
     */
    static double[][] nasobeniMatic(double[][] prvniSoucinitel, double[][] druhySoucinitel) {
        double[][] soucin;
        soucin = new double[prvniSoucinitel.length][druhySoucinitel[1].length];
        double skalarniSoucin = 0;
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
        } else {
            chyba = true;
            double[][] chybnaMatice = {{-1}};
            return chybnaMatice;
        }
    }

    /** Metoda generovaniMatice vygeneruje matici a vrati ji. Jako parametry dostava
     * rozmery matice, ktera se ma vygenerovat.
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

    /** Metoda hodnotaDeterminantu zkontroluje, ze je matice stvercova a pokud ano,
     * tak vypocita hodnotu determinantu matice, kterou ziska jako parametr. Pouziva
     * pri tom metodu gaussEli
     */
    static double hodnotaDeterminantu(double[][] matice) {
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
            System.out.println("Matice neni ctvercova - determinant nelze vypocitat");
            det = -1;
        }
        return det;
    }

    /** Metoda volbyVstupu necha uzivatele vybrat, s jakou matici chce pracovat. S
     * matici A, B, zadanou matici ci generovanou matici a zda ji chce ulozit do promene
     * A ci B. Metoda pouziva metod generovaniMatice, zadaniMatice. V pripade zadani
     * cisla nevhodne hodnoty se metoda rekurzivne zavola.
     */
    static double[][] volbyVstupu() {
        int pocetNabidek = 8;
        int volba = vstupUzivatele(pocetNabidek, "Zvolte si, s kterou matici chcete pracovat(" +
                "pomoci cisla a enteru\n1. Matice A \n2. Matice B \n3. Zadat matici z " +
                "klavesnice a ulozit ji do A\n4. Zadat matici z klavesnice a ulozit ji do" +
                " B\n5. Zadat matici a neukladat ji do zadne promenne\n6. Vygenerovat matici" +
                " a ulozit ji do A\n7. Vygenerovat matici a ulozit ji do B\n8. Vygenerovat" +
                " matici urcenou jen k teto operaci", "Musite zadat a cislo v rozmezi 1-" +
                MAX_ROZMER + " potvrdit enterem");
        int radky = 0;
        int sloupce = 0;
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
                radky = vstupUzivatele(MAX_ROZMER, "Zadejte pocet radku generovane" +
                        " matice:", "Musite zadat cislo 1-" + MAX_ROZMER);
                sloupce = vstupUzivatele(MAX_ROZMER, "Zadejte pocet sloupcu generovane" +
                        " matice:", "Musite zadat cislo 1-" + MAX_ROZMER);
                A = generovaniMatice(radky, sloupce);
                matice = A;
                break;
            case 7:
                radky = vstupUzivatele(MAX_ROZMER, "Zadejte pocet radku generovane" +
                        " matice:", "Musite zadat cislo 1-" + MAX_ROZMER);
                sloupce = vstupUzivatele(MAX_ROZMER, "Zadejte pocet sloupcu generovane" +
                        " matice:", "Musite zadat cislo 1-" + MAX_ROZMER);
                B = generovaniMatice(radky, sloupce);
                matice = B;
                break;
            case 8:
                radky = vstupUzivatele(MAX_ROZMER, "Zadejte pocet radku generovane" +
                        " matice:", "Musite zadat cislo 1-" + MAX_ROZMER);
                sloupce = vstupUzivatele(MAX_ROZMER, "Zadejte pocet sloupcu generovane" +
                        " matice:", "Musite zadat cislo 1-" + MAX_ROZMER);
                matice = generovaniMatice(radky, sloupce);
                break;
            default:
                System.out.println("Musite zadat cislo v rozmezi 1-8");
                matice = volbyVstupu();
                break;
        }
        System.out.println("");
        return matice;
    }

    /**Metoda ovladani slouzi k zkompletovani vsech predeslych metod a k komunikaci
     * s uzivatelem.
     */
    static void ovladani() {
        int ovladac;
        chyba = false;
        while (true) {
            ovladac = vstupUzivatele(14, "Program slouzici k pocitani s maticemi, zadejte akci," +
                    " ktera se ma provest pomoci cisla a enteru\n1. Scitani matic\n2. Odcitani matic" +
                    "\n3. Nasobeni matice matici\n4. Nasobeni matice skalarem\n5. Transponovani " +
                    "matice\n6. Vypocitani hodnosti matice\n7. Zeliminuj matici gaussovou " +
                    "eliminaci\n8. Konec\n9. Vypocitani hodnoty determinantu matice\n10. O" +
                    " programu\n11. Zadani matice A\n12. Zadani matice B\n13. Vypise matici A" +
                    "\n14. Vypise matici B", "Musite zadat cislo v rozmezi 0-14 a potvrdit enterem\n");
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
                        double[][] vysledek = scitaniMatic(soucet1, soucet2);
                        if (chyba == false) {
                            System.out.println("Soucet obou matic je: ");
                            vypisMatici(vysledek);
                            ulozeni(vysledek);
                        } else {
                            System.out.println("Chybne zadani - matice nejsou stejneho typu");
                        }
                    }
                    System.out.println("");
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
                    double[][] vysledek2 = rozdilMatic(rozdil1, rozdil2);
                    if (chyba == false) {
                        System.out.println("Rozdil prvni matice - druha je: ");
                        vypisMatici(vysledek2);
                        ulozeni(vysledek2);
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
                    double[][] vysledek3 = nasobeniMatic(soucin1, soucin2);
                    if (chyba == false) {
                        System.out.println("Soucin matic je: ");
                        vypisMatici(vysledek3);
                        ulozeni(vysledek3);
                    } else {
                        System.out.println("Chybne zadani. Pocet sloupcu prvni matice neni" +
                                "roven poctu radku matice druhe");
                    }
                    break;
                case 4:
                    System.out.println("Zadejte matici, jez ma byt vynasobena");
                    double[][] nasobena = volbyVstupu();
                    System.out.println("Matice, kterou chcete vynasobit ");
                    vypisMatici(nasobena);
                    int nasobek = vstupUzivatele(MAX_NASOBEK, "Zadejte skalar, kterym chcete vynasobit matici",
                            "Musite zadat cislo v rozmezi 0-" + MAX_NASOBEK);
                    System.out.println("Vynasobena matice cislem " + nasobek + "je");
                    double[][] vysledek4 = nasobeniMaticeSkalarem(nasobena, nasobek);
                    vypisMatici(vysledek4);
                    ulozeni(vysledek4);
                    break;
                case 5:
                    double[][] matice = volbyVstupu();
                    System.out.println("Matice, ktera se ma transponovat: ");
                    vypisMatici(matice);
                    System.out.println("Transponovana matic k teto matici je: ");
                    double[][] vysledek5 = transponujMatici(matice);
                    vypisMatici(vysledek5);
                    ulozeni(vysledek5);
                    break;
                case 6:
                    double[][] hodnaMatice = volbyVstupu();
                    System.out.println("Zadana matice:");
                    vypisMatici(hodnaMatice);
                    System.out.println("Zadana matice po eliminaci: ");
                    vypisMatici(gaussEli(hodnaMatice));
                    System.out.println("Hodnost matice je:" + hodnost(hodnaMatice));
                    break;
                case 7:
                    double[][] matice2 = volbyVstupu();
                    vypisMatici(matice2);
                    System.out.println("Matice vypada po gaussove eliminaci takto: ");
                    double[][] vysledek6 = gaussEli(matice2);
                    vypisMatici(vysledek6);
                    ulozeni(vysledek6);
                    break;
                case 9:
                    double[][] detMatice = volbyVstupu();
                    vypisMatici(detMatice);
                    if (chyba == false) {
                        System.out.println("Hodnota determinantu matice je:" +
                                hodnotaDeterminantu(detMatice));
                    } else {
                        System.out.println("Matice neni ctvrecova a tak nelze" +
                                " vypocitat jeji determinant");
                    }
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
            if (ovladac == 8) {
                break;
            }
            System.out.println("");
        }

    }

    public static void main(String[] args) {
        ovladani();

    }
}

