public class ComposedRepaintable implements Repaintable {
    Repaintable[] r;

    public ComposedRepaintable(Repaintable[] rs) {
        r = rs;
    }
    public void repaint() {
        for (int i = 0; i < r.length; i++)
            r[i].repaint();
    }

    public void moveDown(int ii) {
        for (int i = 0; i < r.length; i++)
            r[i].moveDown(ii);
    }

    public void moveRight(int ii) {
        for (int i = 0; i < r.length; i++)
            r[i].moveRight(ii);
    }
}
