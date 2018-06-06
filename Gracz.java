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
public Gracz(){
}
public Gracz(int nr, String nazwa, Color color) {
        numer = nr;
        imie = nazwa;
        kolor = color;
}
/* FUNKCJA DO WRZUCANIA NOWYCH WIERZCHOŁKÓW DO LISTY JUŻ OKUPOWANYCH -- drogi_osady_i_miasta */
public boolean czy_wierzcholek_juz_jest(Wierzcholek W) {
        for (Wierzcholek W_i : drogi_osady_i_miasta) {
                if (W.x == W_i.x && W.y == W_i.y) {
                        return true;
                }
        }
        return false;
}

/* FUNKCJE DO ZBUDOWANIA PIERWSZYCH DWÓCH OSAD WRAZ Z ICH DROGAMI -- Początek */
public void zbuduj_pierwsza_lub_druga_osade(Wierzcholek W){
        W.zbuduj_osade(this.numer, this.imie, this.kolor);
        drogi_osady_i_miasta.add(W);
        punkty++;
        postawione_osady++;
        pozostale_osady--;
        return;
}
public void zbuduj_pierwsza_lub_druga_droge(Krawedz K){
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
/* FUNKCJE DO ZBUDOWANIA PIERWSZYCH DWÓCH OSAD WRAZ Z ICH DROGAMI -- Koniec */


/* FUNKCJA DO ZNAJDYWANIA POTENCJALNYCH MIAST */
public ArrayList<Wierzcholek> znajdz_wszyskie_dostepne_lokalizacje_miast() {
        ArrayList<Wierzcholek> dostepne_miasta = new ArrayList<Wierzcholek>();
        for (Wierzcholek W : drogi_osady_i_miasta) {
                if (W.budynek == 1 && W.nr_gracza == numer)
                {
                        dostepne_miasta.add(W);
                }
        }
        return dostepne_miasta;
}
/* FUNKCJA DO ZNAJDYWANIA POTENCJALNYCH DRÓG */
public ArrayList<Krawedz> znajdz_wszyskie_dostepne_lokalizacje_drog() {
        ArrayList<Krawedz> dostepne_drogi = new ArrayList<Krawedz>();
        for (Wierzcholek W : drogi_osady_i_miasta) {
                if (W.is_empty || W.nr_gracza == numer) {
                        for (Krawedz K : W.sasiednie_krawedzie) {
                                if (K.is_empty) {
                                        dostepne_drogi.add(K);
                                }
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
        W.zbuduj_miasto();

        this.zabierz(2, "siano");
        this.zabierz(3, "kamien");

        punkty++;
        postawione_miasta++;
        pozostale_miasta--;

        postawione_osady--;
        pozostale_osady++;
        return;
}

public boolean czy_mozna_wymienic(int ile, String surowiec) {
        if (surowiec == "owca") {
                return surowce.owca >= ile;
        } else if (surowiec == "siano") {
                return surowce.siano >= ile;
        } else if (surowiec == "drewno") {
                return surowce.drewno >= ile;
        } else if (surowiec == "cegla") {
                return surowce.cegla >= ile;
        } else if (surowiec == "kamien") {
                return surowce.kamien >= ile;
        }
        return false;
}

public boolean czy_mozna_postawic_droge(){
        return (surowce.drewno >= 1 && surowce.cegla >= 1 && pozostale_drogi > 0);
}
public boolean czy_mozna_postawic_osade(){
        return (surowce.owca >= 1 && surowce.siano >= 1 && surowce.drewno >= 1 && surowce.cegla >= 1 && pozostale_osady > 0);
}
public boolean czy_mozna_postawic_miasto(){
        return (surowce.siano >= 2 && surowce.kamien >= 3 && pozostale_miasta > 0);
}
public void czy_mozna_wymienic_wymien(String surowiec_do_kupienia, String surowiec_do_sprzedania) {
        if (surowiec_do_sprzedania == "owca") {
                if (surowce.owca >= 4) {
                        surowce.dodaj(1, surowiec_do_kupienia);
                        surowce.zabierz(4, surowiec_do_sprzedania);
                }
        } else if (surowiec_do_sprzedania == "siano") {
                if (surowce.siano >= 4) {
                        surowce.dodaj(1, surowiec_do_kupienia);
                        surowce.zabierz(4, surowiec_do_sprzedania);
                }
        } else if (surowiec_do_sprzedania == "drewno") {
                if (surowce.drewno >= 4) {
                        surowce.dodaj(1, surowiec_do_kupienia);
                        surowce.zabierz(4, surowiec_do_sprzedania);
                }
        } else if (surowiec_do_sprzedania == "cegla") {
                if (surowce.cegla >= 4) {
                        surowce.dodaj(1, surowiec_do_kupienia);
                        surowce.zabierz(4, surowiec_do_sprzedania);
                }
        } else if (surowiec_do_sprzedania == "kamien") {
                if (surowce.kamien >= 4) {
                        surowce.dodaj(1, surowiec_do_kupienia);
                        surowce.zabierz(4, surowiec_do_sprzedania);
                }
        }
}
public void wypisz(){
        System.out.println(toString());
}

/**
 * Create string representation of Gracz for printing
 * @return
 */
@Override
public String toString() {
        return "Gracz [numer=" + numer + ", imie=" + imie + ", kolor=" + kolor + ", punkty=" + punkty + ", postawione_drogi=" + postawione_drogi + ", postawione_osady=" + postawione_osady + ", postawione_miasta=" + postawione_miasta + ", pozostale_drogi=" + pozostale_drogi + ", pozostale_osady=" + pozostale_osady + ", pozostale_miasta=" + pozostale_miasta + surowce.toString() + "]";
}

}
