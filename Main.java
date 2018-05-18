public class Main{

  public static void main(String[] args) {

    /* STWÓRZ MAPĘ */

    int X, Y, length, high, err;  /* length i high oznaczają odpowiednio długość i wysokość trójkąta równobocznego, wyciętego z sześcikąta foremnego */
    double rotation, default_rotation;
    X = 0;
    Y = 0;
    length = 100;
    high = (int) (length * (Math.cos(Math.PI/6)));
    err = 10;

    rotation = Math.PI/3;   /* krok to 60 stopni */
    default_rotation = Math.PI/6;

    Mapa Map = new Mapa();
    Pole P0 = new Pole(X,Y);
    Map.lista_pol.add(P0);

    // Dla pola P0 tworzymy 6 wierzchołków
    for (int i = 0; i < 6; i++)
    {
      int dx = (int) (Math.sin(i * rotation) * length);
      int dy = (int) (Math.cos(i * rotation) * length);
      Wierzcholek w = new Wierzcholek(dx, dy);
      Map.lista_wierzcholkow.add(w);
      P0.lista_wierzcholkow.add(w);

      int dx2 = (int) (Math.sin(((i + 1) % 6) * rotation) * length);
      int dy2 = (int) (Math.cos(((i + 1) % 6) * rotation) * length);
      Krawedz k = new Krawedz(dx, dy, dx2, dy2);
      Map.lista_krawedzi.add(k);
      P0.lista_wierzcholkow.add(k);
    }

    /* Tworzę 6 pól które bezpośrednio przylegają do P0, dla każdego tworzę krawędzie i wierzchołki, których jeszcze nie ma, dodając je do Mapy */
    for (int i = 0; i < 6; i++)
    {
      int px,py;
      px = (int) (Math.sin(i * rotation + default_rotation) * 2 * high);
      py = (int) (Math.cos(i * rotation + default_rotation) * 2 * high);
      Pole P_i = new Pole(px, py);
      Map.lista_pol.add(P_i);

      /* Dla każdego z pól P_i liczymy sześć współrzędnych wierzchołków/krawędzi i jeśli z pewną dokładnośią są już w liście współrzędny/krawędzi w Mapie, to wartość ustawiamy na tę znalezioną (ewentualnie uśredniamy i w obu zmieniamy), w przeciwnym przypadku towrzymy nowy wierzchołek/krawędz i dodajemy do listy_wierzchołków/listy_krawędzi w P_i oraz Map */
      for (int j = 0; j < 6; j++)
      {
        int dx, dy, dx2, dy2;
        boolean czy_znalazl_podobny;

        dx = (int) (Math.sin(j * rotation) * length);
        dy = (int) (Math.cos(j * rotation) * length);
        dx2 = (int) (Math.sin(((j + 1) % 6) * rotation) * length);
        dy2 = (int) (Math.cos(((j + 1) % 6) * rotation) * length);

        // Sprawdzam czy punkt jest w liście wierzchołków
        czy_znalazl_podobny = false
        for (Wierzcholek W : Map.lista_wierzcholkow)
        {
          if (Math.abs(W.x - dx) < err && Math.abs(W.y - dy) < err)
          {
            czy_znalazl_podobny = true;
            W.x = (int) ((W.x + dx) / 2);
            W.y = (int) ((W.y + dy) / 2);
            P_i.lista_wierzcholkow.add(W);
            break;
          }
        }
        if (!czy_znalazl_podobny)
        {
          Wierzcholek W = new Wierzcholek(dx, dy);
          P_i.lista_wierzcholkow.add(W);
          Map.lista_wierzcholkow.add(W);
        }

        // Sprawdzam czy punkt jest w liście krawędzi
        czy_znalazl_podobny = false;
        for (Krawedz K: Map.lista_krawedzi)
        {
          if (((Math.abs(K.x1 - dx) < err && Math.abs(K.y1 - dy) < err) && Math.abs(K.x2 - dx2) < err && Math.abs(K.y2 - dy2) < err)
          {
            czy_znalazl_podobny = true;
            K.x1 = (int) ((K.x1 + dx) /2);
            K.y1 = (int) ((K.y1 + dy) /2);
            K.x2 = (int) ((K.x2 + dx2) /2);
            K.y2 = (int) ((K.y2 + dy2) /2);
            P_i.lista_krawedzi.add(K);
            break;
          } else if ((Math.abs(K.x2 - dx) < err && Math.abs(K.y2 - dy) < err) && Math.abs(K.x1 - dx2) < err && Math.abs(K.y1 - dy2) < err))
          {
            czy_znalazl_podobny = true;
            K.x1 = (int) ((K.x1 + dx2) /2);
            K.y1 = (int) ((K.y1 + dy2) /2);
            K.x2 = (int) ((K.x2 + dx) /2);
            K.y2 = (int) ((K.y2 + dy) /2);
            P_i.lista_krawedzi.add(K);
            break;
          }
        }
        if (!czy_znalazl_podobny)
        {
          Krawedz K = new Krawedz(dx, dy, dx2, dy2);
          P_i.lista_krawedzi.add(K);
          Map.lista_krawedzi.add(K);
        }
      }
    }


  }
}
