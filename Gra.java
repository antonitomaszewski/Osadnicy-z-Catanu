import java.awt.Color;
import java.util.ArrayList;
/* WAŻNE DO TWORZENIA KLAS::
   Jeśli mam pole, które nie jest zainicjowane w klasie, ale ma wartość domyślną, to spokojnie mogę ją ustawić później
   Jeśli mam pole, które nie ma ani wartości domyślnej, ani nie pojawia się, w konstruktorze, to już się do niego nie dostanę,
   chyba, że jest to pole typu wbudowanego, wtedy nie będzie problemu nim operować */


public class Gra {
public Mapa Mapa = new Mapa();
public int liczba_graczy = 4;
public int nr_gracza_rozpoczynajacego = 1;
public int kolejka = 0;


public ArrayList<Gracz> lista_graczy = new ArrayList<Gracz>();


public ArrayList<Krawedz> dostepne_drogi = new ArrayList<Krawedz>();
public ArrayList<Wierzcholek> dostepne_osady = new ArrayList<Wierzcholek>();
public ArrayList<Wierzcholek> dostepne_miasta = new ArrayList<Wierzcholek>();
int x, y;

/* Po Każdej kolejce sprawdzamy, dopóki wartość jest false, gramy dalej, w momencie pojawienia się pierwszego kończymy grę (ewentualnie gramy tę kolejkę do końca i dopiero wteddy kończymy ) */
public Gra(String[] imiona, Color[] kolory){
        liczba_graczy = imiona.length;
        nr_gracza_rozpoczynajacego = (int) (Math.random() * liczba_graczy + 1);

        int p;
        for (int i = nr_gracza_rozpoczynajacego; i < nr_gracza_rozpoczynajacego + liczba_graczy; i++) {
                p = (i - 1) % liczba_graczy;
                Gracz G = new Gracz(p + 1, imiona[p], kolory[p]);
                lista_graczy.add(G);
        }

        new Okno();
        /* RYSUJE MAPĘ  */


        int x, y;
        for (Gracz G : lista_graczy) {
                dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_osady();
                /* jakaś funkcja łapiąca wybrany przez gracza wierzchołek W */
                zbuduj_pierwsza_osade_ktora_wybral_gracz(G, x, y);

                dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_drogi(G);
                /* analogiczna funkcja co do osady, teraz dla krawędzi -- drogi */
                zbuduj_pierwsza_droge_ktora_wybral_gracz(G, x, y);
        }
        for (int i = liczba_graczy - 1; i >= 0; i--)
        {
                Gracz G = lista_graczy.get(i);
                dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_osady();
                /* jakaś funkcja łapiąca wybrany przez gracza wierzchołek W */
                zbuduj_pierwsza_osade_ktora_wybral_gracz(G, x, y);

                dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_drogi(G);
                /* analogiczna funkcja co do osady, teraz dla krawędzi -- drogi */
                zbuduj_pierwsza_droge_ktora_wybral_gracz(G, x, y);
        }
        pierwsza_runda_rozdaj_surowce();
        nowa_runda();
};
public void nowa_runda(){
        int suma_na_kostkach;
        suma_na_kostkach = wylosuj_i_rozdaj_surowce();
        gracz_działania(suma_na_kostkach);



        if (czy_mamy_zwyciezce())
        {
                ostatnia_kolejka();
        }
        kolejka = (kolejka + 1) % liczba_graczy;
        nowa_runda();
        return;
}

public void gracz_działania(int suma_na_kostkach){

        boolean koniec_kolejki = budujemy_droge = budujemy_osade = budujemy_miasto = false;
        int x, y;
        Gracz G = lista_graczy.get(kolejka);

        if (suma_na_kostkach == 7) {
                /* TODO DZIAŁANIA ZWIIĄZANE ZE ZŁODZIEJEM */
                System.out.println("MOŻESZ PRZESTAWIĆ ZŁODZIEJA -- WYBIERZ POLE");
        }
        while (!koniec_kolejki) {
                /* TODO WYŁAP JAKĄ DECYZJĘ EKONOMICZNĄ PODJĄŁ */
                if (budujemy_droge) {
                        if (G.czy_mozna_postawic_droge()) {
                                dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
                                /* TODO ZNAJDUJESZ PUNKTY x i y */
                                zbuduj_droge_ktora_wybral_gracz(G, x, y);
                        } else {
                                System.out.println("NIE MOŻNA ZBUDOWAĆ DROGI --- ZBYT MAŁO SUROWCÓW lub WSZYSTKIE WYKORZYSTANE");
                        }
                } else if (budujemy_osade) {
                        if (G.czy_mozna_postawic_osade()) {
                                dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
                                /* TODO ZNAJDUJESZ PUNKTY x i y */
                                zbuduj_osade_ktora_wybral_gracz(G, x, y);
                        } else {
                                System.out.println("NIE MOŻNA ZBUDOWAĆ OSADY --- ZBYT MAŁO SUROWCÓW lub WSZYSTKIE WYKORZYSTANE");
                        }
                } else if (budujemy_miasto) {
                        if (G.czy_mozna_postawic_miasto()) {
                                dostepne_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
                                /* TODO ZNAJDUJESZ PUNKTY x i y */
                                zbuduj_miasto_ktore_wybral_gracz(G, x, y);
                        }
                }
                /* TODO WYŁAP CZY GRACZ KOŃCZY KOLEJKĘ -- koniec_kolejki = true */

        }
        /* TUTAJ TRZEBA ZAIMPLEMENTOWAĆ WSZYSKIE DZIAŁANIA JAKIE MOŻE PODJĄĆ GRACZ I POWIĄZAĆ Z NIMI DZIAŁANIA NA MAPIE */
}
/* FUNKCJIE DZIAŁAŃ GRACZA  -- Początek */
public void zbuduj_pierwsza_osade_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : dostepne_osady) {
                if (W.czy_to_tu(x, y)) {
                        G.zbuduj_pierwsza_lub_druga_osade(W);
                        return;
                }
        }
}
public void zbuduj_pierwsza_lub_druga_droge_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : dostepne_drogi) {
                for (Krawedz K : W.sasiednie_krawedzie) {
                        if (K.czy_to_tu(x, y)) {
                                G.zbuduj_pierwsza_lub_druga_droge(K);
                                return;
                        }
                }
        }
}
public void przestaw_zlodzieja_na_pole_ktore_wybral_gracz(Gracz G, int x, int y){
        for (Pole P : Mapa.lista_pol) {
                if (P.czy_to_tu(x, y)) {
                        P.dodaj_zlodzieja();
                } else {
                        P.usun_zlodzieja();
                }
        }
}
public void zbuduj_droge_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : dostepne_drogi) {
                for (Krawedz K : W.sasiednie_krawedzie) {
                        if (K.czy_to_tu(x, y)) {
                                G.zbuduj_droge(K);
                                return;
                        }

                }
        }
}
public void zbuduj_osade_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : dostepne_osady) {
                if (W.czy_to_tu(x, y)) {
                        G.zbuduj_osade(W);
                        return;
                }
        }
}
public void zbuduj_miasto_ktore_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : dostepne_miasta) {
                if (W.czy_to_tu(x, y)) {
                        G.zbuduj_miasto(W);
                        return;
                }
        }
}
/* FUNKCJIE DZIAŁAŃ GRACZA  -- Koniec */







/* FUNKCJE ROZDAWANIA SUROWCÓW  -- Początek */
public void pierwsza_runda_rozdaj_surowce() {
        for (int i = 2; i <= 12; i++)
                rozdaj_surowce(i);
}
public int wylosuj_i_rozdaj_surowce(){
        int kostka_pierwsza, kostka_druga, suma_na_kostkach;
        /* TODO WYŚWIETL WARTOŚCI NA KOSTCE PIERWSZEJ I DRUGIEJ */

        kostka_pierwsza = (int) (Math.random() * 6 + 1);
        kostka_druga = (int) (Math.random() * 6 + 1);
        suma_na_kostkach = kostka_pierwsza + kostka_druga;
        System.out.println("Kostka pierwsza: " + kostka_pierwsza + " Kostka druga: " + kostka_druga);
        rozdaj_surowce(suma_na_kostkach);
        return suma_na_kostkach;
}
public void rozdaj_surowce(int suma_na_kostkach){
        for (Pole P : this.Mapa.lista_pol)
        {
                if (P.wartosc == suma_na_kostkach)
                {
                        if (!P.zlodziej)
                        {
                                for (Wierzcholek W : P.lista_wierzcholkow)
                                {
                                        if (!W.is_empty)
                                        {
                                                daj_graczowi_nr(W.nr_gracza, P.surowiec, W.budynek);
                                        }
                                }
                        }
                }
        }
}
public void daj_graczowi_nr(int nr, String surowiec, int ile){
        Gracz G = lista_graczy.get((nr + (liczba_graczy - nr_gracza_rozpoczynajacego)) % liczba_graczy);
        G.dodaj(ile,surowiec);
}
/* FUNKCJE ROZDAWANIA SUROWCÓW  -- Koniec */



/* FUNKCJE FINALNE */
public boolean czy_mamy_zwyciezce(){
        for (Gracz G : lista_graczy)
                if (G.punkty >= 10)
                        return true;
        return false;
}
public void ostatnia_kolejka(){
        while (kolejka++ < liczba_graczy)
        {
                int suma_na_kostkach;
                suma_na_kostkach = wylosuj_i_rozdaj_surowce();
                gracz_działania(suma_na_kostkach);
                /* TODO */
        }
        wypisz_podium();
        return;

}
public void wypisz_podium(){
        /* TODO */
}

}
