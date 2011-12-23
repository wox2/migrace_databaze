/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import CORE.DataException;
import CORE.PlayerDataException;
import GUI.GUIGate;
import CORE.COREGate;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkGate;

/**
 *
 * @author sprinkler
 */
public class Builder {

    private GUIGate GUI = new GUIGate(this);
    private COREGate CORE = new COREGate();
    private NetworkGate network = new NetworkGate();
    Thread dataServer = new Thread(new DataServerRunnable(this), "dataServer");

    public void startGui() {
        GUI.createWindow();
    }

    public void startNewGame(String playerColor) {
        network.setAsServer();
        CORE.startNewGame(playerColor);
        GUI.waitForJoinPlayer();
        if (!dataServer.isAlive()) {
            dataServer.start();
        }
    }

    public void joinGame(String ipAddress) {
        network.setAsClient();
        send("start", ipAddress);
        startDataServer();
    }

    public void startDataServer() {
        if (!dataServer.isAlive()) {
            dataServer.start();
        }
    }

    public void waitForData() {
        Object acceptData = network.acceptData();
        if (acceptData instanceof String) {
            if (((String) acceptData).equals("start")) {
                try {
                    network.sendData(COREGate.getPlayerData().getOponentColor());
                    GUI.createChessboard();
                    System.out.println("start");
                } catch (PlayerDataException ex) {
                    System.out.println(ex);
                }
            } else if (((String) acceptData).equals("end")) {
                GUI.oponentEndGame();
                CORE.oponentEndGame();
            } else {
                CORE.joinGame((String) acceptData);
                System.out.println("join");
                GUI.createChessboard();
            }
        } else {
            try {
                CORE.Data data = (CORE.Data) acceptData;
                CORE.updateData(data);
                COREGate.getPlayerData().setJustPlaying(true);
                GUI.repaintChessboard();
                createCheckWarling(COREGate.getPlayerData().getPlayerColor());
            } catch (PlayerDataException ex) {
                System.out.println(ex);
            }
        }
    }

    public void sendData() {
        try {
            GUI.repaintChessboard();
            createCheckWarling(COREGate.getPlayerData().getOponentColor());
            send(COREGate.getData());
        } catch (DataException ex) {
            System.out.println(ex);
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    public void send(Object data) {
        if (!network.sendData(data)) {
            if (GUI.isPossibleTrySendAgain()) {
                send(data);
            }
        }
    }

    public void send(Object data, String ipAdress) {
        if (!network.sendData(data, ipAdress)) {
            if (GUI.isPossibleTrySendAgain()) {
                send(data, ipAdress);
            }
        }
    }

    public void endGame() {
        if (dataServer.isAlive()) {
            send("end");
        }
        System.exit(0);
    }

    private void createCheckWarling(String color) {
        if (CORE.getWarning(color) != null) {
            GUI.createWarning(CORE.getWarning(color));
        }
    }

    public void createWarning(String text) {
        GUI.createWarning(text);
    }

    public void repaintChessboard() {
        GUI.repaintChessboard();
    }

    public void changePawnFigure() {
        GUI.createFigureforPawn();
    }

    public void setMove(int[] position) {
        try {
            if (CORE.setMove(position)) {
                sendData();
            }
            if (COREGate.getPlayerData().isTakeFigure()) {
                changePawnFigure();
            }
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    public void changeFigureForPawn(String figureName) {
        CORE.changePawnForFigure(figureName);
        sendData();
    }

    public CORE.Data getData() {
        try {
            return COREGate.getData();
        } catch (DataException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public CORE.PlayerData getPlayerData() {
        try {
            return COREGate.getPlayerData();
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
