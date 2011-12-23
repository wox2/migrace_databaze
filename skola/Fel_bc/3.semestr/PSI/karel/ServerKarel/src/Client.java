
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author woxie alias Martin Lukes
 * zdroje: http://java.sun.com/reference/api/
 *
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String nickname = "";                       //jmeno robota
        Socket echoSocket = null;       //zalozeny socket
        PrintWriter out = null;                     //vystup
        BufferedReader in = null;                   //vstup
        String ip;         //ip adresa
        String incomingMessage = "";                //nactena zprava
        String outcomingCommand = "";               //posilana zprava
        int port;
        Scanner sc = new Scanner(System.in);
        if (args.length != 2) {
            System.out.println("Program musi byt spusten prikazem v forme java "
                    + "Client adresa_serveru PORT - tedy napr. java sunray2.felk.cvut.cz 3555");
            System.exit(1);
        }
        ip = "dsnlab1.felk.cvut.cz";
        port = 3555;

        try {
            port = Integer.decode(args[1]);
            ip = args[0];
        } catch (NumberFormatException ex) {
            System.out.println("Druhy parametr - port musi byt cislo");
            System.exit(1);
        }

        /*
        System.out.println("Zadejte adresu:");
        ip = sc.nextLine();
        System.out.println("Zadejte port");
        port = sc.nextInt();
         */

        
        /*
        ip = "localhost";
        port = 3555;
         */
        echoSocket = new Socket(ip, port);

        int commandCode = 0;                        //kod prichozi zpravy
        boolean automat = true;                     //moznost prepnout na automat
        int direction = -1;                         //smer(default 1)
        int x = 333, y = 0;                         //souradnice
        boolean skipRead = false;


        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Adresa : " + ip + " nenalezena.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Data z " + ip + " nejsou citelna.");
            System.exit(1);
        }

        incomingMessage = readAndPrintIncomingMessage(in);
        nickname = getNick(incomingMessage);

        if (automat == false) {
            do {
                outcomingCommand = sc.nextLine();
                printAndSendCommand(nickname, outcomingCommand, out);    //zapsani prikazu bez nicku, ten je dopsan automatem
                incomingMessage = readAndPrintIncomingMessage(in);
                commandCode = getCommandCode(incomingMessage);
            } while (!(commandCode == 221 || commandCode == 530 || commandCode == 550 || commandCode == 571 ||
                    commandCode == 572 || commandCode == 540));
        } else {
            printAndSendCommand(nickname, "VLEVO", out);          //prvni prikaz = VLEVO
            incomingMessage = readAndPrintIncomingMessage(in);
            skipRead = true;
            commandCode = getCommandCode(incomingMessage);
            x = getX(incomingMessage);
            y = getY(incomingMessage);
            do {
                if (!skipRead) {
                    incomingMessage = readAndPrintIncomingMessage(in);
                    commandCode = getCommandCode(incomingMessage);
                } else {
                    skipRead = false;
                }

                switch (commandCode) {
                    case 570:
                        sendRepairCommand(nickname, incomingMessage, out);
                        break;

                    case 250:
                        if (getEnergy(incomingMessage) <= 10) {
                            printAndSendCommand(nickname, "NABIT", out);
                            break;
                        }
                        if (0 == getX(incomingMessage) && 0 == getY(incomingMessage)) {
                            printAndSendCommand(nickname, "ZVEDNI", out);
                        } else {
                            if (direction != -1) {
                                outcomingCommand = getActionCommand(incomingMessage, direction);
                                printAndSendCommand(nickname, outcomingCommand, out);
                                if (outcomingCommand.equals("VLEVO")) {
                                    direction = (direction + 1) % 4;
                                }
                                break;
                            } else {
                                outcomingCommand = "KROK";
                                printAndSendCommand(nickname, outcomingCommand, out);
                                incomingMessage = readAndPrintIncomingMessage(in);
                                commandCode = getCommandCode(incomingMessage);
                                if (commandCode != 570) {
                                    direction = getDirection(x, y, incomingMessage);
                                }
                                skipRead = true;
                            }
                        }
                }
            } while (!(commandCode == 221 || commandCode == 530 || commandCode == 550 || commandCode == 571
                    || commandCode == 572)); //dokud se nedostavi ukoncovaci prikaz

            out.close();
            in.close();                                         //vsechno se uzavre
            echoSocket.close();
        }
    }

    /**
     * Ziska energii z prichozi zpravy
     * @param message zprava, z ktere se ma hodnota energy vyextrahovat
     * @return
     */
    public static int getEnergy(String message) {
        return Integer.parseInt(message.substring(message.indexOf("(") + 1, message.indexOf(",")));
    }

    /**
     * Zistak prezdivku ze Stringu
     * @param str String, ze ktereho je prezdivka ziskana
     * @return
     */
    public static String getNick(String str) {
        String callMe = "Oslovuj mne ";
        int count;                  //delka osloveni
        int j = 0;    // pom. pr. pro pruchod stringem
        boolean callMeFound = false;     //nalezeni stringu callMe
        while (true) {
            count = 0;
            for (int i = 0; j < str.length(); i++, j++) {
                if (callMe.charAt(i) != str.charAt(j)) {
                    i = -1;
                    if (str.charAt(j) == 'O') {
                        j--;
                    }
                }
                if (i + 1 == callMe.length()) {
                    callMeFound = true;
                    break;
                }
            }
            if (j + 1 == str.length()) {
                throw new IndexOutOfBoundsException();
            }
            if (callMeFound) {
                if (str.charAt(j + 1) == ' ') {
                    continue;
                }
                for (; j + count < str.length(); count++) {
                    if (str.charAt(count + j) == '.') {
                        break;
                    }
                }
                if (count < 8) {
                    continue;
                } else {
                    break;
                }
            }

        }
        return str.substring(j + 1, j + count);
    }

    /**
     *  Nacte radku z BufferedReaderu in
     * @param in  BufferedReader, ze ktereho je zprava nactena
     * @return Nacteny String
     */
    public static String readLine(BufferedReader in) {   //nacteni radky
        String message = "";
        int countChars = 0;
        try {
            int incomingBit = 0;    //prichozi bit
            while (incomingBit != -1) {
                incomingBit = in.read();
                countChars++;
                if (countChars >= 512 * 1024) {
                    throw new IOException();
                }
                if (incomingBit == (int) '\r') {
                    incomingBit = in.read();
                    if (incomingBit == ((int) '\n')) {
                        break;
                    } else {
                        throw new Exception();
                    }
                } else {

                    message += (char) incomingBit;
                }
            }
        } catch (IOException e) {
            System.out.println("Je to moc dlouhy, sefe");
        } catch (Exception e) {
            System.out.println("Neco se pokazilo");
        }
        return message;
    }

    /**
     * Odesla odpovedi serveru
     * @param nickname jmeno serveru
     * @param command odesilana zprava
     * @param out vychozi proud
     */
    static void printAndSendCommand(String nickname, String command, PrintWriter out) {  
        printAndSendCommand(nickname + " " + command, out);
    }

    /**
     * Vypise a vytiskne prikaz
     * @param outcomingCommand prikaz k odeslani
     * @param out vychozi proud
     */
    static void printAndSendCommand(String outcomingCommand, PrintWriter out) {
        System.out.println(outcomingCommand);
        out.print(outcomingCommand + "\r\n");
        out.flush();
    }

    /**
     *  Nacte a vypise prichozi zpravu z bufferu
     * @param in BufferedReader, z ktereho se zprava nacita
     * @return prichozi zprava
     */
    static String readAndPrintIncomingMessage(BufferedReader in) {
        String incomingMessage = readLine(in);
        System.out.println(incomingMessage);
        return incomingMessage;
    }

    /**
     * Vytiskne prichozi zpravu a extrahuje z ni jeji kod
     * @param incomingMessage prichozi zprava
     * @return
     */
    static int getCommandCode(String incomingMessage) {
        int commandCode = -1;
        try {
            commandCode = Integer.parseInt(incomingMessage.substring(0, incomingMessage.indexOf(" "))); //najde prikaz

        } catch (NumberFormatException e) {
            System.err.println("Chyba:Kod nebyl nalezen.");
        }
        return commandCode;
    }

    /**
     * Odesle opravovaci prikaz pres PrintWriter out. Blok k opraveni je extrahovan z prichozi zpravy
     * @param nickname Osloveni serveru
     * @param incomingMessage prichozi zprava
     * @param out PrintWriter
     */
    static void sendRepairCommand(String nickname, String incomingMessage, PrintWriter out) {
        int porucha = (int) incomingMessage.charAt(incomingMessage.length() - 1) - (int) '0';
        printAndSendCommand(nickname, "OPRAVIT " + porucha, out);
    }

    /**
     * Urci prikaz, kterym se ma robot ridit (VLEVO nebo KROK) podle smeru a pozice robota
     * @param incomingMessage prichozi zprava, z ktere je extrahovana pozice
     * @param direction smer natoceni robota
     * @return prikaz k provedeni
     */
    public static String getActionCommand(String incomingMessage, int direction) {
        switch (direction) {
            case 0: {
                if (getY(incomingMessage) < 0) {
                    return "KROK";
                } else {
                    return "VLEVO";
                }
            }
            case 1: {
                if (getX(incomingMessage) > 0) {
                    return "KROK";
                } else {
                    return "VLEVO";
                }
            }
            case 2: {
                if (getY(incomingMessage) > 0) {
                    return "KROK";
                } else {
                    return "VLEVO";
                }
            }
            case 3: {
                if (getX(incomingMessage) < 0) {
                    return "KROK";
                } else {
                    return "VLEVO";
                }
            }
        }
        return "xxx";
    }

    /**
     * Vrati x-ovou pozici robota
     * @param incommingMessage prichozi zprava
     * @return x-ova pozice robota
     */
    public static int getX(String incomingMessage) {
        String pos = incomingMessage.substring(incomingMessage.indexOf(",") + 1);
        return Integer.parseInt(pos.substring(0, pos.indexOf(",")));
    }

    /**
     * Vrati y-ovou pozici robota
     * @param incommingMessage prichozi zprava
     * @return y-ova pozice robota
     */
    public static int getY(String incomingMessage) {
        String pos = incomingMessage.substring(incomingMessage.indexOf(",") + 1);
        return Integer.parseInt(pos.substring(pos.indexOf(",") + 1, pos.indexOf(")")));
    }

    /**
     * Nastavi smer z X a Y a souradnic minulych.
     * @param lastX minule X (pred prikazem KROK)
     * @param lastY minule Y (pred prikazem KROK)
     * @param incomingMessage (prichozi zprava)
     * @return smer
     */
    public static int getDirection(int lastX, int lastY, String incomingMessage) {
        int direction = -1; //dir neni nastaven
        try {
            if (lastX == 333) {
                System.out.println("333");
                throw new Exception();
            }
            int x = getX(incomingMessage);
            int y = getY(incomingMessage);
            if (lastX > x) {
                direction = 1;
            } else if (lastX < x) {
                direction = 3;
            } else if (lastY > y) {             //lastX == x
                direction = 2;
            } else if (lastY < y) {
                direction = 0;
            }
        } catch (Exception e) {
        }
        return direction;
    }
}
