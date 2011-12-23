public class Rozmer_4
{
    private int sirka;
    private int vyska;


    public Rozmer_4(int sirka, int vyska)
    {
        this.sirka = sirka;
        this.vyska = vyska;
    }

    public int getSirka()
    {
        return sirka;
    }

    public int getVyska()
    {
        return vyska;
    }

    public void setRozmer( int sirka, int vyska )
    {
        this.sirka = sirka;
        this.vyska = vyska;
    }

    public void setRozmer( Rozmer_4 rozmer)
    {
        this.sirka = rozmer.sirka;
        this.vyska = rozmer.vyska;
    }
}

