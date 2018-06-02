import java.awt.Color;
import java.util.ArrayList;
/* WAŻNE DO TWORZENIA KLAS::
   Jeśli mam pole, które nie jest zainicjowane w klasie, ale ma wartość domyślną, to spokojnie mogę ją ustawić później
   Jeśli mam pole, które nie ma ani wartości domyślnej, ani nie pojawia się, w konstruktorze, to już się do niego nie dostanę,
   chyba, że jest to pole typu wbudowanego, wtedy nie będzie problemu nim operować */


public class Gra {
    public static Mapa Mapa = new Mapa();
    public static int liczba_graczy = 4;
    public static int nr_gracza_rozpoczynajacego = 1;
    public static int kolejka = 0;
    public static int runda = 0;

    public static ArrayList<Gracz> lista_graczy = new ArrayList<Gracz>();


    /* POLA DOSTĘPNE DO UŻYCIA W CZASIE RUCHU GRACZA -- Początek */
    public static ArrayList<Krawedz> dostepne_drogi = new ArrayList<Krawedz>();
    public static ArrayList<Wierzcholek> dostepne_osady = new ArrayList<Wierzcholek>();
    public static ArrayList<Wierzcholek> dostepne_miasta = new ArrayList<Wierzcholek>();
    public static int x = -1;
    public static int y = -1;



    public static int kostka_pierwsza = -1;
    public static int kostka_druga = -1;
    public static int suma_na_kostkach = -1;

    public static boolean koniec_kolejki = false;
    public static boolean budujemy_droge = false;
    public static boolean budujemy_osade = false;
    public static boolean budujemy_miasto = false;


    /* POLA DOSTĘPNE DO UŻYCIA W CZASIE RUCHU GRACZA -- Koniec */
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



        for (Gracz G : lista_graczy) {
            dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
                /* jakaś funkcja łapiąca wybrany przez gracza wierzchołek W */
            do {
                        /* TODO ŁAP x i y */
            } while(!zbuduj_pierwsza_lub_druga_osade_ktora_wybral_gracz(G, x, y));

            dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
                /* analogiczna funkcja co do osady, teraz dla krawędzi -- drogi */
            do {
                        /* TODO ŁAP x i y */
            } while(!zbuduj_pierwsza_lub_druga_droge_ktora_wybral_gracz(G, x, y));
        }
        for (int i = liczba_graczy - 1; i >= 0; i--)
        {
            Gracz G = lista_graczy.get(i);
            dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
                /* jakaś funkcja łapiąca wybrany przez gracza wierzchołek W */
            do {
                        /* TODO ŁAP x i y */
            } while(!zbuduj_pierwsza_lub_druga_osade_ktora_wybral_gracz(G, x, y));

            dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
                /* analogiczna funkcja co do osady, teraz dla krawędzi -- drogi */
            do {
                        /* TODO ŁAP x i y */
            } while(!zbuduj_pierwsza_lub_druga_droge_ktora_wybral_gracz(G, x, y));
        }
        pierwsza_runda_rozdaj_surowce();
        nowa_runda();
    };
    public void nowa_runda(){
        runda++;
        better_gracz_dzialania();



        if (czy_mamy_zwyciezce())
        {
            ostatnia_kolejka();
        }
        kolejka = (kolejka + 1) % liczba_graczy;
        nowa_runda();
        return;
    }


    public void ustaw_poczatkowe_listy_dostepnych_drog_osad_i_miast(){
        Gracz G = lista_graczy.get(kolejka);

        if (G.czy_mozna_postawic_droge()) {
            dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
        } else {
            dostepne_drogi = new ArrayList<Krawedz>();
        }
        if (G.czy_mozna_postawic_osade()) {
            dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
        } else {
            dostepne_osady = new ArrayList<Wierzcholek>();
        }
        if (G.czy_mozna_postawic_miasto()) {
            dostepne_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
        } else {
            dostepne_miasta = new ArrayList<Wierzcholek>();
        }
    }
    public void better_gracz_dzialania(){
        Gracz G = lista_graczy.get(kolejka);

        /* TODO ACTION LISTENER NA LOSOWANIE */
        if (suma_na_kostkach == 7) {
                /* TODO PRZESTAW ZŁODZIEJA */
            boolean czy_znalazl = false;
            boolean czy_wybrano_wspolrzedne = false;
            while (!czy_znalazl) {
                while(!czy_wybrano_wspolrzedne) {
                                /* Łap współrzędne złodzieja x i y*/
                }
                czy_znalazl = przestaw_zlodzieja_na_pole_ktore_wybral_gracz(x, y);
            }
        }

        ustaw_poczatkowe_listy_dostepnych_drog_osad_i_miast();

        while(!koniec_kolejki) {
            x = y = -1;
            if (budujemy_droge) {
                        /* TODO ZŁAP WSPÓŁRZĘDZNE KTÓRE WYBRAŁ GRACZ */
                if (zbuduj_droge_ktora_wybral_gracz(G, x, y)) {
                    if (G.czy_mozna_postawic_droge()) {
                        dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
                    } else {
                        dostepne_drogi = new ArrayList<Krawedz>();
                    }
                    if (G.czy_mozna_postawic_osade()) {
                        dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
                    } else {
                        dostepne_osady = new ArrayList<Wierzcholek>();
                    }
                }
            } else if (budujemy_osade) {
                        /* TODO ZŁAP WSPÓŁRZĘDZNE KTÓRE WYBRAŁ GRACZ */
                if (zbuduj_osade_ktora_wybral_gracz(G, x, y)) {
                    if (G.czy_mozna_postawic_droge()) {
                        dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
                    } else {
                        dostepne_drogi = new ArrayList<Krawedz>();
                    }
                    if (G.czy_mozna_postawic_osade()) {
                        dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
                    } else {
                        dostepne_osady = new ArrayList<Wierzcholek>();
                    }
                    if (G.czy_mozna_postawic_miasto()) {
                        dostepne_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
                    } else {
                        dostepne_miasta = new ArrayList<Wierzcholek>();
                    }
                }
            } else if (budujemy_miasto) {
                        /* TODO ZŁAP WSPÓŁRZĘDZNE KTÓRE WYBRAŁ GRACZ */
                if (zbuduj_miasto_ktore_wybral_gracz(G, x, y)) {
                    if (G.czy_mozna_postawic_osade()) {
                        dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
                    } else {
                        dostepne_osady = new ArrayList<Wierzcholek>();
                    }
                    if (G.czy_mozna_postawic_miasto()) {
                        dostepne_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
                    } else {
                        dostepne_miasta = new ArrayList<Wierzcholek>();
                    }
                }
            }
            budujemy_droge = budujemy_osade = budujemy_miasto = false;
        }
    }
//    public void gracz_dzialania(){
//        Gracz G = lista_graczy.get(kolejka);
//
//
//        if (suma_na_kostkach == 7) {
//                /* TODO DZIAŁANIA ZWIIĄZANE ZE ZŁODZIEJEM */
//            System.out.println("MOŻESZ PRZESTAWIĆ ZŁODZIEJA -- WYBIERZ POLE");
//        }
//        while (!koniec_kolejki) {
//                /* TODO WYŁAP JAKĄ DECYZJĘ EKONOMICZNĄ PODJĄŁ */
//            if (G.czy_mozna_postawic_droge()) {
//                dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
//            } else {
//                dostepne_drogi = new ArrayList<Krawedz>();
//            }
//            if (G.czy_mozna_postawic_osade()) {
//                dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
//            } else {
//                dostepne_osady = new ArrayList<Wierzcholek>();
//            }
//            if (G.czy_mozna_postawic_miasto()) {
//                dostepne_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
//            } else {
//                dostepne_miasta = new ArrayList<Wierzcholek>();
//            }
//                /* WYŚWIETLANE SĄ WSZYSTKIE MOŻLIWOŚĆI ++ PEWNIE JEŚLI !G.czy_mozna_postawic_() to wtedy przycisk, jest nieaktywny */
//            if (dostepne_drogi.size() > 0) {
//                        /* WYŚWIETLANY PODGLĄD + PRZYCISK BĘDZIE AKTYWNY */
//            }
//            if (dostepne_osady.size() > 0) {
//                        /* WYŚWIETLANY PODGLĄD + PRZYCISK BĘDZIE AKTYWNY */
//            }
//            if (dostepne_miasta.size() > 0) {
//                        /* WYŚWIETLANY PODGLĄD + PRZYCISK BĘDZIE AKTYWNY */
//            }
//
//                /* TERAZ DOPÓKI GRACZ NIE WYBIERZE MIĘDZY ::  BUDOWA CZEGOŚ <> KONIEC KOLEJKI :: CZEKAMY */
//                /* WYBRAŁ JEDNĄ Z 4 MOŻLIWOŚCI, jeśli budowa, to trzeba będzie */
//            wlasnie_wybrano = false;
//                /* alternatywne budowanie: */
//            if (budujemy_droge) {
//                do {
//                                /* DOPÓKI CHCESZ BUDOWAĆ DROGĘ I DOPÓKI NIE UDAŁO CI SIĘ KLIKNĄĆ NA MAPĘ W MIEJSCU, W KTÓRYM MOŻESZ BUDOWAĆ */
//
//                } while (budujemy_droge && !zbuduj_droge_ktora_wybral_gracz(G, x, y));
//            }
//            if (budujemy_droge) {
//                if (G.czy_mozna_postawic_droge()) { // skoro mógł wybrać budujemy_droge, to nie trzeba będzie tego warunku już sprawdzać
//                    dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
//                                /* TODO ZNAJDUJESZ PUNKTY x i y */
//                    zbuduj_droge_ktora_wybral_gracz(G, x, y); // w ostatecznej wersji tylko ta linijka, skoro wybrał drogę, to możliwe, że należałoby je wyróżnić spośród wszystkich dostępnych możliwości budowy, analogicznie z osadą i miastem
//                    while(budujemy_droge && !wlasnie_wybrano) {
//                                        /* TODO WYZNACZ  WSPÓŁRZĘDNE */
//                    } if (wlasnie_wybrano) {
//                        zbuduj_droge_ktora_wybral_gracz(G, x, y);
//                    }
//                } else {
//                    System.out.println("NIE MOŻNA ZBUDOWAĆ DROGI --- ZBYT MAŁO SUROWCÓW lub WSZYSTKIE WYKORZYSTANE");
//                }
//            }
//            wlasnie_wybrano = false;
//            if (budujemy_osade) {
//                if (G.czy_mozna_postawic_osade()) {
//                    dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
//                                /* TODO ZNAJDUJESZ PUNKTY x i y */
//                    zbuduj_osade_ktora_wybral_gracz(G, x, y);
//                } else {
//                    System.out.println("NIE MOŻNA ZBUDOWAĆ OSADY --- ZBYT MAŁO SUROWCÓW lub WSZYSTKIE WYKORZYSTANE");
//                }
//            }
//            wlasnie_wybrano = false;
//            if (budujemy_miasto) {
//                if (G.czy_mozna_postawic_miasto()) {
//                    dostepne_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
//                                /* TODO ZNAJDUJESZ PUNKTY x i y */
//                    zbuduj_miasto_ktore_wybral_gracz(G, x, y);
//                }
//            }
//                /* TODO WYŁAP CZY GRACZ KOŃCZY KOLEJKĘ -- koniec_kolejki = true */
//
//        }
//        /* TUTAJ TRZEBA ZAIMPLEMENTOWAĆ WSZYSKIE DZIAŁANIA JAKIE MOŻE PODJĄĆ GRACZ I POWIĄZAĆ Z NIMI DZIAŁANIA NA MAPIE */
//    }
    /* FUNKCJIE DZIAŁAŃ GRACZA  -- Początek */
    public boolean zbuduj_pierwsza_lub_druga_osade_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : dostepne_osady) {
            if (W.czy_to_tu(x, y)) {
                G.zbuduj_pierwsza_lub_druga_osade(W);
                return true;
            }
        }
        return false;
    }
    public boolean zbuduj_pierwsza_lub_druga_droge_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Krawedz K : G.drogi_osady_i_miasta.get(G.drogi_osady_i_miasta.size() - 1).sasiednie_krawedzie) {
                if (K.czy_to_tu(x, y)) {
                    G.zbuduj_pierwsza_lub_druga_droge(K);
                    return true;
                }
            }
        return false;
    }

    public boolean przestaw_zlodzieja_na_pole_ktore_wybral_gracz(int x, int y){
        boolean czy_znalazl = false;
        for (Pole P : Mapa.lista_pol) {
            if (P.czy_to_tu(x, y)) {
                P.dodaj_zlodzieja();
                czy_znalazl = true;
            } else {
                P.usun_zlodzieja();
            }
        }
        return czy_znalazl;
    }
    public boolean zbuduj_droge_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : G.drogi_osady_i_miasta) {
            for (Krawedz K : W.sasiednie_krawedzie) {
                if (K.czy_to_tu(x, y)) {
                    G.zbuduj_droge(K);
                    return true;
                }

            }
        }
        return false;
    }
    public boolean zbuduj_osade_ktora_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : G.drogi_osady_i_miasta) {
            if (W.czy_to_tu(x, y)) {
                G.zbuduj_osade(W);
                return true;
            }
        }
        return false;
    }
    public boolean zbuduj_miasto_ktore_wybral_gracz(Gracz G, int x, int y){
        for (Wierzcholek W : G.drogi_osady_i_miasta) {
            if (W.czy_to_tu(x, y)) {
                G.zbuduj_miasto(W);
                return true;
            }
        }
        return false;
    }
/* FUNKCJIE DZIAŁAŃ GRACZA  -- Koniec */







    /* FUNKCJE ROZDAWANIA SUROWCÓW  -- Początek */
    public void pierwsza_runda_rozdaj_surowce() {
        for (int i = 2; i <= 12; i++)
            rozdaj_surowce(i);
    }
    public static void wylosuj_i_rozdaj_surowce(){

        /* TODO WYŚWIETL WARTOŚCI NA KOSTCE PIERWSZEJ I DRUGIEJ */

        kostka_pierwsza = (int) (Math.random() * 6 + 1);
        kostka_druga = (int) (Math.random() * 6 + 1);
        suma_na_kostkach = kostka_pierwsza + kostka_druga;
        System.out.println("Kostka pierwsza: " + kostka_pierwsza + " Kostka druga: " + kostka_druga);
        rozdaj_surowce(suma_na_kostkach);
        // return suma_na_kostkach;
    }
    public static void rozdaj_surowce(int suma_na_kostkach){
        for (Pole P : Mapa.lista_pol)
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
    public static void daj_graczowi_nr(int nr, String surowiec, int ile){
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
            wylosuj_i_rozdaj_surowce();
            better_gracz_dzialania();
                /* TODO */
        }
        wypisz_podium();
        return;

    }
    public void wypisz_podium(){
        /* TODO */
    }

}