import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

public class Mapa {
    public static ArrayList<Pole> lista_pol = new ArrayList<Pole>();
    public static ArrayList<Krawedz> lista_krawedzi = new ArrayList<Krawedz>();
    public static ArrayList<Wierzcholek> lista_wierzcholkow = new ArrayList<Wierzcholek>();


    public void losuj_wartosci_i_zasoby_na_polach()
    {
    //Random randomGenerator = new Random();
      ArrayList<Integer> wartosci = new ArrayList<Integer>();
      //int[][] wartosci = new int[11][2];
      ArrayList<String> surowce = new ArrayList<String>();
      //String[] surowce = new String[5];
      ArrayList<Color> surowce_kolory = new ArrayList<Color>();
      //Color[] surowce_kolory = new Color[5];
      ArrayList<Integer> surowce_inty = new ArrayList<Integer>();
      ArrayList<Integer> surowce_inty_ile = new ArrayList<Integer>();
      //int[] surowce_inty = new int[5][2];
      int pozycja;
      int index;
      int index2;

      pozycja = 2;
      //rand = (int) ((Math.random() * ((max - min) + 1)) + min);
      while (pozycja < 13)
      {
        if (pozycja == 2 || pozycja == 7 || pozycja == 12)
        {
          //wartosci.add(wartosc);
          wartosci.add(pozycja);
          pozycja++;
        } else {
          wartosci.add(pozycja);
          wartosci.add(pozycja);
          pozycja++;
        }
      }
      /*
      for (Integer i : wartosci)
        System.out.println(i);
      System.out.println();
      pozycja = 0;
*/
      surowce.add("owca");
      surowce.add("siano");
      surowce.add("drewno");
      surowce.add("cegla");
      surowce.add("kamien");
      /*
      for (String s : surowce)
        System.out.println(s);
      System.out.println();
      */

      surowce_kolory.add(new Color(144, 255, 106));
      surowce_kolory.add(new Color(255, 228, 95));
      surowce_kolory.add(new Color(76, 128, 20));
      surowce_kolory.add(new Color(173, 92, 34));
      surowce_kolory.add(new Color(158, 158, 164));


      surowce_inty.add(0);
      surowce_inty_ile.add(4);

      surowce_inty.add(1);
      surowce_inty_ile.add(4);

      surowce_inty.add(2);
      surowce_inty_ile.add(4);

      surowce_inty.add(3);
      surowce_inty_ile.add(3);

      surowce_inty.add(4);
      surowce_inty_ile.add(3);

      index = (int) (Math.random() * 5 + 1);

/*
      for (Integer i : surowce_inty)
        System.out.println(i);
      System.out.println();
      for (Integer i : surowce_inty_ile)
        System.out.println(i);
      System.out.println();
      */
      int[] inty_ile = new int[5];
      inty_ile[0] = 4;
      inty_ile[1] = 4;
      inty_ile[2] = 4;
      inty_ile[3] = 3;
      inty_ile[4] = 3;
      inty_ile[index]++;
/*
      for (int w = 0; w < 5; w++)
        System.out.println(inty_ile[w]);
*/
      int sur_size = 4;
      int wart_size = 18;
      int w;
      int s;
      //System.out.println(wartosci.size());
      for (Pole P : this.lista_pol)
      {
        /*
        System.out.println();
        for (int w = 0; w < 5; w++)
          System.out.println(inty_ile[w]);
        System.out.println();
        for (Integer i : surowce_inty)
          System.out.println(i);
        System.out.println();
        for (String s : surowce)
          System.out.println(s);
        System.out.println();
        */

        w = (int) (Math.random() * (wart_size + 1));
        s = (int) (Math.random() * (sur_size + 1));  // surowiec na polu
/*
        System.out.println(w);
        System.out.println(s);
        System.out.println(); */
        /*
        for (Integer i : wartosci)
          System.out.println(i);
        System.out.println(); */
        //System.out.println(inty_ile[0] + " " +  inty_ile[1] + " " + inty_ile[2] + " " + inty_ile[3] + " " + inty_ile[4] + "\n\n");

        //wartosci.remove(index);

        P.wartosc = (int) wartosci.get(w);  // wartosc na polu
        P.surowiec = surowce.get(s);
        P.kolor = surowce_kolory.get(s);
        wartosci.remove(w);
        wart_size--;

        //System.out.println("wartosci.size() : " + wartosci.size() + " surowce.size() " + surowce.size() + " wartosc : " + wartosci.get(index) + " ile zostalo intow : " + inty_ile[index2] + '\n');

        if (inty_ile[s] > 1)
        {
          inty_ile[s]--;
        } else {
          surowce_inty.remove(s);
          //surowce_inty_ile.remove(index2);
          surowce.remove(s);
          surowce_kolory.remove(s);
          inty_ile[s] = 0;
          for (int i = s; i < 4; i++)
            inty_ile[i] = inty_ile[i+1];
          inty_ile[sur_size] = 0;
          sur_size--;
        }
      }
    }
    /* STWÓRZ MAPĘ */
    /* DZIAŁA  -- Wyniki 19 Pól, 54 Wierzchołki, 72 krawędzie są dobre */
    public Mapa(){
      int X, Y, length, high, err; /* X, Y współrzędne punktu P0, length i high długość i wysokość trójkąta równobocznego w sześciokącie foremnym, err -- błąd na jaki można sobie pozwolić, przy wyliczaniu współrzędnych */
      double rotation, default_rotation; /* obrót wektora o i * rotation, w przypadku pól typu 2 i 3, potrzebne default_rotation, bo są obkręcone o 30 stopni */

      int dx, dy, dx2, dy2; /* wektory przesunięć z punktu pola do wierzchołków i krawędzi */
      int px, py; /* wektory przesunięć środków pól */

      int[][] Wektory_wierzcholki_krawedzie = new int[7][2];
      int[][] Wektory_pola_typ_1 = new int[6][2];
      int[][] Wektory_pola_typ_2_i_3 = new int[6][2];
      int i, j;

      X = 0;
      Y = 0;
      length = 100;
      high = (int) (length * (Math.cos(Math.PI/6)));
      err = 7;

      rotation = Math.PI/3;   /* krok to 60 stopni */
      default_rotation = Math.PI/6;

      /* liczę wartości wektorów i wrzucam je do tablicy intów, pierwsza rozmiaru 7, aby używać dla krawędzi, tab[0] == tab[6] */
      for (i = 0; i < 7; i++)
      {
        dx = (int) (Math.sin((i % 6) * rotation) * length);
        dy = (int) (Math.cos((i % 6) * rotation) * length);

        Wektory_wierzcholki_krawedzie[i][0] = dx;
        Wektory_wierzcholki_krawedzie[i][1] = dy;
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
      this.lista_pol.add(P0);

      for (i = 0; i < 6; i++)
      {
        dx = Wektory_wierzcholki_krawedzie[i][0];
        dy = Wektory_wierzcholki_krawedzie[i][1];
        dx2 = Wektory_wierzcholki_krawedzie[i + 1][0];
        dy2 = Wektory_wierzcholki_krawedzie[i + 1][1];
        Wierzcholek W = new Wierzcholek(X + dx, Y + dy);
        Krawedz K = new Krawedz(X + dx, Y + dy, X + dx2, Y + dy2);

        P0.lista_wierzcholkow.add(W);
        P0.lista_krawedzi.add(K);
        this.lista_wierzcholkow.add(W);
        this.lista_krawedzi.add(K);
      }

      /* Pola typu 1 wraz z wierzcholkami i krawedziami, tworze i dodaje te ktorych jeszcze nie ma w liscie_wierzcholkow/krawedzi */
      boolean czy_znalazl_podobny;
      for (i = 0; i < 6; i++)
      {
        px = Wektory_pola_typ_1[i][0];
        py = Wektory_pola_typ_1[i][1];
        Pole P_i = new Pole(X + px, Y + py);
        this.lista_pol.add(P_i);

        for (j = 0; j < 6; j++)
        {
          czy_znalazl_podobny = false;
          dx = P_i.x + Wektory_wierzcholki_krawedzie[j][0];
          dy = P_i.y + Wektory_wierzcholki_krawedzie[j][1];
          dx2 = P_i.x + Wektory_wierzcholki_krawedzie[j + 1][0];
          dy2 = P_i.y + Wektory_wierzcholki_krawedzie[j + 1][1];

          for (Wierzcholek W : this.lista_wierzcholkow)
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
            this.lista_wierzcholkow.add(W);
          }

          czy_znalazl_podobny = false;
          for (Krawedz K : this.lista_krawedzi)
          {
            if ((Math.abs(K.x1 - dx) < err && Math.abs(K.y1 - dy) < err) && (Math.abs(K.x2 - dx2) < err && Math.abs(K.y2 - dy2) < err))
            {
              czy_znalazl_podobny = true;
              P_i.lista_krawedzi.add(K);
              break;
            } else if ((Math.abs(K.x2 - dx) < err && Math.abs(K.y2 - dy) < err) && (Math.abs(K.x1 - dx2) < err && Math.abs(K.y1 - dy2) < err))
            {
              czy_znalazl_podobny = true;
              P_i.lista_krawedzi.add(K);
              break;
            }
          }
          if (!czy_znalazl_podobny)
          {
            Krawedz K = new Krawedz(dx, dy, dx2, dy2);
            P_i.lista_krawedzi.add(K);
            this.lista_krawedzi.add(K);
          }
        }
      }

      /* 6 pól typ 2 -- dotykające krawędziami P0 */
      for (i = 0; i < 6; i++)
      {
        px = Wektory_pola_typ_2_i_3[i][0];
        py = Wektory_pola_typ_2_i_3[i][1];

        Pole P_i = new Pole(X + px, Y + py);
        this.lista_pol.add(P_i);

        for (j = 0; j < 6; j++)
        {
          czy_znalazl_podobny = false;
          dx = P_i.x + Wektory_wierzcholki_krawedzie[j][0];
          dy = P_i.y + Wektory_wierzcholki_krawedzie[j][1];
          dx2 = P_i.x + Wektory_wierzcholki_krawedzie[j + 1][0];
          dy2 = P_i.y + Wektory_wierzcholki_krawedzie[j + 1][1];

          for (Wierzcholek W : this.lista_wierzcholkow)
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
              this.lista_wierzcholkow.add(W);
            }

            czy_znalazl_podobny = false;
            for (Krawedz K : this.lista_krawedzi)
            {
              if ((Math.abs(K.x1 - dx) < err && Math.abs(K.y1 - dy) < err) && (Math.abs(K.x2 - dx2) < err && Math.abs(K.y2 - dy2) < err))
              {
                czy_znalazl_podobny = true;
                P_i.lista_krawedzi.add(K);
                break;
              } else if ((Math.abs(K.x2 - dx) < err && Math.abs(K.y2 - dy) < err) && (Math.abs(K.x1 - dx2) < err && Math.abs(K.y1 - dy2) < err))
              {
                czy_znalazl_podobny = true;
                P_i.lista_krawedzi.add(K);
                break;
              }
            }
            if (!czy_znalazl_podobny)
            {
              Krawedz K = new Krawedz(dx, dy, dx2, dy2);
              P_i.lista_krawedzi.add(K);
              this.lista_krawedzi.add(K);
            }
          }
        }

        /* 6 pól typu 3 -- najbardziej oddalone od P0 */
        for (i = 0; i < 6; i++)
        {
          px = Wektory_pola_typ_2_i_3[i][0] * 2;
          py = Wektory_pola_typ_2_i_3[i][1] * 2;

          Pole P_i = new Pole(X + px, Y + py);
          this.lista_pol.add(P_i);

          for (j = 0; j < 6; j++)
          {
            czy_znalazl_podobny = false;
            dx = P_i.x + Wektory_wierzcholki_krawedzie[j][0];
            dy = P_i.y + Wektory_wierzcholki_krawedzie[j][1];
            dx2 = P_i.x + Wektory_wierzcholki_krawedzie[j + 1][0];
            dy2 = P_i.y + Wektory_wierzcholki_krawedzie[j + 1][1];

            for (Wierzcholek W : this.lista_wierzcholkow)
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
                this.lista_wierzcholkow.add(W);
              }

              czy_znalazl_podobny = false;
              for (Krawedz K : this.lista_krawedzi)
              {
                if ((Math.abs(K.x1 - dx) < err && Math.abs(K.y1 - dy) < err) && (Math.abs(K.x2 - dx2) < err && Math.abs(K.y2 - dy2) < err))
                {
                  czy_znalazl_podobny = true;
                  P_i.lista_krawedzi.add(K);
                  break;
                } else if ((Math.abs(K.x2 - dx) < err && Math.abs(K.y2 - dy) < err) && (Math.abs(K.x1 - dx2) < err && Math.abs(K.y1 - dy2) < err))
                {
                  czy_znalazl_podobny = true;
                  P_i.lista_krawedzi.add(K);
                  break;
                }
              }
              if (!czy_znalazl_podobny)
              {
                Krawedz K = new Krawedz(dx, dy, dx2, dy2);
                P_i.lista_krawedzi.add(K);
                this.lista_krawedzi.add(K);
              }
            }
          }
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
