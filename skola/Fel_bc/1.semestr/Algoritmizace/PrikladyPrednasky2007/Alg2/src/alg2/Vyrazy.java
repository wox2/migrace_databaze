package alg2;



public class Vyrazy {
  public static void main(String[] args) {
   System.out.println("7/3="+7/3);

   System.out.println("7%3="+7%3);


    int i=10; double x=12.3; boolean b;
   System.out.println("i==10 ~ " + (i==10));             // vypíše se true
   System.out.println("i+1!=10 ~ " + (i+1!=10));           // vypíše se true
    b = i>x;
   System.out.println("b ~ " + b);                 // vypíše se false
//........................
    int n = 10; boolean b1 = false, b2 = true; 
    double y, z;
   System.out.println(1<=n && n<=20);	// vypíše se true
   System.out.println(b1 || !b2);		// vypíše se false
   y=0; x=1;z=2;
   System.out.println(y != 0 && x/y < z);		// vypíše se false


  }
}