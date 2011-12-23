public class HorizontalElevator implements Movable {
    Repaintable e;

    public HorizontalElevator(Repaintable ee) {
        e = ee;
    }
    public void move() {
        e.moveRight(1);
    }

}
