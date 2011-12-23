package caesarovasifra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    /**
     * Metoda Menu slouží jako hlavní rozcestník pro celý program. Dotáže se uživatele, co přesně chce s daty provést a podle toho jej přesměruje dále do programu.
     * @param vyber je promenna, kterou definujeme, co si uživatel v Menu zvolil jako svou volbu a po té jej pomocí fce switch přesměruje.
     * @exception InputMismatchException výjimka, která ošetřuje zadání jiné než číselné hodnoty v Menu.
     */

    public static void MENU() {
        try {
            int vyber;
            System.out.println("-------------------------------------- VÍTEJTE V MENU -------------------------------------");
            System.out.println("------------------------------ Program CAESAROVA SIFRA Vas vita! --------------------------");
            System.out.println("V tomto programu si muzete vyzkouset sifrovat text, tak jak jej sifroval sam Julius Caesar!");
            System.out.println("--------------------- Jana Michnova Vam preje prijemne uzivani programu. ------------------");
            System.out.println("----------------------- Vyberte si nějakou z následujících možností! ----------------------");
            System.out.println("1 - Zasifruj");
            System.out.println("2 - Rozsifruj");
            System.out.println("3 - Konec programu");
            System.out.println("");
            vyber = new Scanner(System.in).nextInt();
            
            if (vyber < 1 || vyber > 4) {
                System.out.println("Spatna volba - zvolte jinou moznost: ");
                System.out.println("");
                MENU();
            }
            switch (vyber) {
                case 1:
                    zasifruj();
                    break;
                case 2:
                    rozsifruj();
                    break;
                case 3:
                    System.out.println("Program bude nyní ukončen!");
                    System.exit(0);
                    break;
            }
          
        } catch (InputMismatchException e) {
            System.out.println("Vase volba musi byt v ciselne podobe");
            MENU();
        }
    }
/**
    * Metoda zašifruj je napsána jako takový detailnější rozcestník, když se uživatel rozhodl šifrovat. Zeptá se nás odkud chceme text zadávat a poté nás pošle dále do programu po cestě šifrace.
    * @param vyber je promenna, kterou definujeme, co si uživatel v Menu zvolil jako svou volbu a po té jej pomocí fce switch přesměruje.
    * @exception InputMismatchException - výjimka, která ošetřuje zadání jiné než číselné hodnoty v Menu.
  */
    public static void zasifruj() {
        try {
            int vyber;
            System.out.println("-------------- MENU ZASIFRUJ ---------------");
            System.out.println("Vyberte si nějakou z následujících možností!");
            System.out.println("1 - Nacti text ze Souboru");
            System.out.println("2 - Nacti text z klavesnice");
            System.out.println("3 - Zpet na hlavni menu");
            System.out.println("4 - Zcela ukoncit program");
            vyber = new Scanner(System.in).nextInt();

            if (vyber < 1 || vyber > 4) {
                System.out.println("Spatna volba - zvolte jinou moznost: ");
                zasifruj();
            }

            switch (vyber) {
                case 1:
                    ZasifrujnactiZeSouboru();
                    break;
                case 2:
                    ZasifrujNactiZKlavesnice();
                    break;
                case 3:
                    System.out.println("Navrat do hlavniho menu!");
                    System.out.println("");
                    MENU();
                    break;
                case 4:
                    System.out.println("Program bude nyní ukončen!");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Vase volba musi byt v ciselne podobe");
            zasifruj();
        }
    }
/**
 * Tato metoda je posledním rozcestníkem, který nás na cestě za šifrací textu potká. Nyní se nás dotazuje na lokaci, ve které chceme mít výsledek zobrazen nebo uložen.
 * @param vyber je promenna, kterou definujeme, co si uživatel v Menu zvolil jako svou volbu a po té jej pomocí fce switch přesměruje.
 * @exception InputMismatchException výjimka, která ošetřuje zadání jiné než číselné hodnoty v Menu.
 */
    public static void ZasifrujNactiZKlavesnice() {
        try {
            int vyber;
            System.out.println("Vyberte si nějakou z následujících možností!");
            System.out.println("1 - Nacti text a uloz do Souboru");
            System.out.println("2 - Nacti text a vypis na obrazovku");
            System.out.println("3 - Zpet na menu zasifruj");
            System.out.println("4 - Zpet na hlavni menu");
            System.out.println("5 - Zcela ukoncit program");
            vyber = new Scanner(System.in).nextInt();

            if (vyber < 1 || vyber > 4) {
                System.out.println("Spatna volba - zvolte jinou moznost: ");
                ZasifrujNactiZKlavesnice();
            }

            switch (vyber) {
                case 1:
                    ZasifrujNactiZKlavAUlozDoSouboru();
                    break;
                case 2:
                    ZasifrujNactiZKlavAVypisNaObrazovku();
                    break;
                case 3:
                    System.out.println("Navrat do menu zasfruj!");
                    System.out.println("");
                    zasifruj();
                    break;
                case 4:
                    System.out.println("Navrat do hlavniho menu!");
                    System.out.println("");
                    MENU();
                    break;
                case 5:
                    System.out.println("Program bude nyní ukončen!");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Vase volba musi byt v ciselne podobe");
            ZasifrujNactiZKlavesnice();
        }
    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát šifrovat a poté se uživatele optá na text k šifrování. Když pomocí cyklu provede šifraci textu, tak jej vypíše na obrazovku.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější zašifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých zašifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 */
    public static void ZasifrujNactiZKlavAVypisNaObrazovku() {
        System.out.println("Zadejte velikost kroku sifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadej text, ktery chces zakodovat: ");
        String text = new Scanner(System.in).next();
        int pomoc = text.length();
        int ascii;
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        text.getChars(0, pomoc, znaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) znaky[i] + sifra;
            sifrovaneZnaky[i] = (char) ascii;
        }
        System.out.println("Vami zadany text po zasifrovani: ");
        System.out.println(sifrovaneZnaky);
        ZasifrujNactiZKlavesnice();
    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát šifrovat a poté se uživatele optá na text k šifrování. Když pomocí cyklu provede šifraci textu, tak jej uloží do souboru.
 * To provede tak, že se uživatele dotáže pro zadání názvu souboru a ověří, jestli již existuje nebo ne.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější zašifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých zašifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 * @param cesta slouží na určení názvu souboru, do kterého bude zašifrovaný text uložen.
 * @param odpoved proměnná sloužící jako potvrzení o akci se souborem, který byl označen jako existující.
 * @exception IOException výjimka, kdy dojde k chybě na vstupu či výstupu v souboru.
 * @exception FileNotFoundException výjimka, kdy není nalezen zadaný název souboru.
 */
    public static void ZasifrujNactiZKlavAUlozDoSouboru() {
        System.out.println("Zadejte velikost kroku sifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadej text, ktery chces zakodovat: ");
        String text = new Scanner(System.in).next();
        int pomoc = text.length();
        int ascii;
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        text.getChars(0, pomoc, znaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) znaky[i] + sifra;
            sifrovaneZnaky[i] = (char) ascii;
        }
        System.out.println("Zadejte nazev souboru: ");
        File cesta = new File(new Scanner(System.in).next()+".txt");

        if (cesta.exists() == true) {
            System.out.println("Soubor jiz existuje!");
            System.out.println("Prejete si sifrovany text do tohoto souboru pripsat? ANO/NE");
            String vyber = new Scanner(System.in).next();
            String odpoved = vyber.toLowerCase();
            if (odpoved.equals("ano")) {
                try {
                    FileWriter fw = new FileWriter(cesta, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(sifrovaneZnaky);
                    bw.newLine();
                    bw.close();
                    fw.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Soubor nebyl nalezen!");
                } catch (IOException e1) {
                    System.out.println("Chyba pri zapisu!!!");
                }
                System.out.println("Sifrovany text byl ulozen do souboru " + cesta);
            } else {
                System.out.println("Zadejte novy nazev souboru:");
                cesta = new File(new Scanner(System.in).next()+".txt");
                if (cesta.exists() == true) {
                    System.out.println("Soubor opet existuje! Akce byla ukoncena!");
                    ZasifrujNactiZKlavesnice();
                } else {
                    try {
                        FileWriter fw = new FileWriter(cesta, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(sifrovaneZnaky);
                        bw.newLine();
                        bw.close();
                        fw.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Soubor nebyl nalezen!");
                    } catch (IOException e1) {
                        System.out.println("Chyba pri zapisu!!!");
                    }
                    System.out.println("Sifrovany text byl ulozen do souboru " + cesta);
                }
            }
        } else {
            try {
                FileWriter fw = new FileWriter(cesta, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(sifrovaneZnaky);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (FileNotFoundException e) {
                System.out.println("Soubor nebyl nalezen!");
            } catch (IOException e1) {
                System.out.println("Chyba pri zapisu!!!");
            }
            System.out.println("Sifrovany text byl ulozen do souboru " + cesta);
        }
        ZasifrujNactiZKlavesnice();
    }
/**
 * Tato metoda je posledním rozcestníkem, který nás na cestě za šifrací textu potká. Nyní se nás dotazuje na lokaci, ve které je uložen text, který později zašifruje.
 * @param vyber je promenna, kterou definujeme, co si uživatel v Menu zvolil jako svou volbu a po té jej pomocí fce switch přesměruje.
 * @exception InputMismatchException - výjimka, která ošetřuje zadání jiné než číselné hodnoty v Menu.
 */
    public static void ZasifrujnactiZeSouboru() {
        try {
            int vyber;

            System.out.println("Vyberte si nějakou z následujících možností!");
            System.out.println("1 - Zasifruj text ze souboru na obrazovku");
            System.out.println("2 - Zasifruj text ze souboru do souboru");
            System.out.println("3 - Zpet na menu zasifruj");
            System.out.println("4 - Zpet na hlavni menu ");
            System.out.println("5 - Zcela ukoncit program");
            vyber = new Scanner(System.in).nextInt();

            if (vyber < 1 || vyber > 5) {
                System.out.println("Spatna volba - zvolte jinou moznost: ");
                System.out.println("");
                ZasifrujnactiZeSouboru();
            }

            switch (vyber) {
                case 1:
                    ZasifrujNaObrazovkuZeSouboru();
                    break;
                case 2:
                    ZasifrujDoSouboruZeSouboru();
                    break;
                case 3:
                    System.out.println("Navrat do menu rozsifruj!");
                    System.out.println("");
                    zasifruj();
                case 4:
                    System.out.println("Navrat do hlavniho menu!");
                    System.out.println("");
                    MENU();
                    break;
                case 5:
                    System.out.println("Program bude nyní ukončen!");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Vase volba musi byt v ciselne podobe");
            ZasifrujnactiZeSouboru();
        }
    }
/**
 * Metoda sloužicí pouze pro načtení textu ze souboru, který je přímo určen v nějaké z metod, které metodu NactiTextZeSouboru volají během své práce.
 * @param soubor cesta k souboru - daná metodou, která si zavolá tuto metodu během své práce.
 * @exception IOException výjimka, kdy dojde k chybě na vstupu či výstupu v souboru.
 * @exception FileNotFoundException výjimka, kdy není nalezen zadaný název souboru.
 */
    public static String NactiTextZeSouboru(String soubor) {
        try {
            FileReader fr = new FileReader(soubor);
            BufferedReader br = new BufferedReader(fr);
            String textZeSouboru = "";
            while (br.ready()) {
                textZeSouboru = textZeSouboru + br.readLine() + "\n";
            }
            return textZeSouboru;
        } catch (FileNotFoundException e) {
            return "Soubor nebyl nalezen!";

        } catch (IOException e1) {
            return "Soubor nebyl nalezen!";
        }

    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát šifrovat a poté se uživatele optá na název souboru, ze kterého se bude načítat text k šifrování. Když pomocí cyklu provede šifraci textu, tak jej vypíše na obrazovku.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější zašifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých zašifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 * @param cesta slouží na určení názvu souboru, do kterého bude zašifrovaný text uložen.
 * @param casti pole určené pro uložení jednotlivých řádků text souboru, ze kterého je text načítán.
 * @param textZeSouboru2 proměnná sloužící k uložení získaného textu ze souboru.
 */
    public static void ZasifrujNaObrazovkuZeSouboru() {
        System.out.println("Zadejte velikost kroku sifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadejte nazev souboru, ze ktereho se bude cist text k sifrovani: ");
        String cesta = new Scanner(System.in).next();
        String textZeSouboru = NactiTextZeSouboru(cesta);
        String[] casti = textZeSouboru.split("\n");
        String textZeSouboru2 = "";
        for (int i = 0; i < casti.length; i++) {
            textZeSouboru2 = textZeSouboru2 + casti[i];
        }
        int pomoc = textZeSouboru2.length();
        int ascii;
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        textZeSouboru2.getChars(0, pomoc, znaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) znaky[i] + sifra;
            sifrovaneZnaky[i] = (char) ascii;
        }
        System.out.println("Vami text po zasifrovani: ");
        System.out.println(sifrovaneZnaky);
        ZasifrujnactiZeSouboru();
    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát šifrovat a poté se uživatele optá na název souboru, ze kterého se bude načítat text k šifrování. Když pomocí cyklu provede šifraci textu, tak jej uloží do souboru.
 * To provede tak, že se uživatele dotáže pro zadání názvu souboru a ověří, jestli již existuje nebo ne.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější zašifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých zašifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 * @param cesta slouží na určení názvu souboru, do kterého bude zašifrovaný text uložen.
 * @param odpoved proměnná sloužící jako potvrzení o akci se souborem, který byl označen jako existující.
 * @param casti pole určené pro uložení jednotlivých řádků text souboru, ze kterého je text načítán.
 * @param textZeSouboru2 proměnná sloužící k uložení získaného textu ze souboru.
 * @exception IOException výjimka, kdy dojde k chybě na vstupu či výstupu v souboru.
 * @exception FileNotFoundException - výjimka, kdy není nalezen zadaný název souboru.
 */
    public static void ZasifrujDoSouboruZeSouboru() {
        System.out.println("Zadejte velikost kroku sifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadejte nazev souboru, ze ktereho se bude cist text k sifrovani: ");
        String cestaZeSouboru = new Scanner(System.in).next();
        String textZeSouboru = NactiTextZeSouboru(cestaZeSouboru);
        String[] casti = textZeSouboru.split("\n");
        String textZeSouboru2 = "";
        for (int i = 0; i < casti.length; i++) {
            textZeSouboru2 = textZeSouboru2 + casti[i];

        }
        int pomoc = textZeSouboru2.length();
        int ascii;
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        textZeSouboru2.getChars(0, pomoc, znaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) znaky[i] + sifra;
            sifrovaneZnaky[i] = (char) ascii;
        }
        System.out.println("Zadejte nazev souboru pro zapis: ");
        File cestaDoSouboru = new File(new Scanner(System.in).next()+".txt");
        if (cestaDoSouboru.exists() == true) {
            System.out.println("Soubor jiz existuje!");
            System.out.println("Prejete si sifrovany text do tohoto souboru pripsat? ANO/NE");
            String vyber = new Scanner(System.in).next();
            String odpoved = vyber.toLowerCase();
            if (odpoved.equals("ano")) {
                try {
                    FileWriter fw = new FileWriter(cestaDoSouboru, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(sifrovaneZnaky);
                    bw.newLine();
                    bw.close();
                    fw.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Soubor nebyl nalezen!");
                } catch (IOException e1) {
                    System.out.println("Chyba pri zapisu!!!");
                }
                System.out.println("Sifrovany text byl ulozen do souboru " + cestaDoSouboru);
            } else {
                System.out.println("Zadejte novy nazev souboru:");
                cestaDoSouboru = new File(new Scanner(System.in).next()+".txt");
                if (cestaDoSouboru.exists() == true) {
                    System.out.println("Soubor opet existuje! Akce byla ukoncena!");
                    ZasifrujNactiZKlavesnice();
                } else {
                    try {
                        FileWriter fw = new FileWriter(cestaDoSouboru, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(sifrovaneZnaky);
                        bw.newLine();
                        bw.close();
                        fw.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Soubor nebyl nalezen!");
                    } catch (IOException e1) {
                        System.out.println("Chyba pri zapisu!!!");
                    }
                    System.out.println("Sifrovany text byl ulozen do souboru " + cestaDoSouboru);
                }
            }
        } else {
            try {
                FileWriter fw = new FileWriter(cestaDoSouboru, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(sifrovaneZnaky);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (FileNotFoundException e) {
                System.out.println("Soubor nebyl nalezen!");
            } catch (IOException e1) {
                System.out.println("Chyba pri zapisu!!!");
            }
            System.out.println("Sifrovany text byl ulozen do souboru " + cestaDoSouboru);
        }
        ZasifrujnactiZeSouboru();
    }
/**
    * Metoda rozšifruj je napsána jako takový detailnější rozcestník, když se uživatel rozhodl dešifrovat. Zeptá se nás odkud chceme text zadávat a poté nás pošle dále do programu po cestě dešifrace.
    * @param vyber je promenna, kterou definujeme, co si uživatel v Menu zvolil jako svou volbu a po té jej pomocí fce switch přesměruje.
    * @exception InputMismatchException - výjimka, která ošetřuje zadání jiné než číselné hodnoty v Menu.
  */
    public static void rozsifruj() {
        try {
            int vyber;
            System.out.println("------------- MENU ROZSIFRUJ ---------------");
            System.out.println("Vyberte si nějakou z následujících možností!");
            System.out.println("1 - Rozsifruj do souboru");
            System.out.println("2 - Rozsifruj a vypis na obrazovku");
            System.out.println("3 - Zpet na hlavni menu");
            System.out.println("4 - Zcela ukoncit program");
            vyber = new Scanner(System.in).nextInt();

            if (vyber < 1 || vyber > 4) {
                System.out.println("Spatna volba - zvolte jinou moznost: ");
                System.out.println("");
                rozsifruj();
            }

            switch (vyber) {
                case 1:
                    RozsifrujDoSouboru();
                    break;
                case 2:
                    RozsifrujNaObrazovku();
                    break;

                case 3:
                    System.out.println("Navrat do hlavniho menu!");
                    System.out.println("");
                    MENU();
                    break;
                case 4:
                    System.out.println("Program bude nyní ukončen!");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Vase volba musi byt v ciselne podobe");
            rozsifruj();
        }
    }

/**
 * Tato metoda je posledním rozcestníkem, který nás na cestě za dešifrací textu potká. Nyní se nás dotazuje na lokaci, ve které chceme mít výsledek zobrazen nebo uložen.
 * @param vyber je promenna, kterou definujeme, co si uživatel v Menu zvolil jako svou volbu a po té jej pomocí fce switch přesměruje.
 * @exception InputMismatchException výjimka, která ošetřuje zadání jiné než číselné hodnoty v Menu.
 */
    public static void RozsifrujDoSouboru() {
        try {
            int vyber;
            System.out.println("Vyberte si nějakou z následujících možností!");
            System.out.println("1 - Rozsifruj text zadany z klavesnice do souboru");
            System.out.println("2 - Rozsifruj text ze souboru do souboru");
            System.out.println("3 - Zpet na menu rozsifruj");
            System.out.println("4 - Zpet na hlavni menu ");
            System.out.println("5 - Zcela ukoncit program");
            vyber = new Scanner(System.in).nextInt();

            if (vyber < 1 || vyber > 5) {
                System.out.println("Spatna volba - zvolte jinou moznost: ");
                System.out.println("");
                RozsifrujDoSouboru();
            }

            switch (vyber) {
                case 1:
                    RozsifrujDoSouboruZKlavesnice();
                    break;
                case 2:
                    RozsifrujDoSouboruZeSouboru();
                    break;
                case 3:
                    System.out.println("Navrat do menu rozsifruj!");
                    System.out.println("");
                    rozsifruj();
                case 4:
                    System.out.println("Navrat do hlavniho menu!");
                    System.out.println("");
                    MENU();
                    break;
                case 5:
                    System.out.println("Program bude nyní ukončen!");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Vase volba musi byt v ciselne podobe");
            RozsifrujDoSouboru();
        }
    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát dešifrovat a poté se uživatele optá na text k dešifrování. Když pomocí cyklu provede dešifraci textu, tak jej uloží do souboru.
 * To provede tak, že se uživatele dotáže pro zadání názvu souboru a ověří, jestli již existuje nebo ne.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější dešifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých dešifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 * @param cesta slouží na určení názvu souboru, do kterého bude dešifrovaný text uložen.
 * @param odpoved proměnná sloužící jako potvrzení o akci se souborem, který byl označen jako existující.
 * @exception IOException výjimka, kdy dojde k chybě na vstupu či výstupu v souboru.
 * @exception FileNotFoundException výjimka, kdy není nalezen zadaný název souboru.
 */
    public static void RozsifrujDoSouboruZKlavesnice() {
        System.out.println("Zadejte velikost kroku sifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadej text, ktery chces rozsifrovat: ");
        String text = new Scanner(System.in).next();
        int pomoc = text.length();
        int ascii;
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        text.getChars(0, pomoc, sifrovaneZnaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) sifrovaneZnaky[i] - sifra;
            znaky[i] = (char) ascii;
        }
        System.out.println("Zadejte nazev souboru: ");
         File cesta = new File(new Scanner(System.in).next()+".txt");

        if (cesta.exists() == true) {
            System.out.println("Soubor jiz existuje!");
            System.out.println("Prejete si rozsifrovany text do tohoto souboru pripsat? ANO/NE");
            String vyber = new Scanner(System.in).next();
            String odpoved = vyber.toLowerCase();
            if (odpoved.equals("ano")) {
                try {
                    FileWriter fw = new FileWriter(cesta, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(znaky);
                    bw.newLine();
                    bw.close();
                    fw.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Soubor nebyl nalezen!");
                } catch (IOException e1) {
                    System.out.println("Chyba pri zapisu!!!");
                }
                System.out.println("Sifrovany text byl ulozen do souboru " + cesta);
            } else {
                System.out.println("Zadejte novy nazev souboru:");
                cesta = new File(new Scanner(System.in).next()+".txt");
                if (cesta.exists() == true) {
                    System.out.println("Soubor opet existuje! Akce byla ukoncena!");
                    RozsifrujDoSouboruZKlavesnice();
                } else {
                    try {
                        FileWriter fw = new FileWriter(cesta, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(znaky);
                        bw.newLine();
                        bw.close();
                        fw.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Soubor nebyl nalezen!");
                    } catch (IOException e1) {
                        System.out.println("Chyba pri zapisu!!!");
                    }
                    System.out.println("Sifrovany text byl ulozen do souboru " + cesta);
                }
            }
        } else {
            try {
                FileWriter fw = new FileWriter(cesta, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(znaky);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (FileNotFoundException e) {
                System.out.println("Soubor nebyl nalezen!");
            } catch (IOException e1) {
                System.out.println("Chyba pri zapisu!!!");
            }
            System.out.println("Sifrovany text byl ulozen do souboru " + cesta);
        }

        RozsifrujDoSouboru();
    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát dešifrovat a poté se uživatele optá na název souboru, ze kterého se bude načítat text k dešifrování. Když pomocí cyklu provede dešifraci textu, tak jej uloží do souboru.
 * To provede tak, že se uživatele dotáže pro zadání názvu souboru a ověří, jestli již existuje nebo ne.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější dešifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých dešifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 * @param cesta slouží na určení názvu souboru, do kterého bude dešifrovaný text uložen.
 * @param odpoved proměnná sloužící jako potvrzení o akci se souborem, který byl označen jako existující.
 * @param casti pole určené pro uložení jednotlivých řádků text souboru, ze kterého je text načítán.
 * @param textZeSouboru2 proměnná sloužící k uložení získaného textu ze souboru.
 * @exception IOException výjimka, kdy dojde k chybě na vstupu či výstupu v souboru.
 * @exception FileNotFoundException výjimka, kdy není nalezen zadaný název souboru.
 */
    public static void RozsifrujDoSouboruZeSouboru() {
        System.out.println("Zadejte velikost kroku rozsifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadejte nazev souboru, ze ktereho se bude cist text k rozsifrovani: ");
        String cestaZeSouboru = new Scanner(System.in).next();
        String textZeSouboru = NactiTextZeSouboru(cestaZeSouboru);
        String[] casti = textZeSouboru.split("\n");
        String textZeSouboru2 = "";
        for (int i = 0; i < casti.length; i++) {
            textZeSouboru2 = textZeSouboru2 + casti[i];
        }
        int pomoc = textZeSouboru2.length();
        int ascii;
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        textZeSouboru2.getChars(0, pomoc, sifrovaneZnaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) sifrovaneZnaky[i] - sifra;
            znaky[i] = (char) ascii;
        }
        System.out.println("Zadejte nazev souboru: ");
        File cestaDoSouboru = new File(new Scanner(System.in).next()+".txt");

        if (cestaDoSouboru.exists() == true) {
            System.out.println("Soubor jiz existuje!");
            System.out.println("Prejete si sifrovany text do tohoto souboru pripsat? ANO/NE");
            String vyber = new Scanner(System.in).next();
            String odpoved = vyber.toLowerCase();
            if (odpoved.equals("ano")) {
                try {
                    FileWriter fw = new FileWriter(cestaDoSouboru, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(znaky);
                    bw.newLine();
                    bw.close();
                    fw.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Soubor nebyl nalezen!");
                } catch (IOException e1) {
                    System.out.println("Chyba pri zapisu!!!");
                }
                System.out.println("Sifrovany text byl ulozen do souboru " + cestaDoSouboru);
            } else {
                System.out.println("Zadejte novy nazev souboru:");
                cestaDoSouboru = new File(new Scanner(System.in).next()+".txt");
                if (cestaDoSouboru.exists() == true) {
                    System.out.println("Soubor opet existuje! Akce byla ukoncena!");
                    RozsifrujDoSouboru();
                } else {
                    try {
                        FileWriter fw = new FileWriter(cestaDoSouboru, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(znaky);
                        bw.newLine();
                        bw.close();
                        fw.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Soubor nebyl nalezen!");
                    } catch (IOException e1) {
                        System.out.println("Chyba pri zapisu!!!");
                    }
                    System.out.println("Sifrovany text byl ulozen do souboru " + cestaDoSouboru);
                }
            }
        } else {
            try {
                FileWriter fw = new FileWriter(cestaDoSouboru, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(znaky);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (FileNotFoundException e) {
                System.out.println("Soubor nebyl nalezen!");
            } catch (IOException e1) {
                System.out.println("Chyba pri zapisu!!!");
            }
            System.out.println("Sifrovany text byl ulozen do souboru " + cestaDoSouboru);
        }

        RozsifrujDoSouboru();
    }
/**
 * Tato metoda je posledním rozcestníkem, který nás na cestě za dešifrací textu potká. Nyní se nás dotazuje na lokaci, ve které chceme mít výsledek zobrazen nebo uložen.
 * @param vyber je promenna, kterou definujeme, co si uživatel v Menu zvolil jako svou volbu a po té jej pomocí fce switch přesměruje.
 * @exception InputMismatchException výjimka, která ošetřuje zadání jiné než číselné hodnoty v Menu.
 */
    public static void RozsifrujNaObrazovku() {
        try {
            int vyber;
            System.out.println("Vyberte si nějakou z následujících možností!");
            System.out.println("1 - Rozsifruj text zadany z klavesnice na obrazovku");
            System.out.println("2 - Rozsifruj text ze souboru na obrazovku");
            System.out.println("3 - Zpet na menu rozsifruj");
            System.out.println("4 - Zpet na hlavni menu ");
            System.out.println("5 - Zcela ukoncit program");
            vyber = new Scanner(System.in).nextInt();

            if (vyber < 1 || vyber > 5) {
                System.out.println("Spatna volba - zvolte jinou moznost: ");
                System.out.println("");
                RozsifrujDoSouboru();
            }

            switch (vyber) {
                case 1:
                    RozsifrujNaObrazovkuZKlavesnice();
                    break;
                case 2:
                    RozsifrujNaObrazovkuZeSouboru();
                    break;
                case 3:
                    System.out.println("Navrat do menu rozsifruj!");
                    System.out.println("");
                    rozsifruj();
                case 4:
                    System.out.println("Navrat do hlavniho menu!");
                    System.out.println("");
                    MENU();
                    break;
                case 5:
                    System.out.println("Program bude nyní ukončen!");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Vase volba musi byt v ciselne podobe");
            RozsifrujNaObrazovku();
        }
    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát dešifrovat a poté se uživatele optá na text k dešifrování. Když pomocí cyklu provede dešifraci textu, tak jej vypíše na obrazovku.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější dešifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých dešifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 */
    public static void RozsifrujNaObrazovkuZKlavesnice() {
        System.out.println("Zadejte velikost kroku rozsifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadej text, ktery chces rozsifrovat: ");
        String text = new Scanner(System.in).next();
        int pomoc = text.length();
        int ascii;
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        text.getChars(0, pomoc, sifrovaneZnaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) sifrovaneZnaky[i] - sifra;
            znaky[i] = (char) ascii;
        }
        System.out.println("Vami zadany text po rozsifrovani: ");
        System.out.println(znaky);
        RozsifrujNaObrazovku();
    }
/**
 * Metoda se nás optá na velikost kroku, kterou si bude uživatel přát dešifrovat a poté se uživatele optá na název souboru, ze kterého se bude načítat text k dešifrování. Když pomocí cyklu provede dešifraci textu, tak jej vypíše na obrazovku.
 * @param pomoc délka zadaného textu uživatelem - slouží jako pomocná proměnná v cyklu.
 * @param znaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých znaků pro pozdější dešifrování.
 * @param sifrovaneZnaky pole znaku velké jako délka zadaného textu - slouží jako úložiště jednotlivých dešifrovaných znaků.
 * @param ascii proměnná pro uložení ASCII hodnoty získaného znaku z pole.
 * @param cesta slouží na určení názvu souboru, do kterého bude dešifrovaný text uložen.
 * @param casti pole určené pro uložení jednotlivých řádků text souboru, ze kterého je text načítán.
 * @param textZeSouboru2 proměnná sloužící k uložení získaného textu ze souboru.
 */
    public static void RozsifrujNaObrazovkuZeSouboru() {
        System.out.println("Zadejte velikost kroku rozsifrovani!!");
        int sifra = new Scanner(System.in).nextInt();
        System.out.println("Zadejte nazev souboru, ze ktereho se bude cist text k rozsifrovani: ");
        String cesta = new Scanner(System.in).next();
        String textZeSouboru = NactiTextZeSouboru(cesta);
        int ascii;
        String[] casti = textZeSouboru.split("\n");
        String textZeSouboru2 = "";
        for (int i = 0; i < casti.length; i++) {
            textZeSouboru2 = textZeSouboru2 + casti[i];
        }
        int pomoc = textZeSouboru2.length();
        System.out.println("Prevadim!!");
        char[] znaky = new char[pomoc];
        char[] sifrovaneZnaky = new char[pomoc];
        textZeSouboru2.getChars(0, pomoc, sifrovaneZnaky, 0);
        for (int i = 0; i < pomoc; i++) {
            ascii = (int) sifrovaneZnaky[i] - sifra;
            znaky[i] = (char) ascii;
        }
        System.out.println("Vami zadany text po rozsifrovani: ");
        System.out.println(znaky);
        RozsifrujNaObrazovku();
    }
/**
 * Kořenová metoda, která pouze volá metodu Menu k spuštění celého programu.
 *
 */
    public static void main(String[] args) {
        MENU();
       
    }
}
