package geometry;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author já
 */
public class Square {

    private Rectangle obdelnik;

    public Square(int x, int y, int width) {
        this.obdelnik = new Rectangle(x, y, width, width);
    }

    public void paint() {
        obdelnik.paint();


    }
}
