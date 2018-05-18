public class Wierzcholek {
    public int x;
    public int y;

    public boolean is_empty;
    public int budynek;
    public int nr_gracza;

    public Wierzcholek(int x, int y)
    {
      this.x = x;
      this.y = y;

      this.is_empty = true;

      // dodatek w ostatecznej wersji usunac
      this.budyek = 0;
      this.nr_gracza = 0;
    }

    // dodatek int gracz --> w ostatecznej wersji sprawdzam tylko is_empty i czy jest juz osada
    public void zbuduj_miasto(int gracz)
    {
      if (this.is_empty == false and this.budynek == 1 and this.nr_gracza == gracz)
      {
        this.budynek = 2;
        return;
      } else
      {
        System.out.println("Błąd w budowaniu miasta");
        return;
      }
    }
    public void namaluj_miasto(Color kolor_gracza)
    {
      TODO
    }
    
    // dodatek int gracz --> w ostatecznej wersji sprawdzam tylko is_empty
    public void zbuduj_osade(int gracz)
    {
      if (this.is_empty and this.budynek = 0 and this.nr_gracza = 0)
      {
        this.is_empty = false;
        this.budynek = 1;
        this.nr_gracza = gracz;
        return;
      } else
      {
        System.out.println("Błąd w budowaniu osady");
        return;
      }
    }
    public void namaluj_osade(Color kolor_gracza)
    {
      TODO
    }
}
