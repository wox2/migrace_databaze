package alg2;
public class JednucheDatoveTypy {
    public static void main(String[] args ) {
        System.out.println(">>>  START # 1  \n");
        
int i=7;		// deklarace promìnné i typu int
double x;	// deklarace promìnné x typu double
boolean b;      // deklarace promìnné b typu boolean\
String s;       // deklarace promìnné b typu boolean\
final int XXX=64646383;
final int MAX = 100;
final String NAZEV_PREDMETU = "Algoritmizace";


i=55;
x=4512e11;
b=true;
s= "ALFA";
System.out.println("i = " + i*5);
System.out.println("x = " +x);
System.out.println("b = " +b);
System.out.println("s = " +s);
// .......................
int a, c;
a = 7;            //má hodnotu 7 a lze tedy opìt pøiøadit!!
c = a = a + 6;    // vyhodnotí se jako y = (x = (x + 6)); 
System.out.println("a, c = " + a + " , " + c);        
//.........................

a=1; 
c=2;
System.out.println(a+c);
System.out.println("souèet je "+(a+c));
System.out.println("souèet je "+a+c);

System.out.println(">>>  STOP");
    }
}
