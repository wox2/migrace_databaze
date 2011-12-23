package omo2;

public class Address {
    String street;
    String city;

    Address(String s, String c) {
        street = s;
        city = c;
    }

    Address getCopy() {
        return new Address(street, city);
    }

}
