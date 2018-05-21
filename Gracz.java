import java.awt.Color;
import java.util.ArrayList;

public class Gracz {

/* DEKLARACJE ZMIENNYCH */
public int numer = -1;
public String imie = "";
public Color kolor = Color.WHITE;
public int punkty = 0;

public int postawione_drogi = 0;
public int postawione_osady = 0;
public int postawione_miasta = 0;

public int pozostale_drogi = 15;
public int pozostale_osady = 5;
public int pozostale_miasta = 4;


public Surowce surowce = new Surowce();
public ArrayList<Wierzcholek> drogi_osady_i_miasta = new ArrayList<Wierzcholek>();

/* KONSTRUKTOR I SPRAWDZONE FUNKCJE */
public Gracz(int nr, String nazwa, Color color) {
        numer = nr;
        imie = nazwa;
        kolor = color;
}


public boolean czy_wierzcholek_juz_jest(Wierzcholek W) {
        for (Wierzcholek W_i : drogi_osady_i_miasta) {
                if (W.x == W_i.x && W.y == W_i.y) {
                        return true;
                }
        }
        return false;
}



public void pierwsza_osada(Wierzcholek W){
        W.zbuduj_osade(this.numer, this.imie, this.kolor);
        drogi_osady_i_miasta.add(W);
        punkty++;
        postawione_osady++;
        pozostale_osady--;
        return;
}
public void pierwsza_droga(Krawedz K){
        K.zbuduj_droge(this.numer, this.imie, this.kolor);
        if (!czy_wierzcholek_juz_jest(K.wierzcholek_tworzacy_1)) {
                drogi_osady_i_miasta.add(K.wierzcholek_tworzacy_1);
        } else {
                drogi_osady_i_miasta.add(K.wierzcholek_tworzacy_2);
        }
        postawione_drogi++;
        pozostale_drogi--;
        return;
}
public void druga_osada(Wierzcholek W){
        W.zbuduj_osade(this.numer, this.imie, this.kolor);
        drogi_osady_i_miasta.add(W);
        punkty++;
        postawione_osady++;
        pozostale_osady--;
        return;
}
public void druga_droga(Krawedz K){
        K.zbuduj_droge(this.numer, this.imie, this.kolor);
        if (!czy_wierzcholek_juz_jest(K.wierzcholek_tworzacy_1)) {
                drogi_osady_i_miasta.add(K.wierzcholek_tworzacy_1);
        } else if (!czy_wierzcholek_juz_jest(K.wierzcholek_tworzacy_2)) {
                drogi_osady_i_miasta.add(K.wierzcholek_tworzacy_2);
        }
        postawione_drogi++;
        pozostale_drogi--;
        return;
}


/* FUNKCJA DO ZNAJDYWANIA POTENCJALNYCH MIAST */
public ArrayList<Wierzcholek> znajdz_wszyskie_dostepne_lokalizacje_miast() {
        ArrayList<Wierzcholek> dostepne_miasta = new ArrayList<Wierzcholek>();
        for (Wierzcholek W : drogi_osady_i_miasta) {
                if (W.budynek == 1)
                {
                        dostepne_miasta.add(W);
                }
        }
        return dostepne_miasta;
}

/* FUNKCJA DO ZNAJDYWANIA POTENCJALNYCH DRÓG */
public boolean czy_droga_nie_przetnie_osady(Wierzcholek W) {
        /* TODO */
        return false;
}

public ArrayList<Krawedz> znajdz_wszyskie_dostepne_lokalizacje_drog() {
        ArrayList<Krawedz> dostepne_drogi = new ArrayList<Krawedz>();
        for (Wierzcholek W : drogi_osady_i_miasta) {
                for (Krawedz K : W.sasiednie_krawedzie) {
                        if (K.is_empty) {
                                dostepne_drogi.add(K);
                        }
                }

        }
        return dostepne_drogi;
}

/* FUNKCJE DO ZNAJDYWANIA POTENCJALNYCH OSAD */
public boolean czy_wszystkie_sasiadujace_wierzcholki_puste(Wierzcholek W) {
        for (Wierzcholek W_i : W.sasiednie_wierzcholki) {
                if (!W_i.is_empty) {
                        return false;
                }
        }
        return true;
}
public ArrayList<Wierzcholek> znajdz_wszyskie_dostepne_lokalizacje_osad() {
        ArrayList<Wierzcholek> dostepne_osady = new ArrayList<Wierzcholek> ();
        for (Wierzcholek W : drogi_osady_i_miasta) {
                if (W.is_empty && czy_wszystkie_sasiadujace_wierzcholki_puste(W)) {
                        dostepne_osady.add(W);
                }
        }
        return dostepne_osady;
}






public void dodaj(int ile, String surowiec){
        this.surowce.dodaj(ile, surowiec);
}
public void zabierz(int ile, String surowiec){
        this.surowce.zabierz(ile, surowiec);
}


public void zbuduj_droge(Krawedz K){
        K.zbuduj_droge(numer, imie, kolor);
        if (!czy_wierzcholek_juz_jest(K.wierzcholek_tworzacy_1)) {
                drogi_osady_i_miasta.add(K.wierzcholek_tworzacy_1);
        } else if (!czy_wierzcholek_juz_jest(K.wierzcholek_tworzacy_2)) {
                drogi_osady_i_miasta.add(K.wierzcholek_tworzacy_2);
        }
        this.zabierz(1, "drewno");
        this.zabierz(1, "cegla");
        postawione_drogi++;
        pozostale_drogi--;
        return;
}
public void zbuduj_osade(Wierzcholek W){
        W.zbuduj_osade(this.numer, this.imie, this.kolor);
        this.zabierz(1, "owca");
        this.zabierz(1, "siano");
        this.zabierz(1, "drewno");
        this.zabierz(1, "cegla");
        punkty++;
        postawione_osady++;
        pozostale_osady--;
        return;
}
public void zbuduj_miasto(Wierzcholek W){
        W.zbuduj_miasto(); // zakładam, że wtedy wartość w wierzchołku W, w którym gracz ma już osade zmieni się także w liscie osad_i_miast
        this.zabierz(2, "siano");
        this.zabierz(3, "kamien");

        punkty++;
        postawione_miasta++;
        pozostale_miasta--;

        postawione_osady--;
        pozostale_osady++;
        return;
}

public boolean czy_mozna_postawic_droge(){
        return (this.surowce.drewno > 0 && this.surowce.cegla > 0 && this.postawione_drogi < 15);
}
public boolean czy_mozna_postawic_osade(){
        return (this.surowce.owca > 0 && this.surowce.siano > 0 && this.surowce.drewno > 0 && this.surowce.cegla > 0 && this.postawione_osady < 5);
}
public boolean czy_mozna_postawic_miasto(){
        return (this.surowce.siano > 1 && this.surowce.kamien > 2 && this.postawione_miasta < 4);
}
}
