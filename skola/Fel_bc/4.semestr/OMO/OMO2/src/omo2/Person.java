package omo2;

public class Person {
    String name;
    Person spouse;
    Address addr;
    static int count;

    Person(String n, Address a) {
        name = n;
        if ( a != null) addr = a.getCopy();
        count++;
    }

    void marry(Person p) {
        spouse = p;
        p.spouse = this;
        p.addr = addr;
    }

    void divorce() {
        spouse.spouse = null;
        spouse = null;
        addr = addr.getCopy();
    }

    static void printPopulation() {
        System.out.println(count);
    }
}
