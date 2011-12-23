package zapoctovy_test;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author já
 */
public class lukesma4 {

    static char[] prunikSlov(char[] prvniSlovo, char[] druheSlovo) {
        int prvniDelka = prvniSlovo.length;
        int druhaDelka = druheSlovo.length;
        int maximalniDelka;
        if (prvniDelka > druhaDelka) {
            maximalniDelka = druhaDelka;
        } else {
            maximalniDelka = prvniDelka;
        }
        char[] spolecneTokeny = new char[maximalniDelka];
        int indexSpolecnychZnaku = 0;
        char znakPrvnihoPole;
        for (int i = 0; i < prvniSlovo.length; i++) {
            znakPrvnihoPole = prvniSlovo[i];
            if (obsahujeZnak(znakPrvnihoPole, spolecneTokeny) == false && obsahujeZnak(znakPrvnihoPole, druheSlovo) == true) {
                indexSpolecnychZnaku++;
                spolecneTokeny[indexSpolecnychZnaku] = znakPrvnihoPole;
            }
        }
        return spolecneTokeny;
    } // metoda, ktera by mela najit prunikslov, nevim, proc, ale je nejaka chyba v ukladani znaku do pole

    static boolean obsahujeZnak(char znak, char[] testovaneSlovo) {
        for (int i = 0; i < testovaneSlovo.length; i++) {
            if (znak == testovaneSlovo[i]) {
                return true;
            }
        }
        return false;
    // metoda obsahujeZnak zjisti, jestli dane pole obsahuje zadany znak
    }

    static void vypisPole(char[] pole) {
        for (int i = 0; i < pole.length; i++) {
            System.out.println(pole[i]);
        }
    }         // metoda pro vypisn pole

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] betka = new char[4];
        betka[1] = 'e';
        betka[2] = 'e';
        betka[3] = 'e';
        betka[0] = 'e';
        char[] beta = new char[2];
        betka[1] = 'i';
        betka[0] = 'e';
        vypisPole(prunikSlov(betka, beta));
        System.out.println((obsahujeZnak('e', betka)));
        char b = betka[0];
        System.out.println(b);
    }
}
