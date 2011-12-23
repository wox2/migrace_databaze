package alg3;

import java.util.*;

public class KvadratickaRovnice {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
double diskriminant;
double koren1;
double koren2;
double imaginarnicast;
double realnacast;

System.out.println("Kvadraticka rovnice ve tvaru: ax + by + c = 0");
System.out.println("Zadejte hodnotu a: ");
double a = sc.nextDouble();

System.out.println("Zadejte hodnotu b: ");
double b = sc.nextDouble();
System.out.println("Zadejte hodnotu c: ");
double c = sc.nextDouble();

diskriminant = (b * b) - 4 * a * c;
System.out.println("Diskriminat je: " + diskriminant);

if (diskriminant > 0)
{
koren1 = ( -b + Math.sqrt(diskriminant)) / (2 * a);
koren2 = ( -b - Math.sqrt(diskriminant)) / (2 * a);
System.out.println("rovnice ma dva realne koreny: ");
System.out.println("Prvni koren rovnice je: " + koren1);
System.out.println("Druhz koren rovnice je: " + koren2);
}
else if (diskriminant == 0)
{
koren1 = -b / (2 * a);
System.out.println("Tato rovnice ma jeden dvojnasobny koren: " + koren1);
}
else {
realnacast = -b / (2 * a);
imaginarnicast = Math.sqrt(Math.abs(diskriminant)) / (2 * a);
System.out.println("Tato rovnice ma komplexni koreny: ");
System.out.println("X 1: " + realnacast + " +i " + imaginarnicast);
System.out.println("X 1: " + realnacast + " -i " + imaginarnicast);
}
}
}   
    
  
