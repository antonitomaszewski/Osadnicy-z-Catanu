import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class PanelMapa extends JLabel {


public static String s = new String();
// public static String d = new String();

public PanelMapa() {

}

protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        //NAPIS NA GÓRZE
        g2d.setFont(new Font("ArialBlack", Font.ITALIC, 50));
        s = "Runda ";
        s = s + Integer.toString(Gra.runda) + "    Gracz " + Gra.lista_graczy.get(Gra.kolejka).imie;
        if(Gra.czy_mozna_losowac)
                s += "   Rzuć kośćmi";
        if (Gra.ustaw_zlodzieja)
                s += "   Ustaw złodzieja";
        if (Gra.koniec_gry)
                s = "Koniec Gry, LESZCZU PRZEGRAŁ";
        // if (Gra.czas_pobierania_wspolrzednych)
        //         s += "   Wybierz współrzędne";
        // if (Gra.czas_akcji_gracza)
        //         s += "   Możesz podjąć dozwolone akcje lub zakończyć rundę";
        FontMetrics metrics = g.getFontMetrics(g2d.getFont());
        int xs =(Okno.size_width - 2*Okno.label_gracza_width - metrics.stringWidth(s))/2;
        g2d.drawString(s, xs, (int)Okno.size_hight/20 + 5);


        //rysujemy wszystkie pola
        // for(Pole p : Mapa.lista_pol){
        //     Polygon szesciakat = new Polygon();
        //     for(Wierzcholek w : p.lista_wierzcholkow){
        //         szesciakat.addPoint(w.x, w.y);
        //     }
        //     g2d.setColor(p.kolor);
        //     g2d.fill(szesciakat);
        // }
        for(Pole p : Mapa.lista_pol) {
                Polygon szesciakat = new Polygon();
                for(Wierzcholek w : p.lista_wierzcholkow) {
                        szesciakat.addPoint(w.x, w.y);
                }
                g2d.setColor(p.kolor);
                g2d.fill(szesciakat);
                if(p.zlodziej) {
                        g2d.setColor(Color.WHITE);
                        int c = (int)Okno.size_width/36;
                        g2d.setStroke(new BasicStroke(10));
                        g2d.drawLine(p.x - c, p.y - c, p.x + c, p.y + c);
                        g2d.drawLine(p.x - c, p.y + c, p.x + c, p.y - c);
                        g2d.setColor(Color.RED);
                        g2d.setStroke(new BasicStroke(5));
                        g2d.drawLine(p.x - c, p.y - c, p.x + c, p.y + c);
                        g2d.drawLine(p.x - c, p.y + c, p.x + c, p.y - c);

                }
        }


        //rysujemy wszystkie liczby
        for(Pole p : Mapa.lista_pol) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("ArialBlack", Font.PLAIN, 40));
                int x_pom = p.x - metrics.stringWidth(Integer.toString(p.wartosc))/2;
                int y_pom = p.y - metrics.getHeight()/2 + metrics.getAscent();
                g2d.drawString(Integer.toString(p.wartosc), x_pom, y_pom);
        }

        //rysujemy wszystkie drogi
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(10));
        for(Krawedz k : Mapa.lista_krawedzi) {
                g2d.drawLine(k.x1, k.y1, k.x2, k.y2);
        }

        //rysujemy zajete drogi
        g2d.setStroke(new BasicStroke(5));
        for(Krawedz k : Mapa.lista_krawedzi) {
                if(!k.is_empty) {
                        g2d.setColor(k.kolor_gracza);
                        g2d.drawLine(k.x1, k.y1, k.x2, k.y2);
                }
        }

        //rysujemy możliwe drogi
        if(Gra.budujemy_droge) {
                for(Krawedz k : Gra.dostepne_drogi) {
                        g2d.setColor(new Color(218, 218, 218));
                        g2d.drawLine(k.x1, k.y1, k.x2, k.y2);
                }
        }

        int length = (int)Okno.size_width/18;
        int s20 = (int)length/7;
        int s30 = s20*3/2;
        int s40 = s20*2;
        int s5 = s20/4;
        int s3 = s30/10;


        //rysujemy osady
        for(Wierzcholek w : Mapa.lista_wierzcholkow) {
                if(!w.is_empty && w.budynek == 1) {
                        g2d.setColor(w.kolor_gracza);
                        Polygon osada = new Polygon();
                        osada.addPoint(w.x - s30, w.y + s20);
                        osada.addPoint(w.x - s30, w.y - s20);
                        osada.addPoint(w.x, w.y - s40);
                        osada.addPoint(w.x + s30, w.y - s20);
                        osada.addPoint(w.x + s30, w.y + s20);
                        g2d.fill(osada);
                        g2d.setColor(Color.WHITE);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.draw(osada);
                }
        }

        //rysujemy miasta
        for(Wierzcholek w : Mapa.lista_wierzcholkow) {
                if(!w.is_empty && w.budynek == 2) {
                        g2d.setColor(w.kolor_gracza);
                        Polygon miasto = new Polygon();
                        miasto.addPoint(w.x - s40, w.y + s30);
                        miasto.addPoint(w.x - s40, w.y - s20);
                        miasto.addPoint(w.x - s20, w.y - s40);
                        miasto.addPoint(w.x + s3, w.y - s20);
                        miasto.addPoint(w.x + s3, w.y - s5);
                        miasto.addPoint(w.x + s30, w.y - s5);
                        miasto.addPoint(w.x + s30, w.y + s30);
                        g2d.fill(miasto);
                        g2d.setColor(Color.WHITE);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.draw(miasto);
                }
        }

        //rysujemy możliwe osady
        if(Gra.budujemy_osade) {
                for(Wierzcholek w : Gra.dostepne_osady) {
                        g2d.setColor(new Color(218, 218, 218));
                        Polygon osada = new Polygon();
                        osada.addPoint(w.x - s30, w.y + s20);
                        osada.addPoint(w.x - s30, w.y - s20);
                        osada.addPoint(w.x, w.y - s40);
                        osada.addPoint(w.x + s30, w.y - s20);
                        osada.addPoint(w.x + s30, w.y + s20);
                        g2d.fill(osada);
                        g2d.setColor(Color.WHITE);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.draw(osada);
                }
        }

        //rysujemy możliwe miasta
        if(Gra.budujemy_miasto) {
                for(Wierzcholek w : Gra.dostepne_miasta) {
                        g2d.setColor(new Color(218, 218, 218));
                        Polygon miasto = new Polygon();
                        miasto.addPoint(w.x - s40, w.y + s30);
                        miasto.addPoint(w.x - s40, w.y - s20);
                        miasto.addPoint(w.x - s20, w.y - s40);
                        miasto.addPoint(w.x + s3, w.y - s20);
                        miasto.addPoint(w.x + s3, w.y - s5);
                        miasto.addPoint(w.x + s30, w.y - s5);
                        miasto.addPoint(w.x + s30, w.y + s30);
                        g2d.fill(miasto);
                        g2d.setColor(Color.WHITE);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.draw(miasto);

                }
        }

        //CENNIK NA DOLE
        g2d.setColor(new Color(217, 217, 217));
        g2d.fillRect(0, Okno.size_hight - 50, Okno.size_width - 2*Okno.label_gracza_width, 50);

        int mid = Okno.size_hight - 25;
        int b = (Okno.size_width - 2*Okno.label_gracza_width)/44;

        //droga
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(2*b, mid, 6*b, mid);
        g2d.setStroke(new BasicStroke(6));
        g2d.setColor(Color.BLUE);
        g2d.drawLine(2*b, mid, 6*b, mid);

        //=
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(7*b, mid-5, 8*b, mid-5);
        g2d.drawLine(7*b, mid+5, 8*b, mid+5);

        //[] []
        g2d.setColor(new Color(173, 92, 34));
        g2d.fillRect(9*b, mid-b/2, b, b);
        g2d.setColor(new Color(76, 128, 20));
        g2d.fillRect(11*b, mid-b/2, b, b);


        //osada
        int x = 33*b/2;
        Polygon osada = new Polygon();
        osada.addPoint(x - s30, mid + s20);
        osada.addPoint(x - s30, mid - s20);
        osada.addPoint(x, mid - s40);
        osada.addPoint(x + s30, mid - s20);
        osada.addPoint(x + s30, mid + s20);
        g2d.setColor(Color.BLUE);
        g2d.fill(osada);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(osada);

        //=
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(18*b, mid-5, 19*b, mid-5);
        g2d.drawLine(18*b, mid+5, 19*b, mid+5);

        //[][][][]
        g2d.setColor(new Color(144, 255, 106));
        g2d.fillRect(20*b, mid-b/2, b, b);
        g2d.setColor(new Color(255, 228, 95));
        g2d.fillRect(22*b, mid-b/2, b, b);
        g2d.setColor(new Color(173, 92, 34));
        g2d.fillRect(24*b, mid-b/2, b, b);
        g2d.setColor(new Color(76, 128, 20));
        g2d.fillRect(26*b, mid-b/2, b, b);


        //miasto
        x=61*b/2;
        Polygon miasto = new Polygon();
        miasto.addPoint(x - s40, mid + s30);
        miasto.addPoint(x - s40, mid - s20);
        miasto.addPoint(x - s20, mid - s40);
        miasto.addPoint(x + s3, mid - s20);
        miasto.addPoint(x + s3, mid - s5);
        miasto.addPoint(x + s30, mid - s5);
        miasto.addPoint(x + s30, mid + s30);
        g2d.setColor(Color.BLUE);
        g2d.fill(miasto);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(miasto);

        //=
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(32*b, mid-5, 33*b, mid-5);
        g2d.drawLine(32*b, mid+5, 33*b, mid+5);

        //[][][][][]
        g2d.setColor(new Color(255, 228, 95));
        g2d.fillRect(34*b, mid-b/2, b, b);
        g2d.fillRect(36*b, mid-b/2, b, b);
        g2d.setColor(new Color(158, 158, 164));
        g2d.fillRect(38*b, mid-b/2, b, b);
        g2d.fillRect(40*b, mid-b/2, b, b);
        g2d.fillRect(42*b, mid-b/2, b, b);





}
}
