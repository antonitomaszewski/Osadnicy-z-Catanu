import java.util.ArrayList;

public class Mapa {
    public ArrayList<Pole> lista_pol = new ArrayList<Pole>();
    public ArrayList<Krawedz> lista_krawedzi = new ArrayList<Krawedz>();
    public ArrayList<Wierzcholek> lista_wierzcholkow = new ArrayList<Wierzcholek>();


    /* STWÓRZ MAPĘ */
    /* DZIAŁA  -- Wyniki 19 Pól, 54 Wierzchołki, 72 krawędzie są dobre */
    public Mapa(){
      int X, Y, length, high, err;
      int dx, dy, dx2, dy2;
      double rotation, default_rotation;
      int[][] Wektory_wierzcholki_krawedzie_pola_typ_1 = new int[7][2];
      int[][] Wektory_pola_typ_2_i_3 = new int[6][2];
      int i, j;

      X = 0;
      Y = 0;
      length = 100;
      high = (int) (length * (Math.cos(Math.PI/6)));
      err = 10;

      rotation = Math.PI/3;   /* krok to 60 stopni */
      default_rotation = Math.PI/6;

      for (i = 0; i < 7; i++)
      {
        dx = (int) (Math.sin((i % 6) * rotation) * length);
        dy = (int) (Math.cos((i % 6) * rotation) * length);

        Wektory_wierzcholki_krawedzie_pola_typ_1[i][0] = dx;
        Wektory_wierzcholki_krawedzie_pola_typ_1[i][1] = dy;
      }
      for (i = 0; i < 6; i++)
      {
        dx = (int) (Math.sin(i * rotation + default_rotation) * 2 * high);
        dy = (int) (Math.cos(i * rotation + default_rotation) * 2 * high);

        Wektory_pola_typ_2_i_3[i][0] = dx;
        Wektory_pola_typ_2_i_3[i][1] = dy;
      }


      Pole P0 = new Pole(X,Y);
      this.lista_pol.add(P0);

      for (i = 0; i < 6; i++)
      {
        dx = Wektory_wierzcholki_krawedzie_pola_typ_1[i][0];
        dy = Wektory_wierzcholki_krawedzie_pola_typ_1[i][1];
        dx2 = Wektory_wierzcholki_krawedzie_pola_typ_1[i + 1][0];
        dy2 = Wektory_wierzcholki_krawedzie_pola_typ_1[i + 1][1];
        Wierzcholek W = new Wierzcholek(dx, dy);
        Krawedz K = new Krawedz(dx, dy, dx2, dy2);

        P0.lista_wierzcholkow.add(W);
        P0.lista_krawedzi.add(K);
        this.lista_wierzcholkow.add(W);
        this.lista_krawedzi.add(K);
      }

      /* Pola typu 1 wraz z wierzcholkami i krawedziami, tworze i dodaje te ktorych jeszcze nie ma w liscie_wierzcholkow/krawedzi */
      boolean czy_znalazl_podobny;
      for (i = 0; i < 6; i++)
      {
        dx = 3 * Wektory_wierzcholki_krawedzie_pola_typ_1[i][0];
        dy = 3 * Wektory_wierzcholki_krawedzie_pola_typ_1[i][1];
        Pole P_i = new Pole(X + dx, Y + dy);
        this.lista_pol.add(P_i);

        for (j = 0; j < 6; j++)
        {
          czy_znalazl_podobny = false;
          dx = P_i.x + Wektory_wierzcholki_krawedzie_pola_typ_1[j][0];
          dy = P_i.y + Wektory_wierzcholki_krawedzie_pola_typ_1[j][1];
          dx2 = P_i.x + Wektory_wierzcholki_krawedzie_pola_typ_1[j + 1][0];
          dy2 = P_i.y + Wektory_wierzcholki_krawedzie_pola_typ_1[j + 1][1];

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
        dx = Wektory_pola_typ_2_i_3[i][0];
        dy = Wektory_pola_typ_2_i_3[i][1];

        Pole P_i = new Pole(X + dx, Y + dy);
        this.lista_pol.add(P_i);

        for (j = 0; j < 6; j++)
        {
          czy_znalazl_podobny = false;
          dx = P_i.x + Wektory_wierzcholki_krawedzie_pola_typ_1[j][0];
          dy = P_i.y + Wektory_wierzcholki_krawedzie_pola_typ_1[j][1];
          dx2 = P_i.x + Wektory_wierzcholki_krawedzie_pola_typ_1[j + 1][0];
          dy2 = P_i.y + Wektory_wierzcholki_krawedzie_pola_typ_1[j + 1][1];

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
          dx = Wektory_pola_typ_2_i_3[i][0] * 2;
          dy = Wektory_pola_typ_2_i_3[i][1] * 2;

          Pole P_i = new Pole(X + dx, Y + dy);
          this.lista_pol.add(P_i);

          for (j = 0; j < 6; j++)
          {
            czy_znalazl_podobny = false;
            dx = P_i.x + Wektory_wierzcholki_krawedzie_pola_typ_1[j][0];
            dy = P_i.y + Wektory_wierzcholki_krawedzie_pola_typ_1[j][1];
            dx2 = P_i.x + Wektory_wierzcholki_krawedzie_pola_typ_1[j + 1][0];
            dy2 = P_i.y + Wektory_wierzcholki_krawedzie_pola_typ_1[j + 1][1];

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
