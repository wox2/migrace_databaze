package omo3;

public class Father {
    public void feedYourChild(Child s) {
        for (int i = 0; i < 20; i++) {
            s.openYourMouth();
            s.letTheSpoonInYourMouth();
            s.closeYourMouth();
            s.swallow();
        }
        s.burp();
    }
}
