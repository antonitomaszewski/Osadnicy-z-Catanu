import java.awt.Color;
import java.util.ArrayList;
public class Main {

public static void main(String[] args) {

        new Okno();
        Mapa Map = new Mapa();
        Gracz G = new Gracz(1, "A", new Color(170, 79, 49));
        Gracz G2 = new Gracz(2, "B", Color.darkGray);
        Gracz G3 = new Gracz(3, "C", Color.pink);
        ArrayList<Wierzcholek> dostepne_osady = new ArrayList<Wierzcholek>();
        ArrayList<Krawedz> dostepne_drogi  = new ArrayList<Krawedz>();

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
        G.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(2));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G2.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G2);
        G2.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G3.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G3);
        G3.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));



        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G3.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G3);
        G3.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G2.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G2);
        G2.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(15));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
        G.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(0));

        dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
        G.zbuduj_droge(dostepne_drogi.get(2));



        return;
}
}
