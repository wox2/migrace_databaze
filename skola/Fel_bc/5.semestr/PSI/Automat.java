/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Zdroje: http://java.sun.com/javase/6/docs/api/
 * @author woxie
 */
public class Automat {

    static final int FIN_FLAG = 2;
    static final int SYN_FLAG = 1;
    static final int RST_FLAG = 4;
    static final int SEND_SYN_AND_SET_ID_STATE = 1;
    static final int RECEIVE_STATE = 5;
    static final int MESSAGE_NOT_IN_WIN_STATE = 6;
    static final int MESSAGE_IN_WIN_STATE = 7;
    static final int SEND_GET_STATE = 8;
    static final int WINDOW_SIZE = 2048;
    static final int TIMEOUT_DEFAULT_TIME = 200;
    static final int TWO_BYTES_SIZE = 256 * 256;
    private int port;
    private InetAddress inetAddress;
    private DatagramSocket socket;
    private String localFile;
    private String transferedFile;
    private int ack;
    private int id;
    private int seq;
    private int flag;
    private int state;
    private int windowHigherBound;
    private int windowLowerBound;
    private int windowBeggining;
    private byte[] lastAcceptedData;
    private Map<Integer, byte[]> acceptedDataFromServer;
    private boolean connectionClosed;
    private PrintStream logger;

    public static void main(String args[]) {
        if (args.length != 4) {
            System.out.println("Pocet parametru:" + args.length + ". Chyba: Program psitp4 muze byt pouzit volanim java -jar psitp4.jar <host> <port> <vzdaleny_soubor> <lokalni_soubor>");
            System.exit(1);
        }
        String host;
        int port;
        String remoteTransferedFile;
        String localFile;

        host = args[0];
        port = Integer.parseInt(args[1]);
        remoteTransferedFile = args[2];
        localFile = args[3];

        Automat aut = new Automat(port, host, remoteTransferedFile, localFile);
        aut.startSending();

    }

    public Automat(int port, String address, String transferedRemoteFile, String localSavedFile) {
        try {
            this.port = port;
            this.inetAddress = InetAddress.getByName(address);
            generateSeq();
            createSocket();
            flag = 0;
            localFile = localSavedFile;
            connectionClosed = false;
            acceptedDataFromServer = new TreeMap<Integer, byte[]>();
            transferedFile = transferedRemoteFile;
            setState(SEND_SYN_AND_SET_ID_STATE);
            windowHigherBound = 0;
            windowLowerBound = 0;
            logger = new PrintStream(new File("log.txt"));
            windowBeggining = -1;
        } catch (FileNotFoundException ex) {
            System.out.println("Log nemuze byt vytvoren");
        } catch (UnknownHostException ex) {
            println("Neznamy host.");
            System.exit(1);
        }
    }

    public void startSending() {

        while (!connectionClosed) {
            switch (state) {
                case SEND_SYN_AND_SET_ID_STATE: {
                    sendSynAndSetAtributes();
                    if (id != 0) {
                        setState(SEND_GET_STATE);
                        actualizeWindow();
                    }
                    break;
                }

                case SEND_GET_STATE: {
                    sendGet();
                    setState(RECEIVE_STATE);
                    break;
                }

                case RECEIVE_STATE: {
                   println("RECV_STATE");
                    try {
                        lastAcceptedData = receiveAndPrintMessage();
                        if (isInWindow()) {
                            setState(MESSAGE_IN_WIN_STATE);
                        } else {
                            setState(MESSAGE_NOT_IN_WIN_STATE);
                        }
                        checkFLAGS();
                    } catch (RSTFlagException ex) {
                        sendRST();
                        println("EXIT_RST");
                        connectionClosed = true;
                    } catch (WrongIDException ex) {
                        //sendRST();
                    } catch (FINFlagException ex) {
                        println("closed");
                        connectionClosed = true;
                        closeConnection();
                    } catch (SocketTimeoutException ex) {
                        sendAck();
                    } catch (SocketException ex) {
                        println("SocketException");
                        System.exit(1);
                    } catch (IOException ex) {
                        println("IOException");
                        System.exit(1);
                    }
                    break;
                }

                case MESSAGE_NOT_IN_WIN_STATE: {
                    println("Not In WIN");
                    sendAck();
                    setState(RECEIVE_STATE);
                    break;
                }

                case MESSAGE_IN_WIN_STATE: {
                    println("IN WIN");
                    int key = getSeqFromMessage(lastAcceptedData);
                    if(acceptedDataFromServer.containsKey(key))System.out.println("ERROR");
                    if (lastAcceptedData.length > 9 && !(acceptedDataFromServer.containsKey(key))) {
                        byte[] savedData = new byte[lastAcceptedData.length];
                        System.arraycopy(lastAcceptedData, 0, savedData, 0, savedData.length);
                        println("zapisuju data s klicem:" + key);
                        acceptedDataFromServer.put(key, lastAcceptedData);
                        windowBeggining = (windowBeggining == -1 ? key : windowBeggining);
                    }

                    actualizeWindow();
                    //sendAck();
                    setState(RECEIVE_STATE);
                }
            }
        }
        try {
            saveData();
        } catch (IOException ex) {
            println("IOException pri ukladani dat");
        }
    }

    private void setState(int newState) {
        state = newState;
    }

    private static int byteToInt(byte[] data, int start, int length) {
        int num = 0;
        for (int i = 0; i < length; i++) {
            int oneNum = data[i + start];
            if (oneNum < 0) {
                oneNum += 256;
            }
            num = num * 256 + oneNum;
        }
        return num;
    }

    public static byte[] intToByte(int num, int lenght) {
        if (num > Math.pow(256, lenght)) {
            num = num - (int) Math.pow(256, lenght);
        }
        byte[] intInByte = new byte[lenght];
        for (int i = 0; i < intInByte.length; i++) {
            intInByte[i] = (byte) (num / Math.pow(256, lenght - 1 - i));
            int helper = intInByte[i];
            if (helper < 0) {
                helper += 256;
            }
            num -= helper * (int) Math.pow(256, lenght - 1 - i);
        }
        return intInByte;
    }

    private void generateSeq() {
        Random rand = new Random();
        seq = rand.nextInt(256 * 4);
    }

    private void createSocket() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            println("Nelze vytvorit socket");
            System.exit(1);
        }
    }

    private boolean sendSynAndSetAtributes() {
        try {
            flag = 1;
            byte[] sendData = createPacketBytes(null);
            sendAndPrintData(sendData);
            byte[] receivedData = receiveAndPrintMessage();

            id = getIdFromMessage(receivedData);

            ack = increaseByte(getSeqFromMessage(receivedData));
            seq = getAckFromMessage(receivedData);
            flag = 0;
            windowLowerBound = ack;
            windowHigherBound = ack+ WINDOW_SIZE;

        } catch (SocketException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    private byte[] receiveAndPrintMessage() throws SocketTimeoutException, SocketException, IOException {

        byte[] acceptedMessage = new byte[265];
        DatagramPacket packet = new DatagramPacket(acceptedMessage, acceptedMessage.length, inetAddress, port);
        socket.setSoTimeout(TIMEOUT_DEFAULT_TIME/2);
        socket.receive(packet);
        int length = packet.getLength();
        lastAcceptedData = new byte[length];
        System.arraycopy(acceptedMessage, 0, lastAcceptedData, 0, length);
        print("Prijal jsem:");
        printData(lastAcceptedData);

        return lastAcceptedData;
    }

    private void printData(byte[] message) {

        id = getIdFromMessage(message);
        print("ID:" + getHexaRepresentation(id) + "  ");
        print("Seq:" + getSeqFromMessage(message) + "  ");
        print("Ack:" + getAckFromMessage(message) + "  ");
        print("Flag:" + getFlagFromMessage(message) + "  ");

        print("DATA(" + (message.length - 9) + "):");
        if (message.length > 9) {
            for (int i = 9; i < message.length; i++) {
                print("" + (char) message[i]);
            }
        }
        println("");

    }

    private static String getHexaRepresentation(int value) {
        String s = "", temp = "";
        char appendedChar = ' ';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                switch (value % 16) {
                    case 0: {
                        appendedChar = '0';
                        break;
                    }
                    case 1: {
                        appendedChar = '1';
                        break;
                    }
                    case 2: {
                        appendedChar = '2';
                        break;
                    }
                    case 3: {
                        appendedChar = '3';
                        break;
                    }
                    case 4: {
                        appendedChar = '4';
                        break;
                    }
                    case 5: {
                        appendedChar = '5';
                        break;
                    }
                    case 6: {
                        appendedChar = '6';
                        break;
                    }
                    case 7: {
                        appendedChar = '7';
                        break;
                    }
                    case 8: {
                        appendedChar = '8';
                        break;
                    }
                    case 9: {
                        appendedChar = '9';
                        break;
                    }
                    case 10: {
                        appendedChar = 'A';
                        break;
                    }
                    case 11: {
                        appendedChar = 'B';
                        break;
                    }
                    case 12: {
                        appendedChar = 'C';
                        break;
                    }
                    case 13: {
                        appendedChar = 'D';
                        break;
                    }
                    case 14: {
                        appendedChar = 'E';
                        break;
                    }
                    case 15: {
                        appendedChar = 'F';
                        break;
                    }
                }
                temp = ("" + appendedChar).concat(temp);
                value = value / 16;
            }
            s = s.concat(temp);
            temp = "";
        }
        return s;
    }

    private int getIdFromMessage(byte[] message) {
        return byteToInt(message, 0, 4);
    }

    private int getSeqFromMessage(byte[] message) {
        return byteToInt(message, 4, 2);
    }

    private int getAckFromMessage(byte[] message) {
        return byteToInt(message, 6, 2);
    }

    private int getFlagFromMessage(byte[] message) {
        return byteToInt(message, 8, 1);
    }

    public void sendAck() {
        byte[] ackData = new byte[9];

        ackData[0] = intToByte(id, 4)[0];
        ackData[1] = intToByte(id, 4)[1];
        ackData[2] = intToByte(id, 4)[2];
        ackData[3] = intToByte(id, 4)[3];

        ackData[4] = intToByte(seq, 2)[0];
        ackData[5] = intToByte(seq, 2)[1];

        ackData[6] = intToByte(ack, 2)[0];
        ackData[7] = intToByte(ack, 2)[1];

        ackData[8] = intToByte(flag, 1)[0];

        sendAndPrintData(ackData);
    }

    public boolean isInWindow() {
        int seqFromData = getSeqFromMessage(lastAcceptedData);
        if ((seqFromData >= windowLowerBound
                && seqFromData <= windowHigherBound) || (windowLowerBound >= windowHigherBound
                && (windowHigherBound >= seqFromData || windowLowerBound <= seqFromData))) {
            return true;
        }
        return false;
    }

    public boolean checkFLAGS() throws RSTFlagException, WrongIDException, FINFlagException {
        if (getIdFromMessage(lastAcceptedData) != id) {
            println("WRONG_ID");
            throw new WrongIDException();
        }
        if (getFlagFromMessage(lastAcceptedData) == RST_FLAG) {
            println("RST");
            throw new RSTFlagException();
        }
        if (getFlagFromMessage(lastAcceptedData) == FIN_FLAG) {
            println("FIN");
            throw new FINFlagException();
        }
        return true;
    }

    public void sendGet() {
        String getFilenameString = ("GET" + transferedFile);
        byte[] getFilenameBytes = createPacketBytes(getFilenameString.getBytes());
        boolean doAction = true;
        while (doAction) {
            try {
                sendAndPrintData(getFilenameBytes);
                receiveAndPrintMessage();
                doAction = false;
                flag = FIN_FLAG;
            } catch (SocketTimeoutException ex) {
                println("Timeout pri GETu");
            } catch (SocketException ex) {
                Logger.getLogger(Automat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Automat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        seq = addXtoByte(seq, getFilenameString.length());
        sendAck();
    }

    public void saveData() throws IOException {
        try {
            println("=====================================");
            FileOutputStream os = new FileOutputStream(new File(localFile));
            
            byte[] data;
            int key= windowBeggining;
            for(;;){
                data = acceptedDataFromServer.get(key);
                //print("key" + key+" ");
                os.write(data, 9, data.length - 9);
                key = (key+data.length-9)%TWO_BYTES_SIZE;
                if(!acceptedDataFromServer.containsKey(key)) break;
            }

            os.close();
        } catch (FileNotFoundException ex) {
            println("Nelze vytvorit soubor");
        }
    }

    public void sendAndPrintData(byte[] data) {
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
            socket.send(packet);
            print("Odesilam   :");
            printData(data);
        } catch (IOException ex) {
            println("Nepovedlo se odeslani Zpravy");
        }
    }

    public void closeConnection() {
        ack = getSeqFromMessage(lastAcceptedData);
        ack = increaseByte(ack);
        flag = FIN_FLAG;
        byte[] data = createPacketBytes(null);
        sendAndPrintData(data);
    }

    private int increaseByte(int number) {
        number = (number + 1) % TWO_BYTES_SIZE;
        return number;
    }

    private int addXtoByte(int number, int addedValue) {
        for (int i = 0 ; i < addedValue; i++) {
            number = increaseByte(number);
        }
        return number;
    }

    public byte[] createPacketBytes(byte[] data) {
        byte[] sendData = new byte[9];
        if (data != null) {
            sendData = new byte[data.length + 9];
            System.arraycopy(data, 0, sendData, 9, data.length);
        }

        sendData[0] = intToByte(id, 4)[0];
        sendData[1] = intToByte(id, 4)[1];
        sendData[2] = intToByte(id, 4)[2];
        sendData[3] = intToByte(id, 4)[3];

        sendData[4] = intToByte(seq, 2)[0];
        sendData[5] = intToByte(seq, 2)[1];

        sendData[6] = intToByte(ack, 2)[0];
        sendData[7] = intToByte(ack, 2)[1];
        sendData[8] = intToByte(flag, 1)[0];

        return sendData;
    }

    public void sendRST(){
        byte[] sendData = createPacketBytes(null);
        sendData[8]=RST_FLAG;
        sendAndPrintData(sendData);
    }

    public void actualizeWindow() {
        int help = getSeqFromMessage(lastAcceptedData);
        if (ack == help) {
            ack = addXtoByte(ack, lastAcceptedData.length - 9);
            while (acceptedDataFromServer.containsKey(ack)) {
                ack = addXtoByte(ack, acceptedDataFromServer.get(ack).length - 9);
            }

            windowLowerBound = ack;
            windowHigherBound = (ack+WINDOW_SIZE) % TWO_BYTES_SIZE;
        }
    }

    private void print(String message) {
        System.out.print(message);
        logger.print(message);
    }

    private void println(String message) {
        print(message + '\n');
    }
}

class RSTFlagException extends Exception {
}

class FINFlagException extends Exception {
}

class WrongIDException extends Exception {
}

class SYNException extends Exception {
}
