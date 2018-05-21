import java.util.ArrayList;
import java.awt.Color;

public class Mapa {
public static ArrayList<Pole> lista_pol = new ArrayList<Pole>();
public static ArrayList<Krawedz> lista_krawedzi = new ArrayList<Krawedz>();
public static ArrayList<Wierzcholek> lista_wierzcholkow = new ArrayList<Wierzcholek>();

/* PRZENIOSŁEM WARTOŚCI POCZĄTKOWE Z MAPY, DZIĘKI TEMU MOŻESZ JE ZMIENIAĆ W KLASIE I NIE PATRZEĆ NAWET DO ŚRODKA FUNKCJI */
public int X = 950;
public int Y = 570;
public int err = 7;
public int length = 100;


/* FUNKCJE DO ZNAJDYWANIA PIERWSZYCH DWÓCH OSAD I DRÓG  -- TYLKO ONE Z POZIOMU MAPY, NASTĘPNE U GRACZA */
public boolean czy_wierzcholek_dostepny(Wierzcholek W) {
        if (!W.is_empty) {
                return false;
        }
        for (Wierzcholek W_i : W.sasiednie_wierzcholki) {
                if (!W_i.is_empty) {
                        return false;
                }
        }
        return true;
}
public ArrayList<Wierzcholek> dostepne_lokalizacje_pierwszej_osady() {
        ArrayList<Wierzcholek> dostepne_osady = new ArrayList<Wierzcholek>();
        for (Wierzcholek W : lista_wierzcholkow) {
                if (czy_wierzcholek_dostepny(W)) {
                        dostepne_osady.add(W);
                }
        }
        return dostepne_osady;
}

public ArrayList<Krawedz> dostepne_lokalizacje_pierwszej_drogi(Gracz G) {
        ArrayList<Krawedz> dostepne_drogi = new ArrayList<Krawedz>();
        Wierzcholek pierwszy_wierzcholek = G.drogi_osady_i_miasta.get(0);
        for (Krawedz K : pierwszy_wierzcholek.sasiednie_krawedzie) {
                dostepne_drogi.add(K);
        }
        return dostepne_drogi;
}

public ArrayList<Wierzcholek> dostepne_lokalizacje_drugiej_osady() {
        ArrayList<Wierzcholek> dostepne_osady = new ArrayList<Wierzcholek>();
        for (Wierzcholek W : lista_wierzcholkow) {
                if (czy_wierzcholek_dostepny(W)) {
                        dostepne_osady.add(W);
                }
        }
        return dostepne_osady;
}
public ArrayList<Krawedz> dostepne_lokalizacje_drugiej_drogi(Gracz G) {
        ArrayList<Krawedz> dostepne_drogi = new ArrayList<Krawedz>();
        Wierzcholek pierwszy_wierzcholek = G.drogi_osady_i_miasta.get(2);
        for (Krawedz K : pierwszy_wierzcholek.sasiednie_krawedzie) {
                dostepne_drogi.add(K);
        }
        return dostepne_drogi;
}


/* STWÓRZ MAPĘ */
/* DZIAŁA  -- Wyniki 19 Pól, 54 Wierzchołki, 72 krawędzie są dobre */

public void losuj_wartosci_i_zasoby_na_polach() {
        ArrayList<Integer> wartosci = new ArrayList<Integer>();
        ArrayList<String> surowce = new ArrayList<String>();
        ArrayList<Color> surowce_kolory = new ArrayList<Color>();

        int pozycja;

        pozycja = 2;
        while (pozycja < 13)
        {
                if (pozycja == 2 || pozycja == 7 || pozycja == 12)
                {
                        wartosci.add(pozycja);
                        pozycja++;
                } else {
                        wartosci.add(pozycja);
                        wartosci.add(pozycja);
                        pozycja++;
                }
        }

        surowce.add("owca");
        surowce.add("siano");
        surowce.add("drewno");
        surowce.add("cegla");
        surowce.add("kamien");


        surowce_kolory.add(new Color(144, 255, 106));
        surowce_kolory.add(new Color(255, 228, 95));
        surowce_kolory.add(new Color(76, 128, 20));
        surowce_kolory.add(new Color(173, 92, 34));
        surowce_kolory.add(new Color(158, 158, 164));



        int ile_zostalo_wartosci_do_rozdania, ile_zostalo_surowcow_do_rozdania;
        int wylosowana_wartosc_indeks, wylosowany_surowiec_indeks;

        ile_zostalo_wartosci_do_rozdania = 19;
        ile_zostalo_surowcow_do_rozdania = 5;

        wylosowany_surowiec_indeks = (int) (Math.random() * ile_zostalo_surowcow_do_rozdania);

        int[] lista_ile_zostalo_surowca = new int[5];
        lista_ile_zostalo_surowca [0] = 4;
        lista_ile_zostalo_surowca [1] = 4;
        lista_ile_zostalo_surowca [2] = 4;
        lista_ile_zostalo_surowca [3] = 3;
        lista_ile_zostalo_surowca [4] = 3;
        lista_ile_zostalo_surowca [wylosowany_surowiec_indeks]++;

        for (Pole P : lista_pol)
        {
                /* Losowanie wartośći i surowca na danym Polu */
                wylosowana_wartosc_indeks = (int) (Math.random() * ile_zostalo_wartosci_do_rozdania);
                wylosowany_surowiec_indeks = (int) (Math.random() * ile_zostalo_surowcow_do_rozdania); // surowiec na polu
                /* Ustawianie wylosowanych wartości -- kolor jest skojarzony z surowcem, więc go nie należy losować, aby zachować spójność */
                P.wartosc = (int) wartosci.get(wylosowana_wartosc_indeks); // wartosc na polu
                P.surowiec = surowce.get(wylosowany_surowiec_indeks);
                P.kolor = surowce_kolory.get(wylosowany_surowiec_indeks);

                /* Usuwanie wartości z listy wartości, aby każda suma_kostek pojawiła się odpowiednią liczbę razy na planszy */
                wartosci.remove(wylosowana_wartosc_indeks);
                ile_zostalo_wartosci_do_rozdania--;

                /* Usuwanie surowca, jeśli właśnie wykorzystaliśmy jego ostatnią sztukę, w przeciwnym przypadku zmniejszamy jego ilość */
                if (lista_ile_zostalo_surowca [wylosowany_surowiec_indeks] > 1)
                {
                        lista_ile_zostalo_surowca [wylosowany_surowiec_indeks]--;
                } else {
                        surowce.remove(wylosowany_surowiec_indeks);
                        surowce_kolory.remove(wylosowany_surowiec_indeks);

                        for (int i = wylosowany_surowiec_indeks; i < 4; i++)
                                lista_ile_zostalo_surowca [i] = lista_ile_zostalo_surowca [i+1];
                        ile_zostalo_surowcow_do_rozdania--;
                        lista_ile_zostalo_surowca [ile_zostalo_surowcow_do_rozdania] = 0;

                }
        }
}

public boolean czy_dobra_odleglosc(Wierzcholek W1, Wierzcholek W2){
        int err_length, length_err;
        err_length = err + length;
        length_err = length - err;

        int odleglosc;
        odleglosc = (int) Math.sqrt(Math.pow(W1.x - W2.x, 2) + Math.pow(W1.y - W2.y, 2));
        if (odleglosc < err_length && odleglosc > length_err) {
                return true;
        }
        return false;
}
public void stworz_krawedzie_ustaw_wskazniki_na_sasiadow(){
        int err_length, length_err;

        err_length = err + length;
        length_err = length - err;

        int od_ktorego, ktory;
        od_ktorego = 0;

        for (Wierzcholek W : lista_wierzcholkow) {
                ktory = 0;
                for (Wierzcholek W_i : lista_wierzcholkow) {
                        if (ktory > od_ktorego) {
                                if (czy_dobra_odleglosc(W, W_i)) {
                                        W.sasiednie_wierzcholki.add(W_i);
                                        W_i.sasiednie_wierzcholki.add(W);

                                        Krawedz K = new Krawedz(W, W_i);
                                        W.sasiednie_krawedzie.add(K);
                                        W_i.sasiednie_krawedzie.add(K);
                                        lista_krawedzi.add(K);
                                }
                        }
                        ktory++;
                }
                od_ktorego++;
        }
}

public Mapa(){
        int high; /* X, Y współrzędne punktu P0, length i high długość i wysokość trójkąta równobocznego w sześciokącie foremnym, err -- błąd na jaki można sobie pozwolić, przy wyliczaniu współrzędnych */
        double rotation, default_rotation; /* obrót wektora o i * rotation, w przypadku pól typu 2 i 3, potrzebne default_rotation, bo są obkręcone o 30 stopni */

        int dx, dy; /* wektory przesunięć z punktu pola do wierzchołków i krawędzi */
        int px, py; /* wektory przesunięć środków pól */

        int[][] Wektory_wierzcholki = new int[6][2];
        int[][] Wektory_pola_typ_1 = new int[6][2];
        int[][] Wektory_pola_typ_2_i_3 = new int[6][2];
        int i, j;


        high = (int) (length * (Math.cos(Math.PI/6)));


        rotation = Math.PI/3; /* krok to 60 stopni */
        default_rotation = Math.PI/6;

        /* liczę wartości wektorów i wrzucam je do tablicy intów, pierwsza rozmiaru 7, aby używać dla krawędzi, tab[0] == tab[6] */
        for (i = 0; i < 6; i++)
        {
                dx = (int) (Math.sin((i % 6) * rotation) * length);
                dy = (int) (Math.cos((i % 6) * rotation) * length);

                Wektory_wierzcholki[i][0] = dx;
                Wektory_wierzcholki[i][1] = dy;
        }
        for (i = 0; i < 6; i++)
        {
                px = (int) (Math.sin(i * rotation) * 3 * length);
                py = (int) (Math.cos(i * rotation) * 3 * length);

                Wektory_pola_typ_1[i][0] = px;
                Wektory_pola_typ_1[i][1] = py;
        }
        for (i = 0; i < 6; i++)
        {
                px = (int) (Math.sin(i * rotation + default_rotation) * 2 * high);
                py = (int) (Math.cos(i * rotation + default_rotation) * 2 * high);

                Wektory_pola_typ_2_i_3[i][0] = px;
                Wektory_pola_typ_2_i_3[i][1] = py;
        }


        /* liczę pole P0, jego wierzchołki i krawędzie, od niego wszystkie inne pola uzależniam, wszystkie krawedzie i wierzchołki będą też trzymane w listach w mapie, będą to te same obiekty, co w listach pól */
        Pole P0 = new Pole(X,Y);
        lista_pol.add(P0);

        for (i = 0; i < 6; i++)
        {
                dx = Wektory_wierzcholki[i][0];
                dy = Wektory_wierzcholki[i][1];
                Wierzcholek W = new Wierzcholek(X + dx, Y + dy);

                P0.lista_wierzcholkow.add(W);
                lista_wierzcholkow.add(W);

        }

        /* Pola typu 1 wraz z wierzcholkami i krawedziami, tworze i dodaje te ktorych jeszcze nie ma w liscie_wierzcholkow/krawedzi */
        boolean czy_znalazl_podobny;
        for (i = 0; i < 6; i++)
        {
                px = Wektory_pola_typ_1[i][0];
                py = Wektory_pola_typ_1[i][1];
                Pole P_i = new Pole(X + px, Y + py);
                lista_pol.add(P_i);

                for (j = 0; j < 6; j++)
                {
                        czy_znalazl_podobny = false;
                        dx = P_i.x + Wektory_wierzcholki[j][0];
                        dy = P_i.y + Wektory_wierzcholki[j][1];

                        for (Wierzcholek W : lista_wierzcholkow)
                        {
                                if (Math.abs(W.x - dx) < err && Math.abs(W.y - dy) < err)
                                {
                                        czy_znalazl_podobny = true;
                                        P_i.lista_wierzcholkow.add(W);
                                        break;
                                }
                        }
                        if (!czy_znalazl_podobny)
                        {
                                Wierzcholek W = new Wierzcholek(dx, dy);
                                P_i.lista_wierzcholkow.add(W);
                                lista_wierzcholkow.add(W);
                        }

                }
        }

        /* 6 pól typ 2 -- dotykające krawędziami P0 */
        for (i = 0; i < 6; i++)
        {
                px = Wektory_pola_typ_2_i_3[i][0];
                py = Wektory_pola_typ_2_i_3[i][1];

                Pole P_i = new Pole(X + px, Y + py);
                lista_pol.add(P_i);

                for (j = 0; j < 6; j++)
                {
                        czy_znalazl_podobny = false;
                        dx = P_i.x + Wektory_wierzcholki[j][0];
                        dy = P_i.y + Wektory_wierzcholki[j][1];

                        for (Wierzcholek W : lista_wierzcholkow)
                        {
                                if (Math.abs(W.x - dx) < err && Math.abs(W.y - dy) < err)
                                {
                                        czy_znalazl_podobny = true;
                                        P_i.lista_wierzcholkow.add(W);
                                        break;
                                }
                        }
                        if (!czy_znalazl_podobny)
                        {
                                Wierzcholek W = new Wierzcholek(dx, dy);
                                P_i.lista_wierzcholkow.add(W);
                                lista_wierzcholkow.add(W);
                        }
                }
        }

        /* 6 pól typu 3 -- najbardziej oddalone od P0 */
        for (i = 0; i < 6; i++)
        {
                px = Wektory_pola_typ_2_i_3[i][0] * 2;
                py = Wektory_pola_typ_2_i_3[i][1] * 2;

                Pole P_i = new Pole(X + px, Y + py);
                lista_pol.add(P_i);

                for (j = 0; j < 6; j++)
                {
                        czy_znalazl_podobny = false;
                        dx = P_i.x + Wektory_wierzcholki[j][0];
                        dy = P_i.y + Wektory_wierzcholki[j][1];

                        for (Wierzcholek W : lista_wierzcholkow)
                        {
                                if (Math.abs(W.x - dx) < err && Math.abs(W.y - dy) < err)
                                {
                                        czy_znalazl_podobny = true;
                                        P_i.lista_wierzcholkow.add(W);
                                        break;
                                }
                        }
                        if (!czy_znalazl_podobny)
                        {
                                Wierzcholek W = new Wierzcholek(dx, dy);
                                P_i.lista_wierzcholkow.add(W);
                                lista_wierzcholkow.add(W);
                        }
                }
        }
        stworz_krawedzie_ustaw_wskazniki_na_sasiadow();
        losuj_wartosci_i_zasoby_na_polach();
}
}

/* Kolejność działań: Ustawiam pole początkowe P0 -- środek mapy, dla niego ustawiam 18 innych pól,
    rozbijam na 3 fory. Dla każdego środka pola, które są ustawione bezpośrednio z P0 tworzę wierzchołki i krawędzie,
    których jeszcze nie było na mapie, wrzucam je odpowiednio do listy_pól/wierzchołków/krawędzi w zmiennej Map (typ Mapa).
    Nie uśredniam, dzięki czemu wszystkie współrzędne krawędzi będą miały swój identyczny odpowiednik we współrzędnych jakiegoś wierzchołka.

    Z danego pola jest 6 możliwości osiągnięcia wierzchołka, tworzę w tym celu listę intów[7] T, gdzie T[0] == T[6].
    W polach leżących na osi P0 - wierzchołek_P0 środki pól są po prostu 3 * T[i]
    Aby osiągnąc dwa pozostałe rodzaje mam rotation_default = Math.PI/6
    i lecę po rotation + Math.PI/3 i drugą tablicę T2[6] wektorów do znalezienia środków pól typu 1 i 2, w typie 2 przemnażam przez 2 */
