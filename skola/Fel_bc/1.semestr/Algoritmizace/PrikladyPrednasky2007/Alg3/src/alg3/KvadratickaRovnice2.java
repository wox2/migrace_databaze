package alg3;
public class KvadratickaRovnice2 {
  public static void main(String[] args) {
  double a = 0, b = 0, c = 0;
  if (a!=0){
     double diskriminant = b * b - 4 * a * c;
      if (diskriminant < 0) System.out.println("Kvadratická rovnice nemá reálné koøeny");
        else if (diskriminant == 0)  System.out.println("Jedno øešení x=-b/2a = " + (-b/(2*a))); 
         else{        
            double x = Math.sqrt(diskriminant);
            double koren1 = ( -b + diskriminant) / (2 * a);
            double koren2 = ( -b - diskriminant) / (2 * a);
            System.out.println("Kvadratická rovnice má reálné koøeny: " + koren1 + ", " + koren2);
            }
    }else
        if (b!=0)System.out.println("Lineární rovnice x= c/b =" + (-c/b));
          else if (c!=0) System.out.println("Žádné øešení c<>0");
            else System.out.println("Nekoneènì øešení, c=0");
  
 
  }

}
