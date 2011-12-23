/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matice;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author já
 */
public class Main {

    static boolean znamenko;

    static boolean nulovyVektor(double[][] matice, int cisloRadku) {
        for (int i = 0; i < matice[0].length; i++) {
            if (matice[cisloRadku][i] != 0) {
                return false;
            }
        }
        return true;
    }

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

    static double[][] prohodRadky(double[][] matice, int cisloRadku, int cisloRadku2) {
        if (testRektangularnosti(matice) == true) {
            if (znamenko == true) {
                znamenko = false;
            } else if (znamenko == false) {
                znamenko = true;
            }

            for (int i = 0; i < matice[0].length; i++) {
                double odklad = matice[cisloRadku][i];
                matice[cisloRadku][i] = matice[cisloRadku2][i];
                matice[cisloRadku2][i] = odklad;


            }
            return matice;
        } else {
            double[][] chybovaMatice = {{-1}};
            return chybovaMatice;
        }


    }

    static double[][] zadaniMatice() {
        System.out.println("Zadejte pocet radku matice");
        int pocetRadku = new Scanner(System.in).nextInt();
        System.out.println("Zadejte pocet sloupcu matice");
        int pocetSloupcu = new Scanner(System.in).nextInt();
        double[][] matice = new double[pocetRadku][pocetSloupcu];
        for (int i = 0; i < pocetRadku; i++) {
            System.out.println("Prvky "+ i + 1 + ". radek: ");
            for (int j = 0; j < pocetSloupcu; j++) {
                matice[i][j] = new Scanner(System.in).nextDouble();
            }
        }
        return matice;
    }

    static double[][] nuloveNaKonec(double[][] matice) {
        int iPoslNenul = matice.length - 1;
        for (int iRadku = 0; iRadku < matice.length; iRadku++) {
            if (nulovyVektor(matice, iRadku) == true && iPoslNenul > iRadku) {
                while (nulovyVektor(matice, iPoslNenul) == true) {
                    iPoslNenul--;

                }

                prohodRadky(matice, iPoslNenul, iRadku);
            }
            if (nulovyVektor(matice, iRadku) == false) {
                for (int iSloupce = 0; iSloupce < matice[0].length; iSloupce++) {
                }


            }


        }
        return matice;
    }

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

    static double[][] gaussEli(double[][] matice) {
        int nulSloup = 0;
        boolean akce = true;
        for (int iRadku = 0; iRadku - nulSloup < matice.length; iRadku++) {
            if (iRadku < matice[0].length) {
                if (akce == true && matice[iRadku - nulSloup][iRadku] == 0) {
                    matice = serazeniRadku(matice);
                    akce = false;
                }

                {
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
        }
        return matice;
    }

    static int hodnost(double[][] matice) {
        znamenko = true;
        int hodnost = 0;
        if (matice.length == matice[0].length && testRektangularnosti(matice) == true) {
            matice = gaussEli(matice);
            for (int cisloRadku = 0; cisloRadku < matice.length; cisloRadku++) {
                for (int cisloSloupce = 0; cisloSloupce < matice.length; cisloSloupce++) {
                    if (matice[cisloRadku][cisloSloupce] != 0) {
                        hodnost++;
                        cisloSloupce = 0;
                        cisloRadku++;
                    }
                }
            }
        } else {
            System.out.println("Matice neni ctvercova");
        }


        return hodnost;
    }

    static void vypisMatici(double[][] matice) {
        if (testRektangularnosti(matice) == true) {
            for (int i = 0; i < matice.length; i++) {
                for (int j = 0; j < matice[i].length; j++) {
                    System.out.print(matice[i][j] + " ");
                }
                System.out.println();

            }
        }
    }

    static double[][] scitaniMatic(double[][] scitanec1, double[][] scitanec2) {
        if (scitanec1.length == scitanec2.length && scitanec1[1].length ==
                scitanec2[1].length && testRektangularnosti(scitanec1) == true &&
                testRektangularnosti(scitanec2) == true) {
            double[][] soucet = new double[2][4];
            for (int i = 0; i < scitanec1.length; i++) {
                for (int j = 0; j < scitanec1[i].length; j++) {
                    soucet[i][j] = scitanec1[i][j] + scitanec2[i][j];
                }
            }
            return soucet;


        } else {
            System.out.println("Chybne zadani - matice nejsou stejneho typu" +
                    " nebo nejmene jedna z nich neni rektangularni" +
                    "(nema stejny pocet prvku v radcich)");
            double[][] chybnaMatice = new double[1][1];
            chybnaMatice[0][0] = -1;
            return chybnaMatice;
        }

    }

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

    static double[][] nasobeniMaticeSkalarem(double[][] matice, double nasobek) {
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[i].length; j++) {
                matice[i][j] *= nasobek;
            }
        }
        return matice;
    }

    static double[][] rozdilMatic(double[][] mensenec, double[][] mensitel) {

        return scitaniMatic(mensenec, nasobeniMaticeSkalarem(mensitel, -1));
    }

    static double[][] transponujMatici(double[][] matice) {
        double[][] transponovanaMatice = new double[matice[1].length][matice.length];
        if (testRektangularnosti(matice) == true) {
            for (int i = 0; i < matice.length; i++) {
                for (int j = 0; j < matice[i].length; j++) {
                    transponovanaMatice[j][i] = matice[i][j];
                }
            }
            return transponovanaMatice;
        } else {
            double[][] chybovaMatice = new double[1][1];
            chybovaMatice[0][0] = -1;
            return chybovaMatice;
        }
    }

    static double[][] nasobeniMatic(double[][] prvniSoucinitel, double[][] druhySoucinitel) {
        double[][] soucin;
        soucin = new double[prvniSoucinitel.length][druhySoucinitel[1].length];
        double skalarniSoucin = 0;
        int i, j, k;
        int radkuPrvniMatice = prvniSoucinitel.length;
        int radkuDruheMatice = druhySoucinitel.length;
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

    static double hodnotaDeterminantu(double[][] matice) {
        int det = 1;
        if (matice.length == matice[0].length && testRektangularnosti(matice) == true) {
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

    static void ovladani() {
        int n = 0;
double [][] A;
double[][] B;
        while (n != 1) {
            System.out.println("Program slouzici k pocitani s maticemi, zadejte akci," +
                    " ktera se ma provest pomoci cisla a enteru:");
            System.out.println("1. Scitani matic");
            System.out.println("2. Odcitani matic");
            System.out.println("3. Nasobeni matice matici");
            System.out.println("4. Nasobeni matice skalarem");
            System.out.println("5. Transponovani matice");
            System.out.println("6. Vypocitani hodnosti matice");
            System.out.println("7. Generuj matici");
            System.out.println("8. Konec");
            System.out.println("9. Zeliminuj matici gaussovou eliminaci");
            System.out.println("10. Vypocitani hodnoty determinantu matice");
            System.out.println("11. O programu");
            System.out.println("12. Zadani matice A");
            System.out.println("13. Zadani matice B");
            int ovladac = new Scanner(System.in).nextInt();
            switch (ovladac) {
                case 1:
                     {
                        double[][] m1 = {{1, 2, 3, 4}, {1, 2, 3, 5}};
                        double[][] m2 = {{1, 3, 4, 2}, {3, 4, 1, 0}};
                        System.out.println("Prvni matice je: ");
                        vypisMatici(m1);
                        System.out.println("Druha matice je: ");
                        vypisMatici(m2);
                        System.out.println("Soucet obou matic je: ");
                        vypisMatici(scitaniMatic(m1, m2));
                    }
                    break;
                case 2:
                    double[][] m1 = {{1, 2, 3, 4}, {1, 2, 3, 5}};
                    System.out.println("Prvni matice:");
                    vypisMatici(m1);
                    double[][] m2 = {{1, 3, 4, 2}, {3, 4, 1, 0}};
                    System.out.println("Druha matice:");
                    vypisMatici(m2);
                    System.out.println("Rozdil prvni matice - druha je: ");
                    vypisMatici(rozdilMatic(m1, m2));
                    break;
                case 3:
                    double[][] m4 = {{1, 2, 3}, {0, 1, 3}};
                    double[][] m5 = {{3}, {4}, {8}};
                    System.out.println("Prvni soucinitel: ");
                    vypisMatici(m4);
                    System.out.println("Druhy soucinitel: ");
                    vypisMatici(m5);
                    System.out.println("Soucin matic je: ");
                    vypisMatici(nasobeniMatic(m4, m5));
                    break;
                case 4:
                    double[][] m6 = {{1, 2, 3}, {0, 1, 3}};
                    System.out.println("Matice, kterou chcete vynasobit ");
                    vypisMatici(m6);
                    System.out.println("Zadejte skalar, kterym chcete vynasobit matici");
                    int nasobek = new Scanner(System.in).nextInt();
                    System.out.println("Vynasobena matice cislem " + nasobek + "je");
                    vypisMatici(nasobeniMaticeSkalarem(m6, nasobek));
                    break;
                case 5:
                    double[][] m3 = {{1, 3, 4, 2}, {3, 4, 1, 0}, {1, 2, 3, 4}};
                    System.out.println("Treti matice je: ");
                    vypisMatici(m3);
                    System.out.println("Transponovana matic k teto matici je: ");
                    vypisMatici(transponujMatici(m3));
                    break;
                case 6:
                    double[][] m7 = {{1, 1, 1}, {2, 3, 2}, {2, 1, 2}};
                    System.out.println("Zadana matice:");
                    vypisMatici(m7);
                    System.out.println("Zelimonovana zadana matice: ");
                    vypisMatici(gaussEli(m7));
                    System.out.println("Hodnost matice je:" + hodnost(m7));
                    break;
                case 7:
                    System.out.println("Zadejte pocet radku matice, kterou chcete" +
                            " vygenerovat: ");
                    int x = new Scanner(System.in).nextInt();
                    System.out.println("Zadejte pocet sloupcu matice, kterou " +
                            "chcete vygenerovat: ");
                    int y = new Scanner(System.in).nextInt();
                    System.out.println("Vami vygenerovana matice je: ");
                    vypisMatici(generovaniMatice(x, y));
                    break;
                default:
                    System.out.println("Musite zadat cislo v rozmezi 1-9 ");
                    break;
                case 8:
                    n = 1;
                    break;
                case 9:
                    double[][] matice2 = {{0, 0, 1, 2}, {2, 3, 4, 5},
                    {0, 0, 0, 5}, {0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 2, 3}};
                    vypisMatici(matice2);
                    System.out.println("Matice vypada po gaussove eliminaci takto: ");
                    vypisMatici(gaussEli(matice2));
                    break;
                case 10:
                    double[][] detMatice = {{0, 0, 10}, {1, 2, 1}, {1, 1, 1}};
                    vypisMatici(detMatice);
                    System.out.println("Hodnota determinantu matice je:"
                            +hodnotaDeterminantu(detMatice));
                    break;
                case 11:
                    System.out.println("Tento program byl napsan Martinem Lukesem" +
                            " jako zapoctovy program predmetu Algoritmizace na" +
                            "konci roku 2008 a pocatku roku 2009.");break;
                case 12:
                    System.out.println("Zadani hodnot matice A");
                    A=zadaniMatice();
                    vypisMatici(A);
                    break;
                case 13:
                    System.out.println("Zadani hodnot matice B");
                    B=zadaniMatice();
                    break;

            }
        }





    }

    public static void main(String[] args) {
        ovladani();

    }
}


