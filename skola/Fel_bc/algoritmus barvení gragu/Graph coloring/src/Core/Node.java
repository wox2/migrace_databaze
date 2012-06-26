/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.Serializable;

/**
 * Trida Node reprezentuje elementarni strukturu grafu - uzel. Neda se vytvorit deep copy -
 * pri klonovani jednoho Nodu by vznikla kopie cele struktury diky odkazu na predchudce.
 * @author woxie
 */
public class Node implements Serializable {

    /**
     * Promenna identifikujici Node.
     */
    private int id;
    /**
     * Barva Nodu.
     */
    private int color;

    public Node (int idValue, int colorValue){
        id = idValue;
        color = colorValue;
    }

    /**
     *  Vytvori Node s zadanym jmenem prevedenym do velkych pismen.
     * @param name jmeno uzlu slouzici k jednoznacne identifikaci
     */
    public Node(int idValue) {
        this(idValue,0);
    }

    /** Metoda vracici jmeno Nodu.
     * @return jmeno Nodu
     */
    public int getID() {
        return id;
    }
 
    /**
     *  Vraci kopii Nodu.
     * @return vraci kopii Nodu, v teto verzi jen melkou
     */
    @Override
    public Node clone() {
        return new Node(id, color);
    }

    /**
     * Nastavi barvu na hodnotu parametru
     * @param value
     */
    public void setColor(int value){
        color = value;
    }

    /**
     * Navrati barvu Nodu
     * @return
     */
    public int getColor(){
        return color;
    }
}
