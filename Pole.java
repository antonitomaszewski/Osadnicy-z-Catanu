import java.awt.*;
import java.util.ArrayList;

public class Pole {

    public boolean zlodziej;

    public int wartosc;
    public String surowiec;
    public Color color;


    public int x_srodka;
    public int y_srodka;

    public ArrayList<Wierzcholek> lista_wierzcholkow = new ArrayList<Wierzcholek>();
    public ArrayList<Krawedz> lista_krawedzi = new ArrayList<Krawedz>();

    // robi się raz na początku rundy i to koniec
    public Pole(int x, int y)
    {
      x_srodka = x;
      y_srodka = y;

      zlodziej = false;
    }
    public void nadaj_wartosc_i_surowiec(int w, String s, Color c)
    {
      wartosc = w;
      surowiec = s;
      color = c;
    }

    // będą dynamicznie zmieniane
    public void dodaj_zlodzieja()
    {
      zlodziej = true;
    }
    public void usun_zlodzieja()
    {
      zlodziej = false;
    }

}
