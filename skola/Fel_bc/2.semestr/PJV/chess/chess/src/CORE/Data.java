/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CORE;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sprinkler
 */
public class Data implements Serializable {

    private Map<Integer, Figure> figures;

    public Data() {
        figures = new HashMap<Integer, Figure>();
    }

    public Data(Data data) {
        figures = new HashMap<Integer, Figure>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    if (data.get(i, j) != null) {
                        add(data.get(i, j), reverseNumber(i), reverseNumber(j));
                    }
                } catch (DataException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private int reverseNumber(int y) {
        switch (y) {
            case 0:
                return 7;
            case 1:
                return 6;
            case 2:
                return 5;
            case 3:
                return 4;
            case 4:
                return 3;
            case 5:
                return 2;
            case 6:
                return 1;
            default:
                return 0;
        }
    }

    private int key(int x, int y) {
        return (x * 10) + y;
    }

    public void add(Figure figure, int x, int y) throws DataException {
        if (figures.size() < 64) {
            if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8)) {
                try {
                    figures.put(key(x, y), figure);
                    figure.setPosition(new Point(x, y));
                } catch (FigureException ex) {
                    System.out.println(ex);
                }
            } else {
                throw new DataException("Maximum array size is 8 x 8");
            }
        } else {
            throw new DataException("Maximum count figures is 64");
        }
    }

    public void add(Figure figure, int[] position) throws DataException {
        add(figure, position[0], position[1]);
    }

    public boolean remove(int[] position) throws DataException {
        return remove(position[0], position[1]);
    }

    public boolean remove(int x, int y) throws DataException {
        if (figures.size() > 0) {
            if (figures.containsKey(key(x, y))) {
                figures.remove(key(x, y));
                return true;
            } else {
                return false;
            }
        } else {
            throw new DataException("Figures is empty");
        }
    }

    public void change(int[] position) throws DataException {
        try {
            if (COREGate.getPlayerData() == null) {
                throw new DataException("Player data is not set");
            }
            int[] oldPosition = COREGate.getPlayerData().getClickedFigure();
            if ((oldPosition[0] == -1) || (oldPosition[1] == -1)) {
                throw new DataException("Figure is not exist");
            }
            Figure figure = get(oldPosition);
            add(figure, position);
            remove(oldPosition);
        } catch (PlayerDataException ex) {
            System.out.println(ex);
        }
    }

    public Figure get(int x, int y) throws DataException {
        if (figures.size() == 0) {
            throw new DataException("Figures is empty");
        }
        if (figures.containsKey(key(x, y))) {
            return figures.get(key(x, y));
        }
        return null;
    }

    public Figure get(int[] position) throws DataException {
        return get(position[0], position[1]);
    }

    public void clear() {
        figures.clear();
    }
}
