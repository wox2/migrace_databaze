package omo2;

import java.util.PriorityQueue;

public class Main {
/*
 static class Cell {
        int c;

        Cell(int cc) {
            c = cc;
        }

        void setContents(int cc) {
            c = cc;
        }

        int getContents() {
            return c;
        }
    }
*/
    public static void main(String[] args) {
/*       Cell a = new Cell(5);
        Cell b = a;
        b.setContents(3);
        System.out.println( a.getContents());

        Address a = new Address( "Technicka 2", "Praha");
        Person f = new Person( "Franta", a);
        Address b = new Address( "Technicka 3", "Praha");
        Person i = new Person( "Igor", b);
        f.marry(i);
        Person h = new Person( "Hugo", a);
        a.street = "Elm Street";
/*
        System.out.println( f.addr.street);
        System.out.println( i.addr.street);
        System.out.println( h.addr.street);

        new Person( "Agata", null);
        f.divorce();
        i.addr.street = "Technicka 3";
     //   Person.printPopulation();
   /*     System.out.println( f.addr.street);*/
        String a = "FFFFFFFFFFFFFFFFFFFFFFFFFFFUUUUUUUUUUUUUUUUUUUUCK";
        String b = new String(a);
        String c = a;
        a= "You sucks looser";
        System.out.println(c);

        System.out.println(b);
        
        pogo pg = new pogo();
        int i = 10;
        pg.setContent(i);

       WTF fu = new WTF(pg);
        fu.printContent();

        pg.setContent(256);
        fu.printContent();


    }
}

class pogo{
    int content;

    public void setContent(int i){
        content = i;
    }
}

class WTF {
    pogo pg;

    WTF(pogo pgWTF){
        pg = pgWTF;
    }

    void printContent(){
        System.out.println(pg.content);
    }
}