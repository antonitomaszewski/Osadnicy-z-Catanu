public class Surowce {
    public int owca = 0;
    public int siano = 0;
    public int drewno = 0;
    public int cegla = 0;
    public int kamien = 0;

    public Surowce()
    {
      owca = 0;
      siano = 0;
      drewno = 0;
      cegla = 0;
      kamien = 0;
    }

    public void dodaj(int ile, String surowiec)
    {
      if (surowiec == "owca")
      {
        this.owca += ile;
      } else if (surowiec == "siano") {
        this.siano += ile;
      } else if (surowiec == "drewno") {
        this.drewno += ile;
      } else if (surowiec == "cegla") {
        this.cegla += ile;
      } else if (surowiec == "kamien") {
        this.kamien += ile;
      } else {
        System.out.println("Coś poszło nie tak przy dodawaniu surowca " + ile + " " + surowiec);
      }
    }
    public void zabierz(int ile, String surowiec)
    {
      if (surowiec == "owca")
      {
        this.owca -= ile;
      } else if (surowiec == "siano") {
        this.siano -= ile;
      } else if (surowiec == "drewno") {
        this.drewno -= ile;
      } else if (surowiec == "cegla") {
        this.cegla -= ile;
      } else if (surowiec == "kamien") {
        this.kamien -= ile;
      } else {
        System.out.println("Coś poszło nie tak przy zabieraniu surowca " + ile + " " + surowiec);
      }
    }

    /*
    public void dodaj_owca(int ile)
    {
      this.owca += ile;
      return;
    }
    public void dodaj_siano(int ile)
    {
      this.siano += ile;
      return;
    }
    public void dodaj_drewno(int ile)
    {
      this.drewno += ile;
      return;
    }
    public void dodaj_cegla(int ile)
    {
      this.cegla += ile;
      return;
    }
    public void dodaj_kamien(int ile)
    {
      this.kamien += ile;
      return;
    }
    public void zabierz_owca(int ile)
    {
      this.owca -= ile;
      return;
    }
    public void zabierz_siano(int ile)
    {
      this.siano -= ile;
      return;
    }
    public void zabierz_drewno(int ile)
    {
      this.drewno -= ile;
      return;
    }
    public void zabierz_cegla(int ile)
    {
      this.cegla -= ile;
      return;
    }
    public void zabierz_kamien(int ile)
    {
      this.kamien -= ile;
      return;
    }
    */
}
