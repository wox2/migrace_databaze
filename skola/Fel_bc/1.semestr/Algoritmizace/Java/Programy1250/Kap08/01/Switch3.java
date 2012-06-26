// Soubor Switch3.java
// Ukazuje použití pøíkazu switch
// s výètovými typy v JDK 5
//
// Je JDK 5
//

enum Smer {SEVER, JIH, VYCHOD, ZAPAD;}

public class Switch3
{
   static void komentujSmer(Smer x)
   {
     System.out.printf("Jdeme na %s: ", x.name());
     switch(x)
     {
       case SEVER: 
          System.out.printf("Tam nechci\n");
          break;
       case JIH:
          System.out.printf("To by slo\n");
          break;
       default:
          System.out.printf("Hm...\n");
          break;
     }
   }
   public static void main(String [] par)
   {
     komentujSmer(Smer.SEVER);     
     komentujSmer(Smer.ZAPAD);
   }   
}