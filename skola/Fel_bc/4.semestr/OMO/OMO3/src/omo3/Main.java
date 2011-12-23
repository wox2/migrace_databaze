package omo3;

public class Main {

    public static void main(String[] args) {
        Father f = new Father();
        Son s = new Son();
        Child d = new Daughter();
        f.feedYourChild(s);
    }
}
