/* WAŻNE DO TWORZENIA KLAS::
  Jeśli mam pole, które nie jest zainicjowane w klasie, ale ma wartość domyślną, to spokojnie mogę ją ustawić później
  Jeśli mam pole, które nie ma ani wartości domyślnej, ani nie pojawia się, w konstruktorze, to już się do niego nie dostanę,
  chyba, że jest to pole typu wbudowanego, wtedy nie będzie problemu nim operować */


public class Gra{
  public Mapa Mapa = new Mapa();
  public int liczba_graczy = 4;
  public int nr_gracza_rozpoczynajacego = 1;
  public int kolejka = 0;
  /*
  public Gracz Gracz_1;
  public Gracz Gracz_2;
  public Gracz Gracz_3;
  public Gracz Gracz_4;
  */
  public ArrayList<Gracz> lista_graczy = new ArrayList<Gracz>();
  /*
  public int kostka_pierwsza = -1;
  public int kostka_pierwsza = -1;
  */
  /* Po Każdej kolejce sprawdzamy, dopóki wartość jest false, gramy dalej, w momencie pojawienia się pierwszego kończymy grę (ewentualnie gramy tę kolejkę do końca i dopiero wteddy kończymy ) */
  public boolean czy_mamy_zwyciezce(){
    /*
    if (Gracz_1.punkty >= 10 || Gracz_2.punkty >= 10 || Gracz_3.punkty >= 10 || Gracz_4.punkty >= 10)
    {
      return true;
    }
    */
    for (Gracz G : lista_graczy)
      if (G.punkty >= 10)
        return true;
    return false;
  }

  public Gra(String[] imiona, Color[] kolory){
    liczba_graczy = imiona.length;
    nr_gracza_rozpoczynajacego = (int) (Math.random() * liczba_graczy + 1));
    /*
    int i;
    for (i = 0; i < liczba_graczy; i++)
    {
      if (i == 0) {
        Gracz_1 = new Gracz(1, imiona[i], kolory[i]);
        lista_graczy.add(Gracz_1);
      } else if (i % liczba_graczy == 1) {
        Gracz_2 = new Gracz(2, imiona[i], kolory[i]);
        lista_graczy.add(Gracz_2)
      } else if (i % liczba_graczy == 2) {
        Gracz_3 = new Gracz(3, imiona[i], kolory[i]);
        lista_graczy.add(Gracz_3);
      } else if (i % liczba_graczy == 3) {
        Gracz_4 = new Gracz(4, imiona[i], kolory[i]);
        lista_graczy.add(Gracz_4);
      } else {
        System.out.println("Za dużo graczy: " + i);
      }
    }
    */

    int p;
    for (int i = nr_gracza_rozpoczynajacego; i < nr_gracza_rozpoczynajacego + liczba_graczy; i++) {
      p = (i - 1) % liczba_graczy;
      Gracz G = new Gracz(p + 1, imiona[p], kolory[p]);
      lista_graczy.add(G);
    }

    kolejka = 0;

    Mapa = new Mapa();
    Mapa.losuj_wartosci_i_zasoby_na_polach();
    /* RYSUJ MAPĘ TODO */


    for (Gracz G : lista_graczy) {
      /* jakaś funkcja łapiąca wybrany przez gracza wierzchołek W */
      G.pierwsza_osada();
      /* analogiczna funkcja co do osady, teraz dla krawędzi -- drogi */
      G.pierwsza_droga();
    }
    for (int i = liczba_graczy - 1; i >= 0; i--)
    {
      Gracz G = lista_graczy.get(i);
      G.druga_osada();
      G.druga_droga();
    }
    pierwsza_runda_rozdaj_surowce();
    nowa_runda();



  };

  public void pierwsza_runda_rozdaj_surowce() {
    for (int i = 2; i <= 12; i++)
      rozdaj_surowce(i);
  }
  public void wypisz_podium(){
    /* TODO */
  }
  public void ostatnia_kolejka(){
    while (kolejka++ < liczba_graczy)
    {
      wylosuj_i_rozdaj_surowce();
      /* FUNKCJE AKJI GRACZA */
      /* TODO */
    }
    wypisz_podium();
    return;

  }
  public void wylosuj_i_rozdaj_surowce(){
    int kostka_pierwsza, kostka_druga, suma_na_kostkach;

    kostka_pierwsza = (int) (Math.random() * 6 + 1);
    kostka_druga = (int) (Math.random() * 6 + 1);
    suma_na_kostkach = kostka_pierwsza + kostka_druga;
    rozdaj_surowce(suma_na_kostkach);
    return;
  }
  public void nowa_runda(){
    wylosuj_i_rozdaj_surowce();
    /* TUTAJ TRZEBA ZAIMPLEMENTOWAĆ WSZYSKIE DZIAŁANIA JAKIE MOŻE PODJĄĆ GRACZ I POWIĄZAĆ Z NIMI DZIAŁANIA NA MAPIE */

    if (czy_mamy_zwyciezce())
    {
      ostatnia_kolejka();
    }
    kolejka = (kolejka + 1) % liczba_graczy;
    nowa_runda();
    return;
  }
  public void rozdaj_surowce(int suma_na_kostkach){
    String surowiec;
    for (Pole P : this.Mapa.lista_pol)
    {
      if (P.wartosc == suma_na_kostkach)
      {
        surowiec = P.surowiec;
        if (!P.zlodziej)
        {
          for (Wierzcholek W : P.lista_wierzcholkow)
          {
            if (!W.is_empty)
            {
              daj_graczowi_nr(W.nr_gracza, surowiec, W.budynek);
            }
          }
        }
      }
    }
    return;
  }
  public void daj_graczowi_nr(int nr, String surowiec, int ile){
    Gracz G = lista_graczy.get((nr + (liczba_graczy - nr_gracza_rozpoczynajacego)) % liczba_graczy);
    G.dodaj(ile,surowiec);
    /*
    if (nr == 1)
    {
      Gracz_1.dodaj(ile, surowiec);
    } else if (nr == 2) {
      Gracz_2.dodaj(ile, surowiec);
    } else if (nr == 3) {
      Gracz_3.dodaj(ile, surowiec);
    } else if (nr == 4) {
      Gracz_4.dodaj(ile, surowiec);
    } else {
      System.out.println("Błąd w przydzielaniu zapłaty, dla gracza numer: " + nr + " należało się: " + ile + " " + surowiec);
    }
    */
    return;
  }

}
