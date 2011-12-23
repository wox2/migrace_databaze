package des;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import java.io.*;

//********************************************************************************************************
public class DES {

    Cipher ecipher;
    Cipher dcipher;
    static final int DES_KEY_LENGTH = 16; // DES klic ma delku 64 bitu = 8 byt-u = 16 znaku (hexadecimalnich cisel)

    public static void main(String[] args) {

        long startTime = System.nanoTime(); // Zaznamena se pocatecni cas

        initData();	// Inicializace tridnich promennych src a dest; predstavuji otevreny a sifrovany text
        String partialKey = "253267BC9BB"; // Jen prvnich 11 znaku klice, je potreba odhalit cely klic ktery byl pouzit pri sifrovani

        System.out.println("-------DES--------");
        System.out.println("Vstupni data v hexadecimalni podobe:");
        System.out.println(charsToHex(src));
        /* vypis dat v podobe znaku pouzijte pouze vite-li, ze dana promenna obsahuje skutecne znaky */
        System.out.println("Vstupni data v podobe znaku:");
        System.out.println(src);
        System.out.println();
        System.out.println("Vystupni data v hexadecimalni podobe:");
        System.out.println(charsToHex(dest));
        System.out.println();
        /* vypis dat v podobe znaku pouzijte pouze vite-li, ze dana promenna obsahuje skutecne znaky */
        //System.out.println("Vystupni data v podobe znaku:");
        //System.out.println(dest);        

        String tmpKey = null; // Docasny klic, ktery pouzijeme pro testovani
        int i = 0;
        for (; i < Math.pow(2, getMissingLength(partialKey) * 4); i++) { 	// Je 2^pocet_chybejicich_bitu moznych klicu;
            // potrebujeme otestovat kazdy z nich
            try {
                tmpKey = generateKey(partialKey, i);	// Z castecneho klice a cisla instance vygenerujeme cely klic
              

                // TUTO METODU BUDE POTREBA DOPLNIT

                if (testKey(tmpKey, src, dest)) {
                    break; // Otestujeme jestli nami vygenerovany klic je ten spravny; pokud ano, koncime
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime(); // Zaznamena se konec
        long totalTimeInSeconds = Math.round((endTime - startTime) / 1E9); 	// Kolik casu nam zabral vypocet;
        // pokud mate spravne/ doimplementovano, bude priblizne 20 vterin
        System.out.println("Vypocet trval " + totalTimeInSeconds + " vteriny");
        if (testKey(tmpKey, src, dest)) {
            System.out.println("Klic je " + tmpKey);
            double estimatedTimeInSeconds = (totalTimeInSeconds * (Math.pow(2,
                    (DES_KEY_LENGTH - 2) * 4) / i));
            long years = Math.round(estimatedTimeInSeconds / 31536000);
            estimatedTimeInSeconds %= 31536000;
            long days = Math.round(estimatedTimeInSeconds / 86400);
            estimatedTimeInSeconds %= 86400;
            long hours = Math.round(estimatedTimeInSeconds / 3600);
            estimatedTimeInSeconds %= 3600;
            long minutes = Math.round(estimatedTimeInSeconds / 60);
            estimatedTimeInSeconds %= 60;
            long seconds = Math.round(estimatedTimeInSeconds);
            System.out.println("Pokud by nebyl znam ani jeden bit klice, vypocet by trval " + years + " let; " + days + " dnu; " + hours + " hodin; " + minutes + " minut; " + seconds + " sekund");
        } else {
            System.out.println("Nebyl nalezen odpovidajcii klic");
        }

        System.exit(0);

    }

    /**
     * Metoda doplni cast klice na zaklade cisla iterace
     *
     * @param PartialKey	Cast klice
     * @param iteration		Cislo iterace
     * @return 				Cely klic
     */
    static String generateKey(String PartialKey, int iteration) {
       int doplnekLength = getMissingLength(PartialKey);
       String WholeKey="";
       String hex = "";
      while (!isDesKey(WholeKey)){
      hex = convertIntToHex(iteration, doplnekLength);
       
       String doplnek = hex;
       WholeKey = PartialKey + doplnek;}

       return WholeKey;

                /*
                 * Metoda by mela vypadat asi takto
                 * 1. Vypocitat pocet chybejicich znaku
                 * 2. Z cisla iterace generovat odpovidajici doplnek
                 * 3. Sloucit znamou cast klice a generovany doplnek (WholeKey = PartialKey + doplnek)
                 * 4. Vratit vysledek
                 * Pouzijte metody getMissingLength() a convertIntToHex()
                 */
    }

    /**
     * Metoda spocte pocet chybejicich znaku v ciselnem zapisu klice, aby on
     * dosahl delku 16 znaku
     *
     * @param Key		Klic
     * @return 			Pocet chybejicich znaku
     */
    static int getMissingLength(String Key) {
        return DES_KEY_LENGTH - Key.length();
    }

    /**
     * Metoda prevede dekadicke cislo na hexadecimalni cislo urcite delky
     *
     * @param integer		Dekadicke cislo
     * @param length		Pozadovana delka
     * @return 				Hexadecimalni cislo
     */
    static String convertIntToHex(int integer, int length) {
        String hex = Integer.toHexString(integer); // Pouzijeme beznou metodu na konverzi int->hex
        if (hex.length() > length) {
            return "";
        } else {
            // Pokud je delka vystupu mensi nez pozadovana, pridame nuly na zacatek
            String returnHex = "";
            for (int i = 0; i < (length - hex.length()); i++) {
                returnHex += "0";
            }
            returnHex += hex;
            return returnHex.toUpperCase();
        }
    }

    /**
     * Metoda spocte pocet jednicek v hexadecimalnim zapisu jednoho znaku
     *
     * @param c		Znak reprezentujici jedinou hexadecimalni cislici
     * @return 		Pocet jednicek v dane cislici
     */
    static int countOnes(char c) {
        int ones = 0;
        int hexNumber = 0;
        if ('0' <= c && c <= '9') {
            hexNumber = Integer.parseInt(c + "");
        }
        if ('A' <= c && c <= 'F') {
            hexNumber = (int) (c - 'A') + 10;
        }
        for (int i = 0; i < 4; i++) {
            ones += (hexNumber % 2);
            hexNumber /= 2;
        }
        return ones;
    }

    /**
     * Metoda stanovi jestli je dany klic vhodny pro pouziti v algoritmu DES
     * (tj. jestli ma urcitou delku a jestli je kazdy 8.bit paritni)
     *
     * @param Key		Klic
     * @return 			true nebo false
     */
    static boolean isDesKey(String Key) {
        if (Key.length() != DES_KEY_LENGTH) {
            return false;
        }
        for (int i = 0; i < DES_KEY_LENGTH / 2; i += 1) {
            if ((countOnes(Key.charAt(2 * i)) + countOnes(Key.charAt(2 * i + 1))) % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metoda testuje jestli dany klic byl pouzit pro sifrovani otevreneho textu
     *
     * @param Key			Klic
     * @param PlainText		Otevreny text
     * @param CipherText	Odpovidajici sifrovany text
     * @return 				true pokud DES(PlainText, Key)==CipherText, jinak false
     */
    static boolean testKey(String Key, String PlainText, String CipherText) {
        if (!isDesKey(Key)) {
            return false;
        }
        String tmpSrc = decrypt(CipherText, Key);
        String compSrc = PlainText.substring(0, 3);
        String compTmpSrc = tmpSrc.substring(0, 3);
        return compTmpSrc.equalsIgnoreCase(compSrc);
    }

    /**
     * Metoda pro sifrovani zadaneho otevreneho textu pomoci algoritmu DES
     *
     * @param PlainText		Otevreny text (ve znakove podobe)
     * @param stringKey		Klic
     * @return 				Sifrovany text (ve znakove podobe)
     */
    static String encrypt(String PlainText, String stringKey) {
        try {
            byte[] Key = hexToBytes(stringKey);
            KeySpec ks = new DESKeySpec(Key);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey key = kf.generateSecret(ks);
            DESEncrypter encrypter = new DESEncrypter(key);
            ByteArrayInputStream inStream = new ByteArrayInputStream(PlainText.getBytes());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            encrypter.encrypt(inStream, outStream);
            String CipherText = outStream.toString();
            inStream.close();
            outStream.close();
            return CipherText;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Metoda pro desifrovani zadaneho sifrovaneho textu pomoci algoritmu DES
     *
     * @param CipherText	Sifrovany text (ve znakove podobe)
     * @param stringKey		Klic
     * @return 				Otevreny text (ve znakove podobe)
     */
    static String decrypt(String CipherText, String stringKey) {
        try {
            byte[] Key = hexToBytes(stringKey);
            KeySpec ks = new DESKeySpec(Key);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey key = kf.generateSecret(ks);
            DESDecrypter decrypter = new DESDecrypter(key);
            ByteArrayInputStream inStream = new ByteArrayInputStream(CipherText.getBytes());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            decrypter.decrypt(inStream, outStream);
            String PlainText = outStream.toString();
            inStream.close();
            outStream.close();
            return PlainText;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Prevede String sestavajici se z hexadecimalnich znaku na String znaku
     *
     * @param hex		Retezec hexadecimalnich znaku
     * @return 			Retezec znaku
     */
    public static String hexToChars(String hex) {
        byte[] buffer = hexToBytes(hex);
        return new String(buffer);
    }

    /**
     * Prevede String sestavajici se ze znaku na String hexadecimalnich hodnot
     *
     * @param chars		Retezec znaku
     * @return 			Retezec hexadecimalnich hodnot
     */
    public static String charsToHex(String chars) {
        return bytesToHex(chars.getBytes());
    }

    /**
     * Prevede String sestavajici se z hexadecimalnich znaku na pole bytu
     *
     * @param str		Retezec hexadecimalnich znaku
     * @return 			Pole bytu
     */
    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2,
                        i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    /**
     * Prevede pole bytu na String hexadecimalnich hodnot
     *
     * @param data		Pole bytu
     * @return 			Retezec hexadecimalnich hodnot
     */
    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16) {
                    str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
                } else {
                    str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
                }
            }
            return str.toUpperCase();
        }
    }
    public static String src;
    public static String dest;

    public static void initData() {
        src = "This example implements a class for encrypting and decrypting strings using DES.";
        dest = encrypt(src, "243366BD9ABBDEF0");

    }
}

// ********************************************************************************************************
class DESEncrypter {

    Cipher ecipher;

    public DESEncrypter(SecretKey key) {
        // Vytvorit inicializacni vektor
        byte[] iv = new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,
            0x72, 0x6F, 0x5A};
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv); // Specifikace parametru
        try {
            ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 	// Pouzity algoritmus je DES, operacni mod CBC,
            // PKCS5 doplnovani otevreneho textu
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }
    byte[] buf = new byte[1024];

    void encrypt(InputStream in, OutputStream out) {
        try {
            out = new CipherOutputStream(out, ecipher);
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }
    }
}

class DESDecrypter {

    Cipher dcipher;

    public DESDecrypter(SecretKey key) {
        // Vytvorit inicializacni vektor
        byte[] iv = new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,
            0x72, 0x6F, 0x5A};
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv); // Specifikace parametru
        try {
            dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 	// Pouzity algoritmus je DES, operacni mod CBC,
            // PKCS5 doplnovani otevreneho textu
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }
    byte[] buf = new byte[1024];

    void decrypt(InputStream in, OutputStream out) {
        try {
            in = new CipherInputStream(in, dcipher);
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }
    }

    Cipher getDcipher() {
        return dcipher;
    }
}
