public class Main{

  public static void main(String[] args) {

    Mapa Map = new Mapa();
    int i = 0;
    for (Pole P : Map.lista_pol)
    {
      //System.out.println(P.x + " " + P.y + '\n');
      i++;
      int k = 0;
      int w = 0;
      int[][] wierzcholki = new int[6][2];
      int[][] krawedzie = new int[6][4];
      for (Wierzcholek W : P.lista_wierzcholkow)
      {
        wierzcholki[w][0] = W.x;
        wierzcholki[w][1] = W.y;
        w++;
        System.out.println(W.x + " " + W.y);
      }

      for (Krawedz K : P.lista_krawedzi)
      {
        krawedzie[k][0] = K.x1;
        krawedzie[k][1] = K.y1;
        krawedzie[k][2] = K.x2;
        krawedzie[k][3] = K.y2;
        k++;
        System.out.println(K.x1 + " " + K.y1 + " " + K.x2 + " " + K.y2);
      }
      System.out.println("krawedzie: " + k + " wierzchołki: " + w);
    }
    System.out.println("liczba pól = " + i + "\n\n");

    i = 0;
    for (Wierzcholek W : Map.lista_wierzcholkow)
    {
      //System.out.println(W.x + " " + W.y + "\n");
      i++;
    }
    System.out.println("liczba wierzchołków = " + i + "\n\n");

    i = 0;
    for (Krawedz K : Map.lista_krawedzi)
    {
      //System.out.println(K.x1 + " " + K.y1 + "  " + K.x2 + " " + K.y2 + "\n");
      i++;
    }
    System.out.println("Liczba krawędzi = " + i + "\n\n");


    for (Pole P : Map.lista_pol)
    {
      i = 0;
      for (Wierzcholek W : P.lista_wierzcholkow)
      {
        i++;
      }
      if (i != 6)
      {
        System.out.println("Za mało Wierzchołków = " + i);
      }
      i = 0;
      for (Krawedz K : P.lista_krawedzi)
      {
        i++;
      }
      if (i != 6)
      {
        System.out.println("Za mało Krawędzi = " + i);
      }
    }

  }
}
