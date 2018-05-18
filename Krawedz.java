public class Krawedz {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public boolean is_empty;
    public int nr_gracza;


    public Krawedz(int x1, int y1, int x2, int y2)
    {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;

      is_empty = true;

      // dodatek w ostatecznej wersji usunac
      this.nr_gracza = 0;
    }

    // dodatek ostatecznie tylko is_empty i funkcja jest bezargumentowa
    public void zbuduj_droge(int gracz)
    {
      if (this.is_empty and this.nr_gracza = 0)
      {
          this.is_empty = false;
          this.nr_gracza = gracz;
          return;
      } else
      {
        System.out.println("Błąd w budowaniu drogi");
        return;
      }
    }
    public void namaluj_droge(Color kolor_gracza)
    {
      TODO
    }
}
