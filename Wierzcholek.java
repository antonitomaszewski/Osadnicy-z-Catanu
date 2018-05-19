public class Wierzcholek {
    public int x;
    public int y;

    public boolean is_empty = true;
    public int budynek = -1;

    public int nr_gracza = -1;
    public String nazwa_gracza = "";
    public Color kolor_gracza = Color.White;

    public Wierzcholek(int x, int y)
    {
      this.x = x;
      this.y = y;

      this.is_empty = true;

      // dodatek w ostatecznej wersji usunac
      this.budynek = -1;
      this.nr_gracza = -1;
    }


    public boolean czy_wolne_osada()
    {
      return this.is_empty;
    }
    // dodatek int gracz --> w ostatecznej wersji sprawdzam tylko is_empty
    public void zbuduj_osade(int gracz, String imie, Color kolor)
    {
      if (this.is_empty && this.budynek == -1 && this.nr_gracza == -1)
      {
        this.is_empty = false;
        this.budynek = 1;
        this.nr_gracza = gracz;
        this.nazwa_gracza = imie;
        this.kolor_gracza = kolor;
        return;
      } else
      {
        System.out.println("Błąd w budowaniu osady");
        return;
      }
    }


    public boolean czy_wolne_miasto(int nr_gracza)
    {
      return (this.nr_gracza == nr_gracza && budynek = 1);
    }
    // dodatek int gracz --> w ostatecznej wersji sprawdzam tylko is_empty i czy jest juz osada
    public void zbuduj_miasto(int gracz)
    {
      if (this.is_empty == false && this.budynek == 1 && this.nr_gracza == gracz)
      {
        this.budynek = 2;
        return;
      } else
      {
        System.out.println("Błąd w budowaniu miasta");
        return;
      }
    }


}
