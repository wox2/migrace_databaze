public class Pozice_4
{
    //Atributy, v nichz si pamatuji souradnice dane pozice
    private int x;
    private int y;


    public Pozice_4( int x, int y )
    { 
      //atribut = parametr
        this.x  = x;
        this.y  = y;
    }

    public int getX()
    {
        //Tady se x s nicim nehada, takze this pouzivat nemusime
        return x;   //ale muzeme, tj. lze napsat: return this.x;
    }

    public int getY()
    {
        return y;
    }

    public void setPozice( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    public void setPozice( Pozice_4 pozice  )
    {
        //Ani zde bych nemusel pouzivat this, ale pripadalo mi,
        //ze pri jeho pouziti je program prehlednejsi
        this.x = pozice.x;
        this.y = pozice.y;
    }
}

