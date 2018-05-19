public class Krawedz {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public boolean is_empty = true;
    public int nr_gracza = -1;
    public String nazwa_gracza = "";
    public Color kolor_gracza = Color.White;


    public Krawedz(int x1, int y1, int x2, int y2)
    {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;

      is_empty = true;

      // dodatek w ostatecznej wersji usunac
      this.nr_gracza = -1;
    }

    // dodatek ostatecznie tylko is_empty i funkcja jest bezargumentowa
    public void zbuduj_droge(int gracz, String nazwa, Color kolor)
    {
      if (this.is_empty && this.nr_gracza == -1)
      {
          this.is_empty = false;
          this.nr_gracza = gracz;
          this.nazwa_gracza = nazwa;
          this.kolor_gracza = kolor;
          return;
      } else
      {
        System.out.println("Błąd w budowaniu drogi");
        return;
      }
    }
    public boolean czy_wolne_droga()
    {
      return this.is_empty;
    }

}
