import java.awt.*;
import java.util.ArrayList;

public class Pole {

    public boolean zlodziej = false;

    public int wartosc = -1;
    public String surowiec = "";
    public Color kolor = Color.White;


    public int x;
    public int y;

    public ArrayList<Wierzcholek> lista_wierzcholkow = new ArrayList<Wierzcholek>();
    public ArrayList<Krawedz> lista_krawedzi = new ArrayList<Krawedz>();
    //public ArrayList<Gracz> lista_graczy = new ArrayList<Gracz>();

    // robi się raz na początku rundy i to koniec
    public Pole(int x, int y)
    {
      this.x = x;
      this.y = y;

      this.zlodziej = false;
      this.wartosc = -1;
      this.surowiec = "";
    }
    public void nadaj_wartosc_i_surowiec(int w, String s, Color c)
    {
      this.wartosc = w;
      this.surowiec = s;
      //this.kolor = c;
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
