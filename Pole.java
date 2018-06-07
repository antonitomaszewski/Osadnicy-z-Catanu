import java.awt.Color;
import java.util.ArrayList;

public class Pole {

    public int x;
    public int y;

    public boolean zlodziej = false;

    public int wartosc = -1;
    public String surowiec = "";
    public Color kolor = Color.WHITE;

    public ArrayList<Wierzcholek> lista_wierzcholkow = new ArrayList<Wierzcholek>();


    // robi się raz na początku rundy i to koniec
    public Pole(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean czy_to_tu(int x, int y){
        int err = 40;
        return ((Math.abs(this.x - x) < err) && (Math.abs(this.y - y) < err));
    }

// public void nadaj_wartosc_i_surowiec(int w, String s, Color c) {
//         this.wartosc = w;
//         this.surowiec = s;
//         this.kolor = c;
// }

    // będą dynamicznie zmieniane
    public void dodaj_zlodzieja() {
        zlodziej = true;
    }
    public void usun_zlodzieja() {
        zlodziej = false;
    }

}