
import java.net.*;
import java.io.*;
import java.util.Random;

/**
 * @author woxie alias Martin Lukes         
 */
public class Server implements Runnable{
    /**
     * prichozi zprava
     */
    private String message = "";
    /**
     * RobotSimulator simuluje robota chodiciho po meste pro danou instanci Serveru
     */
    private RobotSimulator simulator;
    /**
     * PrintWriter vystupniho proudu.
     */
    private PrintWriter out = null;
    /**
     * Buffered reader
     */
    private BufferedReader in = null;
    /**
    * socket Clienta
    */
    private Socket client;
    /**
     * Osloveni serveru(robota), nastaven na : Ty pekelny stroji Ty pekelny stroji Ty pekelny stroji
     */
    private String nick = "Ty pekelny stroji Ty pekelny stroji Ty pekelny stroji";
   
    public static void main(String[] args) throws IOException {     
        Socket clientSocket = null;
        /**
         * port na kterym Server nasloucha
         */
        int port;

       // port = Integer.decode(args[0]);
        port = 3555;
        ServerSocket serverSocket = new ServerSocket(port);
       

        while (true) {

            try {
                clientSocket = serverSocket.accept();
                clientSocket.setTcpNoDelay(true);
                clientSocket.setSoTimeout(600000);
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            System.out.println("client accepted from: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
            if (clientSocket != null) {
                Thread vlakno = new Thread(new Server(clientSocket));
                vlakno.start();
            }
            if(false) break;
        }
        /*
         * vzhledem k nekonecnemu cyklu a k nesplnitelnemu
         */
        clientSocket.close();
        serverSocket.close();
     }

    /**
     * Konstruktor
     * @param clientSocket socket,
     */
    public Server(Socket clientSocket){
        this.client = clientSocket;
    }

    /**
     * Vytiskne a odesle zpravu.
     * @param message odesilana a vytiskavana zprava
     */
    public void printAndSendMessage(String message) {
        System.out.println(message);
        out.print(message + "\r\n");
        out.flush();
    }
/**
 * Odesle zpravu OK s mnozstvim Energie a pozici (x,y).
 */
    public void printAndSendOk(){
        System.out.println("250 OK ("+simulator.getEnergy() + ","+ simulator.getLocation()+")");
        out.print("250 OK ("+simulator.getEnergy() + ","+ simulator.getLocation()+")\r\n");
        out.flush();
    }
/**
 * Nacitani radku
 * @return String nactena zprava
 */
    public String readLn() {
        String incomingMessage = "";
             //cislo precteneho znaku
            int nummerOfChar = -1;                       

            //boolean znaci, jestli se nalezlo \r
            boolean isRFound = false;                          
            while (true) {
                try {
                    nummerOfChar = in.read();
                } catch (Exception e) {
                    System.out.println(e);
                    return "";
                }
                if (nummerOfChar == -1) {
                    break;
                }
                if (nummerOfChar == (int) '\r') {                  
                    isRFound = true;
                } else if (nummerOfChar == (int) ('\n') && isRFound) {    //nasleduje-li \n
                    break;
                } else {
                    incomingMessage = incomingMessage + ((char) nummerOfChar);
                    isRFound = false;
                    if (incomingMessage.length() > 512) {
                        incomingMessage = "";
                    }
                }
            }
        return incomingMessage;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        simulator = new RobotSimulator();

        printAndSendMessage("220 Dokazal jsi se pripojit, peklo zacalo. Oslovuj mne " + nick + ".");

        while (true) {
            /*               */
            try {
                message = readLn();
            } catch (Exception exc) {
                System.out.println(exc);
                continue;
            }
            if (message.equals("")) {
                printAndSendMessage("500 NEZNAMY PRIKAZ");
                continue;
            }
            System.out.println(message);
            if ((message.equals(nick + " VLEVO"))) {
                try {
                    simulator.turnLeft();
                    printAndSendOk();
                } catch (RobotExhaustedException ex) {
                    printAndSendMessage("540 BATERIE PRAZDNA ");
                    break;
                }
            } else if (message.equals(nick + " KROK")) {
                try {
                    try {
                        simulator.step();
                    } catch (RobotExhaustedException ex) {
                        printAndSendMessage("540 BATERIE PRAZDNA");
                        break;
                    }
                } catch (RobotDestroyedException e) {
                    printAndSendMessage("572 ROBOT SE ROZPADL");
                    break;
                } catch (RobotBrokenException e) {
                    printAndSendMessage("570 PORUCHA BLOK " + e.brokenBlock);
                    continue;
                }
                if (simulator.isInsideTheCity()) {
                    printAndSendOk();
                } else {
                    printAndSendMessage("530 HAVARIE");
                    break;
                }
            } else if ((message.equals(nick + " ZVEDNI"))) {
                if (simulator.isAtZeroZero()) {   // pokud je na pocatku, vyzvedne tajemstvi
                    printAndSendMessage("221 USPECH Vsichni milujeme progtest...");
                    break;
                } else {
                    printAndSendMessage("550 NELZE ZVEDNOUT ZNACKU");
                    break;
                }
            } else {
                if (message.equals(nick + " OPRAVIT 1") || message.equals(nick + " OPRAVIT 2") || message.equals(nick + " OPRAVIT 3") || message.equals(nick + " OPRAVIT 4") ||
                        message.equals(nick + " OPRAVIT 5") || message.equals(nick + " OPRAVIT 6") || message.equals(nick + " OPRAVIT 7") || message.equals(nick + " OPRAVIT 8") || message.equals(nick + " OPRAVIT 9")) {
                    if (simulator.getBrokenBlock() == (int) message.charAt(message.length() - 1) - (int) '0') {
                        printAndSendOk();
                        simulator.repair(simulator.getBrokenBlock());
                    } else {
                        printAndSendMessage("571 NENI PORUCHA");
                        break;
                    }
                } else {
                    if(message.equals(nick + " NABIT")){
                        try {
                            simulator.reacharge();
                        } catch (RobotBrokenException ex) {
                         printAndSendMessage("570 PORUCHA BLOK " + ex.brokenBlock);
                          continue;
                        } catch (RobotDestroyedException ex) {
                           printAndSendMessage("572 ROBOT SE ROZPADL");
                           break;
                        }
                        printAndSendOk();
                    } else{
                        System.out.println(message);
                        printAndSendMessage("500 NEZNAMY PRIKAZ");

                    }
                }
            }
        }
        out.close();
        try {
            in.close();
        } catch (IOException ex) {
        }
    }
}

/**
 * Exception vyvolana rozpadnutim robota
 * @author woxie
 */
class RobotDestroyedException extends Exception {

    public RobotDestroyedException() {
    }
}
/**
 * Exception vyvolana rozbitim robota
 * @author woxie
 */
class RobotBrokenException extends Exception {

    int brokenBlock;

    public RobotBrokenException(int block) {
        brokenBlock = block;
    }
}
/**
 * Exception vyvolana vybitim robota
 * @author woxie
 */
class RobotExhaustedException extends Exception{
}


 
/**
 * Trida reprezentuje robota, ktery chodi po meste.
 * @author woxie
 */
class RobotSimulator {
    /**
     * X-ova pozice robota
     */
    private int x;
    /**
     * Y-ova pozice robota
     */
    private int y;
    /**
     * Smer natoceni robota
     */
    private int dir;
    /**
     * pocet kroku, ktere robot vykonal
     */
    private int count = 0;
    /**
     * Flag znacici, jestli je robot znicen
     */
    private boolean broken = false;
    /**
     * Cislo naposledy porouchaneho bloku
     */
    private int brokenBlock = 0;
    /**
     * Robotova energie.
     */
    private int energy;

    /**
     * Konstruktor vytvori robota na nahodne pozici s energii 100.
     */
    public RobotSimulator() {
        Random r = new Random();             //random location
        x = r.nextInt(33) - 16;
        y = r.nextInt(33) - 16;
        dir = r.nextInt(4);
        energy = 100;
    }

    /**
     * Ziska robotovu pozici jako String
     * @return String znacici robotovu pozici
     */
    public String getLocation() {                       //vrati umisteni robota
        return x + "," + y;
    }

    /**
     * Otoci robota vlevo.
     */
    public void turnLeft() throws RobotExhaustedException {
        energy-=10;
        if(isExhausted())throw new RobotExhaustedException();
        dir = ((dir + 1) % 4);
    }
    /**
     * Opravi robotovi blok.
     * @param repairedBlock Opravovany blok
     */
    public void repair(int repairedBlock) {
        broken = false;
    }
/**
 * Udela krok. Vyhodi vyjimku , pokud je robot znicen ( = pokud se rozpadl ) ci je porouchan.
 * @throws RobotDestroyedException vyjimka znaci, ze se robot rozpadl
 * @throws RobotBrokenException vyjimka znaci, ze se robot porouchal
 */
    public void step() throws RobotDestroyedException, RobotBrokenException, RobotExhaustedException {
        Random rand = new Random();
        if (broken) {
            throw new RobotDestroyedException();
        }
        int random = rand.nextInt(10);
        if (count == 10 || random == 5) {
            broken = true;
            brokenBlock = rand.nextInt(9) + 1;
            count = 0;
            throw new RobotBrokenException(brokenBlock);
        } else {
            energy-=10;
            if(isExhausted()) throw new RobotExhaustedException();
            count++;
            switch (dir) {
                case 0:
                    y++;
                    break;
                case 1:
                    x--;
                    break;
                case 2:
                    y--;
                    break;
                case 3:
                    x++;
                    break;
            }
        }
    }

    /**
     * Vrati blok, ktery se porouchal.
     * @return porouchany blok
     */
    public int getBrokenBlock() {
        return brokenBlock;
    }

    /**
     * Vrati hodnotu true, pokud je robot na pozici (0,0) jinak false
     * @return true, pokud je robot na pocatku, jinak false
     */
    public boolean isAtZeroZero() {
        if (y == 0 && x == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Vrati hodnotu true, pokud je robot v meste, jinak false.
     * @return true, pokud je robot uvnitr mesta, jinak false
     */
    public boolean isInsideTheCity() {
        if (y > 17 || x > 17 || y < -17 || x < -17) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Vrati robotovi energii
     * @return robotova energie
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * Vrati true, pokud je robot bez stavy, jinak false
     * @return true, pokud je robot bez energie, jinak false
     */
    public boolean isExhausted(){
        if(energy<=0)return true;
        return false;
    }

    /**
     * Znovu nabije robota.
     * @throws RobotBrokenException vyjimka vyhozena, pokud se robot poroucha pri nabijeni
     * @throws RobotDestroyedException vyjimka vyhozena, pokud je robot jiz porouchan a mel by se nabit.
     */
    public void reacharge() throws RobotBrokenException, RobotDestroyedException{
        if(broken) throw new RobotDestroyedException();
        Random r = new Random();
        int rand = r.nextInt(3);
        if(rand == 2) {
            energy = 1;
            broken = true;
            brokenBlock = r.nextInt(9) + 1;
            throw new RobotBrokenException(brokenBlock);
        }
        energy = 100;
    }
}
