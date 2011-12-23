/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otacenislova;

class Text {

    private String text;

    public Text() {
        zmenText("nadherny den");
    }

    public Text(String s) {
        text = s;
    }

    public void zmenText() {
        text = "nadherny den";
    }

    public void zmenText(String s) {
        text = s;
    }

    public void vypis() {
        System.out.println(text);
    }
}

public class Main 
{
    public static void main(String[] args)
    {
        Text n1 = new Text();
        n1.vypis();
        Text n2 = new Text("Jezis te vidi");
        n2.vypis();
        Text n3=n2;
        n1.vypis();
        n3.vypis();
        Text n4= new Text("Uz me to jebe");
        n4.vypis();
    }
}
 
