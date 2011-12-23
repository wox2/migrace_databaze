package nahodneslovo;
/* Program, ktery generuje nahodne slovo-slozene z nahodnych pismen s 
 nahodnou delkou*/ 
import java.util.Random;

public class Main {
  static void scitaniNahodnychCisel(){ //metoda k scitani nahodnych cisel,
   Random cislo=new Random();          //pro generovani slov prebytecna
   int i=cislo.nextInt(10);
   int j=cislo.nextInt(10);
      System.out.println(i+"+"+j+"="+(i+j));
  }
  static void generovaniPismen(){ //generování s přetypováním, metoda, ktera
   Random rand=new Random();      // vygeneruje nahodne pismeno
   char znak=(char)(rand.nextInt(26) + 97 ); // pretypovani
   System.out.print(znak);
   
  }
  static void generovaniNahodnehoSlova(){ // samotne generovani slova
    Random cislo=new Random();            // pouzije generovani pismena
                                          //  a opakuje ho cyklicky  
    int delkaSlova=cislo.nextInt(13)+1;   // jednicka zaruci slovo s aspon 
        System.out.println(delkaSlova);   // jednou hlaskou 
    for (int a=delkaSlova;a>0;a--){
      generovaniPismen();
    }
  }
  
  public static void main(String[] args) {
       generovaniNahodnehoSlova();
    }

}
