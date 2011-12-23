package alg2;
public class FormatVystup {
    public static void main(String[] args ) {
     System.out.println("Cislo Pi = " + Math.PI);
     System.out.printf("Cislo Pi = %16.3f %n ", Math.PI);
     System.out.printf("Cislo Pi = %8.3f %n ", Math.PI);    
     System.out.printf("Cislo Pi = %+6.5e %n ", Math.PI);
     System.out.printf("Cislo Pi = %6.5g %n ", Math.PI);
     System.out.printf("Cislo Pi = %06d %n ", 33); 
     System.out.printf("Cislo Pi = %4d %n ", -33); 
     System.out.printf("Cislo Pi = %1$6.3f  %2$6.3f %3$6.3f",Math.PI,Math.PI, Math.PI);

    }
}
