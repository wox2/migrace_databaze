public class Main {
    public static void main( String[] args) {
        Movable[] movables = new Movable[100];
        int count = 0;
        Repaintable r = new Rectangle( 100, 100, 10, 10);
        Movable m1 = new Elevator( r, 50);
        Movable m2 = new HorizontalElevator( r);
        Movable m4 = new Elevator( r, 4);
        Movable m3 = new ComposedMovable( new Movable[] { m1, m2, m4});
        movables[count++] = m3;
        while (true) {
            for (int i = 0; i < count; i++)
                movables[i].move();
            Utils.sleep(40);
        }
    }

}
