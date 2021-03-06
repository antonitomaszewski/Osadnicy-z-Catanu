import java.awt.Color;
import java.util.ArrayList;

public class Wierzcholek {

public int x;
public int y;

public boolean is_empty = true;

public int nr_gracza = -1;
public String nazwa_gracza = "";
public Color kolor_gracza = Color.WHITE;

public int budynek = -1;

int err = (int) Okno.size_width / 36;

public Wierzcholek(int x, int y) {
        this.x = x;
        this.y = y;
};

/* NOWE ZMIANY */
public ArrayList<Wierzcholek> sasiednie_wierzcholki = new ArrayList<Wierzcholek>();
public ArrayList<Krawedz> sasiednie_krawedzie = new ArrayList<Krawedz>();



public void zbuduj_osade(int gracz, String imie, Color kolor) {
        this.is_empty = false;

        this.nr_gracza = gracz;
        this.nazwa_gracza = imie;
        this.kolor_gracza = kolor;

        this.budynek = 1;
        return;
}
public void zbuduj_miasto() {
        this.budynek = 2;
        return;
}

public boolean czy_to_tu(int x, int y){
        return ((Math.abs(this.x - x) < err) && (Math.abs(this.y - y) < err));
}








public void wypisz() {
        for (Wierzcholek W :  sasiednie_wierzcholki) {
                System.out.println(W.toString());
        }
        System.out.println(this.toString());
        return;
}
/**
 * Create string representation of Wierzcholek for printing
 * @return
 */
@Override
public String toString() {
        return "Wierzcholek [x=" + x + ", y=" + y + ", is_empty=" + is_empty + ", nr_gracza=" + nr_gracza + ", nazwa_gracza=" + nazwa_gracza + ", kolor_gracza=" + kolor_gracza + ", budynek=" + budynek + "]";
}

}
