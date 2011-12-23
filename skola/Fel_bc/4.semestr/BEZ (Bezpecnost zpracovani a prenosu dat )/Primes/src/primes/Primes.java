package primes;

//trida umoznujici praci s dlouhymi cisly
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Random;

public class Primes {
    //--------------------------------------------------------------------

    public static void main(String[] args) {
        // V dnesnim cviceni si ukazeme nektere metody testovani prvociselnosti.
        // Preskocime metodu zkusmeho deleni, ktera je pomala.
        // Prejdeme rovnou na metody zalozene na male Fermatove vete.
        // V tomto souboru jsou pro Vas pripraveny priklady.
        // Krokujte program a divejte se do vystupniho okna na vypisy

        // Mala fermatova veta zni: Necht p je prvocislo, a je z mnoziny N takove,
        // ze p nedeli a, potom a^(p-1) == 1 (mod p)

        // Zdalo by se, ze ji muzeme primo pouzit k testovani prvociselnost,
        // protoze jako podminku ma p prvocislo. Zkusme to:

        BigInt n, a, vysl;
        n = new BigInt(137); // Je 137 prvocislo? My vime, ze je.
        a = new BigInt(2); // zvolime si a, ktere je nesoudelne s n. Staci 0 < a < n

        vysl = a.modPow(n.subtract(1), n); // vysl = a^(n-1) mod n
        System.out.println(a + "^" + n.subtract(1) + " mod " + n + " = " + vysl);
        System.out.println();

        n = new BigInt(133); // Je 133 prvocislo? Vime, ze 133 = 7 * 19
        a = new BigInt(2); // zvolime si a.

        vysl = a.modPow(n.subtract(1), n); // vysl = a^(n-1) mod n
        System.out.println(a + "^" + n.subtract(1) + " mod " + n + " = " + vysl);
        System.out.println();

        // Az do ted to vychazelo. Ale tak jednoduche to neni:

        n = new BigInt(341); // Je 341 prvocislo? Vime, ze 341 = 11 * 31
        a = new BigInt(2); // zvolime si a.

        vysl = a.modPow(n.subtract(1), n); // vysl = a^(n-1) mod n
        System.out.println(a + "^" + n.subtract(1) + " mod " + n + " = " + vysl);
        System.out.println();

        // 341 neni prvocislo, presto splnuje rovnici v m. Fermatove vete pro a=2.
        // Rikame, ze 341 je pseudoprvocislo vzhledem k bazi 2.
        //**************************************************************
        // UKOL: Najdete bazi, vzhledem ke ktere 341 neni pseudoprvocislo.

        System.exit(0); // Po splneni ukolu zakomentujte
        //--------------------------------------------------------------

        // Fermatovu vetu musime tedy pouzit s ruznymi bazemi.
        // Pouzijeme funkci FermatTest, zdrojovy kod viz nize

        boolean tst;
        n = new BigInt(341);
        tst = FermatTest(n);
        System.out.println("FermatTest(" + n + ") = " + tst);

        // Pro srovnani pouzijeme metodu isProbablePrime, ktera je definovana ve tride BigInteger
        tst = n.isProbablePrime();
        System.out.println("isProbablePrime(" + n + ") = " + tst);
        System.out.println();

        // Fermatuv test muze ale selhat. Vzdy musime testovat jen urcitou
        // podmnozinu ruznych bazi, kvuli rychlosti.
        // Vyzkousejte:

        n = new BigInt(101 * 151 * 251); // = 3828001

        tst = FermatTest(n);
        System.out.println("FermatTest(" + n + ") = " + tst);
        // Pro srovnani pouzijeme metodu isProbablePrime, ktera je
        // definovana ve tride BigInteger, soucasti Javy
        tst = n.isProbablePrime();
        System.out.println("isProbablePrime(" + n + ") = " + tst);
        System.out.println();

        // Existuji cisla, ktera jsou pseudoprvocisly pro vsechny
        // baze a nesoudelne s n, 1 < a < n-1. Nazyvaji se Carmichaelova cisla.

        // Carmichaelova cisla by nas algoritmus odhalil jako slozena
        // pouze tehdy, pokud bychom testovali nejakou bazi a soudelnou s n:

        a = new BigInt(101);
        vysl = a.modPow(n.subtract(1), n); //vysl = a^(n-1) mod n
        System.out.println(a + "^" + n.subtract(1) + " mod " + n + " = " + vysl);
        System.out.println();

        //**************************************************************
        // UKOL: Modifikujte funkci FermatTest, aby odhalila cislo
        // 101*151*251 jako slozene

        System.exit(0); // Po splneni ukolu zakomentujte
        //--------------------------------------------------------------

        // Napriklad toto Carmichaelovo cislo projde nejspis neodhaleno
        n = (new BigInt(8017)).multiply(16033).multiply(24049);

        tst = FermatTest(n);
        System.out.println("FermatTest(" + n + ") = " + tst);

        // Potrebujeme tedy lepsi test. V praxi se pouziva zejmena
        // Rabin-Milleruv test, ktery je take soucasti knihovni
        // funkce isProbablePrime. (Spolu s Lucas-Lehmerovym testem)
        tst = RabinMiller(n, 10);
        System.out.println("RabinMiller(" + n + ") = " + tst);

        // UKOL: Prostudujte kod funkce RabinMiller
        // Nektera specialni Carmichaelova cisla jdou generovat rychle, viz
        generateCarmichael2();

        // Ukazeme si, ze mnoho Carmichaelovych cisel projde
        // Fermatovym testem neodhaleno, zatimco Rabin-Milleruv test
        // je odhali

        // test prvociselnosti pole carmichael2
        for (int i = 0; i < carmichael2.size(); i++) {
            n = (BigInt) carmichael2.get(i);
            tst = FermatTest(n);
            System.out.println("FermatTest(" + n + ") = " + tst);
            tst = RabinMiller(n, 10);
            System.out.println("RabinMiller(" + n + ") = " + tst);
            System.out.println();
        }


    }

    // --------------------------------------------------------------------
    /**
     * Fermatuv test prvociselnosti
     *
     * @param n
     *            Prvocislo?
     * @return true jestli je prvocislo, false jinak
     */
    static boolean FermatTest(BigInt n) {
        BigInt a;
        BigInt r;

        //Nejdriv zkontrolujeme nektere trivialni pripady

        if (n.lessOrEqual(1)) {
            return false;
        }
        if (n.equal(2)) {
            return true;
        }
        if (n.mod(2) == 0) {
            System.out.println(n + " sude a neni 2, neni prvocislo");
            return false;
        }
        if (n.equal(3)) {
            return true;
        }
        if (n.mod(3) == 0) {
            System.out.println(n + " delitelne tremi, neni prvocislo");
            return false;
        }

        //Ted vyzkousime platnost rovnice z male Fermatovy vety a^(n-1) == 1 (mod n)
        // pro a od 2 do n-1, max. 100

        a = new BigInt(2);
        while ((a.less(n.subtract(1)) && (a.lessOrEqual(100)))) {
            r = a.modPow(n.subtract(1), n); //r = a^(n-1) mod n
            if (!r.equal(1)) { // a je svedek, ze n je slozene
                System.out.println("Cislo n = " + n + " neni prvocislo, protoze pro a= " + a + " plati " + a + "^" + n.subtract(1) + " mod " + n + " = " + r);
                return false;
            }
            a = a.add(1);
        }
        System.out.println("Cislo n = " + n + " je pravdepodobne prvocislo");
        return true;
    }
    private static volatile Random staticRandom = null;

    private static Random getSecureRandom() {
        if (staticRandom == null) {
            staticRandom = new java.security.SecureRandom();
        }
        return staticRandom;
    }
    static final BigInt ONE = new BigInt(1);
    static final BigInt TWO = new BigInt(2);

    /**
     * Metoda predstavuje test prvociselnosti z knihovny java.math.BigInteger
     * (mirne upraveny, aby souhlasil s prednaskovym slidem)
     *
     * @param n
     *            Prvocislo?
     * @param NumTests
     *            Pocet testu
     * @return true jestli je prvocislo, false jinak
     */
    static boolean RabinMiller(BigInt p, int iterations) {

        BigInt p_1 = p.subtract(1);

        // Najdi b a liche m tak, aby platilo p == 1 + 2**b * m
        BigInt m = p_1;
        int b = m.getLowestSetBit();
        m = m.shiftRight(b);

        Random rnd = getSecureRandom(); // vezmi generator nahodnych cisel

        for (int i = 0; i < iterations; i++) {
            // 1. Vyber nahodnou bazi a z intervalu (1, p)
            BigInt a;
            do {
                a = new BigInt(p.bitLength(), rnd); // generuj nahodne cislo
                // stejne delky jako n
            } while (a.compareTo(ONE) <= 0 || a.compareTo(p) >= 0); // aby a > 1, a < p

            // 2. Necht j = 0, z = a^m mod p
            int j = 0;
            BigInt z = a.modPow(m, p);

            // 3. Kdyz z = 1, pak p muze byt prvocislem, k dalsi iteraci
            if (z.equals(ONE)) {
                continue;
            }

            // 4. Dokud umocnovanim nevyjde -1, opakuj
            while (!(z.equals(p_1))) {
                // Pokud v jine nez nulte iteraci vysla 1,
                // nebo pokud jsme prosli vsechny mocniny, cislo je slozene
                if (j > 0 && z.equals(ONE) || (j == b - 1)) {
                    return false; // cislo a je Millerovym svedkem
                }				// jinak umocni z = z^2 mod p
                z = z.modPow(TWO, p);
                j++;
            }
        }
        return true;
    }
    // --------------------------------------------------------------------
    // prvnich 33 Carmichaelovych cisel
    static final long carmichael[] = {
        561, 1105, 1729, 2465, 2821, 6601, 8911, 10585,
        15841, 29341, 41041, 46657, 52633, 62745, 63973,
        75361, 101101, 115921, 126217, 162401, 172081, 188461,
        252601, 278545, 294409, 314821, 334153, 340561, 399001,
        410041, 449065, 488881, 512461};
    //--------------------------------------------------------------------
    static final int NUM_CM2 = 50;
    static ArrayList<BigInt> carmichael2 = new ArrayList<BigInt>();

    // specialni Carmichaelova cisla, ktera jsou soucinem tri prvocisel tvaru
    // (6i+1)(12i+1)(18i+1)
    // daji se velmi rychle generovat
    /**
     * Generovani Carmichaelovych cisel ve tvaru (6i+1)(12i+1)(18i+1)
     */
    static void generateCarmichael2() {
        long i, j;
        BigInt k, l, m;
        for (i = 1, j = 0; j < NUM_CM2; i++) { // do naplneni pole
            k = new BigInt(6 * i + 1);
            if (!k.isProbablePrime()) {
                continue;
            }
            l = new BigInt(12 * i + 1);
            if (!l.isProbablePrime()) {
                continue;
            }
            m = new BigInt(18 * i + 1);
            if (!m.isProbablePrime()) {
                continue;
            }
            // mame ho

            //odkomentovat podle potreby
            System.out.println(k + " * " + l + " * " + m + " = " + new BigInt(k).multiply(l).multiply(m));

            BigInt mul = k.multiply(l).multiply(m);
            carmichael2.add(mul);
            j++;
        }
        //odkomentovat pro vypis vsech Crmichael2 cisel
        for (i=0; i<10; i++) System.out.println(i+": "+carmichael2.get((int)i));

    }
}

//************************************************************************
//rozsireni tridy BigInteger, kterou budeme pouzivat v programu
@SuppressWarnings("serial")
class BigInt extends BigInteger {

    //konstruktor vytvari BigInt ze Stringu
    public BigInt(String s) {
        super(s);
    }
    //konstruktor vytvari BigInt z promeny typu long

    public BigInt(long l) {
        super(Long.toString(l));
    }
    //konstruktor vytvari BigInt z BigIntegeru

    public BigInt(BigInteger b) {
        super(b.toString());
    }

    public BigInt(int numBits, Random rnd) {
        super(numBits, rnd);
    }

    //metoda porovnava aktualni hodnotu s hodnotou promenne l
    public int compareTo(long l) {
        return compareTo(new BigInt(l));
    }
    //<

    public boolean less(long l) {
        return compareTo(l) < 0;
    }
    //<=

    public boolean lessOrEqual(long l) {
        return compareTo(l) <= 0;
    }
    //==

    public boolean equal(long l) {
        return compareTo(l) == 0;
    }
    //>

    public boolean greater(long l) {
        return compareTo(l) > 0;
    }
    //>=

    public boolean greaterOrEqual(long l) {
        return compareTo(l) >= 0;
    }

    //<
    public boolean less(BigInt b) {
        return compareTo(b) < 0;
    }
    //<=

    public boolean lessOrEqual(BigInt b) {
        return compareTo(b) <= 0;
    }
    //==

    public boolean equal(BigInt b) {
        return compareTo(b) == 0;
    }
    //>

    public boolean greater(BigInt b) {
        return compareTo(b) > 0;
    }
    //>=

    public boolean greaterOrEqual(BigInt b) {
        return compareTo(b) >= 0;
    }

    //metoda vraci zbytek po deleni promennou typu long
    public long mod(long l) {
        return mod(new BigInt(l)).longValue();
    }

    //metoda vraci soucet aktualni hodnoty a hodnoty promenne typu long
    public BigInt add(long l) {
        return new BigInt(add(new BigInt(l)));
    }
    //metoda vraci rozdil aktualni hodnoty a hodnoty promenne typu long

    public BigInt subtract(long l) {
        return new BigInt(subtract(new BigInt(l)));
    }
    //metoda vraci soucin aktualni hodnoty a hodnoty promenne typu BigInt

    public BigInt multiply(BigInt b) {
        return new BigInt(super.multiply(b));
    }
    //metoda vraci soucin aktualni hodnoty a hodnoty promenne typu long

    public BigInt multiply(long l) {
        return multiply(new BigInt(l));
    }
    //metoda vraci zbytek po deleni n aktualni hodnoty umocnene na m-tou

    public BigInt modPow(BigInt m, BigInt n) {
        return new BigInt(super.modPow(m, n));
    }

    @Override
    public int getLowestSetBit() {
        return super.getLowestSetBit();
    }

    @Override
    public BigInt shiftRight(int n) {
        return new BigInt(super.shiftRight(n));
    }

    public boolean isProbablePrime() {
        return isProbablePrime(10);
    }

    @Override
    public boolean isProbablePrime(int certanty) {
        return super.isProbablePrime(certanty);
    }
}
