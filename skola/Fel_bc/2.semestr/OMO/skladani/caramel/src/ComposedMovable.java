public class ComposedMovable implements Movable {
    Movable[] m;

    public ComposedMovable(Movable[] ms) {
        m = ms;
    }

    public void move() {
        for (int i = 0; i < m.length; i++)
            m[i].move();
    }
}
