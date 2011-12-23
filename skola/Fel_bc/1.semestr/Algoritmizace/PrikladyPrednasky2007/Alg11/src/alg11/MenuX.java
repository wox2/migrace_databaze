package alg11;

class MenuX extends Menu{
                                        //private String[] prvky;
                                        //private String vyzva;
                                        //private int volba;
private int pocetPrvku;
private int volny=0;
 public MenuX(String[] prvky, String vyzva,int pocet) {
    super(prvky, vyzva);
    this.pocetPrvku = pocet;
    prvky = new String[pocetPrvku];
  }
public void pridejPolozkuDoMenu(String s){
   if (volny < pocetPrvku)prvky[volny++]=s;
  }
public void pridejVyzvu(String s){
    vyzva=s;
        }
}
