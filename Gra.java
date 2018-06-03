import java.awt.Color;
import java.util.ArrayList;
/* WAŻNE DO TWORZENIA KLAS::
   Jeśli mam pole, które nie jest zainicjowane w klasie, ale ma wartość domyślną, to spokojnie mogę ją ustawić później
   Jeśli mam pole, które nie ma ani wartości domyślnej, ani nie pojawia się, w konstruktorze, to już się do niego nie dostanę,
   chyba, że jest to pole typu wbudowanego, wtedy nie będzie problemu nim operować */


public class Gra extends Thread {

public void run() {

}
public static void main(String args[]) {
        String[] imiona = {"Antoni", "Andrzej", "Basia", "Domin"};
        Color[] kolory = {Color.BLACK, Color.PINK, Color.RED, Color.BLUE};
        (new Gra(imiona, kolory)).start();
}






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
public static boolean wymiana = false;
public static boolean potwierdzono_transakcje_i_zamknieto = false;

public static boolean wylosowano = false;
public static boolean wybrano_wspolrzedne = true;
public static boolean czy_mozna_losowac = false;

public static boolean czas_akcji_gracza = false;

public static Wymiana Wymiana = new Wymiana();
public static Okno Okno;

public void wylosuj_przez_i_kolejek_surowce(int n) {
        while (n > 0) {
                wylosuj_i_rozdaj_surowce();
                n--;
        }
}
public void defaultowo() {
        for (Gracz G : lista_graczy) {
                dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
                G.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(4));
                dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
                G.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(0));
        }
        int i = 3;
        while (i >= 0) {
                Gracz G = lista_graczy.get(i);
                dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
                G.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(0));
                dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
                G.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(0));
                i--;
        }
}
public void samemu() {
        for (Gracz G : lista_graczy) {
                dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
                /* jakaś funkcja łapiąca wybrany przez gracza wierzchołek W */
//            wybrano_wspolrzedne = false;
                do {
                        while (!wybrano_wspolrzedne) {
                                try {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                } catch (InterruptedException ex) {
                                        Gra.currentThread().interrupt();
                                }
                        }
                        wybrano_wspolrzedne = false;
//                wybrano_wspolrzedne = false;
                } while(!zbuduj_pierwsza_lub_druga_osade_ktora_wybral_gracz(G, x, y));

//            wybrano_wspolrzedne = false;
                dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
                /* analogiczna funkcja co do osady, teraz dla krawędzi -- drogi */
                do {
                        while (!wybrano_wspolrzedne) {
                                try {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                } catch (InterruptedException ex) {
                                        Gra.currentThread().interrupt();
                                }
                        }
                        wybrano_wspolrzedne = false;
//                wybrano_wspolrzedne = false;
                } while(!zbuduj_pierwsza_lub_druga_droge_ktora_wybral_gracz(G, x, y));
        }
        for (int i = liczba_graczy - 1; i >= 0; i--)
        {
                Gracz G = lista_graczy.get(i);

                dostepne_osady = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
                /* jakaś funkcja łapiąca wybrany przez gracza wierzchołek W */
                do {
                        while (!wybrano_wspolrzedne) {
                                try {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                } catch (InterruptedException ex) {
                                        Gra.currentThread().interrupt();
                                }
                        }
                        wybrano_wspolrzedne = false;
                } while(!zbuduj_pierwsza_lub_druga_osade_ktora_wybral_gracz(G, x, y));

                dostepne_drogi = Mapa.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
                /* analogiczna funkcja co do osady, teraz dla krawędzi -- drogi */
                do {
                        while (!wybrano_wspolrzedne) {
                                try {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                } catch (InterruptedException ex) {
                                        Gra.currentThread().interrupt();
                                }
                        }
                        wybrano_wspolrzedne = false;
                } while(!zbuduj_pierwsza_lub_druga_droge_ktora_wybral_gracz(G, x, y));
        }
}
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
        Okno = new Okno();

        // samemu();
        defaultowo();
        lista_graczy.get(nr_gracza_rozpoczynajacego - 1).punkty = 10;
        pierwsza_runda_rozdaj_surowce();
        //wylosuj_przez_i_kolejek_surowce(200);
        // lista_graczy.get(0).punkty = 10;
        nowa_runda();
}
public void nowa_runda(){
        ++runda;
        wylosowano = false;
        wybrano_wspolrzedne = false;
        better_gracz_dzialania();



        if (czy_mamy_zwyciezce())
        {
                ostatnia_kolejka();
        } else {
                kolejka = (kolejka + 1) % liczba_graczy;
                nowa_runda();
        }
}
public static void ustaw_poczatkowe_listy_dostepnych_drog_osad_i_miast(){
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
        czy_mozna_losowac = true;
        potwierdzono_transakcje_i_zamknieto = false;
        /* TODO ACTION LISTENER NA LOSOWANIE */
//        wylosuj_i_rozdaj_surowce();
        while (!wylosowano) {
                try {
                        Gra.sleep(1000);
                        Gra.currentThread().interrupt();
                } catch (InterruptedException ex) {
                        Gra.currentThread().interrupt();
                }
        }

        // System.out.println("Wylosowano ziom");
//        if (suma_na_kostkach == 7) {
//                /* TODO PRZESTAW ZŁODZIEJA */
//            boolean czy_znalazl = false;
//            while (!czy_znalazl) {
//                while(!wybrano_wspolrzedne)
//                    ;
////                wybrano_wspolrzedne = false;
//                czy_znalazl = przestaw_zlodzieja_na_pole_ktore_wybral_gracz(x, y);
//            }
//        }

        ustaw_poczatkowe_listy_dostepnych_drog_osad_i_miast();

        x = y = -1;
        czas_akcji_gracza = true;
        while(!koniec_kolejki) {

                try
                {
                        Gra.sleep(1000);
                        Gra.currentThread().interrupt();
                }
                catch(InterruptedException ex)
                {
                        Gra.currentThread().interrupt();
                }
                if (budujemy_droge) {
                        /* TODO ZŁAP WSPÓŁRZĘDZNE KTÓRE WYBRAŁ GRACZ */
                        while(!wybrano_wspolrzedne) {
                                try
                                {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                }
                                catch(InterruptedException ex)
                                {
                                        Gra.currentThread().interrupt();
                                }
                        }

                        wybrano_wspolrzedne = false;
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
                        while(!wybrano_wspolrzedne) {
                                try
                                {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                }
                                catch(InterruptedException ex)
                                {
                                        Gra.currentThread().interrupt();
                                }
                        }

                        wybrano_wspolrzedne = false;
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
                        while(!wybrano_wspolrzedne) {
                                try
                                {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                }
                                catch(InterruptedException ex)
                                {
                                        Gra.currentThread().interrupt();
                                }
                        }

                        wybrano_wspolrzedne = false;
//                wybrano_wspolrzedne = false;
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
                else if (wymiana) {
                        // new Wymiana();

                        Wymiana.setAlwaysOnTop (true);
                        Okno.setAlwaysOnTop (false);
                        while(!potwierdzono_transakcje_i_zamknieto) {
                                try
                                {
                                        Gra.sleep(1000);
                                        Gra.currentThread().interrupt();
                                }
                                catch(InterruptedException ex)
                                {
                                        Gra.currentThread().interrupt();
                                }
                        }
                        Wymiana.setAlwaysOnTop (false);
                        Okno.setAlwaysOnTop (true);
                        potwierdzono_transakcje_i_zamknieto = false;
                }
                // Wymiana.setAlwaysOnTop (false);
                wymiana = budujemy_droge = budujemy_osade = budujemy_miasto = wybrano_wspolrzedne = false;
                potwierdzono_transakcje_i_zamknieto = false;

        }

        koniec_kolejki = false;
        czas_akcji_gracza = false;
}


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
        while ((++kolejka) < liczba_graczy)
        {
                ++runda;
                wylosowano = false;
                wybrano_wspolrzedne = false;
                better_gracz_dzialania();
                System.out.println(kolejka + " " + Gra.lista_graczy.get(kolejka).imie);
        }
        // wypisz_podium();
}

public void wypisz_podium(){
        // Gracz[] punkty_graczy = new Gracz[4];
        // int i;
        // for (Gracz G : lista_graczy) {
        //         i = 0;
        //         for (Gracz G1 : lista_graczy) {
        //                 if (G1.punkty > G.punkty) {
        //                         i++;
        //                 }
        //         }
        //         punkty_graczy[i] = G;
        //
        // }
        for (Gracz G : lista_graczy)
                G.wypisz();

}

}
