package omo2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author já
 */
public class PersonalName {

    public String name;
    public String surname;

    public PersonalName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return this.name + " " + this.surname;
    }

    public boolean equals(Object another) {
        if (another instanceof PersonalName) {
            return this.name.equals(((PersonalName) another).name) &&
                    this.surname.equals(((PersonalName) another).surname);

        } else {
            return false;
        }}

        public int hashCode(){
        return
                this.name.hashCode() ^ this.surname.hashCode();
        
        }
}
