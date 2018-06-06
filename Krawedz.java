import java.awt.Color;
import java.util.ArrayList;

public class Krawedz {

    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int x_srodka;
    public int y_srodka;

    public boolean is_empty = true;

    public int nr_gracza = -1;
    public String nazwa_gracza = "";
    public Color kolor_gracza = Color.WHITE;

    public Wierzcholek wierzcholek_tworzacy_1;
    public Wierzcholek wierzcholek_tworzacy_2;
    public Krawedz(Wierzcholek W1, Wierzcholek W2) {
        x1 = W1.x;
        y1 = W1.y;
        x2 = W2.x;
        y2 = W2.y;
        x_srodka = (int) ((x1 + x2) / 2);
        y_srodka = (int) ((y1 + y2) / 2);

        wierzcholek_tworzacy_1 = W1;
        wierzcholek_tworzacy_2 = W2;
    }


    public void zbuduj_droge(int gracz, String nazwa, Color kolor) {
        this.is_empty = false;
        this.nr_gracza = gracz;
        this.nazwa_gracza = nazwa;
        this.kolor_gracza = kolor;
        return;
    }

    public boolean czy_to_tu(int x, int y){
        int err = 15;
        return ((Math.abs(x_srodka - x) < err) && (Math.abs(y_srodka - y) < err));
    }
}