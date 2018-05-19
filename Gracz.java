public class Gracz {
    public int numer = -1;
    public String imie = "";
    public Color kolor = Color.White;
    public int punkty = 0;

    public int postawione_drogi = 0;
    public int postawione_osady = 0;
    public int postawione_miasta = 0;
    2
    public Surowce surowce = new Surowce();
    public ArrayList<Wierzcholek> osady_i_miasta = new ArrayList<Wierzcholek>();
    public ArrayList<Krawedz> drogi = new ArrayList<Krawedz>();

    public Gracz()
    {
      this.numer = -1;
      this.imie = "";
      this.kolor = Color.White;
      this.punkty = 0;
      this.postawione_drogi = 0;
      this.postawione_osady = 0;
      this.postawione_miasta = 0;
    }
    public Gracz(int nr, String nazwa, Color color)
    {
      this.numer = nr;
      this.imie = nazwa;
      this.kolor = color;
      this.punkty = 0;
      this.postawione_drogi = 0;
      this.postawione_osady = 0;
      this.postawione_miasta = 0;
    }

    public void pierwsza_osada(W)
    {
      W.zbuduj_osade(this.numer, this.imie, this.kolor);
      osady_i_miasta.add(W);
      this.punkty++;
      this.postawione_osady++;
      return;
    }
    public void pierwsza_droga(K)
    {
      K.zbuduj_droge(this.numer, this.imie, this.kolor);
      drogi.add(K);
      this.postawione_drogi++;
      return;
    }
    public void druga_osada(W)
    {
      W.zbuduj_osade(this.numer, this.imie, this.kolor);
      osady_i_miasta.add(W);
      this.punkty++;
      this.postawione_osady++;
      return;
    }
    public void druga_droga(K)
    {
      K.zbuduj_droge(this.numer, this.imie, this.kolor);
      drogi.add(K);
      this.postawione_drogi++;
      return;
    }

    public void dodaj(int ile, String surowiec)
    {
      this.surowce.dodaj(ile, surowiec);
    }
    public void zabierz(int ile, String surowiec)
    {
      this.surowce.zabierz(ile, surowiec);
    }

/*
    public void dodaj_owca(int ile)
    {
      this.surowce.dodaj_owca(ile);
      return;
    }
    public void dodaj_siano(int ile)
    {
      this.surowce.dodaj_siano(ile);
      return;
    }
    public void dodaj_drewno(int ile)
    {
      this.surowce.dodaj_drewno(ile);
      return;
    }
    public void dodaj_cegla(int ile)
    {
      this.surowce.dodaj_cegla(ile);
      return;
    }
    public void dodaj_kamien(int ile)
    {
      this.surowce.dodaj_kamien(ile);
      return;
    }

    public void zabierz_owca(int ile)
    {
      this.surowce.zabierz_owca(ile);
      return;
    }
    public void zabierz_siano(int ile)
    {
      this.surowce.zabierz_siano(ile);
      return;
    }
    public void zabierz_drewno(int ile)
    {
      this.surowce.zabierz_drewno(ile);
      return;
    }
    public void zabierz_cegla(int ile)
    {
      this.surowce.zabierz_cegla(ile);
      return;
    }
    public void zabierz_kamien(int ile)
    {
      this.surowce.zabierz_kamien(ile);
      return;
    }
*/

    public void zbuduj_droge(Krawedz K)
    {
      K.zbuduj_droge(this.numer);
      drogi.add(K);
      this.zabierz(1, "drewno");
      this.zabierz(1, "cegla");
      return;
    }
    public void zbuduj_osade(Wierzcholek W)
    {
      W.zbuduj_osade(this.numer, this.imie, this.kolor);
      osady_i_miasta.add(W);
      this.zabierz(1, "owca");
      this.zabierz(1, "siano");
      this.zabierz(1, "drewno");
      this.zabierz(1, "cegla");
      this.punkty++;
      this.postawione_osady++;
      return;
    }
    public void zbuduj_miasto(Wierzcholek W)
    {
      W.zbuduj_miasto(this.numer); // zakładam, że wtedy wartość w wierzchołku W, w którym gracz ma już osade zmieni się także w liscie osad_i_miast
      this.zabierz(2, "siano");
      this.zabierz(3, "kamien");
      this.punkty++;
      this.postawione_miasta++;
      this.postawione_osady--;
      return;
    }

    public boolean czy_mozna_postawic_droge()
    {
      return (this.surowce.drewno > 0 && this.surowce.cegla > 0 && this.postawione_drogi < 15);
    }
    public boolean czy_mozna_postawic_osade()
    {
      return (this.surowce.owca > 0 && this.surowce.siano > 0 && this.surowce.drewno > 0 && this.surowce.cegla > 0 && this.postawione_osady < 5);
    }
    public boolean czy_mozna_postawic_miasto()
    {
      return (this.surowce.siano > 1 && this.surowce.kamien > 2 && this.postawione_miasta < 4);
    }
}
