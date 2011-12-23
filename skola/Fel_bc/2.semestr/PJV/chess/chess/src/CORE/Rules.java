/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sprinkler
 */
public class Rules {

    private void standartSecondLinePosition(String color, int secondLine) {
        try {
            Data data = COREGate.getData();
            data.add(new Rook(color), 0, secondLine);
            data.add(new Rook(color), 7, secondLine);
            data.add(new Knight(color), 1, secondLine);
            data.add(new Knight(color), 6, secondLine);
            data.add(new Bishop(color), 2, secondLine);
            data.add(new Bishop(color), 5, secondLine);
            if (COREGate.getPlayerData().getPlayerColor().equals("white")) {
                data.add(new Queen(color), 4, secondLine);
                data.add(new King(color), 3, secondLine);
            } else {
                data.add(new Queen(color), 3, secondLine);
                data.add(new King(color), 4, secondLine);
            }
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        } catch (DataException ex) {
            System.out.println(ex);
        }
    }

    private void standartFirstLinePosition(String color, int firstLine) {
        try {
            Data data = COREGate.getData();
            for (int i = 0; i < 8; i++) {
                try {
                    data.add(new Pawn(color), i, firstLine);
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        } catch (DataException ex) {
            System.out.println(ex);
        }
    }

    public void standartPosition() {
        try {
            String playerColor = COREGate.getPlayerData().getPlayerColor();
            String oponentColor = COREGate.getPlayerData().getOponentColor();
            standartFirstLinePosition(playerColor, 6);
            standartSecondLinePosition(playerColor, 7);
            standartFirstLinePosition(oponentColor, 1);
            standartSecondLinePosition(oponentColor, 0);
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    public boolean setMove(int[] position) {
        try {
            Data data = COREGate.getData();
            PlayerData playerData = COREGate.getPlayerData();
            Figure figure = data.get(position[0], position[1]);
            String color = playerData.getPlayerColor();
            if (playerData.isJustPlaying()) {
                if ((figure != null) && (figure.getColor().equals(color))) {
                    return clickOnFigure(position);
                } else if (((figure == null) || !(figure.getColor().equals(color))) && (data.get(playerData.getClickedFigure()) != null)) {
                    List<Point> movesClickedFigure = playerData.getMovesClickedFigure();
                    for (int i = 0; i < movesClickedFigure.size(); i++) {
                        if ((movesClickedFigure.get(i).x == position[0]) && (movesClickedFigure.get(i).y == position[1])) {
                            return moveWithFigure(position);
                        }
                    }
                    return nullClickedFigure();
                }
            }
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        } catch (DataException ex) {
            System.out.println(ex);
        }
        return false;
    }

    //nastavuje figurku, která byla oznaèena
    private boolean clickOnFigure(int[] position) throws DataException, PlayerDataException {

        Data data = COREGate.getData();
        PlayerData playerData = COREGate.getPlayerData();
        if ((playerData.getClickedFigure()[0] == position[0]) && (playerData.getClickedFigure()[1] == position[1])) {
            return nullClickedFigure();
        } else {
            playerData.setClickedFigure(position, data.get(position).getMove());

        }
        return false;
    }

    //sestavuje pohyb s oznaèenou figurkou
    private boolean moveWithFigure(int[] position) throws DataException, PlayerDataException  {
            Data data = COREGate.getData();
            PlayerData playerData = COREGate.getPlayerData();
            Figure clickedFigure = data.get(playerData.getClickedFigure());
            if (clickedFigure instanceof King) {
                castling(position);
                ((King) clickedFigure).setPossibleCasting(false);
            } else if (clickedFigure instanceof Rook) {
                ((Rook) clickedFigure).setPossibleCasting(false);
            }
            data.change(position);
            playerData.setClickedFigure(position, null);
            if ((clickedFigure instanceof Pawn) && (changePawnFigure(position))) {
                playerData.setTakeFigure(true);
            } else {
                playerData.nullClickedFigure();
                playerData.setJustPlaying(false);
                return true;
            }
        return false;
    }

    public boolean isCheck(int x, int y, String color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Figure figure = COREGate.getData().get(i, j);
                    if ((figure != null) && !(figure.getColor().equals(color))) {
                        List<Point> coordinate = COREGate.getData().get(i, j).getCheckMove();
                        for (int k = 0; k < coordinate.size(); k++) {
                            if ((coordinate.get(k).x == x) && (coordinate.get(k).y == y)) {
                                return true;
                            }
                        }
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        }
        return false;
    }

    public boolean isCheck(int x, int y, String color, int oldX, int oldY, int newX, int newY) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Figure figure = COREGate.getData().get(i, j);
                    if ((figure != null) && !(figure instanceof King) && !(figure.getColor().equals(color))) {
                        List<Point> coordinate = COREGate.getData().get(i, j).getCheckMove(oldX, oldY, newX, newY);
                        for (int k = 0; k < coordinate.size(); k++) {
                            if ((coordinate.get(k).x == x) && (coordinate.get(k).y == y)) {
                                return true;
                            }
                        }
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        }
        return false;
    }

    public boolean isKingInCheck(String color, int oldX, int oldY, int newX, int newY) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Figure figure = COREGate.getData().get(i, j);
                    if ((figure != null) && (figure instanceof King) && (figure.getColor().equals(color)) && (isCheck(i, j, color, oldX, oldY, newX, newY))) {
                        return true;
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        }
        return false;
    }

    public boolean isKingInCheckOrCheckmate(String color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Figure figure = COREGate.getData().get(i, j);
                    if ((figure != null) && (figure instanceof King) && (figure.getColor().equals(color)) && (isCheck(i, j, color))) {
                        return true;
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        }
        return false;
    }

    public boolean isKingInCheck(String color) {
        if (isKingInCheckOrCheckmate(color) && (isPossibleAnyMove(color))) {
            return true;
        }
        return false;
    }

    public boolean isKingInCheckmate(String color) {
        if (isKingInCheckOrCheckmate(color) && !(isPossibleAnyMove(color))) {
            return true;
        }
        return false;
    }

    private boolean castling(int[] position) {
        try {
            Data data = COREGate.getData();
            Figure clickedFigure = data.get(COREGate.getPlayerData().getClickedFigure());
            if (((King) clickedFigure).isPosssibleCasting()) {
                if ((position[0] == 1) && (position[1] == 7) && (data.get(0, 7) != null) && (data.get(0, 7) instanceof Rook) && (((Rook) data.get(0, 7)).isPosssibleCasting())) {
                    setCastling(position, new int[]{2, 7}, new int[]{0, 7});
                    return true;
                } else if ((position[0] == 6) && (position[1] == 7) && (data.get(7, 7) != null) && (data.get(7, 7) instanceof Rook) && (((Rook) data.get(7, 7)).isPosssibleCasting())) {
                    setCastling(position, new int[]{5, 7}, new int[]{7, 7});
                    return true;
                }
            }
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        } catch (DataException ex) {
            System.out.println(ex);
        }
        return false;
    }

    private void setCastling(int[] kingMove, int[] rookMove, int[] rookPosition) {
        try {
            List<Point> rookMoves = new ArrayList<Point>();
            List<Point> kingMoves = new ArrayList<Point>();
            rookMoves.add(new Point(rookMove[0], rookMove[1]));
            kingMoves.add(new Point(kingMove[0], kingMove[1]));
            PlayerData playerData = COREGate.getPlayerData();
            int[] kingPosition = playerData.getClickedFigure();
            playerData.setClickedFigure(rookPosition, rookMoves);
            COREGate.getData().change(new int[]{rookMove[0], rookMove[1]});
            playerData.setClickedFigure(kingPosition, kingMoves);
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        } catch (DataException ex) {
            System.out.println(ex);
        }
    }

    public boolean isPossibleAnyMove(String color) {
        List<Point> coordinate = new ArrayList<Point>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Figure figure = COREGate.getData().get(i, j);
                    if ((figure != null) && (figure.getColor().equals(color))) {
                        coordinate.addAll(figure.getMove());
                        System.out.println(figure + "  " + figure.getMove() + color);
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        }
        if (coordinate.size() == 0) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isStalemate(String color) {
        if (!isPossibleAnyMove(color)) {
            return true;
        }
        return false;
    }

    private boolean changePawnFigure(int[] position) {
        if (position[1] == 0) {
            return true;
        }
        return false;
    }

    public void changePawnForFigure(String figureName) {
        try {
            Data data = COREGate.getData();
            PlayerData playerData = COREGate.getPlayerData();
            String playerColor = playerData.getPlayerColor();
            int[] clickedFigure = playerData.getClickedFigure();

            data.remove(clickedFigure);
            if (figureName.equals("Queen")) {
                data.add(new Queen(playerColor), clickedFigure);
            } else if (figureName.equals("Rook")) {
                data.add(new Rook(playerColor), clickedFigure);
            } else if (figureName.equals("Bishop")) {
                data.add(new Bishop(playerColor), clickedFigure);
            } else if (figureName.equals("Knight")) {
                data.add(new Knight(playerColor), clickedFigure);
            }
            playerData.nullClickedFigure();
            playerData.setJustPlaying(false);
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        } catch (DataException ex) {
            System.out.println(ex);
        }
    }

    private boolean nullClickedFigure() {
        try {
            PlayerData playerData = COREGate.getPlayerData();
            playerData.nullClickedFigure();
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
        return false;
    }
}
