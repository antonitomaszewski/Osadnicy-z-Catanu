import java.awt.Color;
import java.util.ArrayList;
public class Main {

public static void main(String[] args) {

        new Okno();
        Mapa Map = new Mapa();
        Gracz G = new Gracz(1, "A", new Color(120, 159, 249)); //błękit
        Gracz G2 = new Gracz(2, "B", Color.darkGray); //ciemna szarość
        Gracz G3 = new Gracz(3, "C", Color.pink); //róż
        Gracz G4 = new Gracz(4, "D", new Color(180,100,135));//fiolet
        Gracz G5 = new Gracz(5, "E", new Color(240,70,85)); //czerwony
        Gracz G6 = new Gracz(6, "F", new Color(20, 180, 150)); //seledyn
        Gracz G7 = new Gracz(7, "G", new Color(20, 180, 230));//błękit trzeba wybrać jeden
        Gracz G8 = new Gracz(8, "H", new Color(20, 120, 230));//niebieski
        Gracz G9 = new Gracz(9, "I", new Color(200, 200, 70));//oliwkowy

        ArrayList<Krawedz> dostepne_drogi  = new ArrayList<Krawedz>();
        ArrayList<Wierzcholek> dostepne_osady = new ArrayList<Wierzcholek>();
        ArrayList<Wierzcholek> dostepne_miasta = new ArrayList<Wierzcholek>();

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
        G4.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(30));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G4);
        G4.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G5.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(20));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G5);
        G5.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G6.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(20));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G6);
        G6.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G7.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G7);
        G7.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G8.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(13));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G8);
        G8.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));

        dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        G9.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(13));
        dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G9);
        G9.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));


        // dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        // G4.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(29));
        // dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G4);
        // G4.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));
        //
        // dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        // G3.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        // dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G3);
        // G3.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));
        //
        // dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        // G2.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(10));
        // dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G2);
        // G2.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(1));
        //
        // dostepne_osady = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_osady();
        // G.zbuduj_pierwsza_lub_druga_osade(dostepne_osady.get(15));
        // dostepne_drogi = Map.dostepne_lokalizacje_pierwszej_lub_drugiej_drogi(G);
        // G.zbuduj_pierwsza_lub_druga_droge(dostepne_drogi.get(0));



        // dostepne_drogi = G.znajdz_wszyskie_dostepne_lokalizacje_drog();
        // G.zbuduj_droge(dostepne_drogi.get(2));
        // dostepne_osady = G.znajdz_wszyskie_dostepne_lokalizacje_osad();
        // G.zbuduj_osade(dostepne_osady.get(0));
        //
        // dostepne_drogi = G2.znajdz_wszyskie_dostepne_lokalizacje_drog();
        // G2.zbuduj_droge(dostepne_drogi.get(2));
        // dostepne_osady = G2.znajdz_wszyskie_dostepne_lokalizacje_osad();
        // G2.zbuduj_osade(dostepne_osady.get(0));
        //
        // dostepne_miasta = G.znajdz_wszyskie_dostepne_lokalizacje_miast();
        // G.zbuduj_miasto(dostepne_miasta.get(0));
        //
        // dostepne_drogi = G3.znajdz_wszyskie_dostepne_lokalizacje_drog();
        // G3.zbuduj_droge(dostepne_drogi.get(6));
        // dostepne_osady = G3.znajdz_wszyskie_dostepne_lokalizacje_osad();
        // G3.zbuduj_osade(dostepne_osady.get(0));
        //
        // dostepne_drogi = G3.znajdz_wszyskie_dostepne_lokalizacje_drog();
        // G3.zbuduj_droge(dostepne_drogi.get(5));
        // dostepne_drogi = G3.znajdz_wszyskie_dostepne_lokalizacje_drog();
        // G3.zbuduj_droge(dostepne_drogi.get(7));
        // dostepne_osady = G3.znajdz_wszyskie_dostepne_lokalizacje_osad();
        // G3.zbuduj_osade(dostepne_osady.get(0));
        //
        //
        //
        //
        //
        // G.wypisz();
        return;
}
}
