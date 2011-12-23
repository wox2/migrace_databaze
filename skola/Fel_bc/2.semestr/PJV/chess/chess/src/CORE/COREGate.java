/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

/**
 *
 * @author sprinkler
 */
public class COREGate {

    static private Data data = null;
    static private PlayerData playerData = null;
    private Rules rules = new Rules();

    public static Data getData() throws DataException {
        if (data == null){
            throw new DataException("Data is empty");
        }
        return data;
    }

    public static PlayerData getPlayerData() throws PlayerDataException{
        if (data == null){
            throw new PlayerDataException("Data is empty");
        }
        return playerData;
    }

    public void startNewGame(String playerColor) {
        try {
            data = new Data();
            playerData = new PlayerData(playerColor);
            rules.standartPosition();
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    public void joinGame(String playerColor) {
        try {
            data = new Data();
            playerData = new PlayerData(playerColor);
            rules.standartPosition();
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    public void updateData(Data OponentsData) {
        data = new Data(OponentsData);
    }

    public boolean setMove(int[] position) {
       return rules.setMove(position);
    }

    public String getWarning(String color){
        if (rules.isKingInCheck(color)){
            return "Šach";
        }else if (rules.isKingInCheckmate(color)){
            return "Šach mat";
        }else if (rules.isStalemate(color)){
            return "Pat";
        }
        return null;
    }

    public void changePawnForFigure(String figureName){
        rules.changePawnForFigure(figureName);
    }

    public void oponentEndGame(){
        data = null;
        playerData = null;
    }
}
