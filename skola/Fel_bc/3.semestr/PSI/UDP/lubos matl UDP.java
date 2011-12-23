/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 *
 * @author Sprinkler
 */
 class psitp3 {

    /**
     * Precte parametry v prikazu a podle toho rozhodne jaky konstruktor tridy network zavolat
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("≈†patne parametry");
            System.exit(1);
        }
        String host = args[0];
        int port = 0;
        try {
            port = Integer.parseInt(args[1]);
        } catch (Exception ex) {
            System.out.println("≈†patne parametry");
            System.exit(1);
        }
        String order = args[2];
        if (args.length > 4) {
            String inFile = args[3];
            String outFile = args[4];
            new Network(host, port, order, inFile, outFile);
        } else {
            new Network(host, port, order);
        }
    }
}
class Network {

    private int seq;
    public int port;
    private String inFile = null;
    private String outFile = null;
    public InetAddress address;
    private DatagramSocket socket;
    Thread acceptInCycle;
    Thread sendInCycle;
    SendPacket sendPacket;
    private OrderInterface order;
    private AcceptData acceptData;
    private SendData sendData;

    /**
     * Konstruktor tridy network nastava adresu, port, a prikaz, vytvory spojeni
     * @param host
     * @param port
     * @param order
     */
    public Network(String host, int port, String order) {
        this.port = port;
        setAddress(host);
        createSocet();
        setOrder(order);
        acceptInCycle = new Thread(new AcceptInCycle(socket, sendPacket, this));
        acceptInCycle.start();
        sendPacket.send();
        try {
            acceptInCycle.join();
        } catch (InterruptedException ex) {
            System.out.println("Error cekani na vlakno");
        }
        this.order.readData(acceptData);
    }

    /**
     * Konstruktor tridy network nastavi adresu, port, soubory, a prikaz, vytvori spojeni
     * @param host
     * @param port
     * @param order
     */
    public Network(String host, int port, String order, String inFileName, String outFileName) {
        this.port = port;
        this.inFile = inFileName;
        this.outFile = outFileName;
        setAddress(host);
        createSocet();
        setOrder(order);
        acceptInCycle = new Thread(new AcceptInCycle(socket, sendPacket, this));
        acceptInCycle.start();
        sendPacket.send();
        try {
            acceptInCycle.join();
        } catch (InterruptedException ex) {
            System.out.println("Error cekani na vlakno");
        }
        this.order.readData(acceptData);
    }

    /**
     * Ve chvili kdy je prijata prvni zprava od serveru, naplni se data a spusti se odesilani
     * @param ack
     */
    public void startSend(int ack) {
        acceptData = new AcceptData(ack);
        seq++;
        order.fillData(seq);
        sendInCycle = new Thread(new SendInCycle(sendPacket, sendData));
        sendInCycle.start();
    }

    /**
     * Zjisti adresu z textoveho vstupu, pokud neexistuje ukonci spojeni
     * @param host
     */
    private void setAddress(String host) {
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException ex) {
            System.out.println("Nelze najit server");
            System.exit(0);
        }
    }

    /**
     * Vytvori nahodne seq, vytvori soket
     */
    private void createSocet() {
        try {
            Random random = new Random();
            seq = random.nextInt((int) Math.pow(2, 15));
            socket = new DatagramSocket();
            sendPacket = new SendPacket(socket, address, port, seq);
            sendData = new SendData(seq);
        } catch (SocketException ex) {
            System.out.println("Nelze vytvorit soket");
            System.exit(1);
        }
    }

    /**
     * Zjisti ze vstupu jaky prikaz se ma pouzit
     * @param textOrder
     */
    private void setOrder(String textOrder) {
        textOrder = textOrder.toLowerCase();
        if (textOrder.equals("ping")) {
            order = new PingOrder(sendData);
        } else if (textOrder.equals("list")) {
            order = new ListOrder(sendData);
        } else if (textOrder.equals("get")) {
            if (inFile == null) {
                System.out.println("r†patn√Ω vstupni soubor");
                System.exit(1);
            }
            if (outFile == null) {
                System.out.println("r†patn√Ω v√Ωsupni soubor");
                System.exit(1);
            }
            order = new GetOrder(sendData, inFile, outFile);
        } else if (textOrder.equals("push")) {
            if (inFile == null) {
                System.out.println("r†patn√Ω vstupni soubor");
                System.exit(1);
            }
            if (outFile == null) {
                System.out.println("r†patn√Ω v√Ωsupni soubor");
                System.exit(1);
            }
            order = new PushOrder(sendData, inFile, outFile);
        } else {
            System.out.println("Neznam√Ω prikaz");
            System.exit(1);
        }
    }

    /**
     * Odstrani paket z odesilaciho okenka pokud je prijato potvrzeni
     * @param ack
     */
    public void removeSendPackets(int ack) {
        sendData.remove(ack);
        if (sendData.isEmpty()) {
            sendPacket.setFlag(2);
        }
    }

    /**
     * Zjisti zda jsou odesilaci data prazdna
     * @return
     */
    public boolean isEmptySendData() {
        return sendData.isEmpty();
    }

    /**
     * Getter ktera vrati tridu s prijatymi daty
     * @return
     */
    public AcceptData getAcceptData() {
        return acceptData;
    }
}

interface OrderInterface {

    public void fillData(int seq);

    public void readData(AcceptData acceptData);
}

interface InteratorInterface {

    public boolean isDone();

    public void setNext();

    public int getSeq();

    public byte[] getData();
}

class AcceptData {

    Map<Integer, byte[]> data = new TreeMap<Integer, byte[]>();
    int seq;
    int lastAck;

    /**
     * Konstruktor pro prijata data, nastavi seq a posledni prijate potvrzeni
     * @param seq
     */
    public AcceptData(int seq) {
        this.seq = seq;
        this.lastAck = seq;
    }

    /**
     * Prida nova data, pokud jiz neexistuji
     * @param seq
     * @param data
     * @return
     */
    public boolean add(int seq, byte[] data) {
        if (data.length > 256) {
            return false;
        }
        if (seq < getLastAck() && !this.data.containsKey(seq)) {
            seq += 65536;
        }
        if (this.seq == 0) {
            this.seq = seq;
            this.data.put(seq, data);
            return true;
        }
        if (!this.data.containsKey(seq)) {
            this.data.put(seq, data);
            return true;
        }
        return false;
    }

    /**
     * Zjisti posledni data, ktere muze potvrdit
     * @return
     */
    public int getLastAck() {
        if (data.size() == 0) {
            return seq;
        }
        while (data.containsKey(lastAck)) {
            lastAck += data.get(lastAck).length;
        }
        return lastAck;
    }

    /**
     * Vraci vnitrni tridu interator, ktera slouzi k prochazeni mapy
     * @return
     */
    public InteratorInterface getInterator() {
        return new Interator();
    }

    private class Interator implements InteratorInterface {

        int localSeq = seq;

        /**
         * Zjisti zda jsme prosli urs vsechna data
         * @return
         */
        public boolean isDone() {
            return !data.containsKey(localSeq);
        }

        /**
         * Posune data o jedno dopredu
         */
        public void setNext() {
            localSeq += data.get(localSeq).length;
        }

        /**
         * Vrati aktualni seq na ktera ukazuje ukazatel
         * @return
         */
        public int getSeq() {
            return localSeq;
        }

        /**
         * Vrati data na ktara ukazuje ukazatel
         * @return
         */
        public byte[] getData() {
            return data.get(localSeq);
        }
    }
}

class SendData {

    private Map<Integer, byte[]> data = new TreeMap<Integer, byte[]>();
    int seq;

    /**
     * Konstuktor pro odesilana data, nastavi seq
     * @param seq
     */
    public SendData(int seq) {
        this.seq = seq + 1;
    }

    /**
     * Prida data do odesilanych dat, data se musi pridavat poporade
     * @param seq
     * @param data
     * @return
     */
    public boolean add(int seq, byte[] data) {
        if (data.length > 256) {
            return false;
        }
        byte[] saveData = new byte[data.length];
        System.arraycopy(data, 0, saveData, 0, data.length);
        if (this.data.size() == 0) {
            if (seq == this.seq) {
                this.data.put(seq, saveData);
                return true;
            }
            return false;
        }
        int key = this.seq;
        if (this.data.containsKey(seq)) {
            return false;
        }
        while (this.data.containsKey(key)) {
            if (key + this.data.get(key).length == seq) {
                this.data.put(seq, saveData);
                return true;
            }
            key += this.data.get(key).length;
        }
        return false;
    }

    /**
     * Zjisti zda jsou data prazdna
     * @return
     */
    public boolean isEmpty() {
        return data.size() == 0;
    }

    /**
     * Odstrani data z okenka pokud jsou potvrzena
     * @param seq
     * @return
     */
    public boolean remove(int seq) {
        if (seq < this.seq) {
            seq += 65536;
        }
        int key = this.seq;
        boolean exist = false;
        while (this.data.containsKey(key)) {
            if (key + data.get(key).length == seq) {
                exist = true;
                break;
            }
            key += data.get(key).length;
        }

        if (exist) {
            int key2 = this.seq;
            while (this.data.containsKey(key2)) {
                if (key2 > key) {
                    break;
                }
                System.out.println("remove:" + key2);
                int dataLength = data.get(key2).length;
                data.remove(key2);
                key2 += dataLength;
            }
            this.seq = key2;
            return true;
        }
        return false;
    }

    /**
     * Vraci vnitrni tridu pro prochazeni odesilanych dat
     * @return
     */
    public InteratorInterface getInterator() {
        return new Interator();
    }

    private class Interator implements InteratorInterface {

        int localSeq = seq;
        Map<Integer, byte[]> localData = new TreeMap<Integer, byte[]>(data);

        /**
         * Zjisti zda je morzne posunout ukazatel dale, neni to moræne pokud posuneme ukazatel o vice nez 2048 nebo jsou data prazdne
         * @return
         */
        public boolean isDone() {
            if (!data.containsKey(localSeq)) {
                return true;
            }
            if (localSeq - seq + localData.get(localSeq).length > 2048) {
                return true;
            }
            return false;
        }

        /**
         * Posune ukazatel o jedna dopredu
         */
        public void setNext() {
            localSeq += localData.get(localSeq).length;
        }

        /**
         * Vrati seq na kterou ukazuje ukazatel
         * @return
         */
        public int getSeq() {
            return localSeq;
        }

        /**
         * Vrati data na ktere ukazuje ukazatel
         * @return
         */
        public byte[] getData() {
            return localData.get(localSeq);
        }
    }
}

class SendPacket {

    int seq = 0;
    int ack = 0;
    int flag = 1;
    int id = 0;
    byte[] data = new byte[0];
    DatagramSocket socket;
    InetAddress address;
    int port;

    /**
     * Konstruktor pro tridu ktera slouzi k odesilani paketru, je pouze jedna, spolecna pro vsechny vlakna a je synchronizovana
     * @param socket
     * @param address
     * @param port
     * @param seq
     */
    public SendPacket(DatagramSocket socket, InetAddress address, int port, int seq) {
        this.socket = socket;
        this.address = address;
        this.port = port;
        this.seq = seq;
    }

    /**
     * Setter nastavi ack
     * @param ack
     */
    synchronized public void setAck(int ack) {
        this.ack = ack;
    }

    /**
     * Setter nastafi flagy
     * @param flag
     */
    synchronized public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * Setter nastavi id (volan pouze jednou)
     * @param id
     */
    synchronized public void setId(int id) {
        flag = 0;
        this.id = id;
    }

    /**
     * setter nastavi seq
     * @param seq
     */
    synchronized public void setSeq(int seq) {
        this.seq = seq;
    }

    /**
     * Odesila data
     * @param data
     */
    synchronized public void send(byte[] data) {
        MyPacket packet = new MyPacket(id, seq, ack, flag, data);
        sendPacket(packet);
    }

    /**
     * Odesila potvrzeni
     * @param data
     */
    synchronized public void send(int ack) {
        MyPacket packet = new MyPacket(id, seq + data.length, ack, flag);
        sendPacket(packet);
    }

    /**
     * Odesila potvrzeni
     * @param data
     */
    synchronized public void send() {
        MyPacket packet = new MyPacket(id, seq, ack, flag);
        sendPacket(packet);
    }

    /**
     * Odesila data
     * @param data
     */
    synchronized public void send(int seq, byte[] data) {
        this.data = data;
        this.seq = seq;
        MyPacket packet = new MyPacket(id, seq, ack, flag, data);
        sendPacket(packet);
    }

    /**
     * Odesila paket
     * @param data
     */
    synchronized private void sendPacket(MyPacket packet) {
        try {
            byte[] message = packet.getPacket();
            
            System.out.println("");
            DatagramPacket datagramPacket = new DatagramPacket(message, message.length, address, port);
            socket.send(datagramPacket);
            System.out.println("Odesilam: id " + packet.getId() + " seq: " + packet.getSeq() + " ack: " + packet.getAck() + " flag: " + packet.getFlags()/* + " data: " + packet.getData()*/);
        } catch (IOException ex) {
            System.out.println("Nelze odeslat packet");
        }
    }
}

class MyPacket {

    int seq;
    int ack;
    byte flags;
    int id;
    byte[] byteData;

    /**
     * Vrati data
     * @return
     */
    public byte[] getByteData() {
        return byteData;
    }

    /**
     * Konstruktor pro tridu packet ktera usnadrnuje praci s prijatymi daty
     */
    public MyPacket(int id, int seq, int ack, int flags, byte[] data) {
        this.byteData = data;
        this.seq = seq;
        this.ack = ack;
        this.flags = (byte) flags;
        this.id = id;
    }

    /**
     * Konstruktor pro tridu packet ktera usnadrnuje praci s prijatymi daty
     */
    public MyPacket(int id, int seq, int ack, int flags) {
        this.byteData = new byte[0];
        this.seq = seq;
        this.ack = ack;
        this.flags = (byte) flags;
        this.id = id;
    }

    /**
     * Konstruktor pro tridu packet ktera usnadrnuje praci s prijatymi daty
     */
    public MyPacket(byte[] packed) {
        this.id = byteToInt(packed, 0, 4);
        this.seq = byteToInt(packed, 4, 2);
        this.ack = byteToInt(packed, 6, 2);
        this.flags = packed[8];
        byteData = new byte[packed.length - 9];
        System.arraycopy(packed, 9, byteData, 0, packed.length - 9);
    }

    /**
     * Prevede byty na integer (pro snazr°i manipulaci)
     */
    private int byteToInt(byte[] data, int start, int length) {
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

    /**
     * Prevede integer na byty
     */
    public byte[] intToByte(int num, int lenght) {
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

    /**
     * Vrati packet v bytech
     */
    public byte[] getPacket() {
        byte[] packet = new byte[9 + byteData.length];
        System.arraycopy(intToByte(id, 4), 0, packet, 0, 4);
        System.arraycopy(intToByte(seq, 2), 0, packet, 4, 2);
        System.arraycopy(intToByte(ack, 2), 0, packet, 6, 2);
        packet[8] = flags;
        System.arraycopy(byteData, 0, packet, 9, byteData.length);
        return packet;
    }

    /**
     * vrati ack
     */
    public int getAck() {
        return ack;
    }

    /**
     * vrati ack
     */
    public byte getFlags() {
        return flags;
    }

    /**
     * vrati id
     */
    public int getId() {
        return id;
    }

    /**
     * vrati seq
     */
    public int getSeq() {
        return seq;
    }

    /**
     * vrati data ve stingu
     */
    public String getData() {
        String data = new String(byteData);
        return data;
    }
}

class AcceptInCycle implements Runnable {

    DatagramSocket socket;
    SendPacket sendPacket;
    Network network;

    /**
     * Konstruktor pro vlakno ktere v cyklu prrijima data
     */
    public AcceptInCycle(DatagramSocket socket, SendPacket sendPacket, Network network) {
        this.socket = socket;
        this.sendPacket = sendPacket;
        this.network = network;
    }

    /**
     * Kdyz prijdou data s flagem = 1 spusti odesilani dat, pokud je paket prazdny je to potvrzeni, pokud ne ulozi ho, pokud prije flag ==4 ukonci se, pokud jsou odesilaci data prazdna nastavi flag na 2
     */
    public void run() {
        AcceptPacket acceptPacket = new AcceptPacket(socket);
        while (true) {
            acceptPacket.Accept();
            MyPacket packet = acceptPacket.getPacket();
            if (packet.getFlags() == 1) {
                sendPacket.setId(packet.getId());
                sendPacket.setAck(packet.getSeq() + 1);
                network.startSend(packet.getSeq() + 1);
            } else {
                network.removeSendPackets(packet.getAck());
                if (packet.getByteData().length != 0) {
                    network.getAcceptData().add(packet.getSeq(), packet.getByteData());
                    //sendPacket.send(network.getAcceptData().getLastAck());
                }
                if (packet.getFlags() == 2) {
                    sendPacket.setFlag(2);
                    sendPacket.send(network.getAcceptData().getLastAck());
                    break;
                }
                if (packet.getFlags() == 4) {
                    break;
                }
                if (network.isEmptySendData()) {
                    sendPacket.send(network.getAcceptData().getLastAck());
                }
            }
        }
    }
}

class SendInCycle implements Runnable {

    SendPacket sendPacket;
    SendData sendData;

    /**
     * Konstruktor pro odesila data v cyklu
     * @param sendPacket
     * @param sendData
     */
    public SendInCycle(SendPacket sendPacket, SendData sendData) {
        this.sendPacket = sendPacket;
        this.sendData = sendData;
    }

    /**
     * Odesila okenko dokud neni prazdne pokud je prrazdne nastavi flag trvale na 2
     */
    public void run() {
        while (!sendData.isEmpty()) {
            InteratorInterface interator = sendData.getInterator();
            while (!interator.isDone()) {
                sendPacket.send(interator.getSeq(), interator.getData());
                interator.setNext();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                System.out.println("Error odesilaciho vlakna");
                System.exit(1);
            }
        }
    }
}

class AcceptPacket {

    DatagramSocket socket;
    MyPacket packet;
    byte[] message;

    /**
     * Prijme data
     * @param socket
     */
    public AcceptPacket(DatagramSocket socket) {
        this.socket = socket;
    }

    /**
     * Prijme data a nastavi mypacket
     */
    public void Accept() {
        try {
            byte[] allAcceptMessage = new byte[265];
            DatagramPacket datagramPacket = new DatagramPacket(allAcceptMessage, allAcceptMessage.length);
            socket.receive(datagramPacket);
            message = new byte[datagramPacket.getLength()];
            System.arraycopy(allAcceptMessage, 0, message, 0, datagramPacket.getLength());
            packet = new MyPacket(message);
            System.out.println("Prijimam: id " + packet.getId() + " seq: " + packet.getSeq() + " ack: " + packet.getAck() + " flag: " + packet.getFlags()/* + " data: " + packet.getData()*/);
        } catch (IOException ex) {
                System.out.println("Nelze prijmout packet");
        }
    }

    /**
     * Gettre vrati my packet
     * @return
     */
    public MyPacket getPacket() {
        return packet;
    }
}

class GetOrder implements OrderInterface {

    SendData sendData;
    String inFileName;
    String outFileName;

    /**
     * Prikaz get
     * @param sendData
     * @param inFileName
     * @param outFileName
     */
    public GetOrder(SendData sendData, String inFileName, String outFileName) {
        this.sendData = sendData;
        this.inFileName = inFileName;
        this.outFileName = outFileName;
    }

    /**
     * Vlozi do odeslanych dat prikaz get
     * @param seq
     */
    public void fillData(int seq) {
        String get = "GET" + inFileName;
        sendData.add(seq, get.getBytes());
    }

    /**
     * Ulozi soubor
     * @param acceptData
     */
    public void readData(AcceptData acceptData) {
        new ReadAcceptData(acceptData).saveAcceptData(outFileName);
    }
}

class ListOrder implements OrderInterface {

    SendData sendData;

    /**
     * Prikaz list
     * @param sendData
     */
    public ListOrder(SendData sendData) {
        this.sendData = sendData;
    }

    /**
     * Vlozi do odeslanych dat prikaz list
     * @param seq
     */
    public void fillData(int seq) {
        String ping = "LIST";
        sendData.add(seq, ping.getBytes());
    }

    /**
     * vypise data
     * @param acceptData
     */
    public void readData(AcceptData acceptData) {
        ReadAcceptData readAcceptData = new ReadAcceptData(acceptData);
        readAcceptData.printAcceptData();
    }
}

class PingOrder implements OrderInterface {

    SendData sendData;

    /**
     * Prikaz ping
     * @param sendData
     */
    public PingOrder(SendData sendData) {
        this.sendData = sendData;
    }

    /**
     * Vlozi do odeslanych dat prikaz ping
     * @param seq
     */
    public void fillData(int seq) {
        String ping = "PING";
        sendData.add(seq, ping.getBytes());
    }

    /**
     * vypise data
     * @param acceptData
     */
    public void readData(AcceptData acceptData) {
        ReadAcceptData readAcceptData = new ReadAcceptData(acceptData);
        readAcceptData.printAcceptData();
    }
}

class PushOrder implements OrderInterface {

    SendData sendData;
    String inFileName;
    String outFileName;

    /**
     * Prikaz push
     * @param sendData
     * @param inFileName
     * @param outFileName
     */
    public PushOrder(SendData sendData, String inFileName, String outFileName) {
        this.sendData = sendData;
        this.inFileName = inFileName;
        this.outFileName = outFileName;
    }

    /**
     * Projde odesila soubor a vlozi do odeslanych dat obsah souboru
     * @param seq
     */
   public void fillData(int seq) {
        DataInputStream is = null;
        try {
            String push = "PUSH" + inFileName;
            File outFile = new File(outFileName);
            is = new DataInputStream(new FileInputStream(outFile));
            byte[] data = new byte[256];
            int i = push.length() + 1;
            System.arraycopy(push.getBytes(), 0, data, 0, push.length());
            data[i - 1] = 0;
            int j = is.read(data, i, 256 - i);
            sendData.add(seq, data);
            seq += 256;
            while ((j = is.read(data)) != -1) {
                if (j == 256) {
                    sendData.add(seq, data);
                    seq += 256;
                } else {
                    byte[] smallData2 = new byte[j];
                    System.arraycopy(data, 0, smallData2, 0, j);
                    sendData.add(seq, smallData2);
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Soubor neexistuje");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Soubor nelze ulozit≠st");
            System.exit(1);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                System.out.println("Nelze otevrit soubor");
                System.exit(1);
            }
        }
    }

   
    public void readData(AcceptData acceptData) {
    }
}

class ReadAcceptData {

    AcceptData acceptData;

    /**
     * Zpracovava prijate data
     * @param acceptData
     */
    public ReadAcceptData(AcceptData acceptData) {
        this.acceptData = acceptData;
    }

    /**
     * Vypisuje prijata data
     */
    public void printAcceptData() {
        InteratorInterface interator = acceptData.getInterator();
        System.out.println("------------------------------------");
        System.out.println("Vypis dat");
        while (!interator.isDone()) {
            System.out.print(new String(interator.getData()));
            interator.setNext();
        }
        System.out.println("\n--------------------------------------");
    }

    /**
     * Uloklada prijata data do souboru
     * @param outFileName
     */
    public void saveAcceptData(String outFileName) {
        System.out.println("------------------------------------");
        System.out.println("Ukladam");
        DataOutputStream os = null;
        try {
            os = new DataOutputStream(new FileOutputStream(outFileName));
            InteratorInterface interator = acceptData.getInterator();
            while (!interator.isDone()) {
                os.write(interator.getData());
                interator.setNext();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Soubor neexistuje");
        } catch (IOException ex) {
            System.out.println("Do souboru nelze zapsat");
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                System.out.println("Soubor nelze vytvorit");
            }
        }
    }
}
