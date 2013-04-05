package alg3;
import java.util.*;

public class BodyMassIndex{
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Vas Body mass index");
    System.out.print("Vaha (kg): ");
    double vaha = sc.nextDouble();
    System.out.print("Vyska (cm): ");
    double vyska = sc.nextDouble();

    double bmi = vaha/(vyska/100*vyska/100);

    System.out.printf("BMI:    %6.3f %n  ",  bmi);

    // do podmineneho prikazu lze vkladat dalsi (i podminene) prikazy
    if (bmi < 20)
      System.out.println("Podvaha");
    else
      if (bmi <= 25)
        System.out.println("Vse OK");
      else
        if(bmi < 30)
          System.out.println("nadvaha.");
        else
          System.out.println("Obezita");    
  }
}
