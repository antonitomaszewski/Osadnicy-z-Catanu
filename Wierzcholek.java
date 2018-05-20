import java.awt.*;

public class Wierzcholek {
    public int x;
    public int y;

    public boolean is_empty;
    public int budynek;
    public int nr_gracza;
    public Color kolor_gracza;

    public Wierzcholek(int x, int y)
    {
        this.x = x;
        this.y = y;

        this.is_empty = true;

        // dodatek w ostatecznej wersji usunac
        this.budynek = -1;
        this.nr_gracza = -1;
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


    // dodatek int gracz --> w ostatecznej wersji sprawdzam tylko is_empty
    public void zbuduj_osade(int gracz)
    {
        if (this.is_empty && this.budynek == -1 && this.nr_gracza == -1)
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

}