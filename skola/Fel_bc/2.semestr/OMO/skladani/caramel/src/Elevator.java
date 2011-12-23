public class Elevator implements Movable {
    Repaintable r;
    int rest;
    int height;
    boolean up;

    public Elevator(Repaintable rr, int hh) {
        r = rr;
        rest = hh;
        height = hh;
        up = false;
    }

    public void move() {
        r.moveDown( up ? -1 : 1);
        if ( rest-- == 0) {
            rest = height;
            up = ! up;
        }
    }

}
