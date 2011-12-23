/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafickyeditorv1;



/**
 *
 * @author já
 */


class souradnice {
int x,y;
}

abstract class grafickyObjekt {
    public String barva;
    void vykresli(){};
}

class bod extends grafickyObjekt {
    public souradnice pozice;

 bod(){};
 void vykresli(souradnice pozice){System.out.println("Bod na pozici "+pozice.x+", " + pozice.y );}


}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bod g=new bod();
        g.vykresli();
    }
}
