public class Chessboard implements Repaintable {
    Repaintable r;

    public Chessboard() {
        Rectangle r1 = new Rectangle( 100, 100, 20, 20);
        Rectangle r2 = new Rectangle( 120, 120, 20, 20);
        r = new ComposedRepaintable( new Repaintable[] { r1, r2});
    }

    public void repaint() {
        r.repaint();
    }

    public void moveDown(int i) {
        r.moveDown(i);
    }

    public void moveRight(int i) {
        r.moveRight(i);
    }
}
