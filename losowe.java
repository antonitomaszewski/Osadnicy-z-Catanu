import java.util.ArrayList;
import java.util.Random;

public void losuj_wartosci_i_zasoby_na_polach()
{
  Random randomGenerator;
  ArrayList<int> wartosci = new ArrayList<int>();
  //int[][] wartosci = new int[11][2];
  ArrayList<String> surowce = new ArrayList<surowce>();
  //String[] surowce = new String[5];
  ArrayList<Color> surowce_kolory = new ArrayList<Color>();
  //Color[] surowce_kolory = new Color[5];
  ArrayList<Pair<int, int>> surowce_inty = new ArrayList<Pair<int, int>>();
  //int[] surowce_inty = new int[5][2];
  Pair p;
  int pozycja;


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
      wartosci.add(wartosc);
      wartosci.add(wartosc);
      pozycja++;
    }
  }
  pozycja = 0;

  surowce.add("owca");
  surowce_kolory.add(Color.Green);
  p = new Pair(0,4);
  surowce_inty.add(p);


  surowce.add("siano");
  surowce_kolory.add(Color.Yellow);
  p = new Pair(1,4);
  surowce_inty.add(p);


  surowce.add("drewno");
  p = new Pair(2,4);
  surowce_inty.add(p);
  surowce_kolory.add(Color.Brown);

  surowce.add("cegla");
  p = new Pair(3,3);
  surowce_inty.add(p);
  surowce_kolory.add(Color.Red);

  surowce.add("kamien");
  p = new Pair(4,4);
  surowce_inty.add(p);
  surowce_kolory.add(Color.Grey);


  surowce_inty.get(rand)[1] += 1;

  int index;
  int index2;

  for (Pole P : this.lista_pol)
  {

    index = randomGenerator.nextInt(wartosci.size());  // wartosc na polu
    P.wartosc = index;

    index2 = randomGenerator.nextInt(surowce.size());  // surowiec na polu
    P.surowiec = surowce.get(index2);
    P.kolor = surowce_kolory.get(index2);
    if (surowce_inty.get(index2)[1] > 1)
    {
      surowce_inty.get(index2)[1]--;
    } else {
      surowce_inty.remove(index2);
      surowiec.remove(index2);
      surowce_kolory.remove(index2);
    }


  }

}
