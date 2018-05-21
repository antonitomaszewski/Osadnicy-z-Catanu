import java.awt.Color;
import java.util.ArrayList;
public class Main {

public static void main(String[] args) {

        new Okno();
        Mapa Map = new Mapa();
        Gracz G = new Gracz(1, "A", Color.BLACK);
        Gracz G2 = new Gracz(2, "B", Color.BLUE);

        G.pierwsza_osada(Map.lista_wierzcholkow.get(0));
        ArrayList<Krawedz> drogi = Map.dostepne_lokalizacje_pierwszej_drogi(G);
        G.pierwsza_droga(drogi.get(0));

        ArrayList<Wierzcholek> osady_i_miasta = Map.dostepne_lokalizacje_pierwszej_osady();
        G2.pierwsza_osada(osady_i_miasta.get(0));
        drogi = Map.dostepne_lokalizacje_pierwszej_drogi(G2);
        G2.pierwsza_droga(drogi.get(0));




        G.druga_osada(Map.lista_wierzcholkow.get(15));
        drogi = Map.dostepne_lokalizacje_drugiej_drogi(G);
        G.druga_droga(drogi.get(0));

        osady_i_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
        for (Wierzcholek W : osady_i_miasta) {
                G.zbuduj_miasto(W);
        }

        drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
        for (Krawedz K : drogi) {
                G.zbuduj_droge(K);
        }

        osady_i_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
        for (Wierzcholek W : osady_i_miasta) {
                G.zbuduj_osade(W);
        }

        drogi = G2.znajdz_wszyskie_dostepne_lokalizacje_drog();
        for (Krawedz K : drogi) {
                G2.zbuduj_droge(K);
        }
        drogi = G2.znajdz_wszyskie_dostepne_lokalizacje_drog();
        for (Krawedz K : drogi) {
                G2.zbuduj_droge(K);
        }
        osady_i_miasta = G2.znajdz_wszyskie_dostepne_lokalizacje_osad();
        for (Wierzcholek W : osady_i_miasta) {
                G2.zbuduj_osade(W);
        }
        drogi = G2.znajdz_wszyskie_dostepne_lokalizacje_drog();
        for (Krawedz K : drogi) {
                G2.zbuduj_droge(K);
        }
        osady_i_miasta = G2.znajdz_wszyskie_dostepne_lokalizacje_osad();
        for (Wierzcholek W : osady_i_miasta) {
                G2.zbuduj_osade(W);
        }
        drogi = G2.znajdz_wszyskie_dostepne_lokalizacje_drog();
        for (Krawedz K : drogi) {
                G2.zbuduj_droge(K);
        }
        osady_i_miasta = G2.znajdz_wszyskie_dostepne_lokalizacje_osad();
        for (Wierzcholek W : osady_i_miasta) {
                G2.zbuduj_osade(W);
        }
        drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
        for (Krawedz K : drogi) {
                G.zbuduj_droge(K);
        }
        return;
}
}
